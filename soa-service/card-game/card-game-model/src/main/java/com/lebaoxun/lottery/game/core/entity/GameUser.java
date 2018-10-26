package com.lebaoxun.lottery.game.core.entity;

import java.io.Serializable;

public class GameUser implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4382709026355965032L;
	private String id;
	/**
	 * 玩家ID
	 */
	private Integer userId;
	/**
	 * 抽奖次数
	 */
	private Integer drawCount;
	/**
	 * 战力总和
	 */
	private Integer power;
	/**
	 * 奖品数
	 */
	private Integer prizeCount;
	
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getDrawCount() {
		return drawCount;
	}
	public void setDrawCount(Integer drawCount) {
		this.drawCount = drawCount;
	}
	public Integer getPower() {
		return power;
	}
	public void setPower(Integer power) {
		this.power = power;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Integer getPrizeCount() {
		return prizeCount;
	}
	public void setPrizeCount(Integer prizeCount) {
		this.prizeCount = prizeCount;
	}
}
