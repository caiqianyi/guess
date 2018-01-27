package com.caiqianyi.wechat.service.hystrix;

import org.springframework.stereotype.Component;

import com.caiqianyi.commons.exception.SuccessMessage;
import com.caiqianyi.wechat.service.IWechatService;
import com.ylhy.wechat.vo.AccessToken;
import com.ylhy.wechat.vo.WechatOAConfig;
import com.ylhy.wechat.vo.WechatUserInfo;

@Component
public class WechatServiceHystrix implements IWechatService {
	
	@Override
	public WechatOAConfig getOAConfig(String kindOf) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AccessToken getAccessToken(String code, String kindOf) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public WechatUserInfo getUserInfo(String access_token, String openid) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public WechatUserInfo getUserInfoByKindOf(String kindOf, String openid) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public SuccessMessage getOuath2Url(String kindOf, String redirect_uri, String state) {
		// TODO Auto-generated method stub
		return null;
	}
}
