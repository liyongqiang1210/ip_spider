package com.maven.spider.parsehtml;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.maven.spider.gethtml.GetHtml;

/**
 * 使用Jsoup解析页面
 * 
 * @author Li Yongqiang
 *
 */
public class ParseHtml {

	/**
	 * 分析html页面获取需要的数据
	 * 
	 * @param content
	 * @return
	 */
	public String getData(String content) {
		// 将content转化为doc文档
		Document doc = Jsoup.parse(content);
		//
		Elements elements = doc.select("tr.odd");
		for (Element element : elements) {
			
			String ipAddress = element.select("td:eq(1)").toString();
			String prot = element.select("td:eq(2)").toString();
			String serverAddress = element.select("td:eq(3)").toString();
			String isAnonymous = element.select("td:eq(4)").toString();
			String type = element.select("td:eq(5)").toString();

		}
		return content;
	}

	public static void main(String[] args) {
		GetHtml gh = new GetHtml();
		new ParseHtml().getData(gh.get("http://www.xicidaili.com/nn/"));
	}

}
