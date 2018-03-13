package com.maven.spider.main;

import java.util.List;

import com.maven.spider.entity.CSDN;
import com.maven.spider.httpclient.HttpClientRequest;
import com.maven.spider.parser.ParserCsdn;

public class CsdnMain {

	public static void main(String[] args) {
		HttpClientRequest hcq = new HttpClientRequest();
		String content = hcq.get("https://www.csdn.net/nav/newarticles");
		List<CSDN> csdnArcitleList = ParserCsdn.getCsdnArcitleList(content, "最新文章");
		System.out.println(csdnArcitleList);
	}
}
