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
					BigDecimal odd = bas[k] == null? new BigDecimal("1.00") : bas[k] ;
					logger.debug("bas[{}]={},total={}",k,odd,total.add(odd));
					total = total.add(odd);
				}
			}
			BigDecimal odd = bas[i] == null? new BigDecimal("1.00") : bas[i] ;
			BigDecimal betmoney = base.subtract(ratio);
			odds[i] = total.multiply(betmoney).divide(odd,2,BigDecimal.ROUND_DOWN).add(base);
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
