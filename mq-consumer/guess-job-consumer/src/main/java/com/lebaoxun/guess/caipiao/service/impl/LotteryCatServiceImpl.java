package com.lebaoxun.guess.caipiao.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lebaoxun.commons.beans.SpringConfigTool;
import com.lebaoxun.guess.caipiao.config.K3Cat;
import com.lebaoxun.guess.caipiao.config.SSCCat;
import com.lebaoxun.guess.caipiao.config._11x5Cat;
import com.lebaoxun.guess.caipiao.core.dao.ILotteryIssueMapper;
import com.lebaoxun.guess.caipiao.kaijiang.IKJDataService;
import com.lebaoxun.guess.caipiao.service.ILotteryCatService;
import com.lebaoxun.guess.caipiao.service.ILotteryService;

@Service
public class LotteryCatServiceImpl implements ILotteryCatService {

	@Resource
	private ILotteryIssueMapper lotteryIssueMapper;
	
	@Override
	public List<String> findAllKindOf() {
		// TODO Auto-generated method stub
		return lotteryIssueMapper.findAllKindOf();
	}
	
	@Override
	public ILotteryService getLotteryService(String kindOf) {
		// TODO Auto-generated method stub
		_11x5Cat cat = _11x5Cat.getCatByKindOf(kindOf);
		ILotteryService  lotteryService = null;
		if(cat != null){
			lotteryService = (ILotteryService)SpringConfigTool.getBean("11x5LotteryService");
			lotteryService.setKindOf(kindOf);
			return lotteryService;
		}
		
		SSCCat sscCat = SSCCat.getCatByKindOf(kindOf);
		if(sscCat != null){
			lotteryService = (ILotteryService)SpringConfigTool.getBean("sscLotteryService");
			lotteryService.setKindOf(kindOf);
			return lotteryService;
		}
		
		K3Cat k3Cat = K3Cat.getCatByKindOf(kindOf);
		if(k3Cat != null){
			lotteryService = (ILotteryService)SpringConfigTool.getBean("k3LotteryService");
			lotteryService.setKindOf(kindOf);
			return lotteryService;
		}
		if("bjpk10".equals(kindOf)){
			lotteryService = (ILotteryService)SpringConfigTool.getBean("bjpk10LotteryService");
			lotteryService.setKindOf(kindOf);
			return lotteryService;
		}
		return null;
	}
	
	@Override
	public IKJDataService getKJDataService(String site) {
		// TODO Auto-generated method stub
		return (IKJDataService) SpringConfigTool.getBean(site+"KJDataService");
	}

}
