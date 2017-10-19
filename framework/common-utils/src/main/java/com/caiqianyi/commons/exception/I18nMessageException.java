package com.caiqianyi.commons.exception;

import org.springframework.util.Assert;

/**
 * service层，controller层 统一消息异常类
 * @author cqy
 *
 */
public class I18nMessageException extends RuntimeException {

	private static final long serialVersionUID = -8537903223002821107L;

	private String code;
	private String[] args;
	private String info;
	private Exception prevException;
	
	public I18nMessageException(String code) {
		Assert.notNull(code);
		
		this.code = code;
	}
	
	public I18nMessageException(String code,String... args) {
		Assert.notNull(code);
		this.args = args;
		this.code = code;
	}
	
	public I18nMessageException(String code,String info) {
		this(code);
		//Assert.notNull(info);
		this.info = info;
	}
	
	public I18nMessageException(String code,String info,Exception prevException) {
		this(code , info);
		this.prevException = prevException;
	}
	
	public I18nMessageException(String code,Exception prevException) {
		this(code , prevException.getMessage() , prevException);
	}
	
	public I18nMessageException(Exception prevException) {
		this("500",prevException);
	}
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public Exception getPrevException() {
		return prevException;
	}
	public void setPrevException(Exception prevException) {
		this.prevException = prevException;
	}
	public String[] getArgs() {
		return args;
	}
}
