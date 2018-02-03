package com.caiqianyi.guess.caipiao.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.caiqianyi.guess.caipiao.entity.LotteryIssue;
import com.caiqianyi.guess.caipiao.service.IssueService;

@RestController
public class IssueController {
	@Resource
	private IssueService issueService;
	
	@RequestMapping(method=RequestMethod.GET,value="/issues/{kindOf}/crruent")
	LotteryIssue getCurrentIssue(@PathVariable("kindOf")String kindOf){
		return issueService.getCurrentIssue(kindOf);
	}
}
