package com.lebaoxun.guess.caipiao.match.game.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;
import com.lebaoxun.commons.utils.GenerateCode;
import com.lebaoxun.commons.utils.OddsMath;
import com.lebaoxun.guess.caipiao.match.game.service.ILoLGuessTopicService;
import com.lebaoxun.guess.caipiao.match.game.spider.ILoLMatchNetworkSpider;
import com.lebaoxun.guess.core.dao.IGameMatchMapper;
import com.lebaoxun.guess.core.dao.IGuessTopicMapper;
import com.lebaoxun.guess.entity.GuessTopic;
import com.lebaoxun.guess.entity.GuessTopicOption;
import com.lebaoxun.guess.game.entity.GameMatch;

@Service
public class LoLGuessTopicServiceImpl implements ILoLGuessTopicService {
	
	private Logger logger = LoggerFactory.getLogger(LoLGuessTopicServiceImpl.class);
	
	@Resource
	private IGameMatchMapper gameMatchMapper;
	
	@Resource
	private IGuessTopicMapper guessTopicMapper;
	
	@Resource
	private ILoLMatchNetworkSpider lolMatchNetworkSpider;
	
	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED)
	public List<GuessTopicOption> announceResults() {
		// TODO Auto-generated method stub
		
		//查询进行中比赛
		List<GameMatch> gameMatchs =gameMatchMapper.findAllByTopicStatus(1);
		for(GameMatch match : gameMatchs){
			if(match.getStatus() == 3){
				String matchId = ""+match.getMatchId();
				this.announceResults(matchId);
			}
		}
		return null;
	}
	
	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED)
	public List<GuessTopicOption> announceResults(String matchId) {
		// TODO Auto-generated method stub
		
		/**
		 * 1、
		 */
		GameMatch match = lolMatchNetworkSpider.findByMatchId(matchId);
		List<GuessTopicOption> options = null;
		if(match != null && match.getStatus() == 3){
			options = new ArrayList<GuessTopicOption>();
			Map<String,String> map = lolMatchNetworkSpider.getGuessResultByMatch(match,matchId);
			String groupId = "GM"+DateFormatUtils.format(match.getMatchTime(), "yyyy")+match.getMatchId();
			
			for(String topicType : map.keySet()){
				
				if("lolMatch".equals(topicType) ){
					continue;
				}
				
				GuessTopic paramTopic = new GuessTopic();
				paramTopic.setKind("lol");
				paramTopic.setGroupId(groupId);
				paramTopic.setTopicType(topicType);
				List<GuessTopic> topics = null;
				
				if(guessTopicMapper != null){
					topics = guessTopicMapper.findAllGuessTopicBy(paramTopic, null, null);//
				}
				
				String result[] = map.get(topicType).split("\\|");
				
				if(topics != null){
					if(result.length != topics.size()){
						logger.debug("announceResults|groupId={},map={}",groupId,map);
						logger.debug("announceResults|matchId={},result.length={},result={},topics.size={},topicType={}",matchId,result.length,result,topics.size(),topicType);
					}
					for(int i=0;i<topics.size();i++){
						GuessTopic topic = topics.get(i);
						
						GuessTopicOption queryOption = new GuessTopicOption();
						queryOption.setTopicId(topic.getTopicId());
						queryOption.setValue(result[i]);
						GuessTopicOption option = guessTopicMapper.findOneGuessTopicOptionBy(queryOption);
						
						GuessTopicOption updateOption = new GuessTopicOption();
						updateOption.setId(option.getId());
						updateOption.setIsSelected(true);
						
						GuessTopic queryTopic = new GuessTopic();
						queryTopic.setId(topic.getId());
						queryTopic.setOptionId(option.getId());
						queryTopic.setStatus(2);
						
						guessTopicMapper.update(queryTopic);
						guessTopicMapper.updateOption(updateOption);
						
						options.add(option);
					}
				}
				
			}
		}
		return options;
	}
	
	@Override
	@Transactional(readOnly=false,timeout=10,propagation=Propagation.REQUIRED)
	public List<GuessTopic> updatedLoLTopic(String startDate) {
		
		Calendar c = Calendar.getInstance();
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		
		Date start = c.getTime();
		
		if(startDate != null){
			try{
				start = DateUtils.parseDate(startDate, new String[]{"yyyy-MM-dd HH:mm:ss"});
			}catch(Exception e){
			}
		}
		
		c.set(Calendar.DAY_OF_YEAR, c.get(Calendar.DAY_OF_YEAR) + 8);
		
		Date end = c.getTime();
		
		
		//查询未來七天比赛
		List<GameMatch> gameMatchs = lolMatchNetworkSpider
				.findByLeagueAndTime("", "", 1, 100, start, end);

		List<GuessTopic> topics = new ArrayList<GuessTopic>();
		for (GameMatch match : gameMatchs) {
			topics.addAll(this.createAll(match, "system", null));
		}
		// TODO Auto-generated method stub
		return topics;
	}
	
	@Override
	@Transactional(readOnly=false,timeout=10,propagation=Propagation.REQUIRED)
	public List<GuessTopic> createAll(GameMatch match, String createBy,
			Integer clubId) {
		// TODO Auto-generated method stub
		List<GuessTopic> all = new ArrayList<GuessTopic>();
		GuessTopic sfTopic = this.createTopicForSF(match, createBy, clubId);
		if(sfTopic != null){
			all.add(sfTopic);
		}
		GuessTopic bfTopic = this.createTopicForBF(match, createBy, clubId);
		if(bfTopic != null){
			all.add(bfTopic);
		}
		List<GuessTopic> fbTopics = this.createTopicForFirstBlood(match, createBy, clubId);
		if(fbTopics != null){
			all.addAll(fbTopics);
		}
		List<GuessTopic> ftTopics = this.createTopicForFirstTurret(match, createBy, clubId);
		if(ftTopics != null){
			all.addAll(ftTopics);
		}
		List<GuessTopic> sodTopics = this.createTopicForSOD(match, createBy, clubId);
		if(sodTopics != null){
			all.addAll(sodTopics);
		}
		return all;
	}
	
	private List<GuessTopic> getGuessTopicByTwoOption(GameMatch match,String topicType,String subject,
								String aOption,String bOption,
								String createBy,Integer clubId){
		String groupId = "GM"+DateFormatUtils.format(match.getMatchTime(), "yyyy")+match.getMatchId();
		if(gameMatchMapper != null){
			GameMatch queryMatch = new GameMatch();
			queryMatch.setMatchId(match.getMatchId());
			queryMatch.setGameType("lol");
			
			if(gameMatchMapper.findOneBy(queryMatch) == null){
				gameMatchMapper.insert(match);
			}
		}
		List<GuessTopic> topics = new ArrayList<GuessTopic>();
		int lose = match.getBestOf()/2;
		for(int i=0;i<match.getBestOf()-lose;i++){
			
			GuessTopic topic = new GuessTopic();
			
			Date overTime =  new Date(match.getMatchTime().getTime() - (3 * 60 * 1000) +( i * (30 * 60 * 1000)));//BO1 提前3分钟封盘
			
			Integer topicId = GenerateCode.gen(9);
			List<GuessTopicOption> options = new ArrayList<GuessTopicOption>();
			String round = new String((i+1)+"");
			
			GuessTopicOption teamA = new GuessTopicOption(),
					 teamB = new GuessTopicOption();
			teamA.setName(aOption.replaceAll("#HostTeam", match.getHostTeam())
					.replaceAll("#GuestTeam", match.getGuestTeam())
					.replaceAll("#BestOf", round));
			teamA.setOdds(new BigDecimal("1.85"));
			teamA.setOrderBy(0);
			teamA.setTopicId(topicId);
			teamA.setValue("S");
			
			teamB.setName(bOption.replaceAll("#HostTeam", match.getHostTeam())
					.replaceAll("#GuestTeam", match.getGuestTeam())
					.replaceAll("#BestOf", round));
			teamB.setOdds(new BigDecimal("1.85"));
			teamB.setOrderBy(1);
			teamB.setTopicId(topicId);
			teamB.setValue("F");
			
			options.add(teamA);
			options.add(teamB);
			
			String sj = subject.replaceAll("#HostTeam", match.getHostTeam())
					.replaceAll("#GuestTeam", match.getGuestTeam())
					.replaceAll("#BestOf", round);
			topic.setCreateBy(createBy);
			topic.setOverTime(overTime);
			topic.setCreateTime(new Date());
			topic.setGroupId(groupId);
			topic.setKind("lol");
			topic.setLabel(match.getGroupName());
			topic.setLeague(match.getLeague());
			topic.setSubject(sj);
			topic.setTopicId(topicId);
			topic.setOrderBy(i);
			topic.setOptions(options);
			topic.setTopicType(topicType);
			
			logger.debug("topic={}",new Gson().toJson(topic));
			
			GuessTopic queryTopic = new GuessTopic();
			queryTopic.setTopicType(topicType);
			queryTopic.setGroupId(groupId);
			queryTopic.setOrderBy(topic.getOrderBy());
			if(guessTopicMapper != null && 
					guessTopicMapper.findOneGuessTopicBy(queryTopic) == null){
				guessTopicMapper.insert(topic);
				
				GuessTopicOption queryOption = new GuessTopicOption();
				queryOption.setTopicId(topicId);
				queryOption.setValue(teamA.getValue());
				
				if(guessTopicMapper.findOneGuessTopicOptionBy(queryOption) == null){
					guessTopicMapper.insertOption(teamA);
				}
				queryOption.setValue(teamB.getValue());
				if(guessTopicMapper.findOneGuessTopicOptionBy(queryOption) == null){
					guessTopicMapper.insertOption(teamB);
				}
				logger.info("topic save success.optionA={},optionB={}",new Gson().toJson(teamA),new Gson().toJson(teamB));
			}
			topics.add(topic);
		}
		return topics;
	}
	@Override
	@Transactional(readOnly=false,timeout=10,propagation=Propagation.REQUIRED)
	public GuessTopic createTopicForSF(GameMatch match,String createBy,Integer clubId) {
		String topicType = "SF", groupId = "GM"+DateFormatUtils.format(match.getMatchTime(), "yyyy")+match.getMatchId();
		Date overTime =  new Date(match.getMatchTime().getTime() - 3 * 60 * 1000);//BO1 提前3分钟封盘
		Integer topicId = GenerateCode.gen(9);
		
		List<GuessTopicOption> options = new ArrayList<GuessTopicOption>();
		
		GuessTopicOption teamA = new GuessTopicOption(),
				 teamB = new GuessTopicOption();
		teamA.setName(match.getHostTeam()+ "胜");
		teamA.setOdds(new BigDecimal("1.85"));
		teamA.setOrderBy(0);
		teamA.setTopicId(topicId);
		teamA.setValue("S");
		
		teamB.setName(match.getGuestTeam()+ "胜");
		teamB.setOdds(new BigDecimal("1.85"));
		teamB.setOrderBy(1);
		teamB.setTopicId(topicId);
		teamB.setValue("F");
		
		options.add(teamA);
		options.add(teamB);
		
		GuessTopic topic = new GuessTopic();
		topic.setCreateBy(createBy);
		topic.setClubId(clubId);
		topic.setOverTime(overTime);
		topic.setCreateTime(new Date());
		topic.setGroupId(groupId);
		topic.setTopicType("SF");
		topic.setKind("lol");
		topic.setLabel(match.getGroupName());
		topic.setLeague(match.getLeague());
		topic.setSubject("比赛"+match.getHostTeam()+" VS "+match.getGuestTeam() +"谁将赢得最终胜利？");
		topic.setTopicId(topicId);
		topic.setOrderBy(0);
		topic.setOptions(options);
		
		GuessTopic queryTopic = new GuessTopic();
		queryTopic.setTopicType(topicType);
		queryTopic.setGroupId(groupId);
		if(guessTopicMapper != null && 
				guessTopicMapper.findOneGuessTopicBy(queryTopic) == null){
			guessTopicMapper.insert(topic);
			
			GuessTopicOption queryOption = new GuessTopicOption();
			queryOption.setTopicId(topicId);
			queryOption.setValue(teamA.getValue());
			
			if(guessTopicMapper.findOneGuessTopicOptionBy(queryOption) == null){
				guessTopicMapper.insertOption(teamA);
			}
			queryOption.setValue(teamB.getValue());
			if(guessTopicMapper.findOneGuessTopicOptionBy(queryOption) == null){
				guessTopicMapper.insertOption(teamB);
			}
			logger.info("topic save success.optionA={},optionB={}",new Gson().toJson(teamA),new Gson().toJson(teamB));
		}
		logger.debug("topic={}",new Gson().toJson(topic));
		return topic;
	}

	@Override
	@Transactional(readOnly=false,timeout=10,propagation=Propagation.REQUIRED)
	public GuessTopic createTopicForBF(GameMatch match,String createBy,Integer clubId) {
		if(match.getBestOf() != 1){
			
			String topicType = "BF", groupId = "GM"+DateFormatUtils.format(match.getMatchTime(), "yyyy")+match.getMatchId();
			Date overTime =  new Date(match.getMatchTime().getTime() - 3 * 60 * 1000);//BO1 提前3分钟封盘
			Integer topicId = GenerateCode.gen(9);
			
			List<GuessTopicOption> options = new ArrayList<GuessTopicOption>();
			List<BigDecimal> bas = new ArrayList<BigDecimal>();
			int orderBy = 0,win = match.getBestOf()/2+1;
			for(int i=match.getBestOf();i>=0;i--){
				for(int k=0;k<=match.getBestOf();k++){
					if((i == win && k < win) || (k == win && i < win)){
						GuessTopicOption option = new GuessTopicOption();
						option.setName(i+":"+k);
						option.setOrderBy(orderBy);
						option.setTopicId(topicId);
						option.setValue(i+":"+k);
						options.add(option);
						bas.add(new BigDecimal("1.00"));
						orderBy++;
					}
				}
			}
			
			BigDecimal odds[] = OddsMath.calOdds("0.15", bas.toArray(new BigDecimal[]{}));
			for(int i=0;i<options.size();i++){
				options.get(i).setOdds(odds[i]);
			}
			GuessTopic topic = new GuessTopic();
			topic.setCreateBy(createBy);
			topic.setClubId(clubId);
			topic.setOverTime(overTime);
			topic.setCreateTime(new Date());
			topic.setGroupId(groupId);
			topic.setTopicType(topicType);
			topic.setKind("lol");
			topic.setLabel(match.getGroupName());
			topic.setLeague(match.getLeague());
			topic.setSubject("本场BO"+match.getBestOf()+"最终比分将是？");
			topic.setTopicId(topicId);
			topic.setOrderBy(0);
			topic.setOptions(options);
			
			GuessTopic queryTopic = new GuessTopic();
			queryTopic.setTopicType(topicType);
			queryTopic.setGroupId(groupId);
			if(guessTopicMapper != null && 
					guessTopicMapper.findOneGuessTopicBy(queryTopic) == null){
				guessTopicMapper.insert(topic);
				for(int i=0;i<options.size();i++){
					GuessTopicOption queryOption = new GuessTopicOption();
					queryOption.setTopicId(topicId);
					queryOption.setValue(options.get(i).getValue());
					
					if(guessTopicMapper.findOneGuessTopicOptionBy(queryOption) == null){
						guessTopicMapper.insertOption(options.get(i));
					}
					logger.info("topic save success.options[{}]={}",i,new Gson().toJson(options.get(i)));
				}
			}
			logger.debug("topic={}",new Gson().toJson(topic));
			return topic;
		}
		return null;
	}

	@Override
	@Transactional(readOnly=false,timeout=10,propagation=Propagation.REQUIRED)
	public GuessTopic createTopicForFirst(GameMatch match,String createBy,Integer clubId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(readOnly=false,timeout=10,propagation=Propagation.REQUIRED)
	public List<GuessTopic> createTopicForFirstBlood(GameMatch match,String createBy,Integer clubId) {
		return getGuessTopicByTwoOption(match, "FirstBlood", "第#BestOf局比赛谁先获得一血？","#HostTeam","#GuestTeam",createBy,clubId);
	}

	@Override
	@Transactional(readOnly=false,timeout=10,propagation=Propagation.REQUIRED)
	public List<GuessTopic> createTopicForFirstTurret(GameMatch match,String createBy,Integer clubId) {
		return getGuessTopicByTwoOption(match, "FirstTurret","第#BestOf局比赛谁先获得一塔？","#HostTeam","#GuestTeam",createBy,clubId);
	}

	@Override
	@Transactional(readOnly=false,timeout=10,propagation=Propagation.REQUIRED)
	public List<GuessTopic> createTopicForSOD(GameMatch match,String createBy,Integer clubId) {
		// TODO Auto-generated method stub
		return getGuessTopicByTwoOption(match, "SOD","第#BestOf局比赛总人头数之和为单数OR双数？","单","双",createBy,clubId);
	}
}
