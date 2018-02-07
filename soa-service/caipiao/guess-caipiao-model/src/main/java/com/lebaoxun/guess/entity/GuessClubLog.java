package com.lebaoxun.guess.entity;

import java.util.Date;

/**
 * 俱乐部交易记录
DROP TABLE IF EXISTS `guess_club_log`;
CREATE TABLE `guess_club_log` (
	`id` varchar(36) CHARACTER SET utf8mb4 NOT NULL COMMENT '主键',
	`clubId` int(10) DEFAULT NULL COMMENT '俱乐部',
	`seq` varchar(200) DEFAULT NULL COMMENT '唯一标识',
	`descr` varchar(200) DEFAULT NULL COMMENT '详情说明',
	`cardNum` int(11) NOT NULL COMMENT '房卡',
	`tradeType` int(10) NOT NULL COMMENT '交易状态 0扣卡,1充卡',
	`createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `seq` (`seq`),
  KEY `clubId` (`clubId`),
  KEY `tradeType` (`tradeType`),
  KEY `time_index` (`createTime`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='俱乐部交易记录';
 * @author Caiqianyi
 *
 */
public class GuessClubLog {
	private String id;
	private Integer clubId;
	private String seq;
	private String descr;
	/**
	 * 房间剩余房卡数
	 */
	private Integer cardNum;
	private Integer tradeType;
	/**
	 * 创建时间
	 */
	private Date createTime;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Integer getClubId() {
		return clubId;
	}
	public void setClubId(Integer clubId) {
		this.clubId = clubId;
	}
	public String getDescr() {
		return descr;
	}
	public void setDescr(String descr) {
		this.descr = descr;
	}
	public Integer getCardNum() {
		return cardNum;
	}
	public void setCardNum(Integer cardNum) {
		this.cardNum = cardNum;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Integer getTradeType() {
		return tradeType;
	}
	public void setTradeType(Integer tradeType) {
		this.tradeType = tradeType;
	}
	public String getSeq() {
		return seq;
	}
	public void setSeq(String seq) {
		this.seq = seq;
	}
}
