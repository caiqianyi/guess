package com.caiqianyi.guess.core.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.caiqianyi.guess.entity.GuessTemplate;

@Mapper
public interface GuessTemplateMapper {
	/**
	 * This method was generated by MyBatis Generator. This method corresponds
	 * to the database table guess_template
	 *
	 * @mbggenerated
	 */
	int deleteByPrimaryKey(@Param("id") Integer id,
			@Param("userId") Integer userId);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds
	 * to the database table guess_template
	 *
	 * @mbggenerated
	 */
	int insert(GuessTemplate record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds
	 * to the database table guess_template
	 *
	 * @mbggenerated
	 */
	int insertSelective(GuessTemplate record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds
	 * to the database table guess_template
	 *
	 * @mbggenerated
	 */
	GuessTemplate selectByPrimaryKey(Integer id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds
	 * to the database table guess_template
	 *
	 * @mbggenerated
	 */
	int updateByPrimaryKeySelective(GuessTemplate record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds
	 * to the database table guess_template
	 *
	 * @mbggenerated
	 */
	int updateByPrimaryKey(GuessTemplate record);

	List<GuessTemplate> findAllByWhere(@Param("kindOf") String kindOf,
			@Param("topicType") String topicType,
			@Param("clubId") Integer clubId,
			@Param("userId") Integer userId);
	
	List<GuessTemplate> findByTemplates(@Param("ids")Integer[] ids);

}