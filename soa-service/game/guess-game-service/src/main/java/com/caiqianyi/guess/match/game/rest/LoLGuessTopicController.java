package com.caiqianyi.guess.match.game.rest;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.caiqianyi.commons.exception.SuccessMessage;
import com.caiqianyi.guess.game.entity.GameMatch;
import com.caiqianyi.guess.match.game.service.ILoLGuessTopicService;

@RestController
public class LoLGuessTopicController {

	@Resource
	private ILoLGuessTopicService lolGuessTopicService;
	
	/**
	 * 公布结果
	 * @return
	 */
	@RequestMapping(value="/lol/guess/topic/announceResults/",method=RequestMethod.GET)
	SuccessMessage announceResults(){
		return new SuccessMessage(lolGuessTopicService.announceResults());
	}
	
	/**
	 * 公布结果
	 * @return
	 */
	@RequestMapping(value="/lol/guess/topic/announceResults/{matchId}",method=RequestMethod.GET)
	SuccessMessage announceResults(@PathVariable("matchId")String matchId){
		return new SuccessMessage(lolGuessTopicService.announceResults(matchId));
	}
	
	/**
	 * 系统更新创建LOL所有竞猜话题
	 * @param match
	 * @param createBy
	 * @param roomId
	 * @return
	 */
	@RequestMapping(value="/lol/guess/topic/updatedLoLTopic/",method=RequestMethod.GET)
	SuccessMessage updatedLoLTopic(@RequestParam(value="startDate",required=false)String startDate){
		return new SuccessMessage(lolGuessTopicService.updatedLoLTopic(startDate));
	}
	
	/**
	 * 创建LOL所有竞猜话题
	 * @param match
	 * @param createBy
	 * @param roomId
	 * @return
	 */
	@RequestMapping(value="/lol/guess/topic/createAll/",method=RequestMethod.POST)
	SuccessMessage createAll(@RequestBody GameMatch match,
			@RequestParam(value="createBy",required=false)String createBy,
			@RequestParam(value="roomId",required=false)Integer roomId){
		return new SuccessMessage(lolGuessTopicService.createAll(match, createBy, roomId));
	}
	/**
	 * 创建关于比赛胜负竞猜话题
	 * @param match 比赛
	 * @param createBy 创建人 允许NULL
	 * @param roomId 房间号 允许NULL
	 * @return
	 */
	@RequestMapping(value="/lol/guess/topic/createTopicForSF/",method=RequestMethod.POST)
	SuccessMessage createTopicForSF(@RequestBody GameMatch match,
			@RequestParam(value="createBy",required=false)String createBy,
			@RequestParam(value="roomId",required=false)Integer roomId){
		return new SuccessMessage(lolGuessTopicService.createTopicForSF(match, createBy, roomId));
	}
	
	/**
	 * 创建关于比赛比分竞猜话题
	 * @param match
	 * @param createBy 创建人 允许NULL
	 * @param roomId 房间号 允许NULL
	 * @return
	 */
	@RequestMapping(value="/lol/guess/topic/createTopicForBF/",method=RequestMethod.POST)
	SuccessMessage createTopicForBF(@RequestBody GameMatch match,
			@RequestParam(value="createBy",required=false)String createBy,
			@RequestParam(value="roomId",required=false)Integer roomId){
		return new SuccessMessage(lolGuessTopicService.createTopicForBF(match, createBy, roomId));
	}
	
	/**
	 * 创建关于冠军竞猜话题
	 * @param match
	 * @param createBy 创建人 允许NULL
	 * @param roomId 房间号 允许NULL
	 * @return
	 */
	@RequestMapping(value="/lol/guess/topic/createTopicForFirst/",method=RequestMethod.POST)
	SuccessMessage createTopicForFirst(@RequestBody GameMatch match,
			@RequestParam(value="createBy",required=false)String createBy,
			@RequestParam(value="roomId",required=false)Integer roomId){
		return new SuccessMessage(lolGuessTopicService.createTopicForFirst(match, createBy, roomId));
	}
	
	/**
	 * 创建关于比赛一血竞猜话题
	 * @param match
	 * @param createBy 创建人 允许NULL
	 * @param roomId 房间号 允许NULL
	 * @return
	 */
	@RequestMapping(value="/lol/guess/topic/createTopicForFirstBlood/",method=RequestMethod.POST)
	SuccessMessage createTopicForFirstBlood(@RequestBody GameMatch match,
			@RequestParam(value="createBy",required=false)String createBy,
			@RequestParam(value="roomId",required=false)Integer roomId){
		return new SuccessMessage(lolGuessTopicService.createTopicForFirstBlood(match, createBy, roomId));
	}
	
	/**
	 * 创建关于比赛一塔竞猜话题
	 * @param match
	 * @param createBy 创建人 允许NULL
	 * @param roomId 房间号 允许NULL
	 * @return
	 */
	@RequestMapping(value="/lol/guess/topic/createTopicForFirstTurret/",method=RequestMethod.POST)
	SuccessMessage createTopicForFirstTurret(@RequestBody GameMatch match,
			@RequestParam(value="createBy",required=false)String createBy,
			@RequestParam(value="roomId",required=false)Integer roomId){
		return new SuccessMessage(lolGuessTopicService.createTopicForFirstTurret(match, createBy, roomId));
	}
	
	/**
	 * 创建关于比赛总人头数单双竞猜话题
	 * @param match
	 * @param createBy 创建人 允许NULL
	 * @param roomId 房间号 允许NULL
	 * @return
	 */
	@RequestMapping(value="/lol/guess/topic/createTopicForSOD/",method=RequestMethod.POST)
	SuccessMessage createTopicForSOD(@RequestBody GameMatch match,
			@RequestParam(value="createBy",required=false)String createBy,
			@RequestParam(value="roomId",required=false)Integer roomId){
		return new SuccessMessage(lolGuessTopicService.createTopicForSOD(match, createBy, roomId));
	}
}
