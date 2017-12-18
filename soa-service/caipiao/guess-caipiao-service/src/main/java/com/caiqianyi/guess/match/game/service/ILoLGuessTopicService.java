package com.caiqianyi.guess.match.game.service;

import java.util.List;

import com.caiqianyi.guess.entity.GuessTopic;
import com.caiqianyi.guess.entity.GuessTopicOption;
import com.caiqianyi.guess.game.entity.GameMatch;

public interface ILoLGuessTopicService {
	
	/**
	 * 公布结果
	 * @return
	 */
	List<GuessTopicOption> announceResults();
	/**
	 * 公布结果
	 * @return
	 */
	List<GuessTopicOption> announceResults(String matchId);
	
	/**
	 * 系统更新创建LOL所有竞猜话题
	 * @param match
	 * @param createBy
	 * @param clubId
	 * @return
	 */
	List<GuessTopic> updatedLoLTopic(String startDate);
	
	/**
	 * 创建LOL所有竞猜话题
	 * @param match
	 * @param createBy
	 * @param clubId
	 * @return
	 */
	List<GuessTopic> createAll(GameMatch match,String createBy,Integer clubId);
	/**
	 * 创建关于比赛胜负竞猜话题
	 * @param match 比赛
	 * @param createBy 创建人 允许NULL
	 * @param clubId 房间号 允许NULL
	 * @return
	 */
	GuessTopic createTopicForSF(GameMatch match,String createBy,Integer clubId);
	
	/**
	 * 创建关于比赛比分竞猜话题
	 * @param match
	 * @param createBy 创建人 允许NULL
	 * @param clubId 房间号 允许NULL
	 * @return
	 */
	GuessTopic createTopicForBF(GameMatch match,String createBy,Integer clubId);
	
	/**
	 * 创建关于冠军竞猜话题
	 * @param match
	 * @param createBy 创建人 允许NULL
	 * @param clubId 房间号 允许NULL
	 * @return
	 */
	GuessTopic createTopicForFirst(GameMatch match,String createBy,Integer clubId);
	
	/**
	 * 创建关于比赛一血竞猜话题
	 * @param match
	 * @param createBy 创建人 允许NULL
	 * @param clubId 房间号 允许NULL
	 * @return
	 */
	List<GuessTopic> createTopicForFirstBlood(GameMatch match,String createBy,Integer clubId);
	
	/**
	 * 创建关于比赛一塔竞猜话题
	 * @param match
	 * @param createBy 创建人 允许NULL
	 * @param clubId 房间号 允许NULL
	 * @return
	 */
	List<GuessTopic> createTopicForFirstTurret(GameMatch match,String createBy,Integer clubId);
	
	/**
	 * 创建关于比赛总人头数单双竞猜话题
	 * @param match
	 * @param createBy 创建人 允许NULL
	 * @param clubId 房间号 允许NULL
	 * @return
	 */
	List<GuessTopic> createTopicForSOD(GameMatch match,String createBy,Integer clubId);
	
}
