package com.lebaoxun.guess.caipiao.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.lebaoxun.commons.exception.SuccessMessage;
import com.lebaoxun.guess.caipiao.service.hystrix.GuessClubServiceHystrix;

@FeignClient(value="guess-caipiao-service",fallback=GuessClubServiceHystrix.class)
public interface IGuessClubService {
	
	/**
	 * 创建俱乐部
	 * 
	 * @param createId
	 *            玩家ID
	 * @param maxMember
	 *            最大玩家数
	 * @param name
	 *            俱乐部名称
	 * @param password
	 *            密码
	 * @param cardNum
	 *            房卡数
	 * @param kindOf
	 *            俱乐部分类
	 * @return
	 */
	@RequestMapping(value = "/guess/club/create", method = RequestMethod.GET)
	SuccessMessage create(
			@RequestParam(value = "createId") Integer createId,
			@RequestParam(value = "maxMember") Integer maxMember,
			@RequestParam(value = "name") String name,
			@RequestParam(value = "password", required = false) String password,
			@RequestParam(value = "notice", required = false) String notice,
			@RequestParam(value = "cardNum") Integer cardNum,
			@RequestParam(value = "kindOf") String kindOf);

	/**
	 * 设置俱乐部信息
	 * 
	 * @param createId
	 *            创建人
	 * @param clubId
	 *            俱乐部ID
	 * @param maxMember
	 *            最大玩家数
	 * @param name
	 *            俱乐部名称
	 * @param password
	 *            密码
	 * @param icon
	 *            房卡数
	 * @param notice
	 *            俱乐部分类
	 * @return
	 */
	@RequestMapping(value = "/guess/club/modify", method = RequestMethod.GET)
	SuccessMessage modify(
			@RequestParam(value = "clubId") Integer clubId,
			@RequestParam(value = "createId") Integer createId,
			@RequestParam(value = "maxMember", required = false) Integer maxMember,
			@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "password", required = false) String password,
			@RequestParam(value = "icon", required = false) String icon,
			@RequestParam(value = "notice", required = false) String notice);

	/**
	 * 删除俱乐部
	 * 
	 * @param createId
	 * @param clubId
	 * @return 返回处理成功俱乐部ID
	 */
	@RequestMapping(value = "/guess/club/delete", method = RequestMethod.GET)
	SuccessMessage delete(@RequestParam(value = "clubId") Integer clubId,
			@RequestParam(value = "createId") Integer createId);

	/**
	 * 申请加入
	 * 
	 * @param cludId
	 *            俱乐部ID
	 * @param memberId
	 *            申请玩家ID
	 * @return 返回处理成功玩家ID
	 */
	@RequestMapping(value = "/guess/club/applyJoin", method = RequestMethod.GET)
	SuccessMessage applyJoin(@RequestParam(value = "clubId") Integer clubId,
			@RequestParam(value = "userId") Integer userId);

	/**
	 * 审阅加入玩家
	 * 
	 * @param createId
	 *            创建人
	 * @param clubId
	 *            俱乐部ID
	 * @param memberId
	 *            申请玩家ID
	 * @param agree
	 *            1=批准加入,0=不批准
	 * @return 返回处理成功玩家ID
	 */
	@RequestMapping(value = "/guess/club/approvalJoin", method = RequestMethod.GET)
	SuccessMessage approvalJoin(@RequestParam(value = "clubId") Integer clubId,
			@RequestParam(value = "createId") Integer createId,
			@RequestParam(value = "memberId") Integer memberId,
			@RequestParam(value = "agree") int agree);

	/**
	 * 申请离开工会
	 * 
	 * @param cludId
	 *            俱乐部ID
	 * @param memberId
	 *            玩家成员ID
	 * @return 返回处理成功玩家ID
	 */
	@RequestMapping(value = "/guess/club/applyLeave", method = RequestMethod.GET)
	SuccessMessage applyLeave(@RequestParam(value = "clubId") Integer clubId,
			@RequestParam(value = "userId") Integer userId);

	/**
	 * 审阅申请离开工会
	 * 
	 * @param createId
	 *            创建人
	 * @param clubId
	 *            俱乐部ID
	 * @param memberId
	 *            申请玩家ID
	 * @param agree
	 *            1=批准离开,0=不批准
	 * @return 返回处理成功玩家ID
	 */
	@RequestMapping(value = "/guess/club/approvalLeave", method = RequestMethod.GET)
	SuccessMessage approvalLeave(
			@RequestParam(value = "clubId") Integer clubId,
			@RequestParam(value = "createId") Integer createId,
			@RequestParam(value = "memberId") Integer memberId,
			@RequestParam(value = "agree") int agree);
	
	/**
	 * 删除成员
	 * 
	 * @param createId
	 *            创建人
	 * @param clubId
	 *            俱乐部ID
	 * @param memberId
	 *            玩家ID
	 * @return 返回删除成功玩家ID
	 */
	@RequestMapping(value = "/guess/club/removeMemeber", method = RequestMethod.GET)
	SuccessMessage removeMemeber(
			@RequestParam(value = "clubId") Integer clubId,
			@RequestParam(value = "createId") Integer createId,
			@RequestParam(value = "memberId") Integer memberId);

	/**
	 * 充卡
	 * 
	 * @param createId
	 *            创建人
	 * @param clubId
	 *            俱乐部ID
	 * @param number
	 *            玩家ID
	 * @return
	 */
	@RequestMapping(value = "/guess/club/recharge", method = RequestMethod.GET)
	SuccessMessage recharge(@RequestParam(value = "clubId") Integer clubId,
			@RequestParam(value = "createId") Integer createId,
			@RequestParam(value = "number") Integer number);

	/**
	 * 查询我的所有俱乐部
	 * 
	 * @param createId
	 *            创建人
	 * @return 返回俱乐部信息
	 */
	@RequestMapping(value = "/guess/club/findAllMyClub", method = RequestMethod.GET)
	SuccessMessage findAllMyClub(
			@RequestParam(value = "createId") Integer createId);

	@RequestMapping(value = "/guess/club/findAllMyJoinClub", method = RequestMethod.GET)
	SuccessMessage findAllMyJoinClub(@RequestParam(value = "userId") Integer userId);
	
	/**
	 * 查询俱乐部信息，包括成员信息
	 * 
	 * @param createId
	 *            创建人
	 * @param clubId
	 *            俱乐部ID
	 * @return
	 */
	@RequestMapping(value = "/guess/club/findClubInfo", method = RequestMethod.GET)
	SuccessMessage findClubInfo(@RequestParam(value = "clubId") Integer clubId,
			@RequestParam(value = "createId") Integer createId);

	/**
	 * 查询俱乐部所有成员
	 * 
	 * @param createId
	 *            创建人
	 * @param clubId
	 *            俱乐部ID
	 * @param status
	 *            0=申请中，1=已加入，-1=申请退出中
	 * @return 返回所有俱乐部成员，按活跃度排序
	 */
	@RequestMapping(value = "/guess/club/findAllMemberByClub", method = RequestMethod.GET)
	SuccessMessage findAllMemberByClub(
			@RequestParam(value = "clubId") Integer clubId, 
			@RequestParam(value = "status",required=false) Integer status);

	@RequestMapping(value = "/guess/club/findMemberByUserId", method = RequestMethod.GET)
	SuccessMessage findMemberByUserId(
			@RequestParam(value = "clubId") Integer clubId, 
			@RequestParam(value = "userId") Integer userId);
	/**
	 * 审核成员活跃度
	 * 
	 * @param createId
	 *            创建人
	 * @param clubId
	 *            俱乐部ID
	 * @param memberId
	 *            玩家ID
	 * @return
	 */
	@RequestMapping(value = "/guess/club/checkLiveness", method = RequestMethod.GET)
	SuccessMessage checkLiveness(
			@RequestParam(value = "clubId") Integer clubId,
			@RequestParam(value = "createId") Integer createId,
			@RequestParam(value = "memberId") Integer[] memberId);
}
