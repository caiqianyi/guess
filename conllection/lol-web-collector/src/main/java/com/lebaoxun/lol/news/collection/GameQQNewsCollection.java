package com.lebaoxun.lol.news.collection;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import com.lebaoxun.commons.exception.I18nMessageException;

public class GameQQNewsCollection {
	private final static String API = "http://apps.game.qq.com/lol/match/apis/searchNewsInfo.php?r1=SearchResultList&page=%s&p0=0&p2=11&p4=0&p5=0&_=%s";
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	public Document connect(Integer page){
		Document doc = null;
		try {
			doc = Jsoup
					.connect(String.format(API, page.toString(), "" + System.currentTimeMillis()))
					.userAgent("Mozilla")
					.header("accept-language", "zh-CN,zh;q=0.8")
					.header("referer",
							"https://qs.888.qq.com/m_qq/es/es.lol.html?channelName=landing")
					.header("user-agent",
							"Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.87 Safari/537.36")
					.header("x-requested-with", "XMLHttpRequest")
					.header("Content-Type", "text/html;charset=UTF-8")
					.timeout(3000).get();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return doc;
	}
	
	public List<LinkedTreeMap<String,Object>> getItems(Integer page){
		Document doc = connect(page);
		if(doc == null){
			return null;
		}
		String body = doc.body().text();
		int s = body.indexOf("{"),
			e = body.lastIndexOf(";");
		String json = body.substring(s,e);
		Map<String,Object> response = new Gson().fromJson(json, Map.class);
		String status = (String) response.get("status");
		Object msg = response.get("msg");
		if(!"0".equals(status)){
			logger.error("status={},msg={}",status,msg);
			throw new I18nMessageException("-1",msg.toString());
		}
		logger.debug("msg.class={}",msg.getClass());
		LinkedTreeMap<String,Object> msgMap = (LinkedTreeMap) msg;
		String total = (String)msgMap.get("total"),
				totalPage = (String)msgMap.get("totalpage");
		List<LinkedTreeMap<String,Object>> result = (ArrayList)msgMap.get("result");
		
		logger.debug("total={},totalPage={},result.class={}",total,totalPage,result.getClass());
		
		for(LinkedTreeMap<String,Object> item : result){
			logger.debug("item={}",item);
		}
		return result;
	}
	
	public static void main(String[] args) {
		new GameQQNewsCollection().getItems(-1);
	}
}
