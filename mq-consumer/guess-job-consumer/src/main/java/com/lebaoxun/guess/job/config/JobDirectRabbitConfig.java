package com.lebaoxun.guess.job.config;

public class JobDirectRabbitConfig {
	/**
	 * 同步竞猜奖金  JOB
	 */
	public static final String SYNC_BONUS_JOB = "guess.sync.bonus.job";
	/**
	 * 同步竞猜话题 是否结束 JOB
	 */
	public static final String SYNC_GUESS_OVER_STATE_JOB = "guess.sync.over.state.job";
	/**
	 * 同步比赛结果 JOB
	 */
	public static final String SYNC_GUESS_GAME_LOL_MATCH_RESULT_JOB = "guess.sync.game.lol.match.result.job";
	
	/**
	 * 同步LOL比赛
	 */
	public static final String SYNC_GUESS_GAME_LOL_MATCH_JOB = "guess.sync.game.lol.match.job";
	/**
	 * 同步订单返奖 JOB
	 */
	public static final String SYNC_GUESS_ORDER_BACK_MONEY_JOB = "guess.sync.order.back.money.job";
	
	
	public static final String CREAT_LOTTERY_TOPIC = "lottery.create.topic";
	public static final String SYNC_LOTTERY_ISSUE_JOB = "lottery.sync.issue.job";
	
	public static final String SYNC_LOTTERY_OPENCODE_JOB = "lottery.sync.opencode.job";
	
	public static final String SYNC_GUESS_JCLQ_MATCH_JOB = "guess.sync.jclq.match.job";
	public static final String JCLQ_MATCH_LIVE_JOB = "jclq.match.live.job";
}
