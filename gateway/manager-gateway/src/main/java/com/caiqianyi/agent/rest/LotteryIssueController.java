package com.caiqianyi.agent.rest;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.caiqianyi.commons.exception.SuccessMessage;
import com.caiqianyi.guess.caipiao.service.ILotteryIssueService;

@RestController
public class LotteryIssueController {
	
	@Resource
	private ILotteryIssueService lotteryIssueService;
	
	@RequestMapping(value="/issue/day/{kindOf}/{day}",method=RequestMethod.GET)
	SuccessMessage findIssueByDay(@PathVariable("kindOf") String kindOf,@PathVariable("day") String day){
		return lotteryIssueService.findIssueByDay(kindOf, day);
	}
	
	@RequestMapping(value="/issue/findAllKindOf/",method=RequestMethod.GET)
	SuccessMessage findAllKindOf(){
		return lotteryIssueService.findAllKindOf();
	}
}
