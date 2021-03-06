package com.lebaoxun.guess.job.jclq.listener;

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

import com.lebaoxun.guess.caipiao.service.ILotteryDataSyncService;
import com.lebaoxun.guess.job.config.JobDirectRabbitConfig;

/**
 * 同步LOL比赛
 * @author caiqianyi
 *
 */
@Component
@RabbitListener(queues = JobDirectRabbitConfig.SYNC_GUESS_JCLQ_MATCH_JOB)
public class SyncGuessJCLQMatchListener {

	private Logger logger = LoggerFactory.getLogger(SyncGuessJCLQMatchListener.class);
	
	@Resource
	private ILotteryDataSyncService lotteryDataSyncService;
	
	@Bean 
    public Queue queueSyncGuessJCLQMatchJob() {
        return new Queue(JobDirectRabbitConfig.SYNC_GUESS_JCLQ_MATCH_JOB);
    }

    @Bean
    Binding bindingDirectExchangeSyncGuessJCLQMatchJob(Queue queueSyncGuessJCLQMatchJob, DirectExchange directExchange) {
        return BindingBuilder.bind(queueSyncGuessJCLQMatchJob).to(directExchange).with(JobDirectRabbitConfig.SYNC_GUESS_JCLQ_MATCH_JOB);
    }
	
    /**
     * JCQL.MATCH.SYNC 0 0/30 * * * ? {"queue":"guess.sync.jclq.match.job","message":"sync"}
     * @param data
     */
	@RabbitHandler
    public void receive(Object data) {
		Message message = (Message) data;
		String body = new String(message.getBody());
		logger.debug("body={}",body);
		try {
			lotteryDataSyncService.syncJCLQMatch();
		}  catch (Exception e) {
			e.printStackTrace();
		}
    }
}
