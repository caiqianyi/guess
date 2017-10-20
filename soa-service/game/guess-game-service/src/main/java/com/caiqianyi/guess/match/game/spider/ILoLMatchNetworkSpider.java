package com.caiqianyi.guess.match.game.spider;

import java.util.Date;
import java.util.List;

import com.caiqianyi.guess.game.entity.GameMatch;

public interface ILoLMatchNetworkSpider {

	List<GameMatch> findByLeagueAndTime(String league, String season, Integer pageNum,
			Integer size, Date start, Date end);
	
	

}
