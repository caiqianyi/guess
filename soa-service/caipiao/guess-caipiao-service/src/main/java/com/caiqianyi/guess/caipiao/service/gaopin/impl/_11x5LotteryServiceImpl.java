package com.caiqianyi.guess.caipiao.service.gaopin.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.caiqianyi.guess.caipiao.config._11x5Cat;
import com.caiqianyi.guess.caipiao.core.dao.ILotteryIssueMapper;
import com.caiqianyi.guess.caipiao.entity.LotteryIssue;
import com.caiqianyi.guess.caipiao.service.gaopin.I11x5LotteryService;
import com.caiqianyi.guess.caipiao.support.AbstractLotteryServiceSupport;

@Service("11x5LotteryService")
public class _11x5LotteryServiceImpl extends AbstractLotteryServiceSupport
		implements I11x5LotteryService {
	
	private _11x5Cat cat;

	private Logger logger = LoggerFactory
			.getLogger(_11x5LotteryServiceImpl.class);
	
	@Resource
	private ILotteryIssueMapper lotteryIssueMapper;

	@Override
	public List<LotteryIssue> getIssueForToday() {
		return getIssueByDay(DateFormatUtils.format(new Date(), "yyyyMMdd"));
	}

	@Override
	public List<LotteryIssue> getIssueByDay(String day) {
		try {
			return getLotteryIssueByTimeAndKindOf(cat.getCatId(), day, cat.getPeriod() * 60, cat.getStart(),
					cat.getEnd());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ArrayList<LotteryIssue>();
	}

	public static void main(String[] args) {
		I11x5LotteryService _11x5LotteryService = new _11x5LotteryServiceImpl();
		_11x5LotteryService.setCatId(_11x5Cat.GD11X5);
		_11x5LotteryService.getIssueForToday();
		_11x5LotteryService.captureNewestNum();
	}

	@Override
	public LotteryIssue findLotteryNumByIssue(String issue) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<LotteryIssue> captureNewestNum() {
		// TODO Auto-generated method stub
		String url = String
				.format("http://kaijiang.500.com/static/info/kaijiang/xml/%s/%s.xml?_A=%s",
						cat.getCatId(),DateFormatUtils.format(new Date(), "yyyyMMdd"), ""
								+ System.currentTimeMillis());
		return kaijiang500(url);
	}

	@Override
	public void setCatId(_11x5Cat cat) {
		// TODO Auto-generated method stub
		this.cat = cat;
	}

	@Override
	public LotteryIssue getCurrentIssue() {
		// TODO Auto-generated method stub
		return lotteryIssueMapper.getCurrentIssue(cat.getCatId());
	}
}
