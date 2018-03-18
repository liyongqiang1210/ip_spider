package com.maven.spider.entity;

import java.io.Serializable;

/**
 * 新闻实体类
 * 
 * @author Li Yongqiang
 *
 */
public class News implements Serializable {

	private static final long serialVersionUID = 1L;
	private String newsId;
	private String newsTitle;
	private String newsUrl;
	private String newsSourceWebsite;
	private String newsType;
	private String newsContentId;
	private String newsPictureId;
	private String newsReleaseTime;
	private String newsAuthor;

	public String getNewsId() {
		return newsId;
	}

	public void setNewsId(String newsId) {
		this.newsId = newsId;
	}

	public String getNewsTitle() {
		return newsTitle;
	}

	public void setNewsTitle(String newsTitle) {
		this.newsTitle = newsTitle;
	}

	public String getNewsUrl() {
		return newsUrl;
	}

	public void setNewsUrl(String newsUrl) {
		this.newsUrl = newsUrl;
	}

	public String getNewsSourceWebsite() {
		return newsSourceWebsite;
	}

	public void setNewsSourceWebsite(String newsSourceWebsite) {
		this.newsSourceWebsite = newsSourceWebsite;
	}

	public String getNewsType() {
		return newsType;
	}

	public void setNewsType(String newsType) {
		this.newsType = newsType;
	}

	public String getNewsPictureId() {
		return newsPictureId;
	}

	public void setNewsPictureId(String newsPictureId) {
		this.newsPictureId = newsPictureId;
	}

	public String getNewsReleaseTime() {
		return newsReleaseTime;
	}

	public void setNewsReleaseTime(String newsReleaseTime) {
		this.newsReleaseTime = newsReleaseTime;
	}

	public String getNewsAuthor() {
		return newsAuthor;
	}

	public void setNewsAuthor(String newsAuthor) {
		this.newsAuthor = newsAuthor;
	}

	public String getNewsContentId() {
		return newsContentId;
	}

	public void setNewsContentId(String newsContentId) {
		this.newsContentId = newsContentId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "News [newsId=" + newsId + ", newsTitle=" + newsTitle + ", newsUrl=" + newsUrl + ", newsSourceWebsite="
				+ newsSourceWebsite + ", newsType=" + newsType + ", newsContentId=" + newsContentId + ", newsPictureId="
				+ newsPictureId + ", newsReleaseTime=" + newsReleaseTime + ", newsAuthor=" + newsAuthor + "]";
	}

	public News(String newsTitle, String newsUrl, String newsSourceWebsite, String newsType, String newsReleaseTime,
			String newsAuthor) {
		super();
		this.newsTitle = newsTitle;
		this.newsUrl = newsUrl;
		this.newsSourceWebsite = newsSourceWebsite;
		this.newsType = newsType;
		this.newsReleaseTime = newsReleaseTime;
		this.newsAuthor = newsAuthor;
	}

	public News() {
		super();
	}

}
