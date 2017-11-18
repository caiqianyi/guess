package com.qianyi.guess.caipiao.service;

import java.util.List;

import com.caiqianyi.guess.entity.LotteryIssue;
import com.caiqianyi.guess.entity.LotteryNum;

public interface ILotteryNumService {
	
	LotteryIssue findLotteryNumByIssue(String issue);
	
	List<LotteryNum> captureNewestNum();
}