package com.caiqianyi.guess.caipiao.service;

import java.util.List;

import com.caiqianyi.guess.caipiao.kaijiang.IKJDataService;

public interface ILotteryCatService {
	
	ILotteryService getLotteryService(String kindOf);
	
	ILotterySpiderService getLotteryIssueSpiderService(String kindOf);
	
	List<String> findAllKindOf();
	
	IKJDataService getKJDataService(String site);
}
