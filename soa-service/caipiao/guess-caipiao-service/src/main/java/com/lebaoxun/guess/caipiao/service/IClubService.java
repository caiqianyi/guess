package com.lebaoxun.guess.caipiao.service;

import java.util.List;
import java.util.Map;

import com.lebaoxun.guess.entity.GuessClub;
import com.lebaoxun.guess.entity.GuessClubMember;

public interface IClubService {

	/**
	 * 更新成员属性
	 * @param clubId
	 * @param member
	 * @param flag
	 * @return
	 */
	public Map<String, Object> cacheMember(Integer clubId,GuessClubMember member,Integer flag);
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
	GuessClub create(Integer clubId, Integer createId, Integer maxMember, String name,
			String password, String notice, Integer cardNum, String kindOf);

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
	GuessClub modify(Integer clubId, Integer createId, Integer maxMember,
			String name, String password, String icon, String notice);

	/**
	 * 删除俱乐部
	 * 
	 * @param createId
	 * @param clubId
	 * @return 返回处理成功俱乐部ID
	 */
	GuessClub delete(Integer clubId, Integer createId);

	/**
	 * 申请加入
	 * 
	 * @param cludId
	 *            俱乐部ID
	 * @param userId
	 *            申请玩家ID
	 * @return 返回处理成功玩家ID
	 */
	GuessClubMember applyJoin(Integer clubId, Integer userId);

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
	GuessClub approvalJoin(Integer clubId, Integer createId, Integer memberId,
			int agree);

	/**
	 * 申请离开工会
	 * @param cludId 俱乐部ID
	 * @param userId 玩家成员ID
	 * @return 返回处理成功玩家ID
	 */
	GuessClub applyLeave(Integer cludId, Integer userId);
	
	/**
	 * 审阅申请离开工会
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
	GuessClub approvalLeave(Integer clubId, Integer createId, Integer memberId,
			int agree);
	
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
	GuessClub removeMemeber(Integer clubId, Integer createId, Integer memberId);
	
	/**
	 * 充卡
	 * @param createId 创建人
	 * @param clubId 俱乐部ID
	 * @param number 玩家ID
	 * @return
	 */
	GuessClub recharge(Integer clubId, Integer createId, Integer number);
	
	/**
	 * 查询我的所有俱乐部
	 * @param createId 创建人
	 * @return 返回俱乐部信息
	 */
	List<GuessClub> findAllMyClub(Integer createId);
	
	/**
	 * 查询我所有加入俱乐部
	 * @param userId
	 * @return
	 */
	List<GuessClub> findAllMyJoinClub(Integer userId);
	
	/**
	 * 查询俱乐部信息，包括成员信息 
	 * @param createId 创建人
	 * @param clubId 俱乐部ID
	 * @return 
	 */
	GuessClub findClubInfo(Integer clubId, Integer createId);
	
	GuessClub findClubInfo(Integer clubId);
	
	/**
	 * 查询俱乐部所有成员
	 * @param createId 创建人
	 * @param clubId 俱乐部ID
	 * @param status 0=申请中，1=已加入，-1=申请退出中，-2=已退出
	 * @return 返回所有俱乐部成员，按活跃度排序
	 */
	List<GuessClubMember> findAllMemberByClub(Integer clubId, Integer status);
	
	GuessClubMember findMemberByUserId(Integer clubId, Integer userId);
	
	/**
	 * 审核成员活跃度
	 * @param createId 创建人
	 * @param clubId 俱乐部ID
	 * @param memberId 玩家ID
	 * @return
	 */
	GuessClub checkLiveness(Integer clubId, Integer createId, Integer memberId);
}
