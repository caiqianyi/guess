package com.caiqianyi.commons.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class MoneyFormat {
	/**
	 * 金额格式 保留小数点后两位 四舍五入
	 * @param f
	 * @return
	 */
	public static String format2(double f){
		DecimalFormat   df=new  DecimalFormat("0.00"); 
	    BigDecimal  b=new  BigDecimal(f); 
	    double  fvalue=b.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue(); 
	    return df.format(fvalue);
	}
}
