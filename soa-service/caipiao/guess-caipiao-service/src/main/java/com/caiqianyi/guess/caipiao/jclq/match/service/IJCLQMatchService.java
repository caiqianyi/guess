package com.caiqianyi.guess.caipiao.jclq.match.service;

import java.util.List;

import com.caiqianyi.commons.pager.Pager;
import com.caiqianyi.guess.jclq.entity.JCLQMatch;
import com.caiqianyi.guess.jclq.vo.JCLQMatchGuessTopic;

public interface IJCLQMatchService {
	
	List<JCLQMatch> findMatchByDay(String league,String day,String status);
	
	List<JCLQMatchGuessTopic> findAllMatchTopicByDay(String day);
	
	Pager findMatchByTime(String league,String start,String end,Pager pager);
	
	JCLQMatch findMatchBySeq(String seq);
	
}
