package com.caiqianyi.wechat.service;

import com.ylhy.wechat.vo.AccessToken;
import com.ylhy.wechat.vo.WechatUserInfo;

public interface IWechatApiService {
	
	AccessToken getAccessToken(String appid, String secret, String code);
	
	String getAccessToken(String appid, String secret);
	
	WechatUserInfo getUserInfo(String access_token, String openid);
	
}
