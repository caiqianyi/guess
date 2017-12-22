package com.caiqianyi.guess.service;

import java.util.Date;
import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.caiqianyi.commons.exception.SuccessMessage;
import com.caiqianyi.commons.pager.Pager;
import com.caiqianyi.guess.entity.GuessTopic;
import com.caiqianyi.guess.entity.GuessTopicOption;
import com.caiqianyi.guess.service.hystrix.GuessTopicServiceHystrix;

@FeignClient(value="guess-caipiao-service",fallback=GuessTopicServiceHystrix.class)
public interface IGuessTopicService {
	/**
	 * 查询竞猜详情
	 * @param topicId 竞猜ID
	 * @return
	 */
	@RequestMapping(value="/guess/topic/findBy/{topicId}/",method=RequestMethod.GET)
	GuessTopic findOneGuessTopicByTopicId(@PathVariable("topicId")Integer topicId);
	
	@RequestMapping(value="/guess/topic/findBy/optionId/{optionId}/",method=RequestMethod.GET)
	GuessTopic findOneGuessTopicByOptionId(@PathVariable("optionId")String optionId);
	
	@RequestMapping(value="/guess/topic/findBy/pager/ClubId/{ClubId}/",method=RequestMethod.POST)
	Pager findGuessTopicByClubIdForPager(@PathVariable("ClubId")Integer ClubId,
			@RequestParam(value="status",required=false)Integer status,
			@RequestParam(value="start",required=false)Date start, 
			@RequestParam(value="end",required=false)Date end,
			@RequestBody(required=false) Pager pager);
	
	@RequestMapping(value="/guess/topic/findBy/pager/{kind}/{league}/{groupId}/",method=RequestMethod.POST)
	Pager findGuessTopicByForPager(@PathVariable("kind")String kind,
			@PathVariable("league")String league,
			@PathVariable("groupId")String groupId,
			@RequestParam(value="status",required=false)Integer status,
			@RequestParam(value="orderBy",required=false)Integer orderBy,
			@RequestParam(value="start",required=false)Date start, 
			@RequestParam(value="end",required=false)Date end,
			@RequestBody(required=false) Pager pager);
	
	@RequestMapping(value="/guess/topic/findBy/current/{clubId}/",method=RequestMethod.POST)
	SuccessMessage findCurrentTopicsLeftOptionsBy(@PathVariable("clubId")Integer clubId,
			@RequestParam(value="topicType",required=false) String topicType);
	
	@RequestMapping(value="/guess/topic/insert/",method=RequestMethod.POST)
	SuccessMessage insert(@RequestBody GuessTopic topic);
	
	@RequestMapping(value="/guess/topic/update/",method=RequestMethod.POST)
	SuccessMessage update(@RequestBody GuessTopic topic);
	
	@RequestMapping(value="/guess/topic/delete/{id}",method=RequestMethod.GET)
	SuccessMessage delete(@PathVariable("id")String id);
	
	@RequestMapping(value="/guess/topic/option/findBy/topicId/{topicId}",method=RequestMethod.GET)
	List<GuessTopicOption> findGuessTopicOptionByTopicId(@PathVariable("topicId")Integer topicId);
	
	@RequestMapping(value="/guess/topic/option/insert/",method=RequestMethod.POST)
	SuccessMessage insertOption(@RequestBody GuessTopicOption option);
	
	@RequestMapping(value="/guess/topic/option/update/",method=RequestMethod.POST)
	SuccessMessage updateOption(@RequestBody GuessTopicOption option);
	
	@RequestMapping(value="/guess/topic/option/delete/{id}",method=RequestMethod.GET)
	SuccessMessage deleteOption(@PathVariable("id")String id);
	
	@RequestMapping(value="/guess/topic/option/deleteBy/{topicId}",method=RequestMethod.GET)
	SuccessMessage deleteOptionByTopicId(@PathVariable("topicId")Integer topicId);
	
	/**
	 * 计算奖金 核心算法
	 * @return
	 */
	@RequestMapping(value="/guess/calOdds/",method=RequestMethod.GET)
	SuccessMessage calOdds(@RequestParam(value="topicId",required=false)Integer topicId);

	/**
	 * 查询所有进行中的竞猜话题，用当前时间与话题结束时间比对。
	 * 当结束时间大于当前时间，则修改竞猜话题状态为未开奖（竞猜已结束）
	 * @return
	 */
	@RequestMapping(value="/guess/finishGuess/",method=RequestMethod.GET)
	SuccessMessage finishGuess();
	
	/**
	 * 同步话题结果 
	 * @param kind 话题类型 
	 */
	@RequestMapping(value="/guess/syncTopicOption/{kind}/",method=RequestMethod.GET)
	SuccessMessage syncTopicOption(@PathVariable("kind")String kind);
}
