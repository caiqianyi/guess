package com.caiqianyi.guess.jclq.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 
DROP TABLE IF EXISTS `jclq_match_orders`;
CREATE TABLE `jclq_match_orders` (
  `id` varchar(36) CHARACTER SET utf8mb4 NOT NULL COMMENT '主键',
  `host` varchar(20) NOT NULL COMMENT 'host=主队，guest=客队',
  `matchId` varchar(200) NOT NULL COMMENT '比赛ID',
  `teamName` varchar(200) NOT NULL COMMENT '队伍名称',
  `kindOf` varchar(200) NOT NULL COMMENT '分类：总，主场，客场',
  `total` int(10) DEFAULT NULL COMMENT '总比赛数据',
  `win` int(10) DEFAULT NULL COMMENT '胜',
  `lose` int(10) DEFAULT NULL COMMENT '败',
  `orderBy` int(10) DEFAULT NULL COMMENT '排名',
  `attack` float(10,2) DEFAULT '0.00' COMMENT '平均进攻得分',
  `guard` float(10,2) DEFAULT '0.00' COMMENT '平均防守失分',
  `winRate` varchar(50) NOT NULL COMMENT '胜率',
  `updateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `matchId` (`matchId`),
  KEY `teamName` (`teamName`),
  KEY `createTime` (`createTime`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='篮球比赛排名数据';
 * @author DELL
 *
 */
public class JCLQMatchOrder implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1076428263107965591L;
	private String id;
	private String host;
	private String teamName;
	private String kindOf;
	private String matchId;
	private Integer total;
	private Integer win;
	private Integer lose;
	private Integer orderBy;
	private Double attack;
	private Double guard;
	private String winRate;
	private Date createTime;
	private Date updateTime;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public String getTeamName() {
		return teamName;
	}
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
	public String getMatchId() {
		return matchId;
	}
	public void setMatchId(String matchId) {
		this.matchId = matchId;
	}
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	public Integer getWin() {
		return win;
	}
	public void setWin(Integer win) {
		this.win = win;
	}
	public Integer getLose() {
		return lose;
	}
	public void setLose(Integer lose) {
		this.lose = lose;
	}
	public Integer getOrderBy() {
		return orderBy;
	}
	public void setOrderBy(Integer orderBy) {
		this.orderBy = orderBy;
	}
	public Double getAttack() {
		return attack;
	}
	public void setAttack(Double attack) {
		this.attack = attack;
	}
	public Double getGuard() {
		return guard;
	}
	public void setGuard(Double guard) {
		this.guard = guard;
	}
	public String getWinRate() {
		return winRate;
	}
	public void setWinRate(String winRate) {
		this.winRate = winRate;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public String getKindOf() {
		return kindOf;
	}
	public void setKindOf(String kindOf) {
		this.kindOf = kindOf;
	}
}
