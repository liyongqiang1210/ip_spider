package com.maven.spider.httpclient;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
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
	private static final String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:58.0) Gecko/20100101 Firefox/58.0";
	private static final String ACCEPT = "text/plain;charset=utf-8"; // 设置请求的报文头部的编码
	private static final String CONTENT_TYPE = "application/x-www-form-urlencoded; charset=utf-8"; // 设置期望服务端返回的编码

	/**
	 * get方式获取网页内容 此方法需要传递的参数为目标网址url和目标网址的编码格式
	 * 
	 * @param url
	 * @param code
	 * @return
	 */
	public static String get(String url, String coding) {
		// 创建HttpClient对象
		CloseableHttpClient httpClient = HttpClients.createDefault();
		// 响应的内容
		String content = null;
		try {
			// 创建get请求
			HttpGet req = new HttpGet(url);
			req.setHeader("User-Agent", USER_AGENT);
			req.setHeader("Accept", ACCEPT);
			req.setHeader("Content-Type", CONTENT_TYPE);
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
					content = EntityUtils.toString(entity, coding);
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

	/**
	 * post请求获取百度推荐新闻需传入参数新闻url以及新闻类型type返回的是json字符串
	 * 
	 * @param url
	 * @param type
	 * @return
	 */
	public static String baiDuTuiJianPost(String url, String type) {
		// 创建HttpClient对象
		CloseableHttpClient httpClient = HttpClients.createDefault();
		// 响应的内容
		String content = null;
		try {
			// 将请求参数追加到url后面
			String str = "&mid=03c7a16f2e8028127e42c5f7ca9e210b&ts=0&topic=" + type + "&type=info&"
					+ "token=info&ln=200&an=10&withtopic=0&wf=0&internet-subscribe=0&ver=4&pd=webapp&nids="
					+ "&remote_device_type=1&os_type=1&screen_size_width=1920&screen_size_height=1080";
			// 创建post请求
			HttpPost req = new HttpPost(url + str);
			// 设置请求头header
			req.setHeader("User-Agent", USER_AGENT);
			req.setHeader("Accept", ACCEPT);
			req.setHeader("Content-Type", CONTENT_TYPE);
			// 发送post请求
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
