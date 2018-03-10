package com.lebaoxun.bbs.core.service;

import java.util.List;

import com.lebaoxun.bbs.core.entity.Paste;

public interface IPasteService {
	
	/**
	 * 发帖
	 * @param title 标题
	 * @param content 内容
	 * @param pictures 封面
	 * @param source 
	 * @param userId 发帖人
	 * @param plateId 主题ID
	 * @param top 是否置顶，1=置顶，0=正常
	 * @param highlight 是否加精，1=加精，0=正常
	 * @return
	 */
	Paste createPaste(String title, String content,
			String pictures, String source,
			Integer userId, Integer plateId,
			boolean top, boolean highlight);
	
	/**
	 * 置顶
	 * @param id
	 * @param userId
	 * @param top
	 * @return
	 */
	Paste setTop(Integer id, Integer userId, boolean top);
	
	/**
	 * 加精，高亮
	 * @param id
	 * @param userId
	 * @param highlight
	 * @return
	 */
	Paste setHighlight(Integer id, Integer userId, boolean highlight);
	
	/**
	 * 删除贴
	 * @param id 贴ID
	 * @param userId 创建人
	 * @return
	 */
	Integer deleteBy(Integer id, Integer userId);
	
	/**
	 * 分页查询用户的发贴记录
	 * @param userId 发帖人
	 * @param orderBy 排序方式
	 * @param size 分页大小
	 * @param offset 偏移量
	 * @return
	 */
	List<Paste> findByUserId(Integer userId, String orderBy,
			Integer size,Integer offset);
	
	/**
	 * 分页查询主题ID下帖子记录
	 * @param plateId 主题ID
	 * @param size 分页大小
	 * @param offset 偏移量
	 * @return
	 */
	List<Paste> findByPlateId(Integer userId, Integer plateId, 
			Integer size, Integer offset);
	
	/**
	 * 查询某个帖子详情
	 * @param id 帖子ID
	 * @return
	 */
	Paste findById(Integer userId,Integer id);
	
}