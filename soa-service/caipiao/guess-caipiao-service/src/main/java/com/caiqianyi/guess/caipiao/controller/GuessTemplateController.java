package com.caiqianyi.guess.caipiao.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.caiqianyi.commons.exception.SuccessMessage;
import com.caiqianyi.guess.caipiao.service.IGuessTemplateService;
import com.caiqianyi.guess.entity.GuessTemplate;

@RestController
public class GuessTemplateController {

	@Resource
	private IGuessTemplateService guessTemplateService;

	@RequestMapping(value = "/guess/template/findByUserId/{userId}/", method = RequestMethod.GET)
	SuccessMessage findByUserId(
			@PathVariable("userId") Integer userId,
			@RequestParam(value = "kindOf", required = false) String kindOf,
			@RequestParam(value = "topicType", required = false) String topicType) {
		return new SuccessMessage(guessTemplateService.findByUserId(userId,
				kindOf, topicType));
	}

	@RequestMapping(value = "/guess/template/findByClubId/{userId}/{clubId}/", method = RequestMethod.GET)
	SuccessMessage findByClubId(
			@PathVariable("userId") Integer userId,
			@PathVariable("clubId") Integer clubId,
			@RequestParam(value = "kindOf", required = false) String kindOf,
			@RequestParam(value = "topicType", required = false) String topicType) {
		return new SuccessMessage(guessTemplateService.findByClubId(clubId,
				kindOf, topicType));
	}

	@RequestMapping(value = "/guess/template/deleteBy/{userId}/{id}/", method = RequestMethod.GET)
	SuccessMessage deleteBy(@PathVariable("id") Integer id,
			@PathVariable("userId") Integer userId) {
		return new SuccessMessage(guessTemplateService.deleteBy(id, userId));
	}

	@RequestMapping(value = "/guess/template/create/", method = RequestMethod.POST)
	SuccessMessage create(@RequestBody GuessTemplate template) {
		return new SuccessMessage(guessTemplateService.create(template));
	}

	@RequestMapping(value = "/guess/template/update/", method = RequestMethod.POST)
	SuccessMessage update(@RequestBody GuessTemplate template) {
		return new SuccessMessage(guessTemplateService.update(template));
	}
	
	@RequestMapping(value = "/guess/template/enabled/{userId}/{id}/", method = RequestMethod.GET)
	SuccessMessage enabled(@PathVariable("id") Integer id,@PathVariable("userId") Integer userId) {
		return new SuccessMessage(guessTemplateService.enabled(id, userId));
	}
	@RequestMapping(value = "/guess/template/disable/{userId}/{id}/", method = RequestMethod.GET)
	SuccessMessage disable(@PathVariable("id") Integer id,@PathVariable("userId") Integer userId) {
		return new SuccessMessage(guessTemplateService.disable(id, userId));
	}

	@RequestMapping(value = "/guess/template/copyTemplateToClub/{userId}/{clubId}/{templateId}/", method = RequestMethod.GET)
	SuccessMessage copyTemplateToClub(@PathVariable("userId") Integer userId,
			@PathVariable("templateId") Integer[] templateId,
			@PathVariable("clubId") Integer clubId) {
		return new SuccessMessage(guessTemplateService.copyTemplateToClub(
				templateId, clubId));
	}
}
