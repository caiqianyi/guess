package com.lebaoxun.account.service;

import com.lebaoxun.account.entity.TradeRecord;
import com.lebaoxun.account.entity.User;
import com.lebaoxun.commons.pager.Pager;



public interface IAccountService {
	
	User login(String account,String password);
	
	User findByAccount(String account);
	
	User findByUnionid(String unionid);
	
	User findByOpenid(String openid);

	User findByMobile(String mobile);
	
	User findById(Integer userId);
	
	void modifyBalance(Integer userId,Integer balance,Integer frozenMoney,TradeRecord tradeRecord);
	
	void update(User user);
	
	void register(User user);
	/**
	 * 查询用户时间内账户交易记录
	 * @param userId
	 * @param start
	 * @param end
	 * @return
	 */
	Pager findTradeRecordByUserid(Integer userId,String tradeType,String start,String end,Integer size,Integer offset);
	
	/**
	 * 通关用户关联记录 查询对应交易记录
	 * @param referId
	 * @param tradeType
	 * @return
	 */
	TradeRecord findTradeRecordByReferId(Integer userId,String referId,String tradeType);
	
}
