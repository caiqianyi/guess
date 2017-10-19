package com.caiqianyi.guess.entity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 竞猜购买订单
DROP TABLE IF EXISTS `guess_order`;
CREATE TABLE `guess_order` (
	`id` varchar(36) CHARACTER SET utf8mb4 NOT NULL COMMENT '主键',
  	`orderNo` varchar(500) NOT NULL COMMENT '订单号',
  	`userId` varchar(500) NOT NULL COMMENT '购买玩家ID',
  	`optionId` varchar(500) NOT NULL COMMENT '购买竞猜项ID',
  	`diamond` float(10,2) DEFAULT '0.00' COMMENT '金币',
  	`amount` int(10) NOT NULL COMMENT '倍数',
  	`topicId` int(10) NOT NULL COMMENT '话题ID',
  	`status` int(10) NOT NULL COMMENT '状态 0=未开奖，1=已返奖，-1=未中奖',
	`createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `orderNo` (`orderNo`),
  KEY `user_index` (`userId`),
  KEY `option_index` (`optionId`),
  KEY `topic_index` (`topicId`),
  KEY `status_index` (`status`),
  KEY `time_index` (`createTime`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='竞猜购买订单';
 * @author Caiqianyi
 *
 */
public class GuessOrder {
	
	private String id;
	private String orderNo;
	private String userId;
	private String optionId;
	private BigDecimal diamond;
	private Integer amount;
	private Integer topicId;
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
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
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
	public BigDecimal getDiamond() {
		return diamond;
	}
	public void setDiamond(BigDecimal diamond) {
		this.diamond = diamond;
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
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
}
