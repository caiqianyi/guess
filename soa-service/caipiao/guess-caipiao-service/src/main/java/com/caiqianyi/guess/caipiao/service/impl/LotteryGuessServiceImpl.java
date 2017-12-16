package com.caiqianyi.guess.caipiao.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.caiqianyi.commons.utils.GenerateCode;
import com.caiqianyi.guess.caipiao.data.analysis.LotteryGuessTopicCreator;
import com.caiqianyi.guess.caipiao.service.ILotteryGuessService;
import com.caiqianyi.guess.core.dao.IGuessTopicMapper;
import com.caiqianyi.guess.entity.GuessTemplate;
import com.caiqianyi.guess.entity.GuessTemplateOption;
import com.caiqianyi.guess.entity.GuessTopic;
import com.caiqianyi.guess.entity.GuessTopicOption;
import com.google.gson.Gson;

@Service
public class LotteryGuessServiceImpl implements ILotteryGuessService {

	private Logger logger = LoggerFactory.getLogger(LotteryGuessServiceImpl.class);
	
	@Resource
	private IGuessTopicMapper guessTopicMapper;
	
	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRES_NEW)
	public List<GuessTopic> createGuessTopic(String groupId,
			String league, Date overTime, List<GuessTemplate> templates) {
		// TODO Auto-generated method stub
		if(templates != null){
			List<GuessTopic> topics = new ArrayList<GuessTopic>();
			for(int i=0;i<templates.size();i++){
				GuessTemplate temp = templates.get(i);
				Integer topicId = GenerateCode.gen(9);
				GuessTopic topic = new GuessTopic();
				topic.setOverTime(overTime);
				topic.setGroupId(groupId);
				topic.setKind(temp.getKindOf());
				topic.setLabel(temp.getLabel());
				topic.setLeague(league);
				topic.setOrderBy(temp.getOrderBy());
				topic.setClubId(temp.getClubId());
				topic.setSubject(temp.getSubject());
				topic.setTopicId(topicId);
				topic.setTopicType(temp.getTopicType());
				
				List<GuessTemplateOption> tempOpts = temp.getOptions();
				List<GuessTopicOption> options = new ArrayList<GuessTopicOption>();
				for(int k=0;k<tempOpts.size();k++){
					GuessTemplateOption gto = tempOpts.get(k);
					GuessTopicOption option = new GuessTopicOption();
					option.setName(gto.getName());
					option.setOrderBy(gto.getOrderBy());
					option.setTopicId(topicId);
					option.setValue(gto.getFormula());
					options.add(option);
				}
				topic.setOptions(options);
				GuessTopic queryTopic = new GuessTopic();
				queryTopic.setKind(topic.getKind());
				queryTopic.setTopicType(topic.getTopicType());
				queryTopic.setGroupId(topic.getGroupId());
				queryTopic.setOrderBy(topic.getOrderBy());
				if(guessTopicMapper != null){
					if(guessTopicMapper.findOneGuessTopicBy(queryTopic) == null){
						guessTopicMapper.insert(topic);
						for(GuessTopicOption option : options){
							guessTopicMapper.insertOption(option);
						}
						topics.add(topic);
					}
				}
			}
			logger.debug("topics={}",new Gson().toJson(topics));
			return topics;
		}
		return null;
	}

	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRES_NEW)
	public List<GuessTopic> updateTopicResult(String kind, String groupId,
			String[] lotterys) {
		// TODO Auto-generated method stub
		GuessTopic queryTopic = new GuessTopic();
		queryTopic.setKind(kind);
		queryTopic.setGroupId(groupId);
		List<GuessTopic> topics = guessTopicMapper.findAllGuessTopicLeftOptionsBy(queryTopic, null, null);
		if(topics != null){
			for(int i=0;i<topics.size();i++){
				GuessTopic topic = topics.get(i);
				String optionId = null;
				for(int k=0;k<topic.getOptions().size();k++){
					GuessTopicOption option = topic.getOptions().get(k);
					boolean result = LotteryGuessTopicCreator.check(lotterys, option.getValue());
					if(result){
						optionId = option.getId();
						option.setIsSelected(true);
						guessTopicMapper.updateOption(option);
					}
				}
				topic.setStatus(2);
				topic.setOptionId(optionId);
				guessTopicMapper.update(topic);
			}
		}
		return topics;
	}

}
