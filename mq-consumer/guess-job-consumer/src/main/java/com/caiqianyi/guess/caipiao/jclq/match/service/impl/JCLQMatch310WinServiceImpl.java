package com.caiqianyi.guess.caipiao.jclq.match.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.script.Compilable;
import javax.script.CompiledScript;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

import jdk.nashorn.api.scripting.ScriptObjectMirror;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang.time.DateUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.caiqianyi.commons.utils.FormulaCalculate;
import com.caiqianyi.guess.caipiao.jclq.match.service.IJCLQMatchSyncService;
import com.caiqianyi.guess.jclq.entity.JCLQMatch;
import com.caiqianyi.guess.jclq.entity.JCLQMatchDatas;
import com.caiqianyi.guess.jclq.entity.JCLQMatchOrder;
import com.google.gson.Gson;

@Service("JCLQMatch310WinService")
public class JCLQMatch310WinServiceImpl implements IJCLQMatchSyncService {

	private static Logger logger = LoggerFactory.getLogger(JCLQMatch310WinServiceImpl.class);
	
	private String host = "http://www.310win.com";
	private String url = "/buy/JingCaiBasketHunhe.aspx";
	
	private ArrayList<Map<String, String>> parseZj(JSONArray array){
		ArrayList<Map<String, String>> data = new ArrayList<Map<String,String>>();
		for(int k=0;k<array.size();k++){
			JSONObject ob = array.getJSONObject(k);
			JSONObject value = ob.getJSONObject("value");
			Map<String, String> item = new LinkedHashMap<String,String>();
			String league = value.getString("0"),
					matchTime = value.getString("2"),
						hostName = value.getString("4"),
							score = value.getString("5"),
								guestName = value.getString("8"),
									rf = value.getString("11"),
										dxf = value.getString("14");
			
			item.put("league", league);
			item.put("matchTime", matchTime);
			item.put("hostName", hostName);
			item.put("score", score);
			item.put("guestName", guestName);
			item.put("rf", rf);
			item.put("dxf", dxf);
			data.add(item);
		}
		return data;
	}
	
	private ArrayList<Map<String, Object>> parseOdds(JSONArray array){
		ArrayList<Map<String, Object>> pls = new ArrayList<Map<String, Object>>();
		
		for(int k=0;k<array.size();k++){
			JSONObject ob = array.getJSONObject(k);
			String value = ob.getString("value");
			String srs[] = value.split("\\|");
			Map<String,Object> item = new HashMap<String,Object>();
			Double hodds = null,
					hiodds = null,
					  vodds = null,
					  	viodds = null,
					      hrate = null,
			    		  	hirate = null,
					  		  vrate = null,
					  			virate = null,
					  			  frate = null,
					  				firate = null,
					  				  klzs1 = null,
					  				  	klzs2 = null;
			boolean isRepair = false;//是否为初始盘
			hiodds = Double.parseDouble(srs[3]);
			viodds = Double.parseDouble(srs[4]);
			hirate = Double.parseDouble(srs[5]);
			virate = Double.parseDouble(srs[6]);
			firate = Double.parseDouble(srs[7]);
			
			if(StringUtils.isNotBlank(srs[8])){
				hodds = Double.parseDouble(srs[8]);
			}else{
				hodds = hiodds;
				isRepair = true;
			}
			if(StringUtils.isNotBlank(srs[9])){
				vodds = Double.parseDouble(srs[9]);
			}else{
				vodds = viodds;
				isRepair = true;
			}
			if(StringUtils.isNotBlank(srs[10])){
				hrate = Double.parseDouble(srs[10]);
			}else{
				hrate = hirate;
				isRepair = true;
			}
			if(StringUtils.isNotBlank(srs[11])){
				vrate = Double.parseDouble(srs[11]);
			}else{
				vrate = virate;
				isRepair = true;
			}
			if(StringUtils.isNotBlank(srs[12])){
				frate = Double.parseDouble(srs[12]);
			}else{
				frate = firate;
				isRepair = true;
			}
			if(StringUtils.isNotBlank(srs[13])){
				klzs1 = Double.parseDouble(srs[13]);
			}
			if(StringUtils.isNotBlank(srs[14])){
				klzs2 = Double.parseDouble(srs[14]);
			}
			String lastUpdateTime = srs[15],
					name = srs[16];
			String timeStr[] = lastUpdateTime.split("\\,");
			
			Calendar c = Calendar.getInstance();
			c.set(Calendar.YEAR, Integer.parseInt(timeStr[0]));
			c.set(Calendar.MONTH, FormulaCalculate.calculate(timeStr[1]).intValue());
			c.set(Calendar.DAY_OF_MONTH, Integer.parseInt(timeStr[2]));
			c.set(Calendar.HOUR_OF_DAY, Integer.parseInt(timeStr[3]));
			c.set(Calendar.MINUTE, Integer.parseInt(timeStr[4]));
			c.set(Calendar.SECOND, Integer.parseInt(timeStr[5]));
			
			item.put("name", name);
			item.put("hodds", hodds);
			item.put("hiodds", hiodds);
			item.put("vodds", vodds);
			item.put("viodds", viodds);
			item.put("hrate", hrate);
			item.put("hirate", hirate);
			item.put("vrate", vrate);
			item.put("virate", virate);
			item.put("frate", frate);
			item.put("firate", firate);
			item.put("klzs1", klzs1);
			item.put("klzs2", klzs2);
			item.put("lastUpdateTime", lastUpdateTime);
			item.put("isRepair", isRepair);
			
			pls.add(item);
		}
		return pls;
	}
	@Override
	public void pull(Map<String, JCLQMatch> nums) {
		// TODO Auto-generated method stub
		try {
			Document doc = Jsoup.connect(host+url)
				.ignoreContentType(true)
				.userAgent("Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3218.0 Safari/537.36")
				.timeout(30000).get();
			Elements es = doc.select("#MatchTable tr[id^=row_]");
			logger.debug("es.size={}",es.size());
			for(int i=0;i<es.size();i++){
				Element e = es.get(i);
				Elements tds = e.getElementsByTag("td");
				
				Element s3 = tds.get(3);
				
				String endTimeTile = s3.attr("title");
				String endTimeStr = endTimeTile.substring(5, endTimeTile.length())+":00";
				Date endTime = DateUtils.parseDate(endTimeStr,new String[]{"yyyy-MM-dd HH:mm:ss"}),
						now = new Date();
				Date matchTime = DateUtils.parseDate(endTimeTile.substring(5, 16)+tds.get(2).text()+":00",new String[]{"yyyy-MM-dd HH:mm:ss"});
				if(endTime.after(matchTime)){
					matchTime = DateUtils.addDays(matchTime, 1);
				}
				Integer status = 1;
				if(now.before(matchTime)){
					status = 0;
				}
				if("完场".equals(s3.text())|| now.getTime() > (matchTime.getTime()+3*60*60*1000)){
					status = 2;
				}
				//logger.debug("endTime={},matchTime={}",DateFormatUtils.format(endTime, "yyyy-MM-dd HH:mm:ss"),DateFormatUtils.format(matchTime, "yyyy-MM-dd HH:mm:ss"));
				
				String seq = tds.get(1).text()+":"+DateFormatUtils.format(matchTime, "yyyyMMddHHmm")+"|"+tds.get(0).text();
				JCLQMatch match = new JCLQMatch();
				match.setSeq(seq);
				match.setMatchId(tds.get(0).text());
				match.setLeague(tds.get(1).text());	
				match.setMatchTime(matchTime);
				match.setEndTime(endTime);
				match.setHostTeam(tds.get(4).text());
				match.setScore(tds.get(5).text());
				match.setGustTeam(tds.get(6).text());
				match.setStatus(status+"");
				match.setRf(tds.get(tds.size()-3).text());
				match.setDxf(tds.get(tds.size()-2).text());
				
				
				logger.debug("status={},match={}",matchTime,new Gson().toJson(match));
				
				try{
					if(status == 0 || true){
						Elements as = tds.get(tds.size()-4).getElementsByTag("a");
						String ou = as.get(1).absUrl("href"),
								xi = as.get(2).absUrl("href");
						
						Document ouDoc = Jsoup.connect(ou)
								.ignoreContentType(true)
								.userAgent("Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3218.0 Safari/537.36")
								.timeout(30000).get();
						Document xiDoc = Jsoup.connect(xi)
								.ignoreContentType(true)
								.userAgent("Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3218.0 Safari/537.36")
								.timeout(30000).get();
						
						String oddsUrl = ouDoc.select("script[src^=/info/match/getfile.aspx?file=]").get(0).absUrl("src");
						String oddsScriptStr = Jsoup.connect(oddsUrl)
								.ignoreContentType(true)
								.userAgent("Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3218.0 Safari/537.36")
								.timeout(30000).get().text();
						
						Elements leagueOrder = xiDoc.select("div[style={LeagueNoneOrBlock}] td[valign=top] tr:gt(1)");
						List<JCLQMatchOrder> hostOrder = new ArrayList<JCLQMatchOrder>(),
								guestOrder = new ArrayList<JCLQMatchOrder>();
						for(int k=0;k<8;k++){
							Elements loc = leagueOrder.get(k).children();
							JCLQMatchOrder order = new JCLQMatchOrder();
							
							String loc1 = loc.get(1).text().trim().replaceAll(" ", ""),
									loc2 = loc.get(2).text().trim().replaceAll(" ", ""),
										loc3 = loc.get(3).text().trim().replaceAll(" ", ""),
											loc4 = loc.get(4).text().trim().replaceAll(" ", ""),
												loc5 = loc.get(5).text().trim().replaceAll(" ", ""),
													loc7 = loc.get(7).text().trim().replaceAll(" ", ""),
														loc8 = loc.get(8).text().trim().replaceAll(" ", ""),
																loc0 = loc.get(0).text().replaceAll(" ", "");
							Integer total = null, win = null, lose = null, orderBy = null;
							Double attack = null, guard = null;
							if(StringUtils.isNotBlank(loc1) && StringUtils.isNumeric(loc1)){
								total = Integer.parseInt(loc1);
							}
							if(StringUtils.isNotBlank(loc2) && StringUtils.isNumeric(loc2)){
								win = Integer.parseInt(loc2);
							}
							if(StringUtils.isNotBlank(loc3) && StringUtils.isNumeric(loc3)){
								lose = Integer.parseInt(loc3);
							}
							if(StringUtils.isNotBlank(loc7) && StringUtils.isNumeric(loc7)){
								orderBy = Integer.parseInt(loc7);
							}
							if(StringUtils.isNotBlank(loc4) && StringUtils.isNotBlank(loc4)){
								attack = Double.parseDouble(loc4);
							}
							if(StringUtils.isNotBlank(loc5) && StringUtils.isNotBlank(loc5)){
								guard = Double.parseDouble(loc5);
							}
							order.setTotal(total);
							order.setWin(win);
							order.setLose(lose);
							order.setAttack(attack);
							order.setGuard(guard);
							order.setOrderBy(orderBy);
							order.setWinRate(loc8);
							order.setKindOf(loc0);
							order.setMatchId(seq);
							if(k > 3){
								order.setTeamName(match.getGustTeam());
								order.setHost("guest");
								guestOrder.add(order);
							}else{
								order.setTeamName(match.getHostTeam());
								order.setHost("host");
								hostOrder.add(order);
							}
						}
						
						JCLQMatchDatas datas = new JCLQMatchDatas();
						datas.setGustTeam(match.getGustTeam());
						datas.setHostTeam(match.getHostTeam());
						datas.setLeague(match.getLeague());
						datas.setMatchId(match.getMatchId());
						datas.setMatchTime(match.getMatchTime());
						datas.setHostOrder(hostOrder);
						datas.setGuestOrder(guestOrder);
						
						Elements records = xiDoc.select("script[language=javascript]");
						
						String scriptVar = records.get(3).data().toString();
						
						ScriptEngine engine = new ScriptEngineManager().getEngineByName("javascript");
						
						
						//4、js中未定义返回对象,这里需要将Class数据转换成字符串的数组返回，个人觉得很别扭，不知道是理解错误还是确实如此？
						//如果不这样做则直接在js后加上arrclass，cScript.evel()则返回NativeObject对象的数组
						
						Compilable compilable = (Compilable) engine;
						CompiledScript cScript = compilable.compile(scriptVar);
						
						cScript.eval();
						
						jdk.nashorn.api.scripting.ScriptObjectMirror vobj = (ScriptObjectMirror) engine.get("v_data"),
								hobj = (ScriptObjectMirror) engine.get("h_data"),
										aObj = (ScriptObjectMirror) engine.get("a_data");
						
						CompiledScript oddsScript = compilable.compile(oddsScriptStr);
						
						oddsScript.eval();
						jdk.nashorn.api.scripting.ScriptObjectMirror oddsObj = (ScriptObjectMirror) engine.get("game");
						
						
						if(oddsObj != null){
							String oText = new Gson().toJson(oddsObj.entrySet());
							JSONArray oArray = JSONArray.parseArray(oText);
							ArrayList<Map<String, Object>> pls = parseOdds(oArray);
							datas.setOdds(pls);
						}
						
						if(vobj != null){
							String vText = new Gson().toJson(vobj.entrySet());
							JSONArray vArray = JSONArray.parseArray(vText);
							ArrayList<Map<String, String>> v_data = parseZj(vArray);
							datas.setFightDatas(v_data);
						}
						
						if(hobj != null){
							String hText = new Gson().toJson(hobj.entrySet());
							JSONArray hArray = JSONArray.parseArray(hText);
							ArrayList<Map<String, String>> h_data = parseZj(hArray);
							datas.sethDatas(h_data);
						}
						
						if(aObj != null){
							String aText = new Gson().toJson(aObj.entrySet());
							JSONArray aArray = JSONArray.parseArray(aText);
							ArrayList<Map<String, String>> a_data = parseZj(aArray);
							datas.setgDatas(a_data);
						}
						match.setDatas(datas);
						logger.debug("nodes[{}] ou={},xi={}",tds.size()-4,ou,xi);
					}
				}catch(Exception ex){
					
				}
				
				nums.put(match.getSeq(), match);
			}
			//logger.debug("match={}",new Gson().toJson(matchDatas));
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@Override
	public void pull(Map<String, JCLQMatch> nums, String start, String end) {
		// TODO Auto-generated method stub
		try {
			Document doc = Jsoup.connect(host+"/jingcailanqiu/kaijiang_jclq_all.html")
				.ignoreContentType(true)
				.userAgent("Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3218.0 Safari/537.36")
				.timeout(30000)
				.referrer(host+"/jingcailanqiu/kaijiang_jclq_all.html")
				.data("__VIEWSTATE", "/wEPDwUKMTU2ODcwMzc0NWRkudlePCo+LnEIh2NmOH1WbgRexSc=")
				.data("__VIEWSTATEGENERATOR", "39D93CC6")
				.data("__EVENTVALIDATION", "/wEWBAL4oeODAwLg2ZN+AsKGtEYCjOeKxgZpmpJRcTUZfEtgPTqJ3BSsbJmANw==")
				.data("txtStartDate", start)
				.data("txtEndDate", end)
				.data("Button1", "")
				.post();
			Elements es = doc.select("tr[id^=row_]");
			logger.debug("es.size={}",es.size());
			for(int i=0;i<es.size();i++){
				Element e = es.get(i);
				Elements tds = e.getElementsByTag("td");
				
				String matchId = tds.get(0).text().replaceAll("[\u4e00-\u9fa5]", ""),
						league = tds.get(1).text(),
						matchStr = tds.get(2).text(),
						hostTeam = tds.get(3).text().trim(),
						guestTeam = tds.get(5).text().trim(),
						score = tds.get(4).text().trim(),
						rf = tds.get(7).text(),
						dxf = tds.get(10).text();
				
				Date matchTime = DateUtils.parseDate(start.substring(0,4)+"年"+matchStr+":00",new String[]{"yyyy年MM月dd日 HH:mm:ss"});
				Integer status = 2;
				Calendar c = Calendar.getInstance();
				c.setTime(matchTime);
				
				int hour = c.get(Calendar.HOUR_OF_DAY);
				Date endTime = null;
				if(hour < 6){
					c.set(Calendar.DAY_OF_YEAR, c.get(Calendar.DAY_OF_YEAR) - 1);
					c.set(Calendar.HOUR_OF_DAY, 23);
					c.set(Calendar.MINUTE, 50);
					c.set(Calendar.SECOND, 0);
					endTime = c.getTime();
				}else{
					c.set(Calendar.MINUTE, c.get(Calendar.MINUTE) - 10);
					endTime = c.getTime();
				}
				String seq = league+":"+DateFormatUtils.format(matchTime, "yyyyMMddHHmm")+"|"+matchId;
				JCLQMatch match = new JCLQMatch();
				match.setSeq(seq);
				match.setMatchId(matchId);
				match.setLeague(league);	
				match.setMatchTime(matchTime);
				match.setEndTime(endTime);
				match.setHostTeam(hostTeam);
				match.setScore(score);
				match.setGustTeam(guestTeam);
				match.setStatus(status+"");
				match.setRf(rf);
				match.setDxf(dxf);
				nums.put(match.getSeq(), match);
				logger.debug("match={}",new Gson().toJson(match));
			}
		}catch(Exception e){
			
		}
	}
	
	public static void main(String[] args) {
		Map<String,JCLQMatch> nums = new LinkedHashMap<String, JCLQMatch>();
		new JCLQMatch310WinServiceImpl().pull(nums,"2017-12-07","2017-12-09");
		//logger.info("nums={}",new Gson().toJson(nums));
		/*String timeStr[] = "2017,11-1,29,19,28,04".split("\\,");
		Calendar c = Calendar.getInstance();
		Integer year = Integer.parseInt(timeStr[0]),
				month = LotteryGuessTopicCreator.calculate(timeStr[1]).intValue(),
					day = Integer.parseInt(timeStr[2]),
						hour = Integer.parseInt(timeStr[3]),
							minute = Integer.parseInt(timeStr[4]),
								second = Integer.parseInt(timeStr[5]);
		c.set(Calendar.YEAR, year);
		c.set(Calendar.MONTH, month);
		c.set(Calendar.DAY_OF_MONTH, day);
		logger.debug("{}",c.get(Calendar.HOUR_OF_DAY));
		c.set(Calendar.HOUR_OF_DAY, hour);
		c.set(Calendar.MINUTE, minute);
		c.set(Calendar.SECOND, second);
		
		logger.debug("year={},month={},day={},hour={},minute={},second={}",year,month,day,hour,minute,second);
		logger.debug("c={}",DateFormatUtils.format(c, "yyyy-MM-dd HH:mm:ss"));*/
	}

}
