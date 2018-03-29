package com.maven.spider.main;

import java.util.List;

import com.maven.spider.entity.Ip;
import com.maven.spider.httpclient.HttpClientRequest;
import com.maven.spider.jdbc.IpJdbc;
import com.maven.spider.parser.IpJsoupParser;
import com.maven.spider.testip.TestIP;

/**
 * 爬虫程序入口
 * 
 * @author liyongqiang
 *
 */
public class IpMain {

	public static void main(String[] args) throws InterruptedException {
		IpJsoupParser ph = new IpJsoupParser();
		IpJdbc db = new IpJdbc();
		TestIP test = new TestIP();

		for (int i = 1; i < 2000; i++) {

			// 设置url
			String url = "http://www.xicidaili.com/nn/" + i;
			// 获取网页内容
			String content = HttpClientRequest.get(url,"utf-8");
			// 获取json格式的ip数据列表
			List<Ip> list = ph.getIPList(content);
			for (Ip ip : list) {
				String ip_address = ip.getIp_address();
				String ip_prot = ip.getIp_prot();
				String ip_type = ip.getIp_type();

				// 查询ip是否可用
				boolean ipIsAvailable = test.getIPIsAvailable(ip_address, ip_prot, ip_type);

				// 如果可用那么将其Ip_is_user属性设置为1
				if (ipIsAvailable) {
					ip.setIp_is_user(1);
					// 插入数据库
					db.insertIP(ip);
				}else{
					System.out.println(ip_address + "===========>不可用！");
				}

	 		}
		}
	}
}
