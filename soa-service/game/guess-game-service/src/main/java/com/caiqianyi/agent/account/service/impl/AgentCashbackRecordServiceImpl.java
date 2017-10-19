package com.caiqianyi.agent.account.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.caiqianyi.agent.account.service.IAgentCashbackRecordService;
import com.caiqianyi.agent.core.dao.IAgentCashbackRecordMapper;
import com.caiqianyi.agent.core.entity.AgentCashbackRecord;
import com.caiqianyi.agent.core.vo.DataTables;
import com.caiqianyi.commons.exception.I18nMessageException;

@Service
public class AgentCashbackRecordServiceImpl implements
		IAgentCashbackRecordService {
	
	private Logger logger = LoggerFactory.getLogger(AgentCashbackRecordServiceImpl.class);
	@Resource
	private IAgentCashbackRecordMapper agentCashbackRecordMapper;
	
	@Override
	public AgentCashbackRecord findById(Integer id) {
		// TODO Auto-generated method stub
		return agentCashbackRecordMapper.findById(id);
	}
	
	@Override
	public AgentCashbackRecord findByOrderNo(String orderNo) {
		// TODO Auto-generated method stub
		return agentCashbackRecordMapper.findByOrderNo(orderNo);
	}
	
	@Override
	public DataTables findRecordByAgentId(Integer agentId, Integer status,
			String start, String end, DataTables dataTables) {
		// TODO Auto-generated method stub
		
		logger.debug("startDate={},endDate={}",start,end);
		AgentCashbackRecord record = new AgentCashbackRecord();
		record.setAgentId(agentId);
		record.setStatus(status);
		List<AgentCashbackRecord> list = agentCashbackRecordMapper.findRecordBy(record, start,end, false, dataTables.getiDisplayStart(),dataTables.getPageDisplayLength());
		Integer total = agentCashbackRecordMapper.countRecordBy(record, start,end, false);
		
		dataTables.setiTotalRecords(total);// 所有记录总行数
		dataTables.setiTotalDisplayRecords(total);
		dataTables.setAaData(list);
		
		return dataTables;
	}
	
	@Override
	public void completeStatusById(Integer id) {
		// TODO Auto-generated method stub
		AgentCashbackRecord record = agentCashbackRecordMapper.findById(id);
		if(record == null){
			throw new I18nMessageException("500");
		}
		if(record.getStatus() != 1){
			throw new I18nMessageException("500");
		}
		agentCashbackRecordMapper.completeStatusById(id);
	}
}
