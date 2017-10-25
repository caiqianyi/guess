package com.caiqianyi.guess.core.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.caiqianyi.commons.pager.Pager;
import com.caiqianyi.guess.entity.GuessOrder;


@Mapper
public interface IGuessOrderMapper {
	
	List<GuessOrder> findByForPager(@Param("order")GuessOrder order,
			@Param("start")Date start,@Param("end")Date end,Pager pager);
	
	List<GuessOrder> findAllBy(@Param("order")GuessOrder order,
			@Param("start")Date start,@Param("end")Date end);
	
	GuessOrder findOneBy(@Param("order")GuessOrder order);
	
	int insert(GuessOrder order); 
	
	int update(GuessOrder order);
	
	int delete(@Param("id")String  id);
	
	int deleteByOrderNo(@Param("orderNo")String orderNo);
}
