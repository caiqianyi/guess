package com.lebaoxun.sms.config;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lebaoxun.commons.utils.MD5;
import com.lebaoxun.sms.core.AbstractSMSGatewayClient;
/**
 * 容联 云通信 短信发送
 * @author caiqianyi 2017.9.25
 *
 */
@Component
public class RlyunSMSGatewayClient extends AbstractSMSGatewayClient {
	
	private Logger logger = LoggerFactory.getLogger(RlyunSMSGatewayClient.class);
	
	@Override
	public boolean doSend(String mobile, String template_id,
			String cst_id,String ...datas) {
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		
		JSONObject json = new JSONObject();
		json.put("appId", "8aaf07085ea24877015eb6cc02090590");
		List<String> args = new ArrayList<String>();
		if("fe694f4b87a74cfcba54712862625924".equals(template_id)){//短信验证码
			template_id = "207283";
		}
		if("961d2a5d219640e0b5abfaa91b6dce23".equals(template_id)){
			template_id = "207290";
		}
		
		if("207283".equals(template_id)){
			String vfCode = refreshVfCode(mobile);
			args.add(vfCode);
			args.add("10分钟");
		}else{
			args.addAll(Arrays.asList(datas));
		}
		
		StringBuffer datasBuf = new StringBuffer();
		if(!args.isEmpty()){
			datasBuf.append("[");
			for(int i=0;i<args.size();i++){
				String data = args.get(i);
				datasBuf.append("\""+data+"\"");
				if(i < args.size()-1){
					datasBuf.append(",");
				}
			}
			datasBuf.append("]");
		}
		
		JSONArray jarray = JSONArray.parseArray(datasBuf.toString());
		json.put("templateId", template_id);
		json.put("to", mobile);
		json.put("datas", jarray);
		
		String requsetbody = json.toString();
		logger.debug("templateId={},mobile={},requsetbody={}",template_id,mobile,requsetbody);
		String accountSid = "8aaf07085ea24877015eb6cc00a30589",
				authToken = "278b570053f74b4aa0f5da8c52fff5f2",
				auth = new String(Base64.encodeBase64((accountSid+":"+dateFormat.format(new Date())).getBytes()));
		
		String sign = MD5.md5(accountSid+authToken+dateFormat.format(new Date()));
		
		String smsGateWayUrl = String.format("https://app.cloopen.com:8883/2013-12-26/Accounts/%s/SMS/TemplateSMS?sig=%s", accountSid,sign);
		
		Map<String,String> map = new HashMap<String,String>();
		map.put("Accept", "application/json");
		map.put("Content-Type", "application/json;charset=utf-8");
        map.put("Authorization", auth);
        String result = post(smsGateWayUrl,requsetbody,map);
        
        logger.debug("result={}",result);
        if(result.contains("{\"statusCode\":\"000000\"")){
        	return true;
        }
		return false;
	}

}
