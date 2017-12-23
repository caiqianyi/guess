package com.caiqianyi.guess.caipiao.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.caiqianyi.account.dao.ITradeRecordMapper;
import com.caiqianyi.account.dao.IUserMapper;
import com.caiqianyi.account.entity.User;
import com.caiqianyi.commons.exception.I18nMessageException;
import com.caiqianyi.commons.pager.Pager;
import com.caiqianyi.commons.utils.GenerateCode;
import com.caiqianyi.guess.caipiao.service.IClubService;
import com.caiqianyi.guess.caipiao.service.IGuessOrderService;
import com.caiqianyi.guess.core.dao.IGuessClubMapper;
import com.caiqianyi.guess.core.dao.IGuessClubMemberMapper;
import com.caiqianyi.guess.core.dao.IGuessOrderMapper;
import com.caiqianyi.guess.core.dao.IGuessTopicMapper;
import com.caiqianyi.guess.entity.GuessClub;
import com.caiqianyi.guess.entity.GuessClubMember;
import com.caiqianyi.guess.entity.GuessOrder;
import com.caiqianyi.guess.entity.GuessTopic;
import com.caiqianyi.guess.entity.GuessTopicOption;

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
	
	@Resource
	private IGuessClubMapper guessClubMapper;
	
	@Resource
	private IGuessClubMemberMapper guessClubMemberMapper;
	
	@Resource
	private IClubService clubService;

	@Override
	public GuessOrder findByOrderNo(String orderNo) {
		// TODO Auto-generated method stub
		GuessOrder orderParam = new GuessOrder();
		orderParam.setOrderNo(orderNo);
		return guessOrderMapper.findOneBy(orderParam);
	}

	@Override
	public GuessOrder findById(String id) {
		// TODO Auto-generated method stub
		GuessOrder orderParam = new GuessOrder();
		orderParam.setId(id);
		return guessOrderMapper.findOneBy(orderParam);
	}
	
	@Override
	public List<GuessOrder> findAllBy(Integer userId, Integer clubId,
			Integer status, Integer topicId, String kindOf, String expect) {
		// TODO Auto-generated method stub
		GuessOrder orderParam = new GuessOrder();
		orderParam.setClubId(clubId);
		orderParam.setUserId(userId);
		orderParam.setStatus(status);
		orderParam.setTopicId(topicId);
		orderParam.setKindOf(kindOf);
		orderParam.setExpect(expect);
		return guessOrderMapper.findByForPager(orderParam, null, null, null, null);
	}

	@Override
	public Pager findByForPager(Integer userId, Integer clubId, Integer status,
			Integer topicId, String kindOf, String start, String end, Pager pager) {
		// TODO Auto-generated method stub
		GuessOrder orderParam = new GuessOrder();
		orderParam.setClubId(clubId);
		orderParam.setUserId(userId);
		orderParam.setStatus(status);
		orderParam.setTopicId(topicId);
		orderParam.setKindOf(kindOf);
		List<GuessOrder> datas = guessOrderMapper.findByForPager(orderParam, start, end, pager.getSize(), pager.getOffset());
		pager.setDatas(datas);
		pager.setTotal(guessOrderMapper.countBy(orderParam, start, end));
		return pager;
	}

	@Override
	@Transactional(readOnly=false,timeout=10,propagation=Propagation.REQUIRED)
	public GuessOrder joinGuess(Integer memberId, String optionId, Integer diamond) {
		// TODO Auto-generated method stub
		
		if(diamond == null || diamond < 1){
			throw new I18nMessageException("21004","购买不得少于%s！","1");
		}
		GuessTopicOption option = new GuessTopicOption();
		option.setId(optionId); 
		GuessTopicOption guessTopicOption = guessTopicMapper.findOneGuessTopicOptionBy(option);
		if(guessTopicOption == null){
			throw new I18nMessageException("21003","竞猜项不存在！");
		}
		
		GuessTopic param = new GuessTopic();
		param.setTopicId(option.getTopicId());
		GuessTopic topic = guessTopicMapper.findOneGuessTopicBy(param);
		if(topic == null){
			throw new I18nMessageException("21001","题目不存在！");
		}
		
		if(topic.getStatus() != 0 || topic.getOverTime().before(new Date())){
			throw new I18nMessageException("21002","竞猜已结束！");
		}
		
		Integer clubId = topic.getClubId();
		GuessClubMember member = null;
		Integer userId = memberId;
		if(clubId != null){
			GuessClub club = guessClubMapper.findById(null, clubId);
			if(club == null){
				throw new I18nMessageException("30001","俱乐部不存在");
			}
			member = guessClubMemberMapper.findByClubAndId(clubId, memberId);
			if(member == null || !member.getStatus().equals(1)){
				throw new I18nMessageException("30005","操作失败，成员不存在");
			}
			member.setGuessCount(member.getGuessCount()+1);
			member.setUnauditedLiveness(member.getUnauditedLiveness()+1);
			guessClubMemberMapper.update(member);//增加未审核活跃度、增加竞猜次数
		}else{
			User user = userMapper.findById(memberId);
			if(user == null){
				throw new I18nMessageException("21005","玩家不存在！");
			}
		}
		String expect = topic.getGroupId(),
				kindOf = topic.getKind();
		//增加参与人数
		GuessTopicOption gto = new GuessTopicOption();
		gto.setId(guessTopicOption.getId());
		gto.setBuyCount(guessTopicOption.getBuyCount()+1);
		gto.setBuyAmount(guessTopicOption.getBuyAmount().add(new BigDecimal(diamond)));
				
		//增加参与人数
		GuessTopic gt = new GuessTopic();
		gt.setId(topic.getId());
		gt.setJoinCount(topic.getJoinCount()+1);
		
		String orderNo = GenerateCode.genToolOrderNo("ONGUESS", memberId+"");
		
		GuessOrder order = new GuessOrder();
		order.setAmount(1);
		order.setClubId(clubId);
		order.setDiamond(diamond);
		order.setExpect(expect);
		order.setKindOf(kindOf);
		order.setOptionId(optionId);
		order.setOrderNo(orderNo);
		order.setScore(0);
		order.setStatus(0);
		order.setTopicId(topic.getTopicId());
		order.setUserId(userId);
		order.setMemberId(memberId);
		int rows = 0;
		
		rows += guessTopicMapper.update(gt);
		rows += guessTopicMapper.updateOption(gto);
		rows += guessOrderMapper.insert(order);
		
		if(rows != 3){
			throw new I18nMessageException("500");
		}
		
		if(clubId != null){
			clubService.cacheMember(clubId, member, 1);
		}
		return order;
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

	@Override
	public void backBonus() {
		// TODO Auto-generated method stub
		/**
		 * 已结束未返奖的竞猜
		 */
		GuessTopic topicParam = new GuessTopic();
		topicParam.setStatus(2);
		List<GuessTopic> gtList = guessTopicMapper.findAllGuessTopicBy(topicParam, null, null);
		
		for(GuessTopic topic : gtList){
			String winningOptionId = topic.getOptionId();
			
			GuessTopicOption optionParam = new GuessTopicOption();
			optionParam.setId(winningOptionId);
			GuessTopicOption option = guessTopicMapper.findOneGuessTopicOptionBy(optionParam);
			//查询所有购买此竞猜订单
			GuessOrder orderParam = new GuessOrder();
			orderParam.setTopicId(topic.getTopicId());
			List<GuessOrder> orders = guessOrderMapper.findAllBy(orderParam,null,null);
			for(GuessOrder order : orders){
				String optionId = order.getOptionId();
				if(optionId.equals(winningOptionId)){//判断是否未中奖，true=中奖
					BigDecimal bonus = option.getOdds().multiply(new BigDecimal(order.getDiamond()));
					order.setScore(bonus.intValue());
					order.setStatus(1);//已返奖状态
					
				}else{
					order.setStatus(-1);
				}
				guessOrderMapper.update(order);
			}
		}
		
	}
}
