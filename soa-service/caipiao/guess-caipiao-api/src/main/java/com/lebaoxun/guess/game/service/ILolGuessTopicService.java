package com.lebaoxun.guess.game.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.lebaoxun.commons.exception.SuccessMessage;
import com.lebaoxun.guess.game.entity.GameMatch;
import com.lebaoxun.guess.game.service.hystrix.LolGuessTopicServiceHystrix;

@FeignClient(value="guess-caipiao-service",fallback=LolGuessTopicServiceHystrix.class)
public interface ILolGuessTopicService {
	
	/**
	 * 公布结果
	 * @return
	 */
	@RequestMapping(value="/lol/guess/topic/announceResults/",method=RequestMethod.GET)
	SuccessMessage announceResults();
	/**
	 * 公布结果
	 * @return
	 */
	@RequestMapping(value="/lol/guess/topic/announceResults/{matchId}",method=RequestMethod.GET)
	SuccessMessage announceResults(@PathVariable("matchId")String matchId);
	
	/**
	 * 系统更新创建LOL所有竞猜话题
	 * @param match
	 * @param createBy
	 * @param ClubId
	 * @return
	 */
	@RequestMapping(value="/lol/guess/topic/updatedLoLTopic/",method=RequestMethod.GET)
	SuccessMessage updatedLoLTopic(@RequestParam(value="startDate",required=false)String startDate);
	
	/**
	 * 创建LOL所有竞猜话题
	 * @param match
	 * @param createBy
	 * @param ClubId
	 * @return
	 */
	@RequestMapping(value="/lol/guess/topic/createAll/",method=RequestMethod.POST)
	SuccessMessage createAll(@RequestBody GameMatch match,
			@RequestParam(value="createBy",required=false)String createBy,
			@RequestParam(value="ClubId",required=false)Integer ClubId);
	/**
	 * 创建关于比赛胜负竞猜话题
	 * @param match 比赛
	 * @param createBy 创建人 允许NULL
	 * @param ClubId 房间号 允许NULL
	 * @return
	 */
	@RequestMapping(value="/lol/guess/topic/createTopicForSF/",method=RequestMethod.POST)
	SuccessMessage createTopicForSF(@RequestBody GameMatch match,
			@RequestParam(value="createBy",required=false)String createBy,
			@RequestParam(value="ClubId",required=false)Integer ClubId);
	
	/**
	 * 创建关于比赛比分竞猜话题
	 * @param match
	 * @param createBy 创建人 允许NULL
	 * @param ClubId 房间号 允许NULL
	 * @return
	 */
	@RequestMapping(value="/lol/guess/topic/createTopicForBF/",method=RequestMethod.POST)
	SuccessMessage createTopicForBF(@RequestBody GameMatch match,
			@RequestParam(value="createBy",required=false)String createBy,
			@RequestParam(value="ClubId",required=false)Integer ClubId);
	
	/**
	 * 创建关于冠军竞猜话题
	 * @param match
	 * @param createBy 创建人 允许NULL
	 * @param ClubId 房间号 允许NULL
	 * @return
	 */
	@RequestMapping(value="/lol/guess/topic/createTopicForFirst/",method=RequestMethod.POST)
	SuccessMessage createTopicForFirst(@RequestBody GameMatch match,
			@RequestParam(value="createBy",required=false)String createBy,
			@RequestParam(value="ClubId",required=false)Integer ClubId);
	
	/**
	 * 创建关于比赛一血竞猜话题
	 * @param match
	 * @param createBy 创建人 允许NULL
	 * @param ClubId 房间号 允许NULL
	 * @return
	 */
	@RequestMapping(value="/lol/guess/topic/createTopicForFirstBlood/",method=RequestMethod.POST)
	SuccessMessage createTopicForFirstBlood(@RequestBody GameMatch match,
			@RequestParam(value="createBy",required=false)String createBy,
			@RequestParam(value="ClubId",required=false)Integer ClubId);
	
	/**
	 * 创建关于比赛一塔竞猜话题
	 * @param match
	 * @param createBy 创建人 允许NULL
	 * @param ClubId 房间号 允许NULL
	 * @return
	 */
	@RequestMapping(value="/lol/guess/topic/createTopicForFirstTurret/",method=RequestMethod.POST)
	SuccessMessage createTopicForFirstTurret(@RequestBody GameMatch match,
			@RequestParam(value="createBy",required=false)String createBy,
			@RequestParam(value="ClubId",required=false)Integer ClubId);
	
	/**
	 * 创建关于比赛总人头数单双竞猜话题
	 * @param match
	 * @param createBy 创建人 允许NULL
	 * @param ClubId 房间号 允许NULL
	 * @return
	 */
	@RequestMapping(value="/lol/guess/topic/createTopicForSOD/",method=RequestMethod.POST)
	SuccessMessage createTopicForSOD(@RequestBody GameMatch match,
			@RequestParam(value="createBy",required=false)String createBy,
			@RequestParam(value="ClubId",required=false)Integer ClubId);
}
