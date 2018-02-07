package com.lebaoxun.guess.game.entity;

import java.util.Date;


/**
 * 游戏比赛
DROP TABLE IF EXISTS `game_match`;
CREATE TABLE `game_match` (
	`id` varchar(36) CHARACTER SET utf8mb4 NOT NULL COMMENT '主键',
  	`gameType` varchar(500) NOT NULL COMMENT '游戏类型',
  	`season` varchar(500) NOT NULL COMMENT '赛季名',
  	`league` varchar(500) NOT NULL COMMENT '联赛名',
  	`groupName` varchar(500) NOT NULL COMMENT '小组名',
  	`gameSystem` varchar(500) NOT NULL COMMENT '比赛规则',
  	`hostTeam` varchar(500) NOT NULL COMMENT '主队名称',
  	`guestTeam` varchar(500) NOT NULL COMMENT '客队名称',
  	`score` varchar(500) NOT NULL COMMENT '比分',
  	`bestOf` int(10) default 3 COMMENT '最多打多少场 BO3',
  	`matchId` int(10) default NULL COMMENT '比赛ID',
  	`matchWin` varchar(50) DEFAULT NULL COMMENT '比赛输赢 host=主队赢 guest=客队赢',
  	`status` int(10) default 0 COMMENT '比赛状态 0=未开始，1=进行中，2=已结束',
  	`matchTime` timestamp DEFAULT CURRENT_TIMESTAMP COMMENT '比赛时间',
  	`createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `matchId` (`matchId`),
  KEY `gameTypeIndex` (`gameType`),
  KEY `seasonIndex` (`season`),
  KEY `leagueIndex` (`league`),
  KEY `groupNameIndex` (`groupName`),
  KEY `gameSystemIndex` (`gameSystem`),
  KEY `bestOfIndex` (`bestOf`),
  KEY `statusIndex` (`status`),
  KEY `matchTimeIndex` (`matchTime`),
  KEY `createTimeIndex` (`createTime`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='游戏比赛';
 * @author Caiqianyi
 *
 */
public class GameMatch {
	
	private String id;
	/**
	 * 游戏类型
	 */
	private String gameType;
	/**
	 * 赛季名 
	 */
	private String season;
	/**
	 * 联赛名
	 */
	private String league;
	/**
	 * 小组名
	 */
	private String groupName;
	/**
	 * 比赛规则
	 */
	private String gameSystem;
	/**
	 * 主队名称
	 */
	private String hostTeam;
	/**
	 * 客队名称
	 */
	private String guestTeam;
	/**
	 * 比分
	 */
	private String score;
	/**
	 * 最多打多少场 BO3
	 */
	private Integer bestOf; 
	/**
	 * 比赛ID
	 */
	private Integer matchId;
	/**
	 * 比赛状态 0=未开始，1=进行中，2=已结束
	 */
	private Integer status;
	/**
	 * 比赛时间
	 */
	private Date matchTime;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 比赛输赢 host=主队赢 guest=客队赢
	 */
	private String matchWin;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getGameType() {
		return gameType;
	}
	public void setGameType(String gameType) {
		this.gameType = gameType;
	}
	public String getSeason() {
		return season;
	}
	public void setSeason(String season) {
		this.season = season;
	}
	public String getLeague() {
		return league;
	}
	public void setLeague(String league) {
		this.league = league;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public String getGameSystem() {
		return gameSystem;
	}
	public void setGameSystem(String gameSystem) {
		this.gameSystem = gameSystem;
	}
	public String getHostTeam() {
		return hostTeam;
	}
	public void setHostTeam(String hostTeam) {
		this.hostTeam = hostTeam;
	}
	public String getGuestTeam() {
		return guestTeam;
	}
	public void setGuestTeam(String guestTeam) {
		this.guestTeam = guestTeam;
	}
	public String getScore() {
		return score;
	}
	public void setScore(String score) {
		this.score = score;
	}
	public Integer getBestOf() {
		return bestOf;
	}
	public void setBestOf(Integer bestOf) {
		this.bestOf = bestOf;
	}
	public Integer getMatchId() {
		return matchId;
	}
	public void setMatchId(Integer matchId) {
		this.matchId = matchId;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Date getMatchTime() {
		return matchTime;
	}
	public void setMatchTime(Date matchTime) {
		this.matchTime = matchTime;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getMatchWin() {
		return matchWin;
	}
	public void setMatchWin(String matchWin) {
		this.matchWin = matchWin;
	}
}
