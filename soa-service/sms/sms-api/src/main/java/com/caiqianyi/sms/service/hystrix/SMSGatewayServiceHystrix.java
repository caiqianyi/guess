package com.caiqianyi.sms.service.hystrix;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.caiqianyi.commons.exception.I18nMessageException;
import com.caiqianyi.commons.exception.SuccessMessage;
import com.caiqianyi.sms.service.ISMSGatewayService;

@Component
public class SMSGatewayServiceHystrix implements ISMSGatewayService {
	
	private Logger logger = LoggerFactory.getLogger(SMSGatewayServiceHystrix.class);

	@Override
	public SuccessMessage send(String mobile, String template_id,
			String cst_id, String sign, String[] data) {
		throw new I18nMessageException("502","服务器异常，请稍后重试");
	}

	@Override
	public SuccessMessage checkVfCode(String mobile, String vfCode) {
		// TODO Auto-generated method stub
		throw new I18nMessageException("502","服务器异常，请稍后重试");
	}


}
