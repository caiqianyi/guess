package com.lebaoxun.guess.caipiao.service;

import java.util.List;

import com.lebaoxun.guess.caipiao.entity.LotteryIssue;

public interface ILotteryService {
	
	LotteryIssue getCurrentIssue();
	
	LotteryIssue getLotteryNumByIssue(String issue);
	
	List<LotteryIssue> findIssueByDay(String day);
	
	List<LotteryIssue> getLotteryNumBy(Integer size);
	
	List<LotteryIssue> getIssueForToday();

	List<LotteryIssue> getIssueByDay(String day);
	
	List<LotteryIssue> getOpencode(String day);
	
	void setKindOf(String kindOf);
	
	String[] getLottery();
}
