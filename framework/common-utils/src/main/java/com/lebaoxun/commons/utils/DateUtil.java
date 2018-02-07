package com.lebaoxun.commons.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 时间工具类
 * 
 * @author xhl
 *
 */
public class DateUtil {
	private static final SimpleDateFormat datetimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	private static final SimpleDateFormat dateFormatzh = new SimpleDateFormat("yyyy年MM月dd日");
	private static final SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
	private static final SimpleDateFormat datetmFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	private static final SimpleDateFormat dateFormat_YYYY_MM = new SimpleDateFormat("yyyyMM");
	private static final SimpleDateFormat dateFormat_YYYY_MM_DD = new SimpleDateFormat("yyyy-MM-dd");
	/**
	 * 获取当前时间戳---long型
	 * 
	 * @return
	 */
	public static long getCurrentMillis() {
		return System.currentTimeMillis();
	}

	/**
	 * 获得日期前几天
	 *
	 * @param date
	 *            日期
	 * @param day
	 *            天数
	 * @return Date
	 */
	public static Date getDateBefore(Date date, int day) {
		Calendar now = Calendar.getInstance();
		now.setTime(date);
		now.set(Calendar.DATE, now.get(Calendar.DATE) - day);
		return now.getTime();
	}

	/**
	 * 格式化日期时间
	 *
	 * @param date
	 *            日期
	 * @param pattern
	 *            pattern
	 * @return Datetime
	 */
	public static String formatDatetime(Date date, String pattern) {
		SimpleDateFormat customFormat = (SimpleDateFormat) dateFormat.clone();
		customFormat.applyPattern(pattern);
		return customFormat.format(date);
	}

	/**
	 * 获得当前时间
	 *
	 * @param date
	 *            日期
	 * @return Time HH:mm:ss
	 */
	public static String formatTime(Date date) {
		return timeFormat.format(date);
	}

	/**
	 * 格式化日期
	 *
	 * @param date
	 *            日期
	 * @return Date
	 */
	public static String formatDate(Date date) {
		return dateFormat.format(date);
	}
	
	/**
	 * 格式化日期，中文
	 *
	 * @param date
	 *            日期
	 * @return Date
	 */
	public static String formatZHDate(Date date) {
		return dateFormatzh.format(date);
	}

	/**
	 * 比较日期
	 *
	 * @param String
	 *            startDate , String endDate
	 * 
	 * @return boolean true:表示 开始日期大于结束日期 、false:表示 开始日期小于等于结束日期
	 */
	public static boolean comparDate(String startDate, String endDate) {
		boolean flag = false;
		try {
			Date start_date = datetimeFormat.parse(startDate);
			Date end_date = datetimeFormat.parse(endDate);
			flag = start_date.after(end_date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}

	/**
	 * 获取当前时间戳---long型
	 * 
	 * @return
	 */
	public static long dateTime(String date) {
		try {
			return datetimeFormat.parse(date).getTime() / 1000;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	
	/**
	 * 获取当前时间戳---long型
	 * 
	 * @return
	 */
	public static Date parse(String date) {
		try {
			return dateFormat.parse(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 获取当前日期 不包括的时分秒
	 * 
	 * @return String
	 */
	public static String currentDate() {
		Date currDate = new Date();
		return dateFormat.format(currDate);
	}

	// 获得当天0点时间
	public static int getTimesmorning() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return (int) (cal.getTimeInMillis() / 1000);
	}

	// 获得当天24点时间
	public static int getTimesnight() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 24);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return (int) (cal.getTimeInMillis() / 1000);
	}
	public static Date getYesterDay(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		date = calendar.getTime();
		return date;
	}
	
	/**
	 * 获取当天的前[-1]或后[1]多少天的时间
	 * @param af
	 * @return
	 */
	public static Date getDate(int af) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_MONTH, af); 
		Date date = calendar.getTime();
	    return date;
	}
	/**
	 * 获取当天时间的第一毫秒
	 * 【2016-10-12 00:00:00.001】
	 * @return
	 */
	public static Date getDayBegin() {
		  Calendar cal = Calendar.getInstance();
		  cal.set(Calendar.HOUR_OF_DAY, 0);
		  cal.set(Calendar.SECOND, 0);
		  cal.set(Calendar.MINUTE, 0);
		  cal.set(Calendar.MILLISECOND, 001);
		  return cal.getTime();
	}
	
    /**
     * 将时间戳转换为时间字符串【yyyy-MM-dd HH:mm:ss】
     * @param s
     * @return
     */
    public static String stampToDate(String s){
        String res;
        Date date = new Date(Long.parseLong(s) * 1000L);
        res = datetimeFormat.format(date);
        return res;
    }
    
    /**
	 * 
	 *@author wkx
	 *@Description:返回两个日期之间的相差时间如  2014-9~2014-11={20149,201410,201411}
	 *@param date1
	 *@param date2
	 *@return
	 *@date 2016年12月19日
	 */
	public static String[] getMonthBetw2Date(Long date1,Long date2){
		Calendar c1=Calendar.getInstance();
        Calendar c2=Calendar.getInstance();
        
        c1.setTimeInMillis(date1);
        c2.setTimeInMillis(date2);
        int i =c2.get(Calendar.YEAR)-c1.get(Calendar.YEAR);
        int count = 0;
        
        int year1 = c1.get(Calendar.YEAR);
        int year2 = c2.get(Calendar.YEAR);
        int month1 = c1.get(Calendar.MONTH)+1;
        int month2 = c2.get(Calendar.MONTH)+1;
        
        
        if(i>=0){
            count =  i*12+c2.get(Calendar.MONTH)-c1.get(Calendar.MONTH);
        }else{
        	return null;
        }
        String[] dates = new String[count+1];
        for (int j = 0; j < count+1; j++) {
        	int m = month1 + j;
        	int y = year1 + (m-1)/12;//年份
        	dates[j] = y+""+(m%12==0?12:m%12);
		}
        return dates;
	}
	
	/**
	 * 
	 *@author wkx
	 *@Description:返回两个日期之间的相差天 如  2014-9~2014-11={20149,201410,201411}
	 *@param date1  秒数
	 *@param date2  秒数
	 *@return
	 * @throws ParseException 
	 *@date 2016年12月19日
	 */
	public static Map<String,String> getMonthBetw2Date2(String d1,String d2) throws ParseException{
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");  
	        Date date1 = df.parse(d1);  
	        Date date2 = df.parse(d2);  
	        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
	        Map<String,String> dateList = new HashMap<String,String>();
	        try {  
	          int s = (int) ((date2.getTime() - date1.getTime())/ (24 * 60 * 60 * 1000));  
	          if(s>0){  
	            for(int i = 0;i<=s;i++){  
	              long todayDate = date1.getTime() + i * 24 * 60 * 60 * 1000;  
	              Date tmDate = new Date(todayDate);  
	              /** 
	               * yyyy-MM-dd E :2012-09-01 星期三 
	               */  
	              dateList.put(sf.format(tmDate),sf.format(tmDate));
	            }  
	          }else if(s==0){
	        	  dateList.put(d1,d1);
	          }
	        } catch (Exception e) {  
	          System.out.println("格式错误");  
	        } 
        return dateList;
	}
	
	/**
     * 日期转换 yyyyMM格式
     *
     * @param 时间戳
     * @return String
     */
    public static String dateFormatMonth(Date date) {
		return dateFormat_YYYY_MM.format(date);
    }

}
