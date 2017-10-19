package com.caiqianyi.agent.account.rest;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.caiqianyi.agent.account.service.IGuessTopicService;
import com.caiqianyi.commons.exception.SuccessMessage;
import com.caiqianyi.guess.entity.GuessTopic;
import com.caiqianyi.guess.entity.GuessTopicOption;
import com.caiqianyi.soa.web.framework.model.Pager;

@RestController
public class GuessTopicController {

	@Resource
	private IGuessTopicService guessTopicService;
	
	/**
	 * 查询竞猜详情
	 * @param topicId 竞猜ID
	 * @return
	 */
	@RequestMapping(value="/guess/topic/findBy/{topicId}/",method=RequestMethod.GET)
	GuessTopic findOneGuessTopicByTopicId(@PathVariable("topicId")Integer topicId){
		return guessTopicService.findOneGuessTopicByTopicId(topicId);
	}
	
	@RequestMapping(value="/guess/topic/findBy/optionId/{optionId}/",method=RequestMethod.GET)
	GuessTopic findOneGuessTopicByOptionId(@PathVariable("optionId")String optionId){
		return guessTopicService.findOneGuessTopicByOptionId(optionId);
	}
	
	@RequestMapping(value="/guess/topic/findBy/pager/roomId/{roomId}/",method=RequestMethod.POST)
	Pager findGuessTopicByRoomIdForPager(@PathVariable("roomId")Integer roomId,
			@RequestParam(value="status",required=false)Integer status,
			@RequestParam(value="start",required=false)Date start, 
			@RequestParam(value="end",required=false)Date end,
			@RequestBody(required=false) Pager pager){
		return guessTopicService.findGuessTopicByRoomIdForPager(roomId, status, start, end, pager);
	}
	
	@RequestMapping(value="/guess/topic/findBy/pager/{kind}/{league}/{groupId}/",method=RequestMethod.POST)
	Pager findGuessTopicByForPager(@PathVariable("kind")String kind,
			@PathVariable("league")String league,
			@PathVariable("groupId")String groupId,
			@RequestParam(value="status",required=false)Integer status,
			@RequestParam(value="start",required=false)Date start, 
			@RequestParam(value="end",required=false)Date end,
			@RequestBody(required=false) Pager pager){
		return guessTopicService.findGuessTopicByForPager(kind, league, groupId, status, start, end, pager);
	}
	
	@RequestMapping(value="/guess/topic/insert/",method=RequestMethod.POST)
	SuccessMessage insert(@RequestBody GuessTopic topic){
		return new SuccessMessage(guessTopicService.insert(topic));
	}
	
	@RequestMapping(value="/guess/topic/update/",method=RequestMethod.POST)
	SuccessMessage update(@RequestBody GuessTopic topic){
		return new SuccessMessage(guessTopicService.update(topic));
	}
	
	@RequestMapping(value="/guess/topic/delete/{id}",method=RequestMethod.GET)
	SuccessMessage delete(@PathVariable("id")String id){
		return new SuccessMessage(guessTopicService.delete(id));
	}
	
	@RequestMapping(value="/guess/topic/option/findBy/topicId/{topicId}",method=RequestMethod.GET)
	List<GuessTopicOption> findGuessTopicOptionByTopicId(@PathVariable("topicId")Integer topicId){
		return guessTopicService.findGuessTopicOptionByTopicId(topicId);
	}
	
	@RequestMapping(value="/guess/topic/option/insert/",method=RequestMethod.POST)
	SuccessMessage insertOption(@RequestBody GuessTopicOption option){
		return new SuccessMessage(guessTopicService.insertOption(option));
	}
	
	@RequestMapping(value="/guess/topic/option/update/",method=RequestMethod.POST)
	SuccessMessage updateOption(@RequestBody GuessTopicOption option){
		return new SuccessMessage(guessTopicService.updateOption(option));
	}
	
	@RequestMapping(value="/guess/topic/option/delete/{id}",method=RequestMethod.GET)
	SuccessMessage deleteOption(@PathVariable("id")String id){
		return new SuccessMessage(guessTopicService.deleteOption(id));
	}
	
	@RequestMapping(value="/guess/topic/option/deleteBy/{topicId}",method=RequestMethod.GET)
	SuccessMessage deleteOptionByTopicId(@PathVariable("topicId")Integer topicId){
		return new SuccessMessage(guessTopicService.deleteOptionByTopicId(topicId));
	}
}
