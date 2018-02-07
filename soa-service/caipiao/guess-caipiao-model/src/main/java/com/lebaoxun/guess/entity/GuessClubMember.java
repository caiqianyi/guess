package com.lebaoxun.guess.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 竞猜房间成员
DROP TABLE IF EXISTS `guess_club_member`;
CREATE TABLE `guess_club_member` (
	`id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
	`nickname` varchar(200) NOT NULL COMMENT '昵称',
	`headimgurl` varchar(500) DEFAULT NULL COMMENT '户头像，最后一个数值代表正方形头像大小（有0、46、64、96、132数值可选，0代表640*640正方形头像），用户没有头像时该项为空。若用户更换头像，原有头像URL将失效。',
	`clubId` int(10) DEFAULT NULL COMMENT '房间号',
	`flag` int(10) NOT NULL COMMENT '身份标识，0=创建人，1=普通人',
	`userId` int(10) NOT NULL COMMENT '成员ID',
	`guessCount` int(10) DEFAULT 0 COMMENT '竞猜次数',
	`winCount` int(10) DEFAULT 0 COMMENT '猜中次数',
	`unauditedLiveness` int(10) DEFAULT 0 COMMENT '未审核贡献度',
	`totalLiveness` int(10) DEFAULT 0 COMMENT '贡献度',
	`status` int(10) DEFAULT 0 COMMENT '成员状态',
	`lastUnaudited` datetime DEFAULT NULL COMMENT '最后审核时间',
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
	
	private Integer id;
	private String nickname;
	private String headimgurl;
	private Integer clubId;
	private Integer userId;
	private Integer flag;
	private Integer status;
	private Integer guessCount;//竞猜次数
	private Integer winCount;//猜中次数
	/**
	 * 未审核活跃度
	 */
	private Integer unauditedLiveness;
	/**
	 * 活跃度
	 */
	private Integer totalLiveness;
	private Date lastUnaudited;
	private Date joinTime;
	private Date quitTime;
	
	/**
	 * 聊天室是否在线
	 */
	private boolean isOnline;
	
	/**
	 * 在房间中竞猜战绩
	 */
	private List<GuessOrder> orders;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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

	public Integer getUnauditedLiveness() {
		return unauditedLiveness;
	}

	public void setUnauditedLiveness(Integer unauditedLiveness) {
		this.unauditedLiveness = unauditedLiveness;
	}

	public Integer getTotalLiveness() {
		return totalLiveness;
	}

	public void setTotalLiveness(Integer totalLiveness) {
		this.totalLiveness = totalLiveness;
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

	public Date getLastUnaudited() {
		return lastUnaudited;
	}

	public void setLastUnaudited(Date lastUnaudited) {
		this.lastUnaudited = lastUnaudited;
	}

	public boolean isOnline() {
		return isOnline;
	}

	public void setOnline(boolean isOnline) {
		this.isOnline = isOnline;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getHeadimgurl() {
		return headimgurl;
	}

	public void setHeadimgurl(String headimgurl) {
		this.headimgurl = headimgurl;
	}

	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}
}
