package com.maven.spider.parser.baidu;

import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
			String releaseTime = getReleaseTime("[d]{2}:[d]{2}", element.select("p.info").text());
			String imageUrl = null;
		}
		return vector;

	}

	/**
	 * 此方法用于根据正则表达式匹配出指定字符串 需传入正则表达式与要匹配的文本信息
	 * 
	 * @param info
	 *            文本信息
	 * @param regex
	 *            正则表达式
	 * @return
	 */
	public static String getReleaseTime(String regex, String info) {

		Pattern p = Pattern.compile(regex); // 创建Pattern对象
		Matcher m = p.matcher(regex);
		String releaseTime = null;
		while (m.find()) {
			releaseTime = m.group(); // 获取匹配到的字符串
		}
		return releaseTime;
	}
}
