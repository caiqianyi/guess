package com.caiqianyi.guess.caipiao.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.caiqianyi.commons.exception.SuccessMessage;
import com.caiqianyi.commons.pager.Pager;
import com.caiqianyi.guess.entity.GuessOrder;
import com.caiqianyi.guess.service.IGuessOrderService;

@RestController
public class GuessOrderController {
	
	@Resource
	private IGuessOrderService guessOrderService;
	
	@RequestMapping(value="/guess/order/findBy/{orderNo}/",method=RequestMethod.GET)
	GuessOrder findByOrderNo(@PathVariable("orderNo")String orderNo){
		return guessOrderService.findByOrderNo(orderNo);
	}
	
	@RequestMapping(value="/guess/order/findById/{id}/",method=RequestMethod.GET)
	GuessOrder findById(@PathVariable("id")String id){
		return guessOrderService.findById(id);
	}
	
	@RequestMapping(value="/guess/order/findAllBy/{userId}/",method=RequestMethod.POST)
	List<GuessOrder> findAllBy(@PathVariable("userId")Integer userId,
			@RequestParam(value="clubId",required=false)Integer clubId,
			@RequestParam(value="status",required=false)Integer status,
			@RequestParam(value="topicId",required=false)Integer topicId,
			@RequestParam(value="kindOf",required=false)String kindOf,
			@RequestParam(value="expect",required=false)String expect){
		return guessOrderService.findAllBy(userId, clubId, status, topicId, kindOf, expect);
	}
	
	@RequestMapping(value="/guess/order/findByForPager/",method=RequestMethod.POST)
	Pager findByForPager(@RequestParam(value="userId",required=false)Integer userId,
			@RequestParam(value="clubId",required=false)Integer clubId,
			@RequestParam(value="status",required=false)Integer status,
			@RequestParam(value="topicId",required=false)Integer topicId,
			@RequestParam(value="kindOf",required=false)String kindOf,
			@RequestParam(value="start",required=false)String start, 
			@RequestParam(value="end",required=false)String end, 
			@RequestBody(required=false) Pager pager){
		return guessOrderService.findByForPager(userId, clubId, status, topicId, kindOf, start, end, pager);
	}
	
	@RequestMapping(value="/guess/order/update/",method=RequestMethod.POST)
	SuccessMessage update(@RequestBody GuessOrder order){
		return new SuccessMessage(guessOrderService.update(order));
	}
	
	@RequestMapping(value="/guess/order/delete/{id}",method=RequestMethod.GET)
	SuccessMessage delete(@PathVariable("id")String id){
		return new SuccessMessage(guessOrderService.delete(id));
	}
	
	@RequestMapping(value="/guess/order/delete/{orderNo}",method=RequestMethod.GET)
	SuccessMessage deleteByOrderNo(@PathVariable("orderNo")String orderNo){
		return new SuccessMessage(guessOrderService.deleteByOrderNo(orderNo));
	}
	
	@RequestMapping(value="/guess/order/joinGuess/",method=RequestMethod.GET)
	SuccessMessage joinGuess(@RequestParam(value="userId") Integer userId,
			@RequestParam(value="optionId") String optionId,
			@RequestParam(value="diamond") Integer diamond){
		return new SuccessMessage(guessOrderService.joinGuess(userId, optionId, diamond));
	}
	@RequestMapping(value="/guess/backBonus",method=RequestMethod.GET)
	SuccessMessage backBonus(){
		guessOrderService.backBonus();
		return new SuccessMessage();
	}
}
