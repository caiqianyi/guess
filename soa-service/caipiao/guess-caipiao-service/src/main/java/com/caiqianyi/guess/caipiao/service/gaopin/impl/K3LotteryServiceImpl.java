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

import com.caiqianyi.guess.caipiao.config.K3Cat;
import com.caiqianyi.guess.caipiao.core.dao.ILotteryIssueMapper;
import com.caiqianyi.guess.caipiao.entity.LotteryIssue;
import com.caiqianyi.guess.caipiao.service.gaopin.IK3LotteryService;
import com.caiqianyi.guess.caipiao.support.AbstractLotteryServiceSupport;

@Service("k3LotteryService")
public class K3LotteryServiceImpl extends AbstractLotteryServiceSupport
		implements IK3LotteryService {
	
	private Logger logger = LoggerFactory.getLogger(K3LotteryServiceImpl.class);

	private K3Cat cat;
	
	@Resource
	private ILotteryIssueMapper lotteryIssueMapper;
	
	@Override
	public List<LotteryIssue> getIssueForToday() {
		return getIssueByDay(DateFormatUtils.format(new Date(), "yyyyMMdd"));
	}

	@Override
	public List<LotteryIssue> getIssueByDay(String day) {
		try {
			return getLotteryIssueByTimeAndKindOf(cat.getCatId(),day, cat.getPeriod() * 60, cat.getStart(),
					cat.getEnd());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace(); 
		}
		return new ArrayList<LotteryIssue>();
	}

	public static void main(String[] args) {
		IK3LotteryService k3LotteryService = new K3LotteryServiceImpl();
		k3LotteryService.setCatId(K3Cat.AHK3);
		k3LotteryService.getIssueForToday();
		k3LotteryService.captureNewestNum();
	}

	@Override
	public LotteryIssue getCurrentIssue() {
		// TODO Auto-generated method stub
		return lotteryIssueMapper.getCurrentIssue(cat.getCatId());
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
	public void setCatId(K3Cat cat) {
		// TODO Auto-generated method stub
		this.cat = cat;
	}

}
