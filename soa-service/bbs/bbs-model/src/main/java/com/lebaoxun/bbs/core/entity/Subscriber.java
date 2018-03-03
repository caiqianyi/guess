package com.lebaoxun.bbs.core.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 订阅者
 * @author Caiqianyi
 *
 */
public class Subscriber implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6936235484490250468L;
	private Integer id;
	private Integer userId;
	private Integer themeId;
	private Integer score;
	private Integer pos;
	private Integer pasteCount;
	private String type;
	private boolean isEnabled;
	private Date createTime;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getThemeId() {
		return themeId;
	}
	public void setThemeId(Integer themeId) {
		this.themeId = themeId;
	}
	public Integer getScore() {
		return score;
	}
	public void setScore(Integer score) {
		this.score = score;
	}
	public Integer getPos() {
		return pos;
	}
	public void setPos(Integer pos) {
		this.pos = pos;
	}
	public boolean isEnabled() {
		return isEnabled;
	}
	public void setEnabled(boolean isEnabled) {
		this.isEnabled = isEnabled;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Integer getPasteCount() {
		return pasteCount;
	}
	public void setPasteCount(Integer pasteCount) {
		this.pasteCount = pasteCount;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
}
