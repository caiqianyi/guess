package com.caiqianyi.guess.caipiao.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.caiqianyi.guess.caipiao.core.dao.IJCLQMatchMapper;
import com.caiqianyi.guess.caipiao.core.dao.ILotteryIssueMapper;
import com.caiqianyi.guess.caipiao.entity.LotteryIssue;
import com.caiqianyi.guess.caipiao.jclq.match.service.IJCLQMatchSyncService;
import com.caiqianyi.guess.caipiao.lryl.YllrAnalysis;
import com.caiqianyi.guess.caipiao.service.ILotteryCatService;
import com.caiqianyi.guess.caipiao.service.ILotteryDataSyncService;
import com.caiqianyi.guess.caipiao.service.ILotteryGuessService;
import com.caiqianyi.guess.caipiao.service.ILotteryService;
import com.caiqianyi.guess.core.dao.GuessTemplateMapper;
import com.caiqianyi.guess.entity.GuessTemplate;
import com.caiqianyi.guess.jclq.entity.JCLQMatch;
import com.caiqianyi.guess.jclq.entity.JCLQMatchDatas;
import com.caiqianyi.soa.core.redis.IRedisCache;
import com.google.gson.Gson;

@Service
public class LotteryDataSyncServiceImpl implements ILotteryDataSyncService{
	
	private Logger logger = LoggerFactory.getLogger(LotteryDataSyncServiceImpl.class);

	@Resource
	private ILotteryCatService lotteryCatService;
	
	@Resource
	private ILotteryIssueMapper lotteryIssueMapper;
	
	@Resource
	private IRedisCache redisCache;
	
	@Resource
	private IJCLQMatchSyncService jCLQMatchService;
	
	@Resource
	private IJCLQMatchMapper jCLQMatchMapper;
	
	@Resource
	private GuessTemplateMapper guessTemplateMapper;
	
	@Resource
	private ILotteryGuessService lotteryGuessService;
	
	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRES_NEW)
	public List<JCLQMatch> syncJCLQMatch() {
		// TODO Auto-generated method stub
		Map<String,JCLQMatch> nums = new LinkedHashMap<String, JCLQMatch>();
		jCLQMatchService.pull(nums);
		logger.debug("nums={}",new Gson().toJson(nums));
		List<JCLQMatch> matchs = new ArrayList<JCLQMatch>();
		List<GuessTemplate> templates = guessTemplateMapper.findAllByWhere("jclq",null,null,null);
		for(String key : nums.keySet()){
			JCLQMatch match = nums.get(key);
			JCLQMatch lqMatch = jCLQMatchMapper.findMatch(match.getSeq());
			
			lotteryGuessService.createGuessTopic(match.getSeq(), match.getLeague(), match.getEndTime(), templates);
			if("2".equals(match.getStatus())){
				lotteryGuessService.updateTopicResult("jclq", match.getSeq(), match.getScore().split("\\-"));
			}
			
			if(lqMatch == null){
				matchs.add(match);
				jCLQMatchMapper.insert(match);
			}else{
				lqMatch.setScore(match.getScore());
				lqMatch.setStatus(match.getStatus());
				lqMatch.setDxf(match.getDxf());
				lqMatch.setRf(match.getRf());
				lqMatch.setMatchTime(match.getMatchTime());
				lqMatch.setEndTime(match.getEndTime());
				//logger.debug("lqMatch={},match={}",new Gson().toJson(lqMatch),new Gson().toJson(match));
				jCLQMatchMapper.update(lqMatch);
			}
			JCLQMatchDatas datas = match.getDatas();
			if(datas != null){
				if(redisCache.exists("lottery:jclq:"+match.getSeq())){
					JCLQMatchDatas matchDatas = (JCLQMatchDatas) redisCache.get("lottery:jclq:"+match.getSeq());
					if(datas.getFightDatas() != null && !datas.getFightDatas().isEmpty()){
						matchDatas.setFightDatas(datas.getFightDatas());
					}
					if(datas.getgDatas() != null && !datas.getgDatas().isEmpty()){
						matchDatas.setgDatas(datas.getgDatas());
					}
					if(datas.gethDatas() != null && !datas.gethDatas().isEmpty()){
						matchDatas.sethDatas(datas.gethDatas());
					}
					if(datas.getOdds() != null && !datas.getOdds().isEmpty()){
						matchDatas.setOdds(datas.getOdds());
					}
					redisCache.update("lottery:jclq:"+match.getSeq(), matchDatas);
				}else{
					redisCache.set("lottery:jclq:"+match.getSeq(), datas, 15*24*60*60l);
				}
			}
		}
		return matchs;
	}
	
	@Override
	public List<JCLQMatch> syncJCLQMatch(String start, String end) {
		Map<String,JCLQMatch> nums = new LinkedHashMap<String, JCLQMatch>();
		jCLQMatchService.pull(nums,start,end);
		logger.debug("nums={}",new Gson().toJson(nums));
		List<JCLQMatch> matchs = new ArrayList<JCLQMatch>();
		List<GuessTemplate> templates = guessTemplateMapper.findAllByWhere("jclq",null,null,null);
		for(String key : nums.keySet()){
			JCLQMatch match = nums.get(key);
			JCLQMatch lqMatch = jCLQMatchMapper.findMatch(match.getSeq());
			
			lotteryGuessService.createGuessTopic(match.getSeq(), match.getLeague(), match.getEndTime(), templates);
			if("2".equals(match.getStatus())){
				lotteryGuessService.updateTopicResult("jclq", match.getSeq(), match.getScore().split("\\-"));
			}
			
			if(lqMatch == null){
				matchs.add(match);
				jCLQMatchMapper.insert(match);
			}else{
				lqMatch.setScore(match.getScore());
				lqMatch.setStatus(match.getStatus());
				lqMatch.setDxf(match.getDxf());
				lqMatch.setRf(match.getRf());
				lqMatch.setMatchTime(match.getMatchTime());
				lqMatch.setEndTime(match.getEndTime());
				//logger.debug("lqMatch={},match={}",new Gson().toJson(lqMatch),new Gson().toJson(match));
				jCLQMatchMapper.update(lqMatch);
			}
		}
		return matchs;
	}
	
	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRES_NEW)
	public List<LotteryIssue> syncIssueforWeek(String kindOf) {
		// TODO Auto-generated method stub
		List<LotteryIssue> issues = new ArrayList<LotteryIssue>(),
				success = new ArrayList<LotteryIssue>();
		ILotteryService lotteryService = lotteryCatService.getLotteryService(kindOf);
		for(int i=0;i<7;i++){
			Calendar c = Calendar.getInstance();
			c.set(Calendar.HOUR, 0);
			c.set(Calendar.MINUTE, 0);
			c.set(Calendar.MILLISECOND, 0);
			c.set(Calendar.DAY_OF_YEAR, c.get(Calendar.DAY_OF_YEAR)+i);
			issues.addAll(lotteryService.getIssueByDay(DateFormatUtils.format(c, "yyyyMMdd")));
		}
		if(!issues.isEmpty()){
			List<GuessTemplate> templates = guessTemplateMapper.findAllByWhere(kindOf,null,null,null);
			for(LotteryIssue issue : issues){
				if(lotteryIssueMapper.getIssueByExpect(kindOf, issue.getExpect())==null){
					success.add(issue);
					lotteryGuessService.createGuessTopic(issue.getExpect(), null, issue.getEndTime(), templates);
					lotteryIssueMapper.insert(issue);
				}
			}
		}
		return success;
	}

	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRES_NEW)
	public List<LotteryIssue> syncOpenCodeForDay(String kindOf, String day) {
		List<LotteryIssue> issues = new ArrayList<LotteryIssue>(),
				success = new ArrayList<LotteryIssue>();
		
		ILotteryService lotteryService = lotteryCatService.getLotteryService(kindOf);
		issues.addAll(lotteryService.getOpencode(day));
		if(!issues.isEmpty()){
			List<LotteryIssue> noOpenList = lotteryIssueMapper.getIssusByKindOfAndStatus(kindOf, 0, day);
			for(LotteryIssue issue : issues){
				for(LotteryIssue noOpen : noOpenList){
					if(noOpen.getExpect().equals(issue.getExpect())){
						logger.debug("issue={}",new Gson().toJson(issue));
						noOpen.setOpenCode(issue.getOpenCode());
						noOpen.setStatus(1);
						noOpen.setOpenTime(issue.getOpenTime());
						logger.debug("noOpen={}",new Gson().toJson(noOpen));
						lotteryIssueMapper.update(noOpen);
						lotteryGuessService.updateTopicResult(kindOf, noOpen.getExpect(), issue.getOpenCode().split("\\,"));
						success.add(issue);
					}
				}
			}
			if(!success.isEmpty()){
				syncYllrData(kindOf);
			}
		}
		return success;
	}
	
	@Override
	public void syncYllrData(String kindOf) {
		// TODO Auto-generated method stub
		ILotteryService lotteryService = lotteryCatService.getLotteryService(kindOf);
		List<LotteryIssue> list = lotteryService.getLotteryNumBy(501);
		
		List<Map<String, Object>> datas = (List<Map<String, Object>>) redisCache.get("lottery:yllr:"+kindOf);
		Map<String,Object> initData = null;
		if(datas != null){
			for(Map<String, Object> data : datas){
				String expect = (String) data.get("expect");
				if(expect.equals(list.get(list.size()-1).getExpect())){
					initData = data;
					break;
				}
			}
		}
		logger.debug("initData={}",new Gson().toJson(initData));
		redisCache.set("lottery:yllr:"+kindOf, new YllrAnalysis(list,lotteryService.getLottery(),initData).doAnalysis());
		
		List<Map<String, Object>> datas200 = new YllrAnalysis(list,lotteryService.getLottery()).doAnalysis();
		List<Map<String, Object>> d200 = new ArrayList<Map<String, Object>>();
		if(datas200.size() > 200){
			d200.addAll(datas200.subList(datas200.size()-200, datas200.size()));
		}else{
			d200.addAll(datas200);
		}
		redisCache.set("lottery:yllr:"+kindOf+":200", d200);
	}
}
