package com.maven.spider.parser.baidu;

import java.util.UUID;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.maven.spider.entity.BaiJiaHao;
import com.maven.spider.entity.News;
import com.maven.spider.util.DateUtil;

/**
 * 
 * @author liyongqiang
 *
 */
public class BaiDuJsoupParser {

	/**
	 * 此方法功能是根据html页面内容分析出我们所要的数据需传入参数：网页内容content与网址类型type
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
	 * 获取百度推荐新闻需传入参数：网页内容content与新闻类型
	 * 
	 * @param content
	 * @param type
	 * @return
	 */
	public static Vector<News> getBaiDuTuiJianListData(String content, String type) {

		Vector<News> vector = new Vector<News>();
		// 截取字符串获取正确格式得到json字符串
		String json = content.substring(content.indexOf("news\":[") + 6, content.lastIndexOf("]") + 1);
		// 将json字符串转换为json数组
		JSONArray ja = JSONArray.parseArray(json);
		// 因为返回的json数据中只有前十条是有数据的所以我们只需获取前十条的json数据就可以了
		for (int i = 1; i < 10; i++) {
			JSONObject jo = (JSONObject) ja.get(i);
			String id = UUID.randomUUID().toString();
			String title = (String) jo.get("title");
			String url = (String) jo.get("url");
			News news = new News(id, title, url, "百度推荐", type, "", "");
			vector.add(news);
		}
		return vector;

	}

	/**
	 * 根据单个百家问文章标签获取图片地址需传参数(百家问单个问题标签element)
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
	 * 此方法用于根据正则表达式匹配出指定时间格式字符串 需传入参数：正则表达式regex与要匹配的文本信息info
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
