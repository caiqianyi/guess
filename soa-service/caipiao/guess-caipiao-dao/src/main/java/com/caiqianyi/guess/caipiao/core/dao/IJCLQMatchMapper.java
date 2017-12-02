package com.caiqianyi.guess.caipiao.core.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.caiqianyi.guess.jclq.entity.JCLQMatch;
import com.caiqianyi.guess.jclq.entity.JCLQMatchDatas;

@Mapper
public interface IJCLQMatchMapper {

	int insert(JCLQMatch match);
	
	int insertDatas(JCLQMatchDatas datas);

	int update(JCLQMatch match);
	
	int updateDatas(JCLQMatchDatas datas);

	List<JCLQMatch> findAllMatchByDay(@Param("league") String league,
			@Param("day") String day,
			@Param("status") String status);
	
	JCLQMatch findMatch(@Param("seq") String seq);
	
	JCLQMatchDatas findMatchDatas(@Param("jclqMatchId") String jclqMatchId);
	
}
