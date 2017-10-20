package com.caiqianyi.guess.match.game.service;

import java.util.List;

import com.caiqianyi.guess.entity.GuessTopic;
import com.caiqianyi.guess.game.entity.GameMatch;

public interface ILoLGuessTopicService {

	/**
	 * 创建关于比赛胜负竞猜话题
	 * @param match 比赛
	 * @return
	 */
	List<GuessTopic> createTopicForSF(GameMatch match);
	
	/**
	 * 创建关于比赛比分竞猜话题
	 * @param match
	 * @return
	 */
	GuessTopic createTopicForBF(GameMatch match);
	
	/**
	 * 创建关于冠军竞猜话题
	 * @param match
	 * @return
	 */
	GuessTopic createTopicForFirst(GameMatch match);
	
	/**
	 * 创建关于比赛一血竞猜话题
	 * @param match
	 * @return
	 */
	List<GuessTopic> createTopicForFirstBlood(GameMatch match);
	
	/**
	 * 创建关于比赛一塔竞猜话题
	 * @param match
	 * @return
	 */
	List<GuessTopic> createTopicForFirstTurret(GameMatch match);
	
}
