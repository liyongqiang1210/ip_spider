package com.maven.spider.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.maven.spider.entity.News;
import com.maven.spider.util.DateUtil;

/**
 * 新闻页面解析器类
 * 
 * @author Li Yongqiang
 *
 */
public class NewsJsoupParser {

	// 获取今天的日期
	private static final String dateTime = DateUtil.getYMD();
	
	/**
	 * 获取当天最新新闻url列表
	 * @param content
	 * @param newsSourceWebsite
	 * @param newsType
	 * @return
	 */
	public static List<News> getNewsList(String content,String newsSourceWebsite,String newsType){
		
		List<News> list = new ArrayList<News>();
		Document doc = Jsoup.parse(content);
		Elements elements = doc.select("ul.list_show>li"); // 获取新闻列表集合
		for(Element element:elements){
			String newsId = UUID.randomUUID().toString(); // 获取新闻id
			String newsTitle = element.select("h2>a").text(); // 获取新闻title 
			String newsUrl = element.select("h2>a").attr("href"); // 获取新闻url
			String newsReleaseTime = element.select("div.time").text(); // 获取新闻发布时间
		
			if(newsReleaseTime.equals(dateTime)){// 当发布日期等于今天日期的时候进入

				News news = new News(newsId, newsTitle, newsUrl, newsSourceWebsite, newsType, newsReleaseTime);
				list.add(news);
			}
		}
		return list;
	}
}
