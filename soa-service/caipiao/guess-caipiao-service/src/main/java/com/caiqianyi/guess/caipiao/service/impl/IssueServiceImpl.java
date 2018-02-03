package com.caiqianyi.guess.caipiao.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.caiqianyi.guess.caipiao.core.dao.ILotteryIssueMapper;
import com.caiqianyi.guess.caipiao.entity.LotteryIssue;
import com.caiqianyi.guess.caipiao.service.IssueService;

@Service
public class IssueServiceImpl implements IssueService {
	@Resource
	private ILotteryIssueMapper lotteryIssueMapper;
	
	@Override
	public LotteryIssue getCurrentIssue(String kindOf){
		return lotteryIssueMapper.getCurrentIssue(kindOf);
	}
}
