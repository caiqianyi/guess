package com.caiqianyi.guess.caipiao.kaijiang;

import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.time.DateUtils;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.springframework.stereotype.Service;

import com.caiqianyi.guess.caipiao.entity.LotteryIssue;

@Service("_500WKJDataService")
public class _500WKJDataServiceImpl implements IKJDataService{
	
	public Map<String,LotteryIssue> kaijiang500(String url,Map<String,LotteryIssue> nums){
		try {
			SAXReader reader = new SAXReader();
			Document doc = reader.read(new URL(url));
			List<Node> nodes = doc.selectNodes("/xml/row");
			//同时迭代当前节点下面的所有子节点  
	        //使用递归  
	        Iterator<Node> iterator = nodes.iterator();
	        while(iterator.hasNext()){  
	        	Element e = (Element) iterator.next();
	        	String expect = e.attribute("expect").getText(),
	        			opencode = e.attribute("opencode").getText(),
	        				opentime = e.attribute("opentime").getText();
	        	//logger.debug("expect={},opencode={},opentime={}",expect,opencode,opentime);
	        	
	        	if(!nums.containsKey(expect)){
					LotteryIssue lottery = new LotteryIssue();
					lottery.setExpect(expect.replaceAll("-", ""));
					lottery.setOpenCode(opencode);
					lottery.setOpenTime(DateUtils.parseDate(opentime, new String[]{"yyyy-MM-dd HH:mm:ss"}));
					nums.put(lottery.getExpect(),lottery);
				}
	        }
	        return nums;
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void kaijiang(String kindOf, String day,
			Map<String, LotteryIssue> nums) {
		// TODO Auto-generated method stub
		String url = "http://kaijiang.500.com/static/info/kaijiang/xml/%s/%s.xml?_A=%s";
		String type = kindOf;
		if("bjpk10".equals(kindOf)){
			type = "bjpkshi";
		}
		if("ssc".equals(kindOf)){
			url = "http://kaijiang.500.com/static/public/%s/xml/qihaoxml/%s.xml?_A=%s";
		}
		kaijiang500(String.format(url, type,day,System.currentTimeMillis()+""), nums);
	}
}
