package com.caiqianyi.test.club;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.caiqianyi.GuessCaipiaoApplication;
import com.caiqianyi.guess.caipiao.service.IGuessOrderService;
import com.caiqianyi.guess.caipiao.service.IGuessTopicService;
import com.caiqianyi.guess.entity.GuessTopic;
import com.caiqianyi.guess.entity.GuessTopicOption;
import com.google.gson.Gson;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = GuessCaipiaoApplication.class)// 指定spring-boot的启动类
public class TopicTestCase {
	
	private Logger logger = LoggerFactory.getLogger(TopicTestCase.class);
	@Resource
	private IGuessTopicService guessTopicService;
	
	@Resource
	private IGuessOrderService guessOrderService;
	
	private Integer clubId = 787072,memberId = 6;
	
	@Test
	public void findCurrentTopicsLeftOptionsBy(){
		List<GuessTopic> topics = guessTopicService.findCurrentTopicsLeftOptionsBy(clubId, null);
		logger.debug("topics={}",new Gson().toJson(topics));
		for(GuessTopic topic : topics){
			for(GuessTopicOption option : topic.getOptions()){
				guessOrderService.joinGuess(memberId, new String[]{option.getId()}, 1);
			}
		}
	}
}
