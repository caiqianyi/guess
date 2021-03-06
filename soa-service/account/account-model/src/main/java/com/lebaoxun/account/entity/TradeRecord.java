package com.lebaoxun.account.entity;

import java.util.Date;

/**
 * 账户交易流水表
 * 
DROP TABLE IF EXISTS `trade_records`;
CREATE TABLE `trade_records` (
  `id` varchar(36) CHARACTER SET utf8mb4 NOT NULL COMMENT '主键',
  `userId` int(11) NOT NULL COMMENT '用户ID',
  `referId` varchar(36) DEFAULT NULL COMMENT '涉及内部表关联ID',
  `money` int(11) DEFAULT 0 COMMENT '交易账户余额',
  `tradeMoney` int(11) DEFAULT 0 COMMENT '交易金额',
  `descr` varchar(200) DEFAULT NULL COMMENT '详情说明',
  `host` varchar(200) DEFAULT NULL COMMENT '交易host',
  `status` int(11) NOT NULL COMMENT '交易状态 0 失败 1成功 2回滚',
  `tradeType` varchar(36) NOT NULL COMMENT '交易状态 0 失败 1成功 2回滚',
  `createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '交易时间',
  PRIMARY KEY (`id`),
  KEY `create_time_index` (`createTime`),
  KEY `userid_index` (`userId`),
  KEY `refer_id_index` (`referId`),
  KEY `host_index` (`host`),
  KEY `status_index` (`status`),
  KEY `trade_type_index` (`tradeType`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='账户交易流水表';
 * @author caiqianyi
 *
 */
public class TradeRecord {
	
	/**
	 * 交易流水号
	 */
	private String id;
	/**
	 * 交易账户
	 */
	private Integer userId;
	/**
	 * 涉及ID
	 */
	private String referId;
	/**
	 * 交易账户余额
	 */
	private Integer money;
	/**
	 * 交易金额
	 */
	private Integer tradeMoney;
	/**
	 * 详情说明
	 */
	private String descr;
	/**
	 * 交易ip
	 */
	private String host;
	/**
	 * 交易状态 0 失败 1成功 2回滚
	 */
	private Integer status;
	/**
	 * 交易类型  
	 */
	private String tradeType;
	/**
	 * 交易时间
	 */
	private Date createTime;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getReferId() {
		return referId;
	}
	public void setReferId(String referId) {
		this.referId = referId;
	}
	public Integer getMoney() {
		return money;
	}
	public void setMoney(Integer money) {
		this.money = money;
	}
	public Integer getTradeMoney() {
		return tradeMoney;
	}
	public void setTradeMoney(Integer tradeMoney) {
		this.tradeMoney = tradeMoney;
	}
	public String getDescr() {
		return descr;
	}
	public void setDescr(String descr) {
		this.descr = descr;
	}
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getTradeType() {
		return tradeType;
	}
	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
}
