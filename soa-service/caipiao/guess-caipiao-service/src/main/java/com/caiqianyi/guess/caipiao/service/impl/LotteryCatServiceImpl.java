package com.caiqianyi.guess.caipiao.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.caiqianyi.commons.beans.SpringConfigTool;
import com.caiqianyi.guess.caipiao.config.K3Cat;
import com.caiqianyi.guess.caipiao.config.SSCCat;
import com.caiqianyi.guess.caipiao.config._11x5Cat;
import com.caiqianyi.guess.caipiao.kaijiang.IKJDataService;
import com.caiqianyi.guess.caipiao.service.ILotteryCatService;
import com.caiqianyi.guess.caipiao.service.ILotterySpiderService;
import com.caiqianyi.guess.caipiao.service.ILotteryService;
import com.caiqianyi.guess.caipiao.service.gaopin.I11x5LotteryService;
import com.caiqianyi.guess.caipiao.service.gaopin.IBjscLotteryService;
import com.caiqianyi.guess.caipiao.service.gaopin.IK3LotteryService;
import com.caiqianyi.guess.caipiao.service.gaopin.ISSCLotteryService;

@Service
public class LotteryCatServiceImpl implements ILotteryCatService {

	@Resource
	private I11x5LotteryService _11x5IssueSpiderService;
	
	@Resource
	private ISSCLotteryService sscLotteryService;
	
	@Resource
	private IK3LotteryService k3LotteryService;
	
	@Resource
	private IBjscLotteryService bjxcLotteryService;
	
	@Override
	public ILotteryService getLotteryService(String kindOf) {
		// TODO Auto-generated method stub
		_11x5Cat cat = _11x5Cat.getCatByKindOf(kindOf);
		if(cat != null){
			_11x5IssueSpiderService.setKindOf(kindOf);
			return _11x5IssueSpiderService;
		}
		
		SSCCat sscCat = SSCCat.getCatByKindOf(kindOf);
		if(sscCat != null){
			sscLotteryService.setKindOf(kindOf);
			return sscLotteryService;
		}
		
		K3Cat k3Cat = K3Cat.getCatByKindOf(kindOf);
		if(k3Cat != null){
			k3LotteryService.setKindOf(kindOf);
			return k3LotteryService;
		}
		if("bjpk10".equals(kindOf)){
			return bjxcLotteryService;
		}
		return null;
	}
	
	@Override
	public ILotterySpiderService getLotteryIssueSpiderService(String kindOf) {
		// TODO Auto-generated method stub
		return getLotteryService(kindOf);
	}
	
	@Override
	public IKJDataService getKJDataService(String site) {
		// TODO Auto-generated method stub
		return (IKJDataService) SpringConfigTool.getBean(site+"KJDataService");
	}

}
