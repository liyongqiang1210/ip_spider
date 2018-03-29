package com.maven.spider.main.baidu;

import java.util.Vector;

import com.maven.spider.entity.BaiJiaHao;
import com.maven.spider.httpclient.HttpClientRequest;
import com.maven.spider.parser.baidu.BaiJiaJsoupParser;

public class BaiJiaMain {

	public static void main(String[] args) {
		
		String content = HttpClientRequest.get("https://baijia.baidu.com/channel?cat=1", "utf-8");
		Vector<BaiJiaHao> baiJiaListData = BaiJiaJsoupParser.getBaiJiaListData(content);
	}
}
