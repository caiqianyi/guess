package com.lebaoxun.guess.caipiao.service;

import java.util.List;

import com.lebaoxun.commons.pager.Pager;
import com.lebaoxun.guess.entity.GuessOrder;

public interface IGuessOrderService {

	GuessOrder findByOrderNo(String orderNo);

	GuessOrder findById(String id);

	List<GuessOrder> findAllBy(Integer memberId, Integer clubId, Integer status,
			Integer topicId, String kindOf, String expect);

	Pager findByForPager(Integer memberId, Integer clubId, Integer status,
			Integer topicId, String kindOf, String start,
			String end, Pager pager);

	List<GuessOrder> joinGuess(Integer userId, String optionId[], Integer diamond);
	
	GuessOrder joinGuess(Integer memberId, String optionId, Integer diamond);

	boolean update(GuessOrder order);

	boolean delete(String id);

	boolean deleteByOrderNo(String orderNo);

	void backBonus();

}