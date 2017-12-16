package com.caiqianyi.wechat.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.caiqianyi.wechat.service.hystrix.WechatServiceHystrix;
import com.ylhy.wechat.vo.AccessToken;
import com.ylhy.wechat.vo.WechatOAConfig;
import com.ylhy.wechat.vo.WechatUserInfo;

@FeignClient(value="wechat-service",fallback=WechatServiceHystrix.class)
public interface IWechatService {
	
	@RequestMapping(value = "/wechat/OAConfig/{kindOf}/", method = RequestMethod.GET)
	WechatOAConfig getOAConfig(@PathVariable("kindOf") String kindOf);
	
	@RequestMapping(value = "/wechat/accessToken/{kindOf}/{code}/", method = RequestMethod.GET)
	AccessToken getAccessToken(@PathVariable("code") String code,
			@PathVariable("kindOf") String kindOf);
	
	@RequestMapping(value = "/wechat/userInfo/{openid}/{access_token}/", method = RequestMethod.GET)
	WechatUserInfo getUserInfo(@PathVariable("access_token") String access_token,
			@PathVariable("openid") String openid);
}
