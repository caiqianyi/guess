package com.lebaoxun.bbs.core.service;

import java.util.List;

import com.lebaoxun.bbs.core.entity.Theme;

public interface IThemeService {
	
	int save(String kw, String descr,
			String kindOf, String lables,
			String logo, Integer creator,
			Integer owner);
	
	int updateBy(String descr,
			String kindOf, String lables,
			String logo, Integer owner);
	
	int subscribe(Integer themeId, Integer userId);
	
	boolean isSubscribe(Integer themeId, Integer userId);
	
	List<Theme> search(String kw, int size, int offset);
	
	Theme findByKw(String kw);
	
	int countByKw(String kw);
	
	List<String> findAllKindOf();
	
	List<Theme> findByKindOf(String kindOf,Integer size,Integer offset);
}
