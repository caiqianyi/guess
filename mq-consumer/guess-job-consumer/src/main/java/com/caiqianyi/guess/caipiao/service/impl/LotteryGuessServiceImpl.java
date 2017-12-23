package com.caiqianyi.guess.caipiao.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.caiqianyi.commons.utils.FormulaCalculate;
import com.caiqianyi.commons.utils.GenerateCode;
import com.caiqianyi.guess.caipiao.entity.LotteryIssue;
import com.caiqianyi.guess.caipiao.service.ILotteryGuessService;
import com.caiqianyi.guess.core.dao.IGuessClubMapper;
import com.caiqianyi.guess.core.dao.IGuessClubMemberMapper;
import com.caiqianyi.guess.core.dao.IGuessOrderMapper;
import com.caiqianyi.guess.core.dao.IGuessTopicMapper;
import com.caiqianyi.guess.entity.GuessClub;
import com.caiqianyi.guess.entity.GuessOrder;
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
	
	@Resource
	private IGuessClubMapper guessClubMapper;
	
	@Resource
	private IGuessOrderMapper guessOrderMapper;
	
	@Resource
	private IGuessClubMemberMapper guessClubMemberMapper;
	
	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRES_NEW)
	public List<GuessTopic> createTopicByIssueForClub(LotteryIssue issue) {
		// TODO Auto-generated method stub
		List<GuessClub> clubs = guessClubMapper.findByKindOf(issue.getKindOf(), 10);
		List<GuessTopic> topics = new ArrayList<GuessTopic>();
		if(clubs != null && !clubs.isEmpty()){
			for(GuessClub club : clubs){
				club.setCardNum(club.getCardNum() - 10);
				guessClubMapper.update(club);
				List<GuessTemplate> templates = club.getTemplates();
				if(templates != null && !templates.isEmpty()){
					topics.addAll(createGuessTopic(issue.getExpect(), null, issue.getStartTime(), issue.getEndTime(), templates));
				}
			}
		}
		return topics;
	}
	
	private List<GuessTopic> createGuessTopic(String groupId,
			String league,Date startTime, Date overTime, List<GuessTemplate> templates) {
		// TODO Auto-generated method stub
		if(templates != null){
			List<GuessTopic> topics = new ArrayList<GuessTopic>();
			for(int i=0;i<templates.size();i++){
				GuessTemplate temp = templates.get(i);
				Integer topicId = GenerateCode.gen(9);
				GuessTopic topic = new GuessTopic();
				topic.setCreateTime(startTime);
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
	public List<GuessTopic> createGuessTopic(String groupId,
			String league, Date overTime, List<GuessTemplate> templates) {
		return createGuessTopic(groupId, league, null, overTime, templates);
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
		List<GuessOrder> orders = guessOrderMapper.findAlByKindOfAndExpect(kind, groupId);
		if(topics != null){
			List<Integer> memberIds = new ArrayList<Integer>();
			for(int i=0;i<topics.size();i++){
				GuessTopic topic = topics.get(i);
				GuessTopicOption selectOption = null;
				for(int k=0;k<topic.getOptions().size();k++){
					GuessTopicOption option = topic.getOptions().get(k);
					boolean result = FormulaCalculate.check(lotterys, option.getValue());
					if(result){
						selectOption = option;
						option.setIsSelected(true);
						guessTopicMapper.updateOption(option);
					}
				}
				if(orders != null && !orders.isEmpty()){
					for(GuessOrder order : orders){
						if(order.getTopicId().equals(topic.getTopicId())){
							Integer score = 0,status = -1;
							if(order.getOptionId().equals(selectOption.getId())){
								memberIds.add(order.getMemberId());
								score = Integer.parseInt(selectOption.getOdds().multiply(new BigDecimal(order.getAmount()*100)).setScale(2,BigDecimal.ROUND_HALF_DOWN).toString());
								status = 1;
							}else{
								
							}
							order.setScore(score);
							order.setStatus(status);
							guessOrderMapper.update(order);
						}
					}
				}
				topic.setStatus(2);
				topic.setOptionId(selectOption.getId());
				guessTopicMapper.update(topic);
			}
			guessClubMemberMapper.addWinCount(memberIds.toArray(new Integer[]{}));
		}
		return topics;
	}

}
