package com.lebaoxun.agent.rest.bbs;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lebaoxun.account.entity.User;
import com.lebaoxun.agent.security.Oauth2SecuritySubject;
import com.lebaoxun.bbs.core.service.IPastePostService;
import com.lebaoxun.commons.exception.SuccessMessage;

@RestController
@RequestMapping("/bbs")
public class PastePostController {
	
	@Resource
	private IPastePostService pastePostService;
	
	@Resource
	private Oauth2SecuritySubject oauth2SecuritySubject;
	
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
			@RequestParam("pasteId") Integer pasteId, 
			@RequestParam("source") String source){
		User user = oauth2SecuritySubject.getCurrentUser();
		return pastePostService.replyPaste(content, user.getUserId(), pasteId, source);
	}
	
	/**
	 * 删除回帖
	 * @param id
	 * @param pasteId 原帖ID
	 * @param userId 回帖人ID
	 * @return
	 */
	@RequestMapping(value="/paste/post/deleteBy",method=RequestMethod.GET)
	SuccessMessage deleteBy(@RequestParam("id") Integer id,
			@RequestParam("pasteId") Integer pasteId){
		User user = oauth2SecuritySubject.getCurrentUser();
		return pastePostService.deleteBy(pasteId, pasteId, user.getUserId());
	}
	
	/**
	 * 分页原帖的所有回帖
	 * @param pasteId 原帖ID
	 * @param orderBy 排序方式
	 * @param size 分页大小
	 * @param offset 偏移值
	 * @return
	 */
	@RequestMapping(value="/paste/post/findByPasteId",method=RequestMethod.GET)
	SuccessMessage findByPasteId(@RequestParam("pasteId") Integer pasteId,
			@RequestParam("orderBy") String orderBy, 
			@RequestParam("size") Integer size, 
			@RequestParam("offset") Integer offset){
		return pastePostService.findByPasteId(pasteId, orderBy, size, offset);
	}
}
