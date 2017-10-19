package com.caiqianyi.agent.account.service;

import com.caiqianyi.agent.core.vo.DataTables;

public interface IPlayerChargeService {
	DataTables findSellCardRecordByAgentId(Integer agentId,String startDate,String endDate,DataTables dataTables);
}
