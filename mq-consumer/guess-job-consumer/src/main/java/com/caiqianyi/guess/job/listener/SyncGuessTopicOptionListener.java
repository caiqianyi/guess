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
import com.caiqianyi.guess.service.IGuessTopicService;

/**
 * 同步比赛结果
 * @author caiqianyi
 *
 */
@Component
@RabbitListener(queues = JobDirectRabbitConfig.SYNC_GUESS_GAME_RESULT_JOB)
public class SyncGuessTopicOptionListener {

	private Logger logger = LoggerFactory.getLogger(SyncGuessTopicOptionListener.class);
	
	@Resource
	private IGuessTopicService guessTopicService;
	
	@Bean 
    public Queue queueSyncGuessGameResultJob() {
        return new Queue(JobDirectRabbitConfig.SYNC_GUESS_GAME_RESULT_JOB);
    }

    @Bean
    Binding bindingDirectExchangeSyncGuessGameResultJob(Queue queueSyncGuessGameResultJob, DirectExchange directExchange) {
        return BindingBuilder.bind(queueSyncGuessGameResultJob).to(directExchange).with(JobDirectRabbitConfig.SYNC_GUESS_GAME_RESULT_JOB);
    }
	
	@RabbitHandler
    public void receive(Object data) {
		Message message = (Message) data;
		String body = new String(message.getBody());
		logger.debug("body={}",body);
		try {
			guessTopicService.syncTopicOption("lol");
		}  catch (Exception e) {
			e.printStackTrace();
		}
    }
}
