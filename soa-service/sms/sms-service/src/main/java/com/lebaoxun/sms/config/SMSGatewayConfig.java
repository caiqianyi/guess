package com.lebaoxun.sms.config;

import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.fastjson.JSONObject;
import com.lebaoxun.sms.core.RedisKeyConstant;
import com.lebaoxun.sms.core.SMSGateway;
import com.lebaoxun.soa.core.redis.IRedisCache;
import com.lebaoxun.soa.core.redis.IRedisHash;

@Configuration
public class SMSGatewayConfig {
	
	@Resource
	private IRedisHash redisHash;
	
	@Resource
	private IRedisCache redisCache;
	
	@Bean
	public SMSGateway initGateway(){
		
		SMSGateway Rlyun = new SMSGateway(RlyunSMSGatewayClient.class);
		
		if(!redisHash.hExists(RedisKeyConstant.HASH_SMS_GATEWAY_CONFIGS, "Rlyun")){
			redisHash.hSet(RedisKeyConstant.HASH_SMS_GATEWAY_CONFIGS, "Rlyun", JSONObject.toJSON(Rlyun).toString());
		}
		if(!redisHash.hExists(RedisKeyConstant.HASH_SMS_VFCODE_TEMPLATE_IDS, "fe694f4b87a74cfcba54712862625924")){
			redisHash.hSet(RedisKeyConstant.HASH_SMS_VFCODE_TEMPLATE_IDS,"fe694f4b87a74cfcba54712862625924","#vfcode#（动态验证码），10分钟内有效，请尽快操作，进行验证！#signature#");
		}
		if(!redisHash.hExists(RedisKeyConstant.HASH_SMS_VFCODE_TEMPLATE_IDS, "961d2a5d219640e0b5abfaa91b6dce23")){
			redisHash.hSet(RedisKeyConstant.HASH_SMS_VFCODE_TEMPLATE_IDS,"961d2a5d219640e0b5abfaa91b6dce23","尊敬的用户，您的初始密码是%s，请妥善保存，勿泄露给他人。登录系统，在我的信息中可以进行修改！ ");
		}
		if(!redisHash.hExists(RedisKeyConstant.HASH_SMS_SECRET_CSTID, "58007")){
			redisHash.hSet(RedisKeyConstant.HASH_SMS_SECRET_CSTID,"58007","161dea687be947789169bbe13f0b6e25");
		}
		String sstac_58007 = String.format(RedisKeyConstant.HASH_SMS_SEND_TIME_ASTRICT_CSTID, "58007");
		if(!redisCache.has(sstac_58007)){
			redisHash.hSet(sstac_58007,(5 * 60)+"",3);
			redisHash.hSet(sstac_58007,(10 * 60)+"",8);
			redisHash.hSet(sstac_58007,(60 * 60)+"",10);
			redisHash.hSet(sstac_58007,"yyyyMMdd",20);
		}
		
		if(!redisHash.hExists(RedisKeyConstant.HASH_SMS_SECRET_CSTID, "58006")){
			redisHash.hSet(RedisKeyConstant.HASH_SMS_SECRET_CSTID,"58006","7faca3e9eede4f4ab738ce81627c2327");
		}
		String sstac_58006 = String.format(RedisKeyConstant.HASH_SMS_SEND_TIME_ASTRICT_CSTID, "58006");
		if(!redisCache.has(sstac_58006)){
			redisHash.hSet(sstac_58006,(5 * 60)+"",3);
			redisHash.hSet(sstac_58006,(10 * 60)+"",8);
			redisHash.hSet(sstac_58006,(60 * 60)+"",10);
			redisHash.hSet(sstac_58006,"yyyyMMdd",20);
		}
		
		if(!redisCache.exists(RedisKeyConstant.SMS_GATEWAY_USE_CURRENT)){
			redisCache.set(RedisKeyConstant.SMS_GATEWAY_USE_CURRENT, "RANDOM");
		}
		return null;
	}
	
	public static void main(String[] args) {
		System.out.println(UUID.randomUUID().toString().replaceAll("-", ""));
	}
}
