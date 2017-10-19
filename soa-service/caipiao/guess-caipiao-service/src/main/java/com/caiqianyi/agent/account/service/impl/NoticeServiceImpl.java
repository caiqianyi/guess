package com.caiqianyi.agent.account.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.caiqianyi.agent.account.service.INoticeService;
import com.caiqianyi.agent.core.dao.INoticeMapper;
import com.caiqianyi.agent.core.entity.Notice;

@Service
public class NoticeServiceImpl implements INoticeService {
	
	@Resource
	private INoticeMapper noticeMapper;

	@Override
	public List<Notice> findActivityNotice(String gameId) {
		// TODO Auto-generated method stub
		return noticeMapper.findActivityNotice(gameId);
	}

	@Override
	public List<Notice> findNoticeByType(String type, String gameId) {
		// TODO Auto-generated method stub
		return noticeMapper.findNoticeByType(type, gameId);
	}

}
