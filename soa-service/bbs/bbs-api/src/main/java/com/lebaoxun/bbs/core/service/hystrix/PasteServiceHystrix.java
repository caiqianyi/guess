package com.lebaoxun.bbs.core.service.hystrix;

import org.springframework.stereotype.Component;

import com.lebaoxun.bbs.core.service.IPasteService;
import com.lebaoxun.commons.exception.I18nMessageException;
import com.lebaoxun.commons.exception.SuccessMessage;

@Component
public class PasteServiceHystrix implements IPasteService {

	@Override
	public SuccessMessage publish(String title, String content,
			String pictures, String source, Integer userId, Integer plateId,
			boolean top, boolean highlight) {
		// TODO Auto-generated method stub
		throw new I18nMessageException("502");
	}

	@Override
	public SuccessMessage setTop(Integer id, Integer userId, boolean top) {
		// TODO Auto-generated method stub
		throw new I18nMessageException("502");
	}

	@Override
	public SuccessMessage setHighlight(Integer id, Integer userId,
			boolean highlight) {
		// TODO Auto-generated method stub
		throw new I18nMessageException("502");
	}

	@Override
	public SuccessMessage deleteBy(Integer id, Integer userId) {
		// TODO Auto-generated method stub
		throw new I18nMessageException("502");
	}

	@Override
	public SuccessMessage findByUserId(Integer userId, String orderBy,
			Integer size, Integer offset) {
		// TODO Auto-generated method stub
		throw new I18nMessageException("502");
	}

	@Override
	public SuccessMessage findByPlateId(Integer plateId, Integer size,
			Integer offset) {
		// TODO Auto-generated method stub
		throw new I18nMessageException("502");
	}

	@Override
	public SuccessMessage findById(Integer id) {
		// TODO Auto-generated method stub
		throw new I18nMessageException("502");
	}

}
