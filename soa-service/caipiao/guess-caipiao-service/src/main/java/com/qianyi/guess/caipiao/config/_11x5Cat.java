package com.qianyi.guess.caipiao.config;

public enum _11x5Cat {
	BJ11X5("bjsyxw",10,"09:00:30","23:00:30","北京11选5","bj11x5"),
	TJ11X5("tjsyxw",10,"09:01:00","23:51:00","天津11选5","tj11x5"),
	HB11X5("hbsyxw",10,"08:30:00","22:30:00","河北11选5","hb11x5"),
	NMG11X5("nmgsyxw",10,"09:46:00","22:06:00","内蒙古11选5","nmg11x5"),
	JX11X5("jxsyxw",10,"09:00:00","22:00:00","江西11选5","jx11x5"),
	XJ11X5("xjsyxw",10,"09:00:00","22:00:00","新疆11选5","xj11x5"),
	GD11X5("gdsyxw",10,"09:00:00","22:00:00","广东11选5","gx11x5"),
	;
	
	private String catId;
	private Integer period;
	private String start;
	private String end;
	private String name;
	private String logogram;

	private _11x5Cat(String catId, Integer period, String start, String end,
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
}
