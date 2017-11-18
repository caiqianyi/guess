package com.qianyi.guess.caipiao.service;

import java.util.List;

import com.caiqianyi.guess.entity.LotteryIssue;

public interface ILotteryIssueService {
	
	LotteryIssue getCurrentIssue();
	
	List<LotteryIssue> getIssueForToday();

	List<LotteryIssue> getIssueByDay(String day);
}