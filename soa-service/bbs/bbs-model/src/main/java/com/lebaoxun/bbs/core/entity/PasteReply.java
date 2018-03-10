package com.lebaoxun.bbs.core.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 子回复
 * @author Caiqianyi
 *
 */
public class PasteReply implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4244285784547274949L;
	private Integer id;
	/**
	 * 内容
	 */
	private String content;
	/**
	 * 发帖人
	 */
	private Integer userId;
	/**
	 * 原帖ID
	 */
	private Integer pasteId;
	/**
	 * 回帖ID
	 */
	private Integer postId;
	/**
	 * 回复给
	 */
	private Integer toReplyId;
	/**
	 * 来源
	 */
	private String source;
	/**
	 * 点赞数
	 */
	private Integer praiseCount;
	/**
	 * 发帖时间
	 */
	private Date createTime;
	
	private String nickName;
	
	private String headimgurl;
	
	private boolean isPraise;
	
	private String toReplyNickName;
	
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

	public Integer getPostId() {
		return postId;
	}

	public void setPostId(Integer postId) {
		this.postId = postId;
	}

	public Integer getToReplyId() {
		return toReplyId;
	}

	public void setToReplyId(Integer toReplyId) {
		this.toReplyId = toReplyId;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public Integer getPraiseCount() {
		return praiseCount;
	}

	public void setPraiseCount(Integer praiseCount) {
		this.praiseCount = praiseCount;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	public String getSubmeter() {
		return modulo(pasteId);
	}
	
	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getHeadimgurl() {
		return headimgurl;
	}

	public void setHeadimgurl(String headimgurl) {
		this.headimgurl = headimgurl;
	}

	public boolean isPraise() {
		return isPraise;
	}

	public void setPraise(boolean isPraise) {
		this.isPraise = isPraise;
	}

	/**
	 * 分表取模
	 * @param agentId
	 * @return
	 */
	public static String modulo(Integer pasteId){
		String remainder = String.valueOf(pasteId%DENOMINATOR);
		int length=remainder.length();
		String str="";
		for(int i=0;i<3-length;i++){
			str+="0";
		}
		return str+remainder;
	}
	
	public String getToReplyNickName() {
		return toReplyNickName;
	}

	public void setToReplyNickName(String toReplyNickName) {
		this.toReplyNickName = toReplyNickName;
	}

	public static void main(String[] args) {
		for(int i=0;i<DENOMINATOR;i++){
			String flag = modulo(i);
			String sql = "DROP TABLE IF EXISTS `paste_reply_"+flag+"`;\n"+
			"CREATE TABLE `paste_reply_"+flag+"` (\n"+
			"`id` int(11) NOT NULL AUTO_INCREMENT,\n"+
			"`content` varchar(500) NOT NULL COMMENT '内容',\n"+
			"`userId` int(11) NOT NULL COMMENT '回复人',\n"+
			"`pasteId` int(11) NOT NULL COMMENT '原帖ID',\n"+
			"`postId` int(11) NOT NULL COMMENT '回帖ID',\n"+
			"`toReplyId` int(11) DEFAULT NULL COMMENT '给谁回复',\n"+
			"`source` varchar(500) NOT NULL COMMENT '来源',\n"+
			"`praiseCount` int(11) DEFAULT '0' COMMENT '点赞数',\n"+
			"`createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',\n"+
			"PRIMARY KEY (`id`),\n"+
			"KEY `userId` (`userId`) USING BTREE,\n"+
			"KEY `pasteId` (`pasteId`) USING BTREE,\n"+
			"KEY `postId` (`postId`) USING BTREE,\n"+
			"KEY `toReplyId` (`toReplyId`) USING BTREE,\n"+
			"KEY `source` (`source`) USING BTREE,\n"+
			"KEY `praiseCount` (`praiseCount`) USING BTREE,\n"+
			"KEY `createTime` (`createTime`) USING BTREE\n"+
			") ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='回复表';\n";
			System.out.println(sql);
		}
	}
	
}
