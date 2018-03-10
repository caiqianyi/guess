package com.lebaoxun.account.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.lebaoxun.account.entity.TradeRecord;
import com.lebaoxun.account.entity.User;
import com.lebaoxun.account.service.hystrix.AccountServiceHystrix;
import com.lebaoxun.commons.exception.SuccessMessage;
import com.lebaoxun.commons.pager.Pager;

@FeignClient(value="account-service",fallback=AccountServiceHystrix.class)
public interface IAccountService {
	
	@RequestMapping(value="/account/login/{account}/{password}/",method=RequestMethod.GET)
	User login(@PathVariable(value="account")String account,@PathVariable(value="password")String password);
	
	@RequestMapping(value="/account/findByAccount/{account}/",method=RequestMethod.GET)
	User findByAccount(@PathVariable(value="account")String account);
	
	@RequestMapping(value="/account/findUserByAccount/{account}/",method=RequestMethod.GET)
	User findUserByAccount(@PathVariable(value="account")String account);

	@RequestMapping(value="/account/findByUnionid/{unionid}/",method=RequestMethod.GET)
	User findByUnionid(@PathVariable(value="unionid")String unionid);
	
	@RequestMapping(value="/account/findUserByUnionid/{unionid}/",method=RequestMethod.GET)
	User findUserByUnionid(@PathVariable(value="unionid")String unionid);

	@RequestMapping(value="/account/findByOpenid/{openid}/",method=RequestMethod.GET)
	User findByOpenid(@PathVariable(value="openid")String openid);
	
	@RequestMapping(value="/account/findUserByOpenid/{openid}/",method=RequestMethod.GET)
	User findUserByOpenid(@PathVariable(value="openid")String openid);

	@RequestMapping(value="/account/findByMobile/{mobile}/",method=RequestMethod.GET)
	User findByMobile(@PathVariable(value="mobile")String mobile);
	
	@RequestMapping(value="/account/findUserByMobile/{mobile}/",method=RequestMethod.GET)
	User findUserByMobile(@PathVariable(value="mobile")String mobile);
	
	@RequestMapping(value="/account/findById/{userId}/",method=RequestMethod.GET)
	User findById(@PathVariable(value="userId")Integer userId);
	
	@RequestMapping(value="/account/findUserById/{id}/",method=RequestMethod.GET)
	User findUserById(@PathVariable(value="id")String id);
	
	@RequestMapping(value="/account/modifyBalance/{userId}/{balance}/{frozen}/",method=RequestMethod.POST)
	SuccessMessage modifyBalance(@PathVariable(value="userId")Integer userId,@PathVariable(value="balance")Integer balance,
			@PathVariable(value="frozen")Integer frozen,@RequestBody TradeRecord tradeRecord);
	
	@RequestMapping(value="/account/update/",method=RequestMethod.POST)
	SuccessMessage update(@RequestBody User user);
	
	@RequestMapping(value="/account/register/",method=RequestMethod.POST)
	SuccessMessage register(@RequestBody User user);
	
	/**
	 * 查询用户时间内账户交易记录
	 * @param id
	 * @param start
	 * @param end
	 * @return
	 */
	@RequestMapping(value="/account/findTradeRecordByUserid/{userId}/",method=RequestMethod.GET)
	Pager findTradeRecordByUserid(
			@PathVariable(value="userId")Integer userId,
			@PathVariable(value="tradeType",required=false)String tradeType,
			@RequestParam(value="start",required=false)String start,
			@RequestParam(value="end",required=false)String end,
			@RequestParam(value="size")Integer size,
			@RequestParam(value="offset")Integer offset);
	
	/**
	 * 通关用户关联记录 查询对应交易记录
	 * @param referId
	 * @param tradeType
	 * @return
	 */
	@RequestMapping(value="/account/findTradeRecordByReferId/{userId}/{referId}/{tradeType}",method=RequestMethod.GET)
	TradeRecord findTradeRecordByReferId(@PathVariable(value="userId")Integer userId,
			@PathVariable(value="referId")String referId,
			@PathVariable(value="tradeType")String tradeType);
	
	@RequestMapping(value="/account/findCacheInfoByUserId/",method=RequestMethod.GET)
	User findCacheInfoByUserId(@RequestParam("userId") Integer userId);
}
