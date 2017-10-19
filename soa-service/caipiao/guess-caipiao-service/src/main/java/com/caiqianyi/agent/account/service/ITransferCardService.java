package com.caiqianyi.agent.account.service;

import com.caiqianyi.agent.core.entity.Player;
import com.caiqianyi.agent.core.vo.ParamTranscard;

public interface ITransferCardService {
	/**
	 * @Title:transferCard
	 * @Descript:转卡
	 * @param agentId
	 * @return
	 * @author lanzhaoyi
	 * @CreateTime:2017年5月18日 上午9:55:19
	 */
	boolean transferCard(int agentId,ParamTranscard param);
	
	/**
	 * 给玩家转卡
	 * @param romoteUrl
	 * @param admin
	 * @param openID
	 * @param cardType
	 * @param cardNum
	 * @param currentTime
	 * @param scrit
	 * @return
	 */
	boolean transcardToGameServer(String romoteUrl,String admin, String openID, int cardType, int cardNum, long currentTime,String scrit);
	
	Player getPlayerInfo(String serverCode,Integer playerId);
	
	
}
