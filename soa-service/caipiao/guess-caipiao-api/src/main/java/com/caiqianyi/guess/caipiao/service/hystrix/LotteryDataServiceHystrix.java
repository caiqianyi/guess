package com.caiqianyi.guess.caipiao.service.hystrix;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.caiqianyi.commons.exception.SuccessMessage;
import com.caiqianyi.guess.caipiao.service.ILotteryDataService;

@Component
public class LotteryDataServiceHystrix implements ILotteryDataService {
	
	private Logger logger = LoggerFactory.getLogger(LotteryDataServiceHystrix.class);

	@Override
	public SuccessMessage syncIssueforWeek(String kindOf) {
		return null;
	}

	@Override
	public SuccessMessage syncOpenCodeForToday(String kindOf, String day) {
		return null;
	}

	@Override
	public SuccessMessage syncMatchJCLQForToday() {
		// TODO Auto-generated method stub
		return null;
	}
}
