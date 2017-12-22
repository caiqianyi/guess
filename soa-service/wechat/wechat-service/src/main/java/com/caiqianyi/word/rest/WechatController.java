package com.caiqianyi.word.rest;

import java.nio.charset.Charset;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSONObject;
import com.caiqianyi.commons.exception.I18nMessageException;
import com.ylhy.wechat.vo.AccessToken;
import com.ylhy.wechat.vo.WechatOAConfig;
import com.ylhy.wechat.vo.WechatUserInfo;

@RestController
public class WechatController {

	private Logger logger = LoggerFactory.getLogger(WechatController.class);
	
	@Resource
	private Environment env;

	private RestTemplate restTemplate = new RestTemplate();
	
	@RequestMapping(value = "/wechat/OAConfig/{kindOf}/", method = RequestMethod.GET)
	WechatOAConfig getOAConfig(@PathVariable("kindOf") String kindOf){
		String app_id = env.getProperty(kindOf + ".appid"), secret = env
				.getProperty(kindOf + ".secret"),mch_id = env.getProperty(kindOf+".mch_id");
		if(StringUtils.isNotBlank(app_id)){
			WechatOAConfig woaconfig = new WechatOAConfig();
			woaconfig.setApp_id(app_id);
			woaconfig.setSecret(secret);
			woaconfig.setMch_id(mch_id);
			return woaconfig;
		}
		return null;
	}
	
	@RequestMapping(value = "/wechat/accessToken/{kindOf}/{code}/", method = RequestMethod.GET)
	AccessToken getAccessToken(@PathVariable("code") String code,
			@PathVariable("kindOf") String kindOf) {
		WechatOAConfig woaconfig = getOAConfig(kindOf);
		logger.debug("appid={},secret={},code={}",woaconfig.getApp_id(),woaconfig.getSecret(),code);
		String uri = "https://api.weixin.qq.com/sns/oauth2/access_token?appid={appid}&secret={secret}&code={code}&grant_type=authorization_code";
		String json = restTemplate.getForObject(uri, String.class, woaconfig.getApp_id(), woaconfig.getSecret(), code);
		logger.debug("json={}",json);
		
		JSONObject result = (JSONObject) JSONObject.parse(json);
		if (result.containsKey("errcode")) {
			throw new I18nMessageException("40001","error|"+result.getString("errorcode")+"="+result.getString("errmsg"));
		}
		return result.toJavaObject(AccessToken.class);
	}
	@RequestMapping(value = "/wechat/userInfo/{openid}/{access_token}/", method = RequestMethod.GET)
	WechatUserInfo getUserInfo(@PathVariable("access_token") String access_token,
			@PathVariable("openid") String openid) {
		
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
