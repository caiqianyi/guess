package com.caiqianyi.guess.caipiao.config;

public enum BJPK10Cat {
	BJPK10("bjpk10",5,"09:02:00","23:57:00","北京赛车","bjpk10");
	
	private String catId;
	private Integer period;
	private String start;
	private String end;
	private String name;
	private String logogram;
	private String[] lotterys = new String[] { "01", "02", "03", "04", "05", "06", "07", "08",
			"09", "10" };

	private BJPK10Cat(String catId, Integer period, String start, String end,
			String name, String logogram) {
		// TODO Auto-generated constructor stub
		this.catId = catId;
		this.period = period;
		this.start = start;
		this.end = end;
		this.name = name;
		this.logogram = logogram;
	}

	public String getCatId() {
		return catId;
	}

	public Integer getPeriod() {
		return period;
	}

	public String getStart() {
		return start;
	}

	public String getEnd() {
		return end;
	}

	public String getName() {
		return name;
	}

	public String getLogogram() {
		return logogram;
	}
	
	public static BJPK10Cat getCatByKindOf(String kindOf){
		BJPK10Cat cats[] = BJPK10Cat.values();
		for(BJPK10Cat cat :cats){
			if(cat.getCatId().equals(kindOf)){
				return cat;
			}
		}
		return null;
	}

	public String[] getLotterys() {
		return lotterys;
	}
}
