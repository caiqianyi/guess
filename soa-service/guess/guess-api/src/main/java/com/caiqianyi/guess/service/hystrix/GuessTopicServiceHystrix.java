package com.caiqianyi.guess.service.hystrix;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.caiqianyi.commons.exception.SuccessMessage;
import com.caiqianyi.commons.pager.Pager;
import com.caiqianyi.guess.entity.GuessTopic;
import com.caiqianyi.guess.entity.GuessTopicOption;
import com.caiqianyi.guess.service.IGuessTopicService;

@Component
public class GuessTopicServiceHystrix implements IGuessTopicService {
	
	private Logger logger = LoggerFactory.getLogger(GuessTopicServiceHystrix.class);

	@Override
	public GuessTopic findOneGuessTopicByTopicId(Integer topicId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GuessTopic findOneGuessTopicByOptionId(String optionId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Pager findGuessTopicByRoomIdForPager(Integer roomId, Integer status,
			Date start, Date end, Pager pager) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Pager findGuessTopicByForPager(String kind, String league,
			String groupId, Integer status, Integer orderBy, Date start,
			Date end, Pager pager) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SuccessMessage insert(GuessTopic topic) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SuccessMessage update(GuessTopic topic) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SuccessMessage delete(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<GuessTopicOption> findGuessTopicOptionByTopicId(Integer topicId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SuccessMessage insertOption(GuessTopicOption option) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SuccessMessage updateOption(GuessTopicOption option) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SuccessMessage deleteOption(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SuccessMessage deleteOptionByTopicId(Integer topicId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SuccessMessage calOdds(Integer topicId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SuccessMessage finishGuess() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public SuccessMessage syncTopicOption(String kind) {
		// TODO Auto-generated method stub
		return null;
	}
}
