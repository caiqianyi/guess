package com.caiqianyi.agent.account.rest;

import java.util.Date;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.caiqianyi.agent.account.service.IOrderCardService;
import com.caiqianyi.agent.core.entity.OrderCard;
import com.caiqianyi.agent.core.vo.DataTables;
import com.caiqianyi.commons.exception.SuccessMessage;
import com.caiqianyi.commons.utils.JsonUtil;

@RestController
@RequestMapping("/orderCard")
public class OrderCardController {
	
	private Logger logger = LoggerFactory.getLogger(OrderCardController.class);
	
	@Resource
	private IOrderCardService orderCardService;
	
	@RequestMapping(method = RequestMethod.POST, value = "/findMyRechargeRecordByTime/{agentId}")
	DataTables findMyRechargeRecordByTime(@PathVariable(value="agentId")Integer agentId, String start,
			String end, @RequestBody DataTables dataTables){
		
		logger.debug("dataTables={}",JsonUtil.bean2Json(dataTables));
		return orderCardService.findMyRechargeRecordByTime(agentId, start, end, dataTables);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/findMyRewardRecordByTime/{agentId}")
	DataTables findMyRewardRecordByTime(@PathVariable(value="agentId")Integer agentId, String start,
			String end, @RequestBody DataTables dataTables){
		logger.debug("dataTables={}",JsonUtil.bean2Json(dataTables));
		return orderCardService.findMyRewardRecordByTime(agentId, start, end, dataTables);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/rechargeForAgent/{orderNo}/{time}")
	SuccessMessage cardRechargeForAgent(@PathVariable(value="orderNo")String orderNo,@PathVariable(value="time")Long time){
		Date buyTime = null;
		if(time != null){
			buyTime = new Date(time);
		}
		logger.debug("orderNo={},time={}",orderNo,time);
		return new SuccessMessage(orderCardService.cardRechargeForAgent(orderNo, null,null,buyTime.getTime()));
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/cardRechargeForAgent")
	SuccessMessage cardRechargeForAgent(String out_trade_no,String total_fee,String trade_no,Long gmt_payment){
		return new SuccessMessage(orderCardService.cardRechargeForAgent(out_trade_no, total_fee, trade_no, gmt_payment));
	}
}
