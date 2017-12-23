package com.caiqianyi.guess.entity;

import java.util.Date;
import java.util.List;

/**
 * 竞猜
DROP TABLE IF EXISTS `guess_club`;
CREATE TABLE `guess_club` (
	`id` varchar(36) CHARACTER SET utf8mb4 NOT NULL COMMENT '主键',
	`clubId` int(10) DEFAULT NULL COMMENT '房间号',
	`createId` int(10) NOT NULL COMMENT '创建人',
	`maxMember` int(10) DEFAULT 50 COMMENT '最大成员数',
	`currentMember` int(10) DEFAULT 0 COMMENT '当前玩家数',
	`totalLiveness` int(10) DEFAULT 0 COMMENT '贡献度',
	`name` varchar(50) NOT NULL COMMENT '房间名',
	`password` varchar(50) DEFAULT NULL COMMENT '房间密码',
	`cardNum` int(10) DEFAULT 0 COMMENT '房间剩余房卡数',
	`icon` varchar(200) DEFAULT NULL COMMENT '房间头像',
	`notice` longtext DEFAULT NULL COMMENT '房间公告',
	`kindOf` varchar(50) NOT NULL COMMENT '房间类型  （幸运赛车/竞猜篮球）',
	`status` int(10) DEFAULT 0 COMMENT '房间状态',
	`createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `clubId` (`clubId`),
  KEY `createId` (`createId`),
  KEY `kindOf` (`kindOf`),
  KEY `status` (`status`),
  KEY `time_index` (`createTime`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='竞猜俱乐部';
 * @author Caiqianyi
 *
 */
public class GuessClub {
	private String id;
	private Integer clubId;
	/**
	 * 创建人
	 */
	private Integer createId;
	/**
	 * 最大成员数
	 */
	private Integer maxMember;
	/**
	 * 房间名
	 */
	private String name;
	/**
	 * 房间密码
	 */
	private String password;
	/**
	 * 房间剩余房卡数
	 */
	private Integer cardNum;
	/**
	 * 房间头像
	 */
	private String icon;
	/**
	 * 房间公告
	 */
	private String notice;
	/**
	 * 房间类型  （幸运赛车/竞猜篮球）
	 */
	private String kindOf;
	/**
	 * 当前玩家数
	 */
	private Integer currentMember;
	/**
	 * 房间状态
	 */
	private Integer status;
	
	/**
	 * 创建时间
	 */
	private Date createTime;
	
	/**
	 * 活跃度
	 */
	private Integer totalLiveness;
	
	private List<GuessClubMember> members;
	
	private List<GuessTemplate> templates;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Integer getCreateId() {
		return createId;
	}
	public void setCreateId(Integer createId) {
		this.createId = createId;
	}
	public Integer getMaxMember() {
		return maxMember;
	}
	public void setMaxMember(Integer maxMember) {
		this.maxMember = maxMember;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Integer getCardNum() {
		return cardNum;
	}
	public void setCardNum(Integer cardNum) {
		this.cardNum = cardNum;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getNotice() {
		return notice;
	}
	public void setNotice(String notice) {
		this.notice = notice;
	}
	public String getKindOf() {
		return kindOf;
	}
	public void setKindOf(String kindOf) {
		this.kindOf = kindOf;
	}
	public Integer getCurrentMember() {
		return currentMember;
	}
	public void setCurrentMember(Integer currentMember) {
		this.currentMember = currentMember;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public List<GuessClubMember> getMembers() {
		return members;
	}
	public void setMembers(List<GuessClubMember> members) {
		this.members = members;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Integer getClubId() {
		return clubId;
	}
	public void setClubId(Integer clubId) {
		this.clubId = clubId;
	}
	public Integer getTotalLiveness() {
		return totalLiveness;
	}
	public void setTotalLiveness(Integer totalLiveness) {
		this.totalLiveness = totalLiveness;
	}
	public List<GuessTemplate> getTemplates() {
		return templates;
	}
	public void setTemplates(List<GuessTemplate> templates) {
		this.templates = templates;
	}
}
