package com.caiqianyi.guess.service.hystrix;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.caiqianyi.commons.exception.SuccessMessage;
import com.caiqianyi.commons.pager.Pager;
import com.caiqianyi.guess.entity.GuessOrder;
import com.caiqianyi.guess.service.IGuessOrderService;

@Component
public class GuessOrderServiceHystrix implements IGuessOrderService {
	
	private Logger logger = LoggerFactory.getLogger(GuessOrderServiceHystrix.class);

	@Override
	public GuessOrder findByOrderNo(String orderNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GuessOrder findById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Pager findByUserIdForPager(String userId, Integer status,
			Integer topicId, Date start, Date end, Pager pager) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SuccessMessage buyGuessOption(GuessOrder order) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SuccessMessage update(GuessOrder order) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SuccessMessage delete(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SuccessMessage deleteByOrderNo(String orderNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SuccessMessage backBonus() {
		// TODO Auto-generated method stub
		return null;
	}

}
