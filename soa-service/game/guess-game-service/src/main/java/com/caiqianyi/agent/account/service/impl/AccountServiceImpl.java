package com.caiqianyi.agent.account.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.caiqianyi.agent.account.service.IAccountService;
import com.caiqianyi.agent.account.service.ITransferCardService;
import com.caiqianyi.agent.core.dao.IAgentMapper;
import com.caiqianyi.agent.core.em.EmChargeUserRole;
import com.caiqianyi.agent.core.entity.Agent;
import com.caiqianyi.agent.core.entity.Player;
import com.caiqianyi.agent.core.vo.DataTables;
import com.caiqianyi.commons.exception.I18nMessageException;
import com.caiqianyi.commons.utils.GenerateCode;

@Service
public class AccountServiceImpl implements IAccountService {
	
	private Logger logger = LoggerFactory.getLogger(AccountServiceImpl.class);
	
	@Resource
	private IAgentMapper agentMapper;
	
	@Resource
	private ITransferCardService transferCardService;
	
	@Override
	public Agent findByUsername(String username) {
		// TODO Auto-generated method stub
		return agentMapper.findByUsername(username);
	}

	@Override
	public Agent findUserByAccount(String account, String password) {
		// TODO Auto-generated method stub
		logger.info("findUserByAccount==account={},password={}",account,password);
		return agentMapper.findUserByAccount(account, password);
	}
	
	@Override
	public Agent findByUnionid(String unionid) {
		// TODO Auto-generated method stub
		return agentMapper.findByUnionid(unionid);
	}

	@Override
	public Agent findByMobile(String mobile) {
		// TODO Auto-generated method stub
		return agentMapper.findByMobile(mobile);
	}

	@Override
	public Agent findAgentInfo(String userid, String serverCode) {
		// TODO Auto-generated method stub
		return agentMapper.findAgentInfo(userid, serverCode);
	}

	@Override
	public DataTables findMySubAgent(Integer agentId,DataTables dataTables) {
		
		List<Agent>list = agentMapper.findMySubAgent(agentId, dataTables.getiDisplayStart(),dataTables.getPageDisplayLength());
		
		Integer total = agentMapper.countMySubAgent(agentId);
		dataTables.setiTotalDisplayRecords(total);// 搜索结果总行数
		dataTables.setiTotalRecords(total);// 所有记录总行数
		dataTables.setAaData(list);
		return dataTables;
	}

	@Override
	public Agent findAgentByAgentid(Integer agentId) {
		// TODO Auto-generated method stub
		return agentMapper.findAgentByAgentid(agentId);
	}
	
	@Override
	public void updateMyInfo(Agent agent) {
		// TODO Auto-generated method stub
		agentMapper.updateMyInfo(agent);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	@Override
	public Agent register(Agent agent,Integer agentId) {
		// TODO Auto-generated method stub
		
		Agent createAgent = agentMapper.findAgentByAgentid(agentId);
		if(createAgent == null){
			throw new I18nMessageException("500", "创建失败，开通人不存在！");
		}
		Agent a = agentMapper.findByMobile(agent.getTel());
		if(a != null){
			throw new I18nMessageException("500", "创建失败，代理已存在！");
		}
		Agent newA = new Agent();
		String unionid = null;
		if(StringUtils.isNumeric(agent.getUserid())){
			Player player = transferCardService.getPlayerInfo(createAgent.getServerCode(),Integer.parseInt(agent.getUserid()));
			if(player == null){
				throw new I18nMessageException("500", "创建失败，玩家ID不存在！");
			}
			unionid = player.getUnionId();
		}
		
		//boolean isVIPSub = EmChargeUserRole.VIPSUB.getKey().equals(createAgent.getType()) || EmChargeUserRole.VIPAGENT.equals(createAgent.getCreateUserType());

		newA.setAccount(agent.getTel());
		newA.setAgentId((int)GenerateCode.gen(9));
		newA.setCreateTime(new Date());
		newA.setCreateUserType(agent.getCreateUserType());
		newA.setTel(agent.getTel());
		newA.setNickName(agent.getNickName());
		newA.setName(agent.getNickName());
		
		/*String password=GenerateCode.gen(6)+"";
		newA.setPassword(PwdUtil.getMd5Password(newA.getAccount(),password));*/
		
		newA.setLocation(agent.getLocation());
		newA.setServerCity(agent.getServerCity());
		newA.setProvinceCode(agent.getProvinceCode());
		newA.setCityCode(agent.getCityCode());
		newA.setDistrictCode(agent.getDistrictCode());
		newA.setServerCode(createAgent.getServerCode());
		newA.setUserid(unionid);
		newA.setCreateUserType(EmChargeUserRole.AGENT.getKey());
		newA.setCreateUser(createAgent.getAgentId());
		newA.setLevel1(createAgent.getAgentId());
		newA.setLevel2(createAgent.getLevel1());
		newA.setHost_ip(agent.getHost_ip());
		newA.setEnable("PY");
		newA.setType("P");
		agentMapper.addAgent(newA);
		return newA;
	}

}
