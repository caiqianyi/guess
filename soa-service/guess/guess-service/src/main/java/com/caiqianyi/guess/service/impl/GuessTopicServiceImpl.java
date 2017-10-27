package com.caiqianyi.guess.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.caiqianyi.commons.beans.SpringConfigTool;
import com.caiqianyi.commons.pager.Pager;
import com.caiqianyi.commons.utils.OddsMath;
import com.caiqianyi.guess.core.dao.IGuessTopicMapper;
import com.caiqianyi.guess.entity.GuessTopic;
import com.caiqianyi.guess.entity.GuessTopicOption;
import com.caiqianyi.guess.service.IGuessTopicCalService;
import com.caiqianyi.guess.service.IGuessTopicService;

@Service
public class GuessTopicServiceImpl implements IGuessTopicService {
	
	private static Logger logger = LoggerFactory.getLogger(GuessTopicServiceImpl.class);

	@Value("${guess.bonus.ratio}")
	private String bonusRatio;
	@Resource
	private IGuessTopicMapper guessTopicMapper;

	@Override
	public GuessTopic findOneGuessTopicByTopicId(Integer topicId) {
		// TODO Auto-generated method stub
		GuessTopic topicParam = new GuessTopic();
		topicParam.setTopicId(topicId);
		return guessTopicMapper.findOneGuessTopicBy(topicParam);
	}

	@Override
	public GuessTopic findOneGuessTopicByOptionId(String optionId) {
		GuessTopic topicParam = new GuessTopic();
		topicParam.setOptionId(optionId);
		// TODO Auto-generated method stub
		return guessTopicMapper.findOneGuessTopicBy(topicParam);
	}

	@Override
	public Pager findGuessTopicByRoomIdForPager(Integer roomId, Integer status,
			Date start, Date end, Pager pager) {
		
		GuessTopic topicParam = new GuessTopic();
		topicParam.setRoomId(roomId);
		topicParam.setStatus(status);
		
		// TODO Auto-generated method stub
		List<GuessTopic> datas = guessTopicMapper.findGuessTopicByForPager(topicParam, start, end, pager);
		pager.setDatas(datas);
		return pager;
	}

	@Override
	public Pager findGuessTopicByForPager(String kind, String league,
			String groupId, Integer status, Integer orderBy,
			Date start, Date end, Pager pager) {
		
		GuessTopic topicParam = new GuessTopic();
		topicParam.setKind(kind);
		topicParam.setLeague(league);
		topicParam.setGroupId(groupId);
		topicParam.setStatus(status);
		topicParam.setOrderBy(orderBy);
		
		// TODO Auto-generated method stub
		List<GuessTopic> datas = guessTopicMapper.findGuessTopicByForPager(topicParam, start, end, pager);
		pager.setDatas(datas);
		return pager;
	}
	
	@Override
	public List<GuessTopic> findGuessTopicBy(String kind, String league, String groupId,
			Integer status, Integer orderBy, Date start, Date end) {
		
		GuessTopic topicParam = new GuessTopic();
		topicParam.setKind(kind);
		topicParam.setLeague(league);
		topicParam.setGroupId(groupId);
		topicParam.setStatus(status);
		topicParam.setOrderBy(orderBy);
		// TODO Auto-generated method stub
		return guessTopicMapper.findAllGuessTopicBy(topicParam, start, end);
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
		GuessTopicOption paramOption = new GuessTopicOption();
		paramOption.setTopicId(topicId);
		return guessTopicMapper.findAllGuessTopicOptionBy(paramOption);
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
	@Transactional(readOnly=false,timeout=10,propagation=Propagation.REQUIRED)
	public void calOdds(){
		GuessTopic topicParam = new GuessTopic();
		topicParam.setStatus(0);
		List<GuessTopic> guessing = guessTopicMapper.findAllGuessTopicBy(topicParam, null, null);
		for(GuessTopic topic : guessing){
			calOdds(topic.getTopicId());
		}
	}
	
	@Override
	@Transactional(readOnly=false,timeout=10,propagation=Propagation.REQUIRED)
	public BigDecimal[] calOdds(Integer topicId) {
		List<GuessTopicOption> options = this.findGuessTopicOptionByTopicId(topicId);
		if(options != null){
			BigDecimal bas[] = new BigDecimal[options.size()];
			for(int i=0;i<bas.length;i++){
				GuessTopicOption gto = options.get(i);
				bas[i] = gto.getBuyAmount(); 
			}
			logger.debug("bonusRatio={}",bonusRatio);
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
	
	@Override
	@Transactional(readOnly=false,timeout=10,propagation=Propagation.REQUIRED)
	public int finishGuess() {
		// TODO Auto-generated method stub
		/*
		 * 查询进行中竞猜
		 */
		int execute = 0;
		GuessTopic topicParam = new GuessTopic();
		topicParam.setStatus(0);
		List<GuessTopic> guessing = guessTopicMapper.findAllGuessTopicBy(topicParam, null, null);
		for(GuessTopic topic : guessing){
			boolean isOver = topic.getOverTime().after(new Date());
			if(isOver){
				topic.setStatus(1);
				execute += guessTopicMapper.update(topic);
			}
		}
		return execute;
	}
	
	@Override
	@Transactional(readOnly=false,timeout=10,propagation=Propagation.REQUIRED)
	public void syncTopicOption(String kind) {
		// TODO Auto-generated method stub
		/*
		 * 查询为开奖的比赛
		 */
		GuessTopic topicParam = new GuessTopic();
		topicParam.setStatus(0);
		topicParam.setKind(kind);
		List<GuessTopic> guessing = guessTopicMapper.findAllGuessTopicBy(topicParam, null, null);
		for(GuessTopic topic : guessing){
			String beanName = kind+"TopicCalService";
			IGuessTopicCalService guessTopicCalService = (IGuessTopicCalService) SpringConfigTool.getBean(beanName);
			if(guessTopicCalService != null){
				Integer topicId = topic.getTopicId();
				String optionId = guessTopicCalService.calResult(topicId);
				if(StringUtils.isNotBlank(optionId)){
					GuessTopicOption paramOption = new GuessTopicOption();
					paramOption.setTopicId(topicId);
					paramOption.setId(optionId);
					GuessTopicOption option = guessTopicMapper.findOneGuessTopicOptionBy(paramOption);
					if(option != null){
						GuessTopicOption gtp = new GuessTopicOption();
						gtp.setId(option.getId());
						gtp.setIsSelected(true);//修改为中奖项
						guessTopicMapper.updateOption(option);
						
						GuessTopic gt = new GuessTopic();
						gt.setId(topic.getId());
						gt.setTopicId(topic.getTopicId());
						gt.setOptionId(optionId);
						gt.setStatus(2);
						
						guessTopicMapper.update(gt);
					}
				}
			}
			
		}
	}
}
