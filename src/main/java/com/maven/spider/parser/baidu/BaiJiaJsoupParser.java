package com.maven.spider.parser.baidu;

import java.util.UUID;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.maven.spider.entity.BaiJiaHao;
import com.maven.spider.util.DateUtil;

/**
 * 
 * @author liyongqiang
 *
 */
public class BaiJiaJsoupParser {

	/**
	 * 此方法功能是根据html页面内容分析出我们所要的数据需传入网页内容content与网址类型type两个参数
	 * 
	 * @param content
	 * @param type
	 * @return
	 */
	public static Vector<BaiJiaHao> getBaiJiaListData(String content, String type) {

		Vector<BaiJiaHao> vector = new Vector<BaiJiaHao>();

		Document doc = Jsoup.parse(content); // 将html页面转换成doc文档
		Elements elements = doc.select("div#articleList>div.article-info"); // 用此方法筛选出我们需要的文档标签集合
		for (Element element : elements) {

			String id = UUID.randomUUID().toString();
			String title = element.select("div.title>a").text();
			String url = "https://baijia.baidu.com" + element.select("div.title>a").attr("href");
			String author = element.select("div.author>a").text();
			String authorUrl = "https://baijia.baidu.com" + element.select("div.author>a").attr("href");
			String releaseTime = getReleaseTime("[0-9]{2}:[0-9]{2}|[0-9]{2}-[0-9]{2}", element.select("p.info").text());
			String imageUrl = getImageUrl(element); // 获取图片地址

			BaiJiaHao bjh = new BaiJiaHao(id, title, url, type, author, authorUrl, releaseTime, imageUrl); // 实例化
			vector.add(bjh); // 添加到Vector集合
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
	 * 此方法用于根据正则表达式匹配出指定时间格式字符串 需传入正则表达式与要匹配的文本信息
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
		boolean find = m.find();
		if (find) {
			String group = m.group(); // 获取匹配到的字符串
			if (group.contains("-")) { // 判断是日期还是时间包含-代表是日期否则就是时间
				releaseTime = DateUtil.getYMD().substring(0, 5) + group;
			} else {
				releaseTime = DateUtil.getYMD() + " " + group + ":00"; // 补全时间格式
			}
		}
		return releaseTime;
	}
}
