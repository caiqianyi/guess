package com.caiqianyi.guess.caipiao.match.game.service;

import java.util.Date;

import com.caiqianyi.commons.pager.Pager;
import com.caiqianyi.guess.game.entity.GameMatch;

public interface IGameMatchService {
	
	/**
	 * 查找比赛 
	 * @param gameType 游戏类型
	 * @param matchId 比赛ID
	 * @return
	 */
	GameMatch findByMatchId(String gameType,
			Integer matchId);

	/**
	 * 查找比赛
	 * @param id 主键ID
	 * @return
	 */
	GameMatch findById(String id);

	/**
	 * 分页 查询比赛
	 * @param match 比赛参数
	 * @param start 比赛时间 查询开始时间
	 * @param end 比赛时间 查询结束时间
	 * @param pager 分页参数
	 * @return
	 */
	Pager findByGameTypeForPager(GameMatch match,
			Date start, Date end, Pager pager);

	/**
	 * 新增一场比赛
	 * @param match 比赛信息
	 * @return
	 */
	int insert(GameMatch match);

	/**
	 * 修改比赛信息
	 * @param match
	 * @return
	 */
	int update(GameMatch match);

	/**
	 * 删除比赛
	 * @param id
	 * @return
	 */
	int delete(String id);

	/**
	 * 根据游戏类型，比赛ID定位删除
	 * @param gameType
	 * @param matchId
	 * @return
	 */
	int deleteByMatchId(String gameType,
			Integer matchId);
}
