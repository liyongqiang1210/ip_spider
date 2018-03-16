package com.maven.spider.parser;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.maven.spider.entity.Article;


/**
 * 技术博客文章解析器
 * 
 * @author Li Yongqiang
 *
 */
public class ArticleParser {

	/**
	 * 获取csdn文章url列表的方法
	 * 
	 * @param content
	 * @param articleType
	 * @param source
	 * @return
	 */
	public static List<Article> getCsdnArcitleList(String content, String articleType, String source) {
		List<Article> list = new ArrayList<Article>();
		// 将网页内容转换成doc格式
		Document doc = Jsoup.parse(content);
		Elements elements = doc.select("ul.feedlist_mod>li.clearfix");
		for (Element element : elements) {
			String title = element.select("div.title>h2>a").text();// 获取文章标题
			String url = element.select("div.title>h2>a").attr("href");// 获取文章标题
			String author = element.select("dd.name>a").text();// 获取文章作者
			String createTime = element.select("dd.time").text();// 获取文章创建时间

			// 创建csdn对象
			Article csdn = new Article(url, title, author, createTime, source, articleType);
			list.add(csdn);
		}

		return list;
	}

	/**
	 * 获取博客园文章url列表的方法
	 * 
	 * @param content
	 * @param articleType
	 * @param source
	 * @return
	 */
	public static List<Article> getCnblogsList(String content, String articleType, String source) {
		List<Article> list = new ArrayList<Article>();
		Document doc = Jsoup.parse(content);
		Elements elements = doc.select("div.post_item_body");
		for (Element element : elements) {

			String title = element.select("h3>a.titlelnk").text();// 获取文章标题
			String url = element.select("h3>a").attr("href");// 获取文章标题
			String author = element.select("a.lightblue").text();// 获取文章作者
			String ct = element.select("div.post_item_foot").text();// 获取文章创建时间
			String createTime = ct.substring(ct.indexOf("于") + 2, ct.indexOf("评") - 1);// 截取我们需要的内容

			// 创建cnblogs对象
			Article cnblogs = new Article(url, title, author, createTime, source, articleType);
			list.add(cnblogs); 
		}
		
		return list;

	}
}
