package com.lebaoxun.guess.caipiao.core.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.lebaoxun.guess.jclq.entity.JCLQMatch;
import com.lebaoxun.guess.jclq.vo.JCLQMatchGuessTopic;

@Mapper
public interface IJCLQMatchMapper {

	int insert(JCLQMatch match);
	
	int update(JCLQMatch match);
	
	int countMatchByTime(@Param("league") String league,
			@Param("start") String start,@Param("end") String end);
	
	List<JCLQMatchGuessTopic> findAllMatchTopicByDay(@Param("day") String day);
	
	List<JCLQMatch> findAllMatchByTime(@Param("league") String league,
			@Param("start") String start,@Param("end") String end,
			@Param("size") Integer size,@Param("offset") Integer offset);
	
	List<JCLQMatch> findAllMatchByDay(@Param("league") String league,
			@Param("day") String day,
			@Param("status") String status);
	
	JCLQMatch findMatch(@Param("seq") String seq);
	
}
