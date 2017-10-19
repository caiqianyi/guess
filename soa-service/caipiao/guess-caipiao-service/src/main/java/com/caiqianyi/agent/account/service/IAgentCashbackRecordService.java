package com.caiqianyi.agent.account.service;

import com.caiqianyi.agent.core.entity.AgentCashbackRecord;
import com.caiqianyi.agent.core.vo.DataTables;

public interface IAgentCashbackRecordService {
	
	AgentCashbackRecord findById(Integer id);
	
	AgentCashbackRecord findByOrderNo(String orderNo);
	
	DataTables findRecordByAgentId(Integer agentId, Integer status,String start, String end,
			DataTables dataTables);
	
	void completeStatusById(Integer id);
}
