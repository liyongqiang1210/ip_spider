package com.maven.spider.entity;

import java.io.Serializable;

public class IP implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private String ip_address;
	private String ip_prot;
	private String ip_server_address;
	private String ip_is_anonymous;
	private Integer ip_is_user;
	private String ip_type;
	private String response_time;
	private String create_time;

	
	public Integer getIp_is_user() {
		return ip_is_user;
	}

	public void setIp_is_user(Integer ip_is_user) {
		this.ip_is_user = ip_is_user;
	}

	public String getId() {
		return id;
	}

	public String getIp_address() {
		return ip_address;
	}

	public void setIp_address(String ip_address) {
		this.ip_address = ip_address;
	}

	public String getIp_prot() {
		return ip_prot;
	}

	public void setIp_prot(String ip_prot) {
		this.ip_prot = ip_prot;
	}

	public String getIp_server_address() {
		return ip_server_address;
	}

	public void setIp_server_address(String ip_server_address) {
		this.ip_server_address = ip_server_address;
	}

	public String getIp_is_anonymous() {
		return ip_is_anonymous;
	}

	public void setIp_is_anonymous(String ip_is_anonymous) {
		this.ip_is_anonymous = ip_is_anonymous;
	}

	public String getIp_type() {
		return ip_type;
	}

	public void setIp_type(String ip_type) {
		this.ip_type = ip_type;
	}

	public String getResponse_time() {
		return response_time;
	}

	public void setResponse_time(String response_time) {
		this.response_time = response_time;
	}

	public String getCreate_time() {
		return create_time;
	}

	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public IP() {
		super();
	}

	public IP(String id, String ip_address, String ip_prot, String ip_server_address, String ip_is_anonymous,
			Integer ip_is_user, String ip_type, String response_time, String create_time) {
		super();
		this.id = id;
		this.ip_address = ip_address;
		this.ip_prot = ip_prot;
		this.ip_server_address = ip_server_address;
		this.ip_is_anonymous = ip_is_anonymous;
		this.ip_is_user = ip_is_user;
		this.ip_type = ip_type;
		this.response_time = response_time;
		this.create_time = create_time;
	}

	public IP(String ip_address, String ip_prot, String ip_server_address, String ip_is_anonymous, Integer ip_is_user,
			String ip_type, String response_time, String create_time) {
		super();
		this.ip_address = ip_address;
		this.ip_prot = ip_prot;
		this.ip_server_address = ip_server_address;
		this.ip_is_anonymous = ip_is_anonymous;
		this.ip_is_user = ip_is_user;
		this.ip_type = ip_type;
		this.response_time = response_time;
		this.create_time = create_time;
	}

	
	public IP(String ip_address, String ip_prot, String ip_server_address, String ip_is_anonymous, String ip_type) {
		super();
		this.ip_address = ip_address;
		this.ip_prot = ip_prot;
		this.ip_server_address = ip_server_address;
		this.ip_is_anonymous = ip_is_anonymous;
		this.ip_type = ip_type;
	}

	@Override
	public String toString() {
		return "IP [id=" + id + ", ip_address=" + ip_address + ", ip_prot=" + ip_prot + ", ip_server_address="
				+ ip_server_address + ", ip_is_anonymous=" + ip_is_anonymous + ", ip_is_user=" + ip_is_user
				+ ", ip_type=" + ip_type + ", response_time=" + response_time + ", create_time=" + create_time + "]";
	}

	
}
