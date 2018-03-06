package com.maven.spider.testip;

import java.io.IOException;

import org.apache.http.HttpHost;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class TestIP {

	// 浏览器请求头User-Agent
	private static final String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:58.0) Gecko/20100101 Firefox/58.0";

	/**
	 * 测试IP是否可用
	 * 
	 * @param ipAdress
	 * 
	 * @param port
	 * @return
	 */
	public boolean getIPIsAvailable(String ip_address, String ip_prot, String ip_type) {

		// 设置代理IP、端口、协议（请分别替换）
		HttpHost proxy = new HttpHost(ip_address, Integer.valueOf(ip_prot), ip_type);
		// 把代理设置到请求配置
		RequestConfig defaultRequestConfig = RequestConfig.custom().setConnectTimeout(5000)
				.setConnectionRequestTimeout(1000)
				.setSocketTimeout(5000)
				.setProxy(proxy).build();
		// 实例化CloseableHttpClient对象
		CloseableHttpClient httpClient = HttpClients.custom().setDefaultRequestConfig(defaultRequestConfig).build();
		// 访问目标地址
		HttpGet httpGet = new HttpGet("https://www.baidu.com/");
		httpGet.setHeader("User-Agent", USER_AGENT);

		// 获取响应
		CloseableHttpResponse response = null;
		try {
			response = httpClient.execute(httpGet);
			try {
				// 状态码
				int statusCode = response.getStatusLine().getStatusCode();
				if (statusCode == HttpStatus.SC_OK) {

					System.out.println(ip_address + ":" + ip_prot + "=========>可用");
					return true;
				}
			} finally {
				response.close();
			}

		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// 关闭连接,释放资源
			try {
				httpClient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return false;
	}

}
