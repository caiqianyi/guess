package com.caiqianyi.guess.caipiao.service;

import java.util.List;

import com.caiqianyi.guess.caipiao.entity.LotteryIssue;

public interface ILotteryDataSyncService {
	
	List<LotteryIssue> syncIssueforWeek(String kindOf);
	
	List<LotteryIssue> syncOpenCodeForDay(String kindOf,String day);
	
	void syncYllrData(String kindOf);
}
