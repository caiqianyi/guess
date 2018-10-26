package com.lebaoxun.lottery.game.core.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author Caiqianyi
 *
 */
public class Card implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7844940021486434457L;
	private Integer id;
	private String lottery;
	
	private String title;
	private String descr;
	private Long price;
	private Date downTime;
	
	private Integer level;
	private Integer status;
	private Integer userId;
	private Integer source;
	private Date createTime;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getLottery() {
		return lottery;
	}
	public void setLottery(String lottery) {
		this.lottery = lottery;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescr() {
		return descr;
	}
	public void setDescr(String descr) {
		this.descr = descr;
	}
	public Long getPrice() {
		return price;
	}
	public void setPrice(Long price) {
		this.price = price;
	}
	public Date getDownTime() {
		return downTime;
	}
	public void setDownTime(Date downTime) {
		this.downTime = downTime;
	}
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getSource() {
		return source;
	}
	public void setSource(Integer source) {
		this.source = source;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}