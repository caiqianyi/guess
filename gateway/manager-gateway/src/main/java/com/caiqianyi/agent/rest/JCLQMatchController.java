package com.caiqianyi.agent.rest;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.caiqianyi.commons.exception.SuccessMessage;
import com.caiqianyi.guess.caipiao.service.IJCLQMatchService;

@RestController
public class JCLQMatchController {
	
	@Resource
	private IJCLQMatchService jclqMatchService;
	
	@RequestMapping(value = "/jclq/guess/{seq}", method = RequestMethod.GET)
	SuccessMessage findGuessBySeq(@PathVariable("seq") String seq){
		return jclqMatchService.findGuessBySeq(seq);
	}
	@RequestMapping(value = "/jclq/guess/day/{day}", method = RequestMethod.GET)
	SuccessMessage findGuessByDay(@PathVariable("day") String day){
		return jclqMatchService.findAllMatchTopicByDay(day);
	}

	@RequestMapping(value = "/jclq/match/syncData/", method = RequestMethod.GET)
	SuccessMessage syncMatchJCLQForToday(){
		return jclqMatchService.syncMatchJCLQForToday();
	}

	@RequestMapping(value = "/jclq/match/syncData/{start}/{end}/", method = RequestMethod.GET)
	SuccessMessage syncMatchJCLQ(@PathVariable("start") String start,
			@PathVariable("end") String end){
		return jclqMatchService.syncMatchJCLQ(start, end);
	}

	@RequestMapping(value = "/jclq/data/{seq}", method = RequestMethod.GET)
	SuccessMessage findData(@PathVariable("seq") String seq){
		return jclqMatchService.findData(seq);
	}

	@RequestMapping(value = "/jclq/match/day/{day}", method = RequestMethod.GET)
	SuccessMessage findMatchByDay(@PathVariable("day") String day){
		return jclqMatchService.findMatchByDay(null, day, null);
	}
	
	@RequestMapping(value = "/jclq/match/league/{league}/{day}", method = RequestMethod.GET)
	SuccessMessage findMatchByDay(@PathVariable("league") String league,
			@PathVariable("day") String day,
			@RequestParam(value="status",required=false) String status){
		return jclqMatchService.findMatchByDay(league, day, status);
	}

	@RequestMapping(value = "/jclq/match/{league}/{start}|{end}", method = RequestMethod.GET)
	SuccessMessage findMatchByTime(@PathVariable("league") String league,
			@PathVariable("start") String start, @PathVariable("end") String end, 
			@RequestParam(value="size",required=false) Integer size, 
			@RequestParam(value="offset",required=false) Integer offset){
		return jclqMatchService.findMatchByTime(league, start, end, size, offset);
	}

	@RequestMapping(value = "/jclq/match/{seq}", method = RequestMethod.GET)
	SuccessMessage findMatchBySeq(@PathVariable("seq") String seq){
		return jclqMatchService.findMatchBySeq(seq);
	}
}
