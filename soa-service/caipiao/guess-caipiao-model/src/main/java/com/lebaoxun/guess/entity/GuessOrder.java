package com.lebaoxun.guess.entity;

import java.util.Date;

/**
 * 竞猜购买订单
DROP TABLE IF EXISTS `guess_order`;
CREATE TABLE `guess_order` (
	`id` varchar(36) CHARACTER SET utf8mb4 NOT NULL COMMENT '主键',
	`subject` longtext NOT NULL COMMENT '竞猜题目',
  	`orderNo` varchar(200) NOT NULL COMMENT '订单号',
  	`userId` int(11) NOT NULL COMMENT '用户ID',
  	`memberId` int(11) NOT NULL COMMENT '成员ID',
  	`clubId` int(10) DEFAULT NULL COMMENT '房间ID',
  	`optionId` varchar(200) NOT NULL COMMENT '购买竞猜项ID',
  	`optionName` varchar(500) NOT NULL COMMENT '选项名',
  	`kindOf` varchar(200) NOT NULL COMMENT '类别',
  	`expect` varchar(200) NOT NULL COMMENT '期号ID',
  	`diamond` int(10) DEFAULT '0.00' COMMENT '金币',
  	`score` int(10) DEFAULT NULL COMMENT '奖金',
  	`amount` int(10) NOT NULL COMMENT '倍数',
  	`topicId` int(10) NOT NULL COMMENT '话题ID',
  	`status` int(10) NOT NULL COMMENT '状态 0=未开奖，1=已返奖，-1=未中奖',
	`createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `orderNo` (`orderNo`),
  KEY `club` (`clubId`),
  KEY `user_index` (`userId`),
  KEY `memberId` (`memberId`),
  KEY `option_index` (`optionId`),
  KEY `kindOf` (`kindOf`),
  KEY `expect` (`expect`),
  KEY `topic_index` (`topicId`),
  KEY `status_index` (`status`),
  KEY `time_index` (`createTime`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='竞猜记录';
 * @author Caiqianyi
 *
 */
public class GuessOrder {
	
	private String id;
	private String subject;
	private String orderNo;
	private Integer clubId;
	private Integer memberId;
	private Integer userId;
	private Integer topicId;
	private String optionId;
	private String optionName;
	private String kindOf;
	private String expect;
	private Integer diamond;
	private Integer score;
	private Integer amount;
	private Integer status;
	private Date createTime;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
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
	public Integer getTopicId() {
		return topicId;
	}
	public void setTopicId(Integer topicId) {
		this.topicId = topicId;
	}
	public String getOptionId() {
		return optionId;
	}
	public void setOptionId(String optionId) {
		this.optionId = optionId;
	}
	public Integer getDiamond() {
		return diamond;
	}
	public void setDiamond(Integer diamond) {
		this.diamond = diamond;
	}
	public Integer getScore() {
		return score;
	}
	public void setScore(Integer score) {
		this.score = score;
	}
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getKindOf() {
		return kindOf;
	}
	public void setKindOf(String kindOf) {
		this.kindOf = kindOf;
	}
	public String getExpect() {
		return expect;
	}
	public void setExpect(String expect) {
		this.expect = expect;
	}
	public Integer getMemberId() {
		return memberId;
	}
	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getOptionName() {
		return optionName;
	}
	public void setOptionName(String optionName) {
		this.optionName = optionName;
	}
}
