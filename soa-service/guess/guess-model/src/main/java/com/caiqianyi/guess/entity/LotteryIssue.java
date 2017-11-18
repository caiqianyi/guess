package com.caiqianyi.guess.entity;

import java.util.Date;
/**
 * 彩票期号表
DROP TABLE IF EXISTS `lottery_issue`;
CREATE TABLE `lottery_issue` (
	`id` varchar(36) CHARACTER SET utf8mb4 NOT NULL COMMENT '主键',
  	`issue` varchar(500) NOT NULL COMMENT '期号ID',
  	`openLottery` varchar(500) NOT NULL COMMENT '开奖号码',
  	`kindOf` varchar(500) NOT NULL COMMENT '玩法',
  	`status` int(10) NOT NULL COMMENT '状态：0=未开始，1=进行中，2=已结束，3=已返奖',
  	`startTime` datetime DEFAULT NULL COMMENT '开始时间',
  	`endTime` datetime DEFAULT NULL COMMENT '结束时间',
	`createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `issue_kind_of` (`issue`,`kindOf`),
  KEY `issue` (`issue`),
  KEY `kindOf` (`kindOf`),
  KEY `status` (`status`),
  KEY `startTime` (`startTime`),
  KEY `endTime` (`endTime`),
  KEY `createTime` (`createTime`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='期号表';
 * @author Caiqianyi 
 *
 */
public class LotteryIssue {
	
	private String id;
	private String issue;
	private String openLottery;
	private String kindOf;
	private Integer status;
	private Date startTime;
	private Date endTime;
	private Date createTime;
	private LotteryNum lotteryNum;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getIssue() {
		return issue;
	}
	public void setIssue(String issue) {
		this.issue = issue;
	}
	public String getOpenLottery() {
		return openLottery;
	}
	public void setOpenLottery(String openLottery) {
		this.openLottery = openLottery;
	}
	public String getKindOf() {
		return kindOf;
	}
	public void setKindOf(String kindOf) {
		this.kindOf = kindOf;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
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
	public LotteryNum getLotteryNum() {
		return lotteryNum;
	}
	public void setLotteryNum(LotteryNum lotteryNum) {
		this.lotteryNum = lotteryNum;
	}
}
