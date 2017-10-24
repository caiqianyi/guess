package com.caiqianyi.guess.core.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.caiqianyi.guess.entity.GuessOrder;
import com.caiqianyi.soa.web.framework.model.Pager;


@Mapper
public interface IGuessOrderMapper {
	
	GuessOrder findByOrderNo(@Param("orderNo")String orderNo);
	
	GuessOrder findById(@Param("id")String id);
	
	List<GuessOrder> findByUserIdForPager(@Param("userId")String userId,
			@Param("status")Integer status,@Param("topicId") Integer topicId,
			@Param("start")Date start,@Param("end")Date end,Pager pager);
	
	List<GuessOrder> findByUserId(@Param("userId")String userId,
			@Param("status")Integer status,@Param("topicId") Integer topicId,
			@Param("optionId")String optionId,
			@Param("start")Date start,@Param("end")Date end);
	
	int insert(GuessOrder order); 
	
	int update(GuessOrder order);
	
	int delete(@Param("id")String  id);
	
	int deleteByOrderNo(@Param("orderNo")String orderNo);
}
