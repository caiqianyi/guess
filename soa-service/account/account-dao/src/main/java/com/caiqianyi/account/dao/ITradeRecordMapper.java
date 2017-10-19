package com.caiqianyi.account.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.caiqianyi.account.entity.TradeRecord;

@Mapper
public interface ITradeRecordMapper {
	/**
	 * 查询用户所有账户交易记录
	 * @param userId
	 * @return
	 */
	public List<TradeRecord> findAllTradeRecordByUserid(@Param("userId")String userId,@Param("size")Integer size,@Param("offset")Integer offset);
	
	/**
	 * 查询用户时间内账户交易记录
	 * @param userId
	 * @param start
	 * @param end
	 * @return
	 */
	public List<TradeRecord> findTradeRecordByUserid(@Param("userId")String userId,@Param("start")Date start,@Param("end")Date end,@Param("size")Integer size,@Param("offset")Integer offset);
	
	/**
	 * 通关用户关联记录 查询对应交易记录
	 * @param referId
	 * @param tradeType
	 * @return
	 */
	public TradeRecord findTradeRecordByReferId(@Param("referId")String referId,@Param("tradeType")String tradeType);
	
	/**
	 * 新增交易记录
	 * @param tradeRecord
	 * @return
	 */
	public int saveTradeRecord(TradeRecord tradeRecord);
}
