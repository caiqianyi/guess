package com.lebaoxun.bbs.core.service;

import com.lebaoxun.bbs.core.entity.PraiseLog;
import com.lebaoxun.bbs.core.enums.PraiseLogType;

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
	PraiseLog praise(Integer userId,
			String hostIp, String source,
			Integer pasteId, String recordId,
			PraiseLogType logType);
	
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
	int praiseCancel(Integer userId,
			Integer pasteId, String recordId,
			PraiseLogType logType);
	
	/**
	 * 统计用户某个点赞数量
	 * @param logType 日志类型
	 * @param recordId 点击记录ID
	 * @param userId 用户ID
	 * @return
	 */
	int countByUser(PraiseLogType logType, String recordId,
			Integer userId);
	
}
