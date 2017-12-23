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

import com.caiqianyi.guess.caipiao.service.ILotteryCatService;
import com.caiqianyi.guess.job.config.JobDirectRabbitConfig;

/**
 * 同步生成期号
 * @author caiqianyi
 *
 */
@Component
@RabbitListener(queues = JobDirectRabbitConfig.SYNC_LOTTERY_ISSUE_JOB)
public class CreateTopicListener {

	private Logger logger = LoggerFactory.getLogger(CreateTopicListener.class);
	
	@Resource
	private ILotteryCatService lotteryCatService;
	
	@Bean
    public Queue queueSyncLotteryIssueJob() {
        return new Queue(JobDirectRabbitConfig.SYNC_LOTTERY_ISSUE_JOB);
    }

    @Bean
    Binding bindingDirectExchangeSyncLotteryIssueJob(Queue queueSyncLotteryIssueJob, DirectExchange directExchange) {
        return BindingBuilder.bind(queueSyncLotteryIssueJob).to(directExchange).with(JobDirectRabbitConfig.SYNC_LOTTERY_ISSUE_JOB);
    }
	
	@RabbitHandler
    public void receive(Object data) {
		Message message = (Message) data;
		String body = new String(message.getBody());
		logger.debug("body={}",body);
		String kindOfs[] = body.split("\\,");
		for(String kindOf : kindOfs){
			try {
				lotteryCatService.getLotteryService(kindOf).getCurrentIssue();
			}  catch (Exception e) {
				e.printStackTrace();
			}
		}
    }
}
