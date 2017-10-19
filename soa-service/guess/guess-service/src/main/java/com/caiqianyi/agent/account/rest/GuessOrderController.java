package com.caiqianyi.agent.account.rest;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.caiqianyi.agent.account.service.IGuessOrderService;
import com.caiqianyi.commons.exception.SuccessMessage;
import com.caiqianyi.guess.entity.GuessOrder;
import com.caiqianyi.soa.web.framework.model.Pager;

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
	
	@RequestMapping(value="/guess/order/findByUserIdForPager/{userId}/",method=RequestMethod.POST)
	Pager findByUserIdForPager(@PathVariable("userId")String userId,
			@RequestParam(value="status",required=false)Integer status,
			@RequestParam(value="topicId",required=false)Integer topicId,
			@RequestParam(value="start",required=false)Date start, 
			@RequestParam(value="end",required=false)Date end, 
			@RequestBody(required=false) Pager pager){
		return guessOrderService.findByUserIdForPager(userId, status, topicId, start, end, pager);
	}
	
	@RequestMapping(value="/guess/order/buyGuessOption/",method=RequestMethod.POST)
	SuccessMessage buyGuessOption(@RequestBody GuessOrder order){
		return new SuccessMessage(guessOrderService.insert(order));
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
}
