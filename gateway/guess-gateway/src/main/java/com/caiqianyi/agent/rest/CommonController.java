package com.caiqianyi.agent.rest;

import java.util.Date;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.caiqianyi.agent.security.DesUtils;
import com.caiqianyi.commons.exception.I18nMessageException;
import com.caiqianyi.commons.exception.SuccessMessage;

@RestController
@RequestMapping("/common")
public class CommonController {
	
	private Logger logger = LoggerFactory.getLogger(CommonController.class);
	
	@Resource
	private DesUtils desUtils;
	
	@RequestMapping(value = "/encrypt", method = RequestMethod.GET)
	SuccessMessage encrypt(String encrypts[]) throws Exception{
		if(encrypts != null && encrypts.length < 10){
			StringBuffer bf = new StringBuffer();
			for(int i=0;i<encrypts.length;i++){
				String str = encrypts[i];
				bf.append(desUtils.encrypt(str));
				if(i < encrypts.length-1){
					bf.append(",");
				}
				logger.debug("str[{}]={}",i,str);
			}
			return new SuccessMessage(bf.toString().split(","));
		}
		throw new I18nMessageException("500");
	}
	
	@RequestMapping(value = "/now/", method = RequestMethod.GET)
	String now(){
		return new Date().getTime()+"";
	}
}
