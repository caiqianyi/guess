package com.caiqianyi.guess.game.service.hystrix;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.caiqianyi.commons.exception.I18nMessageException;
import com.caiqianyi.commons.exception.SuccessMessage;
import com.caiqianyi.guess.game.entity.GameMatch;
import com.caiqianyi.guess.game.service.ILolGuessTopicService;

@Component
public class LolGuessTopicServiceHystrix implements ILolGuessTopicService {
	
	private Logger logger = LoggerFactory.getLogger(LolGuessTopicServiceHystrix.class);

	@Override
	public SuccessMessage announceResults() {
		// TODO Auto-generated method stub
		throw new I18nMessageException("502","服务器异常，请稍后重试");
	}
	@Override
	public SuccessMessage announceResults(String matchId) {
		// TODO Auto-generated method stub
		throw new I18nMessageException("502","服务器异常，请稍后重试");
	}

	@Override
	public SuccessMessage updatedLoLTopic(String startDate) {
		// TODO Auto-generated method stub
		throw new I18nMessageException("502","服务器异常，请稍后重试");
	}

	@Override
	public SuccessMessage createAll(GameMatch match, String createBy,
			Integer clubId) {
		// TODO Auto-generated method stub
		throw new I18nMessageException("502","服务器异常，请稍后重试");
	}

	@Override
	public SuccessMessage createTopicForSF(GameMatch match, String createBy,
			Integer clubId) {
		// TODO Auto-generated method stub
		throw new I18nMessageException("502","服务器异常，请稍后重试");
	}

	@Override
	public SuccessMessage createTopicForBF(GameMatch match, String createBy,
			Integer clubId) {
		// TODO Auto-generated method stub
		throw new I18nMessageException("502","服务器异常，请稍后重试");
	}

	@Override
	public SuccessMessage createTopicForFirst(GameMatch match, String createBy,
			Integer clubId) {
		// TODO Auto-generated method stub
		throw new I18nMessageException("502","服务器异常，请稍后重试");
	}

	@Override
	public SuccessMessage createTopicForFirstBlood(GameMatch match,
			String createBy, Integer clubId) {
		// TODO Auto-generated method stub
		throw new I18nMessageException("502","服务器异常，请稍后重试");
	}

	@Override
	public SuccessMessage createTopicForFirstTurret(GameMatch match,
			String createBy, Integer clubId) {
		// TODO Auto-generated method stub
		throw new I18nMessageException("502","服务器异常，请稍后重试");
	}

	@Override
	public SuccessMessage createTopicForSOD(GameMatch match, String createBy,
			Integer clubId) {
		// TODO Auto-generated method stub
		throw new I18nMessageException("502","服务器异常，请稍后重试");
	}



}
