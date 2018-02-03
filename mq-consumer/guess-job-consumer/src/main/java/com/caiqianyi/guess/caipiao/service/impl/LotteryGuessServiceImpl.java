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
import com.caiqianyi.guess.caipiao.service.ILotteryGuessService;
import com.caiqianyi.guess.core.dao.IGuessClubLogMapper;
import com.caiqianyi.guess.core.dao.IGuessClubMapper;
import com.caiqianyi.guess.core.dao.IGuessClubMemberMapper;
import com.caiqianyi.guess.core.dao.IGuessOrderMapper;
import com.caiqianyi.guess.core.dao.IGuessTopicMapper;
import com.caiqianyi.guess.entity.GuessClub;
import com.caiqianyi.guess.entity.GuessClubLog;
import com.caiqianyi.guess.entity.GuessOrder;
import com.caiqianyi.guess.entity.GuessTemplate;
import com.caiqianyi.guess.entity.GuessTemplateOption;
import com.caiqianyi.guess.entity.GuessTopic;
import com.caiqianyi.guess.entity.GuessTopicOption;
import com.caiqianyi.soa.core.redis.IRedisCache;
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
	
	@Resource
	private IGuessClubLogMapper guessClubLogMapper;
	
	@Resource
	private IRedisCache redisCache;
	
	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRES_NEW)
	public List<GuessTopic> createTopicByIssueForClub(
			String seq,int number,String kindOf,String groupId,Date start,Date end) {
		// TODO Auto-generated method stub
		List<GuessTopic> topics = new ArrayList<GuessTopic>();
		int count = guessClubLogMapper.count(null, seq);
		if(count == 0){
			List<GuessClub> clubs = guessClubMapper.findByKindOf(kindOf, number);
			if(clubs != null && !clubs.isEmpty()){
				for(GuessClub club : clubs){
					List<GuessTemplate> templates = club.getTemplates();
					logger.debug("templates.size={}",templates.size());
					if(templates != null && !templates.isEmpty()){
						GuessClubLog log = new GuessClubLog();
						log.setCardNum(number);
						log.setClubId(club.getClubId());
						log.setDescr("创建话题扣卡");
						log.setTradeType(0);
						log.setSeq(seq);
						
						club.setCardNum(club.getCardNum() - number);
						
						guessClubLogMapper.writerLog(log);
						guessClubMapper.update(club);
						
						String key = "guess:club:info:"+club.getCreateId()+":"+club.getClubId();
						GuessClub gc = (GuessClub)redisCache.getSys(key);
						if(gc != null){
							gc.setCardNum(club.getCardNum());
							redisCache.setSys(key, gc);
						}
						topics.addAll(createGuessTopic(groupId, null, start, end, templates));
					}
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
				
				logger.debug("template={}",new Gson().toJson(temp));
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
					option.setOdds(gto.getOdds());
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
							if(selectOption != null && order.getOptionId().equals(selectOption.getId())){
								memberIds.add(order.getMemberId());
								BigDecimal odds = selectOption.getOdds();
								if(odds == null){
									odds = new BigDecimal(1);
								}
								score = odds.multiply(new BigDecimal(order.getAmount()*100)).setScale(2,BigDecimal.ROUND_HALF_DOWN).intValue();
								status = 1;
							}else{
								
							}
							order.setScore(score);
							order.setStatus(status);
							guessOrderMapper.update(order);
						}
					}
					if(selectOption != null){
						topic.setOptionId(selectOption.getId());
					}
				}
				topic.setStatus(2);
				guessTopicMapper.update(topic);
			}
			if(!memberIds.isEmpty())
				guessClubMemberMapper.addWinCount(memberIds);
		}
		return topics;
	}

}
