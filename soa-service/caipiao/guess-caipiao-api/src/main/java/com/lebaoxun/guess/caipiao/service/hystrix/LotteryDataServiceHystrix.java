package com.lebaoxun.guess.caipiao.service.hystrix;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.lebaoxun.commons.exception.SuccessMessage;
import com.lebaoxun.guess.caipiao.service.ILotteryDataService;

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

}
