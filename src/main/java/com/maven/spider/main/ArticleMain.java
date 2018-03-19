package com.maven.spider.main;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import com.maven.spider.entity.Article;
import com.maven.spider.httpclient.HttpClientRequest;
import com.maven.spider.jdbc.ArticleJdbc;
import com.maven.spider.parser.ArticleJsoupParser;
import com.maven.spider.util.DateUtil;

public class ArticleMain {

	// 创建定时器Timer对象
	private static final Timer timer = new Timer();
	// 创建模拟浏览器请求对象
	private final static HttpClientRequest hcq = new HttpClientRequest();

	public static void main(String[] args) {
		// 启动后延迟时间
		long afterSs = 0;
		// 执行周期
		long intervalSsl = 3 * 60000;
		timer.schedule(new TimerTask() {
			// 执行次数计数器
			int i = 0;
			@Override
			public void run() {
				System.out.println("当前时间：" + DateUtil.getYMDHMS() + "定时器执行次数：" + i++);
				// csdn
				ArticleSpiderMain("https://www.csdn.net/nav/newarticles", "最新文章", "CSDN");
				// cnblogs
				ArticleSpiderMain("https://www.cnblogs.com/cate/all/", "所有随笔", "cnblogs");
				// 51CTO
			//	ArticleSpiderMain("http://blog.51cto.com/original", "最新原创", "51CTO");
				// 开源中国
				//ArticleSpiderMain("https://www.oschina.net/blog", "最新文章", "oschina");
			}
		
		}, afterSs, intervalSsl);

	}
	/**
	 * 博客爬虫入口方法
	 * 
	 * @param mainUrl
	 * @param articleType
	 * @param source
	 */
	private static void ArticleSpiderMain(String mainUrl, String articleType, String source) {
		String content = hcq.get(mainUrl);
		List<Article> ArticleList = null;
		if(source == "CSDN") {// CSDN解析器
			ArticleList = ArticleJsoupParser.getCsdnArcitleList(content, articleType, source);
		}else if(source == "cnblogs") {// 博客园解析器
			ArticleList = ArticleJsoupParser.getCnblogsArticleList(content, articleType, source);
		}else if(source == "51CTO"){// 51CTO博客解析器
			ArticleList = ArticleJsoupParser.get51ctoArticleList(content, articleType, source);
		}else if(source == "oschina"){// 开源中国博客解析器
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
