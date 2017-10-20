package com.caiqianyi.guess.match.game.spider.impl;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import com.caiqianyi.guess.game.entity.GameMatch;
import com.caiqianyi.guess.match.game.service.ILoLGuessTopicService;
import com.caiqianyi.guess.match.game.service.impl.LoLGuessTopicServiceImpl;
import com.caiqianyi.guess.match.game.spider.ILoLMatchNetworkSpider;

@Repository
public class LoLMatchNetworkSpiderImpl implements ILoLMatchNetworkSpider {

	private Logger logger = LoggerFactory
			.getLogger(LoLMatchNetworkSpiderImpl.class);
	private String url = "http://apps.game.qq.com/lol/match/apis/searchBMatchInfo.php?p8=%s&p1=%s&p9=%s&p10=%s&p6=3&page=%s&pagesize=%s&r1=retObj&_=";
	
	private String teamUrl = "http://lpl.qq.com/web201612/data/LOL_MATCH2_TEAM_TEAM%s_INFO.js";

	@Override
	public List<GameMatch> findByLeagueAndTime(String league, String season,
			Integer pageNum, Integer size, Date start, Date end) {
		// TODO Auto-generated method stub
		try {
			String s = DateFormatUtils.format(start, "yyyy-MM-dd%20HH:mm:ss"), e = DateFormatUtils
					.format(end, "yyyy-MM-dd%20HH:mm:ss");
			url = String.format(url, league, season, s, e, "" + pageNum, ""
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
			String sw = "var retObj = ";
			String body = doc
					.body()
					.text()
					.substring(sw.length());
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
				List<GameMatch> gameMatchs = new ArrayList<GameMatch>();
				for (int i = 0; i < matchs.size(); i++) {
					JSONObject data = matchs.getJSONObject(i);
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
					
					GameMatch match = new GameMatch();
					//logger.debug("bMatchName={}",data.getString("bMatchName"));
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
					if(names.length == 1){
						
						Document teamADoc =  Jsoup
						.connect(String.format(teamUrl, data.getString("TeamA")))
						.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8")
						.header("Accept-Encoding", "gzip, deflate")
						.header("Accept-Language","zh-CN,zh;q=0.9")
						.header("Host", "lpl.qq.com")
						.header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3218.0 Safari/537.36")
						.userAgent("Mozilla")
						.timeout(3000).ignoreContentType(true).get(),
						teamBDoc = Jsoup
						.connect(String.format(teamUrl, data.getString("TeamB")))
						.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8")
						.header("Accept-Encoding", "gzip, deflate")
						.header("Accept-Language","zh-CN,zh;q=0.9")
						.header("Host", "lpl.qq.com")
						.header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3218.0 Safari/537.36")
						.userAgent("Mozilla")
						.timeout(3000).ignoreContentType(true).get();
						
						//logger.debug("teamADoc.body={},teamBDoc.body={}",teamADoc.body().text(),teamBDoc.body().text());
						
						/*
						 * {"status":"0","msg":{"baseInfo":{"TeamId":"228","TeamName":"\u6e90\u8ba1\u5212\u6218\u961f","TeamEnName":"\u6e90\u8ba1\u5212\u6218\u961f","TeamDesc":"\u6e90\u8ba1\u5212\u6218\u961f","TeamLogo":"http:\/\/shp.qpic.cn\/lolwebvideo\/201501\/4f9db18b06e56c43529c26c00a260c1c\/0","CreateDate":"0000-00-00","sUrl":"","Place":"0","Leader":"","Weibo":"","Tags":"","TeamStatus":"1","RsetNameStatus":"0","RsetTeamId":"","TeamLogoDeep":"","TeamLogo450":""},"activePlayers":null,"historyPlayers":null,"teamAwards":null}},teamBDoc.body={"status":"0","msg":{"baseInfo":{"TeamId":"229","TeamName":"\u9b54\u6cd5\u5c11\u5973\u6218\u961f","TeamEnName":"\u9b54\u6cd5\u5c11\u5973\u6218\u961f","TeamDesc":"\u9b54\u6cd5\u5c11\u5973\u6218\u961f","TeamLogo":"http:\/\/shp.qpic.cn\/lolwebvideo\/201501\/5a88476d21b2afe4b03bc0b888598a4d\/0","CreateDate":"0000-00-00","sUrl":"","Place":"0","Leader":"","Weibo":"","Tags":"","TeamStatus":"1","RsetNameStatus":"0","RsetTeamId":"","TeamLogoDeep":"","TeamLogo450":""},"activePlayers":null,"historyPlayers":null,"teamAwards":null}}
						 */
						JSONObject teamAR = JSONObject.parseObject(teamADoc.body().text()),
								teamBR = JSONObject.parseObject(teamBDoc.body()
										.text());

						if (!"0".equals(teamAR.getString("status")) || !"0".equals(teamBR.getString("status"))) {
							continue;
						}
						
						names = new String[2];
						names[0] = teamAR.getJSONObject("msg").getJSONObject("baseInfo").getString("TeamName");
						names[1] = teamBR.getJSONObject("msg").getJSONObject("baseInfo").getString("TeamName");
					}
					Date matchTime = DateUtils.parseDate(
							data.getString("MatchDate"),
							new String[] { "yyyy-MM-dd HH:mm:ss" });
					
					int bestOf = data.getInteger("GameMode");
					if(bestOf == 4){
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
					match.setGroupName(data.getString("GameProcName"));//第一周
					match.setGameSystem(data.getString("GameTypeName"));//小组赛
					match.setSeason(data.getString("GameName").substring(0,4));
					match.setStatus(data.getInteger("MatchStatus"));

					if(!StringUtils.isNumeric(match.getSeason())){
						match.setSeason(DateFormatUtils.format(matchTime, "yyyy"));
						//logger.debug("match.season={}",match.getSeason());
					}
					gameMatchs.add(match);
				}
				return gameMatchs;
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) throws ParseException {
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		List<GameMatch> gameMatchs = new LoLMatchNetworkSpiderImpl().findByLeagueAndTime("", "", 1, 100,
				format.parse("2017-10-20 00:00:00"),
				format.parse("2017-10-27 00:00:00"));
		
		ILoLGuessTopicService loLGuessTopicService = new LoLGuessTopicServiceImpl();
		for(GameMatch match : gameMatchs){
			loLGuessTopicService.createTopicForSF(match);
			loLGuessTopicService.createTopicForBF(match);
			loLGuessTopicService.createTopicForFirstBlood(match);
			loLGuessTopicService.createTopicForFirstTurret(match);
		}
		
	}
}