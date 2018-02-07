package com.lebaoxun.account.dao;

import javax.annotation.Resource;

import com.lebaoxun.account.entity.TradeRecord;
import com.lebaoxun.account.entity.User;
import com.lebaoxun.commons.exception.I18nMessageException;

public abstract class UserTradeSupport {

	@Resource
	private IUserMapper userMapper;

	@Resource
	private ITradeRecordMapper tradeRecordMapper;

	/**
	 * 增加金额
	 * 
	 * @param userId
	 * @param balance
	 */
	public void increase(Integer userId, Integer tradeMoney,
			String tradeType, String referId, String descr) {
		User user = userMapper.findById(userId);
		if(user == null){
			throw new NullPointerException("The user is null!");
		}
		TradeRecord record = new TradeRecord();
		record.setDescr(descr);
		record.setMoney(user.getBalance());
		record.setTradeMoney(tradeMoney);
		record.setTradeType(tradeType);
		record.setReferId(referId);
		record.setStatus(1);
		record.setUserId(userId);
		tradeRecordMapper.saveTradeRecord(record);
		Integer balance = user.getBalance()+tradeMoney;
		userMapper.modifyBalance(userId, balance , user.getFrozen(),balance+"c8993417ca184f89a8bd492d86c750d3"+user.getFrozen());
	}
	
	public void decrease(Integer userId, Integer tradeMoney,
			String tradeType, String referId, String descr){
		User user = userMapper.findById(userId);
		if(user == null){
			throw new NullPointerException("The user is null!");
		}
		if(user.getBalance() < tradeMoney){
			throw new I18nMessageException("8001","余额不足，交易失败");
		}
		TradeRecord record = new TradeRecord();
		record.setDescr(descr);
		record.setMoney(user.getBalance());
		record.setTradeMoney(-tradeMoney);
		record.setTradeType(tradeType);
		record.setReferId(referId);
		record.setStatus(1);
		record.setUserId(userId);
		tradeRecordMapper.saveTradeRecord(record);
		Integer balance = user.getBalance()-tradeMoney;
		userMapper.modifyBalance(userId, balance , user.getFrozen(),balance+"c8993417ca184f89a8bd492d86c750d3"+user.getFrozen());
	}
}
