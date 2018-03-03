package com.lebaoxun.bbs.core.service.hystrix;

import org.springframework.stereotype.Component;

import com.lebaoxun.bbs.core.enums.PraiseLogType;
import com.lebaoxun.bbs.core.service.IPraiseService;
import com.lebaoxun.commons.exception.I18nMessageException;
import com.lebaoxun.commons.exception.SuccessMessage;

@Component
public class PraiseServiceHystrix implements IPraiseService {

	@Override
	public SuccessMessage praise(Integer userId, String hostIp, String source,
			Integer pasteId, String recordId, PraiseLogType logType) {
		// TODO Auto-generated method stub
		throw new I18nMessageException("502");
	}

	@Override
	public SuccessMessage praiseCancel(Integer userId, Integer pasteId, String recordId,
			PraiseLogType logType) {
		// TODO Auto-generated method stub
		throw new I18nMessageException("502");
	}

	@Override
	public SuccessMessage countByUser(PraiseLogType logType, String recordId,
			Integer userId) {
		// TODO Auto-generated method stub
		throw new I18nMessageException("502");
	}

}
