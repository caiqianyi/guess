package com.caiqianyi.agent.rest;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.caiqianyi.commons.exception.SuccessMessage;
import com.caiqianyi.soa.core.redis.IRedisCache;

@RestController
public class LotteryController {
	
	@Resource
	private IRedisCache redisCache;
	
	@RequestMapping(value="/lottery/yl/bjpk10",method=RequestMethod.GET)
	SuccessMessage bjpk10yl(){
		return new SuccessMessage(redisCache.get("lottery:yl:bjpk10:200"));
	}
	
	@RequestMapping(value="/lottery/lr/bjpk10",method=RequestMethod.GET)
	SuccessMessage bjpk10lr(){
		return new SuccessMessage(redisCache.get("lottery:lr:bjpk10"));
	}
}
