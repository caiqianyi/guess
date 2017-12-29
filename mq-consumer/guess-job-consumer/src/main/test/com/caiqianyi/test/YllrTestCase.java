package com.caiqianyi.test;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.caiqianyi.GuessJobConsumerApplication;
import com.caiqianyi.guess.caipiao.service.ILotteryDataSyncService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = GuessJobConsumerApplication.class)// 指定spring-boot的启动类
public class YllrTestCase {
	@Resource
	private ILotteryDataSyncService lotteryDataSyncService;
	
	@Test
	public void syncYllrData(){
		lotteryDataSyncService.syncYllrData("bjpk10");
	}
	
	@Test
	public void syncJCLQMatch(){
		lotteryDataSyncService.syncJCLQMatch("2017-12-28", "2017-12-29");
	}
}
