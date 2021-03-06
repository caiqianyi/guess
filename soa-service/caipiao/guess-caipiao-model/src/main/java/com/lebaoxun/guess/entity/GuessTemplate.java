package com.lebaoxun.guess.entity;

import java.util.Date;
import java.util.List;

/**
 * 竞猜模板表
DROP TABLE IF EXISTS `guess_template`;
CREATE TABLE `guess_template` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) NOT NULL COMMENT '用户ID',
  `clubId` int(10) DEFAULT NULL COMMENT '房间号',
  `label` varchar(200) DEFAULT NULL COMMENT '标签分类 如联赛',
  `subject` varchar(500) NOT NULL COMMENT '竞猜题目',
  `kindOf` varchar(50) NOT NULL COMMENT '种类',
  `topicType` varchar(500) DEFAULT NULL COMMENT '话题类型',
  `status` int(10) DEFAULT 0 COMMENT '	0=禁用，1=启用 默认是禁用 当俱乐部没有可用模板，则不会开始计费',
  `orderBy` int(10) NOT NULL COMMENT '排序值',
  `createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `userId` (`userId`),
  KEY `clubId` (`clubId`),
  KEY `kindOf` (`kindOf`),
  KEY `topicType` (`topicType`),
  KEY `status` (`status`),
  KEY `orderBy` (`orderBy`),
  KEY `createTime` (`createTime`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='竞猜模板表';
 * @author Caiqianyi
 *
 */
public class GuessTemplate {
	
	private Integer id;
	/**
	 * 竞猜题目
	 */
	private String subject;
	/**
	 * 标签分类 如联赛
	 */
	private String label;
	/**
	 * 种类
	 */
	private String kindOf;
	/**
	 * 话题类型 
	 */
	private String topicType;
	/**
	 * 排序值
	 */
	private Integer orderBy;
	/**
	 * 状态 0=未启用，1=启用
	 */
	private Integer status;
	private Integer clubId;
	private Integer userId;
	/**
	 * 创建时间
	 */
	private Date createTime;
	
	private List<GuessTemplateOption> options;
	
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public String getKindOf() {
		return kindOf;
	}
	public void setKindOf(String kindOf) {
		this.kindOf = kindOf;
	}
	public String getTopicType() {
		return topicType;
	}
	public void setTopicType(String topicType) {
		this.topicType = topicType;
	}
	public Integer getOrderBy() {
		return orderBy;
	}
	public void setOrderBy(Integer orderBy) {
		this.orderBy = orderBy;
	}
	public Integer getClubId() {
		return clubId;
	}
	public void setClubId(Integer clubId) {
		this.clubId = clubId;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public List<GuessTemplateOption> getOptions() {
		return options;
	}
	public void setOptions(List<GuessTemplateOption> options) {
		this.options = options;
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
}
