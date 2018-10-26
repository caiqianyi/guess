package com.lebaoxun.pay.domain;

import java.io.Serializable;

public class AlipayQuickWapConfig implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 53463332778367416L;
	String appid;
	String privateKey;
	String publicKey;
	
	String returnUrl;
	String notifyUrl;
	String mercNo;
	String timeoutExpress;
	public String getAppid() {
		return appid;
	}
	public void setAppid(String appid) {
		this.appid = appid;
	}
	public String getPrivateKey() {
		return privateKey;
	}
	public void setPrivateKey(String privateKey) {
		this.privateKey = privateKey;
	}
	public String getPublicKey() {
		return publicKey;
	}
	public void setPublicKey(String publicKey) {
		this.publicKey = publicKey;
	}
	public String getReturnUrl() {
		return returnUrl;
	}
	public void setReturnUrl(String returnUrl) {
		this.returnUrl = returnUrl;
	}
	public String getNotifyUrl() {
		return notifyUrl;
	}
	public void setNotifyUrl(String notifyUrl) {
		this.notifyUrl = notifyUrl;
	}
	public String getTimeoutExpress() {
		return timeoutExpress;
	}
	public void setTimeoutExpress(String timeoutExpress) {
		this.timeoutExpress = timeoutExpress;
	}
	public String getMercNo() {
		return mercNo;
	}
	public void setMercNo(String mercNo) {
		this.mercNo = mercNo;
	}
}
