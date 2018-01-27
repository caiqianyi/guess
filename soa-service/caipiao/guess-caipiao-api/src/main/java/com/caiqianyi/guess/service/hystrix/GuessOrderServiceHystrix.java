package com.caiqianyi.guess.service.hystrix;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.caiqianyi.commons.exception.I18nMessageException;
import com.caiqianyi.commons.exception.SuccessMessage;
import com.caiqianyi.commons.pager.Pager;
import com.caiqianyi.guess.entity.GuessOrder;
import com.caiqianyi.guess.service.IGuessOrderService;

@Component
public class GuessOrderServiceHystrix implements IGuessOrderService {
	
	private Logger logger = LoggerFactory.getLogger(GuessOrderServiceHystrix.class);

	@Override
	public GuessOrder findByOrderNo(String orderNo) {
		throw new I18nMessageException("502");
	}

	@Override
	public GuessOrder findById(String id) {
		throw new I18nMessageException("502");
	}
	
	@Override
	public List<GuessOrder> findAllBy(Integer userId, Integer clubId,
			Integer status, Integer topicId, String kindOf, String expect) {
		throw new I18nMessageException("502");
	}

	@Override
	public Pager findByForPager(Integer userId, Integer clubId, Integer status,
			Integer topicId, String kindOf, String start,
			String end, Pager pager) {
		throw new I18nMessageException("502");
	}

	@Override
	public SuccessMessage joinGuess(Integer userId, String optionId,
			Integer diamond) {
		throw new I18nMessageException("502");
	}
	
	@Override
	public SuccessMessage update(GuessOrder order) {
		throw new I18nMessageException("502");
	}

	@Override
	public SuccessMessage delete(String id) {
		throw new I18nMessageException("502");
	}

	@Override
	public SuccessMessage deleteByOrderNo(String orderNo) {
		throw new I18nMessageException("502");
	}

	@Override
	public SuccessMessage backBonus() {
		throw new I18nMessageException("502");
	}

}
