package com.caiqianyi.guess.service;

import java.util.List;

import com.caiqianyi.commons.pager.Pager;
import com.caiqianyi.guess.entity.GuessOrder;

public interface IGuessOrderService {

	GuessOrder findByOrderNo(String orderNo);

	GuessOrder findById(String id);

	List<GuessOrder> findAllBy(Integer userId, Integer clubId, Integer status,
			Integer topicId, String kindOf, String expect);

	Pager findByForPager(Integer userId, Integer clubId, Integer status,
			Integer topicId, String kindOf, String start,
			String end, Pager pager);

	GuessOrder joinGuess(Integer userId, String optionId, Integer diamond);

	boolean update(GuessOrder order);

	boolean delete(String id);

	boolean deleteByOrderNo(String orderNo);

	void backBonus();

}