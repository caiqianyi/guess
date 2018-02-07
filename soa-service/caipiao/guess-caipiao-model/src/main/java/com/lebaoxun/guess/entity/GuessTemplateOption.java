package com.lebaoxun.guess.entity;

import java.math.BigDecimal;
import java.util.Date;
/**
 * 
DROP TABLE IF EXISTS `guess_template_option`;
CREATE TABLE `guess_template_option` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `templateId` int(10) NOT NULL COMMENT '竞猜模板ID',
  `name` varchar(500) NOT NULL COMMENT '选项名',
  `formula` varchar(500) NOT NULL COMMENT '判断是否选中公式',
  `orderBy` int(10) NOT NULL COMMENT '排序值',
  `odds` float(10,2) DEFAULT '0.00' COMMENT '赔率',
  `createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `templateId` (`templateId`),
  KEY `orderBy` (`orderBy`),
  KEY `createTime` (`createTime`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='竞猜模板选项表';
 *
 */
public class GuessTemplateOption {
	
	private Integer id;
	private Integer templateId;
	/**
	 * 选项名
	 */
	private String name;
	/**
	 * 判断是否选中公式
	 */
	private String formula;
	/**
	 * 排序值
	 */
	private Integer orderBy;
	/**
	 * 赔率
	 */
	private BigDecimal odds;
	/**
	 * 创建时间
	 */
	private Date createTime;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFormula() {
		return formula;
	}
	public void setFormula(String formula) {
		this.formula = formula;
	}
	public Integer getOrderBy() {
		return orderBy;
	}
	public void setOrderBy(Integer orderBy) {
		this.orderBy = orderBy;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Integer getTemplateId() {
		return templateId;
	}
	public void setTemplateId(Integer templateId) {
		this.templateId = templateId;
	}
	public BigDecimal getOdds() {
		return odds;
	}
	public void setOdds(BigDecimal odds) {
		this.odds = odds;
	}
}
