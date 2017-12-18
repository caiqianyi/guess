package com.caiqianyi.guess.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 竞猜房间成员
DROP TABLE IF EXISTS `guess_club_member`;
CREATE TABLE `guess_club_member` (
	`id` bigint(20) NOT NULL COMMENT '主键',
	`clubId` int(10) DEFAULT NULL COMMENT '房间号',
	`userId` int(10) NOT NULL COMMENT '成员ID',
	`guessCount` int(10) DEFAULT 0 COMMENT '竞猜次数',
	`winCount` int(10) DEFAULT 0 COMMENT '猜中次数',
	`unauditedContribution` int(10) DEFAULT 0 COMMENT '未审核贡献度',
	`totalContribution` int(10) DEFAULT 0 COMMENT '贡献度',
	`status` int(10) DEFAULT 0 COMMENT '成员状态',
	`joinTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	`quitTime` datetime DEFAULT NULL COMMENT '退出时间',
  PRIMARY KEY (`id`),
  KEY `clubId` (`clubId`),
  KEY `userId` (`userId`),
  KEY `status` (`status`),
  KEY `joinTime` (`joinTime`),
  KEY `quitTime` (`quitTime`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='竞猜房间成员';
 * @author Caiqianyi
 *
 */
public class GuessClubMember implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1453539391573679786L;
	
	private Long id;
	private Integer clubId;
	private Integer userId;
	private Integer status;
	private Integer guessCount;//竞猜次数
	private Integer winCount;//猜中次数
	/**
	 * 未审核贡献度
	 */
	private Integer unauditedContribution;
	/**
	 * 贡献度
	 */
	private Integer totalContribution;
	private Date joinTime;
	private Date quitTime;
	
	/**
	 * 在房间中竞猜战绩
	 */
	private List<GuessOrder> orders;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getClubId() {
		return clubId;
	}

	public void setClubId(Integer clubId) {
		this.clubId = clubId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getGuessCount() {
		return guessCount;
	}

	public void setGuessCount(Integer guessCount) {
		this.guessCount = guessCount;
	}

	public Integer getWinCount() {
		return winCount;
	}

	public void setWinCount(Integer winCount) {
		this.winCount = winCount;
	}

	public Integer getUnauditedContribution() {
		return unauditedContribution;
	}

	public void setUnauditedContribution(Integer unauditedContribution) {
		this.unauditedContribution = unauditedContribution;
	}

	public Integer getTotalContribution() {
		return totalContribution;
	}

	public void setTotalContribution(Integer totalContribution) {
		this.totalContribution = totalContribution;
	}

	public Date getJoinTime() {
		return joinTime;
	}

	public void setJoinTime(Date joinTime) {
		this.joinTime = joinTime;
	}

	public List<GuessOrder> getOrders() {
		return orders;
	}

	public void setOrders(List<GuessOrder> orders) {
		this.orders = orders;
	}

	public Date getQuitTime() {
		return quitTime;
	}

	public void setQuitTime(Date quitTime) {
		this.quitTime = quitTime;
	}
}
