package com.caiqianyi.guess.caipiao.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.caiqianyi.commons.exception.SuccessMessage;
import com.caiqianyi.guess.caipiao.service.hystrix.LotteryIssueServiceHystrix;

@FeignClient(value="guess-caipiao-service",fallback=LotteryIssueServiceHystrix.class)
public interface ILotteryIssueService {
	
	@RequestMapping(value="/issue/day/{kindOf}/{day}/",method=RequestMethod.GET)
	SuccessMessage findIssueByDay(@PathVariable("kindOf") String kindOf,@PathVariable("day") String day);
	
	@RequestMapping(value="/issue/findAllKindOf/",method=RequestMethod.GET)
	SuccessMessage findAllKindOf();
	
}
