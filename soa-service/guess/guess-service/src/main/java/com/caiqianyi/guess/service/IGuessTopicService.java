package com.caiqianyi.guess.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.caiqianyi.commons.pager.Pager;
import com.caiqianyi.guess.entity.GuessTopic;
import com.caiqianyi.guess.entity.GuessTopicOption;

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
			String league, String groupId,
			Integer status, Integer orderBy,
			Date start, Date end,Pager pager);
	
	List<GuessTopic> findGuessTopicBy(String kind,
			String league, String groupId,
			Integer status, Integer orderBy,
			Date start, Date end);
	
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
	 * @return
	 */
	void calOdds();
	
	/**
	 * 计算奖金 核心算法
	 * @param bas 选项支持总数
	 * @return
	 */
	BigDecimal[] calOdds(Integer topicId);
	
	/**
	 * 查询所有进行中的竞猜话题，用当前时间与话题结束时间比对。
	 * 当结束时间大于当前时间，则修改竞猜话题状态为未开奖（竞猜已结束）
	 * @return
	 */
	int finishGuess();
	
	/**
	 * 同步话题结果 
	 * @param kind 话题类型 
	 */
	void syncTopicOption(String kind);
}
