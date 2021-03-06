package com.lebaoxun.bbs.core.service;

import java.util.List;

import com.lebaoxun.bbs.core.entity.PastePost;

public interface IPastePostService {
	
	/**
	 * 回帖
	 * @param content 内容
	 * @param userId 回帖人
	 * @param pasteId 原帖ID
	 * @param source 来源
	 * @return
	 */
	PastePost replyPaste(String content,
			String pictures, Integer userId,
			Integer pasteId, String source);
	
	/**
	 * 删除回帖
	 * @param id
	 * @param pasteId 原帖ID
	 * @param userId 回帖人ID
	 * @return
	 */
	int deleteBy(Integer id, Integer pasteId, Integer userId);
	
	/**
	 * 分页原帖的所有回帖
	 * @param pasteId 原帖ID
	 * @param size 分页大小
	 * @param offset 偏移值
	 * @return
	 */
	List<PastePost> findByPasteId(Integer userId, Integer flag, Integer pasteId, Integer size, 
			Integer offset);
	
	PastePost findById(Integer userId,Integer pasteId,Integer id);
}