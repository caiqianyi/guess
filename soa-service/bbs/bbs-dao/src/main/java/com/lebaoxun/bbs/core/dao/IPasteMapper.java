package com.lebaoxun.bbs.core.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.lebaoxun.bbs.core.entity.Paste;

@Mapper
public interface IPasteMapper {
	
	int save(Paste paste);
	
	int updateBy(Paste paste);
	
	int deleteBy(@Param("id") Integer id,
			@Param("userId") Integer userId);
	
	List<Paste> findByUserId(@Param("userId") Integer userId,
			@Param("orderBy") String orderBy,
			@Param("size") Integer size,
			@Param("offset") Integer offset);
	
	List<Paste> findByPlateId(@Param("plateId") Integer plateId,
			@Param("size") Integer size,
			@Param("offset") Integer offset);
	
	Paste findById(@Param("id") Integer id);
	
}
