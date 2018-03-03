package com.lebaoxun.bbs.core.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lebaoxun.bbs.core.service.IPasteReplyService;
import com.lebaoxun.commons.exception.SuccessMessage;

@RestController
public class PasteReplyController {
	
	@Resource
	private IPasteReplyService pasteReplyService;
	
	/**
	 * 发表评论
	 * @param content 评论
	 * @param userId 用户
	 * @param pasteId 帖子ID
	 * @param postId 回复贴ID
	 * @param toReplyId 回复人
	 * @param source 来源
	 * @return
	 */
	@RequestMapping(value="/paste/reply/publish",method=RequestMethod.POST)
	SuccessMessage publish(@RequestParam("content") String content, 
			@RequestParam("userId") Integer userId,
			@RequestParam("pasteId") Integer pasteId, 
			@RequestParam("postId") Integer postId,
			@RequestParam(value="toReplyId",required=false) Integer toReplyId, 
			@RequestParam("source") String source){
		return new SuccessMessage(pasteReplyService.save(content, 
				userId, pasteId, postId, toReplyId, source));
	}
	
	/**
	 * 删除回复
	 * @param id
	 * @param userId 回复人
	 * @return
	 */
	@RequestMapping(value="/paste/replay/deleteBy",method=RequestMethod.GET)
	SuccessMessage deleteBy(@RequestParam("id") Integer id, 
			@RequestParam("pasteId") Integer pasteId, 
			@RequestParam("userId") Integer userId){
		return new SuccessMessage(pasteReplyService.deleteBy(id, pasteId, userId));
	}
	
	/**
	 * 分页查询单个指定回贴回复
	 * @param pasteId 原帖ID
	 * @param postId 回帖ID
	 * @param size 分页大小
	 * @param offset 偏移量
	 * @return
	 */
	@RequestMapping(value="/paste/replay/findByPasteId",method=RequestMethod.GET)
	SuccessMessage findByPasteId(@RequestParam("pasteId") Integer pasteId,
			@RequestParam("pasteId") Integer postId, 
			@RequestParam("size") Integer size,
			@RequestParam("offset") Integer offset){
		return new SuccessMessage(pasteReplyService.findByPasteId(pasteId, 
				postId, size, offset));
	}
	
	/**
	 * 查询指定回帖，前几个回复
	 * @param pasteId 原帖ID
	 * @param postIds 回帖ID
	 * @param size 分页大小
	 * @return
	 */
	@RequestMapping(value="/paste/replay/findByPasteIdForTops",method=RequestMethod.GET)
	SuccessMessage findByPasteIdForTops(@RequestParam("pasteId") Integer pasteId,
			@RequestParam("postIds") Integer[] postIds, 
			@RequestParam("replySize") Integer replySize,
			@RequestParam("size") Integer size){
		return new SuccessMessage(pasteReplyService.findByPasteIdForTops(pasteId, 
				postIds, replySize, size));
	}
}
