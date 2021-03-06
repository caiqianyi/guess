package com.lebaoxun.guess.core.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.lebaoxun.commons.pager.Pager;
import com.lebaoxun.guess.entity.GuessTopic;
import com.lebaoxun.guess.entity.GuessTopicOption;


@Mapper
public interface IGuessTopicMapper {
	
	/**
	 * 查询竞猜详情
	 * @param topicId 竞猜ID
	 * @return
	 */
	GuessTopic findOneGuessTopicBy(@Param("topic")GuessTopic topic);
	
	List<GuessTopic> findGuessTopicByForPager(@Param("topic")GuessTopic topic,
			@Param("start")Date start,
			@Param("end")Date end,Pager pager);
	
	List<GuessTopic> findAllGuessTopicBy(@Param("topic")GuessTopic topic,
			@Param("start")Date start,
			@Param("end")Date end);
	
	List<GuessTopic> findAllGuessTopicLeftOptionsBy(@Param("topic")GuessTopic topic,
			@Param("start")Date start,
			@Param("end")Date end);
	
	List<GuessTopic> findCurrentTopicsLeftOptionsBy(@Param("clubId") Integer clubId, @Param("topicType") String topicType); 
	
	int insert(@Param("topic")GuessTopic topic);
	
	int update(@Param("topic")GuessTopic topic); 
	
	int delete(@Param("id")String id);
	
	GuessTopicOption findOneGuessTopicOptionBy(@Param("option")GuessTopicOption option);
	
	List<GuessTopicOption> findAllGuessTopicOptionBy(@Param("option")GuessTopicOption option);
	
	int insertOption(@Param("option")GuessTopicOption option);
	
	int updateOption(@Param("option")GuessTopicOption option);
	
	int deleteOption(@Param("id")String id);
	
	int deleteOptionByTopicId(@Param("topicId")Integer topicId);
}
