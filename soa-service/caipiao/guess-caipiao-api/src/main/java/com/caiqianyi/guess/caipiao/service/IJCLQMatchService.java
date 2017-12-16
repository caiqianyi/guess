package com.caiqianyi.guess.caipiao.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.caiqianyi.commons.exception.SuccessMessage;
import com.caiqianyi.guess.caipiao.service.hystrix.JCLQMatchServiceHystrix;

@FeignClient(value="guess-caipiao-service",fallback=JCLQMatchServiceHystrix.class)
public interface IJCLQMatchService {
	
	@RequestMapping(value = "/jclq/guess/{seq}/", method = RequestMethod.GET)
	SuccessMessage findGuessBySeq(@PathVariable("seq") String seq);
	
	@RequestMapping(value = "/jclq/guess/day/{day}/", method = RequestMethod.GET)
	SuccessMessage findAllMatchTopicByDay(@PathVariable("day") String day);

	@RequestMapping(value = "/jclq/match/syncData/", method = RequestMethod.GET)
	SuccessMessage syncMatchJCLQForToday();

	@RequestMapping(value = "/jclq/match/syncData/{start}/{end}/", method = RequestMethod.GET)
	SuccessMessage syncMatchJCLQ(@PathVariable("start") String start,
			@PathVariable("end") String end);

	@RequestMapping(value = "/jclq/data/{seq}/", method = RequestMethod.GET)
	SuccessMessage findData(@PathVariable("seq") String seq);

	@RequestMapping(value = "/jclq/match/day/{day}/", method = RequestMethod.GET)
	SuccessMessage findMatchByDay(@RequestParam(value="league",required=false) String league,
			@PathVariable("day") String day,
			@RequestParam(value="status",required=false) String status);

	@RequestMapping(value = "/jclq/match/{league}/{start}/{end}/", method = RequestMethod.GET)
	SuccessMessage findMatchByTime(@PathVariable("league") String league,
			@PathVariable("start") String start, @PathVariable("end") String end, 
			@RequestParam(value="size",required=false) Integer size, 
			@RequestParam(value="offset",required=false) Integer offset);

	@RequestMapping(value = "/jclq/match/{seq}/", method = RequestMethod.GET)
	SuccessMessage findMatchBySeq(@PathVariable("seq") String seq);
}
