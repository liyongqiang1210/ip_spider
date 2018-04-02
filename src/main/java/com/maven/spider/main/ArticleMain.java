package com.maven.spider.main;

import java.util.List;

import com.maven.spider.entity.Article;
import com.maven.spider.httpclient.HttpClientRequest;
import com.maven.spider.jdbc.ArticleJdbc;
import com.maven.spider.parser.ArticleJsoupParser;

public class ArticleMain {

	public static void main(String[] args) {

		Thread bt = new Thread(new BlogThread(), "blogThread");
		bt.start();
	}

}

class BlogThread implements Runnable {

	public void run() {
		while (true) {
			try {
				// csdn
				ArticleSpiderMain("https://www.csdn.net/nav/newarticles", "最新文章", "CSDN");
				// cnblogs
				ArticleSpiderMain("https://www.cnblogs.com/cate/all/", "所有随笔", "cnblogs");
				// 51CTO
				ArticleSpiderMain("http://blog.51cto.com/original", "最新原创", "51CTO");
				// 开源中国
				ArticleSpiderMain("https://www.oschina.net/blog", "最新文章", "oschina");
				Thread.sleep(50000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * 博客爬虫入口方法
	 * 
	 * @param mainUrl
	 * @param articleType
	 * @param source
	 */
	private static void ArticleSpiderMain(String mainUrl, String articleType, String source) {
		String content = HttpClientRequest.get(mainUrl, "utf-8");
		List<Article> ArticleList = null;
		if (source == "CSDN") {// CSDN解析器
			ArticleList = ArticleJsoupParser.getCsdnArcitleList(content, articleType, source);
		} else if (source == "cnblogs") {// 博客园解析器
			ArticleList = ArticleJsoupParser.getCnblogsArticleList(content, articleType, source);
		} else if (source == "51CTO") {// 51CTO博客解析器
			ArticleList = ArticleJsoupParser.get51ctoArticleList(content, articleType, source);
		} else if (source == "oschina") {// 开源中国博客解析器
			ArticleList = ArticleJsoupParser.getOschinaArticleList(content, articleType, source);
		}
		for (Article article : ArticleList) {
			String url = article.getUrl();
			if (url != null && url != "") {
				boolean isExit = ArticleJdbc.selectArticle(url);
				if (isExit) {
					ArticleJdbc.insertArticle(article);
				}
			}
		}
	}

}
