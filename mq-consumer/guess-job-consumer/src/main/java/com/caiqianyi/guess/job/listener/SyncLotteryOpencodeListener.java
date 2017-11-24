package com.caiqianyi.guess.job.listener;

import java.util.Date;

import javax.annotation.Resource;

import org.apache.commons.lang.time.DateFormatUtils;
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
@RabbitListener(queues = JobDirectRabbitConfig.SYNC_LOTTERY_OPENCODE_JOB)
public class SyncLotteryOpencodeListener {

	private Logger logger = LoggerFactory.getLogger(SyncLotteryOpencodeListener.class);
	
	@Resource
	private ILotteryDataService lotteryDataService;
	
	@Bean
    public Queue queueSyncLotteryOpencodeJob() {
        return new Queue(JobDirectRabbitConfig.SYNC_LOTTERY_OPENCODE_JOB);
    }

    @Bean
    Binding bindingDirectExchangeSyncLotteryOpencodeJob(Queue queueSyncLotteryOpencodeJob, DirectExchange directExchange) {
        return BindingBuilder.bind(queueSyncLotteryOpencodeJob).to(directExchange).with(JobDirectRabbitConfig.SYNC_LOTTERY_OPENCODE_JOB);
    }
	
	@RabbitHandler
    public void receive(Object data) {
		Message message = (Message) data;
		String kindOf = new String(message.getBody());
		logger.debug("kindOf={}",kindOf);
		try {
			lotteryDataService.syncOpenCodeForToday(kindOf, DateFormatUtils.format(new Date(), "yyyyMMdd"));
		}  catch (Exception e) {
			e.printStackTrace();
		}
    }
}
