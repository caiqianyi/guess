package com.caiqianyi.account.service.impl;

import java.math.BigDecimal;
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
import com.caiqianyi.account.vo.UserVo;

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
	public UserVo findUserVoByAccount(String account) {
		// TODO Auto-generated method stub
		return userMapper.findUserVoByAccount(account);
	}

	@Override
	public User findByUnionid(String unionid) {
		// TODO Auto-generated method stub
		return userMapper.findByUnionid(unionid);
	}

	@Override
	public UserVo findUserVoByUnionid(String unionid) {
		// TODO Auto-generated method stub
		return userMapper.findUserVoByUnionid(unionid);
	}

	@Override
	public User findByOpenid(String openid) {
		// TODO Auto-generated method stub
		return userMapper.findByOpenid(openid);
	}

	@Override
	public UserVo findUserVoByOpenid(String openid) {
		// TODO Auto-generated method stub
		return userMapper.findUserVoByOpenid(openid);
	}

	@Override
	public User findByMobile(String mobile) {
		// TODO Auto-generated method stub
		return userMapper.findByMobile(mobile);
	}

	@Override
	public UserVo findUserVoByMobile(String mobile) {
		// TODO Auto-generated method stub
		return userMapper.findUserVoByMobile(mobile);
	}

	@Override
	public User findById(String id) {
		// TODO Auto-generated method stub
		return userMapper.findById(id);
	}

	@Override
	public UserVo findUserVoById(String id) {
		// TODO Auto-generated method stub
		return userMapper.findUserVoById(id);
	}

	@Override
	@Transactional(readOnly=false,timeout=10,propagation=Propagation.REQUIRED)
	public void modifyBalance(String id, BigDecimal balance,
			BigDecimal frozenMoney,TradeRecord tradeRecord) {
		// TODO Auto-generated method stub
		userMapper.modifyBalance(id, balance, frozenMoney);
		tradeRecord.setUserId(id);
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
		userMapper.register(user);
	}

	@Override
	public List<TradeRecord> findAllTradeRecordByUserid(String userId,Integer size,Integer offset) {
		// TODO Auto-generated method stub
		return tradeRecordMapper.findAllTradeRecordByUserid(userId,size,offset);
	}

	@Override
	public List<TradeRecord> findTradeRecordByUserid(String userId, Date start,
			Date end,Integer size,Integer offset) {
		// TODO Auto-generated method stub
		return tradeRecordMapper.findTradeRecordByUserid(userId, start, end, size, offset);
	}

	@Override
	public TradeRecord findTradeRecordByReferId(String referId,
			String tradeType) {
		// TODO Auto-generated method stub
		return tradeRecordMapper.findTradeRecordByReferId(referId, tradeType);
	}
	
}
