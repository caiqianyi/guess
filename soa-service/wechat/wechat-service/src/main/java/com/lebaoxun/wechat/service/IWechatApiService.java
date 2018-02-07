package com.lebaoxun.wechat.service;

import com.lebaoxun.wechat.vo.AccessToken;
import com.lebaoxun.wechat.vo.JsapiTicket;
import com.lebaoxun.wechat.vo.WechatUserInfo;

public interface IWechatApiService {
	
	AccessToken getAccessToken(String appid, String secret, String code);
	
	String getAccessToken(String appid, String secret);
	
	WechatUserInfo getUserInfo(String access_token, String openid);
	
	String getJsapiTicket(String appid, String secret);
	
}
