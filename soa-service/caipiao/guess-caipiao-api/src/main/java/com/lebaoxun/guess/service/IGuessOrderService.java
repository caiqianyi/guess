package com.lebaoxun.guess.service;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.lebaoxun.commons.exception.SuccessMessage;
import com.lebaoxun.commons.pager.Pager;
import com.lebaoxun.guess.entity.GuessOrder;
import com.lebaoxun.guess.service.hystrix.GuessOrderServiceHystrix;

@FeignClient(value="guess-caipiao-service",fallback=GuessOrderServiceHystrix.class)
public interface IGuessOrderService {
	
	@RequestMapping(value="/guess/order/findBy/{orderNo}/",method=RequestMethod.GET)
	GuessOrder findByOrderNo(@PathVariable("orderNo")String orderNo);
	
	@RequestMapping(value="/guess/order/findById/{id}/",method=RequestMethod.GET)
	GuessOrder findById(@PathVariable("id")String id);
	
	@RequestMapping(value="/guess/order/findAllBy/{userId}/",method=RequestMethod.POST)
	List<GuessOrder> findAllBy(@PathVariable("userId")Integer userId,
			@RequestParam(value="clubId",required=false)Integer clubId,
			@RequestParam(value="status",required=false)Integer status,
			@RequestParam(value="topicId",required=false)Integer topicId,
			@RequestParam(value="kindOf",required=false)String kindOf,
			@RequestParam(value="expect",required=false)String expect);
	
	@RequestMapping(value="/guess/order/findByForPager/",method=RequestMethod.POST)
	Pager findByForPager(@RequestParam(value="userId",required=false)Integer userId,
			@RequestParam(value="clubId",required=false)Integer clubId,
			@RequestParam(value="status",required=false)Integer status,
			@RequestParam(value="topicId",required=false)Integer topicId,
			@RequestParam(value="kindOf",required=false)String kindOf,
			@RequestParam(value="start",required=false)String start, 
			@RequestParam(value="end",required=false)String end, 
			@RequestBody(required=false) Pager pager);
	
	@RequestMapping(value="/guess/order/joinGuess/",method=RequestMethod.GET)
	SuccessMessage joinGuess(@RequestParam(value="userId") Integer userId,
			@RequestParam(value="optionId") String[] optionId,
			@RequestParam(value="diamond") Integer diamond);
	
	@RequestMapping(value="/guess/order/update/",method=RequestMethod.POST)
	SuccessMessage update(@RequestBody GuessOrder order);
	
	@RequestMapping(value="/guess/order/delete/{id}",method=RequestMethod.GET)
	SuccessMessage delete(@PathVariable("id")String id);
	
	@RequestMapping(value="/guess/order/delete/{orderNo}",method=RequestMethod.GET)
	SuccessMessage deleteByOrderNo(@PathVariable("orderNo")String orderNo);
	
	@RequestMapping(value="/guess/backBonus",method=RequestMethod.GET)
	SuccessMessage backBonus();
}
