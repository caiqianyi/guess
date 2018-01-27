package com.caiqianyi.wechat.service.impl;

import java.nio.charset.Charset;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSONObject;
import com.caiqianyi.commons.exception.I18nMessageException;
import com.caiqianyi.soa.core.redis.IRedisCache;
import com.caiqianyi.soa.core.redis.lock.RedisLock;
import com.caiqianyi.wechat.service.IWechatApiService;
import com.ylhy.wechat.vo.AccessToken;
import com.ylhy.wechat.vo.WechatUserInfo;

@Service
public class WechatApiServiceImpl implements IWechatApiService {
	
	private Logger logger = LoggerFactory.getLogger(WechatApiServiceImpl.class);
	
	private RestTemplate restTemplate = new RestTemplate();
	
	@Resource
	private IRedisCache redisCache;
	
	@Override
	public AccessToken getAccessToken(String appid, String secret, String code) {
		String uri = "https://api.weixin.qq.com/sns/oauth2/access_token?appid={appid}&secret={secret}&code={code}&grant_type=authorization_code";
		String json = restTemplate.getForObject(uri, String.class, appid, secret, code);
		logger.debug("json={}",json);
		JSONObject result = (JSONObject) JSONObject.parse(json);
		if (result.containsKey("errcode")) {
			throw new I18nMessageException("40001","error|"+result.getString("errorcode")+"="+result.getString("errmsg"));
		}
		return result.toJavaObject(AccessToken.class);
	}

	@Override
	@RedisLock(value="wechat:get:access_token:lock:#arg0")
	public String getAccessToken(String appid, String secret) {
		String key = String.format("wechat.%s.access_token", appid);
		String access_token = (String) redisCache.get(key);
		if(StringUtils.isBlank(access_token)){
			String uri = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid={}&secret={}";
			String json = restTemplate.getForObject(uri, String.class, appid, secret);
			logger.debug("json={}",json);
			logger.debug("appid={},secret={}",appid,secret);
			
			JSONObject result = (JSONObject) JSONObject.parse(json);
			if (result.containsKey("errcode")) {
				throw new I18nMessageException("40001","error|"+result.getString("errorcode")+"="+result.getString("errmsg"));
			}
			access_token = result.getString("access_token");
			Long expires_in = result.getLong("expires_in");
			redisCache.set(key, access_token, expires_in);
		}
		return access_token;
	}

	@Override
	public WechatUserInfo getUserInfo(String access_token, String openid) {
		StringHttpMessageConverter m = new StringHttpMessageConverter(Charset.forName("UTF-8"));  
        RestTemplate restTemplate = new RestTemplateBuilder().additionalMessageConverters(m).build();  
        HttpHeaders headers = new HttpHeaders();  
        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");  
        headers.setContentType(type);  
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());  
          
		String uri = "https://api.weixin.qq.com/sns/userinfo?access_token={access_token}&openid={openid}&lang=zh_CN";
		String json = restTemplate.getForObject(uri, String.class, access_token, openid);
		logger.debug("json={}",json);
		
		JSONObject result = (JSONObject) JSONObject.parse(json);
		if (result.containsKey("errcode")) {
			throw new I18nMessageException("40001","error|"+result.getString("errorcode")+"="+result.getString("errmsg"));
		}
		return result.toJavaObject(WechatUserInfo.class);
	}

}
