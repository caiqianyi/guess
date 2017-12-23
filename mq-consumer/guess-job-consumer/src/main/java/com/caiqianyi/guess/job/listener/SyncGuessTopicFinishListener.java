package com.caiqianyi.guess.job.listener;

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
 * 每5秒更新一次 竞猜话题 状态 是否结束
 * @author caiqianyi
 *
 */
@Component
@RabbitListener(queues = JobDirectRabbitConfig.SYNC_GUESS_OVER_STATE_JOB)
public class SyncGuessTopicFinishListener {

	private Logger logger = LoggerFactory.getLogger(SyncGuessTopicFinishListener.class);
	
	//@Resource
	//private IGuessTopicService guessTopicService;
	
	@Bean
    public Queue queueSyncGuessOverStateJob() {
        return new Queue(JobDirectRabbitConfig.SYNC_GUESS_OVER_STATE_JOB);
    }

    @Bean
    Binding bindingDirectExchangeSyncGuessOverStateJob(Queue queueSyncGuessOverStateJob, DirectExchange directExchange) {
        return BindingBuilder.bind(queueSyncGuessOverStateJob).to(directExchange).with(JobDirectRabbitConfig.SYNC_GUESS_OVER_STATE_JOB);
    }
	
	@RabbitHandler
    public void receive(Object data) {
		Message message = (Message) data;
		String body = new String(message.getBody());
		logger.debug("body={}",body);
		try {
			//guessTopicService.finishGuess();
		}  catch (Exception e) {
			e.printStackTrace();
		}
    }
}
