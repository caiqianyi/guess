package com.caiqianyi.guess.caipiao.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.caiqianyi.commons.exception.SuccessMessage;
import com.caiqianyi.guess.caipiao.service.hystrix.LotteryDataServiceHystrix;

@FeignClient(value="guess-caipiao-service",fallback=LotteryDataServiceHystrix.class)
public interface ILotteryDataService {
	
	@RequestMapping(value="/lottery/syncData/{kindOf}/week",method=RequestMethod.GET)
	SuccessMessage syncIssueforWeek(@PathVariable("kindOf")String kindOf);
	
	@RequestMapping(value="/lottery/syncData/{kindOf}/openCode/{day}/",method=RequestMethod.GET)
	SuccessMessage syncOpenCodeForToday(@PathVariable("kindOf")String kindOf,
			@PathVariable("day")String day);
}
