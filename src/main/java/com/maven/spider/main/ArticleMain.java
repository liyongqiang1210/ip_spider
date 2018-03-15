package com.maven.spider.main;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import com.maven.spider.entity.Article;
import com.maven.spider.httpclient.HttpClientRequest;
import com.maven.spider.jdbc.ArticleJdbc;
import com.maven.spider.parser.ArticleParser;
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
				String CsdnContent = hcq.get("https://www.csdn.net/nav/newarticles");
				List<Article> csdnArcitleList = ArticleParser.getCsdnArcitleList(CsdnContent, "最新文章", "CSDN");
				for (Article article : csdnArcitleList) {
					String url = article.getUrl();
					if(url != null && url != "") {
						boolean selectCsdn = ArticleJdbc.selectArticle(url);
						if (selectCsdn) {
							ArticleJdbc.insertArticle(article);
						}
					}
				}
				// cnblogs
				String CnblogsContent = hcq.get("https://www.cnblogs.com/cate/all/");
				List<Article> cnblogsList = ArticleParser.getCnblogsList(CnblogsContent, "所有随笔", "cnblogs");
				for (Article article : cnblogsList) {
					String url = article.getUrl();
					if(url != null && url != "") {
						boolean selectCsdn = ArticleJdbc.selectArticle(url);
						if (selectCsdn) {
							ArticleJdbc.insertArticle(article);
						}
					}
				}
			}
		}, afterSs, intervalSsl);
		
	}
}
