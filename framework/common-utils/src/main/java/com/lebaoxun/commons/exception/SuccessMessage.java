package com.lebaoxun.commons.exception;

import java.io.Serializable;

public class SuccessMessage implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3814041435546894811L;

	private String errmsg = "ok";
	
	private String errcode = "0";
	
	private Object data;
	
	public SuccessMessage() {
	}
	
	public SuccessMessage(Object data) {
		// TODO Auto-generated constructor stub
		this.data = data;
	}
	
	public SuccessMessage(String errmsg,Object data) {
		// TODO Auto-generated constructor stub
		this.errmsg = errmsg;
		this.data = data;
	}

	public String getErrmsg() {
		return errmsg;
	}

	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}

	public String getErrcode() {
		return errcode;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public void setErrcode(String errcode) {
		this.errcode = errcode;
	}
}
