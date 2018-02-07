package com.lebaoxun.account.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.lebaoxun.account.entity.TradeRecord;

@Mapper
public interface ITradeRecordMapper {
	
	/**
	 * 查询用户时间内账户交易记录
	 * @param userId
	 * @param start
	 * @param end
	 * @return
	 */
	List<TradeRecord> findTradeRecordByUserid(@Param("userId")Integer userId,@Param("tradeType")String tradeType,@Param("start")String start,@Param("end")String end,@Param("size")Integer size,@Param("offset")Integer offset);
	
	int countTradeRecordByUserid(@Param("userId")Integer userId,@Param("tradeType")String tradeType,@Param("start")String start,@Param("end")String end);
	
	/**
	 * 通关用户关联记录 查询对应交易记录
	 * @param referId
	 * @param tradeType
	 * @return
	 */
	TradeRecord findTradeRecordByReferId(@Param("userId") Integer userId, @Param("referId")String referId,@Param("tradeType")String tradeType);
	
	/**
	 * 新增交易记录
	 * @param tradeRecord
	 * @return
	 */
	int saveTradeRecord(TradeRecord tradeRecord);
}
