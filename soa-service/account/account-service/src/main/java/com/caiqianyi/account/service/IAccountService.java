package com.caiqianyi.account.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.caiqianyi.account.entity.TradeRecord;
import com.caiqianyi.account.entity.User;
import com.caiqianyi.account.vo.UserVo;



public interface IAccountService {
	
	User login(String account,String password);
	
	User findByAccount(String account);
	
	UserVo findUserVoByAccount(String account);

	User findByUnionid(String unionid);
	
	UserVo findUserVoByUnionid(String unionid);

	User findByOpenid(String openid);
	
	UserVo findUserVoByOpenid(String openid);

	User findByMobile(String mobile);
	
	UserVo findUserVoByMobile(String mobile);
	
	User findById(String id);
	
	UserVo findUserVoById(String id);
	
	void modifyBalance(String id,BigDecimal balance,BigDecimal frozenMoney,TradeRecord tradeRecord);
	
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
