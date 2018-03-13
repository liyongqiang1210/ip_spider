package com.maven.spider.parser;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import com.maven.spider.entity.IP;

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
	 * @throws Exception
	 */
	public List<IP> getIPList(String content) {

		// 创建list集合用来存储ip对象
		List<IP> list = new ArrayList<IP>();
		// 将content转化为doc文档
		Document doc = Jsoup.parse(content);
		// 获取高匿的ip地址
		Elements elements = doc.select("tr.odd");
		// 遍历匹配到的信息
		for (Element element : elements) {

			// 匹配我们需要的标签
			String ip_address = element.select("td:eq(1)").text();
			String ip_prot = element.select("td:eq(2)").text();
			String ip_server_address = element.select("td:eq(3)").select("a").text();
			String ip_is_anonymous = element.select("td:eq(4)").text();
			String ip_type = element.select("td:eq(5)").text();
			
			// 创建带参数的ip对象
			IP ip = new IP(ip_address, ip_prot, ip_server_address, ip_is_anonymous, ip_type);
			list.add(ip);
		}
		
		return list;
	}

}
