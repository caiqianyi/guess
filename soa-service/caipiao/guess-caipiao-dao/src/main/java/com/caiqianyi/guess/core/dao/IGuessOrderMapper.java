package com.caiqianyi.guess.core.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.caiqianyi.guess.entity.GuessOrder;


@Mapper
public interface IGuessOrderMapper {
	
	List<GuessOrder> findByForPager(@Param("order")GuessOrder order,
			@Param("start")String start,@Param("end")String end,Integer size,Integer offset);
	
	int countBy(@Param("order")GuessOrder order,
			@Param("start")String start,@Param("end")String end);
	
	List<GuessOrder> findAllBy(@Param("order")GuessOrder order,
			@Param("start")String start,@Param("end")String end);
	
	GuessOrder findOneBy(@Param("order")GuessOrder order);
	
	int insert(@Param("order")GuessOrder order); 
	
	int update(@Param("order")GuessOrder order);
	
	int delete(@Param("id")String  id);
	
	int deleteByOrderNo(@Param("orderNo")String orderNo);
}
