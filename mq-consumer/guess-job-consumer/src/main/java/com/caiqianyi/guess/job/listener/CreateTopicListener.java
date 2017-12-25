package com.caiqianyi.guess.job.listener;

import javax.annotation.Resource;

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

import com.caiqianyi.guess.caipiao.entity.LotteryIssue;
import com.caiqianyi.guess.caipiao.service.ILotteryCatService;
import com.caiqianyi.guess.caipiao.service.ILotteryGuessService;
import com.caiqianyi.guess.job.config.JobDirectRabbitConfig;
import com.google.gson.Gson;

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
				LotteryIssue issue = lotteryCatService.getLotteryService(kindOf).getCurrentIssue();
				logger.debug("issue={}",new Gson().toJson(issue));
				if(issue != null){
					lotteryGuessService.createTopicByIssueForClub(issue);
				}
			}  catch (Exception e) {
				e.printStackTrace();
			}
		}
    }
}
