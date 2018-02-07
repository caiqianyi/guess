package com.lebaoxun.guess.job.listener;

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

import com.lebaoxun.guess.job.config.JobDirectRabbitConfig;

/**
 * 同步LOL比赛
 * @author caiqianyi
 *
 */
@Component
@RabbitListener(queues = JobDirectRabbitConfig.SYNC_GUESS_GAME_LOL_MATCH_JOB)
public class SyncGuessGameLolMatchListener {

	private Logger logger = LoggerFactory.getLogger(SyncGuessGameLolMatchListener.class);
	
	//@Resource
	//private ILolGuessTopicService lolGuessTopicService;
	
	@Bean 
    public Queue queueSyncGuessGameLolMatchJob() {
        return new Queue(JobDirectRabbitConfig.SYNC_GUESS_GAME_LOL_MATCH_JOB);
    }

    @Bean
    Binding bindingDirectExchangeSyncGuessGameLolMatchJob(Queue queueSyncGuessGameLolMatchJob, DirectExchange directExchange) {
        return BindingBuilder.bind(queueSyncGuessGameLolMatchJob).to(directExchange).with(JobDirectRabbitConfig.SYNC_GUESS_GAME_LOL_MATCH_JOB);
    }
	
	@RabbitHandler
    public void receive(Object data) {
		Message message = (Message) data;
		String body = new String(message.getBody());
		logger.debug("body={}",body);
		try {
			//lolGuessTopicService.updatedLoLTopic(body);
		}  catch (Exception e) {
			e.printStackTrace();
		}
    }
}
