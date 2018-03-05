package com.maven.spider.testip;

import java.io.IOException;

import org.apache.http.HttpHost;
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
	 * 判断IP是否可用
	 * 
	 * @param ipAdress
	 * 
	 * @param port
	 * @return
	 */
	public boolean getIPIsAvailable(String ip_address, String ip_prot, String ip_type) {
		// 创建httpClient对象
		CloseableHttpClient httpClient = HttpClients.createDefault();

		// 设置代理
		HttpHost proxy = new HttpHost(ip_address, Integer.valueOf(ip_prot),ip_type);
		RequestConfig config = RequestConfig.custom().setProxy(proxy).build();
		HttpGet httpGet = new HttpGet("http://www.runoob.com/");
		httpGet.setConfig(config);
		httpGet.setHeader("User-Agent", USER_AGENT);

		// 获取响应
		CloseableHttpResponse response = null;
		try {
			response = httpClient.execute(httpGet);
			try {
				// 状态码
				int statusCode = response.getStatusLine().getStatusCode();
				if (statusCode == 200) {
					
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
