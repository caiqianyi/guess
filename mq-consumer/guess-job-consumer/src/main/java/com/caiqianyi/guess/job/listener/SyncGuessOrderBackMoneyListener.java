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
 * 每30秒检查是否有返奖订单，并进行返奖
 * @author caiqianyi
 *
 */
@Component
@RabbitListener(queues = JobDirectRabbitConfig.SYNC_GUESS_ORDER_BACK_MONEY_JOB)
public class SyncGuessOrderBackMoneyListener {

	private Logger logger = LoggerFactory.getLogger(SyncGuessOrderBackMoneyListener.class);
	
	//@Resource
	//private IGuessOrderService guessOrderService;
	
	@Bean
    public Queue queueSyncGuessOrderBackMoneyJob() {
        return new Queue(JobDirectRabbitConfig.SYNC_GUESS_ORDER_BACK_MONEY_JOB);
    }

    @Bean
    Binding bindingDirectExchangeSyncGuessOrderBackMoneyJob(Queue queueSyncGuessOrderBackMoneyJob, DirectExchange directExchange) {
        return BindingBuilder.bind(queueSyncGuessOrderBackMoneyJob).to(directExchange).with(JobDirectRabbitConfig.SYNC_GUESS_ORDER_BACK_MONEY_JOB);
    }
	
	@RabbitHandler
    public void receive(Object data) {
		Message message = (Message) data;
		String body = new String(message.getBody());
		logger.debug("body={}",body);
		try {
			//guessOrderService.backBonus();
		}  catch (Exception e) {
			e.printStackTrace();
		}
    }
}
