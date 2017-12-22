package com.caiqianyi.agent.rest;

import java.util.Map;
import java.util.TreeMap;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.caiqianyi.account.entity.User;
import com.caiqianyi.account.service.IAccountService;
import com.caiqianyi.agent.constants.CacheKeyConstant;
import com.caiqianyi.agent.constants.WebConstants;
import com.caiqianyi.agent.security.DesUtils;
import com.caiqianyi.agent.security.GlobalToken;
import com.caiqianyi.agent.security.Oauth2;
import com.caiqianyi.agent.security.Oauth2SecuritySubject;
import com.caiqianyi.commons.exception.I18nMessageException;
import com.caiqianyi.commons.exception.SuccessMessage;
import com.caiqianyi.commons.utils.GenerateCode;
import com.caiqianyi.commons.utils.PwdUtil;
import com.caiqianyi.soa.core.redis.IRedisCache;
import com.caiqianyi.wechat.service.IWechatService;
import com.google.gson.Gson;
import com.ylhy.wechat.vo.AccessToken;
import com.ylhy.wechat.vo.WechatUserInfo;

@RestController
public class Oauth2Controller extends BaseController{
	
	private Logger logger = LoggerFactory.getLogger(Oauth2Controller.class);
	
	@Resource
	private IRedisCache redisCache;
	
	@Resource
	private DesUtils desUtils;
	
	@Resource
	private Oauth2SecuritySubject oauth2SecuritySubject;
	
	@Resource
	private IAccountService accountService;
	
	@Resource
	private IWechatService wechatService;
	
	@RequestMapping(value = "/oauth2/token/refresh", method = RequestMethod.GET)
	SuccessMessage oauthToken(String username,String password,
			String platform,
			String verfiyCode,String openid){
		if(StringUtils.isBlank(openid)){
			
			if(!"app".equals(platform)
					&& !"wechatOA".equals(platform)){
				String verifycode = (String) request.getSession().getAttribute(WebConstants.SYS_VERIFYCODE);
				logger.debug("verifycode={},verfiyCode={}",verifycode,verfiyCode);
				if(verifycode == null || !verifycode.equalsIgnoreCase(verfiyCode)){
					throw new I18nMessageException("10001", "验证码不正确");
				}
				request.getSession().removeAttribute(WebConstants.SYS_VERIFYCODE);
			}
			if(StringUtils.isBlank(username) || StringUtils.isBlank(password)){
				throw new I18nMessageException("10001", "验证码不正确");
			}
			String key = CacheKeyConstant.CACHEKEY_LOGIN_ACCOUNT_LOCK + "." + username;
			String value = (String) redisCache.get(key);
			if (StringUtils.isNotBlank(value) && "LOCK".equals(value)) {
				throw new I18nMessageException("10005",new String[]{CacheKeyConstant.ACCOUNT_ERROR_LOCK_TIME/3600+"",redisCache.ttl(key)/60+1+""});
			}
			Integer errorCount = 0;
			if(StringUtils.isNumeric(value)){
				errorCount = Integer.parseInt(value);
			}
			
			String account = username,pw = password,passwd = null;
			if(!"wechatOA".equals(platform)){
				try {
					account = desUtils.decrypt(username);
					passwd = desUtils.decrypt(password);
				} catch (Exception e) {
					IncrErrorCount(username, value);
					throw new I18nMessageException("10002", new String[]{CacheKeyConstant.ACCOUNT_ERROR_COUNT+"",CacheKeyConstant.ACCOUNT_ERROR_LOCK_TIME/3600+"",(CacheKeyConstant.ACCOUNT_ERROR_COUNT-errorCount-1)+""});
				}
				pw = PwdUtil.getMd5Password(account, passwd);
				logger.info("username={},password={},pw={}",account,passwd,pw);
			}
			
			User a = accountService.login(account, pw);
			if(a == null){
				IncrErrorCount(username, value);
				throw new I18nMessageException("10002", new String[]{CacheKeyConstant.ACCOUNT_ERROR_COUNT+"",CacheKeyConstant.ACCOUNT_ERROR_LOCK_TIME/3600+"",(CacheKeyConstant.ACCOUNT_ERROR_COUNT-errorCount-1)+""});
			}
			openid = oauth2SecuritySubject.getOpenid(account);
		}
		Oauth2 oauth2 = oauth2SecuritySubject.refreshToken(openid);
		GlobalToken.setToken(oauth2.getAssess_token());
		Map<String,Object> json = new TreeMap<String,Object>();
		json.put("access_token", oauth2.getAssess_token());
		json.put("openid", oauth2.getOpenid());
		json.put("expires_in", oauth2.getExpires_in());
		return new SuccessMessage(json);
	}
	
	/**
	 * 微信授权登录、注册
	 * @param code
	 * @param state
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/oauth2/token/wechatOA", method = RequestMethod.GET)
	SuccessMessage oauthTokenForWechatOA(String code,String state,
			String openid, String access_token) throws Exception{
		if(StringUtils.isNotBlank(code)){
			logger.info("wechatOA|code={},state={}",code,state);
			AccessToken accessToken = wechatService.getAccessToken(code, state);
			if(accessToken == null || StringUtils.isBlank(accessToken.getOpenid())){
				throw new I18nMessageException("40001","微信授权失败");
			}
			openid = accessToken.getOpenid();
			access_token = accessToken.getAccess_token();
		}
		User user = accountService.findByOpenid(openid);
		logger.debug("user={}",new Gson().toJson(user));
		if(user == null){
			WechatUserInfo userInfo = wechatService.getUserInfo(access_token, openid);
			Integer userId = GenerateCode.gen(8);
			String account = "WX_"+userId;
			String passwd = "R_"+GenerateCode.gen(6);
			String password = PwdUtil.getMd5Password(account, passwd);
			user = new User();
			
			user.setUserId(userId);
			user.setAccount(account);
			user.setPassword(password);
			user.setCity(userInfo.getCity());
			user.setCountry(userInfo.getCountry());
			user.setGroupid(userInfo.getGroupid());
			user.setHeadimgurl(userInfo.getHeadimgurl());
			user.setLanguage(userInfo.getLanguage());
			user.setNickname(userInfo.getNickname());
			user.setOpenid(userInfo.getOpenid());
			user.setProvince(userInfo.getProvince());
			user.setRemark(userInfo.getRemark());
			user.setSex(userInfo.getSex());
			user.setSource("wechatOA");
			user.setSubscribe(userInfo.getSubscribe());
			user.setSubscribeTime(userInfo.getSubscribe_time());
			user.setType("0");
			user.setUnionid(userInfo.getUnionid());
			
			accountService.register(user);
		}
		return oauthToken(user.getAccount(), user.getPassword(), "wechatOA", null, null);
	}
	
	@RequestMapping(value = "/token/clear", method = RequestMethod.GET)
	SuccessMessage logout(){
		oauth2SecuritySubject.logout();
		return new SuccessMessage();
	}
	
	private void IncrErrorCount(String account,String value) {
		// 一天中登录失败10次，账号锁定2小时
		String key = CacheKeyConstant.CACHEKEY_LOGIN_ACCOUNT_LOCK + "." + account;
		if(StringUtils.isBlank(value)) {
			redisCache.set(key, "1", CacheKeyConstant.ACCOUNT_ERROR_CHECK_EXPIRE);
		} else {
			int count = Integer.parseInt(value) + 1;
			if(count >= CacheKeyConstant.ACCOUNT_ERROR_COUNT) {
				String lockKey = CacheKeyConstant.CACHEKEY_LOGIN_ACCOUNT_LOCK+"."+account;
				redisCache.set(lockKey, "LOCK", CacheKeyConstant.ACCOUNT_ERROR_LOCK_TIME);
				redisCache.del(key);
			} else {
				redisCache.set(key, count+"", CacheKeyConstant.ACCOUNT_ERROR_CHECK_EXPIRE);
			}
		}
	}
}
