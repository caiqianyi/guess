package com.lebaoxun.guess.job.jclq.listener;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang.time.DateUtils;
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

import com.lebaoxun.guess.caipiao.core.dao.IJCLQMatchMapper;
import com.lebaoxun.guess.caipiao.service.ILotteryDataSyncService;
import com.lebaoxun.guess.jclq.entity.JCLQMatch;
import com.lebaoxun.guess.job.config.JobDirectRabbitConfig;

/**
 * 同步LOL比赛
 * @author caiqianyi
 *
 */
@Component
@RabbitListener(queues = JobDirectRabbitConfig.JCLQ_MATCH_LIVE_JOB)
public class JCLQLiveListener {

	private Logger logger = LoggerFactory.getLogger(JCLQLiveListener.class);
	
	@Resource
	private ILotteryDataSyncService lotteryDataSyncService;
	
	@Resource
	private IJCLQMatchMapper jCLQMatchMapper;
	
	@Bean 
    public Queue queueJclqMatchLiveJob() {
        return new Queue(JobDirectRabbitConfig.JCLQ_MATCH_LIVE_JOB);
    }

    @Bean
    Binding bindingDirectExchangeJclqMatchLiveJob(Queue queueJclqMatchLiveJob, DirectExchange directExchange) {
        return BindingBuilder.bind(queueJclqMatchLiveJob).to(directExchange).with(JobDirectRabbitConfig.JCLQ_MATCH_LIVE_JOB);
    }
	/**
	 * JCQL.MATCH.LIVE 0/10 * * * * ? {"queue":"jclq.match.live.job","message":"sync"} 竞彩篮球比分直播
	 * @param data
	 */
	@RabbitHandler
    public void receive(Object data) {
		try {
			Date now = new Date();
			String today = DateFormatUtils.format(now, "yyyyMMdd"),
					yesday = DateFormatUtils.format(DateUtils.addDays(now, -1), "yyyyMMdd");
			List<JCLQMatch> matchs = jCLQMatchMapper.findAllMatchByDay(null, today , null);
			boolean isLive = false;
			if(matchs != null && !matchs.isEmpty()){
				for(JCLQMatch match : matchs){
					if(match.getMatchTime().before(now) && !match.getStatus().equals("2")){
						isLive = true;
						break;
					}
				}
			}
			if(isLive){
				lotteryDataSyncService.syncJCLQMatch(yesday, today);
			}
		}  catch (Exception e) {
			e.printStackTrace();
		}
    }
}
