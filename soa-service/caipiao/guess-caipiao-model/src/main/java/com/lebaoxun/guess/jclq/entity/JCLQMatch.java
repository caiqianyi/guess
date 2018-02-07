package com.lebaoxun.guess.jclq.entity;

import java.util.Date;

/**
 * 
DROP TABLE IF EXISTS `jclq_match`;
CREATE TABLE `jclq_match` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `seq` varchar(200) NOT NULL COMMENT '唯一ID',
  `matchId` varchar(200) NOT NULL COMMENT '比赛ID',
  `league` varchar(200) DEFAULT NULL COMMENT '联赛',
  `hostTeam` varchar(200) NOT NULL COMMENT '主队名',
  `gustTeam` varchar(200) NOT NULL COMMENT '客队名',
  `score` varchar(100) DEFAULT NULL COMMENT '比分',
  `status` varchar(100) NOT NULL COMMENT '状态 0=未开始，1=进行中，2=已完场',
  `rf` varchar(100) NOT NULL COMMENT '让分',
  `dxf` varchar(100) NOT NULL COMMENT '大小分',
  `matchTime` datetime NOT NULL COMMENT '比赛时间',
  `endTime` datetime NOT NULL COMMENT '竞猜结束时间',
  `createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `seq` (`seq`),
  KEY `league` (`league`),
  KEY `status` (`status`),
  KEY `matchTime` (`matchTime`),
  KEY `endTime` (`endTime`),
  KEY `createTime` (`createTime`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='篮球比赛';
 * @author Caiqianyi
 *
 */
public class JCLQMatch {
	
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
	
	private JCLQMatchDatas datas;
	
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
	public String getDxf() {
		return dxf;
	}
	public void setDxf(String dxf) {
		this.dxf = dxf;
	}
	public String getRf() {
		return rf;
	}
	public void setRf(String rf) {
		this.rf = rf;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public JCLQMatchDatas getDatas() {
		return datas;
	}
	public void setDatas(JCLQMatchDatas datas) {
		this.datas = datas;
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
}
