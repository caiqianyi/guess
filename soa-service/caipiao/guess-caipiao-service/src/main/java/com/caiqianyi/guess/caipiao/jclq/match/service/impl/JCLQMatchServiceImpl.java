package com.caiqianyi.guess.caipiao.jclq.match.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.caiqianyi.commons.pager.Pager;
import com.caiqianyi.guess.caipiao.core.dao.IJCLQMatchMapper;
import com.caiqianyi.guess.caipiao.jclq.match.service.IJCLQMatchService;
import com.caiqianyi.guess.jclq.entity.JCLQMatch;
import com.caiqianyi.guess.jclq.vo.JCLQMatchGuessTopic;

@Service
public class JCLQMatchServiceImpl implements IJCLQMatchService {

	@Resource
	private IJCLQMatchMapper jclqMatchMapper;

	@Override
	public List<JCLQMatch> findMatchByDay(String league, String day,
			String status) {
		// TODO Auto-generated method stub
		return jclqMatchMapper.findAllMatchByDay(league, day, status);
	}

	@Override
	public Pager findMatchByTime(String league, String start,
			String end,Pager pager) {
		// TODO Auto-generated method stub
		List<JCLQMatch> datas = jclqMatchMapper.findAllMatchByTime(league, start, end, pager.getSize(), pager.getOffset());
		pager.setDatas(datas);
		pager.setTotal(jclqMatchMapper.countMatchByTime(league, start, end));
		return pager;
	}

	@Override
	public JCLQMatch findMatchBySeq(String seq) {
		// TODO Auto-generated method stub
		return jclqMatchMapper.findMatch(seq);
	}
	
	@Override
	public List<JCLQMatchGuessTopic> findAllMatchTopicByDay(String day) {
		// TODO Auto-generated method stub
		return jclqMatchMapper.findAllMatchTopicByDay(day);
	}
}
