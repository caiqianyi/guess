package com.caiqianyi.agent.account.service;

import java.util.List;

import com.caiqianyi.agent.core.entity.Notice;

public interface INoticeService {
	
	List<Notice> findActivityNotice(String gameId);
	
	List<Notice> findNoticeByType(String type,String gameId);
}
