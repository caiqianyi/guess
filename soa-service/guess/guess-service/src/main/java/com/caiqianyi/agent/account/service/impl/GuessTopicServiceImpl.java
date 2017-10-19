package com.caiqianyi.agent.account.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.caiqianyi.agent.account.service.IGuessTopicService;
import com.caiqianyi.guess.core.dao.IGuessTopicMapper;
import com.caiqianyi.guess.entity.GuessTopic;
import com.caiqianyi.guess.entity.GuessTopicOption;
import com.caiqianyi.soa.web.framework.model.Pager;

@Service
public class GuessTopicServiceImpl implements IGuessTopicService {

	@Resource
	private IGuessTopicMapper guessTopicMapper;

	@Override
	public GuessTopic findOneGuessTopicByTopicId(Integer topicId) {
		// TODO Auto-generated method stub
		return guessTopicMapper.findOneGuessTopicByTopicId(topicId);
	}

	@Override
	public GuessTopic findOneGuessTopicByOptionId(String optionId) {
		// TODO Auto-generated method stub
		return guessTopicMapper.findOneGuessTopicByOptionId(optionId);
	}

	@Override
	public Pager findGuessTopicByRoomIdForPager(Integer roomId, Integer status,
			Date start, Date end, Pager pager) {
		// TODO Auto-generated method stub
		List<GuessTopic> datas = guessTopicMapper
				.findGuessTopicByRoomIdForPager(roomId, status, start, end,
						pager);
		pager.setDatas(datas);
		return pager;
	}

	@Override
	public Pager findGuessTopicByForPager(String kind, String league,
			String groupId, Integer status, Date start, Date end, Pager pager) {
		// TODO Auto-generated method stub
		List<GuessTopic> datas = guessTopicMapper.findGuessTopicByForPager(
				kind, league, groupId, status, start, end, pager);
		pager.setDatas(datas);
		return pager;
	}

	@Override
	public boolean insert(GuessTopic topic) {
		// TODO Auto-generated method stub
		return guessTopicMapper.insert(topic)>0;
	}

	@Override
	public boolean update(GuessTopic topic) {
		// TODO Auto-generated method stub
		return guessTopicMapper.update(topic)>0;
	}

	@Override
	public boolean delete(String id) {
		// TODO Auto-generated method stub
		return guessTopicMapper.delete(id)>0;
	}

	@Override
	public List<GuessTopicOption> findGuessTopicOptionByTopicId(Integer topicId) {
		// TODO Auto-generated method stub
		return guessTopicMapper.findGuessTopicOptionByTopicId(topicId);
	}

	@Override
	public boolean insertOption(GuessTopicOption option) {
		// TODO Auto-generated method stub
		return guessTopicMapper.insertOption(option)>0;
	}

	@Override
	public boolean updateOption(GuessTopicOption option) {
		// TODO Auto-generated method stub
		return guessTopicMapper.updateOption(option)>0;
	}

	@Override
	public boolean deleteOption(String id) {
		// TODO Auto-generated method stub
		return guessTopicMapper.deleteOption(id)>0;
	}

	@Override
	public boolean deleteOptionByTopicId(Integer topicId) {
		// TODO Auto-generated method stub
		return guessTopicMapper.deleteOptionByTopicId(topicId)>0;
	}

}
