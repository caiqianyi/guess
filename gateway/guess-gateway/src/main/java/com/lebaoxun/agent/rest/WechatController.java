package com.lebaoxun.agent.rest;

import java.util.Random;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lebaoxun.commons.exception.SuccessMessage;
import com.lebaoxun.commons.utils.MD5;
import com.lebaoxun.commons.utils.SHA1;
import com.lebaoxun.wechat.service.IWechatService;
import com.lebaoxun.wechat.vo.JsapiTicket;
import com.lebaoxun.wechat.vo.WechatOAConfig;

@RestController
public class WechatController {

	@Resource
	private IWechatService wechatService;
	
	@RequestMapping(value="/wechat/jsapiTikect",method=RequestMethod.GET)
	SuccessMessage getJsapiTicket(@RequestParam("url") String url) {
		String kindOf = "guess";
		WechatOAConfig woa = wechatService.getOAConfig(kindOf);
		String ticket = wechatService.getJsapiTicket(kindOf);
		
		// TODO Auto-generated method stub
		Random random = new Random();
		String noncestr = MD5.md5(String.valueOf(random.nextInt(10000)));
		String timestamp = String.valueOf(System.currentTimeMillis() / 1000);
		String signature = SHA1.encrypt("jsapi_ticket=" + ticket + "&noncestr=" + noncestr + "&timestamp=" + timestamp + "&url=" + url);
		JsapiTicket jt = new JsapiTicket();
		jt.setAppid(woa.getApp_id());
		jt.setNoncestr(noncestr);
		jt.setSignature(signature);
		jt.setTimestamp(timestamp);
		return new SuccessMessage(jt);
	}
	
	@RequestMapping(value="/wechat/userInfo",method=RequestMethod.GET)
	SuccessMessage getUserInfo(@RequestParam("openid") String openid) {
		String kindOf = "guess";
		return new SuccessMessage(wechatService.getUserInfoByKindOf(kindOf, openid));
	}

}
