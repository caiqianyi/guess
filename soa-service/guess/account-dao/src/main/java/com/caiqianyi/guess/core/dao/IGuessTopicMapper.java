package com.caiqianyi.guess.core.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.caiqianyi.guess.entity.GuessTopic;
import com.caiqianyi.guess.entity.GuessTopicOption;
import com.caiqianyi.soa.web.framework.model.Pager;


public interface IGuessTopicMapper {
	
	/**
	 * 查询竞猜详情
	 * @param topicId 竞猜ID
	 * @return
	 */
	GuessTopic findOneGuessTopicByTopicId(@Param("topicId")Integer topicId);
	
	GuessTopic findOneGuessTopicByOptionId(@Param("result")String result);
	
	List<GuessTopic> findGuessTopicByRoomId(@Param("roomId")Integer roomId,
			@Param("status")Integer status,@Param("start")Date start,
			@Param("end")Date end,Pager pager);
	
	List<GuessTopic> findGuessTopicByForPager(@Param("kind")String kind,
			@Param("league")String league,@Param("groupId")String groupId,
			@Param("status")Integer status,@Param("start")Date start,
			@Param("end")Date end,Pager pager);
	
	int insert(GuessTopic topic);
	
	int update(GuessTopic topic);
	
	int delete(@Param("id")String id);
	
	List<GuessTopicOption> findGuessTopicOptionByTopicId(@Param("topicId")Integer topicId);
	
	int insertOption(GuessTopicOption option);
	
	int updateOption(GuessTopicOption option);
	
	int deleteOption(@Param("id")String id);
	
	int deleteOptionByTopicId(@Param("topicId")String topicId);
}
