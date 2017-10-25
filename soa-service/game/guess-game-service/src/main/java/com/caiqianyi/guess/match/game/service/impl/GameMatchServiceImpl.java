package com.caiqianyi.guess.match.game.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.caiqianyi.commons.pager.Pager;
import com.caiqianyi.guess.core.dao.IGameMatchMapper;
import com.caiqianyi.guess.game.entity.GameMatch;
import com.caiqianyi.guess.match.game.service.IGameMatchService;

@Service
public class GameMatchServiceImpl implements IGameMatchService {

	@Resource
	private IGameMatchMapper gameMatchMapper;
	
	@Override
	public GameMatch findByMatchId(String gameType, Integer matchId) {
		// TODO Auto-generated method stub
		return gameMatchMapper.findByMatchId(gameType, matchId);
	}

	@Override
	public GameMatch findById(String id) {
		// TODO Auto-generated method stub
		return gameMatchMapper.findById(id);
	}

	@Override
	public List<GameMatch> findByGameTypeForPager(GameMatch match, Date start,
			Date end, Pager pager) {
		// TODO Auto-generated method stub
		return gameMatchMapper.findByGameTypeForPager(match, start, end, pager);
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
