package com.caiqianyi.account.service.hystrix;

import org.springframework.stereotype.Component;

import com.caiqianyi.account.entity.TradeRecord;
import com.caiqianyi.account.entity.User;
import com.caiqianyi.account.service.IAccountService;
import com.caiqianyi.commons.exception.I18nMessageException;
import com.caiqianyi.commons.exception.SuccessMessage;
import com.caiqianyi.commons.pager.Pager;
@Component
public class AccountServiceHystrix implements IAccountService {

	@Override
	public User login(String account, String password) {
		throw new I18nMessageException("502","服务器异常，请稍后重试");
	}

	@Override
	public User findByAccount(String account) {
		// TODO Auto-generated method stub
		throw new I18nMessageException("502","服务器异常，请稍后重试");
	}

	@Override
	public User findUserByAccount(String account) {
		// TODO Auto-generated method stub
		throw new I18nMessageException("502","服务器异常，请稍后重试");
	}

	@Override
	public User findByUnionid(String unionid) {
		// TODO Auto-generated method stub
		throw new I18nMessageException("502","服务器异常，请稍后重试");
	}

	@Override
	public User findUserByUnionid(String unionid) {
		// TODO Auto-generated method stub
		throw new I18nMessageException("502","服务器异常，请稍后重试");
	}

	@Override
	public User findByOpenid(String openid) {
		// TODO Auto-generated method stub
		throw new I18nMessageException("502","服务器异常，请稍后重试");
	}

	@Override
	public User findUserByOpenid(String openid) {
		// TODO Auto-generated method stub
		throw new I18nMessageException("502","服务器异常，请稍后重试");
	}

	@Override
	public User findByMobile(String mobile) {
		// TODO Auto-generated method stub
		throw new I18nMessageException("502","服务器异常，请稍后重试");
	}

	@Override
	public User findUserByMobile(String mobile) {
		// TODO Auto-generated method stub
		throw new I18nMessageException("502","服务器异常，请稍后重试");
	}

	@Override
	public User findById(Integer userId) {
		// TODO Auto-generated method stub
		throw new I18nMessageException("502","服务器异常，请稍后重试");
	}

	@Override
	public User findUserById(String id) {
		// TODO Auto-generated method stub
		throw new I18nMessageException("502","服务器异常，请稍后重试");
	}

	@Override
	public SuccessMessage modifyBalance(Integer userId, Integer balance,
			Integer frozen, TradeRecord tradeRecord) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public SuccessMessage update(User user) {
		// TODO Auto-generated method stub
		throw new I18nMessageException("502","服务器异常，请稍后重试");
	}

	@Override
	public SuccessMessage register(User user) {
		// TODO Auto-generated method stub
		throw new I18nMessageException("502","服务器异常，请稍后重试");
	}

	@Override
	public TradeRecord findTradeRecordByReferId(Integer userId, String referId,
			String tradeType) {
		// TODO Auto-generated method stub
		throw new I18nMessageException("502","服务器异常，请稍后重试");
	}
	
	@Override
	public Pager findTradeRecordByUserid(Integer userId, String tradeType,
			String start, String end, Integer size, Integer offset) {
		// TODO Auto-generated method stub
		throw new I18nMessageException("502","服务器异常，请稍后重试");
	}
}
