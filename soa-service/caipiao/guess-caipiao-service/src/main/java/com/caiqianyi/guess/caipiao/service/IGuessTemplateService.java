package com.caiqianyi.guess.caipiao.service;

import java.util.List;

import com.caiqianyi.guess.entity.GuessTemplate;

public interface IGuessTemplateService {
	
	List<GuessTemplate> findByUserId(Integer userId,String kindOf,String topicType);
	
	List<GuessTemplate> findByClubId(Integer clubId,String kindOf,String topicType);
	
	int deleteBy(Integer id,Integer userId);
	
	GuessTemplate create(GuessTemplate template);
	
	GuessTemplate update(GuessTemplate template);
	
	List<GuessTemplate> copyTemplateToClub(Integer[] templateId,Integer clubId);
}
