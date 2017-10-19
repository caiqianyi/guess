package com.caiqianyi.sms.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.caiqianyi.commons.exception.SuccessMessage;
import com.caiqianyi.sms.service.hystrix.SMSGatewayServiceHystrix;

@FeignClient(value="sms-service",fallback=SMSGatewayServiceHystrix.class)
public interface ISMSGatewayService {
	
	
	@RequestMapping(method = RequestMethod.GET, value = "/sms/send/{cst_id}/{mobile}/{template_id}/{sign}")
	SuccessMessage send(@PathVariable("mobile") String mobile,
			@PathVariable("template_id") String template_id,
			@PathVariable("cst_id")String cst_id,
			@PathVariable("sign")String sign,
			@RequestParam(value="data")String[] data);
		
	@RequestMapping(method = RequestMethod.GET, value = "/sms/checkVfCode/{mobile}/{vfCode}")
	SuccessMessage checkVfCode(@PathVariable("mobile") String mobile,
			@PathVariable("vfCode") String vfCode);
}
