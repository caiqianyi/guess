package com.lebaoxun.sms.core;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSONObject;
import com.lebaoxun.commons.utils.Assert;
import com.lebaoxun.commons.utils.MD5;
import com.lebaoxun.soa.core.redis.IRedisHash;

@Component
public class SMSGatewayClient extends AbstractSMSGatewayClient implements ApplicationContextAware{
	
	public static ApplicationContext context = null;
	
	@Resource
	private IRedisHash redisHash;
	
	private Logger logger = LoggerFactory.getLogger(SMSGatewayClient.class);
	
	public boolean doSend(String mobile,String template_id,String cst_id,String ...datas){
		SMSGateway config = this.getCurrentGateway();
		Assert.notNull(config, "500");
		
		if(config.getClientClass() != null){
			return context.getBean(config.getClientClass()).doSend(mobile, template_id, cst_id, datas);
		}
		
		String content = (String) redisHash.hGet(RedisKeyConstant.HASH_SMS_VFCODE_TEMPLATE_IDS, template_id);
		Assert.notEmpty(content, "10401" , "为没有找到短信模板");
		
		String vfCode = refreshVfCode(mobile);
		content = content.replace("#signature#", "【"+config.getSignature()+"】").replace("#vfcode#", vfCode);
		
		if(datas != null){
			content = String.format(content, datas);
		}
		
		logger.debug("sms send content={}",content);
		
		String smsGateWayUrl = config.getUrl();
		String result = null;
		try {
			
			String message = java.net.URLEncoder.encode(content,config.getCharset());
			// 短信网关请求为GET
			if(RequestMethod.GET.compareTo(config.getMethod()) == 0){
				smsGateWayUrl = String.format(smsGateWayUrl, mobile,message);
				logger.debug("RequestMethod.GET - smsGateWayUrl={}",smsGateWayUrl);
				result = get(smsGateWayUrl);
				logger.debug("RequestMethod.GET - result={}",result);
			}else{
				logger.debug("RequestMethod.POST - requestBody={}",config.getRequestBody());
				if(config.getRequestBody() != null){
					logger.debug("RequestMethod.POST - smsGateWayUrl={}",smsGateWayUrl);
					logger.debug("RequestMethod.POST - isJson={}",config.isJson());
					if(config.isJson()){
						JSONObject josn = JSONObject.parseObject(config.getRequestBody());
						Map<String,String> map = new HashMap<String,String>();
						map.put("Content-Type", "application/json");
				        map.put("Connection", "Keep-Alive");
				        
				        josn.put("msg", java.net.URLEncoder.encode(content,"utf-8"));
				        josn.put("phone", mobile);
				        josn.put("sendtime", DateFormatUtils.format(new Date(), "yyyyMMddHHmm"));
				        
				        String json = josn.toJSONString();
				        logger.debug("RequestMethod.POST - data={}",json);
						result = post(smsGateWayUrl,json,map);
					}else{
						String data = config.getRequestBody();
						logger.debug("RequestMethod.POST - data={}",data);
						result = post(smsGateWayUrl,String.format(data, mobile,message));
					}
				}else{
					smsGateWayUrl = String.format(smsGateWayUrl, mobile,message);
					logger.debug("RequestMethod.POST - smsGateWayUrl={}",smsGateWayUrl);
					result = post(smsGateWayUrl,null);
				}
				logger.debug("RequestMethod.POST - result={}",result);
			}
			if(result.contains(config.getSuccessText()))
				/*redisHash.hSet(String.format(RedisKeyConstant.HASH_SMS_SEND_RECORDS,  
						DateUtil.formatDatetime(new Date(),"yyyyMMdd")) , 
						mobile , count, 24 * 60 * 60);*/
				logger.info("sms send success - mobile={},template_id={},gateway={},",mobile,template_id,JSONObject.toJSON(config));
				return true;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		logger.error("sms send fail - mobile={},message={},gateway={},result={}",mobile,content,JSONObject.toJSON(config),result);
		return false;
	}
	
	@Override  
    public void setApplicationContext(ApplicationContext applicationContext)  
            throws BeansException {  
		context = applicationContext;  
    }  
	
}
