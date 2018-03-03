package com.maven.spider.entity;

import java.io.Serializable;

public class IP implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private String ipAddress;
	private String prot;
	private String serverAddress;
	private String isAnonymous;
	private String type;
	private String responseTime;
	private String createTime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public String getProt() {
		return prot;
	}

	public void setProt(String prot) {
		this.prot = prot;
	}

	public String getServerAddress() {
		return serverAddress;
	}

	public void setServerAddress(String serverAddress) {
		this.serverAddress = serverAddress;
	}

	public String getIsAnonymous() {
		return isAnonymous;
	}

	public void setIsAnonymous(String isAnonymous) {
		this.isAnonymous = isAnonymous;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getResponseTime() {
		return responseTime;
	}

	public void setResponseTime(String responseTime) {
		this.responseTime = responseTime;
	}

	public String getCreateTime() {
		return createTime;
	}

	public IP(String id, String ipAddress, String prot, String serverAddress, String isAnonymous, String type) {
		super();
		this.id = id;
		this.ipAddress = ipAddress;
		this.prot = prot;
		this.serverAddress = serverAddress;
		this.isAnonymous = isAnonymous;
		this.type = type;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public IP() {
		super();
	}

	public IP(String id, String ipAddress, String prot, String serverAddress, String isAnonymous, String type,
			String responseTime, String createTime) {
		super();
		this.id = id;
		this.ipAddress = ipAddress;
		this.prot = prot;
		this.serverAddress = serverAddress;
		this.isAnonymous = isAnonymous;
		this.type = type;
		this.responseTime = responseTime;
		this.createTime = createTime;
	}

	@Override
	public String toString() {
		return "{id:" + id + ", ipAddress:" + ipAddress + ", prot:" + prot + ", serverAddress:" + serverAddress
				+ ", isAnonymous:" + isAnonymous + ", type:" + type + ", responseTime:" + responseTime + ", createTime:"
				+ createTime + "}";
	}

}
