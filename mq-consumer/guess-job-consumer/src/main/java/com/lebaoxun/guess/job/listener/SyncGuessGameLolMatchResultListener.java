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
 * 同步LOL比赛赛果
 * @author caiqianyi
 *
 */
@Component
@RabbitListener(queues = JobDirectRabbitConfig.SYNC_GUESS_GAME_LOL_MATCH_RESULT_JOB)
public class SyncGuessGameLolMatchResultListener {

	private Logger logger = LoggerFactory.getLogger(SyncGuessGameLolMatchResultListener.class);
	
	//@Resource
	//private ILolGuessTopicService lolGuessTopicService;
	
	@Bean 
    public Queue queueSyncGuessGameLolMatchResultJob() {
        return new Queue(JobDirectRabbitConfig.SYNC_GUESS_GAME_LOL_MATCH_RESULT_JOB);
    }

    @Bean
    Binding bindingDirectExchangeSyncGuessGameLolMatchResultJob(Queue queueSyncGuessGameLolMatchResultJob, DirectExchange directExchange) {
        return BindingBuilder.bind(queueSyncGuessGameLolMatchResultJob).to(directExchange).with(JobDirectRabbitConfig.SYNC_GUESS_GAME_LOL_MATCH_RESULT_JOB);
    }
	
	@RabbitHandler
    public void receive(Object data) {
		Message message = (Message) data;
		String body = new String(message.getBody());
		logger.debug("body={}",body);
		try {
			//lolGuessTopicService.announceResults();
		}  catch (Exception e) {
			e.printStackTrace();
		}
    }
}
