package com.lebaoxun.sms.core;

import org.springframework.web.bind.annotation.RequestMethod;

public class SMSGateway {
	private String gatewayName;
	private String url;//
	private RequestMethod method;//POST|GET
	private String charset = "UTF-8";
	private String successText;//成功信息
	private String failClass = null;//失败回调处理类
	private boolean isJson = false;
	private String requestBody;
	private String signature;//签名
	private String group;//分组类型 如：语音短信|上线短信|国际短信|国内短信
	private String code;
	private Class<? extends AbstractSMSGatewayClient> clientClass;//自定义处理
	
	public SMSGateway() {
		// TODO Auto-generated constructor stub
	}
	
	public SMSGateway(Class<? extends AbstractSMSGatewayClient> clientClass) {
		// TODO Auto-generated constructor stub
		this.clientClass = clientClass;
	}
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public RequestMethod getMethod() {
		return method;
	}
	public void setMethod(RequestMethod method) {
		this.method = method;
	}
	public String getCharset() {
		return charset;
	}
	public void setCharset(String charset) {
		this.charset = charset;
	}
	public String getSuccessText() {
		return successText;
	}
	public void setSuccessText(String successText) {
		this.successText = successText;
	}
	public String getFailClass() {
		return failClass;
	}
	public void setFailClass(String failClass) {
		this.failClass = failClass;
	}
	public boolean isJson() {
		return isJson;
	}
	public void setJson(boolean isJson) {
		this.isJson = isJson;
	}
	public String getRequestBody() {
		return requestBody;
	}
	public void setRequestBody(String requestBody) {
		this.requestBody = requestBody;
	}
	public String getGatewayName() {
		return gatewayName;
	}
	public void setGatewayName(String gatewayName) {
		this.gatewayName = gatewayName;
	}
	public String getSignature() {
		return signature;
	}
	public void setSignature(String signature) {
		this.signature = signature;
	}
	public String getGroup() {
		return group;
	}
	public void setGroup(String group) {
		this.group = group;
	}
	public Class<? extends AbstractSMSGatewayClient> getClientClass() {
		return clientClass;
	}
	public void setClientClass(Class<? extends AbstractSMSGatewayClient> clientClass) {
		this.clientClass = clientClass;
	}
	public String getCode() {
		return code;
	}
	void setCode(String code) {
		this.code = code;
	}
	
}
