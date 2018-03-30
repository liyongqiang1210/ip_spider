package com.maven.spider.entity;

import java.io.Serializable;

/**
 * 百家号实体类
 * 
 * @author liyongqiang
 *
 */
public class BaiJiaHao implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private String title;
	private String url;
	private String type;
	private String author;
	private String authorUrl;
	private String releaseTime;
	private String imageUrl;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getAuthorUrl() {
		return authorUrl;
	}

	public void setAuthorUrl(String authorUrl) {
		this.authorUrl = authorUrl;
	}

	public String getReleaseTime() {
		return releaseTime;
	}

	public void setReleaseTime(String releaseTime) {
		this.releaseTime = releaseTime;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	@Override
	public String toString() {
		return "BaiJiaHao [id=" + id + ", title=" + title + ", url=" + url + ", type=" + type + ", author=" + author
				+ ", authorUrl=" + authorUrl + ", releaseTime=" + releaseTime + ", imageUrl=" + imageUrl + "]";
	}

	public BaiJiaHao() {
		super();
	}

	public BaiJiaHao(String id, String title, String url, String type, String author, String authorUrl,
			String releaseTime, String imageUrl) {
		super();
		this.id = id;
		this.title = title;
		this.url = url;
		this.type = type;
		this.author = author;
		this.authorUrl = authorUrl;
		this.releaseTime = releaseTime;
		this.imageUrl = imageUrl;
	}

}
