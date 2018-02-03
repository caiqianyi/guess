package com.caiqianyi.agent.rest;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.caiqianyi.agent.security.Oauth2SecuritySubject;
import com.caiqianyi.commons.exception.SuccessMessage;
import com.caiqianyi.guess.caipiao.service.IGuessClubService;
import com.caiqianyi.guess.service.IGuessTopicService;

@RestController
public class ClubController {

	@Resource
	private IGuessClubService clubService;

	@Resource
	private Oauth2SecuritySubject oauth2SecuritySubject;
	
	@Resource
	private IGuessTopicService guessTopicService;
	
	@Autowired
	private DiscoveryClient discoveryClient;
	
	@RequestMapping(method=RequestMethod.GET,value="/guess/club/webchat/discovery")
	public SuccessMessage serviceUrl() {
		String application = "web-chat";
		List<String> url = new ArrayList<String>();
		List<ServiceInstance> serviceInstances = discoveryClient.getInstances(application);
		for(ServiceInstance si : serviceInstances){
			String ws = "ws://"+si.getHost()+":"+si.getPort()+"/server/club/";
			url.add(ws);
		}
	    return new SuccessMessage(url);
	}

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
			@RequestParam(value = "maxMember") Integer maxMember,
			@RequestParam(value = "name") String name,
			@RequestParam(value = "password", required = false) String password,
			@RequestParam(value = "notice", required = false) String notice,
			@RequestParam(value = "cardNum") Integer cardNum,
			@RequestParam(value = "kindOf") String kindOf) {
		return clubService.create(oauth2SecuritySubject.getCurrentUser()
				.getUserId(), maxMember, name, password, notice, cardNum, kindOf);
	}

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
			@RequestParam(value = "maxMember", required = false) Integer maxMember,
			@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "password", required = false) String password,
			@RequestParam(value = "icon", required = false) String icon,
			@RequestParam(value = "notice", required = false) String notice) {
		return clubService.modify(clubId, oauth2SecuritySubject
				.getCurrentUser().getUserId(), maxMember, name, password, icon,
				notice);
	}

	/**
	 * 删除俱乐部
	 * 
	 * @param createId
	 * @param clubId
	 * @return 返回处理成功俱乐部ID
	 */
	@RequestMapping(value = "/guess/club/delete", method = RequestMethod.GET)
	SuccessMessage delete(@RequestParam(value = "clubId") Integer clubId) {
		return clubService.delete(clubId, oauth2SecuritySubject
				.getCurrentUser().getUserId());
	}

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
	SuccessMessage applyJoin(@RequestParam(value = "clubId") Integer clubId) {
		return clubService.applyJoin(clubId, oauth2SecuritySubject
				.getCurrentUser().getUserId());
	}

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
			@RequestParam(value = "memberId") Integer memberId,
			@RequestParam(value = "agree") int agree) {
		return clubService.approvalJoin(clubId, oauth2SecuritySubject
				.getCurrentUser().getUserId(), memberId, agree);
	}

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
	SuccessMessage applyLeave(@RequestParam(value = "clubId") Integer clubId) {
		return clubService.applyLeave(clubId, oauth2SecuritySubject
				.getCurrentUser().getUserId());
	}

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
			@RequestParam(value = "memberId") Integer memberId, int agree) {
		return clubService.approvalLeave(clubId, oauth2SecuritySubject
				.getCurrentUser().getUserId(), memberId, agree);
	}

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
	@RequestMapping(value = "/guess/club/remove/memeber", method = RequestMethod.GET)
	SuccessMessage removeMemeber(
			@RequestParam(value = "clubId") Integer clubId,
			@RequestParam(value = "memberId") Integer memberId) {
		return clubService.removeMemeber(clubId, oauth2SecuritySubject
				.getCurrentUser().getUserId(), memberId);
	}

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
			@RequestParam(value = "number") Integer number) {
		return clubService.recharge(clubId, oauth2SecuritySubject
				.getCurrentUser().getUserId(), number);
	}

	/**
	 * 查询我的所有俱乐部
	 * 
	 * @param createId
	 *            创建人
	 * @return 返回俱乐部信息
	 */
	@RequestMapping(value = "/guess/club/myCreateBy", method = RequestMethod.GET)
	SuccessMessage myCreateBy() {
		return clubService.findAllMyClub(oauth2SecuritySubject.getCurrentUser()
				.getUserId());
	}
	
	@RequestMapping(value = "/guess/club/myJoinBy", method = RequestMethod.GET)
	SuccessMessage myJoinBy() {
		return clubService.findAllMyJoinClub(oauth2SecuritySubject.getCurrentUser()
				.getUserId());
	}

	/**
	 * 查询俱乐部信息，包括成员信息
	 * 
	 * @param createId
	 *            创建人
	 * @param clubId
	 *            俱乐部ID
	 * @return
	 */
	@RequestMapping(value = "/guess/club/info", method = RequestMethod.GET)
	SuccessMessage findClubInfo(@RequestParam(value = "clubId") Integer clubId) {
		return clubService.findClubInfo(clubId, oauth2SecuritySubject
				.getCurrentUser().getUserId());
	}

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
	@RequestMapping(value = "/guess/club/members", method = RequestMethod.GET)
	SuccessMessage findAllMemberByClub(
			@RequestParam(value = "clubId") Integer clubId,
			@RequestParam(value = "status", required = false) Integer status) {
		return clubService.findAllMemberByClub(clubId, status);
	}
	
	@RequestMapping(value = "/guess/club/into", method = RequestMethod.GET)
	SuccessMessage findMemberByUserId(@RequestParam(value = "clubId") Integer clubId){
		return clubService.findMemberByUserId(clubId, oauth2SecuritySubject.getCurrentUser()
				.getUserId());
	}

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
			@RequestParam(value = "memberId") Integer[] memberId) {
		return clubService.checkLiveness(clubId, oauth2SecuritySubject
				.getCurrentUser().getUserId(), memberId);
	}
	
	/**
	 * 查询俱乐部当前可参与的竞猜话题
	 * @param clubId 俱乐部ID
	 * @param topicType 话题类型 可不传
	 * @return
	 */
	@RequestMapping(value="/guess/topic/current/{clubId}",method=RequestMethod.POST)
	SuccessMessage findCurrentTopicsLeftOptionsBy(@PathVariable("clubId")Integer clubId,
			@RequestParam(value="topicType",required=false) String topicType){
		return guessTopicService.findCurrentTopicsLeftOptionsBy(clubId, topicType);
	}
}
