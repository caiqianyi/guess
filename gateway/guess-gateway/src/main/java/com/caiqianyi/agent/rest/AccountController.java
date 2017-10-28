package com.caiqianyi.agent.rest;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.caiqianyi.account.entity.User;
import com.caiqianyi.account.service.IAccountService;
import com.caiqianyi.agent.security.DesUtils;
import com.caiqianyi.agent.security.Oauth2SecuritySubject;
import com.caiqianyi.commons.exception.I18nMessageException;
import com.caiqianyi.commons.exception.SuccessMessage;
import com.caiqianyi.commons.utils.PwdUtil;

@RestController
@RequestMapping("/account")
public class AccountController extends BaseController{
	
	private Logger logger = LoggerFactory.getLogger(AccountController.class);
	
	@Resource
	private Oauth2SecuritySubject oauth2SecuritySubject;
	
	@Resource
	private IAccountService accountService;
	
	@Resource
	private DesUtils desUtils;
	
	@RequestMapping(value = "/authority", method = RequestMethod.GET)
	SuccessMessage authority(){
		User user = oauth2SecuritySubject.getCurrentUser();
		return new SuccessMessage(oauth2SecuritySubject.getAuthority(user.getAccount()));
	}
	
	@RequestMapping(value = "/info", method = RequestMethod.GET)
	public SuccessMessage info() throws Exception{
		User user = oauth2SecuritySubject.getCurrentUser();
		user.setPassword(null);
		user.setAccountSign(null);
		return new SuccessMessage(user);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/editInfo")
	SuccessMessage editInfo(String nickName){
		User user = oauth2SecuritySubject.getCurrentUser();
		User u = new User();
		u.setId(user.getId());
		u.setNickName(nickName);
		return accountService.update(u);
	}
	
	@RequestMapping(value = "/resetPassword", method = RequestMethod.POST)
	public SuccessMessage resetPassword(String prepsw,String newpsw){
		String newpasswd,passwd;
		logger.info("prepsw={},newpsw={}",prepsw,newpsw);
		try {
			passwd = desUtils.decrypt(prepsw);
			newpasswd = desUtils.decrypt(newpsw);
		} catch (Exception e) {
			e.printStackTrace();
			throw new I18nMessageException("500", "非法请求",e);
		}
		logger.info("newpasswd={},passwd={}",newpasswd,passwd);
		User current = oauth2SecuritySubject.getCurrentUser();
		
		String pw = PwdUtil.getMd5Password(current.getAccount(), passwd);
		String npw = PwdUtil.getMd5Password(current.getAccount(), newpasswd);
		logger.info("npw={},pw={}",npw,pw);
		
		if (!pw.equals(current.getPassword())) {
			throw new I18nMessageException("10004","原密码不对");
		}
		
		User user = new User();
		user.setId(user.getId());
		user.setPassword(npw);
		return accountService.update(user);
	}
	
}
