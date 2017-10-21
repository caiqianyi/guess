package com.caiqianyi.guess.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
/**
 * 竞猜话题
 * @author caiqianyi
 * 
DROP TABLE IF EXISTS `guess_topic`;
CREATE TABLE `guess_topic` (
  `id` varchar(36) CHARACTER SET utf8mb4 NOT NULL COMMENT '主键',
  `createBy` varchar(36) DEFAULT NULL COMMENT '创建人',
  `roomId` int(10) NOT NULL COMMENT '房间号',
  `subject` longtext NOT NULL COMMENT '竞猜题目',
  `label` varchar(200) NOT NULL COMMENT '标签分类',
  `kind` varchar(50) NOT NULL COMMENT '种类',
  `league` varchar(500) DEFAULT NULL COMMENT '联赛',
  `groupId` varchar(500) DEFAULT NULL COMMENT '比赛ID',
  `topicType` varchar(500) DEFAULT NULL COMMENT '话题类型',
  `topicId` int(10) NOT NULL COMMENT '话题ID',
  `joinCount` int(10) DEFAULT 0 COMMENT '参与人数',
  `status` int(10) DEFAULT 0 COMMENT '状态 0=进行中，1=未开奖，2=已开奖',
  `optionId` varchar(36) DEFAULT NULL COMMENT '正确选项ID',
  `createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `beginTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '话题开始竞猜时间',
  `overTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '终止竞猜时间',
  `finishTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '结算时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `roomId` (`roomId`),
  UNIQUE KEY `topicId` (`topicId`),
  KEY `createBy_index` (`createBy`),
  KEY `label_index` (`label`),
  KEY `kind_index` (`kind`),
  KEY `league_index` (`league`),
  KEY `group_id_index` (`groupId`),
  KEY `join_count_index` (`joinCount`),
  KEY `status_index` (`status`),
  KEY `create_time_index` (`createTime`),
  KEY `begin_time_index` (`beginTime`),
  KEY `over_time_index` (`overTime`),
  KEY `finish_time_index` (`finishTime`)
 ) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='竞猜表';
 *
 */
public class GuessTopic implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6338921305202682461L;
	private String id;
	private Integer topicId;
	
	/**
	 * 创建人
	 */
	private String createBy;
	/**
	 * 房间号
	 */
	private Integer roomId;
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
	private String kind;
	/**
	 * 联赛|采种
	 */
	private String league;
	/**
	 * 比赛ID|期号
	 */
	private String groupId;
	/**
	 * 话题类型 
	 */
	private String topicType;
	/**
	 * 参与人数
	 */
	private Integer joinCount;
	/**
	 * 话题状态 0=进行中，1=未开奖，2=已开奖
	 */
	private Integer status;
	/**
	 * 中奖option id
	 */
	private String optionId;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 话题开始竞猜时间
	 */
	private Date beginTime;
	/**
	 * 终止竞猜时间
	 */
	private Date overTime;
	/**
	 * 结算时间
	 */
	private Date finishTime;
	
	/**
	 * 竞猜项
	 */
	private List<GuessTopicOption> options;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
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
	public String getKind() {
		return kind;
	}
	public void setKind(String kind) {
		this.kind = kind;
	}
	
	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	public Integer getTopicId() {
		return topicId;
	}
	public void setTopicId(Integer topicId) {
		this.topicId = topicId;
	}
	public Integer getJoinCount() {
		return joinCount;
	}
	public void setJoinCount(Integer joinCount) {
		this.joinCount = joinCount;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getLeague() {
		return league;
	}
	public void setLeague(String league) {
		this.league = league;
	}
	public String getOptionId() {
		return optionId;
	}
	public void setOptionId(String optionId) {
		this.optionId = optionId;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getBeginTime() {
		return beginTime;
	}
	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}
	public Date getOverTime() {
		return overTime;
	}
	public void setOverTime(Date overTime) {
		this.overTime = overTime;
	}
	public Date getFinishTime() {
		return finishTime;
	}
	public void setFinishTime(Date finishTime) {
		this.finishTime = finishTime;
	}
	public Integer getRoomId() {
		return roomId;
	}
	public void setRoomId(Integer roomId) {
		this.roomId = roomId;
	}
	public List<GuessTopicOption> getOptions() {
		return options;
	}
	public void setOptions(List<GuessTopicOption> options) {
		this.options = options;
	}
	public String getTopicType() {
		return topicType;
	}
	public void setTopicType(String topicType) {
		this.topicType = topicType;
	}
	public String getCreateBy() {
		return createBy;
	}
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
}
