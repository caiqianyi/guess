package com.lebaoxun.guess.caipiao.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.annotation.Resource;

import org.apache.commons.lang.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;
import com.lebaoxun.guess.caipiao.core.dao.IJCLQMatchMapper;
import com.lebaoxun.guess.caipiao.core.dao.ILotteryIssueMapper;
import com.lebaoxun.guess.caipiao.entity.LotteryIssue;
import com.lebaoxun.guess.caipiao.jclq.match.service.IJCLQMatchSyncService;
import com.lebaoxun.guess.caipiao.lryl.YllrAnalysis;
import com.lebaoxun.guess.caipiao.service.ILotteryCatService;
import com.lebaoxun.guess.caipiao.service.ILotteryDataSyncService;
import com.lebaoxun.guess.caipiao.service.ILotteryGuessService;
import com.lebaoxun.guess.caipiao.service.ILotteryService;
import com.lebaoxun.guess.jclq.entity.JCLQMatch;
import com.lebaoxun.guess.jclq.entity.JCLQMatchDatas;
import com.lebaoxun.soa.core.redis.IRedisCache;

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
	private ILotteryGuessService lotteryGuessService;
	
	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRES_NEW)
	public List<JCLQMatch> syncJCLQMatch() {
		// TODO Auto-generated method stub
		Map<String,JCLQMatch> nums = new LinkedHashMap<String, JCLQMatch>();
		jCLQMatchService.pull(nums);
		logger.debug("nums={}",new Gson().toJson(nums));
		List<JCLQMatch> matchs = new ArrayList<JCLQMatch>();
		for(String key : nums.keySet()){
			JCLQMatch match = nums.get(key);
			JCLQMatch lqMatch = jCLQMatchMapper.findMatch(match.getSeq());
			
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
		for(String key : nums.keySet()){
			JCLQMatch match = nums.get(key);
			JCLQMatch lqMatch = jCLQMatchMapper.findMatch(match.getSeq());
			
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
			//List<GuessTemplate> templates = guessTemplateMapper.findAllByWhere(kindOf,null,null,null);
			for(LotteryIssue issue : issues){
				if(lotteryIssueMapper.getIssueByExpect(kindOf, issue.getExpect())==null){
					success.add(issue);
					//lotteryGuessService.createGuessTopic(issue.getExpect(), null, issue.getEndTime(), templates);
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
		
		LotteryIssue prevIssue = lotteryIssueMapper.getCurrentPrevIssue(kindOf);
		if(!(prevIssue == null || prevIssue.getStatus().equals(1))){
			int openSecond = calOpenTime(kindOf);
			if(System.currentTimeMillis() - prevIssue.getEndTime().getTime() >= openSecond * 1000 - 15000){
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
						syncYllrData(prevIssue,kindOf);
					}
				}
			}
		}
		return success;
	}
	
	private int calOpenTime(String kindOf){
		TreeMap<Integer,Integer> opens = new TreeMap<Integer,Integer>();
		String opk = "lottery:open:"+kindOf;
		if(redisCache.exists(opk)){
			opens = (TreeMap<Integer,Integer>) redisCache.get(opk);
		}
		List<Map.Entry<Integer, Integer>> entryArrayList = new ArrayList<Map.Entry<Integer, Integer>>(opens.entrySet());
        Collections.sort(entryArrayList, (o1, o2) -> o1.getValue().compareTo(o2.getValue()));
        Map.Entry<Integer, Integer> item = entryArrayList.get(entryArrayList.size()-1);
		return item.getKey();
	}
	
	private int updateOpenTime(LotteryIssue prevIssue,String kindOf){
		Integer openSecond = 45;
		if(prevIssue != null){
			openSecond = (int) ((System.currentTimeMillis() - prevIssue.getEndTime().getTime())/1000);
		}
		TreeMap<Integer,Integer> opens = new TreeMap<Integer,Integer>();
		String opk = "lottery:open:"+kindOf;
		if(redisCache.exists(opk)){
			opens = (TreeMap<Integer,Integer>) redisCache.get(opk);
		}
		int count = 0;
		if(opens.containsKey(openSecond)){
			count = opens.get(openSecond);
		}
		count ++;
		opens.put(openSecond,count);
		redisCache.set(opk, opens);
		List<Map.Entry<Integer, Integer>> entryArrayList = new ArrayList<Map.Entry<Integer, Integer>>(opens.entrySet());
        Collections.sort(entryArrayList, (o1, o2) -> o1.getValue().compareTo(o2.getValue()));
        Map.Entry<Integer, Integer> item = entryArrayList.get(entryArrayList.size()-1);
		return item.getKey();
	}
	
	@Override
	public void syncYllrData(LotteryIssue prevIssue,String kindOf) {
		
		Integer openSecond = updateOpenTime(prevIssue,kindOf);
		
		// TODO Auto-generated method stub
		ILotteryService lotteryService = lotteryCatService.getLotteryService(kindOf);
		
		List<LotteryIssue> list = lotteryService.getLotteryNumBy(201);
		
		Map<String, Object> datas = (Map<String, Object>) redisCache.get("lottery:yllr:"+kindOf);
		Map<String,Object> initData = null;
		if(datas != null){
			List<Map<String, Object>> yldatas = (List<Map<String, Object>>) datas.get("yl_datas"); 
			for(Map<String, Object> data : yldatas){
				String expect = (String) data.get("expect");
				if(expect.equals(list.get(list.size()-1).getExpect())){
					initData = data;
					break;
				}
			}
		}
		
		logger.debug("initData={}",new Gson().toJson(initData));
		
		Map<String,Object> _500datas = new YllrAnalysis(kindOf,list,lotteryService.getLottery(),initData).doAnalysis();
		_500datas.put("openSecond", openSecond);
		redisCache.set("lottery:yllr:"+kindOf, _500datas);
		
		List<LotteryIssue> d200 = new ArrayList<LotteryIssue>();
		if(list.size() > 200){
			d200.addAll(list.subList(list.size()-200, list.size()));
		}else{
			d200.addAll(list);
		}
		Map<String,Object> _200datas = new YllrAnalysis(kindOf,d200,lotteryService.getLottery()).doAnalysis();
		_200datas.put("openSecond", openSecond);
		redisCache.set("lottery:yllr:"+kindOf+":200", _200datas);
	}
}
