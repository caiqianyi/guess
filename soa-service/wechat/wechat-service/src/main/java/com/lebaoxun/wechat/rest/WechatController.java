package com.lebaoxun.wechat.rest;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lebaoxun.commons.exception.SuccessMessage;
import com.lebaoxun.soa.core.redis.lock.RedisLock;
import com.lebaoxun.wechat.service.IWechatApiService;
import com.lebaoxun.wechat.vo.AccessToken;
import com.lebaoxun.wechat.vo.WechatOAConfig;
import com.lebaoxun.wechat.vo.WechatUserInfo;

@RestController
public class WechatController {

	private Logger logger = LoggerFactory.getLogger(WechatController.class);
	
	@Resource
	private Environment env;

	@Resource
	private IWechatApiService wechatApiService;
	
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
	
	@RequestMapping(value = "/wechat/ouath2/snsapi_userinfo/", method = RequestMethod.GET)
	SuccessMessage getOuath2Url(@RequestParam(value="kindOf") String kindOf,
			@RequestParam(value="redirect_uri") String redirect_uri,
			@RequestParam(value="state") String state){
		WechatOAConfig woaconfig = getOAConfig(kindOf);
		String url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=%s&redirect_uri=%s&response_type=code&scope=snsapi_userinfo&state=%s#wechat_redirect";
		return new SuccessMessage(String.format(url, woaconfig.getApp_id(),redirect_uri,state));
	}
	
	@RequestMapping(value = "/wechat/accessToken/{kindOf}/{code}/", method = RequestMethod.GET)
	AccessToken getAccessToken(@PathVariable("code") String code,
			@PathVariable("kindOf") String kindOf) {
		WechatOAConfig woaconfig = getOAConfig(kindOf);
		logger.debug("appid={},secret={},code={}",woaconfig.getApp_id(),woaconfig.getSecret(),code);
		return wechatApiService.getAccessToken(woaconfig.getApp_id(), woaconfig.getSecret(), code);
	}
	
	@RequestMapping(value = "/wechat/accessToken/", method = RequestMethod.GET)
	@RedisLock(value="wechat:get:access_token:lock:#arg0")
	String getAccessToken(@RequestParam("kindOf") String kindOf) {
		WechatOAConfig woaconfig = getOAConfig(kindOf);
		logger.debug("appid={},secret={}",woaconfig.getApp_id(),woaconfig.getSecret());
		return wechatApiService.getAccessToken(woaconfig.getApp_id(), woaconfig.getSecret());
	}
	
	@RequestMapping(value = "/wechat/jsapiTicket/", method = RequestMethod.GET)
	@RedisLock(value="wechat:get:jsapiTicket:lock:#arg0")
	String jsapiTicket(@RequestParam("kindOf") String kindOf) {
		WechatOAConfig woaconfig = getOAConfig(kindOf);
		logger.debug("appid={},secret={}",woaconfig.getApp_id(),woaconfig.getSecret());
		return wechatApiService.getJsapiTicket(woaconfig.getApp_id(), woaconfig.getSecret());
	}
	
	@RequestMapping(value = "/wechat/userInfo/{openid}/{access_token}/", method = RequestMethod.GET)
	WechatUserInfo getUserInfo(@PathVariable("access_token") String access_token,
			@PathVariable("openid") String openid) {
		return wechatApiService.getUserInfo(access_token, openid);
	}
	
	@RequestMapping(value = "/wechat/userInfo/kindOf/{kindOf}/{openid}/", method = RequestMethod.GET)
	WechatUserInfo getUserInfoByKindOf(@PathVariable("kindOf") String kindOf,
			@PathVariable("openid") String openid) {
		WechatOAConfig woaconfig = getOAConfig(kindOf);
		logger.debug("appid={},secret={}",woaconfig.getApp_id(),woaconfig.getSecret());
		String asscessToken = wechatApiService.getAccessToken(woaconfig.getApp_id(), woaconfig.getSecret());
		return wechatApiService.getUserInfo(asscessToken, openid);
	}
	
}
