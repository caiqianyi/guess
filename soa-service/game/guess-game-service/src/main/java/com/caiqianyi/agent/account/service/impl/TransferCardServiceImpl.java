package com.caiqianyi.agent.account.service.impl;

import java.text.MessageFormat;
import java.util.Map;
import java.util.TreeMap;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.caiqianyi.agent.account.service.ITransferCardService;
import com.caiqianyi.agent.core.dao.IAgentMapper;
import com.caiqianyi.agent.core.dao.IGameServerMapper;
import com.caiqianyi.agent.core.dao.IPlayerChargeMapper;
import com.caiqianyi.agent.core.em.EmChargeType;
import com.caiqianyi.agent.core.em.EmChargeUserRole;
import com.caiqianyi.agent.core.entity.Agent;
import com.caiqianyi.agent.core.entity.GameServer;
import com.caiqianyi.agent.core.entity.Player;
import com.caiqianyi.agent.core.entity.PlayerCharge;
import com.caiqianyi.agent.core.vo.ParamTranscard;
import com.caiqianyi.commons.exception.I18nMessageException;
import com.caiqianyi.commons.utils.Constant;
import com.caiqianyi.commons.utils.DateUtil;
import com.caiqianyi.commons.utils.EmojiCharacterUtil;
import com.caiqianyi.commons.utils.HttpClientUtil;
import com.caiqianyi.commons.utils.JsonUtil;
import com.caiqianyi.commons.utils.MD5;
import com.caiqianyi.commons.utils.ModuloUtil;

@Service
public class TransferCardServiceImpl implements ITransferCardService{
	
	private final static Logger logger = LoggerFactory.getLogger(TransferCardServiceImpl.class);
	
	@Resource
	private IAgentMapper agentMapper;
	
	@Resource
	private IPlayerChargeMapper playerChargeMapper;
	
	@Value(value="${transfer.card.screct}")
	private String transferCardScrect;
	
	@Resource
	private IGameServerMapper gameServerMapper;
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	@Override
	public boolean transferCard(int agentId, ParamTranscard param) {
		
		logger.info("处理代理:{},给玩家:{},转卡业务,参数信息:{}",agentId,param.getUserid(),JsonUtil.bean2Json(param));
		// 得到当前agent
		Agent currentAgent=agentMapper.findAgentByAgentid(agentId);
		if(currentAgent==null){
			throw new I18nMessageException("10101", "参数不正确");
		}		
		
		// 判断房卡库存是否足够
		if(currentAgent.getCard2()==null || currentAgent.getCard2()<param.getCardNum()){
			throw new I18nMessageException("10102", "房卡库存不足");
		}
		Integer playerId = param.getUserid();
		// 得到玩家信息
		Player player = this.getPlayerInfo(param.getServerCode(),playerId);
		if(player==null){
			throw new I18nMessageException("10103", "玩家不存在");
		}
		
		// 扣卡
		agentMapper.deductAgentBalance(currentAgent.getAgentId(), param.getCardNum());
		
		GameServer gameServer = gameServerMapper.findGameServerByServerCode(param.getServerCode());
		if(gameServer == null){
			logger.error("转卡失败:serverCode={}",param.getServerCode());
			throw new I18nMessageException("10104","转卡失败，未找到服务器信息");
		}
		try{
			String serverUrl = gameServer.getRemoteUrl();
			String serverName = (String) gameServer.getServerName();
			
			logger.debug("serverUrl={},serverName={}",serverUrl,serverName);
			
			PlayerCharge playerCharge = new PlayerCharge();
			playerCharge.setCardNum(param.getCardNum());
			playerCharge.setUserId(playerId);
			playerCharge.setUserNick(EmojiCharacterUtil.filterEmoji(player.getNick()));
			playerCharge.setChargeUser(currentAgent.getAgentId());
			playerCharge.setChargerType(EmChargeUserRole.AGENT.getKey());
			playerCharge.setChargeType(param.getCardType());
			playerCharge.setServerCode(param.getServerCode());
			playerCharge.setServerName(serverName);
			playerCharge.setHost_ip(param.getHostIP());
			playerCharge.setSubmeter(ModuloUtil.modulo(currentAgent.getAgentId(),Constant.PLAYER_CHARGE_MODE));
			playerCharge.setCardType(param.getCardType());

			this.playerChargeMapper.writeChargePlayLog(playerCharge);
			
			// 转玩家转卡		
			long currentTime=DateUtil.getCurrentMillis();
			boolean result = this.transcardToGameServer(serverUrl,currentAgent.getAccount(), player.getPlayerId()+"", param.getCardType(), param.getCardNum(), currentTime,transferCardScrect);
			if(!result){
				logger.error("调用游戏充值接口返回失败");
				throw new I18nMessageException("10104","服务器转卡失败");
			}
			// 返回信息
			return true;
		}catch(I18nMessageException ime){
			throw ime;
		}catch(Exception e){
			e.printStackTrace();
			logger.error("给玩家转卡异常,异常信息:{},堆栈信息:{}",e.getMessage(),e.getStackTrace());
			throw new I18nMessageException("10104","异常转卡失败");
		}
	}
	
	@Override
	public boolean transcardToGameServer(String romoteUrl, String admin,
			String openID, int cardType, int cardNum, long currentTime,
			String scrit) {
		// 签名	
		TreeMap<String,Object> map=new TreeMap<String,Object>();
		map.put("time", currentTime);
		map.put("msg", "charge");
		map.put("admin", admin);
		map.put("openId",openID);
		map.put("cardType", cardType);
		map.put("cardNum", cardNum);
		map.put("operType", EmChargeType.BUY.getKey());				
		String sign = MD5.getMD5Sign(map,scrit);
		// 调用远程接口
		String url=MessageFormat.format("{0}?msg=charge&admin={1}&openId={2}&cardType={3}&cardNum={4}&operType={5}&time={6}&sign={7}",romoteUrl,admin,openID,cardType,cardNum+"",EmChargeType.BUY.getKey(),currentTime+"",sign);		
		logger.info("调用远程接口,给玩家传卡,Url:{}",url);
		String returnJson="";
		try {
			returnJson=HttpClientUtil.doGetRequest(url);
			logger.info("给玩家转卡返回结果串： {}", returnJson);			
			Map<String,Object> resultMap= JsonUtil.json2Map(returnJson);
			if(!"0".equals(resultMap.get("errorCode").toString())){
				return false;
			}
		} catch (Exception e) {
			logger.error("调用游戏端给玩家充值接口异常,url:{},异常信息:{}",url, e);
			return false;
		}
		return true;
	}
	
	@Override
	public Player getPlayerInfo(String serverCode, Integer playerId) {
		
		GameServer gameServer = gameServerMapper.findGameServerByServerCode(serverCode);
		if(gameServer == null){
			logger.error("查询玩家失败:serverCode={}",serverCode);
			throw new I18nMessageException("10104","未找到服务器信息");
		}
		String serverUrl = gameServer.getRemoteUrl();
		TreeMap<String,Object> map=new TreeMap<String,Object>();
		long currentTime=DateUtil.getCurrentMillis();
		map.put("time", currentTime);
		map.put("msg", "CardInfo");
		map.put("openId",playerId+"");
		String sign = MD5.getMD5Sign(map,transferCardScrect);
		// 调用远程接口
		String url=MessageFormat.format("{0}?msg=CardInfo&openId={1}&time={2}&sign={3}",serverUrl,playerId+"",currentTime+"",sign);		
		logger.debug("调用远程接口,查询玩家信息,Url={},transferCardScrect={}",url,transferCardScrect);
		String returnJson="";
		try {
			returnJson=HttpClientUtil.doGetRequest(url);
			logger.info("查询玩家信息： {}", returnJson);
			Map<String,Object> resultMap= JsonUtil.json2Map(returnJson);
			if("0".equals(resultMap.get("errorCode").toString())){
				Player playerInfo = new Player();
				playerInfo.setCardNum((Integer) resultMap.get("data"));
				playerInfo.setNick((String)resultMap.get("nickname"));
				playerInfo.setPlayerId(playerId);
				playerInfo.setUnionId((String)resultMap.get("uuid"));
				return playerInfo;
			}
		} catch (Exception e) {
			logger.error("查询玩家信息接口异常,url:{},异常信息:{}",url, e);
		}		
		return null;
	}
}
