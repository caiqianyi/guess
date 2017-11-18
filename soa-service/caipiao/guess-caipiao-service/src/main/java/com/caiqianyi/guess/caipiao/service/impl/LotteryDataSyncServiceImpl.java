package com.caiqianyi.guess.caipiao.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.time.DateFormatUtils;
import org.springframework.stereotype.Service;

import com.caiqianyi.guess.caipiao.config.K3Cat;
import com.caiqianyi.guess.caipiao.config.SSCCat;
import com.caiqianyi.guess.caipiao.config._11x5Cat;
import com.caiqianyi.guess.caipiao.core.dao.ILotteryIssueMapper;
import com.caiqianyi.guess.caipiao.entity.LotteryIssue;
import com.caiqianyi.guess.caipiao.service.ILotteryDataSyncService;
import com.caiqianyi.guess.caipiao.service.gaopin.I11x5LotteryService;
import com.caiqianyi.guess.caipiao.service.gaopin.IBjxcLotteryService;
import com.caiqianyi.guess.caipiao.service.gaopin.IK3LotteryService;
import com.caiqianyi.guess.caipiao.service.gaopin.ISSCLotteryService;

@Service
public class LotteryDataSyncServiceImpl implements ILotteryDataSyncService{

	@Resource
	private I11x5LotteryService _11x5IssueSpiderService;
	
	@Resource
	private ISSCLotteryService sscLotteryService;
	
	@Resource
	private IK3LotteryService k3LotteryService;
	
	@Resource
	private IBjxcLotteryService bjxcLotteryService;
	
	@Resource
	private ILotteryIssueMapper lotteryIssueMapper;
	
	@Override
	public List<LotteryIssue> syncIssueforWeek(String kindOf) {
		// TODO Auto-generated method stub
		List<LotteryIssue> issues = new ArrayList<LotteryIssue>(),
				success = new ArrayList<LotteryIssue>();
		_11x5Cat cat = _11x5Cat.getCatByKindOf(kindOf);
		if(cat != null){
			_11x5IssueSpiderService.setCatId(cat);
			for(int i=0;i<7;i++){
				Calendar c = Calendar.getInstance();
				c.set(Calendar.HOUR, 0);
				c.set(Calendar.MINUTE, 0);
				c.set(Calendar.MILLISECOND, 0);
				c.set(Calendar.DAY_OF_YEAR, c.get(Calendar.DAY_OF_YEAR)+i);
				issues.addAll(_11x5IssueSpiderService.getIssueByDay(DateFormatUtils.format(c, "yyyyMMdd")));
			}
		}
		
		SSCCat sscCat = SSCCat.getCatByKindOf(kindOf);
		if(sscCat != null){
			sscLotteryService.setCatId(sscCat);
			for(int i=0;i<7;i++){
				Calendar c = Calendar.getInstance();
				c.set(Calendar.HOUR, 0);
				c.set(Calendar.MINUTE, 0);
				c.set(Calendar.MILLISECOND, 0);
				c.set(Calendar.DAY_OF_YEAR, c.get(Calendar.DAY_OF_YEAR)+i);
				issues.addAll(sscLotteryService.getIssueByDay(DateFormatUtils.format(c, "yyyyMMdd")));
			}
		}
		
		K3Cat k3Cat = K3Cat.getCatByKindOf(kindOf);
		if(k3Cat != null){
			k3LotteryService.setCatId(k3Cat);
			for(int i=0;i<7;i++){
				Calendar c = Calendar.getInstance();
				c.set(Calendar.HOUR, 0);
				c.set(Calendar.MINUTE, 0);
				c.set(Calendar.MILLISECOND, 0);
				c.set(Calendar.DAY_OF_YEAR, c.get(Calendar.DAY_OF_YEAR)+i);
				issues.addAll(k3LotteryService.getIssueByDay(DateFormatUtils.format(c, "yyyyMMdd")));
			}
		}
		if("bjsc".equals(kindOf)){
			for(int i=0;i<7;i++){
				Calendar c = Calendar.getInstance();
				c.set(Calendar.HOUR, 0);
				c.set(Calendar.MINUTE, 0);
				c.set(Calendar.MILLISECOND, 0);
				c.set(Calendar.DAY_OF_YEAR, c.get(Calendar.DAY_OF_YEAR)+i);
				issues.addAll(bjxcLotteryService.getIssueByDay(DateFormatUtils.format(c, "yyyyMMdd")));
			}
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
	public List<LotteryIssue> syncOpenCodeForToday(String kindOf) {
		_11x5Cat cat = _11x5Cat.getCatByKindOf(kindOf);
		List<LotteryIssue> issues = new ArrayList<LotteryIssue>(),
				success = new ArrayList<LotteryIssue>();
		if(cat != null){
			_11x5IssueSpiderService.setCatId(cat);
			issues.addAll(_11x5IssueSpiderService.captureNewestNum());
		}
		
		SSCCat sscCat = SSCCat.getCatByKindOf(kindOf);
		if(sscCat != null){
			sscLotteryService.setCatId(sscCat);
			issues.addAll(sscLotteryService.captureNewestNum());
		}
		
		K3Cat k3Cat = K3Cat.getCatByKindOf(kindOf);
		if(k3Cat != null){
			k3LotteryService.setCatId(k3Cat);
			issues.addAll(k3LotteryService.captureNewestNum());
		}
		if("bjsc".equals(kindOf)){
			issues.addAll(bjxcLotteryService.captureNewestNum());
		}
		if(!issues.isEmpty()){
			List<LotteryIssue> noOpenList = lotteryIssueMapper.getIssusByKindOfAndStatus(kindOf, 0, DateFormatUtils.format(new Date(), "yyyy-MM-dd"));
			for(LotteryIssue issue : issues){
				for(LotteryIssue noOpen : noOpenList){
					if(noOpen.getExpect().equals(issue.getExpect())){
						noOpen.setOpenCode(issue.getOpenCode());
						noOpen.setStatus(1);
						issue.setOpenTime(issue.getOpenTime());
						lotteryIssueMapper.update(issue);
					}
				}
			}
			
		}
		return success;
	}


}
