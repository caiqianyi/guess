package com.caiqianyi.agent.account.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.caiqianyi.agent.account.service.IOrderCardService;
import com.caiqianyi.agent.core.dao.IAgentCashbackRecordMapper;
import com.caiqianyi.agent.core.dao.IAgentMapper;
import com.caiqianyi.agent.core.dao.IOrderCardMapper;
import com.caiqianyi.agent.core.em.EmToolOrderStatus;
import com.caiqianyi.agent.core.entity.OrderCard;
import com.caiqianyi.agent.core.query.Criteria;
import com.caiqianyi.agent.core.vo.DataTables;
import com.caiqianyi.commons.exception.I18nMessageException;
import com.caiqianyi.commons.utils.DateUtil;
import com.caiqianyi.commons.utils.JsonUtil;

@Service
public class OrderCardServiceImpl implements IOrderCardService {
	private Logger logger = LoggerFactory.getLogger(OrderCardServiceImpl.class);
	@Resource
	private IOrderCardMapper orderCardMapper;
	
	@Resource
	private IAgentMapper agentMapper;
	
	@Resource
	private IAgentCashbackRecordMapper agentCashbackRecordMapper;

	@Override
	public DataTables findMyRechargeRecordByTime(Integer agentId, String start,
			String end, DataTables dataTables) {
		
		Date s = DateUtil.parse(start),e = DateUtil.parse(end);
		
		//判断是否跨越分查询的
		if(!DateUtil.dateFormatMonth(s).equals(DateUtil.dateFormatMonth(e))){
			return null;
		}
		
		String month = DateUtil.dateFormatMonth(s);
		
		List<OrderCard> list = orderCardMapper.findMyRechargeRecordByTime(month, agentId, start, end, dataTables.getiDisplayStart(),dataTables.getPageDisplayLength(), null, null);
		
		Integer total = this.orderCardMapper.countMyRechargeRecordByTime(month, agentId, start, end);
		dataTables.setiTotalDisplayRecords(total);// 搜索结果总行数
		dataTables.setiTotalRecords(total);// 所有记录总行数
		dataTables.setAaData(list);
		return dataTables;
	}
	
	@Override
	public DataTables findMyRewardRecordByTime(Integer agentId, String start,
			String end, DataTables dataTables) {
		Date s = DateUtil.parse(start),e = DateUtil.parse(end);
		
		//判断是否跨越分查询的
		if(!DateUtil.dateFormatMonth(s).equals(DateUtil.dateFormatMonth(e))){
			return null;
		}
		
		String month = DateUtil.dateFormatMonth(s);
		
		List<OrderCard> list = orderCardMapper.findMyRewardRecordByTime(month, agentId, start, end, dataTables.getiDisplayStart(),dataTables.getPageDisplayLength());
		
		Integer total = this.orderCardMapper.countMyRewardRecordByTime(month, agentId, start, end);
		dataTables.setiTotalDisplayRecords(total);// 搜索结果总行数
		dataTables.setiTotalRecords(total);// 所有记录总行数
		dataTables.setAaData(list);
		return dataTables;
	}
	
	@Override
	public OrderCard cardRechargeForAgent(String out_trade_no,String total_fee,String trade_no,Long gmt_payment) {
		// TODO Auto-generated method stub
		if(gmt_payment == null){
			throw new I18nMessageException("500","交易失败，订单号不存在");
		}
		Criteria criteria = new Criteria();
		criteria.put("orderno", out_trade_no);
		OrderCard order = orderCardMapper.selectOneByWhere(criteria);
		logger.debug("order.json={}",JsonUtil.bean2Json(order));
		if(order == null){
			criteria.setRtableTag(DateUtil.formatDate(new Date()).substring(0, 7).replaceAll("-", ""));
			order = orderCardMapper.selectOneByWhere(criteria);
			if(order==null){//
				throw new I18nMessageException("500","交易失败，订单号不存在");
			}
			
			if("C".equals(order.getOrderstatus())){//已充值成功订单
				return order;
			}
		}
		
		if ((float) Math.floor(order.getDiamondcount()) != (float) Math.floor(Float.parseFloat(total_fee))) {
			throw new I18nMessageException("500","交易失败，订单号不存在");
		}
		
		if("C".equals(order.getOrderstatus())){//为移动到月表订单
			order.setOrderstatus(EmToolOrderStatus.CHARGE.getKey());
			order.setRtableTag(DateUtil.dateFormatMonth(new Date(gmt_payment)));
			order.setBuytime((int) (gmt_payment/1000));
			orderCardMapper.insertSelective(order);
			orderCardMapper.deleteByPrimaryKey(order.getId());
			return order;
		}
		
		Integer agentId = order.getAgentid();
		
		Integer cardNum = order.getCardnum(),
				vipSend = order.getVipsend() == null ? 0 : order.getVipsend(),
				activitySend = order.getActivitysend() == null ? 0 : order.getActivitysend();
		Integer totalCard = cardNum + vipSend + activitySend;
		logger.debug("agentId={},totalCard={}",agentId,totalCard);
		agentMapper.addAgentBalance(agentId, totalCard);//增加代理账户卡数余额
		agentMapper.updateLastBuyTime(agentId);//修改最后购买房卡时间
		order.setId(null);
		order.setOrderstatus(EmToolOrderStatus.CHARGE.getKey());
		order.setRtableTag(DateUtil.dateFormatMonth(new Date(gmt_payment)));
		order.setBuytime((int) (gmt_payment/1000));
		
		orderCardMapper.insertSelective(order);	
		orderCardMapper.deleteByPrimaryKey(order.getId());
		//agentCashbackRecordMapper.passAuditByOrderNo(out_trade_no);//返利审核通过
		return order;
	}
	
	@Override
	public OrderCard findByOrderNo(String orderNo) {
		Criteria criteria = new Criteria();
		criteria.put("orderNo", orderNo);
		return orderCardMapper.selectOneByWhere(criteria);
	}

}
