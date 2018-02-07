package com.lebaoxun.guess.caipiao.service.hystrix;

import org.springframework.stereotype.Component;

import com.lebaoxun.commons.exception.SuccessMessage;
import com.lebaoxun.guess.caipiao.service.IJCLQMatchService;

@Component
public class JCLQMatchServiceHystrix implements IJCLQMatchService{

	@Override
	public SuccessMessage findAllMatchTopicByDay(String day) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public SuccessMessage findGuessBySeq(String seq) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SuccessMessage syncMatchJCLQForToday() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SuccessMessage syncMatchJCLQ(String start, String end) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SuccessMessage findData(String seq) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SuccessMessage findMatchByDay(String league, String day,
			String status) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SuccessMessage findMatchByTime(String league, String start,
			String end, Integer size, Integer offset) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SuccessMessage findMatchBySeq(String seq) {
		// TODO Auto-generated method stub
		return null;
	}

}
