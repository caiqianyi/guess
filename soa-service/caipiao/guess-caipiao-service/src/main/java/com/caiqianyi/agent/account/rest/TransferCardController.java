package com.caiqianyi.agent.account.rest;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.caiqianyi.agent.account.service.ITransferCardService;
import com.caiqianyi.agent.core.entity.Player;
import com.caiqianyi.agent.core.vo.ParamTranscard;
import com.caiqianyi.commons.exception.SuccessMessage;

@RestController
@RequestMapping("/transferCard")
public class TransferCardController {
	
	@Resource
	private ITransferCardService transferCardService;
	
	@RequestMapping(method = RequestMethod.POST, value = "/execute/{agentId}")
	SuccessMessage execute(@RequestBody ParamTranscard param,@PathVariable(value="agentId")Integer agentId){
		return new SuccessMessage(transferCardService.transferCard(agentId, param));
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/getPlayerInfo/{serverCode}/{playerId}")
	Player getPlayerInfo(@PathVariable(value="serverCode")String serverCode,@PathVariable(value="playerId")Integer playerId){
		return transferCardService.getPlayerInfo(serverCode, playerId);
	}
}
