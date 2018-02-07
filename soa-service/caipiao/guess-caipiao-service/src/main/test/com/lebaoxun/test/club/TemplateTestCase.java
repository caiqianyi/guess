package com.lebaoxun.test.club;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.lebaoxun.GuessCaipiaoApplication;
import com.lebaoxun.guess.caipiao.service.IGuessTemplateService;
import com.lebaoxun.guess.entity.GuessTemplate;
import com.lebaoxun.guess.entity.GuessTemplateOption;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = GuessCaipiaoApplication.class)// 指定spring-boot的启动类
public class TemplateTestCase {

	@Resource
	private IGuessTemplateService guessTemplateService;
	
	private Integer clubId=787072,userId=88731584;
	
	private String kindOf = "bjpk10";
	
	public void findByUserId(
			@PathVariable("userId") Integer userId,
			@RequestParam(value = "kindOf", required = false) String kindOf,
			@RequestParam(value = "topicType", required = false) String topicType) {
		guessTemplateService.findByUserId(userId,
				kindOf, topicType);
	}

	public void findByClubId(
			@PathVariable("userId") Integer userId,
			@PathVariable("clubId") Integer clubId,
			@RequestParam(value = "kindOf", required = false) String kindOf,
			@RequestParam(value = "topicType", required = false) String topicType) {
		guessTemplateService.findByClubId(clubId,
				kindOf, topicType);
	}

	public void deleteBy(@PathVariable("id") Integer id,
			@PathVariable("userId") Integer userId) {
		guessTemplateService.deleteBy(id, userId);
	}

	@Test
	public void create() {
		
		GuessTemplate template = new GuessTemplate();
		template.setClubId(clubId);
		template.setKindOf(kindOf);
		template.setOrderBy(0);
		template.setSubject("本期哪辆车将会是冠军？");
		template.setTopicType("guan");
		template.setUserId(userId);
		
		List<GuessTemplateOption> options = new ArrayList<GuessTemplateOption>();
		for(int i=1;i<11;i++){
			GuessTemplateOption option = new GuessTemplateOption();
			String che = (i>10?"":"0")+i;
			option.setFormula("#N1="+i);
			option.setName(che);
			option.setOdds(new BigDecimal(5));
			option.setOrderBy(i);
			options.add(option);
		}
		template.setOptions(options);
		guessTemplateService.create(template);
	}
	@Test
	public void createSf() {
		
		GuessTemplate template = new GuessTemplate();
		template.setClubId(259589);
		template.setKindOf("jclq");
		template.setOrderBy(0);
		template.setSubject("本场比赛谁将获得最终胜利？");
		template.setTopicType("sf");
		template.setUserId(userId);
		template.setStatus(0);
		
		List<GuessTemplateOption> options = new ArrayList<GuessTemplateOption>();
		
		GuessTemplateOption option1 = new GuessTemplateOption();
		option1.setFormula("#N1>#N2");
		option1.setName("#HOST");
		option1.setOdds(new BigDecimal(5));
		option1.setOrderBy(0);
		options.add(option1);
		
		GuessTemplateOption option2 = new GuessTemplateOption();
		option2.setFormula("#N1<#N2");
		option2.setName("#GUEST");
		option2.setOdds(new BigDecimal(5));
		option2.setOrderBy(1);
		options.add(option2);
		template.setOptions(options);
		guessTemplateService.create(template);
	}

	public void update(GuessTemplate template) {
		guessTemplateService.update(template);
	}
	
	@Test
	public void enabled(){
		guessTemplateService.enabled(3, userId);
	}
	
	@Test
	public void disable(){
		guessTemplateService.disable(1, userId);
	}

	public void copyTemplateToClub(@PathVariable("userId") Integer userId,
			@PathVariable("templateId") Integer[] templateId,
			@PathVariable("clubId") Integer clubId) {
		guessTemplateService.copyTemplateToClub(
				templateId, clubId);
	}
}
