package com.caiqianyi.guess.caipiao.config;

public enum SSCCat {
	SSC("ssc", new Integer[] { 5, 10, 5 }, new String[] {
			"00:00:00-02:00:00", "10:00:00-22:00:00", "22:00:00-24:00:00" },
			"重庆时时彩", "cqssc"), //120期
	TJSSC("tjssc", new Integer[] {10}, new String[] {"09:00:00-23:00:00"}, "天津时时彩", "tjssc"),//84期
	XJSSC("xjssc", new Integer[] {10}, new String[] {"09:59:00-23:49:00"}, "新疆时时彩", "xjssc"),//83期
	YNSSC("ynssc", new Integer[] {10}, new String[] {"09:30:00-21:30:00"}, "云南时时彩", "ynssc"),//72期
	;
	
	private String catId;
	private Integer[] periods;
	private String times[];
	private String name;
	private String logogram;

	private SSCCat(String catId, Integer[] periods, String times[],
			String name, String logogram) {
		// TODO Auto-generated constructor stub
		this.catId = catId;
		this.periods = periods;
		this.times = times;
		this.name = name;
		this.logogram = logogram;
	}

	public String getCatId() {
		return catId;
	}

	public Integer[] getPeriods() {
		return periods;
	}

	public String[] getTimes() {
		return times;
	}

	public String getName() {
		return name;
	}

	public String getLogogram() {
		return logogram;
	}
	
	public static SSCCat getCatByKindOf(String kindOf){
		SSCCat cats[] = SSCCat.values();
		for(SSCCat cat :cats){
			if(cat.getCatId().equals(kindOf)){
				return cat;
			}
		}
		return null;
	}
}
