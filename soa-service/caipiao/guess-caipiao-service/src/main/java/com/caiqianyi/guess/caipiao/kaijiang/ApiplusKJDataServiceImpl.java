package com.caiqianyi.guess.caipiao.kaijiang;

import java.io.IOException;
import java.text.ParseException;
import java.util.Map;

import org.apache.commons.lang.time.DateUtils;
import org.jsoup.Jsoup;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.caiqianyi.guess.caipiao.entity.LotteryIssue;

@Service("ApiplusKJDataService")
public class ApiplusKJDataServiceImpl implements IKJDataService{

	@Override
	public void kaijiang(String kindOf, String day,
			Map<String, LotteryIssue> nums) {
		// TODO Auto-generated method stub
		String url = "http://f.apiplus.net/"+kindOf+"-20.json";
		try {
			org.jsoup.nodes.Document doc = Jsoup.connect(url)
				.ignoreContentType(true)
				.userAgent("Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3218.0 Safari/537.36")
				.timeout(5000).get();
			JSONObject json = JSONObject.parseObject(doc.text());
			JSONArray array = json.getJSONArray("data");
			for(int i=0;i<array.size();i++){
				JSONObject op = array.getJSONObject(i);
				String expect = op.getString("expect");
				String opencode = op.getString("opencode");
				String opentime = op.getString("opentime");
				
				//logger.debug("expect={}",expect);
				if(!nums.containsKey(expect)){
					LotteryIssue lottery = new LotteryIssue();
					lottery.setExpect(expect.replaceAll("-", ""));
					lottery.setOpenCode(opencode);
					lottery.setOpenTime(DateUtils.parseDate(opentime, new String[]{"yyyy-MM-dd HH:mm:ss"}));
					lottery.setKindOf("bjsc");
					nums.put(expect, lottery);
				}
			}
		} catch (IOException e) {
		} catch (ParseException e) {
		} catch (JSONException e){
		}
	}
}
