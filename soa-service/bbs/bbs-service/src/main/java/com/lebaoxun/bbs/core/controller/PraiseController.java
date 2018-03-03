package com.lebaoxun.bbs.core.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lebaoxun.bbs.core.enums.PraiseLogType;
import com.lebaoxun.bbs.core.service.IPraiseService;
import com.lebaoxun.commons.exception.SuccessMessage;

@RestController
public class PraiseController {
	
	@Resource
	private IPraiseService praiseService;
	
	/**
	 * 用户点赞
	 * @param userId 用户ID
	 * @param hostIp 客户端IP
	 * @param source 操作来源
	 * @param pasteId 原帖ID
	 * @param recordId 点击记录ID
	 * @param logType 日志类型
	 * @return
	 */
	@RequestMapping(value="/praise/addLog",method=RequestMethod.GET)
	SuccessMessage praise(@RequestParam("userId") Integer userId,
			@RequestParam("hostIp") String hostIp, 
			@RequestParam("source") String source,
			@RequestParam("pasteId") Integer pasteId, 
			@RequestParam("recordId") String recordId,
			@RequestParam("logType") PraiseLogType logType){
		return new SuccessMessage(praiseService.praise(userId, 
				hostIp, source, pasteId, recordId, logType));
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
	@RequestMapping(value="/praise/cancel",method=RequestMethod.GET)
	SuccessMessage praiseCancel(@RequestParam("userId") Integer userId,
			@RequestParam("pasteId") Integer pasteId, 
			@RequestParam("recordId") String recordId,
			@RequestParam("logType") PraiseLogType logType){
		return new SuccessMessage(praiseService.praiseCancel(userId, 
				 pasteId, recordId, logType));
	}
	
	/**
	 * 统计用户某个点赞数量
	 * @param logType 日志类型
	 * @param recordId 点击记录ID
	 * @param userId 用户ID
	 * @return
	 */
	@RequestMapping(value="/praise/count/byUser",method=RequestMethod.GET)
	SuccessMessage countByUser(@RequestParam("logType") PraiseLogType logType, 
			@RequestParam("recordId") String recordId,
			@RequestParam("userId") Integer userId){
		return new SuccessMessage(praiseService.countByUser(logType, 
				recordId, userId));
	}
}
