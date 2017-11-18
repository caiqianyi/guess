package com.caiqianyi.guess.caipiao.service;

import java.util.List;

import com.caiqianyi.guess.caipiao.entity.LotteryIssue;

public interface ILotteryIssueSpiderService {
	
	List<LotteryIssue> getIssueForToday();

	List<LotteryIssue> getIssueByDay(String day);
	
	List<LotteryIssue> captureNewestNum();

}