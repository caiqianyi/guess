package com.lebaoxun.bbs.core.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.lebaoxun.bbs.core.entity.PraiseLog;

@Mapper
public interface IPraiseLogMapper {
	
	int save(PraiseLog praiseLog);
	
	int deleteBy(@Param("submeter") String submeter,
			@Param("logType") String logType,
			@Param("recordId") String recordId,
			@Param("userId") Integer userId);
	
	int countByUser(@Param("submeter") String submeter,
			@Param("logType") String logType,
			@Param("recordId") String recordId,
			@Param("userId") Integer userId);
}
