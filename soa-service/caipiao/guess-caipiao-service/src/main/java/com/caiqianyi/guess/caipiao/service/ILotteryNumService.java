package com.caiqianyi.guess.caipiao.service;

import com.caiqianyi.guess.caipiao.entity.LotteryIssue;

public interface ILotteryNumService {
	
	LotteryIssue findLotteryNumByIssue(String issue);
	
}