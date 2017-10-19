package com.caiqianyi.guess.game.entity;

import java.util.Date;


/**
 * 竞猜购买订单
DROP TABLE IF EXISTS `guess_order`;
CREATE TABLE `game_match` (
	`id` varchar(36) CHARACTER SET utf8mb4 NOT NULL COMMENT '主键',
  	`orderNo` varchar(500) NOT NULL COMMENT '订单号',
  	`userId` varchar(500) NOT NULL COMMENT '购买玩家ID',
  	`optionId` varchar(500) NOT NULL COMMENT '购买竞猜项ID',
  	`diamond` float(10,2) DEFAULT '0.00' COMMENT '金币',
  	`amount` int(10) NOT NULL COMMENT '倍数',
  	`topicId` int(10) NOT NULL COMMENT '话题ID',
  	`status` int(10) NOT NULL COMMENT '状态 0=未开奖，1=已返奖，-1=未中奖',
	`createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `orderNo` (`orderNo`),
  KEY `user_index` (`userId`),
  KEY `option_index` (`optionId`),
  KEY `topic_index` (`topicId`),
  KEY `status_index` (`status`),
  KEY `time_index` (`createTime`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='竞猜购买订单';
 * @author Caiqianyi
 *
 */
public class GameMatch {
	
	private String id;
	/**
	 * 游戏类型
	 */
	private String gameType;
	/**
	 * 赛季名 
	 */
	private String season;
	/**
	 * 联赛名
	 */
	private String league;
	/**
	 * 小组名
	 */
	private String groupName;
	/**
	 * 比赛规则
	 */
	private String gameSystem;
	/**
	 * 最多打多少场 BO3
	 */
	private Integer bestOf; 
	/**
	 * 主队名称
	 */
	private String hostTeam;
	/**
	 * 客队名称
	 */
	private String guestTeam;
	/**
	 * 比赛
	 */
	private Date matchDate;
}
