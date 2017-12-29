package com.caiqianyi.guess.caipiao.lryl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.caiqianyi.guess.caipiao.entity.LotteryIssue;

public class Bjpk10LRAnalysis {
	
	private Logger logger = LoggerFactory.getLogger(Bjpk10LRAnalysis.class);
	
	private List<LotteryIssue> lotterys = null;
	
	public Bjpk10LRAnalysis(List<LotteryIssue> lotterys) {
		// TODO Auto-generated constructor stub
		this.lotterys = lotterys;
		if(this.lotterys.size() > 50){
			this.lotterys = this.lotterys.subList(0, 50);
		}
	}
	
	public List<Map<String,Map<String,Integer>>> lr(){
		List<Map<String,Map<String,Integer>>> datas = new ArrayList<Map<String,Map<String,Integer>>>();
		for(int i=0;i<10;i++){
			TreeMap<String,Integer> map = new TreeMap<String,Integer>();
			String lots[] = new String[]{"01","02","03","04","05","06","07","08","09","10"};
			
			for(int k=0;k<lots.length;k++){
				int count = 0;
				for(int j=0;j<lotterys.size();j++){
					String lts[] = lotterys.get(j).getOpenCode().split("\\,");
					if(lts[k].equals(lots[k])){
						count++;
					}
				}
				map.put(lots[k], count);
			}
			
			List<Entry<String, Integer>> list = new ArrayList<Entry<String, Integer>>(map.entrySet());  
	          
	        Collections.sort(list,new Comparator<Map.Entry<String,Integer>>() {  
	            //升序排序  
	            public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {  
	                return o1.getValue().compareTo(o2.getValue());  
	            }  
	        });
	        
	        Map<String,Integer> l = new LinkedHashMap<String, Integer>(),
	        		r = new LinkedHashMap<String, Integer>(),
	        		w = new LinkedHashMap<String, Integer>();
	        Integer pre_r = null, pre_w = null;
	        for(int k=list.size()-1;k>-1;k--){
	        	String key = list.get(k).getKey();
	        	Integer val = list.get(k).getValue();
	        	if(k > 8 || val.equals(pre_r)){
	        		r.put(key, val);
	        		pre_r = val;
	        	}else if(k > 2 || val.equals(pre_w)){
	        		pre_r = null;
	        		w.put(key, val);
	        		pre_w = val;
	        	}else{
	        		pre_r = null;
	        		pre_w = null;
	        		l.put(key, val);
	        	}
	        }
	        Map<String,Map<String,Integer>> item = new HashMap<String,Map<String,Integer>>();
	        item.put("r", r);
	        item.put("w", w);
	        item.put("l", l);
	        logger.debug("item={}",item);
			datas.add(item);
		}
		return datas;
	}
	
}
