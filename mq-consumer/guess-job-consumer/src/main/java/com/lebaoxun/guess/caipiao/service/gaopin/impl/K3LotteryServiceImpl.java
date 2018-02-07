package com.lebaoxun.guess.caipiao.service.gaopin.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.lebaoxun.guess.caipiao.config.K3Cat;
import com.lebaoxun.guess.caipiao.core.dao.ILotteryIssueMapper;
import com.lebaoxun.guess.caipiao.entity.LotteryIssue;
import com.lebaoxun.guess.caipiao.service.AbstractLotteryServiceSupport;
import com.lebaoxun.guess.caipiao.service.ILotteryService;

@Service("k3LotteryService")
public class K3LotteryServiceImpl extends AbstractLotteryServiceSupport
		implements ILotteryService {
	
	private Logger logger = LoggerFactory.getLogger(K3LotteryServiceImpl.class);

	private K3Cat cat;
	
	@Resource
	private ILotteryIssueMapper lotteryIssueMapper;
	
	@Override
	public List<LotteryIssue> getIssueForToday() {
		return getIssueByDay(DateFormatUtils.format(new Date(), "yyyyMMdd"));
	}

	@Override
	public List<LotteryIssue> getIssueByDay(String day) {
		try {
			List<LotteryIssue> issues = getLotteryIssueByTimeAndKindOf(cat.getCatId(),day, cat.getPeriod() * 60, cat.getStart(),
					cat.getEnd(),3);
			
			for(LotteryIssue issue : issues){
				issue.setExpect(issue.getExpect().substring(2));
			}
			return issues;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace(); 
		}
		return new ArrayList<LotteryIssue>();
	}

	public static void main(String[] args) {
		ILotteryService k3LotteryService = new K3LotteryServiceImpl();
		k3LotteryService.setKindOf("jsk3");
		System.out.println(new Gson().toJson(k3LotteryService.getIssueForToday()));
		//k3LotteryService.getOpencode(DateFormatUtils.format(new Date(), "yyyyMMdd"));
	}

	@Override
	public List<LotteryIssue> getOpencode(String day) {
		return kaijiang(day,cat.getCatId());
	}

	@Override
	public void setKindOf(String kindOf) {
		// TODO Auto-generated method stub
		this.cat = K3Cat.getCatByKindOf(kindOf);
	}
	
	@Override
	protected String getKindOf() {
		return cat.getCatId();
	}
	
	@Override
	public String[] getLottery() {
		return new String[]{"01","02","03","04","05","06"};
	}
}
