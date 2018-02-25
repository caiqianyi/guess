package com.lebaoxun.bbs.core.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 发帖
 * @author Caiqianyi
 *
 */
public class Paste implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1042098861040986532L;
	private Integer id;
	/**
	 * 标题
	 */
	private String title;
	/**
	 * 内容
	 */
	private String content;
	/**
	 * 封面图片
	 */
	private String pictures;
	/**
	 * 来源
	 */
	private String source;
	/**
	 * 发帖人
	 */
	private Integer userId;
	/**
	 * 最后回帖
	 */
	private Integer lastPostId;
	/**
	 * 板块ID
	 */
	private Integer plateId;
	/**
	 * 收藏数
	 */
	private Integer collectCount;
	/**
	 * 转发数
	 */
	private Integer transpondCount;
	/**
	 * 点赞数
	 */
	private Integer praiseCount;
	/**
	 * 回贴数
	 */
	private Integer replyPasteCount;
	/**
	 * 浏览数
	 */
	private Integer scanCount;
	/**
	 * 是否置顶
	 */
	private boolean top;
	/**
	 * 是否高亮
	 */
	private boolean highlight;
	/**
	 * 是否删除
	 */
	private boolean deleted;
	/**
	 * 发帖时间
	 */
	private Date createTime;
	/**
	 * 最后更新时间
	 */
	private Date lastUpdateTime;
	/**
	 * 最后回贴时间
	 */
	private Date lastPostTime;
	/**
	 * 最后回复时间
	 */
	private Date lastReplyTime;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getPictures() {
		return pictures;
	}
	public void setPictures(String pictures) {
		this.pictures = pictures;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getLastPostId() {
		return lastPostId;
	}
	public void setLastPostId(Integer lastPostId) {
		this.lastPostId = lastPostId;
	}
	public Integer getPlateId() {
		return plateId;
	}
	public void setPlateId(Integer plateId) {
		this.plateId = plateId;
	}
	public Integer getCollectCount() {
		return collectCount;
	}
	public void setCollectCount(Integer collectCount) {
		this.collectCount = collectCount;
	}
	public Integer getTranspondCount() {
		return transpondCount;
	}
	public void setTranspondCount(Integer transpondCount) {
		this.transpondCount = transpondCount;
	}
	public Integer getPraiseCount() {
		return praiseCount;
	}
	public void setPraiseCount(Integer praiseCount) {
		this.praiseCount = praiseCount;
	}
	public Integer getReplyPasteCount() {
		return replyPasteCount;
	}
	public void setReplyPasteCount(Integer replyPasteCount) {
		this.replyPasteCount = replyPasteCount;
	}
	public Integer getScanCount() {
		return scanCount;
	}
	public void setScanCount(Integer scanCount) {
		this.scanCount = scanCount;
	}
	public boolean isTop() {
		return top;
	}
	public void setTop(boolean top) {
		this.top = top;
	}
	public boolean isHighlight() {
		return highlight;
	}
	public void setHighlight(boolean highlight) {
		this.highlight = highlight;
	}
	public boolean isDeleted() {
		return deleted;
	}
	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}
	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}
	public Date getLastPostTime() {
		return lastPostTime;
	}
	public void setLastPostTime(Date lastPostTime) {
		this.lastPostTime = lastPostTime;
	}
	public Date getLastReplyTime() {
		return lastReplyTime;
	}
	public void setLastReplyTime(Date lastReplyTime) {
		this.lastReplyTime = lastReplyTime;
	}
}
