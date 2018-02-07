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

import com.lebaoxun.guess.caipiao.config._11x5Cat;
import com.lebaoxun.guess.caipiao.core.dao.ILotteryIssueMapper;
import com.lebaoxun.guess.caipiao.entity.LotteryIssue;
import com.lebaoxun.guess.caipiao.service.AbstractLotteryServiceSupport;
import com.lebaoxun.guess.caipiao.service.ILotteryService;

@Service("11x5LotteryService")
public class _11x5LotteryServiceImpl extends AbstractLotteryServiceSupport
		implements ILotteryService {
	
	private _11x5Cat cat;

	private Logger logger = LoggerFactory
			.getLogger(_11x5LotteryServiceImpl.class);
	
	@Resource
	private ILotteryIssueMapper lotteryIssueMapper;

	@Override
	public List<LotteryIssue> getIssueForToday() {
		return getIssueByDay(DateFormatUtils.format(new Date(), "yyyyMMdd"));
	}

	@Override
	public List<LotteryIssue> getIssueByDay(String day) {
		try {
			List<LotteryIssue> issues = getLotteryIssueByTimeAndKindOf(cat.getCatId(), day, cat.getPeriod() * 60, cat.getStart(),
					cat.getEnd());
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
		ILotteryService _11x5LotteryService = new _11x5LotteryServiceImpl();
		_11x5LotteryService.setKindOf("gdsyxw");
		_11x5LotteryService.getIssueForToday();
		_11x5LotteryService.getOpencode(DateFormatUtils.format(new Date(), "yyyyMMdd"));
	}

	@Override
	public List<LotteryIssue> getOpencode(String day) {
		// TODO Auto-generated method stub
		return kaijiang(day,cat.getCatId());
	}

	@Override
	public void setKindOf(String kindOf) {
		// TODO Auto-generated method stub
		this.cat = _11x5Cat.getCatByKindOf(kindOf);
	}
	
	@Override
	public LotteryIssue getCurrentIssue() {
		// TODO Auto-generated method stub
		return lotteryIssueMapper.getCurrentIssue(cat.getCatId());
	}

	@Override
	protected String getKindOf() {
		return cat.getCatId();
	}
	
	@Override
	public String[] getLottery() {
		return new String[]{"01","02","03","04","05","06","07","08","09","10","11"};
	}
}
