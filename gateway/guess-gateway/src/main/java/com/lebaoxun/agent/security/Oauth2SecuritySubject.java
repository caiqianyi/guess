package com.lebaoxun.agent.security;

import java.util.Arrays;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.lebaoxun.account.entity.User;
import com.lebaoxun.account.service.IAccountService;
import com.lebaoxun.agent.constants.WebConstants;
import com.lebaoxun.commons.exception.I18nMessageException;
import com.lebaoxun.commons.utils.CodeUtil;
import com.lebaoxun.soa.core.redis.IRedisCache;

@Component
public class Oauth2SecuritySubject{
	
	@Resource
	private Environment env;
	
	@Resource
	private IRedisCache redisCache;
	
	@Resource
	private IAccountService accountService;
	
	public String getOpenid(String account){
		String openid = null;
		if(account != null){
			try {
				openid = new DesUtils(WebConstants.DES_OAUTH2_OPENID_KEY).encrypt(account);
				return openid;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return openid;
	}
	
	public Oauth2 refreshToken(String openid){
		try {
			String account = new DesUtils(WebConstants.DES_OAUTH2_OPENID_KEY).decrypt(openid);
			if(accountService.findByAccount(account) == null){
				throw new I18nMessageException("500");
			}
			String assess_token = new DesUtils(WebConstants.DES_OAUTH2_TOKEN_KEY).encrypt(openid+","+System.currentTimeMillis()+","+CodeUtil.generateString(10));
			Long expires_in = 2 * 60 * 60l;
			redisCache.set("oauth2:token:"+openid, assess_token, expires_in);
			Oauth2 oauth2 = new Oauth2();
			oauth2.setAssess_token(assess_token);
			oauth2.setOpenid(openid);
			oauth2.setExpires_in(expires_in);
			return oauth2;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public boolean checkToken(String token){
		try {
			String openid = getOpenidByToken(token);
			return redisCache.exists("oauth2:token:"+openid) && 
					token.equals(redisCache.get("oauth2:token:"+openid));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	private String getOpenidByToken(String token) throws Exception{
		String str = new DesUtils(WebConstants.DES_OAUTH2_TOKEN_KEY).decrypt(token);
		if(StringUtils.isBlank(str) || str.indexOf(",") < 0){
			throw new I18nMessageException("500");
		}
		String openid = str.split(",")[0];
		return openid;
	}
	
	public User getCurrentUser() {
		String token = GlobalToken.getCurrentToken();
		try {
			String openid = getOpenidByToken(token);
			String account = new DesUtils(WebConstants.DES_OAUTH2_OPENID_KEY).decrypt(openid);
			User agent = accountService.findByAccount(account);
			return agent;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void logout() {
		String token = GlobalToken.getCurrentToken();
		String openid;
		try {
			openid = getOpenidByToken(token);
			String key = "oauth2:token:"+openid;
			if(redisCache.exists(key)){
				redisCache.del("oauth2:token:"+openid);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public boolean isWhiteAccess(String account,String path){
		String names = env.getProperty("guess.accessWhite.names");
    	if(StringUtils.isNotBlank(names)){
    		String nms[] = names.split("\\,");
    		for(String nm : nms){
    			String sta = "agent.accessWhite."+nm+".";
    			String pathPattern = env.getProperty(sta+"pathPattern");
    			if(StringUtils.isBlank(pathPattern)){
    				continue;
    			}
    			String pps[] = pathPattern.split(",");
    			for(String pp : pps){
    				if(path.matches(pp)
    						&& !Arrays.asList(env.getProperty(sta+"whiteList").split(","))
    						.contains(account)){
    					return false;
    				}
    			}
    		}
    	}
    	return true;
	}
	
}
