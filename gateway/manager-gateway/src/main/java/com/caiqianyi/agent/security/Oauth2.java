package com.caiqianyi.agent.security;

public class Oauth2 {
	
	private String openid;
	private String assess_token;
	private Long expires_in;
	
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public String getAssess_token() {
		return assess_token;
	}
	public void setAssess_token(String assess_token) {
		this.assess_token = assess_token;
	}
	public Long getExpires_in() {
		return expires_in;
	}
	public void setExpires_in(Long expires_in) {
		this.expires_in = expires_in;
	}
	
}
