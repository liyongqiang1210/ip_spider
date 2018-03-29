package com.maven.spider.main;

import java.util.List;

import com.maven.spider.entity.News;
import com.maven.spider.entity.UrlList;
import com.maven.spider.httpclient.HttpClientRequest;
import com.maven.spider.jdbc.NewsJdbc;
import com.maven.spider.jdbc.NewsUrlJdbc;
import com.maven.spider.parser.NewsJsoupParser;

/**
 * 新闻爬虫入口主函数
 * 会者不难，难者不会
 * @author Li Yongqiang
 *
 */
public class NewsMain {


	public static void main(String[] args) {
		
		List<UrlList> urlList = NewsUrlJdbc.getUrlList("中新网");
		
		for (UrlList ul : urlList) {

			String content = HttpClientRequest.get(ul.getNewsUrl(),"gb2312");
			List<News> newsList = NewsJsoupParser.getNewsList(content, ul.getNewsUrlSource(), ul.getNewsType());
			
			for (News news : newsList) {
				boolean isExit = NewsJdbc.getNewsUrlIsExit(news.getNewsUrl());
				if (!isExit) {
					NewsJdbc.insertNewsUrl(news);
				}
			}
		}
	}
}
