package com.caiqianyi.guess.caipiao.service.gaopin.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.caiqianyi.guess.caipiao.config.SSCCat;
import com.caiqianyi.guess.caipiao.core.dao.ILotteryIssueMapper;
import com.caiqianyi.guess.caipiao.entity.LotteryIssue;
import com.caiqianyi.guess.caipiao.service.gaopin.ISSCLotteryService;
import com.caiqianyi.guess.caipiao.support.AbstractLotteryServiceSupport;

@Service("sscLotteryService")
public class SSCLotteryServiceImpl extends AbstractLotteryServiceSupport
		implements ISSCLotteryService {

	private SSCCat cat;

	private Logger logger = LoggerFactory
			.getLogger(SSCLotteryServiceImpl.class);
	
	@Resource
	private ILotteryIssueMapper lotteryIssueMapper;

	@Override
	public List<LotteryIssue> getIssueByDay(String day) {
		try {
			List<LotteryIssue> all = new ArrayList<LotteryIssue>();
			String[] times = cat.getTimes();
			logger.debug("day={}", day);
			for (int i = 0; i < times.length; i++) {
				String time[] = times[i].split("\\-");
				if (i == 0) {
					all.addAll(getLotteryIssueByTimeAndKindOf(cat.getCatId(),day,
							cat.getPeriods()[i] * 60, time[0], time[1], 3,null,null,times.length == 1));
					continue;
				}

				Date s = DateUtils.addMilliseconds(all.get(all.size() - 1)
						.getStartTime(), cat.getPeriods()[i] * 60 * 1000);
				boolean isLast = i == times.length -1;
				List<LotteryIssue> list = getLotteryIssueByTimeAndKindOf(
						cat.getCatId(), 
						day,
						cat.getPeriods()[i] * 60, time[0], time[1], 3, s,
						Long.parseLong(all.get(all.size() - 1).getExpect()) + 1,isLast);
				all.addAll(list);
			}
			if(all.size() > 0){
				Date startTime = DateUtils.parseDate(day + " "+cat.getTimes()[0].split("-")[0],
						new String[] { dateFormat });
				LotteryIssue last = all.get(all.size()-1);
				last.setEndTime(DateUtils.addDays(startTime, 1));
			}
			logger.debug("issue.size={}", all.size());
			return all;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ArrayList<LotteryIssue>();
	}

	@Override
	public List<LotteryIssue> getIssueForToday() {
		// TODO Auto-generated method stub
		return getIssueByDay(DateFormatUtils.format(new Date(), "yyyyMMdd"));
	}

	public static void main(String[] args) {
		ISSCLotteryService sscLotteryService = new SSCLotteryServiceImpl();
		
		sscLotteryService.setKindOf("ssc");
		for(int i=0;i<2;i++){
			Calendar c = Calendar.getInstance();
			c.set(Calendar.HOUR, 0);
			c.set(Calendar.MINUTE, 0);
			c.set(Calendar.MILLISECOND, 0);
			c.set(Calendar.DAY_OF_YEAR, c.get(Calendar.DAY_OF_YEAR)+i);
			sscLotteryService.getIssueByDay(DateFormatUtils.format(c, "yyyyMMdd"));
		}
		//sscLotteryService.getOpencode(DateFormatUtils.format(new Date(), "yyyyMMdd"));
		//sscLotteryService.getIssueForToday();
	}

	@Override
	public List<LotteryIssue> getOpencode(String day) {
		// TODO Auto-generated method stub
		String url = "http://kaijiang.500.com/static/public/%s/xml/qihaoxml/%s.xml?_A=%s";
		if(!SSCCat.SSC.equals(cat)){
			url = "http://kaijiang.500.com/static/info/kaijiang/xml/%s/%s.xml?_A=%s";
		}
		url = String.format(url,cat.getCatId(),day, ""+ System.currentTimeMillis());
		return kaijiang(day,cat.getCatId());
	}

	@Override
	public void setKindOf(String kindOf) {
		// TODO Auto-generated method stub
		this.cat = SSCCat.getCatByKindOf(kindOf);
	}
	
	@Override
	protected String getKindOf() {
		return cat.getCatId();
	}
	
	@Override
	public String[] getLottery() {
		return new String[]{"0","1","2","3","4","5","6","7","8","9"};
	}
}
