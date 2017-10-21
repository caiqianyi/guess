package com.caiqianyi.agent.account.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.caiqianyi.guess.entity.GuessTopic;
import com.caiqianyi.guess.entity.GuessTopicOption;
import com.caiqianyi.soa.web.framework.model.Pager;

public interface IGuessTopicService {

	/**
	 * 查询竞猜详情
	 * @param topicId 竞猜ID
	 * @return
	 */
	GuessTopic findOneGuessTopicByTopicId(Integer topicId);
	
	GuessTopic findOneGuessTopicByOptionId(String optionId);
	
	Pager findGuessTopicByRoomIdForPager(Integer roomId,
			Integer status,Date start,
			Date end,Pager pager);
	
	Pager findGuessTopicByForPager(String kind,
			String league,String groupId,
			Integer status,Date start,
			Date end,Pager pager);
	
	boolean insert(GuessTopic topic);
	
	boolean update(GuessTopic topic); 
	
	boolean delete(String id);
	
	List<GuessTopicOption> findGuessTopicOptionByTopicId(Integer topicId);
	
	boolean insertOption(GuessTopicOption option);
	
	boolean updateOption(GuessTopicOption option);
	
	boolean deleteOption(String id);
	
	boolean deleteOptionByTopicId(Integer topicId);
	
	/**
	 * 计算奖金 核心算法
	 * @param bas 选项支持总数
	 * @return
	 */
	BigDecimal[] calOdds(Integer topicId);
	
}
