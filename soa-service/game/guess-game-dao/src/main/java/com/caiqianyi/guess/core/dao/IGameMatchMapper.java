package com.caiqianyi.guess.core.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.caiqianyi.commons.pager.Pager;
import com.caiqianyi.guess.game.entity.GameMatch;

@Mapper
public interface IGameMatchMapper {

	GameMatch findByMatchId(@Param("gameType") String gameType,
			@Param("matchId") Integer matchId);

	GameMatch findById(@Param("id") String id);

	List<GameMatch> findByGameTypeForPager(GameMatch match,
			@Param("start") Date start, @Param("end") Date end, Pager pager);

	int insert(GameMatch match);

	int update(GameMatch match);

	int delete(@Param("id") String id);

	int deleteByMatchId(@Param("gameType") String gameType,
			@Param("matchId") Integer matchId);
}
