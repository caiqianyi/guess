package com.caiqianyi.pay.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.caiqianyi.commons.exception.SuccessMessage;
import com.caiqianyi.pay.service.hystrix.AlipayServiceHystrix;

@FeignClient(value="pay-service",fallback=AlipayServiceHystrix.class)
public interface IAlipayService {
	
	@RequestMapping(value="/alipay/payment", method = RequestMethod.POST)
	SuccessMessage payment(@RequestBody String aplipayReqest);
}
