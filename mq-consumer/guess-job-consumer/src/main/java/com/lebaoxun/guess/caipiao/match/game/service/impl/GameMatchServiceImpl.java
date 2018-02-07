package com.lebaoxun.guess.caipiao.match.game.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lebaoxun.commons.pager.Pager;
import com.lebaoxun.guess.caipiao.match.game.service.IGameMatchService;
import com.lebaoxun.guess.core.dao.IGameMatchMapper;
import com.lebaoxun.guess.game.entity.GameMatch;

@Service
public class GameMatchServiceImpl implements IGameMatchService {

	@Resource
	private IGameMatchMapper gameMatchMapper;
	
	@Override
	public GameMatch findByMatchId(String gameType, Integer matchId) {
		// TODO Auto-generated method stub
		
		GameMatch match = new GameMatch();
		match.setGameType(gameType);
		match.setMatchId(matchId);
		return gameMatchMapper.findOneBy(match);
	}

	@Override
	public GameMatch findById(String id) {
		// TODO Auto-generated method stub
		GameMatch match = new GameMatch();
		match.setId(id);
		return gameMatchMapper.findOneBy(match);
	}

	@Override
	public Pager findByGameTypeForPager(GameMatch match, Date start,
			Date end, Pager pager) {
		// TODO Auto-generated method stub
		List<GameMatch> datas = gameMatchMapper.findByForPager(match, start, end, pager);
		pager.setDatas(datas);
		return pager;
	}

	@Override
	public int insert(GameMatch match) {
		// TODO Auto-generated method stub
		return gameMatchMapper.insert(match);
	}

	@Override
	public int update(GameMatch match) {
		// TODO Auto-generated method stub
		return gameMatchMapper.update(match);
	}

	@Override
	public int delete(String id) {
		// TODO Auto-generated method stub
		return gameMatchMapper.delete(id);
	}

	@Override
	public int deleteByMatchId(String gameType, Integer matchId) {
		// TODO Auto-generated method stub
		return gameMatchMapper.deleteByMatchId(gameType, matchId);
	}

}
