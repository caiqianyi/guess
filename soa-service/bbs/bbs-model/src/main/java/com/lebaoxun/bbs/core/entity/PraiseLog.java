package com.lebaoxun.bbs.core.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 点赞记录
 * @author Caiqianyi
 *
 */
public class PraiseLog implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3658449095868299617L;
	private Integer id;
	private Integer userId;
	/**
	 * 点赞类型
	 */
	private String logType;
	/**
	 * 点赞IP
	 */
	private String hostIp;
	/**
	 * 来源
	 */
	private String source;
	/**
	 * 记录ID
	 */
	private String recordId;
	/**
	 * 时间
	 */
	private Date createTime;
	
	private final static int DENOMINATOR = 100;
	
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
	public String getLogType() {
		return logType;
	}
	public void setLogType(String logType) {
		this.logType = logType;
	}
	public String getHostIp() {
		return hostIp;
	}
	public void setHostIp(String hostIp) {
		this.hostIp = hostIp;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getRecordId() {
		return recordId;
	}
	public void setRecordId(String recordId) {
		this.recordId = recordId;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getSubmeter() {
		return modulo(userId);
	}
	/**
	 * 分表取模
	 * @param agentId
	 * @return
	 */
	public static String modulo(Integer userId){
		String remainder=String.valueOf(userId%DENOMINATOR);
		int length=remainder.length();
		String str="";
		for(int i=0;i<3-length;i++){
			str+="0";
		}
		return str+remainder;
	}
	
	public static void main(String[] args) {
		for(int i=0;i<DENOMINATOR;i++){
			String flag = modulo(i);
			String sql = "DROP TABLE IF EXISTS `praise_log_"+flag+"`;\n"+
			"CREATE TABLE `praise_log_"+flag+"` (\n"+
			  "`id` int(11) NOT NULL AUTO_INCREMENT,\n"+
			  "`userId` int(11) DEFAULT NULL COMMENT '用户ID',\n"+
			  "`logType` varchar(50) DEFAULT 'P' COMMENT '日志类型  LOGIN, INFO_UPDATE, PASSWD_BACK, PASSWD_UPDATE,PAY,SUB_AGENT_RECHARGE,SUB_AGENT_CREATE,PLAYER_RECHARGE, UNION_CREATE, UNION_INFO_UPDATE, UNION_DELETE, UNION_EXPEND, UNION_RECHARGE, UNION_WEEK_BACKCARD_ACTIVITY,MATCH_CREATE, MATCH_UPDATE , MATCH_DELETE',\n"+
			  "`hostIp` varchar(500) DEFAULT NULL COMMENT 'IP',\n"+
			  "`source` varchar(500) NOT NULL COMMENT '操作平台 WECAHT,PC,MOBILE',\n"+
			  "`recordId` varchar(200) DEFAULT NULL COMMENT '记录ID',\n"+
			  "`createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '日志创建时间',\n"+
			  "PRIMARY KEY (`id`),\n"+
			  "UNIQUE KEY `uuid` (`userId`,`logType`,`recordId`) USING BTREE,\n"+
			  "KEY `userId` (`userId`) USING BTREE,\n"+
			  "KEY `recordId` (`recordId`) USING BTREE,\n"+
			  "KEY `logType` (`logType`) USING BTREE,\n"+
			  "KEY `hostIp` (`hostIp`) USING BTREE,\n"+
			  "KEY `source` (`source`) USING BTREE,\n"+
			  "KEY `createTime` (`createTime`) USING BTREE\n"+
			") ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='点赞表';\n";
			System.out.println(sql);
		}
	}
}
