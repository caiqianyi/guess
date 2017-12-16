package com.caiqianyi.guess.jclq.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
DROP TABLE IF EXISTS `jclq_match_datas`;
CREATE TABLE `jclq_match_datas` (
	`id` varchar(36) CHARACTER SET utf8mb4 NOT NULL COMMENT '主键',
  `jclqMatchId` varchar(200) NOT NULL COMMENT '比赛ID',
  `hostOrder` longtext DEFAULT NULL COMMENT '主队排名',
  `guestOrder` longtext NOT NULL COMMENT '客队排名',
  `fightDatas` longtext NOT NULL COMMENT '两队交战',
  `hDatas` longtext NOT NULL COMMENT '主队过去战绩',
  `gDatas` longtext NOT NULL COMMENT '客队过去战绩',
  `odds` longtext NOT NULL COMMENT '欧赔',
	`createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `jclqMatchId` (`jclqMatchId`),
  KEY `createTime` (`createTime`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='篮球比赛数据';
 * @author DELL
 *
 */
public class JCLQMatchDatas implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2916816971091747450L;
	private String matchId;
	private String league;
	private String hostTeam;
	private String gustTeam;
	private ArrayList<Map<String, String>> fightDatas;
	private ArrayList<Map<String, String>> hDatas;
	private ArrayList<Map<String, String>> gDatas;
	private ArrayList<Map<String, Object>> odds;
	private List<JCLQMatchOrder> hostOrder;
	private List<JCLQMatchOrder> guestOrder;
	private Date matchTime;

	public String getMatchId() {
		return matchId;
	}
	public void setMatchId(String matchId) {
		this.matchId = matchId;
	}
	public String getLeague() {
		return league;
	}
	public void setLeague(String league) {
		this.league = league;
	}
	public String getHostTeam() {
		return hostTeam;
	}
	public void setHostTeam(String hostTeam) {
		this.hostTeam = hostTeam;
	}
	public String getGustTeam() {
		return gustTeam;
	}
	public void setGustTeam(String gustTeam) {
		this.gustTeam = gustTeam;
	}
	public Date getMatchTime() {
		return matchTime;
	}
	public void setMatchTime(Date matchTime) {
		this.matchTime = matchTime;
	}
	public ArrayList<Map<String, String>> getFightDatas() {
		return fightDatas;
	}
	public void setFightDatas(ArrayList<Map<String, String>> fightDatas) {
		this.fightDatas = fightDatas;
	}
	public ArrayList<Map<String, String>> gethDatas() {
		return hDatas;
	}
	public void sethDatas(ArrayList<Map<String, String>> hDatas) {
		this.hDatas = hDatas;
	}
	public ArrayList<Map<String, String>> getgDatas() {
		return gDatas;
	}
	public void setgDatas(ArrayList<Map<String, String>> gDatas) {
		this.gDatas = gDatas;
	}
	public ArrayList<Map<String, Object>> getOdds() {
		return odds;
	}
	public void setOdds(ArrayList<Map<String, Object>> odds) {
		this.odds = odds;
	}
	public List<JCLQMatchOrder> getHostOrder() {
		return hostOrder;
	}
	public void setHostOrder(List<JCLQMatchOrder> hostOrder) {
		this.hostOrder = hostOrder;
	}
	public List<JCLQMatchOrder> getGuestOrder() {
		return guestOrder;
	}
	public void setGuestOrder(List<JCLQMatchOrder> guestOrder) {
		this.guestOrder = guestOrder;
	}
}
