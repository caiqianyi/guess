package com.caiqianyi.guess.caipiao.config;

public class KindOfCons {
	
	public static String[] getLotterys(String kindOf){
		BJPK10Cat cat = BJPK10Cat.getCatByKindOf(kindOf);
		if(cat != null){
			return cat.getLotterys();
		}
		return null;
	}
}
