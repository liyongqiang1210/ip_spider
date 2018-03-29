package com.maven.spider.entity;

import java.io.Serializable;

public class UrlList implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String newsUrl;
	private String newsUrlSource;
	private String newsType;

	public String getNewsUrl() {
		return newsUrl;
	}

	public void setNewsUrl(String newsUrl) {
		this.newsUrl = newsUrl;
	}

	public String getNewsUrlSource() {
		return newsUrlSource;
	}

	public void setNewsUrlSource(String newsUrlSource) {
		this.newsUrlSource = newsUrlSource;
	}

	public String getNewsType() {
		return newsType;
	}

	public void setNewsType(String newsType) {
		this.newsType = newsType;
	}

}
