package com.caiqianyi.account.service.hystrix;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import com.caiqianyi.account.entity.TradeRecord;
import com.caiqianyi.account.entity.User;
import com.caiqianyi.account.service.IAccountService;
import com.caiqianyi.account.vo.UserVo;
import com.caiqianyi.commons.exception.I18nMessageException;
import com.caiqianyi.commons.exception.SuccessMessage;
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
	public UserVo findUserVoByAccount(String account) {
		// TODO Auto-generated method stub
		throw new I18nMessageException("502","服务器异常，请稍后重试");
	}

	@Override
	public User findByUnionid(String unionid) {
		// TODO Auto-generated method stub
		throw new I18nMessageException("502","服务器异常，请稍后重试");
	}

	@Override
	public UserVo findUserVoByUnionid(String unionid) {
		// TODO Auto-generated method stub
		throw new I18nMessageException("502","服务器异常，请稍后重试");
	}

	@Override
	public User findByOpenid(String openid) {
		// TODO Auto-generated method stub
		throw new I18nMessageException("502","服务器异常，请稍后重试");
	}

	@Override
	public UserVo findUserVoByOpenid(String openid) {
		// TODO Auto-generated method stub
		throw new I18nMessageException("502","服务器异常，请稍后重试");
	}

	@Override
	public User findByMobile(String mobile) {
		// TODO Auto-generated method stub
		throw new I18nMessageException("502","服务器异常，请稍后重试");
	}

	@Override
	public UserVo findUserVoByMobile(String mobile) {
		// TODO Auto-generated method stub
		throw new I18nMessageException("502","服务器异常，请稍后重试");
	}

	@Override
	public User findById(String id) {
		// TODO Auto-generated method stub
		throw new I18nMessageException("502","服务器异常，请稍后重试");
	}

	@Override
	public UserVo findUserVoById(String id) {
		// TODO Auto-generated method stub
		throw new I18nMessageException("502","服务器异常，请稍后重试");
	}

	@Override
	public SuccessMessage modifyBalance(String id, BigDecimal balance,
			BigDecimal frozenMoney, TradeRecord tradeRecord) {
		// TODO Auto-generated method stub
		throw new I18nMessageException("502","服务器异常，请稍后重试");
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
	public List<TradeRecord> findAllTradeRecordByUserid(String id,
			Integer size, Integer offset) {
		// TODO Auto-generated method stub
		throw new I18nMessageException("502","服务器异常，请稍后重试");
	}

	@Override
	public List<TradeRecord> findTradeRecordByUserid(String id, Date start,
			Date end, Integer size, Integer offset) {
		// TODO Auto-generated method stub
		throw new I18nMessageException("502","服务器异常，请稍后重试");
	}

	@Override
	public TradeRecord findTradeRecordByReferId(String referId,
			String tradeType) {
		// TODO Auto-generated method stub
		throw new I18nMessageException("502","服务器异常，请稍后重试");
	}

}
