package com.lebaoxun.lottery.game.service;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import com.lebaoxun.lottery.game.core.entity.DrawLotteryLog;
import com.lebaoxun.lottery.game.core.entity.Prize;

/**
 * 
 * @author Caiqianyi
 *
 */
public interface ILotteryService {
	
	/**
	 * 获取抽奖机会，每1个小时领取一次
	 * @param userId
	 * @return 返回领取的记录
	 */
	DrawLotteryLog gainToken(Integer userId);
	
	/**
	 * 支付购买次数
	 * @param userId
	 * @param count
	 * @return
	 */
	List<DrawLotteryLog> buyGainToken(Integer userId,Integer count);
	
	boolean isGain(Integer userId);
	
	/**
	 * 查询我的抽奖机会数
	 * @param userId
	 * @return 返回次数
	 */
	int findMyDrawCount(Integer userId);
	
	/**
	 * 抽奖
	 * @param userId
	 * @param count 次数
	 * @return 返回抽奖操作号
	 */
	String runDrawLottery(Integer userId,Integer count);
	
	/**
	 * 查询某次抽奖结果
	 * @param userId
	 * @param handle 操作ID
	 * @return
	 */
	List<DrawLotteryLog> findLotteryByHandle(Integer userId,String handle);
	
	/**
	 * 我的卡数
	 * @param userId
	 * @return
	 */
	TreeMap<String,Integer> findMyLottery(Integer userId);
	
	/**
	 * 返回所有排名数据
	 * @return
	 */
	List<Map<String,Object>> rankTops();
	
	/**
	 * 返回我当前排名
	 * @param userId
	 * @return
	 */
	Map<String,Object> findMyRank(Integer userId);
	
	/**
	 * 返回所有奖品
	 * @return
	 */
	List<Prize> findAllPrize();
}