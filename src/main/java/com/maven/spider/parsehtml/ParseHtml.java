package com.maven.spider.parsehtml;

import java.io.IOException;
import java.util.UUID;

import org.apache.http.HttpHost;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.alibaba.fastjson.JSONArray;
import com.maven.spider.entity.IP;
import com.maven.spider.gethtml.GetHtml;

/**
 * 使用Jsoup解析页面
 * 
 * @author Li Yongqiang
 *
 */
public class ParseHtml {

	// 浏览器请求头User-Agent
	final String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:58.0) Gecko/20100101 Firefox/58.0";

	/**
	 * 分析html页面获取需要的数据
	 * 
	 * @param content
	 * @return
	 */
	public String getJsonData(String content) {
		// 创建json数组对象
		JSONArray jsonArray = new JSONArray();
		// 将content转化为doc文档
		Document doc = Jsoup.parse(content);
		//
		Elements elements = doc.select("tr.odd");
		for (Element element : elements) {
			// 生成UUID
			String id = UUID.randomUUID().toString();
			String ipAddress = element.select("td:eq(1)").text();
			String prot = element.select("td:eq(2)").text();
			String serverAddress = element.select("td:eq(3)").select("a").text();
			String isAnonymous = element.select("td:eq(4)").text();
			String type = element.select("td:eq(5)").text();

			IP ip = new IP(id, ipAddress, prot, serverAddress, isAnonymous, type);
			boolean ipIsAvailable = this.getIPIsAvailable(ipAddress, prot, type);
			
			// 如果ip地址可用那么添加到jsonArray中
			if(ipIsAvailable){
				jsonArray.add(ip);
			}
			
		}
		// 将json数组转为json字符串
		String jsonStr = jsonArray.toJSONString();

		return jsonStr;
	}

	/**
	 * 判断IP是否可用
	 * 
	 * @param ipAdress
	 * 
	 * @param port
	 * @return
	 */
	public boolean getIPIsAvailable(String ipAddress, String prot, String type) {
		// 创建httpClient对象
		CloseableHttpClient httpClient = HttpClients.createDefault();
		
		HttpHost proxy = new HttpHost(ipAddress, Integer.valueOf(prot), type);
		RequestConfig config = RequestConfig.custom().setProxy(proxy).build();
		HttpGet httpGet = new HttpGet("https://www.baidu.com/");
		httpGet.setHeader("User-Agent", USER_AGENT);
		httpGet.setConfig(config);
		// 获取响应
		CloseableHttpResponse response = null;
		try {

			response = httpClient.execute(httpGet);

			// 状态码
			int statusCode = response.getStatusLine().getStatusCode();
			if (statusCode == 200) {
				System.out.println(ipAddress + ":" + prot + "=========>可用");
				return true;
			}

		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (response != null) {
				try {
					response.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (httpClient != null) {
				try {
					httpClient.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		return false;
	}

	public static void main(String[] args) {
		String string = new GetHtml().get("http://www.xicidaili.com/nn/");
		String jsonData = new ParseHtml().getJsonData(string);
		System.out.println(jsonData);
	}

}
