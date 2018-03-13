package com.maven.spider.parser;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.maven.spider.entity.CSDN;

/**
 * csdn解析器
 * 
 * @author Li Yongqiang
 *
 */
public class ParserCsdn {

	private static final String source = "CSDN";

	/**
	 * 获取csdn文章列表的方法
	 * 
	 * @param csdn_url
	 * @param articleType
	 * @return
	 */
	public static List<CSDN> getCsdnArcitleList(String content, String articleType) {
		List<CSDN> list = new ArrayList<CSDN>();
		// 将网页内容转换成doc格式
		Document doc = Jsoup.parse(content);
		Elements elements = doc.select("li.clearfix");
		for (Element element : elements) {
			String title = element.select("div.title>h2>a").text();// 获取文章标题
			String url = element.select("div.title>h2>a").attr("href");// 获取文章标题
			String author = element.select("dd.name>a").text();// 获取文章作者
			String createTime = element.select("dd.time").text();// 获取文章创建时间

			// 创建csdn对象
			CSDN csdn = new CSDN(url, title, author, createTime, source, articleType);
			list.add(csdn);
		}
		
		return list;
	}
}
