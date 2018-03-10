package com.lebaoxun.bbs.core.service;

import java.util.List;

import com.lebaoxun.bbs.core.entity.PasteReply;

public interface IPasteReplyService {

	PasteReply save(String content, Integer userId,
			Integer pasteId, Integer postId,
			Integer toReplyId, String source);
	
	/**
	 * 删除回复
	 * @param id
	 * @param userId 回复人
	 * @return
	 */
	int deleteBy(Integer id, Integer pasteId, Integer userId);
	
	/**
	 * 分页查询单个指定回贴回复
	 * @param pasteId 原帖ID
	 * @param postId 回帖ID
	 * @param size 分页大小
	 * @param offset 偏移量
	 * @return
	 */
	List<PasteReply> findByPasteId(
			Integer userId, Integer pasteId,
			Integer postId, Integer flag,
			Integer size, Integer offset);
	
	/**
	 * 查询指定回帖，前几个回复
	 * @param pasteId 原帖ID
	 * @param postIds 回帖ID
	 * @param size 分页大小
	 * @return
	 */
	List<PasteReply> findByPasteIdForTops(Integer pasteId,
			Integer[] postIds, Integer replySize,
			Integer size);
}
