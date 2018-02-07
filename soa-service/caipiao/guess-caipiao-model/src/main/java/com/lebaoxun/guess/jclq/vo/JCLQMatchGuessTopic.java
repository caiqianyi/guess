package com.lebaoxun.guess.jclq.vo;

import java.util.Date;
import java.util.List;

import com.lebaoxun.guess.entity.GuessTopic;
import com.lebaoxun.guess.entity.GuessTopicOption;

public class JCLQMatchGuessTopic {
	private Integer id;
	private String seq;
	private String matchId;
	private String league;
	private String hostTeam;
	private String gustTeam;
	private String score;
	private String status;
	private String rf;
	private String dxf;
	private Date matchTime;
	private Date endTime;
	private Date createTime;
	
	private GuessTopic topic;
	private List<GuessTopicOption> options;
	
	public GuessTopic getTopic() {
		return topic;
	}
	public void setTopic(GuessTopic topic) {
		this.topic = topic;
	}
	public List<GuessTopicOption> getOptions() {
		return options;
	}
	public void setOptions(List<GuessTopicOption> options) {
		this.options = options;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getSeq() {
		return seq;
	}
	public void setSeq(String seq) {
		this.seq = seq;
	}
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
	public String getScore() {
		return score;
	}
	public void setScore(String score) {
		this.score = score;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getRf() {
		return rf;
	}
	public void setRf(String rf) {
		this.rf = rf;
	}
	public String getDxf() {
		return dxf;
	}
	public void setDxf(String dxf) {
		this.dxf = dxf;
	}
	public Date getMatchTime() {
		return matchTime;
	}
	public void setMatchTime(Date matchTime) {
		this.matchTime = matchTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}
