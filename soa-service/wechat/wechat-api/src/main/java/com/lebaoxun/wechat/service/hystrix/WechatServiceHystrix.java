package com.lebaoxun.wechat.service.hystrix;

import org.springframework.stereotype.Component;

import com.lebaoxun.commons.exception.SuccessMessage;
import com.lebaoxun.wechat.service.IWechatService;
import com.lebaoxun.wechat.vo.AccessToken;
import com.lebaoxun.wechat.vo.WechatOAConfig;
import com.lebaoxun.wechat.vo.WechatUserInfo;

@Component
public class WechatServiceHystrix implements IWechatService {
	
	@Override
	public String getAccessToken(String kindOf) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public String getJsapiTicket(String kindOf) {
		// TODO Auto-generated method stub
		return null;
	}
	
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
