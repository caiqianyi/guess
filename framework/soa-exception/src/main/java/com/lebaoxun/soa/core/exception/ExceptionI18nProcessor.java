package com.lebaoxun.soa.core.exception;

import java.util.Properties;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.lebaoxun.commons.beans.PropertyConfigurer;
import com.lebaoxun.commons.exception.I18nMessageException;
import com.lebaoxun.commons.exception.SuccessMessage;

@Component
public class ExceptionI18nProcessor {
	
	private static Logger logger = LoggerFactory.getLogger(ExceptionI18nProcessor.class);
	
	private Properties props = null;
	
	private PropertyConfigurer propertyConfigurer;
	
	public SuccessMessage exceptionHandler(Exception e) {
		SuccessMessage message = new SuccessMessage();
    	I18nMessageException i18ne = null;
    	if(!(e instanceof I18nMessageException)){
    		i18ne = new I18nMessageException(e);
    		if("class com.netflix.hystrix.exception.HystrixRuntimeException".equals(e.getClass().toString()))
    			i18ne.setCode("502");
    	}else{
    		i18ne = ((I18nMessageException)e);
    	}
		String key = i18ne.getCode();
		String msg = props.getProperty(key);
		
		if(StringUtils.isBlank(msg)){
			msg = i18ne.getInfo();
		}
		
		logger.debug("key={},msg={}",key,msg);
		
		if(i18ne.getArgs() != null){
			msg = String.format(msg, i18ne.getArgs());
		}
		
		message.setErrcode(i18ne.getCode());
    	message.setErrmsg(msg);

    	return message;
    }
	
	@Resource
	public void setPropertyConfigurer(PropertyConfigurer propertyConfigurer) {
		this.propertyConfigurer = propertyConfigurer;
		this.props = this.propertyConfigurer.lazyLoadUniqueProperties("classpath*:i18n_messages.properties");
	}

}
