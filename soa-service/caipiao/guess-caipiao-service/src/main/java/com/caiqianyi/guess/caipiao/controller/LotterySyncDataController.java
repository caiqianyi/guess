package com.caiqianyi.guess.caipiao.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.caiqianyi.commons.exception.SuccessMessage;
import com.caiqianyi.guess.caipiao.service.ILotteryDataSyncService;

@RestController
public class LotterySyncDataController {
	@Resource
	private ILotteryDataSyncService lotteryDataSyncService;
	
	@RequestMapping(value="/lottery/syncData/{kindOf}/week",method=RequestMethod.GET)
	SuccessMessage syncIssueforWeek(@PathVariable("kindOf")String kindOf){
		return new SuccessMessage(lotteryDataSyncService.syncIssueforWeek(kindOf));
	}
	
	@RequestMapping(value="/lottery/syncData/{kindOf}/openCode",method=RequestMethod.GET)
	SuccessMessage syncOpenCodeForToday(String kindOf){
		return new SuccessMessage(lotteryDataSyncService.syncOpenCodeForToday(kindOf));
	}
}
