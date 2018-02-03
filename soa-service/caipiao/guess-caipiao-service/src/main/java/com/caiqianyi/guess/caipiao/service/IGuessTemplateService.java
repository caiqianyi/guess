package com.caiqianyi.guess.caipiao.service;

import java.util.List;

import com.caiqianyi.guess.entity.GuessTemplate;

public interface IGuessTemplateService {
	
	List<GuessTemplate> findByUserId(Integer userId,String kindOf,String topicType);
	
	List<GuessTemplate> findByClubId(Integer clubId,String kindOf,String topicType);
	
	GuessTemplate findBy(Integer id,Integer userId);
	
	int deleteBy(Integer id,Integer userId);
	
	GuessTemplate create(GuessTemplate template);
	
	GuessTemplate update(GuessTemplate template);
	
	int enabled(Integer id,Integer userId);
	
	int disable(Integer id,Integer userId);
	
	List<GuessTemplate> copyTemplateToClub(Integer[] templateId,Integer clubId);
}
