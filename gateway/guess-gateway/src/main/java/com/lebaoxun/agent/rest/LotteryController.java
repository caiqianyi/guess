package com.lebaoxun.agent.rest;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lebaoxun.commons.exception.SuccessMessage;
import com.lebaoxun.guess.caipiao.entity.LotteryIssue;
import com.lebaoxun.guess.caipiao.service.IssueService;
import com.lebaoxun.soa.core.redis.IRedisCache;

@RestController
public class LotteryController {
	
	@Resource
	private IRedisCache redisCache;
	
	@Resource
	private IssueService issueService;
	
	@RequestMapping(value="/lottery/issue/{kindOf}",method=RequestMethod.GET)
	SuccessMessage issue(@PathVariable("kindOf")String kindOf){
		LotteryIssue issue = issueService.getCurrentIssue(kindOf);
		return new SuccessMessage(issue);
	}
	
	@RequestMapping(value="/lottery/yllr/{kindOf}/200",method=RequestMethod.GET)
	SuccessMessage bjpk10yl(@PathVariable("kindOf")String kindOf){
		return new SuccessMessage(redisCache.get("lottery:yllr:"+kindOf+":200"));
	}
	
	@RequestMapping(value="/lottery/yllr/{kindOf}",method=RequestMethod.GET)
	SuccessMessage bjpk10lr(@PathVariable("kindOf")String kindOf){
		return new SuccessMessage(redisCache.get("lottery:yllr:"+kindOf+""));
	}
}
