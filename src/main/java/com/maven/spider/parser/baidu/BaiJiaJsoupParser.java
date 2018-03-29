package com.maven.spider.parser.baidu;

import java.util.Vector;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.maven.spider.entity.BaiJiaHao;

public class BaiJiaJsoupParser {

	public static Vector<BaiJiaHao> getBaiJiaListData(String content) {
		Vector<BaiJiaHao> vector = new Vector<BaiJiaHao>();
		
		Document doc = Jsoup.parse(content); // 将html页面转换成doc文档
		Elements elements = doc.select("div#articleList>div.article-info"); // 用此方法筛选出我们需要的文档标签集合
		for (Element element : elements) {

			String title = element.select("div.title>a").text();
			String url = "https://baijia.baidu.com" + element.select("div.title>a").attr("href");
			String author = element.select("div.author>a").text();
			String authorUrl = element.select("div.author>a").attr("href");
			String releaseTime = element.select("p.info").text();
		}
		return null;
		
	}
}
