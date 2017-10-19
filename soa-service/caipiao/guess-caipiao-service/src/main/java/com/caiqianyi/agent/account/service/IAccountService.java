package com.caiqianyi.agent.account.service;

import com.caiqianyi.agent.core.entity.Agent;
import com.caiqianyi.agent.core.vo.DataTables;

public interface IAccountService {
	
	Agent findByUsername(String username);

	Agent findUserByAccount(String account,String password);
	
	Agent findByUnionid(String unionid);

	Agent findByMobile(String mobile);
	
	Agent findAgentInfo(String unionid,String gameId);
	
	void updateMyInfo(Agent agent);
	
	DataTables findMySubAgent(Integer agentId,DataTables dataTables);
	
	Agent findAgentByAgentid(Integer agentId);
	
	Agent register(Agent agent,Integer agentId);
}
