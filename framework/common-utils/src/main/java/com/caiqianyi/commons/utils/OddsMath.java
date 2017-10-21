package com.caiqianyi.commons.utils;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OddsMath {
	
	private static Logger logger = LoggerFactory.getLogger(OddsMath.class);
	
	public static BigDecimal[] calOdds(String bonusRatio,BigDecimal[] bas) {
		BigDecimal ratio = new BigDecimal(bonusRatio),base = new BigDecimal("1.00");
		BigDecimal odds[] = new BigDecimal[bas.length];
		for(int i=0;i<bas.length;i++){
			BigDecimal total = new BigDecimal("0.00");
			for(int k=0;k<bas.length;k++){
				if(i != k){
					logger.debug("bas[{}]={},total={}",k,bas[k],total.add(bas[k]));
					total = total.add(bas[k]);
				}
			}
			BigDecimal betmoney = base.subtract(ratio);
			odds[i] = total.multiply(betmoney).divide(bas[i],2,BigDecimal.ROUND_DOWN).add(base);
			logger.debug("index={},betmoney={},total={},odds={}",i,betmoney,total,odds[i]);
		}
		logger.debug("length={},odds={}",odds.length,odds);
		return odds;
	}
	
	public static void main(String[] args) {
		BigDecimal bas[] = new BigDecimal[]{new BigDecimal("1.00"),new BigDecimal("100.00"),new BigDecimal("1.00")
		,new BigDecimal("1.00")};
		OddsMath.calOdds("0.2", bas);
	}
}
