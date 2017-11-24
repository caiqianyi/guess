package com.caiqianyi.guess.caipiao.service;

import java.util.List;

import com.caiqianyi.guess.caipiao.entity.LotteryIssue;

public interface ILotteryService extends ILotterySpiderService,ILotteryCalService{
	
	LotteryIssue getCurrentIssue();
	
	LotteryIssue getLotteryNumByIssue(String issue);
	
	List<LotteryIssue> getLotteryNumBy(Integer size);
	
	void setKindOf(String kindOf);
	
	String[] getLottery();
}
