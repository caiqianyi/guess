package com.caiqianyi.guess.service;

import java.util.Date;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.caiqianyi.commons.exception.SuccessMessage;
import com.caiqianyi.commons.pager.Pager;
import com.caiqianyi.guess.entity.GuessOrder;
import com.caiqianyi.guess.service.hystrix.GuessOrderServiceHystrix;

@FeignClient(value="guess-caipiao-service",fallback=GuessOrderServiceHystrix.class)
public interface IGuessOrderService {
	
	@RequestMapping(value="/guess/order/findBy/{orderNo}/",method=RequestMethod.GET)
	GuessOrder findByOrderNo(@PathVariable("orderNo")String orderNo);
	
	@RequestMapping(value="/guess/order/findById/{id}/",method=RequestMethod.GET)
	GuessOrder findById(@PathVariable("id")String id);
	
	@RequestMapping(value="/guess/order/findByUserIdForPager/{userId}/",method=RequestMethod.POST)
	Pager findByUserIdForPager(@PathVariable("userId")String userId,
			@RequestParam(value="status",required=false)Integer status,
			@RequestParam(value="topicId",required=false)Integer topicId,
			@RequestParam(value="start",required=false)Date start, 
			@RequestParam(value="end",required=false)Date end, 
			@RequestBody(required=false) Pager pager);
	
	@RequestMapping(value="/guess/order/buyGuessOption/",method=RequestMethod.POST)
	SuccessMessage buyGuessOption(@RequestBody GuessOrder order);
	
	@RequestMapping(value="/guess/order/update/",method=RequestMethod.POST)
	SuccessMessage update(@RequestBody GuessOrder order);
	
	@RequestMapping(value="/guess/order/delete/{id}",method=RequestMethod.GET)
	SuccessMessage delete(@PathVariable("id")String id);
	
	@RequestMapping(value="/guess/order/delete/{orderNo}",method=RequestMethod.GET)
	SuccessMessage deleteByOrderNo(@PathVariable("orderNo")String orderNo);
	
	@RequestMapping(value="/guess/backBonus",method=RequestMethod.GET)
	SuccessMessage backBonus();
}
