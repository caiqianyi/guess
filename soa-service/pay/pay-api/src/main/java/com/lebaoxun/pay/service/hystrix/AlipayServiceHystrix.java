package com.lebaoxun.pay.service.hystrix;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.lebaoxun.commons.exception.I18nMessageException;
import com.lebaoxun.commons.exception.SuccessMessage;
import com.lebaoxun.pay.service.IAlipayService;

@Component
public class AlipayServiceHystrix implements IAlipayService {
	
	private Logger logger = LoggerFactory.getLogger(AlipayServiceHystrix.class);

	@Override
	public SuccessMessage payment(String aplipayReqest) {
		throw new I18nMessageException("502","服务器异常，请稍后重试");
	}

}
