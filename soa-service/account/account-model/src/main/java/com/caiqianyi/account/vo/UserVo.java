package com.caiqianyi.account.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 用户表
 * @author caiqianyi
 *
 */
public class UserVo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 840319899504317101L;
	
	private String id;
	private String account;//账号
	private String mobile;//手机号
	private String showMobile;//展示手机号
	private String nickName;//昵称
	private String source;//注册来源
	private String type;//账号类型
	private String openid;//微信openid
	private String unionid;//微信UnionId
	private String status;//Y 正常 N 禁用  F 冻结 
	private String payOrderNo;//第一次支付订单号
	private Integer payCount;//充值次数
	private String host;//登录信息
	private Date createTime;//注册时间
	private Date lastLoginTime;//最后登录时间
	private Date lastBuyTime;//最后购买时间
	private Date lastUpdateTime;//最后修改时间
	private Integer level;//等级
	private BigDecimal balance;//账户余额
	private BigDecimal frozenMoney;//冻结余额
	
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
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
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
	public BigDecimal getBalance() {
		return balance;
	}
	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
	public BigDecimal getFrozenMoney() {
		return frozenMoney;
	}
	public void setFrozenMoney(BigDecimal frozenMoney) {
		this.frozenMoney = frozenMoney;
	}
	public String getShowMobile() {
		return showMobile;
	}
	public void setShowMobile(String showMobile) {
		this.showMobile = showMobile;
	}
}
