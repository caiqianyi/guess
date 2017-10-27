package com.caiqianyi.guess.entity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 竞猜选项
DROP TABLE IF EXISTS `guess_topic_option`;
CREATE TABLE `guess_topic_option` (
	`id` varchar(36) CHARACTER SET utf8mb4 NOT NULL COMMENT '主键',
  	`topicId` int(10) NOT NULL COMMENT '话题ID',
  	`name` varchar(500) NOT NULL COMMENT '选项名',
  	`value` varchar(500) NOT NULL COMMENT '选项值（程序用）',
  	`orderBy` int(10) NOT NULL COMMENT '排序值',
  	`buyCount` int(10) NOT NULL COMMENT '购买时间',
  	`buyAmount` float(10,2) DEFAULT '0.00' COMMENT '金币',
  	`isSelected` tinyint(1) DEFAULT 0 COMMENT '是否为中奖选项',
  	`odds` float(10,2) DEFAULT '0.00' COMMENT '赔率',
	`createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `topic_id_index` (`topicId`),
  KEY `name_index` (`name`),
  KEY `value_index` (`value`),
  KEY `order_by_index` (`orderBy`),
  KEY `is_selected_index` (`isSelected`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='竞猜选项表';
 * @author Caiqianyi 
 *
 */
public class GuessTopicOption {
	
	private String id;
	/**
	 * 话题ID
	 */
	private Integer topicId;
	/**
	 * 选项名
	 */
	private String name;
	/**
	 * 选项值（程序用）
	 */
	private String value;
	/**
	 * 排序值
	 */
	private Integer orderBy;
	
	/**
	 * 购买人数
	 */
	private Integer buyCount;
	/**
	 * 购买金额
	 */
	private BigDecimal buyAmount;
	/**
	 * 是否为中奖选项	
	 */
	private Boolean isSelected;
	/**
	 * 赔率
	 */
	private BigDecimal odds;
	private Date createTime;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public Boolean getIsSelected() {
		return isSelected;
	}
	public void setIsSelected(Boolean isSelected) {
		this.isSelected = isSelected;
	}
	public BigDecimal getOdds() {
		return odds;
	}
	public void setOdds(BigDecimal odds) {
		this.odds = odds;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Integer getTopicId() {
		return topicId;
	}
	public void setTopicId(Integer topicId) {
		this.topicId = topicId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getOrderBy() {
		return orderBy;
	}
	public void setOrderBy(Integer orderBy) {
		this.orderBy = orderBy;
	}
	public Integer getBuyCount() {
		return buyCount;
	}
	public void setBuyCount(Integer buyCount) {
		this.buyCount = buyCount;
	}
	public BigDecimal getBuyAmount() {
		return buyAmount;
	}
	public void setBuyAmount(BigDecimal buyAmount) {
		this.buyAmount = buyAmount;
	}
}
