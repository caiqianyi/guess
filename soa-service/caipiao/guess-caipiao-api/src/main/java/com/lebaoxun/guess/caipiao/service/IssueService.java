package com.lebaoxun.guess.caipiao.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lebaoxun.commons.exception.SuccessMessage;
import com.lebaoxun.guess.caipiao.entity.LotteryIssue;
import com.lebaoxun.guess.caipiao.service.hystrix.IssueServiceHystrix;

@FeignClient(value="guess-caipiao-service",fallback=IssueServiceHystrix.class)
public interface IssueService {
	
	@RequestMapping(method=RequestMethod.GET,value="/issues/{kindOf}/crruent")
	LotteryIssue getCurrentIssue(@PathVariable("kindOf")String kindOf);
}
