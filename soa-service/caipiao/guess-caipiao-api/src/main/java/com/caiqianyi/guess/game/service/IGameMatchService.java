package com.caiqianyi.guess.game.service;

import java.util.Date;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.caiqianyi.commons.exception.SuccessMessage;
import com.caiqianyi.commons.pager.Pager;
import com.caiqianyi.guess.game.entity.GameMatch;
import com.caiqianyi.guess.game.service.hystrix.LolGuessTopicServiceHystrix;

@FeignClient(value="guess-caipiao-service",fallback=LolGuessTopicServiceHystrix.class)
public interface IGameMatchService {
	
	/**
	 * 查找比赛
	 * @param gameType 游戏类型
	 * @param matchId 比赛ID
	 * @return
	 */
	@RequestMapping(value = "/guess/game/match/findOne/matchId/{gameType}/{matchId}/", method = RequestMethod.GET)
	GameMatch findByMatchId(
			@PathVariable("gameType")String gameType,
			@PathVariable("matchId")Integer matchId);

	/**
	 * 查找比赛
	 * 
	 * @param id 主键ID
	 * @return
	 */
	@RequestMapping(value = "/guess/game/match/findOne/id/{id}/", method = RequestMethod.GET)
	GameMatch findById(@PathVariable("id")String id);

	/**
	 * 分页 查询比赛
	 * 
	 * @param match 比赛参数
	 * @param start 比赛时间 查询开始时间
	 * @param end 比赛时间 查询结束时间
	 * @param pager 分页参数
	 * @return
	 */
	@RequestMapping(value = "/guess/game/match/find/pager/", method = RequestMethod.POST)
	Pager findByForPager(
			@RequestBody(required=false) GameMatch match,
			@RequestParam(required=false) Date start,
			@RequestParam(required=false) Date end, 
			@RequestBody(required=false) Pager pager);

	/**
	 * 新增一场比赛
	 * @param match 比赛信息
	 * @return
	 */
	@RequestMapping(value = "/guess/game/match/insert/", method = RequestMethod.POST)
	SuccessMessage insert(@RequestBody GameMatch match);

	/**
	 * 修改比赛信息
	 * @param match
	 * @return
	 */
	@RequestMapping(value = "/guess/game/match/update/", method = RequestMethod.POST)
	SuccessMessage update(@RequestBody GameMatch match);

	/**
	 * 删除比赛
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/guess/game/match/delete/{id}/", method = RequestMethod.GET)
	SuccessMessage delete(@PathVariable("id") String id);

	/**
	 * 根据游戏类型，比赛ID定位删除
	 * @param gameType
	 * @param matchId
	 * @return
	 */
	@RequestMapping(value = "/guess/game/match/delete/matchId/{gameType}/{matchId}/", method = RequestMethod.GET)
	SuccessMessage deleteByMatchId(@PathVariable("gameType") String gameType,
			@PathVariable("matchId")Integer matchId);
}
