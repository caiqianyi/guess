package com.lebaoxun.guess.caipiao.core.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.lebaoxun.guess.caipiao.entity.LotteryIssue;

@Mapper
public interface ILotteryIssueMapper {

	int insert(@Param("issue") LotteryIssue issue);

	int update(@Param("issue") LotteryIssue issue);

	LotteryIssue getCurrentIssue(@Param("kindOf") String kindOf);
	
	LotteryIssue getCurrentPrevIssue(@Param("kindOf") String kindOf);

	LotteryIssue getIssueByExpect(@Param("kindOf") String kindOf,
			@Param("expect") String expect);
	
	List<LotteryIssue> getIssusByKindOfAndStatus(@Param("kindOf") String kindOf,
			@Param("status")Integer status,
			@Param("day")String day);

	List<LotteryIssue> getHistoryOpenCode(@Param("kindOf") String kindOf,
			@Param("start") Date start, @Param("end") Date end,
			@Param("size") Integer size, @Param("offset") Integer offset);
	
	List<String> findAllKindOf();
	
}
