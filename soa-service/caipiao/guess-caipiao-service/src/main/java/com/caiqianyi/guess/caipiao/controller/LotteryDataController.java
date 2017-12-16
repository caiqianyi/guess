package com.caiqianyi.guess.caipiao.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.time.DateFormatUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.caiqianyi.commons.exception.SuccessMessage;
import com.caiqianyi.guess.caipiao.service.ILotteryCatService;
import com.caiqianyi.guess.core.dao.GuessTemplateMapper;
import com.caiqianyi.guess.entity.GuessTemplate;
import com.caiqianyi.soa.core.redis.IRedisCache;

@RestController
public class LotteryDataController {
	
	@Resource
	private ILotteryCatService lotteryCatService;
	
	@Resource
	private IRedisCache redisCache;
	
	@RequestMapping(value="/lottery/getCurrentIssue/{kindOf}/",method=RequestMethod.GET)
	SuccessMessage getCurrentIssue(@PathVariable("kindOf")String kindOf){
		return new SuccessMessage(lotteryCatService.getLotteryService(kindOf).getCurrentIssue());
	}
	
	@RequestMapping(value="/yllr/data/{kindOf}/",method=RequestMethod.GET)
	SuccessMessage yllr(@PathVariable("kindOf")String kindOf){
		return new SuccessMessage(redisCache.get("lottery:yllr:"+kindOf));
	}
	
	@RequestMapping(value="/yllr/data/{kindOf}/{zu}/",method=RequestMethod.GET)
	SuccessMessage yllr(@PathVariable("kindOf")String kindOf,
			@PathVariable("zu")String zu){
		List<Map<String, Object>> datas = (List<Map<String, Object>>)redisCache.get("lottery:yllr:"+kindOf);
		List<Map<String, Object>> result = new ArrayList<Map<String,Object>>();
		for(Map<String, Object> data : datas){
			Map<String, Object> item = new LinkedHashMap<String, Object>();
			item.put("expect", data.get("expect"));
			item.put("lottery", data.get("lottery"));
			item.put(zu, data.get(zu));
			result.add(item);
		}
		return new SuccessMessage(result);
	}
	
	@RequestMapping(value="/yllr/data/200/{kindOf}/",method=RequestMethod.GET)
	SuccessMessage yllr200(@PathVariable("kindOf")String kindOf){
		return new SuccessMessage(redisCache.get("lottery:yllr:"+kindOf+":200"));
	}
	
	@RequestMapping(value="/jclq/{league}/{matchId}",method=RequestMethod.GET)
	SuccessMessage jclq(@PathVariable("league")String league,@PathVariable("matchId")String matchId){
		return new SuccessMessage(redisCache.get("lottery:jclq:"+league+":"+DateFormatUtils.format(new Date(), "yyyyMMdd")+"|"+matchId));
	}
	
	@RequestMapping(value="/yllr/data/200/{kindOf}/{zu}/",method=RequestMethod.GET)
	SuccessMessage yllr200(@PathVariable("kindOf")String kindOf,
			@PathVariable("zu")String zu){
		List<Map<String, Object>> datas = (List<Map<String, Object>>)redisCache.get("lottery:yllr:"+kindOf+":200");
		List<Map<String, Object>> result = new ArrayList<Map<String,Object>>();
		for(Map<String, Object> data : datas){
			Map<String, Object> item = new LinkedHashMap<String, Object>();
			item.put("expect", data.get("expect"));
			item.put("lottery", data.get("lottery"));
			item.put(zu, data.get(zu));
			result.add(item);
		}
		return new SuccessMessage(result);
	}
	
	
}
