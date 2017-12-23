package com.caiqianyi.guess.caipiao.kaijiang;

import java.util.Map;

import com.caiqianyi.guess.caipiao.entity.LotteryIssue;

public interface IKJDataService {
	
	void kaijiang(String kindOf,String day,Map<String,LotteryIssue> nums);
	
}
