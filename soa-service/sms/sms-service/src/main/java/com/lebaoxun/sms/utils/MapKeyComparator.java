package com.lebaoxun.sms.utils;

import java.util.Comparator;

public class MapKeyComparator implements Comparator<String>{
	
	private boolean desc = false;
	
	public MapKeyComparator() {
		// TODO Auto-generated constructor stub
	}
	
	public MapKeyComparator(boolean desc){
		this.desc = desc;
	}

    @Override
    public int compare(String str1, String str2) {
    	if(desc){
    		return str2.compareTo(str1);
    	}
        return str1.compareTo(str2);
    }
}

