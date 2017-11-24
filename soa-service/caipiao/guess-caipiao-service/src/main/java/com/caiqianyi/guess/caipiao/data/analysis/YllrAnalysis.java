package com.caiqianyi.guess.caipiao.data.analysis;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.caiqianyi.guess.caipiao.entity.LotteryIssue;
import com.caiqianyi.guess.caipiao.service.gaopin.I11x5LotteryService;
import com.caiqianyi.guess.caipiao.service.gaopin.impl.BjscLotteryServiceImpl;
import com.caiqianyi.guess.caipiao.service.gaopin.impl._11x5LotteryServiceImpl;
import com.google.gson.Gson;

public class YllrAnalysis {
	
	private Logger logger = LoggerFactory.getLogger(YllrAnalysis.class);
	
	private List<LotteryIssue> lotterys = new ArrayList<LotteryIssue>();
	
	private Map<String,Map<String,Object>> datas = new LinkedHashMap<String,Map<String,Object>>();
	
	private Map<String,Object> initData = null;
	
	private String lots[];
	
	public YllrAnalysis(List<LotteryIssue> lotterys,String lots[]) {
		// TODO Auto-generated constructor stub
		this(lotterys, lots, null);
	}
	public YllrAnalysis(List<LotteryIssue> lotterys,String lots[],Map<String,Object> initData) {
		// TODO Auto-generated constructor stub
		this.lotterys = lotterys;
		this.lots = lots;
		this.initData = initData;
	}
	
	public List<Map<String, Object>> doAnalysis(){
		this.datas = new LinkedHashMap<String,Map<String,Object>>();
		Collections.sort(lotterys, new Comparator<LotteryIssue>(){
			@Override
			public int compare(LotteryIssue o1, LotteryIssue o2) {
				// TODO Auto-generated method stub
				return o1.getExpect().compareTo(o2.getExpect());
			}
			
		});
		for(int i=0;i<lotterys.size();i++){
			doAnalysisForCode(i);
			int len = lotterys.get(i).getOpenCode().split("\\,").length;
			len = len == lots.length? len / 2 : len;
			for(int k=2;k<=len;k++){
				doAnalysisForRank(i,k);
			}
			doAnalysisForZhi(i);
		}
		List<Map<String, Object>> datas = new ArrayList<Map<String, Object>>(this.datas.values());
		logger.debug("datas={}",new Gson().toJson(datas));
		return datas;
	}
	private Integer getDataByIndex(LotteryIssue lotteryIssue,String type,Integer i){
		Map<String,Object> data = new LinkedHashMap<String,Object>();
		if(datas.containsKey(lotteryIssue.getExpect())){
			data = datas.get(lotteryIssue.getExpect());
		}
		Integer datas[] = (Integer[]) data.get(type);
		return datas[i];
	}
	
	private void doAnalysisForCode(int index){
		LotteryIssue lotteryIssue = lotterys.get(index);
		Map<String,Object> data = new LinkedHashMap<String,Object>();
		if(datas.containsKey(lotteryIssue.getExpect())){
			data = datas.get(lotteryIssue.getExpect());
		}
		data.put("expect", lotteryIssue.getExpect());
		data.put("lottery", lotteryIssue.getOpenCode());
		datas.put(lotteryIssue.getExpect(), data);
	}
	
	private void doAnalysisForRank(int index,Integer len){
		LotteryIssue lotteryIssue = lotterys.get(index);
		Map<String,Object> data = new LinkedHashMap<String,Object>();
		if(datas.containsKey(lotteryIssue.getExpect())){
			data = datas.get(lotteryIssue.getExpect());
		}
		String codes[] = lotteryIssue.getOpenCode().split("\\,");
		Integer rankData[] = new Integer[lots.length];
		for(int i=0;i<lots.length;i++){
			boolean flag = false;
			if(index == 0){
				if(initData != null){
					rankData[i] = ((Integer[]) initData.get("zu"+len))[i];
					continue;
				}
				flag = true;
			}
			for(int k=0;k<len;k++){
				int n = Integer.parseInt(codes[k]);
				if(n == i+1){
					flag = true;
					break;
				}
			}
			if(flag){
				rankData[i] = 0;
				continue;
			}
			rankData[i] = getDataByIndex(lotterys.get(index-1), "zu"+len , i)+1;
			
		}
		//logger.debug("rankData|expect={},rankData={},lottery={}",lotteryIssue.getExpect(),rankData,lotteryIssue.getOpenCode());
		data.put("zu"+len, rankData);
		datas.put(lotteryIssue.getExpect(), data);
	}
	
	private void doAnalysisForZhi(int index){
		LotteryIssue lotteryIssue = lotterys.get(index);
		Map<String,Object> data = new LinkedHashMap<String,Object>();
		if(datas.containsKey(lotteryIssue.getExpect())){
			data = datas.get(lotteryIssue.getExpect());
		}
		String codes[] = lotteryIssue.getOpenCode().split("\\,");
		Integer q1Data[] = new Integer[lots.length],
				q2Data[] = new Integer[lots.length],
				q3Data[] = new Integer[lots.length];
		for(int i=0;i<lots.length;i++){
			Integer n1 = Integer.parseInt(codes[0]),
					n2 = Integer.parseInt(codes[1]),
					n3 = Integer.parseInt(codes[2]);
			if(index == 0){
				if(initData != null){
					q1Data[i] = ((Integer[]) initData.get("q1"))[i];
					q2Data[i] = ((Integer[]) initData.get("q2"))[i];
					q3Data[i] = ((Integer[]) initData.get("q3"))[i];
				}else{
					q1Data[i] = 0;
					q2Data[i] = 0;
					q3Data[i] = 0;
				}
				continue;
			}
			q1Data[i] = 0;
			if(!n1.equals(i+1)){
				q1Data[i] = getDataByIndex(lotterys.get(index-1), "q1", i)+1;
			}
			q2Data[i] = 0;
			if(!n2.equals(i+1)){
				q2Data[i] = getDataByIndex(lotterys.get(index-1), "q2", i)+1;
			}
			q3Data[i] = 0;
			if(!n3.equals(i+1)){
				q3Data[i] = getDataByIndex(lotterys.get(index-1), "q3", i)+1;
			}
		}
		//logger.debug("doAnalysisForQ1|expect={},rankData={},lottery={}",lotteryIssue.getExpect(),rankData,lotteryIssue.getOpenCode());
		data.put("q1", q1Data);
		data.put("q2", q2Data);
		data.put("q3", q3Data);
		datas.put(lotteryIssue.getExpect(), data);
	}
	
	public static void main(String[] args) {
		BjscLotteryServiceImpl bls = new BjscLotteryServiceImpl();
		
		I11x5LotteryService _11x5LotteryService = new _11x5LotteryServiceImpl();
		_11x5LotteryService.setKindOf("gdsyxw");
		
		List<LotteryIssue> list = new ArrayList<LotteryIssue>();
		LotteryIssue issue1 = new LotteryIssue(),
				issue2 = new LotteryIssue(),
				issue3 = new LotteryIssue();
		issue1.setExpect("651997");
		issue1.setOpenCode("05,07,03,10,04,01,08,09,02,06");
		issue2.setExpect("651996");
		issue2.setOpenCode("02,04,06,09,01,08,05,10,07,03");
		issue3.setExpect("651995");
		issue3.setOpenCode("05,03,07,10,04,06,08,02,09,01");
		list.add(issue1);
		list.add(issue2);
		list.add(issue3);
		list = bls.getOpencode(DateFormatUtils.format(new Date(), "yyyyMMdd"));
		//list = _11x5LotteryService.getOpencode(DateFormatUtils.format(new Date(), "yyyyMMdd"));
		new YllrAnalysis(list,new String[]{"01","02","03","04","05","06","07","08","09","10","11"}).doAnalysis();
	}
}