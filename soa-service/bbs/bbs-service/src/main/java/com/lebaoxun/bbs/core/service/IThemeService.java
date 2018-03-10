package com.lebaoxun.bbs.core.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lebaoxun.bbs.core.entity.Subscriber;
import com.lebaoxun.bbs.core.entity.Theme;

public interface IThemeService {
	
	int create(String kw, String descr, Integer flag,
			String kindOf, String lables,
			String logo, Integer creator,
			Integer owner);
	
	int updateBy(String kw, String descr,
			String kindOf, String lables,
			String logo, Integer owner);
	
	Subscriber subscribe(Integer themeId, Integer userId);
	
	boolean unsubscribe(Integer themeId, Integer userId);
	
	Subscriber findByUserId(Integer themeId, Integer userId);
	
	List<Theme> search(String kw, int size, int offset);
	
	Theme findByKw(String kw);
	
	int countByKw(String kw);
	
	List<String> findAllKindOf();
	
	List<Theme> findByKindOf(String kindOf,Integer size,Integer offset);
	
	List<Theme> findByRecommend(Integer userId, Integer size);
	
	List<Theme> findByUserSubscriber(Integer userId);
}
