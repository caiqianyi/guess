package com.lebaoxun.agent.rest.bbs;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lebaoxun.account.entity.User;
import com.lebaoxun.agent.security.Oauth2SecuritySubject;
import com.lebaoxun.bbs.core.service.IPasteService;
import com.lebaoxun.commons.exception.SuccessMessage;

@RestController
@RequestMapping("/bbs")
public class PasteController {
	
	@Resource
	private IPasteService pasteService;
	
	@Resource
	private Oauth2SecuritySubject oauth2SecuritySubject;
	
	/**
	 * 发帖
	 * @param title 标题
	 * @param content 内容
	 * @param pictures 封面
	 * @param source 
	 * @param plateId 主题ID
	 * @param top 是否置顶，1=置顶，0=正常
	 * @param highlight 是否加精，1=加精，0=正常
	 * @return
	 */
	@RequestMapping(value="/paste/publish",method=RequestMethod.POST)
	SuccessMessage publish(@RequestParam("title") String title, 
			@RequestParam("content") String content,
			@RequestParam("pictures") String pictures, 
			@RequestParam("source") String source,
			@RequestParam("plateId") Integer plateId,
			@RequestParam("top") boolean top, 
			@RequestParam("highlight") boolean highlight){
		User user = oauth2SecuritySubject.getCurrentUser();
		return pasteService.publish(title, content, pictures, source, 
				user.getUserId(), plateId, top, highlight);
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
			@RequestParam("top") boolean top){
		User user = oauth2SecuritySubject.getCurrentUser();
		return pasteService.setTop(id, user.getUserId(), top);
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
			@RequestParam("highlight") boolean highlight){
		User user = oauth2SecuritySubject.getCurrentUser();
		return pasteService.setHighlight(id, user.getUserId(), highlight);
	}
	
	/**
	 * 删除贴
	 * @param id 贴ID
	 * @param userId 创建人
	 * @return
	 */
	@RequestMapping(value="/paste/deleteBy",method=RequestMethod.GET)
	SuccessMessage deleteBy(@RequestParam("id") Integer id){
		User user = oauth2SecuritySubject.getCurrentUser();
		return pasteService.deleteBy(id, user.getUserId());
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
	SuccessMessage findByUserId(@RequestParam("orderBy") String orderBy,
			@RequestParam("size") Integer size,
			@RequestParam("offset") Integer offset){
		User user = oauth2SecuritySubject.getCurrentUser();
		return pasteService.findByUserId(user.getUserId(), orderBy, size, offset);
	}
	
	/**
	 * 分页查询主题ID下帖子记录
	 * @param plateId 主题ID
	 * @param size 分页大小
	 * @param offset 偏移量
	 * @return
	 */
	@RequestMapping(value="/paste/findByPlateId",method=RequestMethod.GET)
	SuccessMessage findByPlateId(@RequestParam("plateId") Integer plateId, 
			@RequestParam("size") Integer size, 
			@RequestParam("offset") Integer offset){
		return pasteService.findByPlateId(plateId, size, offset);
	}
	
	/**
	 * 查询某个帖子详情
	 * @param id 帖子ID
	 * @return
	 */
	@RequestMapping(value="/paste/findById",method=RequestMethod.GET)
	SuccessMessage findById(@RequestParam("id") Integer id){
		return pasteService.findById(id);
	}
}
