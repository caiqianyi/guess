package com.lebaoxun.bbs.core.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 回帖
 * @author Caiqianyi
 *
 */
public class PastePost implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5286766485820825864L;
	private Integer id;
	/**
	 * 内容
	 */
	private String content;
	/**
	 * 回帖人
	 */
	private Integer userId;
	/**
	 * 原帖ID
	 */
	private Integer pasteId;
	/**
	 * 来源
	 */
	private String source;
	/**
	 * 是否为创建人
	 */
	private Boolean isCreator;
	/**
	 * 贴层
	 */
	private Integer tier;
	/**
	 * 点赞数
	 */
	private Integer praiseCount;
	/**
	 * 回复数
	 */
	private Integer replyCount;
	/**
	 * 发帖时间
	 */
	private Date createTime;
	/**
	 * 最后回复人
	 */
	private Integer lastReplyId;
	/**
	 * 最后回复时间
	 */
	private Date lastReplyTime;
	
	private final static int DENOMINATOR = 100;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getPasteId() {
		return pasteId;
	}
	public void setPasteId(Integer pasteId) {
		this.pasteId = pasteId;
	}
	public Boolean getIsCreator() {
		return isCreator;
	}
	public void setIsCreator(Boolean isCreator) {
		this.isCreator = isCreator;
	}
	public Integer getTier() {
		return tier;
	}
	public void setTier(Integer tier) {
		this.tier = tier;
	}
	public Integer getPraiseCount() {
		return praiseCount;
	}
	public void setPraiseCount(Integer praiseCount) {
		this.praiseCount = praiseCount;
	}
	public Integer getReplyCount() {
		return replyCount;
	}
	public void setReplyCount(Integer replyCount) {
		this.replyCount = replyCount;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Integer getLastReplyId() {
		return lastReplyId;
	}
	public void setLastReplyId(Integer lastReplyId) {
		this.lastReplyId = lastReplyId;
	}
	public Date getLastReplyTime() {
		return lastReplyTime;
	}
	public void setLastReplyTime(Date lastReplyTime) {
		this.lastReplyTime = lastReplyTime;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getSubmeter() {
		return modulo(pasteId);
	}
	/**
	 * 分表取模
	 * @param agentId
	 * @return
	 */
	public static String modulo(Integer pasteId){
		String remainder = String.valueOf(pasteId % DENOMINATOR);
		int length = remainder.length();
		String str = "";
		for(int i = 0;i< 3-length; i++){
			str += "0";
		}
		return str + remainder;
	}
	
	public static void main(String[] args) {
		for(int i=0;i<DENOMINATOR;i++){
			String flag = modulo(i);
			String sql = "DROP TABLE IF EXISTS `paste_post_"+flag+"`;\n"+
			"CREATE TABLE `paste_post_"+flag+"` (\n"+
			"`id` int(11) NOT NULL AUTO_INCREMENT,\n"+
			"`content` varchar(500) NOT NULL COMMENT '内容',\n"+
			"`userId` int(11) NOT NULL COMMENT '回帖人',\n"+
			"`pasteId` int(11) DEFAULT NULL COMMENT '原帖ID',\n"+
			"`source` varchar(500) NOT NULL COMMENT '来源',\n"+
			"`isCreator` tinyint(1) DEFAULT '0' COMMENT '是否为创建人',\n"+
			"`tier` int(11) NOT NULL COMMENT '回贴层',\n"+
			"`praiseCount` int(11) DEFAULT '0' COMMENT '点赞数',\n"+
			"`replyCount` int(11) DEFAULT '0' COMMENT '回复数',\n"+
			"`createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',\n"+
			"`lastReplyId` int(11) DEFAULT NULL COMMENT '最后回复记录',\n"+
			"`lastReplyTime` datetime DEFAULT NULL COMMENT '最后回复时间',\n"+
			"PRIMARY KEY (`id`),\n"+
			"KEY `userId` (`userId`) USING BTREE,\n"+
			"KEY `pasteId` (`pasteId`) USING BTREE,\n"+
			"KEY `source` (`source`) USING BTREE,\n"+
			"KEY `isCreator` (`isCreator`) USING BTREE,\n"+
			"KEY `tier` (`tier`) USING BTREE,\n"+
			"KEY `praiseCount` (`praiseCount`) USING BTREE,\n"+
			"KEY `replyCount` (`replyCount`) USING BTREE,\n"+
			"KEY `lastReplyId` (`lastReplyId`) USING BTREE,\n"+
			"KEY `lastReplyTime` (`lastReplyTime`) USING BTREE,\n"+
			"KEY `createTime` (`createTime`) USING BTREE\n"+
			") ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='回帖表';\n";
			System.out.println(sql);
		}
	}
}
