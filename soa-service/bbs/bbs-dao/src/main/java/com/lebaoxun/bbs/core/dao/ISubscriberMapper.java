package com.lebaoxun.bbs.core.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.lebaoxun.bbs.core.entity.Subscriber;

@Mapper
public interface ISubscriberMapper {
	
	int subscribeFor(Subscriber subscriber);
	
	int unsubscribe(@Param("type") String type,
			@Param("themeId") Integer themeId,
			@Param("userId") Integer userId);
	
	int incrPasteCount(@Param("type") String type,
			@Param("themeId") Integer themeId,
			@Param("userId") Integer userId);
	
	int incrScore(@Param("type") String type,
			@Param("themeId") Integer themeId,
			@Param("userId") Integer userId,
			@Param("score") Integer score);
	
	int reducePasteCount(@Param("type") String type,
			@Param("themeId") Integer themeId,
			@Param("userId") Integer userId);
	
	int reduceScore(@Param("type") String type,
			@Param("themeId") Integer themeId,
			@Param("userId") Integer userId,
			@Param("score") Integer score);
	
	int setPos(@Param("type") String type,
			@Param("themeId") Integer themeId,
			@Param("userId") Integer userId,
			@Param("pos") Integer pos);
	
	Subscriber findByUserId(@Param("type") String type,
			@Param("themeId") Integer themeId,
			@Param("userId") Integer userId);
	
	int countByThemeId(@Param("type") String type,
			@Param("themeId") Integer themeId);
	
	List<Subscriber> findByUserIdForAll(@Param("type") String type,
			@Param("userId") Integer userId);
}