package com.lebaoxun.agent.rest.bbs;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lebaoxun.account.entity.User;
import com.lebaoxun.agent.security.Oauth2SecuritySubject;
import com.lebaoxun.bbs.core.enums.PraiseLogType;
import com.lebaoxun.bbs.core.service.IPraiseService;
import com.lebaoxun.commons.exception.SuccessMessage;
import com.lebaoxun.commons.utils.CommonUtil;

@RestController
@RequestMapping("/bbs")
public class PraiseController {
	
	@Resource
	private IPraiseService praiseService;
	
	@Resource
	private Oauth2SecuritySubject oauth2SecuritySubject;
	
	/**
	 * 用户点赞
	 * @param userId 用户ID
	 * @param hostIp 客户端IP
	 * @param source 操作来源
	 * @param pasteId 原帖ID
	 * @param recordId 点击记录ID
	 * @return
	 */
	@RequestMapping(value="/praise/addLog/{logType}",method=RequestMethod.GET)
	SuccessMessage praisePaste(@RequestParam("source") String source,
			@RequestParam("pasteId") Integer pasteId, 
			@RequestParam("recordId") String recordId,
			@PathVariable("logType") String logType,
			HttpServletRequest request){
		User user = oauth2SecuritySubject.getCurrentUser();
		return praiseService.praise(user.getUserId(), CommonUtil.getIp2(request), source, pasteId, 
				recordId, PraiseLogType.valueOf(logType));
	}
	
	/**
	 * 取消点赞
	 * @param userId 用户ID
	 * @param hostIp 客户端IP
	 * @param source 操作来源
	 * @param pasteId 原帖ID
	 * @param recordId 点击记录ID
	 * @param logType 日志类型
	 * @return
	 */
	@RequestMapping(value="/praise/cancel/{logType}",method=RequestMethod.GET)
	SuccessMessage praiseCancel(@RequestParam("source") String source,
			@RequestParam("pasteId") Integer pasteId, 
			@RequestParam("recordId") String recordId,
			@PathVariable("logType") String logType){
		User user = oauth2SecuritySubject.getCurrentUser();
		return praiseService.praiseCancel(user.getUserId(), pasteId, 
				recordId, PraiseLogType.valueOf(logType));
	}
	
	/**
	 * 统计用户某个点赞数量
	 * @param logType 日志类型
	 * @param recordId 点击记录ID
	 * @param userId 用户ID
	 * @return
	 */
	@RequestMapping(value="/praise/count/byUser/{logType}",method=RequestMethod.GET)
	SuccessMessage countByUser(@PathVariable("logType") String logType, 
			@RequestParam("recordId") String recordId){
		User user = oauth2SecuritySubject.getCurrentUser();
		return praiseService.countByUser(PraiseLogType.valueOf(logType), recordId, user.getUserId());
	}
}
