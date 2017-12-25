package com.caiqianyi.account.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.caiqianyi.account.dao.ITradeRecordMapper;
import com.caiqianyi.account.dao.IUserMapper;
import com.caiqianyi.account.entity.TradeRecord;
import com.caiqianyi.account.entity.User;
import com.caiqianyi.account.service.IAccountService;
import com.caiqianyi.commons.pager.Pager;

@Service
public class AccountServiceImpl implements IAccountService {
	
	private Logger logger = LoggerFactory.getLogger(AccountServiceImpl.class);
	
	@Resource
	private IUserMapper userMapper;
	
	@Resource
	private ITradeRecordMapper tradeRecordMapper;

	@Override
	public User login(String account, String password) {
		// TODO Auto-generated method stub
		return userMapper.login(account, password);
	}

	@Override
	public User findByAccount(String account) {
		// TODO Auto-generated method stub
		return userMapper.findByAccount(account);
	}

	@Override
	public User findByUnionid(String unionid) {
		// TODO Auto-generated method stub
		return userMapper.findByUnionid(unionid);
	}

	@Override
	public User findByOpenid(String openid) {
		// TODO Auto-generated method stub
		return userMapper.findByOpenid(openid);
	}

	@Override
	public User findByMobile(String mobile) {
		// TODO Auto-generated method stub
		return userMapper.findByMobile(mobile);
	}

	@Override
	public User findById(Integer userId) {
		// TODO Auto-generated method stub
		return userMapper.findById(userId);
	}

	@Override
	@Transactional(readOnly=false,timeout=10,propagation=Propagation.REQUIRED)
	public void modifyBalance(Integer userId, Integer balance,
			Integer frozenMoney,TradeRecord tradeRecord) {
		// TODO Auto-generated method stub
		userMapper.modifyBalance(userId, balance , frozenMoney,balance+"c8993417ca184f89a8bd492d86c750d3"+frozenMoney);
		tradeRecord.setUserId(userId);
		tradeRecordMapper.saveTradeRecord(tradeRecord);
	}

	@Override
	@Transactional(readOnly=false,timeout=10,propagation=Propagation.REQUIRED)
	public void update(User user) {
		// TODO Auto-generated method stub
		userMapper.update(user);
	}

	@Override
	@Transactional(readOnly=false,timeout=10,propagation=Propagation.REQUIRED)
	public void register(User user) {
		// TODO Auto-generated method stub
		user.setBalance(0);
		userMapper.register(user);
	}

	@Override
	public Pager findTradeRecordByUserid(Integer userId, String tradeType, String start,
			String end,Integer size,Integer offset) {
		// TODO Auto-generated method stub
		Pager pager = new Pager();
		
		List<TradeRecord> datas = tradeRecordMapper.findTradeRecordByUserid(userId, tradeType, start, end, size, offset);
		pager.setDatas(datas);
		pager.setOffset(offset);
		pager.setSize(size);
		tradeRecordMapper.countTradeRecordByUserid(userId, tradeType, start, end);
		return pager;
	}

	@Override
	public TradeRecord findTradeRecordByReferId(Integer userId,String referId,
			String tradeType) {
		// TODO Auto-generated method stub
		return tradeRecordMapper.findTradeRecordByReferId(userId, referId, tradeType);
	}
	
}
