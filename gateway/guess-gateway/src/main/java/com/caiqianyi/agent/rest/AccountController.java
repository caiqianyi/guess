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
import com.caiqianyi.commons.exception.SuccessMessage;
import com.caiqianyi.guess.caipiao.service.IGuessTemplateService;

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
	
	@RequestMapping(value = "/info", method = RequestMethod.GET)
	public SuccessMessage info() throws Exception{
		User user = oauth2SecuritySubject.getCurrentUser();
		user.setPassword(null);
		user.setAccountSign(null);
		return new SuccessMessage(user);
	}
	
}
