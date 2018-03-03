package com.lebaoxun.bbs.core.service.hystrix;

import org.springframework.stereotype.Component;

import com.lebaoxun.bbs.core.service.IPasteReplyService;
import com.lebaoxun.commons.exception.I18nMessageException;
import com.lebaoxun.commons.exception.SuccessMessage;

@Component
public class PasteReplyServiceHystrix implements IPasteReplyService {

	@Override
	public SuccessMessage publish(String content, Integer userId,
			Integer pasteId, Integer postId, Integer toReplyId, String source) {
		// TODO Auto-generated method stub
		throw new I18nMessageException("502");
	}

	@Override
	public SuccessMessage deleteBy(Integer id, Integer pasteId, Integer userId) {
		// TODO Auto-generated method stub
		throw new I18nMessageException("502");
	}

	@Override
	public SuccessMessage findByPasteId(Integer pasteId, Integer postId,
			Integer size, Integer offset) {
		// TODO Auto-generated method stub
		throw new I18nMessageException("502");
	}

	@Override
	public SuccessMessage findByPasteIdForTops(Integer pasteId,
			Integer[] postIds, Integer replySize, Integer size) {
		// TODO Auto-generated method stub
		throw new I18nMessageException("502");
	}

}
