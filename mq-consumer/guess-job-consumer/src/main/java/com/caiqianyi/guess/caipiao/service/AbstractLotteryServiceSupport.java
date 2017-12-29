package com.caiqianyi.guess.caipiao.service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.caiqianyi.commons.beans.SpringConfigTool;
import com.caiqianyi.commons.exception.I18nMessageException;
import com.caiqianyi.guess.caipiao.core.dao.ILotteryIssueMapper;
import com.caiqianyi.guess.caipiao.entity.LotteryIssue;
import com.caiqianyi.guess.caipiao.kaijiang.IKJDataService;

public abstract class AbstractLotteryServiceSupport implements ILotteryService{

	private Logger logger = LoggerFactory
			.getLogger(AbstractLotteryServiceSupport.class);

	protected String dateFormat = "yyyyMMdd HH:mm:ss";
	
	protected abstract String getKindOf();
	
	@Resource
	private ILotteryIssueMapper lotteryIssueMapper;

	@Override
	public LotteryIssue getCurrentIssue() {
		// TODO Auto-generated method stub
		return lotteryIssueMapper.getCurrentIssue(getKindOf());
	}
	
	@Override
	public List<LotteryIssue> getLotteryNumBy(Integer size) {
		// TODO Auto-generated method stub
		return lotteryIssueMapper.getHistoryOpenCode(getKindOf(), null, null, size, null);
	}
	
	@Override
	public List<LotteryIssue> findIssueByDay(String day) {
		// TODO Auto-generated method stub
		return lotteryIssueMapper.getIssusByKindOfAndStatus(getKindOf(), null, day);
	}
	
	@Override
	public LotteryIssue getLotteryNumByIssue(String issue){
		return lotteryIssueMapper.getIssueByExpect(getKindOf(), issue);
	}
	
	/**
	 * 获取某天的期号
	 * 
	 * @param day
	 *            时间 yyyyMMdd
	 * @param period
	 *            周期 单位：秒
	 * @param star
	 *            开始时间 格式：HH:mm:ss
	 * @param end
	 *            结束 格式: HH:mm:ss
	 * @return
	 * @throws ParseException
	 */
	public List<LotteryIssue> getLotteryIssueByTimeAndKindOf(String kindOf,String day,
			Integer period, String start, String end) throws ParseException {
		return getLotteryIssueByTimeAndKindOf(kindOf,day, period, start, end, null,
				null);
	}

	/**
	 * 获取某天的期号
	 * 
	 * @param day
	 *            时间 yyyyMMdd
	 * @param period
	 *            周期 单位：秒
	 * @param star
	 *            开始时间 格式：HH:mm:ss
	 * @param end
	 *            结束 格式: HH:mm:ss
	 * @return
	 * @throws ParseException
	 */
	public List<LotteryIssue> getLotteryIssueByTimeAndKindOf(String kindOf,String day,
			Integer period, String start, String end, Date iEndTime, Long issue)
			throws ParseException {
		return getLotteryIssueByTimeAndKindOf(kindOf,day, period, start, end, null,
				iEndTime, issue,true);
	}

	/**
	 * 获取某天的期号
	 * 
	 * @param day
	 *            时间 yyyyMMdd
	 * @param period
	 *            周期 单位：秒
	 * @param star
	 *            开始时间 格式：HH:mm:ss
	 * @param end
	 *            结束 格式: HH:mm:ss
	 * @return
	 * @throws ParseException
	 */
	public List<LotteryIssue> getLotteryIssueByTimeAndKindOf(String kindOf,String day,
			Integer period, String start, String end, Integer issueLength)
			throws ParseException {
		return getLotteryIssueByTimeAndKindOf(kindOf,day, period, start, end,
				issueLength, null, null,true);
	}

	/**
	 * 获取某天的期号
	 * 
	 * @param day
	 *            时间 yyyyMMdd
	 * @param period
	 *            周期 单位：秒
	 * @param star
	 *            开始时间 格式：HH:mm:ss
	 * @param end
	 *            结束 格式: HH:mm:ss
	 * @return
	 * @throws ParseException
	 */
	public List<LotteryIssue> getLotteryIssueByTimeAndKindOf(String kindOf,String day,
			Integer period, String start, String end, Integer issueLength,
			Date iEndTime, Long issue,Boolean isOver) throws ParseException {
		List<LotteryIssue> issues = new ArrayList<LotteryIssue>();
		Date startTime = DateUtils.parseDate(day + " " + start,
				new String[] { dateFormat }), endTime = DateUtils.parseDate(day
				+ " " + end, new String[] { dateFormat });
		if (startTime.after(endTime)) {
			throw new I18nMessageException("500", "开始时间不能晚于结束时间！");
		}
		Integer total = (int) ((endTime.getTime() - startTime.getTime()) / 1000 / period);
		if (issueLength == null) {
			issueLength = ("" + total).length();
		}

		//logger.debug("isOver={},day={}", isOver,day);
		if (iEndTime != null) {
			int days = differentDays(iEndTime, startTime);
			issue += days * total;
			iEndTime = startTime;
			//logger.debug("issue={},total={}", issue, total);
		}

		for (int i = 0; i < total; i++) {
			Date istart = DateUtils.addMilliseconds(startTime, i * period
					* 1000), iend = DateUtils.addMilliseconds(startTime,
					(i + 1) * period * 1000);
			String qh = day + "" + zeroFill(i + 1, issueLength);
			if (issue != null && iEndTime != null) {
				qh = "" + (issue + ((istart.getTime() - iEndTime.getTime()) / 1000 / period));
			}
			if(i == 0){
				istart = DateUtils.addDays(endTime, -1);
			}
			LotteryIssue li = new LotteryIssue();
			li.setStartTime(istart);
			li.setEndTime(iend);
			li.setExpect(qh);
			li.setKindOf(kindOf);
			issues.add(li);
			//logger.debug("istart={},iend={},issue={}", DateFormatUtils.format(istart, "yyyy-MM-dd HH:mm:ss"), DateFormatUtils.format(iend, "yyyy-MM-dd HH:mm:ss"), qh);
		}
		return issues;
	}

	/**
	 * date2比date1多的天数
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public int differentDays(Date date1, Date date2) {
		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(date1);

		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(date2);
		int day1 = cal1.get(Calendar.DAY_OF_YEAR);
		int day2 = cal2.get(Calendar.DAY_OF_YEAR);

		int year1 = cal1.get(Calendar.YEAR);
		int year2 = cal2.get(Calendar.YEAR);
		if (year1 != year2) {//不同年
			int timeDistance = 0;
			for (int i = year1; i < year2; i++) {
				if (i % 4 == 0 && i % 100 != 0 || i % 400 == 0) {// 闰年
					timeDistance += 366;
				} else {// 不是闰年
					timeDistance += 365;
				}
			}
			return timeDistance + (day2 - day1);
		}
		return day2 - day1;
	}

	public String zeroFill(Integer num, Integer places) {
		String str = num + "", blank = "";
		for (int i = 0; i < places - str.length(); i++) {
			blank += "0";
		}
		return blank + num;
	}
	
	public List<LotteryIssue> kaijiang(String day,String kindOf){
		Map<String,LotteryIssue> nums = new LinkedHashMap<String,LotteryIssue>();
		String sites[] = new String[]{"Cp8s","Cp91"};
		for(int i=0;i<sites.length;i++){
			((IKJDataService) SpringConfigTool.getBean(sites[i]+"KJDataService")).kaijiang(kindOf, day, nums);
		}
		List<LotteryIssue> lotterys = new ArrayList<LotteryIssue>();
		lotterys.addAll(nums.values());
		//logger.debug("lotterys={}",new Gson().toJson(lotterys));
		return lotterys;
	}
}
