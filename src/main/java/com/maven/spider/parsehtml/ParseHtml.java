package com.maven.spider.parsehtml;

import java.util.UUID;

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
			String ipAddress = element.select("td:eq(1)").toString();
			String prot = element.select("td:eq(2)").toString();
			String serverAddress = element.select("td:eq(3)").toString();
			String isAnonymous = element.select("td:eq(4)").toString();
			String type = element.select("td:eq(5)").toString();
			
			IP ip = new IP(id, ipAddress, prot, serverAddress, isAnonymous, type);
			jsonArray.add(ip);
		}
		// 将json数组转为json字符串
		String jsonStr = jsonArray.toJSONString(); 
		
		return jsonStr;
	}

}
