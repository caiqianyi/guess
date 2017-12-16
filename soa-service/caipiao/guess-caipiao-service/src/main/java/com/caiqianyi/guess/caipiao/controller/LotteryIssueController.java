package com.caiqianyi.guess.caipiao.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.caiqianyi.commons.exception.SuccessMessage;
import com.caiqianyi.guess.caipiao.service.ILotteryCatService;
import com.caiqianyi.guess.caipiao.service.ILotteryService;

@RestController
public class LotteryIssueController {
	
	@Resource
	private ILotteryCatService lotteryCatService;
	
	@RequestMapping(value="/issue/day/{kindOf}/{day}/",method=RequestMethod.GET)
	SuccessMessage findIssueByDay(@PathVariable("kindOf") String kindOf,@PathVariable("day") String day){
		ILotteryService lotteryService = lotteryCatService.getLotteryService(kindOf);
		return new SuccessMessage(lotteryService.findIssueByDay(day));
	}
	
	@RequestMapping(value="/issue/findAllKindOf/",method=RequestMethod.GET)
	SuccessMessage findAllKindOf(){
		return new SuccessMessage(lotteryCatService.findAllKindOf());
	}
}
