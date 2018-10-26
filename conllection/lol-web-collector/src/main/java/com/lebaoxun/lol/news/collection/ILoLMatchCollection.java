package com.lebaoxun.lol.news.collection;

import java.util.Date;
import java.util.List;

import com.lebaoxun.lol.entity.LoLMatch;

public interface ILoLMatchCollection {
	/**
	 * 获取LOL 赛区时间内赛程
	 * @param league 
	 * @param season
	 * @param pageNum
	 * @param size
	 * @param start
	 * @param end
	 * @return
	 */
	List<LoLMatch> findMatchByLeague(String league, String season, Integer pageNum,
			Integer size, Date start, Date end);
	
	LoLMatch findByMatchId(String matchId);
	
	List<LoLMatch> findSMatchByMatchId(String matchId);
	
	LoLMatch findMatchInfoBySMatchId(String sMatchId);
}
