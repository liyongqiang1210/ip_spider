package com.maven.spider.httpclient;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 * 模拟浏览器获取网页信息
 * 
 * @author Li Yongqiang
 *
 */
public class HttpClientRequest {

	// 浏览器请求头User-Agent
	final String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:58.0) Gecko/20100101 Firefox/58.0";

	/**
	 * get方式获取网页内容
	 * 
	 * @param url
	 * @return
	 */
	public String get(String url) {
		// 创建HttpClient对象
		CloseableHttpClient httpClient = HttpClients.createDefault();
		// 响应的内容
		String content = null;
		try {
			// 创建get请求
			HttpGet req = new HttpGet(url);
			req.setHeader("User-Agent", USER_AGENT);
			// 发送get请求
			CloseableHttpResponse res = httpClient.execute(req);
			try {
				// 获取响应实体
				HttpEntity entity = res.getEntity();

				// 响应状态
				int statusCode = res.getStatusLine().getStatusCode();
				System.out.println("Code：" + statusCode);

				// 如果返回200则证明请求成功
				if (statusCode == 200) {
					// 获取网页内容
					content = EntityUtils.toString(entity);
				}
			} finally {
				res.close();
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// 关闭链接，释放资源
			try {
				httpClient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return content;
	}

}
