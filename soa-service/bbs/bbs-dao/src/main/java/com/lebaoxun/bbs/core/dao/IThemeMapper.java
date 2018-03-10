package com.lebaoxun.bbs.core.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.lebaoxun.bbs.core.entity.Theme;

@Mapper
public interface IThemeMapper {
	
	int save(Theme theme);
	
	int updateBy(Theme theme);
	
	List<Theme> search(@Param("kw") String kw, 
			@Param("size") Integer size,
			@Param("offset") Integer offset);
	
	Theme findByKw(@Param("kw") String kw);
	
	Theme findById(@Param("id") Integer id);
	
	int countByKw(@Param("kw") String kw);
	
	List<String> findAllKindOf();
	
	List<Theme> findByKindOf(@Param("kindOf") String kindOf,
			@Param("size") Integer size,
			@Param("offset") Integer offset);
	
	List<Theme> findByRecommend(@Param("kws") String[] kws,
			@Param("likeKindOfs") String likeKindOfs[],
			@Param("size") Integer size);
	
	List<Theme> findByUserSubscriber(@Param("userId") Integer userId);
}