package com.lebaoxun.bbs.core.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.lebaoxun.bbs.core.service.hystrix.PastePostServiceHystrix;
import com.lebaoxun.commons.exception.SuccessMessage;

@FeignClient(value="bbs-service",fallback=PastePostServiceHystrix.class)
public interface IPastePostService {

	/**
	 * 回帖
	 * @param content 内容
	 * @param userId 回帖人
	 * @param pasteId 原帖ID
	 * @param source 来源
	 * @return
	 */
	@RequestMapping(value="/paste/post/reply",method=RequestMethod.POST)
	SuccessMessage replyPaste(@RequestParam("content") String content, 
			@RequestParam(value="pictures",required=false) String pictures, 
			@RequestParam("userId") Integer userId,
			@RequestParam("pasteId") Integer pasteId, 
			@RequestParam("source") String source);
	
	/**
	 * 删除回帖
	 * @param id
	 * @param pasteId 原帖ID
	 * @param userId 回帖人ID
	 * @return
	 */
	@RequestMapping(value="/paste/post/deleteBy",method=RequestMethod.GET)
	SuccessMessage deleteBy(@RequestParam("id") Integer id,
			@RequestParam("pasteId") Integer pasteId, 
			@RequestParam("userId") Integer userId);
	
	/**
	 * 分页原帖的所有回帖
	 * @param pasteId 原帖ID
	 * @param orderBy 排序方式
	 * @param size 分页大小
	 * @param offset 偏移值
	 * @return
	 */
	@RequestMapping(value="/paste/post/findByPasteId",method=RequestMethod.GET)
	SuccessMessage findByPasteId(
			@RequestParam("userId") Integer userId, 
			@RequestParam("flag") Integer flag,
			@RequestParam("pasteId") Integer pasteId,
			@RequestParam("size") Integer size, 
			@RequestParam("offset") Integer offset);
	
	@RequestMapping(value="/paste/post/findById",method=RequestMethod.GET)
	SuccessMessage findById(@RequestParam("userId") Integer userId,
			@RequestParam("pasteId") Integer pasteId,
			@RequestParam("id") Integer id);
}
