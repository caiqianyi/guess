package com.lebaoxun.guess.caipiao.kaijiang;

import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.time.DateUtils;
import org.jsoup.Jsoup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lebaoxun.guess.caipiao.entity.LotteryIssue;

@Service("Cp8sKJDataService")
public class Cp8sKJDataServiceImpl implements IKJDataService{

	private static Map<String,String> map = new HashMap<String,String>();
	static{
		map.put("bjpk10", "bjpk10");
		map.put("xyft", "xyft");
		map.put("jspk10", "jspk10");
		map.put("jsft", "jsft");
		map.put("cqssc", "cqssc");
		map.put("jsssc", "jsssc");
		map.put("jsssc", "jsssc");
		map.put("cqxync", "cqxync");
		map.put("jsk3", "jsk3");
		map.put("tjssc", "tjssc");
		map.put("xjssc", "xjssc");
		map.put("xjssc", "xjssc");
		map.put("gdsyxw", "gd11x5");
		map.put("gdklsf", "gdkl10");
	}
	
	private Logger logger = LoggerFactory
			.getLogger(Cp8sKJDataServiceImpl.class);
	
	@Override
	public void kaijiang(String kindOf,String day,Map<String,LotteryIssue> nums){
		if(!map.containsKey(kindOf)){
			return;
		}
		String catId = map.get(kindOf);
		StringBuffer bf = new StringBuffer(day);
		bf.insert(4, "-");
		bf.insert(7, "-");
		
		String url = "https://www.cp8s.com/data/%s/lotteryList/%s.json?%s";
		try {
			org.jsoup.nodes.Document doc = Jsoup.connect(String.format(url,catId, bf.toString(),System.currentTimeMillis()+""))
				.ignoreContentType(true)
				.userAgent("Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3218.0 Safari/537.36")
				.timeout(30000).get();
			JSONArray datas = JSONArray.parseArray(doc.text());
			for(int i=0;i<datas.size();i++){
				JSONObject op = datas.getJSONObject(i);
				String expect = op.getString("issue");
				JSONArray opens = op.getJSONArray("openNum");
				String opentime = op.getString("openDateTime");
				
				//logger.debug("expect={}",expect);
				if(!nums.containsKey(expect)){
					LotteryIssue lottery = new LotteryIssue();
					lottery.setExpect(expect.replaceAll("-", ""));
					StringBuffer opencode = new StringBuffer();
					for(int k=0;k<opens.size();k++){
						if(!kindOf.endsWith("ssc")
								&& !kindOf.endsWith("k3")){
							opencode.append(opens.getIntValue(k) > 9 ? opens.getIntValue(k): "0"+opens.getIntValue(k));
						}else{
							opencode.append(opens.getIntValue(k));
						}
						if(k<opens.size()-1){
							opencode.append(",");
						}
					}
					logger.debug("Cp9811|expect={},opencode={},opentime={}",expect,opencode,opentime);
					lottery.setOpenCode(opencode.toString());
					lottery.setOpenTime(DateUtils.parseDate(opentime, new String[]{"yyyy-MM-dd HH:mm:ss"}));
					lottery.setKindOf(kindOf);
					nums.put(expect, lottery);
				}
			}
		} catch (IOException e) {
		} catch (ParseException e) {
		} catch (Exception e){
		}
	}
	
}
