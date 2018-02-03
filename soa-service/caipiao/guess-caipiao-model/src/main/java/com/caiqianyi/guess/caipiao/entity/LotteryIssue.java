package com.caiqianyi.guess.caipiao.entity;

import java.util.Date;
/**
 * 彩票期号表
DROP TABLE IF EXISTS `lottery_issue`;
CREATE TABLE `lottery_issue` (
	`id` varchar(36) CHARACTER SET utf8mb4 NOT NULL COMMENT '主键',
  	`expect` varchar(200) NOT NULL COMMENT '期号ID',
  	`openCode` varchar(200) DEFAULT NULL COMMENT '开奖号码',
  	`kindOf` varchar(200) NOT NULL COMMENT '玩法',
  	`status` int(10) NOT NULL COMMENT '状态：0=进行中，1=已开奖，2=已返奖',
  	`openTime` datetime DEFAULT NULL COMMENT '开奖时间',
  	`startTime` datetime DEFAULT NULL COMMENT '开始时间',
  	`endTime` datetime DEFAULT NULL COMMENT '结束时间',
	`createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `issue_kind_of` (`expect`,`kindOf`),
  KEY `expect` (`expect`),
  KEY `kindOf` (`kindOf`),
  KEY `status` (`status`),
  KEY `startTime` (`startTime`),
  KEY `endTime` (`endTime`),
  KEY `createTime` (`createTime`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='彩票期号表';
 * @author Caiqianyi 
 *
 */
public class LotteryIssue {
	
	private String id;
	private String expect;
	private String openCode = "";
	private String kindOf;
	private Integer status;
	private Date openTime = new Date();
	private Date startTime;
	private Date endTime;
	private Date createTime;
	
	private Integer timeRemaining;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getKindOf() {
		return kindOf;
	}
	public void setKindOf(String kindOf) {
		this.kindOf = kindOf;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getExpect() {
		return expect;
	}
	public void setExpect(String expect) {
		this.expect = expect;
	}
	public String getOpenCode() {
		return openCode;
	}
	public void setOpenCode(String openCode) {
		this.openCode = openCode;
	}
	public Date getOpenTime() {
		return openTime;
	}
	public void setOpenTime(Date openTime) {
		this.openTime = openTime;
	}
	public Integer getTimeRemaining() {
		if(endTime != null){
			timeRemaining = ((int)((endTime.getTime() - System.currentTimeMillis()) / 1000));
		}
		return timeRemaining;
	}
}
