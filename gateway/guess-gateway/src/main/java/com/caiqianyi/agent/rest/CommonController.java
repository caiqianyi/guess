package com.caiqianyi.agent.rest;

import java.util.Date;

import javax.annotation.Resource;

import org.apache.commons.lang.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.caiqianyi.agent.security.DesUtils;
import com.caiqianyi.commons.exception.I18nMessageException;
import com.caiqianyi.commons.exception.SuccessMessage;
import com.caiqianyi.soa.core.redis.IRedisCache;

@RestController
public class CommonController {
	
	private Logger logger = LoggerFactory.getLogger(CommonController.class);
	
	@Resource
	private DesUtils desUtils;
	
	@Resource
	private IRedisCache redisCache;
	
	@Value("${spring.cloud.config.profile}")
	private String profile;
	
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
	
	@RequestMapping(value = "/now", method = RequestMethod.GET)
	SuccessMessage now(){
		return new SuccessMessage(new Date().getTime()+"");
	}
	
	@RequestMapping(value = "/profile", method = RequestMethod.GET)
	SuccessMessage profile(){
		return new SuccessMessage(profile);
	}
	
	@RequestMapping(value = "/js/version",produces="application/javascript;charset=UTF-8", method = RequestMethod.GET)
	String version(){
		String version = DateFormatUtils.format(new Date(), "yyyyMMDDHHmmss"),key = "agent:statics:version";
		/*if(redisCache.exists(key)){
			version = (String) redisCache.get(key);
		}else{
			redisCache.set(key, version);
		}*/
		return "var static_version = \""+version+"\";";
	}
}
