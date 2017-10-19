package com.caiqianyi.agent.account.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.caiqianyi.agent.account.service.IPlayerChargeService;
import com.caiqianyi.agent.core.dao.IPlayerChargeMapper;
import com.caiqianyi.agent.core.em.EmChargeType;
import com.caiqianyi.agent.core.entity.PlayerCharge;
import com.caiqianyi.agent.core.vo.DataTables;
import com.caiqianyi.commons.utils.Constant;
import com.caiqianyi.commons.utils.ModuloUtil;

@Service
public class PlayerChargeServiceImpl implements IPlayerChargeService {
	
	private Logger logger = LoggerFactory.getLogger(PlayerChargeServiceImpl.class);
	
	@Resource
	private IPlayerChargeMapper playerChargeMapper;
	
	@Override
	public DataTables findSellCardRecordByAgentId(Integer agentId, String start, String end,
			DataTables dataTables) {
		Integer chargeType = EmChargeType.BUY.getKey();
		
		String submeter = ModuloUtil.modulo(agentId,Constant.PLAYER_CHARGE_MODE);
		List<PlayerCharge> list = playerChargeMapper.findSellCardRecordByAgentId(submeter, agentId, chargeType, start, end, dataTables.getiDisplayStart(),dataTables.getPageDisplayLength());
		Integer total = playerChargeMapper.countSellCardRecordByAgentId(submeter, agentId, chargeType, start, end);
		Integer allcount = playerChargeMapper.countSellCardRecordByAgentId(submeter, agentId, chargeType, null, null);
		
		Integer sellsum = playerChargeMapper.getSellSum(submeter, agentId, chargeType, start, end);
		Integer playcount = playerChargeMapper.getPlayerCount(submeter, agentId, chargeType, start, end);
		
		dataTables.setiTotalRecords(total);// 所有记录总行数
		dataTables.setiTotalDisplayRecords(allcount);
		dataTables.setAaData(list);
		
		Map<String, Object> other = new HashMap<String, Object>();
		other.put("playcount", playcount);
		other.put("sellsum", sellsum == null ? 0 : sellsum);
		dataTables.setOther(other);	
		
		return dataTables;
	}
	
}
