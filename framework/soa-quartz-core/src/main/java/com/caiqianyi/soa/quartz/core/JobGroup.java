package com.caiqianyi.soa.quartz.core;

public enum JobGroup {
	
	SYSTEM("ct.system"),DATA_SYNC("ct.data.sync");
	
	private String name;
	
	JobGroup(String name){
		this.name = name;
	}
	public String getName() {
		return name;
	}
	
	public static JobGroup valueByName(String name){
		JobGroup jgs[] = JobGroup.values();
		for(JobGroup jg : jgs){
			if(jg.getName().equals(name)){
				return jg;
			}
		}
		return null;
	}
}
