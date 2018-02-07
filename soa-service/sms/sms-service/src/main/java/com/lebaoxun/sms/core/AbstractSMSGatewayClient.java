package com.lebaoxun.sms.core;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lebaoxun.commons.utils.Assert;
import com.lebaoxun.commons.utils.DateUtil;
import com.lebaoxun.commons.utils.GenerateCode;
import com.lebaoxun.commons.utils.MD5;
import com.lebaoxun.sms.utils.MapKeyComparator;
import com.lebaoxun.soa.core.redis.IRedisCache;
import com.lebaoxun.soa.core.redis.IRedisHash;

public abstract class AbstractSMSGatewayClient {
	
	@Resource
	private IRedisHash redisHash;
	
	@Resource
	private IRedisCache redisCache;
	
	private Logger logger = LoggerFactory.getLogger(AbstractSMSGatewayClient.class);
	
	public String refreshVfCode(String tel){
		String vfcode = GenerateCode.gen(5)+"";
		redisCache.set(String.format(RedisKeyConstant.SMS_SEND_VFCODE_MOBILE, tel), vfcode, 10 * 60l);
		return vfcode;
	}
	
	public boolean checkVfCode(String tel,String vfCode){
		String vfcode = (String) redisCache.get(String.format(RedisKeyConstant.SMS_SEND_VFCODE_MOBILE, tel));
		return vfcode == null || !vfcode.equalsIgnoreCase(vfCode) ? false : true;
	}
	
	public boolean send(String cst_id,String mobile, String template_id, String sign,String ...args){
		
		Assert.notEmpty(mobile, "10401" , "手机号不能为空");
		Assert.notEmpty(template_id, "10401" , "短信模板不能为空");
		Assert.notEmpty(cst_id, "10401" , "系统编号不能为空");
		Assert.notEmpty(sign, "10401" , "数据签名不能为空");
		
		String secret = (String) redisHash.hGet(RedisKeyConstant.HASH_SMS_SECRET_CSTID, cst_id);
		Assert.notNull(secret, "10401");//系统编号不正确
		
		String signback = MD5.md5(mobile + cst_id + secret);
		
		logger.debug("signback={},secret={}",signback,secret);
		
		Assert.equals(signback, sign, "10402", "签名错误");
		
		Assert.isTrue(!redisHash.hExists(RedisKeyConstant.HASH_SMS_BLACKLIST_MOBILES, mobile), "10405" , "黑名单无法发送");
		
		Assert.isTrue(!redisCache.exists(String.format(RedisKeyConstant.SMS_FREEZE_LIST_MOBILES, mobile)), "10403" , "该手机号已被冻结！");
		
		boolean result = doSend(mobile, template_id, cst_id, args);
		
		if(result){//发送成功
			smsRestrict(mobile, cst_id);
			return true;
		}
		
		/*SMSGateway config = this.getCurrentGateway();
		
		String cstFailKey = String.format(RedisKeyConstant.SMS_SEND_GATEWAYNAME_FAIL_MIN_COUNT, config.getCode());
		Integer failCount = (Integer) redisCache.get(cstFailKey);
		failCount = failCount == null ? 1 : failCount + 1;
		if(redisCache.exists(cstFailKey)){
			redisCache.update(RedisKeyConstant.SMS_SEND_GATEWAYNAME_FAIL_MIN_COUNT, failCount);
		}else{
			redisCache.set(RedisKeyConstant.SMS_SEND_GATEWAYNAME_FAIL_MIN_COUNT, failCount, 60l);
		}
		if(failCount > 5){//一分钟内，该短信通道失败次数超过5次。则删除该通道连接，熔断机制
			redisHash.hDel(RedisKeyConstant.HASH_SMS_GATEWAY_CONFIGS, config.getCode());
		}*/
		
		return result;
	}
	
	public abstract boolean doSend(String mobile,String template_id,String cst_id,String ...datas);
	
	public SMSGateway getCurrentGateway(){
		String code = (String) redisCache.get(RedisKeyConstant.SMS_GATEWAY_USE_CURRENT);
		Assert.notEmpty(code, "500" , null);
		
		Map<String,Object> smsGateways = redisHash.hGetAll(RedisKeyConstant.HASH_SMS_GATEWAY_CONFIGS);
		
		Assert.notNull(smsGateways, "500");
		Assert.notEmpty(smsGateways, "500", "没有可用网关");
		
		if("RANDOM".equals(code)){
			int u = RandomUtils.nextInt(smsGateways.size());
			code = (String) smsGateways.keySet().toArray()[u];
		}
		if(!smsGateways.containsKey(code)){//当前短信通道不存在，则切换成随机模式
			redisCache.set(RedisKeyConstant.SMS_GATEWAY_USE_CURRENT,"RANDOM");
			return getCurrentGateway();
		}
		String config = (String) smsGateways.get(code);
		logger.debug("useGateway={},config={},smsGateways={}",code,config,JSONObject.toJSON(smsGateways));
		SMSGateway sgc = JSON.parseObject(config,SMSGateway.class);
		sgc.setCode(code);
		return sgc;
	}
	
	/**
	 * 记录发送次数，发送场景，比对限制，拉入黑名单
	 * @param mobile
	 * @param cst_id
	 */
	public void smsRestrict(String mobile,String cst_id){
		Map<String,Object>  smsAstrict = redisHash.hGetAll(String.format(RedisKeyConstant.HASH_SMS_SEND_TIME_ASTRICT_CSTID, cst_id));//短信限制配置
  		if(smsAstrict != null){
  			for(String key : smsAstrict.keySet()){
  				Integer max = (Integer) smsAstrict.get(key),count = -1;
  				String reason = null;
  				if(StringUtils.isNumeric(key)){
  	  				Long time = Long.parseLong(key);
  	  				String astrictCache = String.format(RedisKeyConstant.SMS_SEND_COUNT_SECOND_MOBILE, time,mobile);
  	  				if(!redisCache.has(astrictCache)){
  	  					redisCache.set(astrictCache, 0 , time);
  	  				}
  	  				count = (Integer) redisCache.get(astrictCache)+1;
  	  				redisCache.update(astrictCache, count , time);
  	  				reason = astrictCache;
  				}else{
  					String t = null;
  					Integer expire = null;
  	  				if("yyyyMMdd".equalsIgnoreCase(key) ||
  	  						"yyyyMM".equalsIgnoreCase(key) ||
  	  						"yyyy".equalsIgnoreCase(key)){
  	  					t = DateUtil.formatDatetime(new Date(),key);
  	  					if("yyyyMMdd".equalsIgnoreCase(key)){
  	  						expire = 24 * 60 * 60;
  	  					}
  	  				}
  	  				if(t == null){
  	  					continue;
  	  				}
  	  				String countDate = String.format(RedisKeyConstant.HASH_SMS_SEND_COUNT_DATE, t);
  	  				if(!redisHash.hExists(countDate, mobile)){
  	  					redisHash.hSet(countDate, mobile , 0, expire);
  	  				}
  	  				reason = countDate;
  	  				count = (Integer)redisHash.hGet(countDate, mobile) + 1;
  	  				redisHash.hSet(countDate , mobile , count, expire);
  				}
  				logger.debug("key={},max={},count={},reason={}",key,max,count,reason);
  				if(count >= max){//超过最大值立即拉黑
  					freeze(mobile, reason, count);
  				}
  			}
  		}
	}
	
	public void freeze(String mobile,String reason,Integer count){
		
		String freezeReason = "";
		String freezeMobile = String.format(RedisKeyConstant.HASH_SMS_FREEZE_RECORDS_MOBILES, mobile);
		Map<String,Object> freezeRecord = redisHash.hGetAll(freezeMobile);
		Long time = new Date().getTime();
		
		if(freezeRecord == null || freezeRecord.isEmpty()){//第一次冻结
			Long freezeTime = 60 * 60l;
			freezeReason = reason+"|freezeTime:"+freezeTime;
			redisCache.set(String.format(RedisKeyConstant.SMS_FREEZE_LIST_MOBILES,mobile),reason,freezeTime);//冻结一小时
		} else {
			Map<String, Object> sortMap = new TreeMap<String, Object>(
	                new MapKeyComparator(true));
	        sortMap.putAll(freezeRecord);
	        
	        String fzr = sortMap.values().iterator().next().toString();
	        Long freezeTime = 60 * 60l * 5;
	        try{
	        	freezeTime = Long.parseLong(fzr.split("freezeTime:")[1]) * 5;
	        }catch(Exception e){
	        	logger.error("e:{}",e);
	        }
	        logger.debug("mobile={},freezeTime={},fzr={}",mobile,freezeTime,fzr);
	        freezeReason = reason+"|freezeTime:"+freezeTime;
			redisCache.set(String.format(RedisKeyConstant.SMS_FREEZE_LIST_MOBILES,mobile),reason,freezeTime);//冻结上一次时间*5
		}
		
		redisHash.hSet(freezeMobile, time+"", freezeReason);
		
		if(freezeRecord != null && freezeRecord.size() > 4){//冻结次数超过5次则永久拉黑
			redisHash.hSet(RedisKeyConstant.HASH_SMS_BLACKLIST_MOBILES, mobile, "time="+time);
		}
	}
	
	public String get(String smsGateWayUrl) {
		String result = "error";
		
		// 连接超时及读取超时设置
		System.setProperty("sun.net.client.defaultConnectTimeout", "30000"); // 连接超时：30秒
		System.setProperty("sun.net.client.defaultReadTimeout", "30000"); // 读取超时：30秒
		try {
			// 把buffer链接存入新建的URL中
			URL url = new URL(smsGateWayUrl);
			// 打开URL链接
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestProperty("Connection", "Keep-Alive");
            conn.setUseCaches(false);
            conn.setDoOutput(true);

			// 发送短信内容
			BufferedReader in = new BufferedReader(new InputStreamReader(
					conn.getInputStream(), "utf-8"));
			// 获取返回值
			
			String line;
			result = "";
            while ((line = in.readLine()) != null) {
                result += line + "\n";
            }
            in.close();
			// 输出result内容，查看返回值，成功为success，错误为error，详见该文档起始注释
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public String post(String smsGateWayUrl,String data) {
		Map<String,String> map = new HashMap<String,String>();
		map.put("Content-Type", "application/x-www-form-urlencoded");
        map.put("Connection", "Keep-Alive");
		return post(smsGateWayUrl, data, map);
	}
	
	public String post(String smsGateWayUrl,String data,Map<String,String> requestProperty) {
		String result = "error";
		// 连接超时及读取超时设置
		System.setProperty("sun.net.client.defaultConnectTimeout", "30000"); // 连接超时：30秒
		System.setProperty("sun.net.client.defaultReadTimeout", "30000"); // 读取超时：30秒

		try {
			// 把buffer链接存入新建的URL中
			URL url = new URL(smsGateWayUrl);
			// 打开URL链接
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            
            if(requestProperty != null){
            	for(String key : requestProperty.keySet()){
            		conn.setRequestProperty(key, requestProperty.get(key));
            	}
            }
            conn.setUseCaches(false);
            conn.setDoOutput(true);

            if(data != null && data.length() > 0){
            	conn.setRequestProperty("Content-Length", "" + data.length());
            	OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
            	out.write(data);
            	out.flush();
            	out.close();
            }

            //获取响应状态
            if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
                return "error";
            }
			// 发送短信内容
			BufferedReader in = new BufferedReader(new InputStreamReader(
					conn.getInputStream(), "utf-8"));
			// 获取返回值
			
			String line;
			result = "";
            while ((line = in.readLine()) != null) {
                result += line + "\n";
            }
            in.close();
			// 输出result内容，查看返回值，成功为success，错误为error，详见该文档起始注释
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
