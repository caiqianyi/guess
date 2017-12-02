package com.caiqianyi.guess.caipiao.service;

import java.util.List;

import com.caiqianyi.guess.caipiao.entity.LotteryIssue;
import com.caiqianyi.guess.jclq.entity.JCLQMatch;

public interface ILotteryDataSyncService {
	
	List<LotteryIssue> syncIssueforWeek(String kindOf);
	
	List<LotteryIssue> syncOpenCodeForDay(String kindOf,String day);
	
	List<JCLQMatch> syncJCLQMatch();
	
	void syncYllrData(String kindOf);
}
