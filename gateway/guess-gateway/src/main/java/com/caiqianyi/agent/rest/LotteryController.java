package com.caiqianyi.agent.rest;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.caiqianyi.commons.exception.SuccessMessage;
import com.caiqianyi.soa.core.redis.IRedisCache;

@RestController
public class LotteryController {
	
	@Resource
	private IRedisCache redisCache;
	
	@RequestMapping(value="/lottery/yllr/{kindOf}/200",method=RequestMethod.GET)
	SuccessMessage bjpk10yl(@PathVariable("kindOf")String kindOf){
		return new SuccessMessage(redisCache.get("lottery:yllr:"+kindOf+":200"));
	}
	
	@RequestMapping(value="/lottery/yllr/{kindOf}",method=RequestMethod.GET)
	SuccessMessage bjpk10lr(@PathVariable("kindOf")String kindOf){
		return new SuccessMessage(redisCache.get("lottery:yllr:"+kindOf+""));
	}
}
