package com.caiqianyi.agent.account.service;

import java.util.Date;

import com.caiqianyi.guess.entity.GuessOrder;
import com.caiqianyi.soa.web.framework.model.Pager;

public interface IGuessOrderService {

	GuessOrder findByOrderNo(String orderNo);
	
	GuessOrder findById(String id);
	
	Pager findByUserIdForPager(String userId,
			Integer status,Integer topicId,
			Date start, Date end, Pager pager);
	
	boolean insert(GuessOrder order);
	
	boolean update(GuessOrder order);
	
	boolean delete(String  id);
	
	boolean deleteByOrderNo(String orderNo);
	
	void backBonus();
	
}