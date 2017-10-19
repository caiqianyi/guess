package com.caiqianyi.account.dao;

import java.math.BigDecimal;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.caiqianyi.account.entity.User;
import com.caiqianyi.account.vo.UserVo;

@Mapper
public interface IUserMapper {

	User login(@Param(value = "account") String account,@Param(value = "password") String password);
	
	User findByAccount(@Param(value = "account")String account);
	
	UserVo findUserVoByAccount(String account);

	User findByUnionid(@Param(value = "unionid")String unionid);
	
	UserVo findUserVoByUnionid(@Param(value = "unionid")String unionid);

	User findByOpenid(@Param(value = "openid")String openid);
	
	UserVo findUserVoByOpenid(@Param(value = "openid")String openid);

	User findByMobile(@Param(value = "mobile")String mobile);
	
	UserVo findUserVoByMobile(@Param(value = "mobile")String mobile);
	
	User findById(@Param(value = "id")String id);
	
	UserVo findUserVoById(@Param(value = "id")String id);
	
	UserVo findAccountNormal(@Param(value = "id")String id);
	
	int modifyBalance(@Param(value = "id")String id,@Param(value = "balance")BigDecimal balance,
			@Param(value = "frozenMoney")BigDecimal frozenMoney);
	
	int update(User user);
	
	int register(User user);
	
}