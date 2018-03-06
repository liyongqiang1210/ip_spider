package com.maven.spider.main;

import java.util.List;

import com.maven.spider.entity.IP;
import com.maven.spider.gethtml.GetHtml;
import com.maven.spider.jdbc.DBUtils;
import com.maven.spider.parsehtml.ParseHtml;
import com.maven.spider.testip.TestIP;

/**
 * 爬虫程序入口
 * 
 * @author liyongqiang
 *
 */
public class SpiderMain {

	public static void main(String[] args) throws InterruptedException {
		GetHtml gh = new GetHtml();
		ParseHtml ph = new ParseHtml();
		DBUtils db = new DBUtils();
		TestIP test = new TestIP();

		for (int i = 1; i < 20; i++) {

			// 设置url
			String url = "http://www.xicidaili.com/nn/" + i;
			// 获取网页内容
			String content = gh.get(url);
			// 获取json格式的ip数据列表
			List<IP> list = ph.getIPList(content);
			System.out.println(list);
			for(IP ip:list){
				String ip_address = ip.getIp_address();
				String ip_prot = ip.getIp_prot();
				String ip_type = ip.getIp_type();
				
				// 查询ip是否可用
				boolean ipIsAvailable = test.getIPIsAvailable(ip_address, ip_prot, ip_type);
				 
				// 如果可用那么将其Ip_is_user属性设置为1
				if(ipIsAvailable){
					ip.setIp_is_user(1);
				}else{
					ip.setIp_is_user(0);
				}
				
				// 插入数据库
//				db.insertIP(ip);
				
			}
		}
	}
}
