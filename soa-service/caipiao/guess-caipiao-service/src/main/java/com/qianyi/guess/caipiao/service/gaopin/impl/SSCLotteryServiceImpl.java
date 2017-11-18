package com.qianyi.guess.caipiao.service.gaopin.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.caiqianyi.guess.entity.LotteryIssue;
import com.caiqianyi.guess.entity.LotteryNum;
import com.google.gson.Gson;
import com.qianyi.guess.caipiao.config.SSCCat;
import com.qianyi.guess.caipiao.service.gaopin.ISSCLotteryService;
import com.qianyi.guess.caipiao.support.AbstractLotteryServiceSupport;

@Service
public class SSCLotteryServiceImpl extends AbstractLotteryServiceSupport
		implements ISSCLotteryService {

	private SSCCat cat;

	private Logger logger = LoggerFactory
			.getLogger(SSCLotteryServiceImpl.class);

	@Override
	public List<LotteryIssue> getIssueByDay(String day) {
		try {
			List<LotteryIssue> all = new ArrayList<LotteryIssue>();
			String[] times = cat.getTimes();
			for (int i = 0; i < times.length; i++) {
				String time[] = times[i].split("\\-");
				if (i == 0) {
					all.addAll(getLotteryIssueByTimeAndKindOf(day,
							cat.getPeriods()[i] * 60, time[0], time[1], 3));
					continue;
				}

				Date s = DateUtils.addMilliseconds(all.get(all.size() - 1)
						.getStartTime(), cat.getPeriods()[i] * 60 * 1000);
				List<LotteryIssue> list = getLotteryIssueByTimeAndKindOf(
						DateFormatUtils.format(new Date(), "yyyyMMdd"),
						cat.getPeriods()[i] * 60, time[0], time[1], 3, s,
						Long.parseLong(all.get(all.size() - 1).getIssue()) + 1);
				all.addAll(list);
			}
			logger.debug("issue.size={}", all.size());
			return all;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<LotteryIssue> getIssueForToday() {
		// TODO Auto-generated method stub
		return getIssueByDay(DateFormatUtils.format(new Date(), "yyyyMMdd"));
	}

	public static void main(String[] args) {
		ISSCLotteryService sscLotteryService = new SSCLotteryServiceImpl();
		sscLotteryService.setCatId(SSCCat.TJSSC);
		sscLotteryService.captureNewestNum();
		sscLotteryService.getCurrentIssue();
		sscLotteryService.getIssueForToday();
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
		String url = "http://kaijiang.500.com/static/public/%s/xml/qihaoxml/%s.xml?_A=%s";
		if(!SSCCat.CQSSC.equals(cat)){
			url = "http://kaijiang.500.com/static/info/kaijiang/xml/%s/%s.xml?_A=%s";
		}
		url = String
				.format(url,
						cat.getCatId(),
						DateFormatUtils.format(new Date(), "yyyyMMdd"), ""
								+ System.currentTimeMillis());
		return kaijiang500(url);
	}

	@Override
	public void setCatId(SSCCat cat) {
		// TODO Auto-generated method stub
		this.cat = cat;
	}
}
