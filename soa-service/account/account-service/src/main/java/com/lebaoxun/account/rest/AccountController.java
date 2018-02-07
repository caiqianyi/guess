package com.lebaoxun.account.rest;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lebaoxun.account.entity.TradeRecord;
import com.lebaoxun.account.entity.User;
import com.lebaoxun.account.service.IAccountService;
import com.lebaoxun.commons.exception.SuccessMessage;
import com.lebaoxun.commons.pager.Pager;

@RestController
@RequestMapping("/account")
public class AccountController {
	
	@Resource
	private IAccountService accountService;
	
	@RequestMapping(value="/login/{account}/{password}/",method=RequestMethod.GET)
	User login(@PathVariable(value="account")String account,@PathVariable(value="password")String password){
		return accountService.login(account, password);
	}
	
	@RequestMapping(value="/findByAccount/{account}/",method=RequestMethod.GET)
	User findByAccount(@PathVariable(value="account")String account){
		return accountService.findByAccount(account);
	}
	
	@RequestMapping(value="/findByUnionid/{unionid}/",method=RequestMethod.GET)
	User findByUnionid(@PathVariable(value="unionid")String unionid){
		return accountService.findByUnionid(unionid);
	}
	
	@RequestMapping(value="/findByOpenid/{openid}/",method=RequestMethod.GET)
	User findByOpenid(@PathVariable(value="openid")String openid){
		return accountService.findByOpenid(openid);
	}

	@RequestMapping(value="/findByMobile/{mobile}/",method=RequestMethod.GET)
	User findByMobile(@PathVariable(value="mobile")String mobile){
		return accountService.findByMobile(mobile);
	}
	
	@RequestMapping(value="/findById/{userId}/",method=RequestMethod.GET)
	User findById(@PathVariable(value="userId")Integer userId){
		return accountService.findById(userId);
	}
	
	@RequestMapping(value="/modifyBalance/{userId}/{balance}/{frozen}/",method=RequestMethod.POST)
	SuccessMessage modifyBalance(@PathVariable(value="userId")Integer userId,@PathVariable(value="balance")Integer balance,
			@PathVariable(value="frozen")Integer frozen,@RequestBody TradeRecord tradeRecord){
		accountService.modifyBalance(userId, balance, frozen, tradeRecord);
		return new SuccessMessage("ok");
	}
	
	@RequestMapping(value="/update/",method=RequestMethod.POST)
	SuccessMessage update(@RequestBody User user){
		accountService.update(user);
		return new SuccessMessage("ok");
	}
	
	@RequestMapping(value="/register/",method=RequestMethod.POST)
	SuccessMessage register(@RequestBody User user){
		accountService.register(user);
		return new SuccessMessage("ok");
	}
	
	
	/**
	 * 查询用户时间内账户交易记录
	 * @param id
	 * @param start
	 * @param end
	 * @return
	 */
	@RequestMapping(value="/findTradeRecordByUserid/{userId}/",method=RequestMethod.GET)
	Pager findTradeRecordByUserid(
			@PathVariable(value="userId")Integer userId,
			@PathVariable(value="tradeType",required=false)String tradeType,
			@RequestParam(value="start",required=false)String start,
			@RequestParam(value="end",required=false)String end,
			@RequestParam(value="size")Integer size,
			@RequestParam(value="offset")Integer offset){
		return accountService.findTradeRecordByUserid(userId,tradeType, start, end, size, offset);
	}
	
	/**
	 * 通关用户关联记录 查询对应交易记录
	 * @param referId
	 * @param tradeType
	 * @return
	 */
	@RequestMapping(value="/findTradeRecordByReferId/{userId}/{referId}/{tradeType}",method=RequestMethod.GET)
	TradeRecord findTradeRecordByReferId(@PathVariable(value="userId")Integer userId,
			@PathVariable(value="referId")String referId,
			@PathVariable(value="tradeType")String tradeType){
		return accountService.findTradeRecordByReferId(userId, referId, tradeType);
	}
	
}
