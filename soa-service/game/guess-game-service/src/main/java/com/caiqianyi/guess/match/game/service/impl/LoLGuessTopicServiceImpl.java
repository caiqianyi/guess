package com.caiqianyi.guess.match.game.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.caiqianyi.commons.utils.GenerateCode;
import com.caiqianyi.guess.entity.GuessTopic;
import com.caiqianyi.guess.entity.GuessTopicOption;
import com.caiqianyi.guess.game.entity.GameMatch;
import com.caiqianyi.guess.match.game.service.ILoLGuessTopicService;
import com.google.gson.Gson;

@Service
public class LoLGuessTopicServiceImpl implements ILoLGuessTopicService {
	private Logger logger = LoggerFactory.getLogger(LoLGuessTopicServiceImpl.class);
	
	private List<GuessTopic> getGuessTopicByTwoOption(GameMatch match,String subject){
		if(match.getStatus() == 1){
			List<GuessTopic> topics = new ArrayList<GuessTopic>();
			int orderBy = 1,lose = match.getBestOf()/2;
			for(int i=0;i<match.getBestOf()-lose;i++){
				
				GuessTopic topic = new GuessTopic();
				
				Date beginTime =  new Date(match.getMatchTime().getTime() - 3 * 60 * 1000 +( i * (30 + 60 * 1000)));//BO1 提前3分钟封盘
				
				Integer topicId = GenerateCode.gen(9);
				List<GuessTopicOption> options = new ArrayList<GuessTopicOption>();
				
				GuessTopicOption teamA = new GuessTopicOption(),
						 teamB = new GuessTopicOption();
				teamA.setName(match.getHostTeam());
				teamA.setOdds(new BigDecimal("2.00"));
				teamA.setOrderBy(orderBy);
				teamA.setTopicId(topicId);
				teamA.setValue("S");
				
				teamB.setName(match.getGuestTeam());
				teamB.setOdds(new BigDecimal("2.00"));
				teamB.setOrderBy(2);
				teamB.setTopicId(topicId);
				teamB.setValue("F");
				
				options.add(teamA);
				options.add(teamB);
				
				topic.setBeginTime(beginTime);
				topic.setCreateTime(new Date());
				topic.setGroupId("GM"+DateFormatUtils.format(new Date(), "yyyy")+match.getMatchId());
				topic.setKind("game");
				topic.setLabel(match.getGroupName());
				topic.setLeague(match.getLeague());
				String round = new String((i+1)+"");
				String sj = subject.replaceAll("#HostTeam", match.getHostTeam())
						.replaceAll("#GuestTeam", match.getGuestTeam())
						.replaceAll("#BestOf", round);
				
				topic.setSubject(sj);
				topic.setTopicId(topicId);
				topic.setOptions(options);
				
				logger.debug("topic={}",new Gson().toJson(topic));
				topics.add(topic);
				
				orderBy++;
			}
			return topics;
		}
		return null;
	}
	@Override
	public List<GuessTopic> createTopicForSF(GameMatch match) {
		return getGuessTopicByTwoOption(match, "比赛 #HostTeam VS #GuestTeam 谁将赢得最终胜利？");
	}

	@Override
	public GuessTopic createTopicForBF(GameMatch match) {
		if(match.getStatus() == 1 && match.getBestOf() != 1){
			GuessTopic topic = new GuessTopic();
			Integer topicId = GenerateCode.gen(9);
			
			List<GuessTopicOption> options = new ArrayList<GuessTopicOption>();
			int orderBy = 1,win  =match.getBestOf()/2+1;
			for(int i=match.getBestOf();i>=0;i--){
				for(int k=0;k<=match.getBestOf();k++){
					
					if((i == win && k < win) || (k == win && i < win)){
						GuessTopicOption option = new GuessTopicOption();
						option.setName(i+":"+k);
						option.setOdds(new BigDecimal("2.00"));
						option.setOrderBy(orderBy);
						option.setTopicId(topicId);
						option.setValue(i>k?"H"+i:"G"+k);
						options.add(option);
						orderBy++;
					}
				}
			}
			topic.setBeginTime(match.getMatchTime());
			topic.setCreateTime(new Date());
			topic.setGroupId("GM"+DateFormatUtils.format(new Date(), "yyyy")+match.getMatchId());
			topic.setKind("game");
			topic.setLabel(match.getGroupName());
			topic.setLeague(match.getLeague());
			topic.setSubject("本场BO"+match.getBestOf()+"最终比分将是？");
			topic.setTopicId(topicId);
			topic.setOptions(options);
			
			logger.debug("topic={}",new Gson().toJson(topic));
			return topic;
		}
		return null;
	}

	@Override
	public GuessTopic createTopicForFirst(GameMatch match) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<GuessTopic> createTopicForFirstBlood(GameMatch match) {
		return getGuessTopicByTwoOption(match, "第#BestOf局比赛谁先获得一血？");
	}

	@Override
	public List<GuessTopic> createTopicForFirstTurret(GameMatch match) {
		return getGuessTopicByTwoOption(match, "第#BestOf局比赛谁先获得一塔？");
	}

}
