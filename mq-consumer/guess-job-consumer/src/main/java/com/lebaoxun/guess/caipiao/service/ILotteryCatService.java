package com.lebaoxun.guess.caipiao.service;

import java.util.List;

import com.lebaoxun.guess.caipiao.kaijiang.IKJDataService;

public interface ILotteryCatService {
	
	ILotteryService getLotteryService(String kindOf);
	
	List<String> findAllKindOf();
	
	IKJDataService getKJDataService(String site);
}
