package com.lebaoxun.pay.wxpay.entity;

public class QRCodeEntity {
	private String ToUserName;
	private String FromUserName;
	private String CreateTime;
	private String MsgType;
	private String Event;
	private String EventKey;
	private String KfAccount;
	private String FromKfAccount;
	private String ToKfAccount;

	public String getFromKfAccount() {
		return FromKfAccount;
	}

	public void setFromKfAccount(String fromKfAccount) {
		FromKfAccount = fromKfAccount;
	}

	public String getToKfAccount() {
		return ToKfAccount;
	}

	public void setToKfAccount(String toKfAccount) {
		ToKfAccount = toKfAccount;
	}

	public String getKfAccount() {
		return KfAccount;
	}

	public void setKfAccount(String kfAccount) {
		KfAccount = kfAccount;
	}

	public String getToUserName() {
		return this.ToUserName;
	}

	public void setToUserName(String toUserName) {
		this.ToUserName = toUserName;
	}

	public String getFromUserName() {
		return this.FromUserName;
	}

	public void setFromUserName(String fromUserName) {
		this.FromUserName = fromUserName;
	}

	public String getCreateTime() {
		return this.CreateTime;
	}

	public void setCreateTime(String createTime) {
		this.CreateTime = createTime;
	}

	public String getMsgType() {
		return this.MsgType;
	}

	public void setMsgType(String msgType) {
		this.MsgType = msgType;
	}

	public String getEvent() {
		return this.Event;
	}

	public void setEvent(String event) {
		this.Event = event;
	}

	public String getEventKey() {
		return this.EventKey;
	}

	public void setEventKey(String eventKey) {
		this.EventKey = eventKey;
	}
}