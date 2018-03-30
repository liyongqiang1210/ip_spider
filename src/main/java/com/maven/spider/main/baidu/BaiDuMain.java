package com.maven.spider.main.baidu;

import java.util.Vector;

import com.maven.spider.entity.BaiJiaHao;
import com.maven.spider.httpclient.HttpClientRequest;
import com.maven.spider.parser.baidu.BaiJiaJsoupParser;

public class BaiDuMain {

	public static void main(String[] args) {
		BaiJiaHaoMain();
	}

	/**
	 * 百家号爬虫入口
	 */
	private static void BaiJiaHaoMain() {
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
			Vector<BaiJiaHao> baiJiaListData = BaiJiaJsoupParser.getBaiJiaListData(content, type);
			for (BaiJiaHao baiJiaHao : baiJiaListData) {
				System.out.println(baiJiaHao);
			}
		}
	}
}
