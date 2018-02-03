package com.caiqianyi.guess.caipiao.service.impl;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.caiqianyi.account.dao.IUserMapper;
import com.caiqianyi.account.dao.UserTradeSupport;
import com.caiqianyi.account.entity.User;
import com.caiqianyi.commons.exception.I18nMessageException;
import com.caiqianyi.guess.caipiao.service.IClubService;
import com.caiqianyi.guess.core.dao.IGuessClubLogMapper;
import com.caiqianyi.guess.core.dao.IGuessClubMapper;
import com.caiqianyi.guess.core.dao.IGuessClubMemberMapper;
import com.caiqianyi.guess.entity.GuessClub;
import com.caiqianyi.guess.entity.GuessClubLog;
import com.caiqianyi.guess.entity.GuessClubMember;
import com.caiqianyi.soa.core.redis.IRedisCache;
import com.caiqianyi.soa.core.redis.IRedisHash;

@Service("clubService")
public class ClubServiceImpl extends UserTradeSupport implements IClubService {
	
	@Resource
	private IGuessClubMapper guessClubMapper;
	
	@Resource
	private IGuessClubMemberMapper guessClubMemberMapper;
	
	@Resource
	private IUserMapper userMapper;
	
	@Resource
	private IRedisHash redisHash;

	@Resource
	private IGuessClubLogMapper guessClubLogMapper;
	
	@Resource
	private IRedisCache redisCache;
	
	@Override
	public Map<String, Object> cacheMember(Integer clubId,GuessClubMember member,Integer flag){
		String key = "guess:club:members:"+clubId;
		if(flag != null){
			if(1 == flag){
				redisHash.hSet(key, ""+member.getId() , member);
			}
			if(-1 == flag){
				redisHash.hDel(key, ""+member.getId());
			}
			return null;
		}
		return redisHash.hGetAll(key);
	}

	@Bean  
    public KeyGenerator guessClubInfoKeyGenerator(){//缓存key策略
        return new KeyGenerator() {  
            @Override  
            public Object generate(Object target, Method method, Object... params) {
            	 StringBuilder sb = new StringBuilder();  
                 sb.append("guess:club:info:");  
                 sb.append(params[1].toString()+":");  
                 sb.append(params[0].toString());  
                 return sb.toString();  
            }  
        };  
    }
	
	@Override
	@Transactional(readOnly=false,timeout=10,propagation=Propagation.REQUIRED)
	@CachePut(value = "guess:club:info", keyGenerator="guessClubInfoKeyGenerator")
	public GuessClub create(Integer clubId, Integer createId, Integer maxMember, String name,
			String password, String notice, Integer cardNum, String kindOf) {
		// TODO Auto-generated method stub
		
		User create = userMapper.findById(createId);
		if(create == null){
			throw new I18nMessageException("30011");
		}
		
		int count = guessClubMapper.countByCreateId(createId, name);
		if(count > 0){
			throw new I18nMessageException("30010","俱乐部名字重复");
		}
		
		GuessClub club = new GuessClub();
		club.setCardNum(cardNum);
		club.setCreateId(createId);
		club.setCurrentMember(1);
		club.setClubId(clubId);
		club.setKindOf(kindOf);
		club.setMaxMember(maxMember);
		club.setName(name);
		club.setPassword(password);
		club.setNotice(notice);
		club.setStatus(0);
		club.setTotalLiveness(0);
		
		GuessClubMember member = new GuessClubMember();
		member.setFlag(0);
		member.setNickname(create.getNickname());
		member.setHeadimgurl(create.getHeadimgurl());
		member.setClubId(clubId);
		member.setUserId(create.getUserId());
		member.setGuessCount(0);
		member.setStatus(1);
		member.setTotalLiveness(0);
		member.setUnauditedLiveness(0);
		member.setWinCount(0);
		member.setJoinTime(new Date());
		
		GuessClubLog log = new GuessClubLog();
		log.setCardNum(cardNum);
		log.setClubId(clubId);
		log.setDescr("俱乐部充卡");
		log.setTradeType(1);
		log.setSeq(""+createId);
		
		guessClubLogMapper.writerLog(log);
		guessClubMemberMapper.addMember(member);
		this.decrease(createId, cardNum, "CLUB_C", clubId+"", "创建俱乐部，扣除"+cardNum);
		guessClubMapper.createClub(club);
		
		cacheMember(clubId, member, 1);
		return club;
	}

	@Override
	@Transactional(readOnly=false,timeout=10,propagation=Propagation.REQUIRED)
	@CachePut(value = "guess:club:info", keyGenerator="guessClubInfoKeyGenerator")
	public GuessClub modify(Integer clubId, Integer createId, 
			Integer maxMember, String name, String password, String icon,
			String notice) {
		// TODO Auto-generated method stub
		GuessClub club = guessClubMapper.findById(createId, clubId);
		if(club == null){
			throw new I18nMessageException("30001","俱乐部不存在");
		}
		if(maxMember != null){
			club.setMaxMember(maxMember);
		}
		if(name != null){
			club.setName(name);
		}
		if(password != null){
			club.setPassword(password);
		}
		if(icon != null){
			club.setIcon(icon);
		}
		if(notice != null){
			club.setNotice(notice);
		}
		guessClubMapper.update(club);
		return club;
	}

	@Override
	@Transactional(readOnly=false,timeout=10,propagation=Propagation.REQUIRED)
	@CacheEvict(value="guess:club:info", keyGenerator="guessClubInfoKeyGenerator")
	public GuessClub delete(Integer clubId, Integer createId) {
		GuessClub club = guessClubMapper.findById(createId, clubId);
		if(club == null){
			throw new I18nMessageException("30001","俱乐部不存在");
		}
		club.setCardNum(0);
		club.setStatus(-1);
		this.increase(createId, club.getCardNum(), "CLUB_D", clubId+"", "删除俱乐部，增加"+club.getCardNum());
		guessClubMapper.update(club);
		
		List<GuessClubMember> members = this.findAllMemberByClub(clubId, null);
		
		for(GuessClubMember member: members){
			guessClubMemberMapper.quit(member.getId(), 1);
			member.setStatus(-2);
			cacheMember(clubId, member, -1);
		}
		
		String key = "guess:club:members:"+clubId;
		if(redisHash.exists(key)){
			redisHash.hDel(key);
		}
		return club;
	}

	@Override
	@Transactional(readOnly=false,timeout=10,propagation=Propagation.REQUIRED)
	public GuessClubMember applyJoin(Integer clubId, Integer userId) {
		// TODO Auto-generated method stub
		GuessClub club = guessClubMapper.findById(null, clubId);
		if(club == null){
			throw new I18nMessageException("30001","俱乐部不存在");
		}
		if(club.getMaxMember().equals(club.getCurrentMember())){
			 throw new I18nMessageException("30002","俱乐部成员已满，失败");
		}
		User user = userMapper.findById(userId);
		if(user == null){
			throw new I18nMessageException("30011");
		}
		
		GuessClubMember member = guessClubMemberMapper.findByClubAndUserId(clubId, userId);
		if(member == null){
			member = new GuessClubMember();
			member.setNickname(user.getNickname());
			member.setHeadimgurl(user.getHeadimgurl());
			member.setClubId(clubId);
			member.setUserId(userId);
			member.setGuessCount(0);
			member.setStatus(0);
			member.setTotalLiveness(0);
			member.setUnauditedLiveness(0);
			member.setWinCount(0);
			member.setJoinTime(new Date());
			guessClubMemberMapper.applyJoin(member);
			cacheMember(clubId, member, 1);
			return member;
		}
		if(member.getStatus().equals(0)){
			throw new I18nMessageException("30003","已提交申请加入，请勿重复操作");
		}
		throw new I18nMessageException("30004","已成功加入俱乐部");
	}

	@Override
	@Transactional(readOnly=false,timeout=10,propagation=Propagation.REQUIRED)
	@CachePut(value = "guess:club:info", keyGenerator="guessClubInfoKeyGenerator")
	public GuessClub approvalJoin(Integer clubId, Integer createId,
			Integer memberId, int agree) {
		// TODO Auto-generated method stub
		GuessClub club = guessClubMapper.findById(createId, clubId);
		if(club == null){
			throw new I18nMessageException("30001","俱乐部不存在");
		}
		GuessClubMember member = guessClubMemberMapper.findByClubAndId(clubId, memberId);
		if(member == null){
			throw new I18nMessageException("30005","操作失败，成员不存在");
		}
		if(member.getStatus().equals(0)){
			if(agree == 1){
				club.setCurrentMember(club.getCurrentMember()+1);
				guessClubMapper.update(club);
				guessClubMemberMapper.join(memberId,1);
				member.setStatus(1);
				cacheMember(clubId, member, 1);
			}else{
				guessClubMemberMapper.quit(memberId,1);
				member.setStatus(-2);
				cacheMember(clubId, member, -1);
			}
			return club;
		}
		if(member.getStatus().equals(1)){
			throw new I18nMessageException("30006","已加入成功，请勿重复增加");
		}
		return null;
	}

	@Override
	@Transactional(readOnly=false,timeout=10,propagation=Propagation.REQUIRED)
	@CachePut(value = "guess:club:info", keyGenerator="guessClubInfoKeyGenerator")
	public GuessClub applyLeave(Integer clubId, Integer userId) {
		GuessClub club = guessClubMapper.findById(null, clubId);
		if(club == null){
			throw new I18nMessageException("30001","俱乐部不存在");
		}
		GuessClubMember member = guessClubMemberMapper.findByClubAndUserId(clubId, userId);
		if(member == null){
			throw new I18nMessageException("30005","操作失败，成员不存在");
		}
		if(member.getStatus().equals(1)){
			club.setCurrentMember(club.getCurrentMember()-1);
			guessClubMapper.update(club);
			guessClubMemberMapper.applyQuit(member.getId());
			member.setStatus(-1);
			cacheMember(clubId, member, 1);
			return club;
		}
		throw new I18nMessageException("30007","申请失败，成员未加入俱乐中");
	}

	@Override
	@Transactional(readOnly=false,timeout=10,propagation=Propagation.REQUIRED)
	@CachePut(value = "guess:club:info", keyGenerator="guessClubInfoKeyGenerator")
	public GuessClub approvalLeave(Integer clubId, Integer createId,
			Integer memberId, int agree) {
		GuessClub club = guessClubMapper.findById(createId, clubId);
		if(club == null){
			throw new I18nMessageException("30001","俱乐部不存在");
		}
		GuessClubMember member = guessClubMemberMapper.findByClubAndId(clubId, memberId);
		if(member == null){
			throw new I18nMessageException("30005","操作失败，成员不存在");
		}
		if(member.getStatus().equals(-1)){
			if(agree == 1){
				guessClubMemberMapper.quit(memberId,1);
				member.setStatus(-2);
				cacheMember(clubId, member, -1);
			}else{
				club.setCurrentMember(club.getCurrentMember()+1);
				guessClubMapper.update(club);
				guessClubMemberMapper.join(memberId,null);
				member.setStatus(1);
				cacheMember(clubId, member, 1);
			}
			return club;
		}
		throw new I18nMessageException("30009","审核失败，成员未申请退出");
	}

	@Override
	@Transactional(readOnly=false,timeout=10,propagation=Propagation.REQUIRED)
	@CachePut(value = "guess:club:info", keyGenerator="guessClubInfoKeyGenerator")
	public GuessClub removeMemeber(Integer clubId, Integer createId,
			Integer memberId) {
		GuessClub club = guessClubMapper.findById(createId, clubId);
		if(club == null){
			throw new I18nMessageException("30001","俱乐部不存在");
		}
		GuessClubMember member = guessClubMemberMapper.findByClubAndId(clubId, memberId);
		if(member == null){
			throw new I18nMessageException("30005","操作失败，成员不存在");
		}
		club.setCurrentMember(club.getCurrentMember()-1);
		guessClubMapper.update(club);
		guessClubMemberMapper.quit(memberId, 1);
		member.setStatus(-2);
		cacheMember(clubId, member, -1);
		return club;
	}

	@Override
	@Transactional(readOnly=false,timeout=10,propagation=Propagation.REQUIRED)
	@CachePut(value = "guess:club:info", keyGenerator="guessClubInfoKeyGenerator")
	public GuessClub recharge(Integer clubId, Integer createId, Integer number) {
		// TODO Auto-generated method stub
		GuessClub club = guessClubMapper.findById(createId, clubId);
		if(club == null){
			throw new I18nMessageException("30001","俱乐部不存在");
		}
		
		GuessClubLog log = new GuessClubLog();
		log.setCardNum(number);
		log.setClubId(clubId);
		log.setDescr("俱乐部充卡");
		log.setTradeType(1);
		log.setSeq(""+createId);
		
		guessClubLogMapper.writerLog(log);
		this.decrease(createId, number, "CLUB_R", clubId+"", "俱乐部充值，扣除"+number);
		club.setCardNum(club.getCardNum()+number);
		guessClubMapper.update(club);
		return club;
	}
	
	@Override
	public GuessClub findClubInfo(Integer clubId) {
		// TODO Auto-generated method stub
		Set<String> keys = redisCache.searchKey("guess:club:info:*:"+clubId);
		for(String key : keys){
			GuessClub club = (GuessClub) redisCache.getSys(key);
			club.setCardNum(null);
			club.setCreateId(null);
			club.setId(null);
			club.setMaxMember(null);
			club.setPassword(null);
			return club;
		}
		return null;
	}

	@Override
	public List<GuessClub> findAllMyClub(Integer createId) {
		// TODO Auto-generated method stub
		Set<String> keys = redisCache.searchKey("guess:club:info:"+createId+":*");
		List<GuessClub> clubs = new ArrayList<GuessClub>();
		for(String key : keys){
			clubs.add((GuessClub) redisCache.getSys(key));
		}
		return clubs;
	}
	
	@Override
	public List<GuessClub> findAllMyJoinClub(Integer userId) {
		List<GuessClubMember> members = guessClubMemberMapper.findByUserIdForMember(userId);
		List<GuessClub> clubs = new ArrayList<GuessClub>();
		if(members != null){
			for(GuessClubMember member : members){
				Set<String> keys = redisCache.searchKey("guess:club:info:*:"+member.getClubId());
				for(String key : keys){
					clubs.add((GuessClub) redisCache.getSys(key));
				}
			}
		}
		return clubs;
	}

	@Override
	@Cacheable(value="guess:club:info", keyGenerator="guessClubInfoKeyGenerator")
	public GuessClub findClubInfo(Integer clubId, Integer createId) {
		// TODO Auto-generated method stub
		return guessClubMapper.findById(createId, clubId);
	}

	@Override
	public List<GuessClubMember> findAllMemberByClub(Integer clubId, Integer status) {
		Map<String, Object> members = cacheMember(clubId, null, null);
		List<GuessClubMember> mems = new ArrayList<GuessClubMember>();
		if(members != null && !members.isEmpty()){
			for(String key : members.keySet()){
				GuessClubMember member = (GuessClubMember) members.get(key);
				if((status == null && !member.getStatus().equals(-2)) 
						|| member.getStatus().equals(status)){
					mems.add(member);
				}
			}
		}
		return mems;
	}
	
	@Override
	public GuessClubMember findMemberByUserId(Integer clubId, Integer userId) {
		// TODO Auto-generated method stub
		
		Map<String, Object> members = cacheMember(clubId, null, null);
		if(members != null && !members.isEmpty()){
			for(String key : members.keySet()){
				GuessClubMember member = (GuessClubMember) members.get(key);
				if(member.getStatus().equals(1) && member.getUserId().equals(userId)){
					return member; 
				}
			}
		}
		return null;
	}

	@Override
	@Transactional(readOnly=false,timeout=10,propagation=Propagation.REQUIRED)
	@CachePut(value = "guess:club:info", keyGenerator="guessClubInfoKeyGenerator")
	public GuessClub checkLiveness(Integer clubId, Integer createId,
			Integer memberId) {
		GuessClub club = guessClubMapper.findById(createId, clubId);
		if(club == null){
			throw new I18nMessageException("30001","俱乐部不存在");
		}
		GuessClubMember member = guessClubMemberMapper.findByClubAndId(clubId, memberId);
		if(member == null){
			throw new I18nMessageException("30005","操作失败，成员不存在");
		}
		if(member.getUnauditedLiveness() < 1){
			throw new I18nMessageException("30008","该玩家没有未审核活跃度");
		}
		club.setTotalLiveness(club.getTotalLiveness()+member.getUnauditedLiveness());
		guessClubMapper.update(club);
		member.setTotalLiveness(member.getTotalLiveness() + member.getUnauditedLiveness());
		member.setUnauditedLiveness(0);
		member.setLastUnaudited(new Date());
		guessClubMemberMapper.update(member);
		cacheMember(clubId, member, 1);
		return club;
	}

}
