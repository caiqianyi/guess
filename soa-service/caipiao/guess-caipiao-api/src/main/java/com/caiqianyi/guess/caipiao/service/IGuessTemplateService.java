package com.caiqianyi.guess.caipiao.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.caiqianyi.commons.exception.SuccessMessage;
import com.caiqianyi.guess.caipiao.service.hystrix.GuessTemplateServiceHystrix;
import com.caiqianyi.guess.entity.GuessTemplate;

@FeignClient(value="guess-caipiao-service",fallback=GuessTemplateServiceHystrix.class)
public interface IGuessTemplateService {

	@RequestMapping(value="/guess/template/findByUserId/{userId}/",method=RequestMethod.GET)
	SuccessMessage findByUserId(@PathVariable("userId") Integer userId,
			@RequestParam(value = "kindOf",required = false) String kindOf,
			@RequestParam(value = "topicType",required = false) String topicType);
	
	@RequestMapping(value="/guess/template/findByClubId/{userId}/{clubId}/",method=RequestMethod.GET)
	SuccessMessage findByClubId(@PathVariable("userId") Integer userId,
			@PathVariable("clubId") Integer clubId,
			@RequestParam(value = "kindOf",required = false) String kindOf,
			@RequestParam(value = "topicType",required = false) String topicType);
	
	@RequestMapping(value="/guess/template/deleteBy/{userId}/{id}/",method=RequestMethod.GET)
	SuccessMessage deleteBy(@PathVariable("id") Integer id,
			@PathVariable("userId") Integer userId);
	
	@RequestMapping(value="/guess/template/create/",method=RequestMethod.POST)
	SuccessMessage create(@RequestBody GuessTemplate template);
	
	@RequestMapping(value="/guess/template/update/",method=RequestMethod.POST)
	SuccessMessage update(@RequestBody GuessTemplate template);
	
	@RequestMapping(value = "/guess/template/enabled/{userId}/{id}/", method = RequestMethod.GET)
	SuccessMessage enabled(@PathVariable("id") Integer id,@PathVariable("userId") Integer userId);
	
	@RequestMapping(value = "/guess/template/disable/{userId}/{id}/", method = RequestMethod.GET)
	SuccessMessage disable(@PathVariable("id") Integer id,@PathVariable("userId") Integer userId);
	
	@RequestMapping(value="/guess/template/copyTemplateToClub/{userId}/{clubId}/{templateId}/",method=RequestMethod.GET)
	SuccessMessage copyTemplateToClub(@PathVariable("userId") Integer userId,@PathVariable("templateId") Integer[] templateId,
			@PathVariable("clubId") Integer clubId);
}
