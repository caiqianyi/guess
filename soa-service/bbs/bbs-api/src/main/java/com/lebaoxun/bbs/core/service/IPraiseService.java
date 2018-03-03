package com.lebaoxun.bbs.core.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.lebaoxun.bbs.core.enums.PraiseLogType;
import com.lebaoxun.bbs.core.service.hystrix.PraiseServiceHystrix;
import com.lebaoxun.commons.exception.SuccessMessage;

@FeignClient(value="bbs-service",fallback=PraiseServiceHystrix.class)
public interface IPraiseService {

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
			@RequestParam("logType") PraiseLogType logType);
	
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
			@RequestParam("logType") PraiseLogType logType);
	
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
			@RequestParam("userId") Integer userId);
}
