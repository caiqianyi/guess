package com.lebaoxun.test;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.google.gson.Gson;
import com.lebaoxun.GuessJobConsumerApplication;
import com.lebaoxun.guess.caipiao.core.dao.ILotteryIssueMapper;
import com.lebaoxun.guess.caipiao.entity.LotteryIssue;
import com.lebaoxun.guess.caipiao.lryl.YllrAnalysis;
import com.lebaoxun.guess.caipiao.service.ILotteryCatService;
import com.lebaoxun.guess.caipiao.service.ILotteryDataSyncService;
import com.lebaoxun.guess.caipiao.service.ILotteryService;
import com.lebaoxun.soa.core.redis.IRedisCache;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = GuessJobConsumerApplication.class)// 指定spring-boot的启动类
public class YllrTestCase {
	
	private Logger logger = LoggerFactory.getLogger(YllrTestCase.class);
	@Resource
	private ILotteryDataSyncService lotteryDataSyncService;
	
	@Resource
	private ILotteryIssueMapper lotteryIssueMapper;
	
	@Resource
	private ILotteryCatService lotteryCatService;
	
	@Resource
	private IRedisCache redisCache;
	
	@Test
	public void syncYllrData(){
		
		String  kindOf = "bjpk10";
		ILotteryService lotteryService = lotteryCatService.getLotteryService(kindOf);
		
		List<LotteryIssue> list = lotteryService.getLotteryNumBy(2);
		
		
		Map<String,Object> _500datas = new YllrAnalysis(kindOf,list,lotteryService.getLottery(),null).doAnalysis();
		
		logger.debug("list={}",new Gson().toJson(list));
		logger.debug("_500datas={}",new Gson().toJson(_500datas));
	}
	
	@Test
	public void syncJCLQMatch(){
		lotteryDataSyncService.syncJCLQMatch("2017-12-28", "2017-12-29");
	}
}
