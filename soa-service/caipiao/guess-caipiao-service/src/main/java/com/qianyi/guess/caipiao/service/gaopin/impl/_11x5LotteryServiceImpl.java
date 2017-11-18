package com.qianyi.guess.caipiao.service.gaopin.impl;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.caiqianyi.guess.entity.LotteryIssue;
import com.caiqianyi.guess.entity.LotteryNum;
import com.google.gson.Gson;
import com.qianyi.guess.caipiao.config._11x5Cat;
import com.qianyi.guess.caipiao.service.gaopin.I11x5LotteryService;
import com.qianyi.guess.caipiao.support.AbstractLotteryServiceSupport;

public class _11x5LotteryServiceImpl extends AbstractLotteryServiceSupport
		implements I11x5LotteryService {
	
	private _11x5Cat cat;

	private Logger logger = LoggerFactory
			.getLogger(_11x5LotteryServiceImpl.class);

	@Override
	public List<LotteryIssue> getIssueForToday() {
		return getIssueByDay(DateFormatUtils.format(new Date(), "yyyyMMdd"));
	}

	@Override
	public List<LotteryIssue> getIssueByDay(String day) {
		try {
			return getLotteryIssueByTimeAndKindOf(day, cat.getPeriod() * 60, cat.getStart(),
					cat.getEnd());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) {
		I11x5LotteryService _11x5LotteryService = new _11x5LotteryServiceImpl();
		_11x5LotteryService.setCatId(_11x5Cat.JX11X5);
		_11x5LotteryService.getIssueForToday();
		_11x5LotteryService.getCurrentIssue();
		_11x5LotteryService.captureNewestNum();
	}

	@Override
	public LotteryIssue getCurrentIssue() {
		// TODO Auto-generated method stub

		Date now = new Date();
		List<LotteryIssue> issues = getIssueForToday();
		for (LotteryIssue issue : issues) {
			if (now.after(issue.getStartTime())
					&& now.before(issue.getEndTime())) {
				logger.debug("issue={}", new Gson().toJson(issue));
				return issue;
			}
		}
		return null;
	}

	@Override
	public LotteryIssue findLotteryNumByIssue(String issue) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<LotteryNum> captureNewestNum() {
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
}
