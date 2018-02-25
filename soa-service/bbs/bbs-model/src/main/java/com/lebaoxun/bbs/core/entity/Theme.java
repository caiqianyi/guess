package com.lebaoxun.bbs.core.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 发帖主题
 * @author Caiqianyi
 *
 */
public class Theme implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4832636440715917683L;
	private Integer id;
	/**
	 * 主题词
	 */
	private String kw;
	/**
	 * 说明
	 */
	private String descr;
	/**
	 * 订阅数
	 */
	private Integer subscribes;
	/**
	 * 帖子数
	 */
	private Integer pasteCount;
	/**
	 * 分类
	 */
	private String kindOf;
	/**
	 * 标签
	 */
	private String lables;
	/**
	 * 图标
	 */
	private String logo;
	/**
	 * 标识，1=正常，2=未开放，0=申请中
	 */
	private Integer flag;
	/**
	 * 创建人
	 */
	private Integer creator;
	/**
	 * 吧主
	 */
	private Integer owner;
	private Date createTime;
	private Date lastPublishTime;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getKw() {
		return kw;
	}
	public void setKw(String kw) {
		this.kw = kw;
	}
	public String getDescr() {
		return descr;
	}
	public void setDescr(String descr) {
		this.descr = descr;
	}
	public Integer getSubscribes() {
		return subscribes;
	}
	public void setSubscribes(Integer subscribes) {
		this.subscribes = subscribes;
	}
	public Integer getPasteCount() {
		return pasteCount;
	}
	public void setPasteCount(Integer pasteCount) {
		this.pasteCount = pasteCount;
	}
	public String getKindOf() {
		return kindOf;
	}
	public void setKindOf(String kindOf) {
		this.kindOf = kindOf;
	}
	public String getLables() {
		return lables;
	}
	public void setLables(String lables) {
		this.lables = lables;
	}
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	public Integer getFlag() {
		return flag;
	}
	public void setFlag(Integer flag) {
		this.flag = flag;
	}
	public Integer getCreator() {
		return creator;
	}
	public void setCreator(Integer creator) {
		this.creator = creator;
	}
	public Integer getOwner() {
		return owner;
	}
	public void setOwner(Integer owner) {
		this.owner = owner;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getLastPublishTime() {
		return lastPublishTime;
	}
	public void setLastPublishTime(Date lastPublishTime) {
		this.lastPublishTime = lastPublishTime;
	}
	
}