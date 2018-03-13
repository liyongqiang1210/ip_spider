package com.maven.spider.main;

import com.maven.spider.httpclient.HttpClientRequest;

public class CsdnMain {

	public static void main(String[] args) {
		HttpClientRequest hcq = new HttpClientRequest();
		String content = hcq.get("https://www.csdn.net/nav/newarticles");
		System.out.println(content);
	}
}
