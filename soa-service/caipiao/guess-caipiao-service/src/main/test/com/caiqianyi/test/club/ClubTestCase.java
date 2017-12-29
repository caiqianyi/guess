package com.caiqianyi.test.club;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.caiqianyi.GuessCaipiaoApplication;
import com.caiqianyi.commons.utils.GenerateCode;
import com.caiqianyi.guess.caipiao.service.IClubService;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = GuessCaipiaoApplication.class)// 指定spring-boot的启动类
public class ClubTestCase {
	@Resource
	private IClubService clubService;
	
	private String kindOf = "jclq";
	private Integer createId = 88731584;
	private Integer cardNum = 200;
	private Integer maxMember = 1000;
	private Integer clubId = 787072;
	
	
	@Test
	public void create() {
		clubService.create(GenerateCode.gen(6),
				createId, maxMember, "豆蔻年华", null, cardNum, kindOf);
	}

	@Test
	public void modify() {
		Integer maxMember = 1200;
		String name = "最佳一角",password = null, icon = null, notice = null;
		clubService.modify(clubId, createId,
				maxMember, name, password, icon, notice);
	}

	/**
	 * 删除俱乐部
	 * 
	 */
	@Test
	public void delete() {
		clubService.delete(clubId, createId);
	}

	/**
	 * 申请加入
	 * 
	 */
	@Test
	public void applyJoin() {
		Integer userId = 1;
		clubService.applyJoin(clubId, 76801658);
	}

	/**
	 * 审阅加入玩家
	 */
	@Test
	public void approvalJoin() {
		Integer memberId = 3, agree = 1;
		clubService.approvalJoin(clubId, createId,
				memberId, agree);
	}

	/**
	 * 申请离开工会
	 * 
	 */
	@Test
	public void applyLeave() {
		Integer userId = null;
		clubService.applyLeave(clubId, userId);
	}

	/**
	 * 审阅申请离开工会
	 * 
	 */
	@Test
	public void approvalLeave() {
		Integer memberId = null, agree = 1;
		clubService.approvalLeave(clubId, createId,
				memberId, agree);
	}

	/**
	 * 删除成员
	 * 
	 */
	@Test
	public void removeMemeber() {
		Integer memberId = null;
		clubService.removeMemeber(clubId, createId,
				memberId);
	}

	/**
	 * 充卡
	 * @return
	 */
	@Test
	public void recharge() {
		Integer number = 1000;
		clubService.recharge(clubId, createId, number);
	}

	/**
	 * 查询我的所有俱乐部
	 */
	@Test
	public void findAllMyClub() {
		clubService.findAllMyClub(createId);
	}

	/**
	 * 查询俱乐部信息，包括成员信息
	 * @return
	 */
	@Test
	public void findClubInfo() {
		clubService.findClubInfo(clubId, createId);
	}

	/**
	 * 查询俱乐部所有成员
	 * 
	 */
	@Test
	public void findAllMemberByClub() {
		Integer status = null;
		clubService.findAllMemberByClub(clubId,
				status);
	}

	/**
	 * 审核成员活跃度
	 * @return
	 */
	@Test
	public void checkLiveness() {
		Integer memberId = 1;
		clubService.checkLiveness(clubId, createId,
				memberId);
	}
}
