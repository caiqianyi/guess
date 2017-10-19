package com.caiqianyi.agent.account.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.caiqianyi.account.dao.ITradeRecordMapper;
import com.caiqianyi.account.dao.IUserMapper;
import com.caiqianyi.account.entity.TradeRecord;
import com.caiqianyi.account.entity.User;
import com.caiqianyi.agent.account.service.IGuessOrderService;
import com.caiqianyi.commons.exception.I18nMessageException;
import com.caiqianyi.guess.core.dao.IGuessOrderMapper;
import com.caiqianyi.guess.core.dao.IGuessTopicMapper;
import com.caiqianyi.guess.entity.GuessOrder;
import com.caiqianyi.guess.entity.GuessTopic;
import com.caiqianyi.guess.entity.GuessTopicOption;
import com.caiqianyi.soa.web.framework.model.Pager;

@Service
public class GuessOrderServiceImpl implements IGuessOrderService {
	
	@Resource
	private IGuessOrderMapper guessOrderMapper;
	
	@Resource
	private IGuessTopicMapper guessTopicMapper;
	
	@Resource
	private IUserMapper userMapper;
	
	@Resource
	private ITradeRecordMapper tradeRecordMapper;

	@Override
	public GuessOrder findByOrderNo(String orderNo) {
		// TODO Auto-generated method stub
		return guessOrderMapper.findByOrderNo(orderNo);
	}

	@Override
	public GuessOrder findById(String id) {
		// TODO Auto-generated method stub
		return guessOrderMapper.findById(id);
	}

	@Override
	public Pager findByUserIdForPager(String userId, Integer status,
			Integer topicId, Date start, Date end, Pager pager) {
		// TODO Auto-generated method stub
		List<GuessOrder> datas = guessOrderMapper.findByUserIdForPager(userId, status, topicId, start, end, pager);
		pager.setDatas(datas);
		return pager;
	}

	@Override
	@Transactional(readOnly=false,timeout=10,propagation=Propagation.REQUIRED)
	public boolean insert(GuessOrder order) {
		// TODO Auto-generated method stub
		
		if(order.getDiamond() == null || order.getDiamond().compareTo(new BigDecimal(1000.0))==-1 ){
			throw new I18nMessageException("21004","购买不得少于%s！","1000");
		}
		
		GuessTopic topic = guessTopicMapper.findOneGuessTopicByTopicId(order.getTopicId());
		if(topic == null){
			throw new I18nMessageException("21001","题目不存在！");
		}
		
		if(topic.getStatus() != 0 || topic.getBeginTime().before(new Date())){
			throw new I18nMessageException("21002","竞猜已结束！");
		}
		
		GuessTopicOption guessTopicOption = guessTopicMapper.findOneGuessTopicOptionByTopicId(topic.getTopicId(), order.getOptionId());
		if(guessTopicOption == null){
			throw new I18nMessageException("21003","竞猜项不存在！");
		}
		
		User user = userMapper.findById(order.getUserId());
		if(user == null){
			throw new I18nMessageException("21005","玩家不存在！");
		}
		
		if(userMapper.findAccountNormal(user.getId()) == null){
			throw new I18nMessageException("21006","账户异常，交易失败！");
		}
		
		//增加参与人数
		GuessTopicOption gto = new GuessTopicOption();
		gto.setId(guessTopicOption.getId());
		gto.setBuyCount(guessTopicOption.getBuyCount()+1);
		gto.setBuyAmount(guessTopicOption.getBuyAmount().add(order.getDiamond()));
				
		//增加参与人数
		GuessTopic gt = new GuessTopic();
		gt.setId(topic.getId());
		gt.setJoinCount(topic.getJoinCount()+1);
		
		//增加交易记录
		TradeRecord tr = new TradeRecord();
		tr.setDescr("参与竞猜，消费"+order.getDiamond()+"");
		tr.setHost("");
		tr.setMoney(user.getBalance());
		tr.setTradeMoney(order.getDiamond());
		tr.setReferId(order.getOrderNo());
		tr.setStatus(1);
		tr.setTradeType("joinGuess");
		tr.setUserId(user.getId());
		
		BigDecimal balance = user.getBalance().subtract(order.getDiamond());
		int rows = 0;
		
		rows += tradeRecordMapper.saveTradeRecord(tr);
		rows += userMapper.modifyBalance(user.getId(), balance, user.getFrozenMoney());
		rows += guessTopicMapper.update(gt);
		rows += guessTopicMapper.updateOption(gto);
		rows += guessOrderMapper.insert(order);
		
		if(rows != 5){
			throw new I18nMessageException("500");
		}
		return false;
	}

	@Override
	@Transactional(readOnly=false,timeout=10,propagation=Propagation.REQUIRED)
	public boolean update(GuessOrder order) {
		// TODO Auto-generated method stub
		return guessOrderMapper.update(order) > 0;
	}

	@Override
	@Transactional(readOnly=false,timeout=10,propagation=Propagation.REQUIRED)
	public boolean delete(String id) {
		// TODO Auto-generated method stub
		return guessOrderMapper.delete(id) > 0;
	}

	@Override
	@Transactional(readOnly=false,timeout=10,propagation=Propagation.REQUIRED)
	public boolean deleteByOrderNo(String orderNo) {
		// TODO Auto-generated method stub
		return guessOrderMapper.deleteByOrderNo(orderNo) > 0;
	}

}
