package com.caiqianyi.guess.caipiao.service;

import com.caiqianyi.guess.caipiao.kaijiang.IKJDataService;

public interface ILotteryCatService {
	
	ILotteryService getLotteryService(String kindOf);
	
	ILotterySpiderService getLotteryIssueSpiderService(String kindOf);
	
	IKJDataService getKJDataService(String site);
}
