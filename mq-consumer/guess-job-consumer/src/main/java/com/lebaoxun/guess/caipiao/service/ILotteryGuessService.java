package com.lebaoxun.guess.caipiao.service;

import java.util.Date;
import java.util.List;

import com.lebaoxun.guess.entity.GuessTemplate;
import com.lebaoxun.guess.entity.GuessTopic;

public interface ILotteryGuessService {

	List<GuessTopic> createGuessTopic(String groupId,
			String league, Date overTime, List<GuessTemplate> templates);
	
	List<GuessTopic> updateTopicResult(String kind, String groupId, String lotterys[]);
	
	List<GuessTopic> createTopicByIssueForClub(String seq,int number,String kindOf,String groupId,Date start,Date end);
}
