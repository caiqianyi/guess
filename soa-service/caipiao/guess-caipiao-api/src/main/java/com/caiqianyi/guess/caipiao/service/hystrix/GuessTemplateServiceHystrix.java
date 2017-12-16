package com.caiqianyi.guess.caipiao.service.hystrix;

import com.caiqianyi.commons.exception.SuccessMessage;
import com.caiqianyi.guess.caipiao.service.IGuessTemplateService;
import com.caiqianyi.guess.entity.GuessTemplate;

public class GuessTemplateServiceHystrix implements IGuessTemplateService {

	@Override
	public SuccessMessage findByUserId(Integer userId, String kindOf,
			String topicType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SuccessMessage findByClubId(Integer userId, Integer clubId, String kindOf,
			String topicType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SuccessMessage deleteBy(Integer id, Integer userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SuccessMessage create(GuessTemplate template) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SuccessMessage update(GuessTemplate template) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SuccessMessage copyTemplateToClub(Integer userId,Integer[] templateId,
			Integer clubId) {
		// TODO Auto-generated method stub
		return null;
	}

}
