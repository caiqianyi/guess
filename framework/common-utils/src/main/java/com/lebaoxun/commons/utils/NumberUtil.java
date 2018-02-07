package com.lebaoxun.commons.utils;

import org.apache.commons.lang.StringUtils;

/**
 * 阿拉伯数字 中文数字转换工具
 * @author caiqianyi
 *
 */
public class NumberUtil {
	
	private static final String CH_NUMBERS[] = new String[]{"零","一","二","三","四","五","六","七","八","九","十","百","千","万","亿","兆"};
	private static final String CH_FT_NUMBERS[] = new String[]{"零","壹","贰","叁","肆","伍","陆","柒","捌","玖","拾","佰","仟","万","亿","兆"};

	private static final Long[] utils = new Long[]{10l,100l,1000l,10000l,100000000l,1000000000l};
	
	/**
	 * 将阿拉伯数字转换成中文数字
	 * @param number 阿拉伯数字
	 * @param isHant 是否为繁体
	 * @return
	 */
	public static String toChinese(String number,boolean isHant){
		if(StringUtils.isBlank(number) || !StringUtils.isNumeric(number)){
			return "";
		}
		Long num = Long.parseLong(number);
		int index = 5;
		for(int i=0;i<utils.length;i++){
			Long util = utils[i];
			if(num < util){
				index = i;
				break;
			}
		}
		String numbers[] = isHant ? CH_FT_NUMBERS : CH_NUMBERS;
		if(index == 0){
			return numbers[num.intValue()];
		}
		int offset = index > 5 ? (utils[index]+"").length()-1 : index;
		String bnum = number.substring(0,number.length()-offset);
		String astr = number.substring(number.length()-offset);
		Long anum = Long.parseLong(astr);
		String w = toChinese(bnum,isHant)+numbers[9+index];
		if(anum == 0){
			return w;
		}
		if(astr.startsWith("0") || bnum.endsWith("0")){
			w += toChinese("0",isHant);
		}
		return w +(anum < 10 ? numbers[anum.intValue()] :toChinese(astr,isHant));
	}
}
