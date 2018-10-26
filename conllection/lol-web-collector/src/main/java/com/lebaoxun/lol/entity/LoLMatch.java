package com.lebaoxun.lol.entity;

import java.io.Serializable;
import java.util.Date;

public class LoLMatch implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3023824212002603701L;
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
	 * 比赛输赢 host=主队赢 guest=客队赢
	 */
	private String matchWin;
	/**
	 * 一血方
	 */
	private String firstBlood;
	/**
	 * 一塔方
	 */
	private String firstTower;
	/**
	 * 击杀数
	 */
	private String kills;
	/**
	 * 大龙数
	 */
	private String bDragon;
	/**
	 * 塔数
	 */
	private String tower;
	/**
	 * 小龙数
	 */
	private String sDragon;
	/**
	 * 比赛时间
	 */
	private Date matchTime;
	/**
	 * 创建时间
	 */
	private Date createTime;
	
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
	public String getFirstBlood() {
		return firstBlood;
	}
	public void setFirstBlood(String firstBlood) {
		this.firstBlood = firstBlood;
	}
	public String getFirstTower() {
		return firstTower;
	}
	public void setFirstTower(String firstTower) {
		this.firstTower = firstTower;
	}
	public String getKills() {
		return kills;
	}
	public void setKills(String kills) {
		this.kills = kills;
	}
	public String getbDragon() {
		return bDragon;
	}
	public void setbDragon(String bDragon) {
		this.bDragon = bDragon;
	}
	public String getTower() {
		return tower;
	}
	public void setTower(String tower) {
		this.tower = tower;
	}
	public String getsDragon() {
		return sDragon;
	}
	public void setsDragon(String sDragon) {
		this.sDragon = sDragon;
	}
}
