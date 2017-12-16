package com.caiqianyi.agent.rest;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.caiqianyi.account.entity.User;
import com.caiqianyi.agent.security.Oauth2SecuritySubject;
import com.caiqianyi.commons.exception.SuccessMessage;
import com.caiqianyi.guess.caipiao.service.IGuessTemplateService;
import com.caiqianyi.guess.entity.GuessTemplate;

@RestController
public class GuessTemplateController {
	
	@Resource
	private IGuessTemplateService guessTemplateService;
	
	@Resource
	private Oauth2SecuritySubject oauth2SecuritySubject;
	
	/**
	 * 获取我的竞猜话题模板
	 * @param kindOf 彩种
	 * @param topicType 话题类型
	 * @return
	 */
	@RequestMapping(value="/guess/template/findByUserId/",method=RequestMethod.GET)
	SuccessMessage findByUserId(@RequestParam(value = "kindOf",required = false) String kindOf,
			@RequestParam(value = "topicType",required = false) String topicType){
		User user = oauth2SecuritySubject.getCurrentUser();
		return guessTemplateService.findByUserId(user.getUserId(), kindOf, topicType);
	}
	
	/**
	 * 获取俱乐部的竞猜话题模板
	 * @param clubId 俱乐部ID
	 * @param kindOf 彩种
	 * @param topicType 话题类型
	 * @return
	 */
	@RequestMapping(value="/guess/template/findByClubId/{clubId}/",method=RequestMethod.GET)
	SuccessMessage findByClubId(@PathVariable("clubId") Integer clubId,
			@RequestParam(value = "kindOf",required = false) String kindOf,
			@RequestParam(value = "topicType",required = false) String topicType){
		User user = oauth2SecuritySubject.getCurrentUser();
		return new SuccessMessage(guessTemplateService.findByClubId(user.getUserId(), clubId, kindOf, topicType));
	}
	
	/**
	 * 删除竞猜话题模板
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/guess/template/deleteBy/{id}/",method=RequestMethod.GET)
	SuccessMessage deleteBy(@PathVariable("id") Integer id){
		User user = oauth2SecuritySubject.getCurrentUser();
		return new SuccessMessage(guessTemplateService.deleteBy(id, user.getUserId()));
	}
	
	/**
	 * 创建竞猜话题模板
	 * @param template
	 * @return
	 */
	@RequestMapping(value="/guess/template/create/",method=RequestMethod.POST)
	SuccessMessage create(GuessTemplate template){
		User user = oauth2SecuritySubject.getCurrentUser();
		template.setUserId(user.getUserId());
		return new SuccessMessage(guessTemplateService.create(template));
	}
	
	/**
	 * 修改竞猜话题模板
	 * @param template
	 * @return
	 */
	@RequestMapping(value="/guess/template/update/",method=RequestMethod.POST)
	SuccessMessage update(GuessTemplate template){
		return new SuccessMessage(guessTemplateService.update(template));
	}
	
	/**
	 * 复制竞猜模板到俱乐部并创建
	 * @param templateId
	 * @param clubId
	 * @return
	 */
	@RequestMapping(value="/guess/template/copyTemplateToRoom/{clubId}/{templateId}/",method=RequestMethod.GET)
	SuccessMessage copyTemplateToClub(@PathVariable("templateId") Integer[] templateId,
			@PathVariable("clubId") Integer clubId){
		User user = oauth2SecuritySubject.getCurrentUser();
		return new SuccessMessage(guessTemplateService.copyTemplateToClub(user.getUserId(), templateId, clubId));
	}
	
}
