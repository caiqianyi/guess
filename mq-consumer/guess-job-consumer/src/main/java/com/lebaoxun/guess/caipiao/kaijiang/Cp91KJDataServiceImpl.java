package com.lebaoxun.guess.caipiao.kaijiang;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.time.DateFormatUtils;
import org.jsoup.Jsoup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.lebaoxun.guess.caipiao.entity.LotteryIssue;

@Service("Cp91KJDataService")
public class Cp91KJDataServiceImpl implements IKJDataService {

	private Logger logger = LoggerFactory
			.getLogger(Cp8sKJDataServiceImpl.class);
	
	private static Map<String,String> map = new HashMap<String,String>();
	static{
		map.put("bjpk10", "bjpk10");
		map.put("cqssc", "cqssc");
	}
	
	@Override
	public void kaijiang(String kindOf, String day,
			Map<String, LotteryIssue> nums) {
		// TODO Auto-generated method stub

		String catId = map.get(kindOf);
		
		String url = "https://www.cp91.com/getLotteryBase.do?gameCode=%s";
		try {
			org.jsoup.nodes.Document doc = Jsoup.connect(String.format(url,catId,System.currentTimeMillis()+""))
				.ignoreContentType(true)
				.userAgent("Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3218.0 Safari/537.36")
				.timeout(30000).get();
			JSONObject op = JSONObject.parseObject(doc.text());
			String expect = op.getString("preIssue");
			JSONArray opens = op.getJSONArray("openNum");
			Long opentime = op.getLong("currentOpenDateTime");
			
			//logger.debug("expect={}",expect);
			if(!nums.containsKey(expect)){
				LotteryIssue lottery = new LotteryIssue();
				StringBuffer opencode = new StringBuffer();
				for(int k=0;k<opens.size();k++){
					if(!kindOf.endsWith("ssc")
							&&!kindOf.endsWith("k3")){
						opencode.append(opens.getIntValue(k) > 9 ? opens.getIntValue(k): "0"+opens.getIntValue(k));
					}else{
						opencode.append(opens.getIntValue(k));
					}
					if(k<opens.size()-1){
						opencode.append(",");
					}
				}
				Date openTime = new Date(opentime);
				lottery.setExpect(expect.replaceAll("-", ""));
				lottery.setOpenCode(opencode.toString());
				lottery.setOpenTime(openTime);
				lottery.setKindOf(kindOf);
				logger.debug("Cp91|expect={},opencode={},openTime={}",expect,opencode,openTime);
				logger.debug("Cp91|lottery={}",new Gson().toJson(lottery));
				nums.put(expect, lottery);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		/*Map<String, LotteryIssue> nums = new HashMap<String,LotteryIssue>();
		new Cp91KJDataServiceImpl().kaijiang("bjpk10", null, nums);*/
		System.out.println(DateFormatUtils.format(new Date(1514524065614l), "yyyy-MM-dd HH:mm:ss"));
	}

}
