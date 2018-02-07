package com.lebaoxun.guess.caipiao.kaijiang;

import java.util.Map;

import com.lebaoxun.guess.caipiao.entity.LotteryIssue;

public interface IKJDataService {
	
	void kaijiang(String kindOf,String day,Map<String,LotteryIssue> nums);
	
}
