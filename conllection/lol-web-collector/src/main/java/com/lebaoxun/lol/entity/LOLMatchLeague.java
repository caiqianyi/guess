package com.lebaoxun.lol.entity;

public enum LOLMatchLeague {
	LPL("中国职业联赛","5"), LCK("韩国职业联赛","51"),LCS("北美职业联赛","55"), OLCS("欧洲职业联赛","54"), 
	LMS("台港澳职业联赛","68"), MSI("季中冠军赛","8"),
	ZJS("71","洲际系列赛"), LSPL("中国发展联赛","75");
	private String qq;
	private String name;
	
	private LOLMatchLeague(String name,String qq) {
		// TODO Auto-generated constructor stub
		this.qq = qq;
		this.name = name;
	}

	public String getQq() {
		return qq;
	}

	public String getName() {
		return name;
	}
}
