package com.lebaoxun.job.core;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.lebaoxun.soa.amqp.core.sender.IRabbitmqSender;

@Component
public class TemplateJob implements Job{
	
	private Logger logger = LoggerFactory.getLogger(TemplateJob.class);

	@Resource
	private IRabbitmqSender rabbitmqSender;
	
	@Override
	public void execute(JobExecutionContext context)
			throws JobExecutionException {
		JobDataMap jobMap =  context.getJobDetail().getJobDataMap();
		if(jobMap != null){
			String dataJson = jobMap.getString("dataJson");
			JSONObject datas = JSONObject.parseObject(dataJson);
			String queue = datas.getString("queue");
			String message = datas.getString("message");
			if(StringUtils.isNotBlank(queue) &&
					StringUtils.isNotBlank(message)){
				logger.debug("queue={},message={}",queue,message);
				rabbitmqSender.sendContractDirect(queue, message);
			}
		}
		
	}

}
