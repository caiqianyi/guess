package com.lebaoxun.guess.game.lol.vo;

public class BattleData {
	
	private Integer bDragon;//大龙数
	private Integer tower;//塔数
	private String firstBlood;//一血方
	private String firstTower;//一塔方
	private Integer sDragon;//小龙数
	private String blueTeam;//蓝色方
	private String win;//是否赢 1为赢
	private Integer kills;//击杀数
	
	private BattleData hostTeam;
	private BattleData guestTeam;
	public Integer getbDragon() {
		return bDragon;
	}
	public void setbDragon(Integer bDragon) {
		this.bDragon = bDragon;
	}
	public Integer getTower() {
		return tower;
	}
	public void setTower(Integer tower) {
		this.tower = tower;
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
	public Integer getsDragon() {
		return sDragon;
	}
	public void setsDragon(Integer sDragon) {
		this.sDragon = sDragon;
	}
	public String getBlueTeam() {
		return blueTeam;
	}
	public void setBlueTeam(String blueTeam) {
		this.blueTeam = blueTeam;
	}
	public BattleData getHostTeam() {
		return hostTeam;
	}
	public void setHostTeam(BattleData hostTeam) {
		this.hostTeam = hostTeam;
	}
	public BattleData getGuestTeam() {
		return guestTeam;
	}
	public void setGuestTeam(BattleData guestTeam) {
		this.guestTeam = guestTeam;
	}
	public String getWin() {
		return win;
	}
	public void setWin(String win) {
		this.win = win;
	}
	public Integer getKills() {
		return kills;
	}
	public void setKills(Integer kills) {
		this.kills = kills;
	}
}
