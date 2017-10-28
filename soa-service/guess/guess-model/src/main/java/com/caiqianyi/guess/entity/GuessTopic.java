package com.caiqianyi.guess.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
/**
 * 竞猜话题
 * @author caiqianyi
 * 
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` varchar(36) CHARACTER SET utf8mb4 NOT NULL COMMENT '主键',
  `account` varchar(20) NOT NULL COMMENT '用户账号',
  `mobile` varchar(20) NOT NULL COMMENT '手机号',
  `password` varchar(50) NOT NULL COMMENT '密码',
  `nickName` varchar(50) NOT NULL COMMENT '用户昵称',
  `source` varchar(200) DEFAULT NULL COMMENT '注册来源',
  `type` varchar(200) DEFAULT NULL COMMENT '账号类型',
  `openid` varchar(200) DEFAULT NULL COMMENT '微信openid',
  `unionid` varchar(200) DEFAULT NULL COMMENT '微信UnionId',
  `status` varchar(50) DEFAULT NULL COMMENT 'Y 正常 N 禁用  F 异常冻结',
  `payOrderNo` varchar(200) DEFAULT NULL COMMENT '第一次支付订单号',
  `payCount` int(11) NOT NULL DEFAULT 0 COMMENT '充值次数',
  `host` varchar(50) DEFAULT NULL COMMENT '登录信息',
  `level` int(11) NOT NULL DEFAULT 2 COMMENT '账户等级',
  `balance` float(10,2) DEFAULT '0.00' COMMENT '账户金额',
  `frozenMoney` float(10,2) DEFAULT '0.00' COMMENT '冻结金额',
  `accountSign` varchar(50) NOT NULL COMMENT '账户签名',
  `createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '注册时间',
  `lastLoginTime` datetime DEFAULT NULL COMMENT '最后登录时间',
  `lastBuyTime` datetime DEFAULT NULL COMMENT'最后购买时间',
  `lastUpdateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `account` (`account`),
  UNIQUE KEY `nick_name` (`nickName`),
  UNIQUE KEY `mobile` (`mobile`),
  UNIQUE KEY `unionid` (`unionid`),
  KEY `source_index` (`source`),
  KEY `type_index` (`type`),
  KEY `status_index` (`status`),
  KEY `level_index` (`level`),
  KEY `openid_index` (`openid`),
  KEY `create_time_index` (`createTime`),
  KEY `last_login_time_index` (`lastLoginTime`),
  KEY `last_buy_time_index` (`lastBuyTime`),
  KEY `pay_order_no_index` (`payOrderNo`),
  KEY `pay_count_index` (`payCount`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';
 *
 */
public class GuessTopic implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6338921305202682461L;
	private String id;
	private Integer topicId;
	
	/**
	 * 创建人
	 */
	private String createBy;
	/**
	 * 房间号
	 */
	private Integer roomId;
	/**
	 * 竞猜题目
	 */
	private String subject;
	/**
	 * 标签分类 如联赛
	 */
	private String label;
	/**
	 * 种类
	 */
	private String kind;
	/**
	 * 联赛|采种
	 */
	private String league;
	/**
	 * 比赛ID|期号
	 */
	private String groupId;
	/**
	 * 话题类型 
	 */
	private String topicType;
	/**
	 * 排序值
	 */
	private Integer orderBy;
	/**
	 * 参与人数
	 */
	private Integer joinCount;
	/**
	 * 话题状态 0=进行中，1=未开奖，2=已开奖
	 */
	private Integer status;
	/**
	 * 中奖option id
	 */
	private String optionId;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 终止竞猜时间
	 */
	private Date overTime;
	/**
	 * 结算时间
	 */
	private Date finishTime;
	
	/**
	 * 竞猜项
	 */
	private List<GuessTopicOption> options;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public String getKind() {
		return kind;
	}
	public void setKind(String kind) {
		this.kind = kind;
	}
	
	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	public Integer getTopicId() {
		return topicId;
	}
	public void setTopicId(Integer topicId) {
		this.topicId = topicId;
	}
	public Integer getJoinCount() {
		return joinCount;
	}
	public void setJoinCount(Integer joinCount) {
		this.joinCount = joinCount;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getLeague() {
		return league;
	}
	public void setLeague(String league) {
		this.league = league;
	}
	public String getOptionId() {
		return optionId;
	}
	public void setOptionId(String optionId) {
		this.optionId = optionId;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getOverTime() {
		return overTime;
	}
	public void setOverTime(Date overTime) {
		this.overTime = overTime;
	}
	public Date getFinishTime() {
		return finishTime;
	}
	public void setFinishTime(Date finishTime) {
		this.finishTime = finishTime;
	}
	public Integer getRoomId() {
		return roomId;
	}
	public void setRoomId(Integer roomId) {
		this.roomId = roomId;
	}
	public List<GuessTopicOption> getOptions() {
		return options;
	}
	public void setOptions(List<GuessTopicOption> options) {
		this.options = options;
	}
	public String getTopicType() {
		return topicType;
	}
	public void setTopicType(String topicType) {
		this.topicType = topicType;
	}
	public String getCreateBy() {
		return createBy;
	}
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	public Integer getOrderBy() {
		return orderBy;
	}
	public void setOrderBy(Integer orderBy) {
		this.orderBy = orderBy;
	}
}
