package com.lebaoxun.bbs.core.service.hystrix;

import org.springframework.stereotype.Component;

import com.lebaoxun.bbs.core.service.IPastePostService;
import com.lebaoxun.commons.exception.I18nMessageException;
import com.lebaoxun.commons.exception.SuccessMessage;

@Component
public class PastePostServiceHystrix implements IPastePostService {

	@Override
	public SuccessMessage replyPaste(String content, Integer userId,
			Integer pasteId, String source) {
		throw new I18nMessageException("502");
	}

	@Override
	public SuccessMessage deleteBy(Integer id, Integer pasteId, Integer userId) {
		// TODO Auto-generated method stub
		throw new I18nMessageException("502");
	}

	@Override
	public SuccessMessage findByPasteId(Integer pasteId, String orderBy,
			Integer size, Integer offset) {
		// TODO Auto-generated method stub
		throw new I18nMessageException("502");
	}

}
