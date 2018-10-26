package com.lebaoxun.lottery.game.core.entity;

import java.util.Date;

public class CardLog {
	
	private Integer id;
	private Integer cardId;
	private Integer toCardId;
	private Long price;
	/**
	 * 1=卖，2=买，0=合成，-1=被合成
	 */
	private Integer logType;
	private Integer level;
	private Integer userId;
	private Integer tradeUser;
	private boolean result;
	
	private Date createTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCardId() {
		return cardId;
	}

	public void setCardId(Integer cardId) {
		this.cardId = cardId;
	}

	public Integer getToCardId() {
		return toCardId;
	}

	public void setToCardId(Integer toCardId) {
		this.toCardId = toCardId;
	}

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	public Integer getLogType() {
		return logType;
	}

	public void setLogType(Integer logType) {
		this.logType = logType;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getTradeUser() {
		return tradeUser;
	}

	public void setTradeUser(Integer tradeUser) {
		this.tradeUser = tradeUser;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public boolean isResult() {
		return result;
	}

	public void setResult(boolean result) {
		this.result = result;
	}
}
