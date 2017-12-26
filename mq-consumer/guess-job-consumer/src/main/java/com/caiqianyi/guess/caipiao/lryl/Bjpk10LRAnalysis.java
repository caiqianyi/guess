package com.caiqianyi.guess.caipiao.lryl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.caiqianyi.guess.caipiao.entity.LotteryIssue;

public class Bjpk10LRAnalysis {
	
	private Logger logger = LoggerFactory.getLogger(Bjpk10LRAnalysis.class);
	
	private List<LotteryIssue> lotterys = null;
	
	private Integer r = 20,l = 2;
	
	public Bjpk10LRAnalysis(List<LotteryIssue> lotterys) {
		// TODO Auto-generated constructor stub
		this.lotterys = lotterys;
	}
	
	public Map<String,List<String>> lr(){
		Map<String,List<String>> map = new HashMap<String,List<String>>();
		List<String> l_list = new ArrayList<String>(),
				r_list = new ArrayList<String>(),
				w_list = new ArrayList<String>();
		for(int i=0;i<10;i++){
			String lots[] = new String[]{"01","02","03","04","05","06","07","08","09","10"};
			
			for(int k=0;k<lots.length;k++){
				int count = 0;
				for(int j=0;j<lotterys.size();j++){
					if(lotterys.get(j).getOpenCode().equals(lots[k])){
						count++;
					}
				}
				if(count >= lotterys.size() * r / 100){
					//热
					r_list.add(lots[k]);
				}else if(count <= lotterys.size() * l / 100){
					//冷
					l_list.add(lots[k]);
				}else{
					//温
					w_list.add(lots[k]);
				}
			}
		}
		map.put("l", l_list);
		map.put("r", r_list);
		map.put("w", w_list);
		return map;
	}
	
}
