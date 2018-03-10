package com.lebaoxun.agent.rest.bbs;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lebaoxun.account.entity.User;
import com.lebaoxun.agent.security.Oauth2SecuritySubject;
import com.lebaoxun.bbs.core.service.IPastePostService;
import com.lebaoxun.commons.exception.SuccessMessage;
import com.lebaoxun.commons.utils.UAgentInfo;

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
			@RequestParam(value="pictures",required=false) String pictures,
			@RequestParam("pasteId") Integer pasteId, 
			HttpServletRequest request){
		UAgentInfo detector = new UAgentInfo(request.getHeader("User-Agent"), request.getHeader("Accept"));
		String source = "";
		if(detector.isWechat()){
			source = "微信公众号";
		}else if (detector.detectMobileQuick()) {
			source = "移动浏览器";
		} else {
		    //PC浏览器
			source = "PC浏览器";
		}
		User user = oauth2SecuritySubject.getCurrentUser();
		return pastePostService.replyPaste(content, pictures, user.getUserId(), pasteId, source);
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
	 * @param orderBy 排序方式
	 * @param size 分页大小
	 * @param offset 偏移值
	 * @return
	 */
	@RequestMapping(value="/paste/post/findByPasteId",method=RequestMethod.GET)
	SuccessMessage findByPasteId(@RequestParam("pasteId") Integer pasteId,
			@RequestParam("flag") Integer flag,
			@RequestParam("size") Integer size, 
			@RequestParam("offset") Integer offset){
		User user = oauth2SecuritySubject.getCurrentUser();
		return pastePostService.findByPasteId(user.getUserId(), flag, pasteId, size, offset);
	}
	
	@RequestMapping(value="/paste/post/findById",method=RequestMethod.GET)
	SuccessMessage findById(@RequestParam("pasteId") Integer pasteId,
			@RequestParam("id") Integer id){
		User user = oauth2SecuritySubject.getCurrentUser();
		return pastePostService.findById(user.getUserId(), pasteId, id);
	}
}
