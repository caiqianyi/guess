package com.lebaoxun.lottery.game.core.entity;

import java.io.Serializable;
import java.util.Date;

public class DrawLotteryLog implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 9198678210492209311L;
	private String id;
	private Integer userId;
	private String expect;
	/**
	 * 口令状态 0=未使用，1=使用
	 */
	private Integer status;
	/**
	 * 获取途径 GET,BUY,SYS_GET
	 */
	private String type;
	/**
	 * 获得奖品
	 */
	private String lottery;
	/**
	 * 抽奖类型 ONE,TEN
	 */
	private String drawType;
	/**
	 * 操作ID
	 */
	private String handle;
	/**
	 * 获取时间
	 */
	private Date createTime;
	/**
	 * 使用时间
	 */
	private Date drawTime;
	
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
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getLottery() {
		return lottery;
	}
	public void setLottery(String lottery) {
		this.lottery = lottery;
	}
	public String getDrawType() {
		return drawType;
	}
	public void setDrawType(String drawType) {
		this.drawType = drawType;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getHandle() {
		return handle;
	}
	public void setHandle(String handle) {
		this.handle = handle;
	}
	public Date getDrawTime() {
		return drawTime;
	}
	public void setDrawTime(Date drawTime) {
		this.drawTime = drawTime;
	}
	public String getExpect() {
		return expect;
	}
	public void setExpect(String expect) {
		this.expect = expect;
	}
}
