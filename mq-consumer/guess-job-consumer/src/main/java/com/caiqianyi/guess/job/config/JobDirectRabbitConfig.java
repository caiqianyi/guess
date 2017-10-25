package com.caiqianyi.guess.job.config;

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
	public static final String SYNC_GUESS_GAME_RESULT_JOB = "guess.sync.game.result.job";
	/**
	 * 同步订单返奖 JOB
	 */
	public static final String SYNC_GUESS_ORDER_BACK_MONEY_JOB = "guess.sync.order.back.money.job";
	
}
