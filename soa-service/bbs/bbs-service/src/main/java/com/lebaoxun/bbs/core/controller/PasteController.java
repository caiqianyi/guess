package com.lebaoxun.bbs.core.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lebaoxun.bbs.core.service.IPasteService;
import com.lebaoxun.commons.exception.SuccessMessage;

@RestController
public class PasteController {
	
	@Resource
	private IPasteService pasteService;
	
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
	@RequestMapping(value="/paste/publish",method=RequestMethod.POST)
	SuccessMessage publish(@RequestParam("title") String title, 
			@RequestParam("content") String content,
			@RequestParam(value="pictures",required=false) String pictures, 
			@RequestParam("source") String source,
			@RequestParam("userId") Integer userId, 
			@RequestParam("plateId") Integer plateId,
			@RequestParam("top") boolean top, 
			@RequestParam("highlight") boolean highlight){
		return new SuccessMessage(pasteService.createPaste(title, 
				content, pictures, source, userId, plateId, top, highlight));
	}
	
	/**
	 * 置顶
	 * @param id
	 * @param userId
	 * @param top
	 * @return
	 */
	@RequestMapping(value="/paste/setTop",method=RequestMethod.GET)
	SuccessMessage setTop(@RequestParam("id") Integer id, 
			@RequestParam("userId") Integer userId, 
			@RequestParam("top") boolean top){
		return new SuccessMessage(pasteService.setTop(id, userId, top));
	}
	
	/**
	 * 加精，高亮
	 * @param id
	 * @param userId
	 * @param highlight
	 * @return
	 */
	@RequestMapping(value="/paste/setHighlight",method=RequestMethod.GET)
	SuccessMessage setHighlight(@RequestParam("id") Integer id, 
			@RequestParam("userId") Integer userId, 
			@RequestParam("highlight") boolean highlight){
		return new SuccessMessage(pasteService.setHighlight(id, userId, highlight));
	}
	
	/**
	 * 删除贴
	 * @param id 贴ID
	 * @param userId 创建人
	 * @return
	 */
	@RequestMapping(value="/paste/deleteBy",method=RequestMethod.GET)
	SuccessMessage deleteBy(@RequestParam("id") Integer id, 
			@RequestParam("userId") Integer userId){
		return new SuccessMessage(pasteService.deleteBy(id, userId));
	}
	
	/**
	 * 分页查询用户的发贴记录
	 * @param userId 发帖人
	 * @param orderBy 排序方式
	 * @param size 分页大小
	 * @param offset 偏移量
	 * @return
	 */
	@RequestMapping(value="/paste/findByUserId",method=RequestMethod.GET)
	SuccessMessage findByUserId(@RequestParam("userId") Integer userId, 
			@RequestParam("orderBy") String orderBy,
			@RequestParam("size") Integer size,
			@RequestParam("offset") Integer offset){
		return new SuccessMessage(pasteService.findByUserId(userId, 
				orderBy, size, offset));
	}
	
	/**
	 * 分页查询主题ID下帖子记录
	 * @param plateId 主题ID
	 * @param size 分页大小
	 * @param offset 偏移量
	 * @return
	 */
	@RequestMapping(value="/paste/findByPlateId",method=RequestMethod.GET)
	SuccessMessage findByPlateId(
			@RequestParam(value="userId",required=false) Integer userId, 
			@RequestParam("plateId") Integer plateId, 
			@RequestParam("size") Integer size, 
			@RequestParam("offset") Integer offset){
		return new SuccessMessage(pasteService.findByPlateId(userId,plateId, size, offset));
	}
	
	/**
	 * 查询某个帖子详情
	 * @param id 帖子ID
	 * @return
	 */
	@RequestMapping(value="/paste/findById",method=RequestMethod.GET)
	SuccessMessage findById(
			@RequestParam(value="userId",required=false) Integer userId,
			@RequestParam("id") Integer id){
		return new SuccessMessage(pasteService.findById(userId,id));
	}
}
