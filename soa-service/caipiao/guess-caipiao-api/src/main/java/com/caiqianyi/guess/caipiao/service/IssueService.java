package com.caiqianyi.guess.caipiao.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.caiqianyi.commons.exception.SuccessMessage;
import com.caiqianyi.guess.caipiao.entity.LotteryIssue;
import com.caiqianyi.guess.caipiao.service.hystrix.IssueServiceHystrix;

@FeignClient(value="guess-caipiao-service",fallback=IssueServiceHystrix.class)
public interface IssueService {
	
	@RequestMapping(method=RequestMethod.GET,value="/issues/{kindOf}/crruent")
	LotteryIssue getCurrentIssue(@PathVariable("kindOf")String kindOf);
}
