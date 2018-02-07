package com.lebaoxun.sms.rest;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lebaoxun.commons.exception.SuccessMessage;
import com.lebaoxun.commons.utils.Assert;
import com.lebaoxun.sms.core.SMSGatewayClient;

@RestController
@RequestMapping("/sms")
public class SMSController {
	
	private Logger logger = LoggerFactory.getLogger(SMSController.class);
	
	@Resource
	private SMSGatewayClient smsGatewayClient;
	
	@RequestMapping(method = RequestMethod.GET, value = "/send/{cst_id}/{mobile}/{template_id}/{sign}")
	SuccessMessage send(@PathVariable("mobile") String mobile,
			@PathVariable("template_id") String template_id,
			@PathVariable("cst_id")String cst_id,
			@PathVariable("sign")String sign,
			String[] data){
		
		logger.debug("mobile={},template_id={},cst_id={},sign={},data={}",mobile,template_id,cst_id,sign,data);

		boolean result = smsGatewayClient.send(cst_id, mobile, template_id , sign , data);
		
		Assert.isTrue(result, "500", "系统短信发送失败");
		
		return new SuccessMessage(result);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/checkVfCode/{mobile}/{vfCode}")
	SuccessMessage checkVfCode(@PathVariable("mobile") String mobile,
			@PathVariable("vfCode") String vfCode){
		boolean result = smsGatewayClient.checkVfCode(mobile, vfCode);
		Assert.isTrue(result, "10406", "验证码不正确");
		return new SuccessMessage("ok");
	}
	
}
