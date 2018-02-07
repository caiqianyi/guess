package com.lebaoxun.guess.job.listener;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.lebaoxun.guess.caipiao.core.dao.IJCLQMatchMapper;
import com.lebaoxun.guess.caipiao.entity.LotteryIssue;
import com.lebaoxun.guess.caipiao.service.ILotteryCatService;
import com.lebaoxun.guess.caipiao.service.ILotteryGuessService;
import com.lebaoxun.guess.jclq.entity.JCLQMatch;
import com.lebaoxun.guess.job.config.JobDirectRabbitConfig;

/**
 * 同步生成期号
 * @author caiqianyi
 *
 */
@Component
@RabbitListener(queues = JobDirectRabbitConfig.CREAT_LOTTERY_TOPIC)
public class CreateTopicListener {

	
	private Logger logger = LoggerFactory.getLogger(CreateTopicListener.class);
	
	@Resource
	private ILotteryCatService lotteryCatService;
	
	@Resource
	private ILotteryGuessService lotteryGuessService;
	
	@Resource
	private IJCLQMatchMapper jCLQMatchMapper;
	
	@Bean
    public Queue queuecreateLotteryTopicJob() {
        return new Queue(JobDirectRabbitConfig.CREAT_LOTTERY_TOPIC);
    }

    @Bean
    Binding bindingDirectExchangecreateLotteryTopicJob(Queue queuecreateLotteryTopicJob, DirectExchange directExchange) {
        return BindingBuilder.bind(queuecreateLotteryTopicJob).to(directExchange).with(JobDirectRabbitConfig.CREAT_LOTTERY_TOPIC);
    }
	
	@RabbitHandler
    public void receive(Object data) {
		Message message = (Message) data;
		String body = new String(message.getBody());
		logger.debug("body={}",body);
		String kindOfs[] = body.split("\\,");
		for(String kindOf : kindOfs){
			try {
				if("jclq".equals(kindOf)){
					logger.debug("kindOf={}",kindOf);
					List<JCLQMatch> matchs = jCLQMatchMapper.findAllMatchByDay(null, null, "0");
					for(JCLQMatch match: matchs){
						lotteryGuessService.createTopicByIssueForClub(match.getSeq(), 1, kindOf, match.getSeq(), null, match.getEndTime());
					}
				}else{
					LotteryIssue issue = lotteryCatService.getLotteryService(kindOf).getCurrentIssue();
					logger.debug("issue={}",new Gson().toJson(issue));
					if(issue != null){
						String seq = issue.getKindOf()+"|"+issue.getExpect();
						lotteryGuessService.createTopicByIssueForClub(seq, 1, kindOf, issue.getExpect(), issue.getStartTime(), issue.getEndTime());
					}
				}
			}  catch (Exception e) {
				e.printStackTrace();
			}
		}
    }
}
