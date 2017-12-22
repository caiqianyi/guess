package com.caiqianyi.account.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户表
 * @author caiqianyi
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` varchar(36) CHARACTER SET utf8mb4 NOT NULL COMMENT '主键',
  `userId` int(11) NOT NULL COMMENT '用户ID',
  `account` varchar(20) DEFAULT NULL COMMENT '用户账号',
  `mobile` varchar(20) DEFAULT NULL COMMENT '手机号',
  `password` varchar(50) DEFAULT NULL COMMENT '密码',
  `source` varchar(200) DEFAULT NULL COMMENT '注册来源',
  `type` varchar(200) DEFAULT NULL COMMENT '账号类型',
  `status` varchar(50) DEFAULT NULL COMMENT 'Y 正常 N 禁用  F 异常冻结',
  `payOrderNo` varchar(200) DEFAULT NULL COMMENT '第一次支付订单号',
  `payCount` int(11) DEFAULT 0 COMMENT '充值次数',
  `host` varchar(50) DEFAULT NULL COMMENT '登录信息',
  `level` int(11) DEFAULT 0 COMMENT '账户等级',
  `balance` int(11) DEFAULT 0 COMMENT '账户金额',
  `frozen` int(11) DEFAULT 0 COMMENT '冻结金额',
  
  `subscribe` varchar(50) DEFAULT NULL COMMENT '用户是否订阅该公众号标识，值为0时，代表此用户没有关注该公众号，拉取不到其余信息',
  `openid` varchar(200) DEFAULT NULL COMMENT '微信openid',
  `unionid` varchar(200) DEFAULT NULL COMMENT '只有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段',
  `nickname` varchar(200) DEFAULT NULL COMMENT '用户的昵称',
  `sex` int(10) DEFAULT 0 COMMENT '用户的性别，值为1时是男性，值为2时是女性，值为0时是未知',
  `city` varchar(200) DEFAULT NULL COMMENT '用户所在城市',
  `country` varchar(200) DEFAULT NULL COMMENT '用户所在国家',
  `province` varchar(200) DEFAULT NULL COMMENT '用户所在省份',
  `language` varchar(200) DEFAULT NULL COMMENT '用户的语言，简体中文为zh_CN',
  `headimgurl` varchar(500) DEFAULT NULL COMMENT '户头像，最后一个数值代表正方形头像大小（有0、46、64、96、132数值可选，0代表640*640正方形头像），用户没有头像时该项为空。若用户更换头像，原有头像URL将失效。',
  `subscribeTime` varchar(50) DEFAULT NULL COMMENT '用户关注时间，为时间戳。如果用户曾多次关注，则取最后关注时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '公众号运营者对粉丝的备注，公众号运营者可在微信公众平台用户管理界面对粉丝添加备注',
  `groupid` varchar(200) DEFAULT NULL COMMENT '用户所在的分组ID（兼容旧的用户分组接口）',
  
  `accountSign` varchar(50) NOT NULL COMMENT '账户签名',
  `createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '注册时间',
  `lastLoginTime` datetime DEFAULT NULL COMMENT '最后登录时间',
  `lastBuyTime` datetime DEFAULT NULL COMMENT '最后购买时间',
  `lastUpdateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `userId` (`userId`),
  UNIQUE KEY `account` (`account`),
  UNIQUE KEY `mobile` (`mobile`),
  UNIQUE KEY `openid` (`openid`),
  KEY `source_index` (`source`),
  KEY `type_index` (`type`),
  KEY `status_index` (`status`),
  KEY `level_index` (`level`),
  KEY `create_time_index` (`createTime`),
  KEY `last_login_time_index` (`lastLoginTime`),
  KEY `last_buy_time_index` (`lastBuyTime`),
  KEY `pay_order_no_index` (`payOrderNo`),
  KEY `pay_count_index` (`payCount`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';
 *
 */
public class User implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 840319899504317101L;
	
	private String id;
	private Integer userId;
	private String account;//账号
	private String mobile;//手机号
	private String password;//密码
	private String source;//注册来源
	private String type;//账号类型
	private String status;//Y 正常 N 禁用  F 冻结 
	private String payOrderNo;//第一次支付订单号
	private Integer payCount;//充值次数
	private String host;//登录信息
	private Date createTime;//注册时间
	private Date lastLoginTime;//最后登录时间
	private Date lastBuyTime;//最后购买时间
	private Date lastUpdateTime;//最后修改时间
	private Integer level;//等级
	private Integer balance;//账户余额
	private Integer frozen;//冻结余额
	private String accountSign;//账户签名
	
	/**用户是否订阅该公众号标识，值为0时，代表此用户没有关注该公众号，拉取不到其余信息。*/
	private int subscribe;
	/**用户的标识，对当前公众号唯一*/
	private String openid;
	/**用户的昵称*/
	private String nickname;
	/**用户的性别，值为1时是男性，值为2时是女性，值为0时是未知*/
	private int sex;
	/**用户所在城市*/
	private String city;
	/**用户所在国家*/
	private String country;
	/**用户所在省份*/
	private String province;
	/**用户的语言，简体中文为zh_CN*/
	private String language;
	/**用户头像，最后一个数值代表正方形头像大小（有0、46、64、96、132数值可选，0代表640*640正方形头像），用户没有头像时该项为空。若用户更换头像，原有头像URL将失效。*/
	private String headimgurl;
	/**用户关注时间，为时间戳。如果用户曾多次关注，则取最后关注时间*/
	private String subscribeTime;
	/**只有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段。*/
	private String unionid;
	/**公众号运营者对粉丝的备注，公众号运营者可在微信公众平台用户管理界面对粉丝添加备注*/
	private String remark;
	/**用户所在的分组ID（兼容旧的用户分组接口）*/
	private String groupid;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public String getUnionid() {
		return unionid;
	}
	public void setUnionid(String unionid) {
		this.unionid = unionid;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getPayOrderNo() {
		return payOrderNo;
	}
	public void setPayOrderNo(String payOrderNo) {
		this.payOrderNo = payOrderNo;
	}
	public Integer getPayCount() {
		return payCount;
	}
	public void setPayCount(Integer payCount) {
		this.payCount = payCount;
	}
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getLastLoginTime() {
		return lastLoginTime;
	}
	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}
	public Date getLastBuyTime() {
		return lastBuyTime;
	}
	public void setLastBuyTime(Date lastBuyTime) {
		this.lastBuyTime = lastBuyTime;
	}
	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}
	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
	public Integer getBalance() {
		return balance;
	}
	public void setBalance(Integer balance) {
		this.balance = balance;
	}
	public Integer getFrozen() {
		return frozen;
	}
	public void setFrozen(Integer frozen) {
		this.frozen = frozen;
	}
	public String getAccountSign() {
		return accountSign;
	}
	public void setAccountSign(String accountSign) {
		this.accountSign = accountSign;
	}
	public int getSubscribe() {
		return subscribe;
	}
	public void setSubscribe(int subscribe) {
		this.subscribe = subscribe;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getHeadimgurl() {
		return headimgurl;
	}
	public void setHeadimgurl(String headimgurl) {
		this.headimgurl = headimgurl;
	}
	public String getSubscribeTime() {
		return subscribeTime;
	}
	public void setSubscribeTime(String subscribeTime) {
		this.subscribeTime = subscribeTime;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getGroupid() {
		return groupid;
	}
	public void setGroupid(String groupid) {
		this.groupid = groupid;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
}
