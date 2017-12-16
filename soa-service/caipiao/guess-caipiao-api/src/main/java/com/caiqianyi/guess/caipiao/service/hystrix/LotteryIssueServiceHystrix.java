package com.caiqianyi.guess.caipiao.service.hystrix;

import org.springframework.stereotype.Component;

import com.caiqianyi.commons.exception.SuccessMessage;
import com.caiqianyi.guess.caipiao.service.ILotteryIssueService;

@Component
public class LotteryIssueServiceHystrix implements ILotteryIssueService {

	@Override
	public SuccessMessage findIssueByDay(String kindOf, String day) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SuccessMessage findAllKindOf() {
		// TODO Auto-generated method stub
		return null;
	}

}
