package com.maven.spider.main;

import java.util.List;

import com.maven.spider.entity.News;
import com.maven.spider.httpclient.HttpClientRequest;
import com.maven.spider.jdbc.NewsJdbc;
import com.maven.spider.parser.NewsJsoupParser;

/**
 * 新闻爬虫入口主函数
 * @author Li Yongqiang
 *
 */
public class NewsMain {

	
	
	
	static final HttpClientRequest hcq = new HttpClientRequest();
	
	public static void main(String[] args){
		//String url = "http://www.xinhuanet.com/mil/index.htm"; // 军事
		//String url = "http://www.xinhuanet.com/world/index.htm"; // 国际
		//String url = "http://www.news.cn/house/index.htm"; // 房产
		String url = "http://www.xinhuanet.com/tech/index.htm"; // 科技
		String content = hcq.get(url);
		List<News> newsList = NewsJsoupParser.getNewsList(content, "新华网", "科技");
		for(News news: newsList){
			boolean isExit = NewsJdbc.getNewsUrlIsExit(news.getNewsUrl());
			if(!isExit){
				NewsJdbc.insertNewsUrl(news);
			}
		}
	}
}
