package com.caiqianyi.guess.caipiao.service.hystrix;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.caiqianyi.commons.exception.I18nMessageException;
import com.caiqianyi.commons.exception.SuccessMessage;
import com.caiqianyi.guess.caipiao.service.ILotteryDataService;

@Component
public class LotteryDataServiceHystrix implements ILotteryDataService {
	
	private Logger logger = LoggerFactory.getLogger(LotteryDataServiceHystrix.class);

	@Override
	public SuccessMessage syncIssueforWeek(String kindOf) {
		throw new I18nMessageException("502","服务器异常，请稍后重试");
	}

	@Override
	public SuccessMessage syncOpenCodeForToday(String kindOf, String day) {
		throw new I18nMessageException("502","服务器异常，请稍后重试");
	}

}
