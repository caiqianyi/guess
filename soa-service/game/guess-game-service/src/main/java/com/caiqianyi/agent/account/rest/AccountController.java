package com.caiqianyi.agent.account.rest;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.caiqianyi.agent.account.service.IAccountService;
import com.caiqianyi.agent.account.service.IAgentCashbackRecordService;
import com.caiqianyi.agent.core.entity.Agent;
import com.caiqianyi.agent.core.vo.DataTables;
import com.caiqianyi.commons.exception.SuccessMessage;

@RestController
@RequestMapping("/account")
public class AccountController {
	
	@Resource
	private IAccountService accountService;
	
	@Resource
	private IAgentCashbackRecordService agentCashbackRecordService;
	
	@RequestMapping(method = RequestMethod.GET, value = "/findByUsername/{username}")
	Agent findByUsername(@PathVariable(value="username")String username){
		return accountService.findByUsername(username);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/login/{account}/{password}")
	Agent login(@PathVariable(value="account")String account,@PathVariable(value="password")String password){
		return accountService.findUserByAccount(account, password);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/findByUnionid/{unionid}")
	Agent findByUnionid(@PathVariable(value="unionid")String unionid){
		return accountService.findByUnionid(unionid);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/findByMobile/{mobile}")
	Agent findByMobile(@PathVariable(value="mobile")String mobile){
		return accountService.findByMobile(mobile);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/findAgentInfo/{serverCode}/{unionid}")
	Agent findAgentInfo(@PathVariable(value="unionid")String unionid,@PathVariable(value="serverCode")String serverCode){
		return accountService.findAgentInfo(unionid, serverCode);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/findMySubAgent/{agentId}")
	DataTables findMySubAgent(@PathVariable(value="agentId")Integer agentId,@RequestBody DataTables dataTables){
		return accountService.findMySubAgent(agentId,dataTables);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/findAgentByAgentid/{agentId}")
	Agent findAgentByAgentid(@PathVariable(value="agentId")Integer agentId){
		return accountService.findAgentByAgentid(agentId);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/register/{createAgentId}",consumes="application/json")
	Agent register(@RequestBody Agent agent,@PathVariable(value="createAgentId")Integer createAgentId){
		return accountService.register(agent,createAgentId);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/updateMyInfo")
	SuccessMessage updateMyInfo(@RequestBody Agent agent){
		accountService.updateMyInfo(agent);
		return new SuccessMessage(true);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/cashback/{agentId}/{status}")
	DataTables findRecordByAgentId(@PathVariable(value="agentId")Integer agentId, @PathVariable(value="status")Integer status,
			@RequestParam(value="start")String start, @RequestParam(value="end")String end,
			@RequestBody DataTables dataTables){
		return agentCashbackRecordService.findRecordByAgentId(agentId, status, start, end, dataTables);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/cashback/complete/{id}")
	SuccessMessage completeStatusById(@PathVariable(value="id")Integer id){
		agentCashbackRecordService.completeStatusById(id);
		return new SuccessMessage("ok");
	}
}
