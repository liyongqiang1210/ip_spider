package com.maven.spider.main.baidu;

import java.util.Vector;

import com.maven.spider.entity.BaiJiaHao;
import com.maven.spider.entity.News;
import com.maven.spider.httpclient.HttpClientRequest;
import com.maven.spider.jdbc.BaiJiaHaoJdbc;
import com.maven.spider.jdbc.NewsJdbc;
import com.maven.spider.parser.baidu.BaiDuJsoupParser;

public class BaiDuMain {

	public static void main(String[] args) {

		Thread bjht = new Thread(new BaiJiaHaoThread(), "baijiahao_thread");
		Thread bdtjt = new Thread(new BaiDuTuiJianThread(), "baidutuijian_thread");
		bdtjt.start(); // 百度推荐新闻爬虫线程启动
		bjht.start(); // 百家号爬虫线程启动
	}

}

/**
 * 百度推荐线程类
 * 
 * @author Li Yongqiang
 *
 */
class BaiDuTuiJianThread implements Runnable {

	public void run() {

		while (true) {
			try {
				System.out.println(Thread.currentThread().getName() + "---Thread Start...");
				String[] typeArray = { "科技", "国内", "国际", "互联网", "军事", "娱乐", "科技", "财经" };

				for (int i = 0; i < typeArray.length; i++) {

					String content = HttpClientRequest.baiDuTuiJianPost(
							"http://news.baidu.com/news?tn=bdapibaiyue&t=recommendlist", typeArray[i]);
					Vector<News> baiDuTuiJianListData = BaiDuJsoupParser.getBaiDuTuiJianListData(content, typeArray[i]);
					for (News news : baiDuTuiJianListData) {
						boolean newsUrlIsExit = NewsJdbc.getNewsUrlIsExit(news.getNewsUrl()); // 获取数据库是否存在此条信息
						if (!newsUrlIsExit) { // 如果数据库不存在此条消息则添加
							NewsJdbc.insertNewsUrl(news);
						}
					}
				}
				Thread.sleep(200000);// 20分钟爬取一次百度推荐新闻
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}

/**
 * 百家号线程类
 * 
 * @author Li Yongqiang
 *
 */
class BaiJiaHaoThread implements Runnable {

	public void run() {

		while (true) {
			System.out.println(Thread.currentThread().getName() + "---Thread Start...");
			try {

				for (int i = 1; i <= 5; i++) {
					String content = HttpClientRequest.get("https://baijia.baidu.com/channel?cat=" + i, "utf-8");
					String type = null;
					if (i == 1) {
						type = "科技";
					} else if (i == 2) {
						type = "影视娱乐";
					} else if (i == 3) {
						type = "财经";
					} else if (i == 4) {
						type = "体育";
					} else {
						type = "文化";
					}
					Vector<BaiJiaHao> baiJiaListData = BaiDuJsoupParser.getBaiJiaListData(content, type);
					for (BaiJiaHao baiJiaHao : baiJiaListData) {
						boolean selectBaiJiaHaoIsExist = BaiJiaHaoJdbc.selectBaiJiaHaoIsExist(baiJiaHao.getUrl());
						if (!selectBaiJiaHaoIsExist) {
							BaiJiaHaoJdbc.insertBaiJiaHao(baiJiaHao);
						}
					}
				}

				Thread.sleep(200000); // 20分钟爬取一次百家号文章
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
