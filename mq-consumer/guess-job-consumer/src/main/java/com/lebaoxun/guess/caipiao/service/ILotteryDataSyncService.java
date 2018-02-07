package com.lebaoxun.guess.caipiao.service;

import java.util.List;

import com.lebaoxun.guess.caipiao.entity.LotteryIssue;
import com.lebaoxun.guess.jclq.entity.JCLQMatch;

public interface ILotteryDataSyncService {
	
	List<LotteryIssue> syncIssueforWeek(String kindOf);
	
	List<LotteryIssue> syncOpenCodeForDay(String kindOf,String day);
	
	List<JCLQMatch> syncJCLQMatch();
	
	List<JCLQMatch> syncJCLQMatch(String start,String end);
	
	void syncYllrData(LotteryIssue prevIssue,String kindOf);
}
