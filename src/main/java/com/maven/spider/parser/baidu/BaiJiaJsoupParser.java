package com.maven.spider.parser.baidu;

import java.util.Iterator;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.maven.spider.entity.BaiJiaHao;

public class BaiJiaJsoupParser {

	public static Vector<BaiJiaHao> getBaiJiaListData(String content) {

		Vector<BaiJiaHao> vector = new Vector<BaiJiaHao>();

		Document doc = Jsoup.parse(content); // 将html页面转换成doc文档
		Elements elements = doc.select("div#articleList>div.article-info"); // 用此方法筛选出我们需要的文档标签集合
		for (Element element : elements) {

			String title = element.select("div.title>a").text();
			String url = "https://baijia.baidu.com" + element.select("div.title>a").attr("href");
			String author = element.select("div.author>a").text();
			String authorUrl = "https://baijia.baidu.com" + element.select("div.author>a").attr("href");
			String releaseTime = getReleaseTime("[0-9]{2}:[0-9]{2}", element.select("p.info").text());
			String imageUrl = getImageUrl(element); // 获取图片地址
			System.out.println(title + ":" + imageUrl);
		}

		return vector;

	}

	/**
	 * 根据单个百家问文章标签获取图片地址需传参数(百家问单个问题标签)
	 * 
	 * @param element
	 *            百家问单个问题标签
	 * @return
	 */
	private static String getImageUrl(Element element) {

		Elements elements_img = element.select("img"); // 获取图片标签集合
		int imageSize = elements_img.size(); // 获取图片数量
		String imageUrl = "";

		// 根据img标签数量判断图片是否为多个
		if (imageSize > 1) {

			for (Element element_img : elements_img) {

				String image = element_img.select("img").attr("src");
				imageUrl = imageUrl + image + ";"; // 将图片url地址拼接到一起
			}
		} else {
			imageUrl = imageUrl + elements_img.select("img").attr("src");
		}
		return imageUrl;
	}

	/**
	 * 此方法用于根据正则表达式匹配出指定字符串 需传入正则表达式与要匹配的文本信息
	 * 
	 * @param info
	 *            文本信息
	 * @param regex
	 *            正则表达式
	 * @return
	 */
	public static String getReleaseTime(String regex, String info) {

		Pattern p = Pattern.compile(regex); // 创建Pattern对象
		Matcher m = p.matcher(info);
		String releaseTime = null;
		System.out.println(m.find());
		if (!m.find()) {
			System.out.println("进来了");
			releaseTime = m.group(); // 获取匹配到的字符串
		}
		return releaseTime;
	}
}
