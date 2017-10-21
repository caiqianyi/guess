package com.caiqianyi.agent.account.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.caiqianyi.agent.account.service.IGuessTopicService;
import com.caiqianyi.commons.utils.OddsMath;
import com.caiqianyi.guess.core.dao.IGuessTopicMapper;
import com.caiqianyi.guess.entity.GuessTopic;
import com.caiqianyi.guess.entity.GuessTopicOption;
import com.caiqianyi.soa.web.framework.model.Pager;

@Service
public class GuessTopicServiceImpl implements IGuessTopicService {
	
	private static Logger logger = LoggerFactory.getLogger(GuessTopicServiceImpl.class);

	@Value("{guess.bonus.ratio}")
	private String bonusRatio;
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
	@Transactional(readOnly=false,timeout=10,propagation=Propagation.REQUIRED)
	public boolean insert(GuessTopic topic) {
		// TODO Auto-generated method stub
		return guessTopicMapper.insert(topic)>0;
	}

	@Override
	@Transactional(readOnly=false,timeout=10,propagation=Propagation.REQUIRED)
	public boolean update(GuessTopic topic) {
		// TODO Auto-generated method stub
		return guessTopicMapper.update(topic)>0;
	}

	@Override
	@Transactional(readOnly=false,timeout=10,propagation=Propagation.REQUIRED)
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
	@Transactional(readOnly=false,timeout=10,propagation=Propagation.REQUIRED)
	public boolean insertOption(GuessTopicOption option) {
		// TODO Auto-generated method stub
		return guessTopicMapper.insertOption(option)>0;
	}

	@Override
	@Transactional(readOnly=false,timeout=10,propagation=Propagation.REQUIRED)
	public boolean updateOption(GuessTopicOption option) {
		// TODO Auto-generated method stub
		return guessTopicMapper.updateOption(option)>0;
	}

	@Override
	@Transactional(readOnly=false,timeout=10,propagation=Propagation.REQUIRED)
	public boolean deleteOption(String id) {
		// TODO Auto-generated method stub
		return guessTopicMapper.deleteOption(id)>0;
	}

	@Override
	@Transactional(readOnly=false,timeout=10,propagation=Propagation.REQUIRED)
	public boolean deleteOptionByTopicId(Integer topicId) {
		// TODO Auto-generated method stub
		return guessTopicMapper.deleteOptionByTopicId(topicId)>0;
	}
	
	@Override
	public BigDecimal[] calOdds(Integer topicId) {
		List<GuessTopicOption> options = this.findGuessTopicOptionByTopicId(topicId);
		if(options != null){
			BigDecimal bas[] = new BigDecimal[options.size()];
			for(int i=0;i<bas.length;i++){
				GuessTopicOption gto = options.get(i);
				bas[i] = gto.getBuyAmount(); 
			}
			BigDecimal odds[] = OddsMath.calOdds(bonusRatio, bas);
			for(int i=0;i<odds.length;i++){
				GuessTopicOption gto = options.get(i);
				bas[i] = gto.getBuyAmount() == null ? new BigDecimal("1.00") : gto.getBuyAmount();
				GuessTopicOption option = new GuessTopicOption();
				option.setId(gto.getId());
				option.setOdds(odds[i]);
				guessTopicMapper.updateOption(option);
			}
		}
		return null;
	}
	
}
