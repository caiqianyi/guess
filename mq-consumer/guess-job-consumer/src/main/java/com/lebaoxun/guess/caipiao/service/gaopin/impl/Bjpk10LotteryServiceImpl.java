package com.lebaoxun.guess.caipiao.service.gaopin.impl;

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

import com.google.gson.Gson;
import com.lebaoxun.guess.caipiao.core.dao.ILotteryIssueMapper;
import com.lebaoxun.guess.caipiao.entity.LotteryIssue;
import com.lebaoxun.guess.caipiao.service.AbstractLotteryServiceSupport;
import com.lebaoxun.guess.caipiao.service.ILotteryService;

@Service("bjpk10LotteryService")
public class Bjpk10LotteryServiceImpl extends AbstractLotteryServiceSupport
		implements ILotteryService {

	private Logger logger = LoggerFactory
			.getLogger(Bjpk10LotteryServiceImpl.class);

	@Resource
	private ILotteryIssueMapper lotteryIssueMapper;
	private String kindOf = "bjpk10";

	@Override
	public List<LotteryIssue> getIssueForToday() {
		// TODO Auto-generated method stub
		return getIssueByDay(DateFormatUtils.format(new Date(), "yyyyMMdd"));
	}

	@Override
	public List<LotteryIssue> getIssueByDay(String day) {
		try {
			return getLotteryIssueByTimeAndKindOf(kindOf, day, 5 * 60,
					"09:02:00", "23:57:00", DateUtils.parseDate(
							"2017-11-13 09:07:00",
							new String[] { "yyyy-MM-dd HH:mm:ss" }), 650453l);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ArrayList<LotteryIssue>();
	}

	@Override
	public List<LotteryIssue> getOpencode(String day) {
		return kaijiang(day,kindOf);
	}

	@Override
	public void setKindOf(String kindOf) {
		// TODO Auto-generated method stub
		this.kindOf = kindOf;
	}

	@Override
	protected String getKindOf() {
		return kindOf;
	}

	@Override
	public String[] getLottery() {
		return new String[] { "01", "02", "03", "04", "05", "06", "07", "08",
				"09", "10" };
	}

	public static void main(String[] args) {
		Bjpk10LotteryServiceImpl bls = new Bjpk10LotteryServiceImpl();
		bls.setKindOf("bjpk10");
		List<LotteryIssue> issues = bls.getIssueByDay("20171212");
		System.out.println(new Gson().toJson(issues.get(0)));
	}

}
