package com.lebaoxun.guess.caipiao.match.game.spider;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.lebaoxun.guess.game.entity.GameMatch;
import com.lebaoxun.guess.game.lol.vo.BattleData;
import com.lebaoxun.guess.game.lol.vo.LoLSMatch;

public interface ILoLMatchNetworkSpider {

	Map<String,String> getGuessResultByMatch(GameMatch match,String matchId);
	
	List<GameMatch> findByLeagueAndTime(String league, String season, Integer pageNum,
			Integer size, Date start, Date end);
	
	GameMatch findByMatchId(String matchId);
	
	List<LoLSMatch> findSMatchByMatchId(String matchId);
	
	BattleData findMatchInfoBySMatchId(String sMatchId);

}
