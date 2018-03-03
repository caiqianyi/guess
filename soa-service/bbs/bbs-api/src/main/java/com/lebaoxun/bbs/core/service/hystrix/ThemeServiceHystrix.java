package com.lebaoxun.bbs.core.service.hystrix;

import org.springframework.stereotype.Component;

import com.lebaoxun.bbs.core.service.IThemeService;
import com.lebaoxun.commons.exception.I18nMessageException;
import com.lebaoxun.commons.exception.SuccessMessage;

@Component
public class ThemeServiceHystrix implements IThemeService {

	@Override
	public SuccessMessage create(String kw, String descr, Integer flag,
			String kindOf, String lables, String logo, Integer creator,
			Integer owner) {
		// TODO Auto-generated method stub
		throw new I18nMessageException("502");
	}

	@Override
	public SuccessMessage updateBy(String kw, String descr, String kindOf,
			String lables, String logo, Integer owner) {
		// TODO Auto-generated method stub
		throw new I18nMessageException("502");
	}

	@Override
	public SuccessMessage subscribe(Integer themeId, Integer userId) {
		// TODO Auto-generated method stub
		throw new I18nMessageException("502");
	}

	@Override
	public SuccessMessage unsubscribe(Integer themeId, Integer userId) {
		// TODO Auto-generated method stub
		throw new I18nMessageException("502");
	}

	@Override
	public SuccessMessage findByUserId(Integer themeId, Integer userId) {
		// TODO Auto-generated method stub
		throw new I18nMessageException("502");
	}

	@Override
	public SuccessMessage search(String kw, int size, int offset) {
		// TODO Auto-generated method stub
		throw new I18nMessageException("502");
	}

	@Override
	public SuccessMessage findByKw(String kw) {
		// TODO Auto-generated method stub
		throw new I18nMessageException("502");
	}

	@Override
	public SuccessMessage countByKw(String kw) {
		// TODO Auto-generated method stub
		throw new I18nMessageException("502");
	}

	@Override
	public SuccessMessage findAllKindOf() {
		// TODO Auto-generated method stub
		throw new I18nMessageException("502");
	}

	@Override
	public SuccessMessage findByKindOf(String kindOf, Integer size,
			Integer offset) {
		// TODO Auto-generated method stub
		throw new I18nMessageException("502");
	}

}
