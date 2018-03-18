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
				+ newsPictureId + ", newsReleaseTime=" + newsReleaseTime +  "]";
	}

	public News(String newsId, String newsTitle, String newsUrl, String newsSourceWebsite, String newsType, String newsReleaseTime) {
		super();
		this.newsId = newsId;
		this.newsTitle = newsTitle;
		this.newsUrl = newsUrl;
		this.newsSourceWebsite = newsSourceWebsite;
		this.newsType = newsType;
		this.newsReleaseTime = newsReleaseTime;
	}

	public News() {
		super();
	}

}
