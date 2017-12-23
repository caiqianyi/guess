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

import com.caiqianyi.guess.job.config.JobDirectRabbitConfig;

/**
 * 每5分钟更新一次 竞猜话题 每项奖金
 * @author caiqianyi
 *
 */
@Component
@RabbitListener(queues = JobDirectRabbitConfig.SYNC_BONUS_JOB)
public class SyncGuessTopicBonusListener {

	private Logger logger = LoggerFactory.getLogger(SyncGuessTopicBonusListener.class);
	
	//@Resource
	//private IGuessTopicService guessTopicService;
	
	@Bean
    public Queue queueSyncBonusJob() {
        return new Queue(JobDirectRabbitConfig.SYNC_BONUS_JOB);
    }

    @Bean
    Binding bindingDirectExchangeSyncBonusJob(Queue queueSyncBonusJob, DirectExchange directExchange) {
        return BindingBuilder.bind(queueSyncBonusJob).to(directExchange).with(JobDirectRabbitConfig.SYNC_BONUS_JOB);
    }
	
	@RabbitHandler
    public void receive(Object data) {
		Message message = (Message) data;
		String body = new String(message.getBody());
		logger.debug("body={}",body);
		try {
			//guessTopicService.calOdds(null);
		}  catch (Exception e) {
			e.printStackTrace();
		}
    }
}
