package com.caiqianyi.guess.caipiao.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.caiqianyi.commons.exception.SuccessMessage;
import com.caiqianyi.commons.pager.Pager;
import com.caiqianyi.guess.caipiao.service.ILotteryDataSyncService;
import com.caiqianyi.guess.core.dao.IGuessTopicMapper;
import com.caiqianyi.guess.entity.GuessTopic;
import com.caiqianyi.guess.jclq.match.service.IJCLQMatchService;
import com.caiqianyi.guess.jclq.vo.JCLQMatchGuessTopic;
import com.caiqianyi.soa.core.redis.IRedisCache;

@RestController
public class JCLQMatchController {

	@Resource
	private ILotteryDataSyncService lotteryDataSyncService;

	@Resource
	private IGuessTopicMapper guessTopicMapper;

	@Resource
	private IRedisCache redisCache;

	@Resource
	private IJCLQMatchService jclqMatchService;

	@RequestMapping(value = "/jclq/guess/{seq}/", method = RequestMethod.GET)
	SuccessMessage findGuessBySeq(@PathVariable("seq") String seq) {
		GuessTopic queryTopic = new GuessTopic();
		queryTopic.setKind("jclq");
		queryTopic.setGroupId(seq);
		return new SuccessMessage(guessTopicMapper.findAllGuessTopicLeftOptionsBy(queryTopic,
				null, null));
	}
	
	@RequestMapping(value = "/jclq/guess/day/{day}/", method = RequestMethod.GET)
	SuccessMessage findAllMatchTopicByDay(@PathVariable("day") String day){
		return new SuccessMessage(jclqMatchService.findAllMatchTopicByDay(day));
	}

	@RequestMapping(value = "/jclq/match/syncData/", method = RequestMethod.GET)
	SuccessMessage syncMatchJCLQForToday() {
		return new SuccessMessage(lotteryDataSyncService.syncJCLQMatch());
	}

	@RequestMapping(value = "/jclq/match/syncData/{start}/{end}/", method = RequestMethod.GET)
	SuccessMessage syncMatchJCLQ(@PathVariable("start") String start,
			@PathVariable("end") String end) {
		return new SuccessMessage(lotteryDataSyncService.syncJCLQMatch(start,
				end));
	}

	@RequestMapping(value = "/jclq/data/{seq}/", method = RequestMethod.GET)
	SuccessMessage findData(@PathVariable("seq") String seq) {
		return new SuccessMessage(redisCache.get("lottery:jclq:" + seq));
	}

	@RequestMapping(value = "/jclq/match/day/{day}/", method = RequestMethod.GET)
	SuccessMessage findMatchByDay(@RequestParam(value="league",required=false) String league,
			@PathVariable("day") String day,
			@RequestParam(value="status",required=false) String status) {
		return new SuccessMessage(jclqMatchService.findMatchByDay(league, day,
				status));
	}

	@RequestMapping(value = "/jclq/match/{league}/{start}/{end}/", method = RequestMethod.GET)
	SuccessMessage findMatchByTime(@PathVariable("league") String league,
			@PathVariable("start") String start, @PathVariable("end") String end, 
			@RequestParam(value="size",required=false) Integer size, 
			@RequestParam(value="offset",required=false) Integer offset) {
		return new SuccessMessage(jclqMatchService.findMatchByTime(league,
				start, end, new Pager(size,offset)));
	}

	@RequestMapping(value = "/jclq/match/{seq}/", method = RequestMethod.GET)
	SuccessMessage findMatchBySeq(@PathVariable("seq") String seq){
		return new SuccessMessage(jclqMatchService.findMatchBySeq(seq));
	}
}
