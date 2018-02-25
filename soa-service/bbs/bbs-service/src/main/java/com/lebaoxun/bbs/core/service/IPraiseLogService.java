package com.lebaoxun.bbs.core.service;

import com.lebaoxun.bbs.core.entity.PraiseLog;

public interface IPraiseLogService {

	PraiseLog save(Integer userId, String logType, 
			String hostIp, String source,
			String recordId);
	
	int deleteBy(String logType, Integer recordId,
			Integer userId);
	
	int countByUser(String logType, Integer recordId,
			Integer userId);
	
	int countBy(String logType, Integer recordId);
}
