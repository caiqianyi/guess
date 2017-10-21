package com.caiqianyi.guess.match.game.spider;

import java.util.Date;
import java.util.List;

import com.caiqianyi.guess.game.entity.GameMatch;
import com.caiqianyi.guess.game.lol.vo.BattleData;
import com.caiqianyi.guess.game.lol.vo.LoLSMatch;

public interface ILoLMatchNetworkSpider {

	List<GameMatch> findByLeagueAndTime(String league, String season, Integer pageNum,
			Integer size, Date start, Date end);
	
	GameMatch findByMatchId(String matchId);
	
	List<LoLSMatch> findSMatchByMatchId(String matchId);
	
	BattleData findMatchInfoBySMatchId(String sMatchId);

}
