package com.lebaoxun.guess.core.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.lebaoxun.guess.entity.GuessOrder;


@Mapper
public interface IGuessOrderMapper {
	
	List<GuessOrder> findByForPager(@Param("order")GuessOrder order,
			@Param("start")String start,@Param("end")String end,@Param("size")Integer size,@Param("offset")Integer offset);
	
	int countBy(@Param("order")GuessOrder order,
			@Param("start")String start,@Param("end")String end);
	
	List<GuessOrder> findAllBy(@Param("order")GuessOrder order,
			@Param("start")String start,@Param("end")String end);
	
	List<GuessOrder> findAlByKindOfAndExpect(@Param("kindOf") String kindOf,@Param("expect") String expect);
	
	GuessOrder findOneBy(@Param("order")GuessOrder order);
	
	int insert(@Param("order")GuessOrder order); 
	
	int update(@Param("order")GuessOrder order);
	
	int delete(@Param("id")String  id);
	
	int deleteByOrderNo(@Param("orderNo")String orderNo);
}
