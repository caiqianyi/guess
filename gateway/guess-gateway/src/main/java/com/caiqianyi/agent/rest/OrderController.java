package com.caiqianyi.agent.rest;

import java.util.Calendar;
import java.util.Date;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.http.client.utils.DateUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.caiqianyi.account.entity.User;
import com.caiqianyi.agent.security.Oauth2SecuritySubject;
import com.caiqianyi.commons.exception.SuccessMessage;
import com.caiqianyi.commons.pager.Pager;
import com.caiqianyi.guess.service.IGuessOrderService;

@RestController
public class OrderController {

	@Resource
	private IGuessOrderService guessOrderService;
	
	@Resource
	private Oauth2SecuritySubject oauth2SecuritySubject;

	/**
	 * 历史战绩
	 * 
	 * @param start
	 * @param end
	 * @param pager
	 * @return
	 */
	@RequestMapping(value = "/guess/order/pastRecords", method = RequestMethod.POST)
	SuccessMessage findByUserIdForPager(
			@RequestParam(value = "start", required = false) String start,
			@RequestParam(value = "end", required = false) String end,
			Pager pager) {
		/*if (StringUtils.isBlank(start)) {
			Calendar c = Calendar.getInstance();
			c.set(Calendar.DAY_OF_YEAR, c.get(Calendar.DAY_OF_YEAR) - 90);
			start = DateUtils.formatDate(c.getTime(), "yyyy-MM-dd");
			end = DateUtils.formatDate(new Date(), "yyyy-MM-dd");
		}*/
		return new SuccessMessage(guessOrderService.findByForPager(
				oauth2SecuritySubject.getCurrentUser().getUserId(), null, null,
				null, null, start, end, pager));
	}

	/**
	 * 查询我在俱乐部中战绩列表
	 * 
	 * @param clubId
	 * @param start
	 * @param end
	 * @param pager
	 * @return
	 */
	@RequestMapping(value = "/guess/order/club/{clubId}", method = RequestMethod.POST)
	SuccessMessage findByClubIdForPager(@PathVariable("clubId") Integer clubId,
			@RequestParam(value = "start", required = false) String start,
			@RequestParam(value = "end", required = false) String end,
			Pager pager) {
		if (StringUtils.isBlank(start)) {
			Calendar c = Calendar.getInstance();
			c.set(Calendar.DAY_OF_YEAR, c.get(Calendar.DAY_OF_YEAR) - 90);
			start = DateUtils.formatDate(c.getTime(), "yyyy-MM-dd");
			end = DateUtils.formatDate(new Date(), "yyyy-MM-dd");
		}
		return new SuccessMessage(guessOrderService.findByForPager(
				oauth2SecuritySubject.getCurrentUser().getUserId(), clubId, null,
				null, null, start, end, pager));
	}

	/**
	 * 查询每期的中奖记录
	 * @param clubId 俱乐部ID
	 * @param kindOf 彩种ID
	 * @param expect 期号
	 * @return
	 */
	@RequestMapping(value = "/guess/order/winning/{clubId}/{kindOf}/{expect}", method = RequestMethod.POST)
	SuccessMessage findWinningByExpect(
			@PathVariable(value = "clubId") Integer clubId,
			@PathVariable(value = "kindOf")String kindOf,
			@PathVariable(value = "expect") String expect) {
		return new SuccessMessage(guessOrderService.findAllBy(
				oauth2SecuritySubject.getCurrentUser().getUserId(), clubId,
				1, null, kindOf, expect));
	}
	/**
	 * 查询每期的中奖记录
	 * @param clubId 俱乐部ID
	 * @param kindOf 彩种ID
	 * @param expect 期号
	 * @return
	 */
	@RequestMapping(value = "/guess/order/{clubId}/{kindOf}/{expect}/{status}", method = RequestMethod.POST)
	SuccessMessage findOrderByExpect(
			@RequestParam(value = "clubId") Integer clubId,
			@RequestParam(value = "Integer") Integer status,
			@RequestParam(value = "kindOf")String kindOf,
			@RequestParam(value = "expect") String expect) {
		User user = oauth2SecuritySubject.getCurrentUser();
		return new SuccessMessage(guessOrderService.findAllBy(
				null , clubId,
				status , null, kindOf, expect));
	}
	
	/**
	 * 参与竞猜
	 * @param optionId
	 * @param diamond
	 * @return
	 */
	@RequestMapping(value="/guess/order/joinGuess/",method=RequestMethod.GET)
	SuccessMessage joinGuess(@RequestParam(value = "optionId") String[] optionId,@RequestParam(value = "diamond") Integer diamond){
		return guessOrderService.joinGuess(oauth2SecuritySubject.getCurrentUser().getUserId(), optionId, diamond);
	}
	
}
