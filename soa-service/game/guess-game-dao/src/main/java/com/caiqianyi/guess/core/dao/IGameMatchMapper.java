package com.caiqianyi.guess.core.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.caiqianyi.commons.pager.Pager;
import com.caiqianyi.guess.game.entity.GameMatch;

@Mapper
public interface IGameMatchMapper {

	GameMatch findOneBy(@Param("match") GameMatch match);

	List<GameMatch> findAllBy(@Param("match")GameMatch match,
			@Param("start") Date start, @Param("end") Date end);
	
	List<GameMatch> findAllByTopicStatus(@Param("status")Integer topicStatus);
	
	List<GameMatch> findByForPager(@Param("match")GameMatch match,
			@Param("start") Date start, @Param("end") Date end, Pager pager);

	int insert(@Param("match")GameMatch match);

	int update(@Param("match")GameMatch match);

	int delete(@Param("id") String id);

	int deleteByMatchId(@Param("gameType") String gameType,
			@Param("matchId") Integer matchId);
}
