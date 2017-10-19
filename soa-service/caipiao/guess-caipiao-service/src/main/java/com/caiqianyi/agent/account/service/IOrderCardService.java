package com.caiqianyi.agent.account.service;

import com.caiqianyi.agent.core.entity.OrderCard;
import com.caiqianyi.agent.core.vo.DataTables;

public interface IOrderCardService {
	
	DataTables findMyRechargeRecordByTime(Integer agentId,String start,String end,DataTables dataTables);
	
	DataTables findMyRewardRecordByTime(Integer agentId,String start,String end,DataTables dataTables);

	OrderCard cardRechargeForAgent(String out_trade_no,String total_fee,String trade_no,Long gmt_payment);
	
	OrderCard findByOrderNo(String orderNo);
}
