package com.lebaoxun.wechat.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.lebaoxun.commons.exception.SuccessMessage;
import com.lebaoxun.wechat.service.hystrix.WechatServiceHystrix;
import com.lebaoxun.wechat.vo.AccessToken;
import com.lebaoxun.wechat.vo.WechatOAConfig;
import com.lebaoxun.wechat.vo.WechatUserInfo;

@FeignClient(value="wechat-service",fallback=WechatServiceHystrix.class)
public interface IWechatService {
	
	@RequestMapping(value = "/wechat/OAConfig/{kindOf}/", method = RequestMethod.GET)
	WechatOAConfig getOAConfig(@PathVariable("kindOf") String kindOf);
	
	@RequestMapping(value = "/wechat/accessToken/{kindOf}/{code}/", method = RequestMethod.GET)
	AccessToken getAccessToken(@PathVariable("code") String code,
			@PathVariable("kindOf") String kindOf);
	
	@RequestMapping(value = "/wechat/accessToken/", method = RequestMethod.GET)
	String getAccessToken(@RequestParam("kindOf") String kindOf);
	
	@RequestMapping(value = "/wechat/jsapiTicket/", method = RequestMethod.GET)
	String getJsapiTicket(@RequestParam("kindOf") String kindOf);
	
	@RequestMapping(value = "/wechat/userInfo/{openid}/{access_token}/", method = RequestMethod.GET)
	WechatUserInfo getUserInfo(@PathVariable("access_token") String access_token,
			@PathVariable("openid") String openid);
	
	@RequestMapping(value = "/wechat/userInfo/kindOf/{kindOf}/{openid}/", method = RequestMethod.GET)
	WechatUserInfo getUserInfoByKindOf(@PathVariable("kindOf") String kindOf,
			@PathVariable("openid") String openid);
	
	@RequestMapping(value = "/wechat/ouath2/snsapi_userinfo/", method = RequestMethod.GET)
	SuccessMessage getOuath2Url(@RequestParam(value="kindOf") String kindOf,
			@RequestParam(value="redirect_uri") String redirect_uri,
			@RequestParam(value="state") String state);
}
