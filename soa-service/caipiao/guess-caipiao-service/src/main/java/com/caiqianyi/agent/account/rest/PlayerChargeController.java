package com.caiqianyi.agent.account.rest;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.caiqianyi.agent.account.service.IPlayerChargeService;
import com.caiqianyi.agent.core.vo.DataTables;

@RestController
@RequestMapping("/playerCharge")
public class PlayerChargeController {
	
	@Resource
	private IPlayerChargeService playerChargeService;
	
	@RequestMapping(method = RequestMethod.POST, value = "/findSellCardRecordByAgentId/{agentId}")
	DataTables findSellCardRecordByAgentId(@PathVariable(value="agentId")Integer agentId,String start,String end,@RequestBody DataTables dataTables){
		return playerChargeService.findSellCardRecordByAgentId(agentId, start, end, dataTables);
	}
}
