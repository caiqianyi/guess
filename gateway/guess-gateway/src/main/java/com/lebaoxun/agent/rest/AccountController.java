package com.lebaoxun.agent.rest;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lebaoxun.account.entity.User;
import com.lebaoxun.account.service.IAccountService;
import com.lebaoxun.agent.security.DesUtils;
import com.lebaoxun.agent.security.Oauth2SecuritySubject;
import com.lebaoxun.commons.exception.SuccessMessage;
import com.lebaoxun.guess.caipiao.service.IGuessTemplateService;

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
	SuccessMessage info() throws Exception{
		User user = oauth2SecuritySubject.getCurrentUser();
		user.setPassword(null);
		user.setAccountSign(null);
		return new SuccessMessage(user);
	}
	
	/**
	 * 房卡交易记录
	 * @param size
	 * @param offset
	 * @param tradeType
	 * @param start
	 * @param end
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/transaction/record/{size}/{offset}/", method = RequestMethod.GET)
	SuccessMessage transactionRecord(@PathVariable("size") Integer size,
			@PathVariable("offset") Integer offset, 
			String tradeType,String start,String end) throws Exception{
		User user = oauth2SecuritySubject.getCurrentUser();
		return new SuccessMessage(accountService.findTradeRecordByUserid(user.getUserId(), tradeType, start, end, size, offset));
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	SuccessMessage logout(){
		oauth2SecuritySubject.logout();
		return new SuccessMessage();
	}
}
