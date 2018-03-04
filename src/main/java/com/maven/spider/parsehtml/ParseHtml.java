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
import com.maven.spider.testip.TestIP;

/**
 * 使用Jsoup解析页面
 * 
 * @author Li Yongqiang
 *
 */
public class ParseHtml {

	/**
	 * 分析html页面获取需要的数据
	 * 
	 * @param content
	 * @return
	 * @throws Exception
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

			boolean ipIsAvailable = new TestIP().getIPIsAvailable(ipAddress, prot, type);

			System.out.println("ip地址：" + ipAddress + ",测试结果：" + ipIsAvailable);
			
			if (ipIsAvailable) {
				jsonArray.add(ip);
			}

		}
		// 将json数组转为json字符串
		String jsonStr = jsonArray.toJSONString();

		return jsonStr;
	}

	public static void main(String[] args) {
		String string = new GetHtml().get("http://www.xicidaili.com/nn/");
		String jsonData = new ParseHtml().getJsonData(string);
		System.out.println(jsonData);
	}

}
