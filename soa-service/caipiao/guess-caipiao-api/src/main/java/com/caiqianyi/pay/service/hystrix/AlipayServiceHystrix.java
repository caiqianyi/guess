package com.caiqianyi.pay.service.hystrix;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.caiqianyi.commons.exception.I18nMessageException;
import com.caiqianyi.commons.exception.SuccessMessage;
import com.caiqianyi.pay.service.IAlipayService;

@Component
public class AlipayServiceHystrix implements IAlipayService {
	
	private Logger logger = LoggerFactory.getLogger(AlipayServiceHystrix.class);

	@Override
	public SuccessMessage payment(String aplipayReqest) {
		throw new I18nMessageException("502","服务器异常，请稍后重试");
	}

}
