package com.caiqianyi.account.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.caiqianyi.account.entity.TradeRecord;
import com.caiqianyi.account.entity.User;
import com.caiqianyi.account.service.hystrix.AccountServiceHystrix;
import com.caiqianyi.account.vo.UserVo;
import com.caiqianyi.commons.exception.SuccessMessage;

@FeignClient(value="account-service",fallback=AccountServiceHystrix.class)
public interface IAccountService {
	
	@RequestMapping(value="/account/login/{account}/{password}/",method=RequestMethod.GET)
	User login(@PathVariable(value="account")String account,@PathVariable(value="password")String password);
	
	@RequestMapping(value="/account/findByAccount/{account}/",method=RequestMethod.GET)
	User findByAccount(@PathVariable(value="account")String account);
	
	@RequestMapping(value="/account/findUserVoByAccount/{account}/",method=RequestMethod.GET)
	UserVo findUserVoByAccount(@PathVariable(value="account")String account);

	@RequestMapping(value="/account/findByUnionid/{unionid}/",method=RequestMethod.GET)
	User findByUnionid(@PathVariable(value="unionid")String unionid);
	
	@RequestMapping(value="/account/findUserVoByUnionid/{unionid}/",method=RequestMethod.GET)
	UserVo findUserVoByUnionid(@PathVariable(value="unionid")String unionid);

	@RequestMapping(value="/account/findByOpenid/{openid}/",method=RequestMethod.GET)
	User findByOpenid(@PathVariable(value="openid")String openid);
	
	@RequestMapping(value="/account/findUserVoByOpenid/{openid}/",method=RequestMethod.GET)
	UserVo findUserVoByOpenid(@PathVariable(value="openid")String openid);

	@RequestMapping(value="/account/findByMobile/{mobile}/",method=RequestMethod.GET)
	User findByMobile(@PathVariable(value="mobile")String mobile);
	
	@RequestMapping(value="/account/findUserVoByMobile/{mobile}/",method=RequestMethod.GET)
	UserVo findUserVoByMobile(@PathVariable(value="mobile")String mobile);
	
	@RequestMapping(value="/account/findById/{id}/",method=RequestMethod.GET)
	User findById(@PathVariable(value="id")String id);
	
	@RequestMapping(value="/account/findUserVoById/{id}/",method=RequestMethod.GET)
	UserVo findUserVoById(@PathVariable(value="id")String id);
	
	@RequestMapping(value="/account/modifyBalance/{id}/{balance}/{frozenMoney}/",method=RequestMethod.POST)
	SuccessMessage modifyBalance(@PathVariable(value="id")String id,@PathVariable(value="balance")BigDecimal balance,
			@PathVariable(value="frozenMoney")BigDecimal frozenMoney,@RequestBody TradeRecord tradeRecord);
	
	@RequestMapping(value="/account/update/",method=RequestMethod.POST)
	SuccessMessage update(@RequestBody User user);
	
	@RequestMapping(value="/account/register/",method=RequestMethod.POST)
	SuccessMessage register(@RequestBody User user);
	
	/**
	 * 查询用户所有账户交易记录
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/account/findAllTradeRecordByUserid/{id}/",method=RequestMethod.GET)
	List<TradeRecord> findAllTradeRecordByUserid(@PathVariable(value="id")String id,
			@RequestParam(value="size")Integer size,
			@RequestParam(value="offset")Integer offset);
	
	/**
	 * 查询用户时间内账户交易记录
	 * @param id
	 * @param start
	 * @param end
	 * @return
	 */
	@RequestMapping(value="/account/findTradeRecordByUserid/{id}/",method=RequestMethod.GET)
	List<TradeRecord> findTradeRecordByUserid(@PathVariable(value="id")String id,
			@RequestParam(value="start")Date start,
			@RequestParam(value="end")Date end,
			@RequestParam(value="size")Integer size,
			@RequestParam(value="offset")Integer offset);
	
	/**
	 * 通关用户关联记录 查询对应交易记录
	 * @param referId
	 * @param tradeType
	 * @return
	 */
	@RequestMapping(value="/account/findTradeRecordByReferId/{referId}/{tradeType}",method=RequestMethod.GET)
	TradeRecord findTradeRecordByReferId(@PathVariable(value="referId")String referId,
			@PathVariable(value="tradeType")String tradeType);
}
