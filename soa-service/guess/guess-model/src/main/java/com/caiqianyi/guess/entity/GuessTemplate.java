package com.caiqianyi.guess.entity;

import java.util.Date;
import java.util.List;

/**
 * 竞猜话题模板
DROP TABLE IF EXISTS `guess_template`;
CREATE TABLE `guess_template` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `roomId` int(10) DEFAULT NULL COMMENT '房间号',
  `subject` longtext NOT NULL COMMENT '竞猜题目',
  `kindOf` varchar(50) NOT NULL COMMENT '种类',
  `seq` varchar(500) DEFAULT NULL COMMENT '比赛ID',
  `topicType` varchar(500) DEFAULT NULL COMMENT '话题类型',
  `orderBy` int(10) NOT NULL COMMENT '排序值',
  `createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `roomId` (`roomId`),
  KEY `kindOf` (`kindOf`),
  KEY `seq` (`seq`),
  KEY `topicType` (`topicType`),
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
	
	private String roomId;
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
	public String getRoomId() {
		return roomId;
	}
	public void setRoomId(String roomId) {
		this.roomId = roomId;
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
}
