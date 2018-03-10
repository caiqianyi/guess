package com.lebaoxun.bbs.core.service.hystrix;

import org.springframework.stereotype.Component;

import com.lebaoxun.bbs.core.service.IPastePostService;
import com.lebaoxun.commons.exception.I18nMessageException;
import com.lebaoxun.commons.exception.SuccessMessage;

@Component
public class PastePostServiceHystrix implements IPastePostService {

	@Override
	public SuccessMessage replyPaste(String content, String pictures, Integer userId,
			Integer pasteId, String source) {
		throw new I18nMessageException("502");
	}

	@Override
	public SuccessMessage deleteBy(Integer id, Integer pasteId, Integer userId) {
		// TODO Auto-generated method stub
		throw new I18nMessageException("502");
	}

	@Override
	public SuccessMessage findByPasteId(Integer userId, Integer flag,
			Integer pasteId, Integer size, Integer offset) {
		throw new I18nMessageException("502");
	}

	@Override
	public SuccessMessage findById(Integer userId, Integer pasteId, Integer id) {
		throw new I18nMessageException("502");
	}
}
