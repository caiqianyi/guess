package com.lebaoxun.bbs.core.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.lebaoxun.bbs.core.entity.PastePost;

@Mapper
public interface IPastePostMapper {
	
	int save(PastePost pastePost);
	
	int updateBy(PastePost pastePost);
	
	int deleteBy(@Param("submeter") String submeter,
			@Param("id") Integer id,
			@Param("userId") Integer userId);
	
	int findTier(@Param("submeter") String submeter,
			@Param("pasteId") Integer pasteId);
	
	PastePost findPostByPasteAndTier(@Param("submeter") String submeter,
			@Param("tier") Integer tier,
			@Param("pasteId") Integer pasteId);
	
	PastePost findPostById(@Param("submeter") String submeter,
			@Param("id") Integer id);
	
	List<PastePost> findByPasteId(@Param("submeter") String submeter,
			@Param("pasteId") Integer pasteId,
			@Param("orderBy") String orderBy,
			@Param("size") Integer size,
			@Param("offset") Integer offset);
}
