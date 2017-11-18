package com.caiqianyi.guess.caipiao.service.gaopin.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.caiqianyi.guess.caipiao.core.dao.ILotteryIssueMapper;
import com.caiqianyi.guess.caipiao.entity.LotteryIssue;
import com.caiqianyi.guess.caipiao.service.gaopin.IBjxcLotteryService;
import com.caiqianyi.guess.caipiao.support.AbstractLotteryServiceSupport;
import com.google.gson.Gson;

@Service
public class BjxcLotteryServiceImpl extends AbstractLotteryServiceSupport
		implements IBjxcLotteryService {

	private Logger logger = LoggerFactory.getLogger(BjxcLotteryServiceImpl.class);
	
	@Resource
	private ILotteryIssueMapper lotteryIssueMapper;
	private String kindOf = "bjsc";
	
	@Override
	public LotteryIssue getCurrentIssue() {
		// TODO Auto-generated method stub
		return lotteryIssueMapper.getCurrentIssue(kindOf);
	}
	
	@Override
	public List<LotteryIssue> getIssueForToday() {
		// TODO Auto-generated method stub
		return getIssueByDay(DateFormatUtils.format(new Date(), "yyyyMMdd"));
	}
	
	@Override
	public List<LotteryIssue> getIssueByDay(String day) {
		try {
			return getLotteryIssueByTimeAndKindOf(kindOf,day, 5 * 60,
					"09:02:00", "23:57:00",
					DateUtils.parseDate("2017-11-13 09:07:00", new String[]{"yyyy-MM-dd HH:mm:ss"}),
					650453l);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ArrayList<LotteryIssue>();
	}


	@Override
	public LotteryIssue findLotteryNumByIssue(String issue) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<LotteryIssue> captureNewestNum() {
		// TODO Auto-generated method stub
		try {
			String url = String.format("http://kaijiang.500.com/static/info/kaijiang/xml/bjpkshi/%s.xml?_A=%s",
					DateFormatUtils.format(new Date(), "yyyyMMdd"),
					"" + System.currentTimeMillis());
			return kaijiang500(url);
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	public static void main(String[] args) {
		BjxcLotteryServiceImpl bls = new BjxcLotteryServiceImpl();
		bls.captureNewestNum();
		bls.getIssueForToday();
	}
}
