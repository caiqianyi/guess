package com.caiqianyi.guess.caipiao.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.caiqianyi.guess.caipiao.core.dao.ILotteryIssueMapper;
import com.caiqianyi.guess.caipiao.data.analysis.YllrAnalysis;
import com.caiqianyi.guess.caipiao.entity.LotteryIssue;
import com.caiqianyi.guess.caipiao.service.ILotteryCatService;
import com.caiqianyi.guess.caipiao.service.ILotteryDataSyncService;
import com.caiqianyi.guess.caipiao.service.ILotteryService;
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
	
	@Override
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
			for(LotteryIssue issue : issues){
				if(lotteryIssueMapper.getIssueByExpect(kindOf, issue.getExpect())==null){
					success.add(issue);
					lotteryIssueMapper.insert(issue);
				}
			}
			
		}
		return success;
	}

	@Override
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
