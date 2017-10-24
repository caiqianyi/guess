package com.caiqianyi.guess.match.game.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.caiqianyi.commons.utils.GenerateCode;
import com.caiqianyi.commons.utils.OddsMath;
import com.caiqianyi.guess.core.dao.IGuessTopicMapper;
import com.caiqianyi.guess.entity.GuessTopic;
import com.caiqianyi.guess.entity.GuessTopicOption;
import com.caiqianyi.guess.game.entity.GameMatch;
import com.caiqianyi.guess.game.lol.vo.BattleData;
import com.caiqianyi.guess.game.lol.vo.LoLSMatch;
import com.caiqianyi.guess.match.game.service.ILoLGuessTopicService;
import com.caiqianyi.guess.match.game.spider.ILoLMatchNetworkSpider;
import com.caiqianyi.guess.match.game.spider.impl.LoLMatchNetworkSpiderImpl;
import com.google.gson.Gson;

@Service
public class LoLGuessTopicServiceImpl implements ILoLGuessTopicService {
	
	private Logger logger = LoggerFactory.getLogger(LoLGuessTopicServiceImpl.class);
	
	@Resource
	private IGuessTopicMapper guessTopicMapper;
	
	@Resource
	private ILoLMatchNetworkSpider loLMatchNetworkSpider;
	
	
	public Map<String,String> getGuessResultByMatch(String matchId){
		loLMatchNetworkSpider = loLMatchNetworkSpider == null ? new LoLMatchNetworkSpiderImpl():loLMatchNetworkSpider;
		
		GameMatch match = loLMatchNetworkSpider.findByMatchId(matchId);
		if(match.getStatus() == 3){
			
			List<LoLSMatch> battleDatas = loLMatchNetworkSpider.findSMatchByMatchId(matchId);
			Map<String,String> result = new LinkedHashMap<String,String>();
			String sfOp = "F",fbOp = "",ftOp = "",sodOp = "",bfOp = match.getScore();
			if("host".equals(match.getMatchWin())){
				sfOp = "S";
			}
			if(battleDatas != null && !battleDatas.isEmpty()){
				for(int i=0;i<battleDatas.size();i++){
					LoLSMatch lsm = battleDatas.get(i);
					BattleData battleData = lsm.getBattleData();
					fbOp += "host".equals(battleData.getFirstBlood()) ? "S" : "F";
					ftOp += "host".equals(battleData.getFirstTower()) ? "S" : "F";
					sodOp += battleData.getKills() % 2 != 0 ? "S" : "F";
					if(i<battleDatas.size()-1){
						fbOp+="|";
						ftOp+="|";
						sodOp+="|";
					}
				}
			}
			result.put("SF", sfOp);
			result.put("FirstBlood", fbOp);
			result.put("FirstTurret", ftOp);
			result.put("SOD", sodOp);
			result.put("BF", bfOp);
			result.put("game", match.getHostTeam()+" VS "+match.getGuestTeam());
			logger.debug("result={}",result);
			return result;
		}
		return null;
	}
	
	private List<GuessTopic> getGuessTopicByTwoOption(GameMatch match,String topicType,String subject,
								String aOption,String bOption,
								String createBy,Integer roomId){
		if(match.getStatus() == 1){
			List<GuessTopic> topics = new ArrayList<GuessTopic>();
			int lose = match.getBestOf()/2;
			for(int i=0;i<match.getBestOf()-lose;i++){
				
				GuessTopic topic = new GuessTopic();
				
				Date beginTime =  new Date(match.getMatchTime().getTime() - 3 * 60 * 1000 +( i * (30 + 60 * 1000)));//BO1 提前3分钟封盘
				
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
				
				topic.setBeginTime(beginTime);
				topic.setCreateTime(new Date());
				topic.setGroupId("GM"+DateFormatUtils.format(match.getMatchTime(), "yyyy")+match.getMatchId());
				topic.setKind("game");
				topic.setLabel(match.getGroupName());
				topic.setLeague(match.getLeague());
				topic.setSubject(sj);
				topic.setTopicId(topicId);
				topic.setOrderBy(i);
				topic.setOptions(options);
				topic.setTopicType(topicType);
				
				logger.debug("topic={}",new Gson().toJson(topic));
				
				if(guessTopicMapper != null){
					guessTopicMapper.insert(topic);
					guessTopicMapper.insertOption(teamA);
					guessTopicMapper.insertOption(teamB);
					logger.info("topic save success.optionA={},optionB={}",new Gson().toJson(teamA),new Gson().toJson(teamB));
				}
				topics.add(topic);
			}
			return topics;
		}
		return null;
	}
	@Override
	public GuessTopic createTopicForSF(GameMatch match,String createBy,Integer roomId) {
		if(match.getStatus() == 1){
			Date beginTime =  new Date(match.getMatchTime().getTime() - 3 * 60 * 1000 +( match.getBestOf() * (30 + 60 * 1000)));//BO1 提前3分钟封盘
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
			topic.setRoomId(roomId);
			topic.setBeginTime(beginTime);
			topic.setCreateTime(new Date());
			topic.setGroupId("GM"+DateFormatUtils.format(match.getMatchTime(), "yyyy")+match.getMatchId());
			topic.setTopicType("SF");
			topic.setKind("game");
			topic.setLabel(match.getGroupName());
			topic.setLeague(match.getLeague());
			topic.setSubject("比赛"+match.getHostTeam()+" VS "+match.getGuestTeam() +"谁将赢得最终胜利？");
			topic.setTopicId(topicId);
			topic.setOrderBy(0);
			topic.setOptions(options);
			
			logger.debug("topic={}",new Gson().toJson(topic));
			return topic;
		}
		return null;
	}

	@Override
	public GuessTopic createTopicForBF(GameMatch match,String createBy,Integer roomId) {
		if(match.getStatus() == 1 && match.getBestOf() != 1){
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
			topic.setRoomId(roomId);
			topic.setBeginTime(match.getMatchTime());
			topic.setCreateTime(new Date());
			topic.setGroupId("GM"+DateFormatUtils.format(match.getMatchTime(), "yyyy")+match.getMatchId());
			topic.setKind("game");
			topic.setLabel(match.getGroupName());
			topic.setLeague(match.getLeague());
			topic.setSubject("本场BO"+match.getBestOf()+"最终比分将是？");
			topic.setTopicId(topicId);
			topic.setOrderBy(0);
			topic.setOptions(options);
			
			logger.debug("topic={}",new Gson().toJson(topic));
			return topic;
		}
		return null;
	}

	@Override
	public GuessTopic createTopicForFirst(GameMatch match,String createBy,Integer roomId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<GuessTopic> createTopicForFirstBlood(GameMatch match,String createBy,Integer roomId) {
		return getGuessTopicByTwoOption(match, "FirstBlood", "第#BestOf局比赛谁先获得一血？","#HostTeam","#GuestTeam",createBy,roomId);
	}

	@Override
	public List<GuessTopic> createTopicForFirstTurret(GameMatch match,String createBy,Integer roomId) {
		return getGuessTopicByTwoOption(match, "FirstTurret","第#BestOf局比赛谁先获得一塔？","#HostTeam","#GuestTeam",createBy,roomId);
	}

	@Override
	public List<GuessTopic> createTopicForSOD(GameMatch match,String createBy,Integer roomId) {
		// TODO Auto-generated method stub
		return getGuessTopicByTwoOption(match, "SOD","第#BestOf局比赛总人头数之和为单数OR双数？","单","双",createBy,roomId);
	}
}
