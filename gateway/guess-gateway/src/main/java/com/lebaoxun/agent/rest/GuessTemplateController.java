package com.lebaoxun.agent.rest;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lebaoxun.account.entity.User;
import com.lebaoxun.agent.security.Oauth2SecuritySubject;
import com.lebaoxun.commons.exception.I18nMessageException;
import com.lebaoxun.commons.exception.SuccessMessage;
import com.lebaoxun.commons.utils.FormulaCalculate;
import com.lebaoxun.guess.caipiao.service.IGuessTemplateService;
import com.lebaoxun.guess.entity.GuessTemplate;

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
	@RequestMapping(value="/guess/template/findByUserId",method=RequestMethod.GET)
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
	@RequestMapping(value="/guess/template/findByClubId/{clubId}",method=RequestMethod.GET)
	SuccessMessage findByClubId(@PathVariable("clubId") Integer clubId,
			@RequestParam(value = "kindOf",required = false) String kindOf,
			@RequestParam(value = "topicType",required = false) String topicType){
		User user = oauth2SecuritySubject.getCurrentUser();
		return guessTemplateService.findByClubId(user.getUserId(), clubId, kindOf, topicType);
	}
	
	@RequestMapping(value = "/guess/template/findById/{id}", method = RequestMethod.GET)
	SuccessMessage findById(@PathVariable("id") Integer id){
		User user = oauth2SecuritySubject.getCurrentUser();
		return guessTemplateService.findById(id, user.getUserId());
	}
	
	/**
	 * 删除竞猜话题模板
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/guess/template/deleteBy/{id}",method=RequestMethod.GET)
	SuccessMessage deleteBy(@PathVariable("id") Integer id){
		User user = oauth2SecuritySubject.getCurrentUser();
		return guessTemplateService.deleteBy(id, user.getUserId());
	}
	
	/**
	 * 创建竞猜话题模板
	 * @param template
	 * @return
	 */
	@RequestMapping(value="/guess/template/create",method=RequestMethod.POST)
	SuccessMessage create(GuessTemplate template){
		User user = oauth2SecuritySubject.getCurrentUser();
		template.setUserId(user.getUserId());
		return guessTemplateService.create(template);
	}
	
	/**
	 * 修改竞猜话题模板
	 * @param template
	 * @return
	 */
	@RequestMapping(value="/guess/template/update",method=RequestMethod.POST)
	SuccessMessage update(GuessTemplate template){
		User user = oauth2SecuritySubject.getCurrentUser();
		template.setUserId(user.getUserId());
		return guessTemplateService.update(template);
	}
	
	/**
	 * 启用禁用模板
	 * @param id 模板ID
	 * @param flag 0=禁用，1=启用
	 * @return
	 */
	@RequestMapping(value="/guess/template/enabled/{id}",method=RequestMethod.POST)
	SuccessMessage enabled(@PathVariable("id") Integer id,
			@RequestParam(value = "flag") int flag){
		User user = oauth2SecuritySubject.getCurrentUser();
		if(flag == 0){
			return guessTemplateService.disable(id, user.getUserId());
		}
		return guessTemplateService.enabled(id, user.getUserId());
	}
	
	/**
	 * 复制竞猜模板到俱乐部并创建
	 * @param templateId
	 * @param clubId
	 * @return
	 */
	@RequestMapping(value="/guess/template/copyTemplateToRoom/{clubId}/{templateId}",method=RequestMethod.GET)
	SuccessMessage copyTemplateToClub(@PathVariable("templateId") Integer[] templateId,
			@PathVariable("clubId") Integer clubId){
		User user = oauth2SecuritySubject.getCurrentUser();
		return guessTemplateService.copyTemplateToClub(user.getUserId(), templateId, clubId);
	}
	
	/**
	 * 检查选项公式是否可执行
	 * @param formula
	 * @return
	 */
	@RequestMapping(value="/guess/template/checkFormula/bjpk10",method=RequestMethod.GET)
	SuccessMessage checkFormulaForBjpk10(String formula){
		try{
			return new SuccessMessage(FormulaCalculate.check(new String[] { "01", "02", "03", "04", "05", "06", "07", "08",
					"09", "10" }, formula));
		}catch(Exception e){
			throw new I18nMessageException("20102","选项公式错误,无法计算结果");
		}
	}
	
}
