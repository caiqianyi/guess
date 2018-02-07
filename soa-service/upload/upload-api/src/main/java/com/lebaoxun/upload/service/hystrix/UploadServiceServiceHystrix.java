package com.lebaoxun.upload.service.hystrix;

import org.springframework.stereotype.Component;

import com.lebaoxun.commons.exception.I18nMessageException;
import com.lebaoxun.commons.exception.SuccessMessage;
import com.lebaoxun.upload.service.IUploadService;

@Component
public class UploadServiceServiceHystrix implements IUploadService {
	
	//private Logger logger = LoggerFactory.getLogger(UploadServiceServiceHystrix.class);

	@Override
	public SuccessMessage uploadImg(String mode, String namespace,
			String fileType, String imgStr) {
		throw new I18nMessageException("502","服务器异常，请稍后重试");
	}

	@Override
	public SuccessMessage deleteFile(String mode, String file) {
		throw new I18nMessageException("502","服务器异常，请稍后重试");
	}
}
