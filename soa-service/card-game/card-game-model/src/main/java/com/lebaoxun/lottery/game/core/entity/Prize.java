package com.lebaoxun.lottery.game.core.entity;

import java.io.Serializable;

public class Prize implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6681836559429816644L;
	private Integer id;
	/**
	 * 获奖概率
	 */
	private Double prob;
	/**
	 * 数量
	 */
	private Integer amount;
	/**
	 * 开奖数量
	 */
	private Integer drawAmount;
	/**
	 * 总数
	 */
	private Integer totalAmount;
	/**
	 * 战斗力
	 */
	private Long power;
	/**
	 * 等级
	 */
	private Integer level;
	/**
	 * 开奖
	 */
	private String lottery;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Double getProb() {
		return prob;
	}
	public void setProb(Double prob) {
		this.prob = prob;
	}
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	public Long getPower() {
		return power;
	}
	public void setPower(Long power) {
		this.power = power;
	}
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
	public String getLottery() {
		return lottery;
	}
	public void setLottery(String lottery) {
		this.lottery = lottery;
	}
	public Integer getDrawAmount() {
		return drawAmount;
	}
	public void setDrawAmount(Integer drawAmount) {
		this.drawAmount = drawAmount;
	}
	public Integer getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(Integer totalAmount) {
		this.totalAmount = totalAmount;
	}
}
