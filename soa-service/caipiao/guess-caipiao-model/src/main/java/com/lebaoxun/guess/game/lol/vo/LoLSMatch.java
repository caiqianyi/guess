package com.lebaoxun.guess.game.lol.vo;

public class LoLSMatch {
	private String sMatchId;
	private String sMatchName;
	private Integer MatchNum;
	private BattleData battleData;
	public String getsMatchId() {
		return sMatchId;
	}
	public void setsMatchId(String sMatchId) {
		this.sMatchId = sMatchId;
	}
	public String getsMatchName() {
		return sMatchName;
	}
	public void setsMatchName(String sMatchName) {
		this.sMatchName = sMatchName;
	}
	public Integer getMatchNum() {
		return MatchNum;
	}
	public void setMatchNum(Integer matchNum) {
		MatchNum = matchNum;
	}
	public BattleData getBattleData() {
		return battleData;
	}
	public void setBattleData(BattleData battleData) {
		this.battleData = battleData;
	}
}
