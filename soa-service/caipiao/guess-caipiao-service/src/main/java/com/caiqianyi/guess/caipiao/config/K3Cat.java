package com.caiqianyi.guess.caipiao.config;

public enum K3Cat {
	BJK3("bjk3",10,"09:00:00","23:50:00","北京快3","bjk3"),
	HEBK3("hebk3",10,"08:29:00","21:59:00","河北快3","hebk3"),
	JLK3("jlk3",10,"08:29:00","22:49:00","吉林快3","jlk3"),
	SHHK3("shhk3",10,"08:48:00","22:28:00","上海快3","shhk3"),
	JSK3("jsk3",10,"08:29:00","22:09:00","江苏快3","jsk3"),//82
	AHK3("ahk3",10,"08:40:00","22:00:00","安徽快3","ahk3"),
	FJK3("fjk3",10,"09:01:00","22:01:00","福建快3","fjk3"),//78
	HBK3("hbk3",10,"09:00:00","22:00:00","湖北快3","hbk3"),//78
	;
	
	private String catId;
	private Integer period;
	private String start;
	private String end;
	private String name;
	private String logogram;

	private K3Cat(String catId, Integer period, String start, String end,
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
	
	public static K3Cat getCatByKindOf(String kindOf){
		K3Cat cats[] = K3Cat.values();
		for(K3Cat cat :cats){
			if(cat.getCatId().equals(kindOf)){
				return cat;
			}
		}
		return null;
	}
}
