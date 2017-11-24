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

import com.caiqianyi.guess.caipiao.service.ILotteryDataService;
import com.caiqianyi.guess.job.config.JobDirectRabbitConfig;

/**
 * 每5秒更新一次 竞猜话题 状态 是否结束
 * @author caiqianyi
 *
 */
@Component
@RabbitListener(queues = JobDirectRabbitConfig.SYNC_LOTTERY_ISSUE_JOB)
public class SyncLotteryIssueListener {

	private Logger logger = LoggerFactory.getLogger(SyncLotteryIssueListener.class);
	
	@Resource
	private ILotteryDataService lotteryDataService;
	
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
				lotteryDataService.syncIssueforWeek(kindOf);
			}  catch (Exception e) {
				e.printStackTrace();
			}
		}
    }
}
