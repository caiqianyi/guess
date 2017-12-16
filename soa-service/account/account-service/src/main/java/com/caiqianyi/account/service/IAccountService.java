package com.caiqianyi.account.service;

import java.util.Date;
import java.util.List;

import com.caiqianyi.account.entity.TradeRecord;
import com.caiqianyi.account.entity.User;



public interface IAccountService {
	
	User login(String account,String password);
	
	User findByAccount(String account);
	
	User findByUnionid(String unionid);
	
	User findByOpenid(String openid);

	User findByMobile(String mobile);
	
	User findById(String id);
	
	void modifyBalance(String id,Integer balance,Integer frozenMoney,TradeRecord tradeRecord);
	
	void update(User user);
	
	void register(User user);
	
	/**
	 * 查询用户所有账户交易记录
	 * @param userId
	 * @return
	 */
	List<TradeRecord> findAllTradeRecordByUserid(String userId,Integer size,Integer offset);
	
	/**
	 * 查询用户时间内账户交易记录
	 * @param userId
	 * @param start
	 * @param end
	 * @return
	 */
	List<TradeRecord> findTradeRecordByUserid(String userId,Date start,Date end,Integer size,Integer offset);
	
	/**
	 * 通关用户关联记录 查询对应交易记录
	 * @param referId
	 * @param tradeType
	 * @return
	 */
	TradeRecord findTradeRecordByReferId(String referId,String tradeType);
	
}
