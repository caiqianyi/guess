package com.qianyi.guess.caipiao.service.gaopin.impl;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.caiqianyi.guess.entity.LotteryIssue;
import com.caiqianyi.guess.entity.LotteryNum;
import com.google.gson.Gson;
import com.qianyi.guess.caipiao.config.K3Cat;
import com.qianyi.guess.caipiao.service.gaopin.IK3LotteryService;
import com.qianyi.guess.caipiao.support.AbstractLotteryServiceSupport;

@Service
public class K3LotteryServiceImpl extends AbstractLotteryServiceSupport
		implements IK3LotteryService {
	
	private Logger logger = LoggerFactory.getLogger(K3LotteryServiceImpl.class);

	private K3Cat cat;
	
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
		IK3LotteryService k3LotteryService = new K3LotteryServiceImpl();
		k3LotteryService.setCatId(K3Cat.AHK3);
		k3LotteryService.getIssueForToday();
		k3LotteryService.getCurrentIssue();
		k3LotteryService.captureNewestNum();
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
	public void setCatId(K3Cat cat) {
		// TODO Auto-generated method stub
		this.cat = cat;
	}

}
