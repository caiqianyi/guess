package com.caiqianyi.guess.game.service.hystrix;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.caiqianyi.commons.exception.I18nMessageException;
import com.caiqianyi.commons.exception.SuccessMessage;
import com.caiqianyi.commons.pager.Pager;
import com.caiqianyi.guess.game.entity.GameMatch;
import com.caiqianyi.guess.game.service.IGameMatchService;

@Component
public class GameMatchServiceHystrix implements IGameMatchService {
	
	private Logger logger = LoggerFactory.getLogger(GameMatchServiceHystrix.class);

	@Override
	public GameMatch findByMatchId(String gameType, Integer matchId) {
		// TODO Auto-generated method stub
		throw new I18nMessageException("502","服务器异常，请稍后重试");
	}

	@Override
	public GameMatch findById(String id) {
		// TODO Auto-generated method stub
		throw new I18nMessageException("502","服务器异常，请稍后重试");
	}

	@Override
	public Pager findByForPager(GameMatch match, Date start, Date end,
			Pager pager) {
		// TODO Auto-generated method stub
		throw new I18nMessageException("502","服务器异常，请稍后重试");
	}

	@Override
	public SuccessMessage insert(GameMatch match) {
		// TODO Auto-generated method stub
		throw new I18nMessageException("502","服务器异常，请稍后重试");
	}

	@Override
	public SuccessMessage update(GameMatch match) {
		// TODO Auto-generated method stub
		throw new I18nMessageException("502","服务器异常，请稍后重试");
	}

	@Override
	public SuccessMessage delete(String id) {
		// TODO Auto-generated method stub
		throw new I18nMessageException("502","服务器异常，请稍后重试");
	}

	@Override
	public SuccessMessage deleteByMatchId(String gameType, Integer matchId) {
		// TODO Auto-generated method stub
		throw new I18nMessageException("502","服务器异常，请稍后重试");
	}


}
