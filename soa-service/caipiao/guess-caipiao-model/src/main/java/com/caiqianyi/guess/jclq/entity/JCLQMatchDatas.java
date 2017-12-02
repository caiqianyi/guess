package com.caiqianyi.guess.jclq.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;


/**
DROP TABLE IF EXISTS `jclq_match_datas`;
CREATE TABLE `jclq_match_datas` (
	`id` varchar(36) CHARACTER SET utf8mb4 NOT NULL COMMENT '主键',
  `jclqMatchId` varchar(200) NOT NULL COMMENT '比赛ID',
  `hostOrder` longtext DEFAULT NULL COMMENT '主队排名',
  `guestOrder` longtext NOT NULL COMMENT '客队排名',
  `fightDatas` longtext NOT NULL COMMENT '两队交战',
  `hDatas` longtext NOT NULL COMMENT '主队过去战绩',
  `gDatas` longtext NOT NULL COMMENT '客队过去战绩',
  `odds` longtext NOT NULL COMMENT '欧赔',
	`createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `jclqMatchId` (`jclqMatchId`),
  KEY `createTime` (`createTime`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='篮球比赛数据';
 * @author DELL
 *
 */
public class JCLQMatchDatas implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2916816971091747450L;
	private String id;
	private ArrayList<Map<String, String>> fightDatas;
	private ArrayList<Map<String, String>> hDatas;
	private ArrayList<Map<String, String>> gDatas;
	private ArrayList<Map<String, Object>> odds;
	private Date createTime;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public ArrayList<Map<String, String>> getFightDatas() {
		return fightDatas;
	}
	public void setFightDatas(ArrayList<Map<String, String>> fightDatas) {
		this.fightDatas = fightDatas;
	}
	public ArrayList<Map<String, String>> gethDatas() {
		return hDatas;
	}
	public void sethDatas(ArrayList<Map<String, String>> hDatas) {
		this.hDatas = hDatas;
	}
	public ArrayList<Map<String, String>> getgDatas() {
		return gDatas;
	}
	public void setgDatas(ArrayList<Map<String, String>> gDatas) {
		this.gDatas = gDatas;
	}
	public ArrayList<Map<String, Object>> getOdds() {
		return odds;
	}
	public void setOdds(ArrayList<Map<String, Object>> odds) {
		this.odds = odds;
	}
}
