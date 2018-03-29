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
	 * 
	 * @param content
	 * @param newsSourceWebsite
	 * @param newsType
	 * @return
	 */
	public static List<News> getNewsList(String content, String newsSourceWebsite, String newsType) {

		List<News> list = new ArrayList<News>();
		Document doc = Jsoup.parse(content);
		if (newsSourceWebsite.equals("新华网")) {

			if (newsType.equals("军事")) {

				Elements elements = doc.select("ul.list_show>li"); // 获取新闻列表集合

				for (Element element : elements) {

					String newsId = UUID.randomUUID().toString(); // 获取新闻id
					String newsTitle = element.select("h2>a").text(); // 获取新闻title
					String newsUrl = element.select("h2>a").attr("href"); // 获取新闻url
					String newsReleaseTime = element.select("div.time").text(); // 获取新闻发布时间

					if (newsReleaseTime.equals(dateTime)) {// 当发布日期等于今天日期的时候进入

						News news = new News(newsId, newsTitle, newsUrl, newsSourceWebsite, newsType, newsReleaseTime);
						list.add(news);
					}
				}
			} else if (newsType.equals("国际") || newsType.equals("房产") || newsType.equals("科技") || newsType.equals("娱乐")
					|| newsType.equals("汽车")) {

				// 获取新闻列表集合
				Elements elements = doc.select("ul.dataList>li");
				for (Element element : elements) {
					String newsId = UUID.randomUUID().toString(); // 获取新闻id
					String newsTitle = element.select("h3>a").text(); // 获取新闻title
					String newsUrl = element.select("h3>a").attr("href"); // 获取新闻url
					String newsReleaseTime = element.select("span.time").text(); // 获取新闻发布时间

					if (newsReleaseTime.equals(dateTime)) {// 当发布日期等于今天日期的时候进入

						News news = new News(newsId, newsTitle, newsUrl, newsSourceWebsite, newsType, newsReleaseTime);
						list.add(news);
					}
				}

				// 获取房产滚动新闻集合
				Elements gd_elements = doc.select("ul#gd_content>li");
				for (Element element : gd_elements) {
					String newsId = UUID.randomUUID().toString(); // 获取新闻id
					String newsTitle = element.select("li>a").text(); // 获取新闻title
					String newsUrl = element.select("li>a").attr("href"); // 获取新闻url
					String newsReleaseTime = DateUtil.getYMD(); // 获取今天的日期

					if (newsUrl.matches(".*" + newsReleaseTime + ".*")) { // 根据url地址判断是否是今天的新闻

						News news = new News(newsId, newsTitle, newsUrl, newsSourceWebsite, newsType, newsReleaseTime);
						list.add(news);
					}
				}

				// 获取科技新闻集合
				Elements kj_element = doc.select("a");
				for (Element element : kj_element) {

					String newsUrl = element.select("a").attr("href");
					String newsReleaseTime = DateUtil.getYMD();

					if (newsUrl.matches(
							".*" + newsReleaseTime.substring(0, 7) + "/" + newsReleaseTime.substring(8) + ".*")) {

						String newsId = UUID.randomUUID().toString(); // 获取新闻id
						String newsTitle = element.select("a").text();

						News news = new News(newsId, newsTitle, newsUrl, newsSourceWebsite, newsType, newsReleaseTime);
						list.add(news);
					}
				}
			} else if (newsType.equals("体育")) {
				Elements ty_element = doc.select("div.scpd_page_box>li");
				for (Element element : ty_element) {

					String newsId = UUID.randomUUID().toString(); // 获取新闻id
					String newsUrl = element.select("div.bnn>a").attr("href");
					String newsReleaseTime = DateUtil.getYMD().substring(0, 5) + element.select("b").text();
					String newsTitle = element.select("div.bnn>a").text();

					if (newsReleaseTime.equals(dateTime)) {

						News news = new News(newsId, newsTitle, newsUrl, newsSourceWebsite, newsType, newsReleaseTime);
						list.add(news);
					}
				}
			}
		}else if(newsSourceWebsite.equals("中新网")){
			
			Elements elements = doc.select("div.content_list>ul>li");
			for(Element element : elements){
				
				String newsId = UUID.randomUUID().toString();
				String newsUrl = element.select("div.dd_bt>a").attr("href");
				newsType = element.select("div.dd_lm>a").text(); // 获取新闻类型
				String newsReleaseTime = element.select("div.dd_time").text();
				String newsTitle = element.select("div.dd_bt>a").text();
				if(newsUrl.matches(".*/" + dateTime.substring(5) + "/.*")){
					News news = new News(newsId, newsTitle, newsUrl, newsSourceWebsite, newsType, newsReleaseTime);
					list.add(news);
				}
			}
		}

		return list;
	}
}
