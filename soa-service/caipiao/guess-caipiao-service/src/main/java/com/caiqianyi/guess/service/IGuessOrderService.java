package com.caiqianyi.guess.service;

import java.util.Date;

import com.caiqianyi.commons.pager.Pager;
import com.caiqianyi.guess.entity.GuessOrder;

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