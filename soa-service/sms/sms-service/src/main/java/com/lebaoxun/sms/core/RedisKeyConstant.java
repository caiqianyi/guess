package com.lebaoxun.sms.core;


public class RedisKeyConstant {
	
	/**
	 * 存储手机号短信黑名单
	 *  | field mobile
	 *  | value reason 原因
	 */
	public final static String HASH_SMS_BLACKLIST_MOBILES = "sms:blacklist:mobiles";//永久黑名单key
	
	public final static String HASH_SMS_FREEZE_RECORDS_MOBILES = "sms:freeze:records:%s";//冻结记录
	
	public final static String SMS_FREEZE_LIST_MOBILES = "sms:freeze:list:%s";//当前冻结手机号
	
	/**
	 * 当前使用短信网关名称 
	 * value RANDOM 随机模式
	 */
	public final static String SMS_GATEWAY_USE_CURRENT = "sms:gateway:use:current";
	
	/**
	 * 网关配置map
	 * field 网关名称
	 * value com:lebaoxun:sms:core:SMSGatewayConfig json
	 */
	public final static String HASH_SMS_GATEWAY_CONFIGS = "sms:gateway:configs";
	
	/**
	 * 短信模板集合
	 * field template_id
	 * value 短信模板 #signature# 签名 #signature# #vfcode#验证码
	 */
	public final static String HASH_SMS_VFCODE_TEMPLATE_IDS = "sms:vfcode:template_ids";
	
	/**
	 * cst_id 分组短信发送频率限制
	 * field 时间单位 Integer 秒单位| yyyyMMdd yyyyMM yyyy
	 * value 发送次数 Integer
	 */
	public final static String HASH_SMS_SEND_TIME_ASTRICT_CSTID = "sms:send:time:astrict:%s";
	
	/**
	 * 
	 */
	public final static String HASH_SMS_SECRET_CSTID = "sms:secrets";
	/**
	 * 手机号秒单位限制记录 second mobile
	 * value 发送次数 Integer
	 */
	public final static String SMS_SEND_COUNT_SECOND_MOBILE = "sms:send:count:second(%s):%s";
	
	/**
	 * 年月日单元限制 格式为 yyyyMMdd yyyyMM yyyy 日期值
	 * field mobile
	 * value 发送次数 Integer
	 */
	public final static String HASH_SMS_SEND_COUNT_DATE = "sms:send:count:date(%s)";
	
	/**
	 * 短信发送机记录　%s 日期　yyyyMMdd
	 * field mobile
	 * value 发送次数 Integer
	 */
	public final static String HASH_SMS_SEND_RECORDS = "sms:send:records:%s";
	
	public final static String SMS_SEND_GATEWAYNAME_FAIL_MIN_COUNT = "sms:send:%s:fail:min:count";
	
	public final static String SMS_SEND_VFCODE_MOBILE = "sms:send:vfcode:%s";
	
}
