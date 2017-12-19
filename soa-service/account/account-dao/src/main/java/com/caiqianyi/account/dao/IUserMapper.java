package com.caiqianyi.account.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.caiqianyi.account.entity.User;

@Mapper
public interface IUserMapper {

	User login(@Param(value = "account") String account,@Param(value = "password") String password);
	
	User findByAccount(@Param(value = "account")String account);
	
	User findUserByAccount(String account);

	User findByUnionid(@Param(value = "unionid")String unionid);
	
	User findByOpenid(@Param(value = "openid")String openid);
	
	User findByMobile(@Param(value = "mobile")String mobile);
	
	User findById(@Param(value = "userId")Integer userId);
	
	User findAccountNormal(@Param(value = "userId")Integer userId);
	
	int modifyBalance(@Param(value = "userId")Integer userId,@Param(value = "balance")Integer balance,
			@Param(value = "frozen")Integer frozen);
	
	int update(User user);
	
	int register(User user);
	
}