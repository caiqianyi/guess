package com.lebaoxun.guess.caipiao.service.hystrix;

import org.springframework.stereotype.Component;

import com.lebaoxun.commons.exception.SuccessMessage;
import com.lebaoxun.guess.caipiao.service.IGuessClubService;

@Component
public class GuessClubServiceHystrix implements IGuessClubService {

	@Override
	public SuccessMessage create(Integer createId, Integer maxMember,
			String name, String password, String notice, Integer cardNum, 
			String kindOf) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SuccessMessage modify(Integer clubId, Integer createId,
			Integer maxMember, String name, String password, String icon,
			String notice) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SuccessMessage delete(Integer clubId, Integer createId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SuccessMessage applyJoin(Integer clubId, Integer userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SuccessMessage approvalJoin(Integer clubId, Integer createId,
			Integer memberId, int agree) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SuccessMessage applyLeave(Integer clubId, Integer userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SuccessMessage approvalLeave(Integer clubId, Integer createId,
			Integer memberId, int agree) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SuccessMessage removeMemeber(Integer clubId, Integer createId,
			Integer memberId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SuccessMessage recharge(Integer clubId, Integer createId,
			Integer number) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SuccessMessage findAllMyClub(Integer createId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SuccessMessage findClubInfo(Integer clubId, Integer createId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SuccessMessage findAllMemberByClub(Integer clubId, Integer status) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SuccessMessage findMemberByUserId(Integer clubId, Integer userId) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public SuccessMessage checkLiveness(Integer clubId, Integer createId,
			Integer[] memberId) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public SuccessMessage findAllMyJoinClub(Integer userId) {
		// TODO Auto-generated method stub
		return null;
	}

}
