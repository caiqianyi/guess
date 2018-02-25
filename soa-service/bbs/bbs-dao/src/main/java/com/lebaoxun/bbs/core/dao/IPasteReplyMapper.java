package com.lebaoxun.bbs.core.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.lebaoxun.bbs.core.entity.PasteReply;

@Mapper
public interface IPasteReplyMapper {
	
	int save(PasteReply pasteReply);
	
	int updateBy(PasteReply pasteReply);
	
	int deleteBy(@Param("submeter") String submeter,
			@Param("id") Integer id,
			@Param("userId") Integer userId);
	
	PasteReply findBy(@Param("submeter") String submeter,
			@Param("id") Integer id);
	
	PasteReply findByPrev(@Param("submeter") String submeter,
			@Param("postId") Integer postId,
			@Param("id") Integer id);
	
	/**
	 * 分页查询单个指定回贴回复
	 * @param submeter
	 * @param pasteId
	 * @param postId
	 * @param size
	 * @param offset
	 * @return
	 */
	List<PasteReply> findByPasteId(@Param("submeter") String submeter,
			@Param("pasteId") Integer pasteId,
			@Param("postId") Integer postId,
			@Param("size") Integer size,
			@Param("offset") Integer offset);
	
	/**
	 * 查询指定回帖，前几个回复
	 * @param submeter
	 * @param pasteId
	 * @param postIds
	 * @param size
	 * @return
	 */
	List<PasteReply> findByPasteIdForTops(@Param("submeter") String submeter,
			@Param("pasteId") Integer pasteId,
			@Param("postIds") Integer[] postIds,
			@Param("replySize") Integer replySize,
			@Param("size") Integer size);
}
