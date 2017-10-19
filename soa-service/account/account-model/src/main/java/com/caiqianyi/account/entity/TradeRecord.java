package com.caiqianyi.account.entity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 账户交易流水表
 * 
DROP TABLE IF EXISTS `trade_records`;
CREATE TABLE `trade_records` (
  `id` varchar(36) CHARACTER SET utf8mb4 NOT NULL COMMENT '主键',
  `userId` varchar(36) DEFAULT NULL COMMENT '用户ID',
  `referId` varchar(36) DEFAULT NULL COMMENT '涉及内部表关联ID',
  `money` float(10,2) DEFAULT '0.00' COMMENT '交易金额',
  `tradeMoney` float(10,2) DEFAULT '0.00' COMMENT '交易时余额',
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
	private String userId;
	/**
	 * 涉及ID
	 */
	private String referId;
	/**
	 * 交易金额
	 */
	private BigDecimal money;
	/**
	 * 交易时余额
	 */
	private BigDecimal tradeMoney;
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
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getReferId() {
		return referId;
	}
	public void setReferId(String referId) {
		this.referId = referId;
	}
	public BigDecimal getMoney() {
		return money;
	}
	public void setMoney(BigDecimal money) {
		this.money = money;
	}
	public BigDecimal getTradeMoney() {
		return tradeMoney;
	}
	public void setTradeMoney(BigDecimal tradeMoney) {
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
