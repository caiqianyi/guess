package com.lebaoxun.guess.caipiao.match.game.spider.impl;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang.time.DateUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.lebaoxun.guess.caipiao.match.game.service.ILoLGuessTopicService;
import com.lebaoxun.guess.caipiao.match.game.service.impl.LoLGuessTopicServiceImpl;
import com.lebaoxun.guess.caipiao.match.game.spider.ILoLMatchNetworkSpider;
import com.lebaoxun.guess.game.entity.GameMatch;
import com.lebaoxun.guess.game.lol.vo.BattleData;
import com.lebaoxun.guess.game.lol.vo.LoLSMatch;

@Repository
public class LoLMatchNetworkSpiderImpl implements ILoLMatchNetworkSpider {

	private Logger logger = LoggerFactory
			.getLogger(LoLMatchNetworkSpiderImpl.class);
	
	private final String url = "http://apps.game.qq.com/lol/match/apis/searchBMatchInfo_bak.php?p8=%s&p1=%s&p9=%s&p10=%s&p6=3&page=%s&pagesize=%s&r1=retObj&_=";

	private final String teamUrl = "http://lpl.qq.com/web201612/data/LOL_MATCH2_TEAM_TEAM%s_INFO.js";
	
	private final String searchBMatchInfo = "http://apps.game.qq.com//lol/match/apis/searchBMatchInfo.php?p0=%s&r1=bMatchObj&_=%s";

	private final String searchSMatchList = "http://apps.game.qq.com/lol/match/apis/searchSMatchList.php?p0=%s&r1=SMatchListArr&_=%s";

	private final String searchMatchInfo = "http://apps.game.qq.com/lol/match/apis/searchMatchInfo_s.php?p0=%s&r1=MatchInfo&_=%s";
	
	@Override
	public Map<String,String> getGuessResultByMatch(GameMatch match,String matchId){
		
		if(match != null && match.getStatus() == 3){
			
			List<LoLSMatch> battleDatas = this.findSMatchByMatchId(matchId);
			Map<String,String> result = new LinkedHashMap<String,String>();
			String sfOp = "F",fbOp = "",ftOp = "",sodOp = "",bfOp = match.getScore();
			if("host".equals(match.getMatchWin())){
				sfOp = "S";
			}
			if(battleDatas != null && !battleDatas.isEmpty()){
				for(int i=0;i<battleDatas.size();i++){
					LoLSMatch lsm = battleDatas.get(i);
					BattleData battleData = lsm.getBattleData();
					fbOp += "host".equals(battleData.getFirstBlood()) ? "S" : "F";
					ftOp += "host".equals(battleData.getFirstTower()) ? "S" : "F";
					sodOp += battleData.getKills() % 2 != 0 ? "S" : "F";
					if(i<battleDatas.size()-1){
						fbOp+="|";
						ftOp+="|";
						sodOp+="|";
					}
				}
			}
			result.put("SF", sfOp);
			result.put("FirstBlood", fbOp);
			result.put("FirstTurret", ftOp);
			result.put("SOD", sodOp);
			result.put("BF", bfOp);
			result.put("lolMatch", match.getHostTeam()+" VS "+match.getGuestTeam());
			logger.debug("result={}",result);
			return result;
		}
		return null;
	}
	
	@Override
	public BattleData findMatchInfoBySMatchId(String sMatchId) {
		// TODO Auto-generated method stub
		try {
			Document doc = Jsoup
					.connect(String.format(searchMatchInfo, sMatchId, "" + System.currentTimeMillis()))
					.userAgent("Mozilla")
					.header("accept-language", "zh-CN,zh;q=0.8")
					.header("referer",
							"https://qs.888.qq.com/m_qq/es/es.lol.html?channelName=landing")
					.header("user-agent",
							"Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.87 Safari/537.36")
					.header("x-requested-with", "XMLHttpRequest")
					.header("Content-Type", "text/html;charset=UTF-8")
					.timeout(3000).get();
			
			String sw = "var MatchInfo=";
			String body = doc.body().text()
					.substring(sw.length(), doc.body().text().length() - 1);
			JSONObject datas = JSONObject.parseObject(body);
			String status = datas.getString("status");
			if ("0".equals(status)) {
				JSONObject jsonB = datas.getJSONObject("msg").getJSONObject("battleInfo").getJSONObject("BattleData");
				JSONObject sMatchInfoJson = datas.getJSONObject("msg").getJSONObject("sMatchInfo");
				
				
				Integer matchWin = sMatchInfoJson.getInteger("MatchWin");
				String blueTeam = sMatchInfoJson.getString("BlueTeam").equals(sMatchInfoJson.get("TeamA"))? "host" :"guest";
				JSONObject leftData = jsonB.getJSONObject("left"),
						rightData = jsonB.getJSONObject("right");
				Integer leftKills = 0,rightKills = 0;
				
				JSONArray leftPlayers = leftData.getJSONArray("players"),
						 rightPlayers = rightData.getJSONArray("players");
				for(int i=0;i<leftPlayers.size();i++){
					JSONObject leftItem = leftPlayers.getJSONObject(i),
							rightItem = rightPlayers.getJSONObject(i);
					Integer leftKill = leftItem.getInteger("kill"),
							rightKill = rightItem.getInteger("kill");
					leftKills += leftKill;
					rightKills += rightKill;
				}
				
				BattleData left = new BattleData();
				left.setbDragon(leftData.getInteger("b-dragon"));//大龙数
				left.setTower(leftData.getInteger("tower"));//塔数
				left.setFirstBlood(leftData.getString("firstBlood"));//是否一血 1为一血
				left.setFirstTower(leftData.getString("firstTower"));//是否一塔 1为一血
				left.setsDragon(leftData.getInteger("s-dragon"));//小龙数
				left.setKills(leftKills);
				
				BattleData right = new BattleData();
				right.setbDragon(rightData.getInteger("b-dragon"));//大龙数
				right.setTower(rightData.getInteger("tower"));//塔数
				right.setFirstBlood(rightData.getString("firstBlood"));//是否一血 1为一血
				right.setFirstTower(rightData.getString("firstTower"));//是否一塔 1为一血
				right.setsDragon(rightData.getInteger("s-dragon"));//小龙数
				right.setKills(rightKills);
				
				BattleData battleData = new BattleData();
				battleData.setbDragon(left.getbDragon()+right.getbDragon());//大龙数
				battleData.setTower(left.getTower()+right.getTower());//塔数
				battleData.setsDragon(left.getsDragon()+right.getsDragon());//小龙数
				
				if(matchWin == 2 && "right".equals(jsonB.getString("game-win"))
						|| (matchWin == 1 && "left".equals(jsonB.getString("game-win")))){
					battleData.setFirstBlood("1".equals(left.getFirstBlood())?"host":"guest");
					battleData.setFirstTower("1".equals(left.getFirstTower())?"host":"guest");
					battleData.setHostTeam(left);
					battleData.setGuestTeam(right);
				}else{
					battleData.setFirstBlood("1".equals(left.getFirstBlood())?"guest":"host");
					battleData.setFirstTower("1".equals(left.getFirstTower())?"guest":"host");
					battleData.setHostTeam(right);
					battleData.setGuestTeam(left);
				}
				
				battleData.setWin(matchWin == 2?"guest":"host");
				battleData.setBlueTeam(blueTeam);
				battleData.setKills(leftKills+rightKills);
				
				logger.debug("game-win={},matchWin={},blueTeam={}",jsonB.getString("game-win"),matchWin,blueTeam);
				logger.debug("battleData={}",new Gson().toJson(battleData));
				return battleData;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public List<LoLSMatch> findSMatchByMatchId(String matchId) {
		// TODO Auto-generated method stub
		List<LoLSMatch> sMatch = new ArrayList<LoLSMatch>();

		try {
			String url = String.format(searchSMatchList, matchId,
					"" + System.currentTimeMillis());
			logger.debug("url={}", url);
			Document doc = Jsoup
					.connect(url)
					.userAgent("Mozilla")
					.header("accept-language", "zh-CN,zh;q=0.8")
					.header("referer",
							"https://qs.888.qq.com/m_qq/es/es.lol.html?channelName=landing")
					.header("user-agent",
							"Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.87 Safari/537.36")
					.header("x-requested-with", "XMLHttpRequest")
					.header("Content-Type", "text/html;charset=UTF-8")
					.timeout(3000).get();

			String sw = "var SMatchListArr=";
			String body = doc.body().text()
					.substring(sw.length(), doc.body().text().length() - 1);
			JSONObject datas = JSONObject.parseObject(body);
			String status = datas.getString("status");

			if ("0".equals(status)) {
				JSONArray matchs =  datas.getJSONArray("msg");
				for (int i = 0; i < matchs.size(); i++) {
					LoLSMatch lsm = matchs.getObject(i, LoLSMatch.class);
					lsm.setBattleData(findMatchInfoBySMatchId(lsm.getsMatchId()));
					sMatch.add(lsm);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sMatch;
	}
	
	@Override
	public GameMatch findByMatchId(String matchId) {
		// TODO Auto-generated method stub
		try {
			Document doc = Jsoup
					.connect(String.format(searchBMatchInfo, matchId, "" + System.currentTimeMillis()))
					.userAgent("Mozilla")
					.header("accept-language", "zh-CN,zh;q=0.8")
					.header("referer",
							"https://qs.888.qq.com/m_qq/es/es.lol.html?channelName=landing")
					.header("user-agent",
							"Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.87 Safari/537.36")
					.header("x-requested-with", "XMLHttpRequest")
					.header("Content-Type", "text/html;charset=UTF-8")
					.timeout(3000).get();
			
			String sw = "var bMatchObj = ";
			String body = doc.body().text()
					.substring(sw.length());
			JSONObject datas = JSONObject.parseObject(body);
			String status = datas.getString("status");
			if ("0".equals(status)) {
				JSONArray result = datas.getJSONObject("msg").getJSONArray("result");
				if(result != null && result.size() == 1){
					return findByMatchId(result.getJSONObject(0)); 
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private GameMatch findByMatchId(JSONObject data) {
		/*
		 * { "bMatchId": "2672", "bMatchName": "EDG VS JDG",
		 * "GameId": "49", "GameName": "2017职业联赛", "GameTypeId":
		 * "7", "GameMode": "3", "GameTypeName": "夏季赛常规赛",
		 * "GameProcId": "160", "GameProcName": "第六周", "TeamA": "1",
		 * "ScoreA": "2", "TeamB": "29", "ScoreB": "0", "MatchDate":
		 * "2017-07-20 17:00:00", "MatchStatus": "3", "MatchWin":
		 * "1", "iQTMatchId": "24290", "bGameId": "5", "NewsId":
		 * "11900", "HighlightsId": "11899", "Video1": "0",
		 * "Video2": "0", "Video3": "764502578", "Chat1":
		 * "94961178", "Chat2":
		 * "http://zhibojiasu.tuwan.com/EDGvsJDG0720live.htm",
		 * "Chat3": "", "News1":
		 * "http://zhibojiasu.tuwan.com/EDGvsJDG0720news.html",
		 * "News2":
		 * "http://zhibojiasu.tuwan.com/EDGvsJDG0720zhanbao.html",
		 * "News3": "" }
		 */

		try {
			GameMatch match = new GameMatch();
			// logger.debug("bMatchName={}",data.getString("bMatchName"));
			String mathName = data.getString("bMatchName");
			String names[] = mathName.replaceAll(" ", "")
					.replaceAll("VS", "vs").split("vs");
	
			logger.debug(
					"GameId={},GameName={},GameTypeId={},GameMode={},GameTypeName={},GameProcId={},GameProcName={}",
					data.getString("GameId"),
					data.getString("GameName"),
					data.getString("GameTypeId"),
					data.getInteger("GameMode"),
					data.getString("GameTypeName"),
					data.getString("GameProcId"),
					data.getString("GameProcName"));
			if (names.length == 1) {
	
				Document teamADoc = Jsoup
						.connect(
								String.format(teamUrl,
										data.getString("TeamA")))
						.header("Accept",
								"text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8")
						.header("Accept-Encoding", "gzip, deflate")
						.header("Accept-Language", "zh-CN,zh;q=0.9")
						.header("Host", "lpl.qq.com")
						.header("User-Agent",
								"Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3218.0 Safari/537.36")
						.userAgent("Mozilla").timeout(3000)
						.ignoreContentType(true).get(), teamBDoc = Jsoup
						.connect(
								String.format(teamUrl,
										data.getString("TeamB")))
						.header("Accept",
								"text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8")
						.header("Accept-Encoding", "gzip, deflate")
						.header("Accept-Language", "zh-CN,zh;q=0.9")
						.header("Host", "lpl.qq.com")
						.header("User-Agent",
								"Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3218.0 Safari/537.36")
						.userAgent("Mozilla").timeout(3000)
						.ignoreContentType(true).get();
	
				// logger.debug("teamADoc.body={},teamBDoc.body={}",teamADoc.body().text(),teamBDoc.body().text());
	
				/*
				 * {"status":"0","msg":{"baseInfo":{"TeamId":"228",
				 * "TeamName"
				 * :"\u6e90\u8ba1\u5212\u6218\u961f","TeamEnName"
				 * :"\u6e90\u8ba1\u5212\u6218\u961f"
				 * ,"TeamDesc":"\u6e90\u8ba1\u5212\u6218\u961f"
				 * ,"TeamLogo":
				 * "http:\/\/shp.qpic.cn\/lolwebvideo\/201501\/4f9db18b06e56c43529c26c00a260c1c\/0","CreateDate":"0000-00-00","sUrl":"","Place":"0","Leader":"","Weibo":"","Tags":"","TeamStatus":"1","RsetNameStatus":"0","RsetTeamId":"","TeamLogoDeep":"","TeamLogo450":""},"activePlayers":null,"historyPlayers":null,"teamAwards":null}},teamBDoc.body={"status":"0","msg":{"baseInfo":{"TeamId":"229","TeamName":"\u9b54\u6cd5\u5c11\u5973\u6218\u961f","TeamEnName":"\u9b54\u6cd5\u5c11\u5973\u6218\u961f","TeamDesc":"\u9b54\u6cd5\u5c11\u5973\u6218\u961f","TeamLogo":"http:\/\/shp.qpic.cn\/lolwebvideo\/201501\/5a88476d21b2afe4b03bc0b888598a4d\/0","CreateDate":"0000-00-00","sUrl":"","Place":"0","Leader":"","Weibo":"","Tags":"","TeamStatus":"1","RsetNameStatus":"0","RsetTeamId":"","TeamLogoDeep":"","TeamLogo450":""},"activePlayers":null,"historyPlayers":null,"teamAwards":null}}
				 */
				JSONObject teamAR = JSONObject.parseObject(teamADoc
						.body().text()), teamBR = JSONObject
						.parseObject(teamBDoc.body().text());
	
				if (!"0".equals(teamAR.getString("status"))
						|| !"0".equals(teamBR.getString("status"))) {
					return null;
				}
	
				names = new String[2];
				names[0] = teamAR.getJSONObject("msg")
						.getJSONObject("baseInfo")
						.getString("TeamName");
				names[1] = teamBR.getJSONObject("msg")
						.getJSONObject("baseInfo")
						.getString("TeamName");
			}
			Date matchTime = DateUtils.parseDate(
					data.getString("MatchDate"),
					new String[] { "yyyy-MM-dd HH:mm:ss" });
	
			int bestOf = data.getInteger("GameMode");
			if (bestOf == 4) {
				bestOf = 5;
			}
			match.setBestOf(bestOf);
			match.setCreateTime(new Date());
			match.setGameType("LOL");
			match.setHostTeam(names[0]);
			match.setGuestTeam(names[1]);
			match.setMatchId(data.getInteger("bMatchId"));
			match.setMatchTime(matchTime);
			match.setScore(data.getString("ScoreA") + ":"
					+ data.getString("ScoreB"));
			match.setLeague(data.getString("GameName"));
			match.setGroupName(data.getString("GameProcName"));// 第一周
			match.setGameSystem(data.getString("GameTypeName"));// 小组赛
			match.setSeason(data.getString("GameName").substring(0, 4));
			match.setStatus(data.getInteger("MatchStatus"));
			Integer win = data.getInteger("MatchWin");
			String matchWin = null;
			if(win == 1){
				matchWin = "host";
			}
			if(win == 2){
				matchWin = "guest";
			}
			match.setMatchWin(matchWin);
			if (!StringUtils.isNumeric(match.getSeason())) {
				match.setSeason(DateFormatUtils.format(matchTime,
						"yyyy"));
			}
			// logger.debug("match.season={}",match.getSeason());
			return match;
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		return null;
	}
	@Override
	public List<GameMatch> findByLeagueAndTime(String league, String season,
			Integer pageNum, Integer size, Date start, Date end) {
		// TODO Auto-generated method stub
		List<GameMatch> gameMatchs = new ArrayList<GameMatch>();
		try {
			String s = DateFormatUtils.format(start, "yyyy-MM-dd%20HH:mm:ss"), e = DateFormatUtils
					.format(end, "yyyy-MM-dd%20HH:mm:ss");
			logger.debug("findByLeagueAndTime|league={},season={},s={},e={},pageNum={},size={}",league,season,s,e,pageNum,size);
			String url = String.format(this.url, league, season, s, e, "" + pageNum, ""
					+ size);
			logger.debug("url={}", url);
			Document doc = Jsoup
					.connect(url)
					.userAgent("Mozilla")
					.header("accept-language", "zh-CN,zh;q=0.8")
					.header("referer",
							"https://qs.888.qq.com/m_qq/es/es.lol.html?channelName=landing")
					.header("user-agent",
							"Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.87 Safari/537.36")
					.header("x-requested-with", "XMLHttpRequest")
					.header("Content-Type", "text/html;charset=UTF-8")
					.timeout(3000).get();
			String sw = "var retObj=";
			String body = doc.body().text().substring(sw.length(),doc.body().text().length()-1);
			logger.debug("data={}", body);
			JSONObject datas = JSONObject.parseObject(body);
			String status = datas.getString("status");

			if ("0".equals(status)) {
				JSONObject msg = (JSONObject) datas.get("msg");
				String total = msg.getString("total"), totalpage = msg
						.getString("totalpage"), page = msg.getString("page");
				logger.info("total={},totalpage={},page={}", total, totalpage,
						page);

				JSONArray matchs = msg.getJSONArray("result");
				logger.info("matchs.size={}", matchs.size());
				for (int i = 0; i < matchs.size(); i++) {
					JSONObject data = matchs.getJSONObject(i);
					gameMatchs.add(findByMatchId(data));
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return gameMatchs;
	}

	public static void main(String[] args) throws ParseException {
		
		ILoLMatchNetworkSpider loLMatchNetworkSpider = new LoLMatchNetworkSpiderImpl();
		ILoLGuessTopicService loLGuessTopicService = new LoLGuessTopicServiceImpl();
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		List<GameMatch> gameMatchs = loLMatchNetworkSpider
				.findByLeagueAndTime("5", "95", 1, 50,
						format.parse("2018-03-20 00:00:00"),
						format.parse("2018-03-28 00:00:00"));

		for (GameMatch match : gameMatchs) {
			System.out.println(new Gson().toJson(match));
			/*loLGuessTopicService.createTopicForSF(match, null, null);
			loLGuessTopicService.createTopicForBF(match, null, null);
			loLGuessTopicService.createTopicForFirstBlood(match, null, null);
			loLGuessTopicService.createTopicForFirstTurret(match, null, null);
			loLGuessTopicService.createTopicForSOD(match, null, null);
			loLMatchNetworkSpider.getGuessResultByMatch(match,match.getMatchId()+"");*/
		}
		/*String matchId = "2822";
		GameMatch match = loLMatchNetworkSpider.findByMatchId(matchId);
		loLMatchNetworkSpider.getGuessResultByMatch(match,matchId+"");*/
		//loLMatchNetworkSpider.findByMatchId("2823");
	}
}