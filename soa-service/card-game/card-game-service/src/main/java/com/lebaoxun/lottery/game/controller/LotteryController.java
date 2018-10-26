package com.lebaoxun.lottery.game.controller;

import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lebaoxun.commons.exception.SuccessMessage;
import com.lebaoxun.lottery.game.core.entity.DrawLotteryLog;
import com.lebaoxun.lottery.game.service.ILotteryService;

@RestController
public class LotteryController {
	
	@Resource
	private ILotteryService lotteryService;

	/**
	 * 获取抽奖机会，每1个小时领取一次
	 * @param userId
	 * @return 返回领取的记录
	 */
	@RequestMapping(value="/lottery/token/gain",method=RequestMethod.GET)
	SuccessMessage gainToken(@RequestParam("userId")Integer userId){
		return new SuccessMessage(lotteryService.gainToken(userId));
	}
	
	@RequestMapping(value="/lottery/token/isGain",method=RequestMethod.GET)
	SuccessMessage isGain(@RequestParam("userId")Integer userId){
		return new SuccessMessage(lotteryService.isGain(userId));
	}
	
	/**
	 * 购买抽奖机会
	 * @param userId
	 * @param count 购买次数
	 * @return
	 */
	@RequestMapping(value="/lottery/token/gain/buy",method=RequestMethod.GET)
	SuccessMessage buyGainToken(@RequestParam("userId")Integer userId,
			@RequestParam("count")Integer count){
		return new SuccessMessage(lotteryService.buyGainToken(userId, count));
	}
	
	/**
	 * 查询我的抽奖机会数
	 * @param userId
	 * @return 返回次数
	 */
	@RequestMapping(value="/lottery/find/myDrawCount",method=RequestMethod.GET)
	SuccessMessage findMyDrawCount(@RequestParam("userId")Integer userId){
		return new SuccessMessage(lotteryService.findMyDrawCount(userId));
	}
		
	/**
	 * 抽奖
	 * @param userId
	 * @param count 次数
	 * @return 返回抽奖操作号
	 */
	@RequestMapping(value="/lottery/draw/run",method=RequestMethod.GET)
	SuccessMessage runDrawLottery(@RequestParam("userId")Integer userId,
			@RequestParam("count")Integer count){
		return new SuccessMessage(lotteryService.runDrawLottery(userId, count));
	}
	
	/**
	 * 查询某次抽奖结果
	 * @param userId
	 * @param handle 操作ID
	 * @return
	 */
	@RequestMapping(value="/lottery/find/byHandle",method=RequestMethod.GET)
	SuccessMessage findLotteryByHandle(@RequestParam("userId")Integer userId,
			@RequestParam("handle")String handle){
		return new SuccessMessage(lotteryService.findLotteryByHandle(userId, handle));
	}
	
	/**
	 * 我的卡数
	 * @param userId
	 * @return
	 */
	@RequestMapping(value="/lottery/find/my",method=RequestMethod.GET)
	SuccessMessage findMyLottery(@RequestParam("userId")Integer userId){
		return new SuccessMessage(lotteryService.findMyLottery(userId));
	}
	
	
	/**
	 * 返回排名数据
	 * @return
	 */
	@RequestMapping(value="/lottery/find/rank/tops",method=RequestMethod.GET)
	SuccessMessage rankTops(){
		return new SuccessMessage(lotteryService.rankTops());
	}
	
	/**
	 * 返回我当前排名
	 * @param userId
	 * @return
	 */
	@RequestMapping(value="/lottery/find/rank/my",method=RequestMethod.GET)
	SuccessMessage findMyRank(@RequestParam("userId")Integer userId){
		return new SuccessMessage(lotteryService.findMyRank(userId));
	}
	
	/**
	 * 返回所有奖品
	 * @return
	 */
	@RequestMapping(value="/lottery/find/prizes",method=RequestMethod.GET)
	SuccessMessage findAllPrize(){
		return new SuccessMessage(lotteryService.findAllPrize());
	}
}
