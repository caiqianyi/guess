package com.lebaoxun.guess.caipiao.service.hystrix;

import org.springframework.stereotype.Component;

import com.lebaoxun.guess.caipiao.entity.LotteryIssue;
import com.lebaoxun.guess.caipiao.service.IssueService;

@Component
public class IssueServiceHystrix implements IssueService {

	@Override
	public LotteryIssue getCurrentIssue(String kindOf) {
		// TODO Auto-generated method stub
		return null;
	}
}
