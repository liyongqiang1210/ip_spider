package com.maven.spider.entity;

import java.io.Serializable;

/**
 * 技术博客实体类
 * @author Li Yongqiang
 *
 */
public class Arcitle implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String url;
	private String title;
	private String author;
	private String createTime;
	private String source;
	private String articleType;
	private int isDelete;

	public Arcitle() {
		super();
	}

	public Arcitle(String url, String title, String author, String createTime, String source, String articleType) {
		super();
		this.url = url;
		this.title = title;
		this.author = author;
		this.createTime = createTime;
		this.source = source;
		this.articleType = articleType;
	}


	public Arcitle(int id, String url, String title, String author, String createTime, String source, String articleType,
			int isDelete) {
		super();
		this.id = id;
		this.url = url;
		this.title = title;
		this.author = author;
		this.createTime = createTime;
		this.source = source;
		this.articleType = articleType;
		this.isDelete = isDelete;
	}

	@Override
	public String toString() {
		return "CSDN [id=" + id + ", url=" + url + ", title=" + title + ", author=" + author + ", createTime="
				+ createTime + ", source=" + source + ", articleType=" + articleType + ", isDelete=" + isDelete + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getArticleType() {
		return articleType;
	}

	public void setArticleType(String articleType) {
		this.articleType = articleType;
	}

	public int getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(int isDelete) {
		this.isDelete = isDelete;
	}

}
