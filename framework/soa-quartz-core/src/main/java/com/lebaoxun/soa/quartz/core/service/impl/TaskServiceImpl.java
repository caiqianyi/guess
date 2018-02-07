package com.lebaoxun.soa.quartz.core.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.JobPersistenceException;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.quartz.impl.matchers.GroupMatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.lebaoxun.commons.exception.I18nMessageException;
import com.lebaoxun.soa.quartz.core.JobGroup;
import com.lebaoxun.soa.quartz.core.entity.TaskInfo;
import com.lebaoxun.soa.quartz.core.service.ITaskService;

@Service
public class TaskServiceImpl implements ITaskService{
	private Logger logger = LoggerFactory.getLogger(TaskServiceImpl.class);
	@Autowired
	private Scheduler scheduler;
	
	/**
	 * 所有任务列表
	 * 2016年10月9日上午11:16:59
	 * @throws SchedulerException 
	 */
	public List<TaskInfo> list(String groupJob) throws SchedulerException{
		List<TaskInfo> list = new ArrayList<TaskInfo>();
		List<String> gjs = new ArrayList<String>();
		if(StringUtils.isEmpty(groupJob)){
			gjs.addAll(scheduler.getJobGroupNames());
		}else{
			JobGroup jg = JobGroup.valueByName(groupJob);
			if(jg == null){
				return null;
			}
			gjs.add(jg.getName());
		}
		
		for(String gj : gjs){
			for(JobKey jobKey: scheduler.getJobKeys(GroupMatcher.<JobKey>groupEquals(gj))){
				List<? extends Trigger> triggers = scheduler.getTriggersOfJob(jobKey);
				for (Trigger trigger: triggers) {
					Trigger.TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());
					JobDetail jobDetail = null;
					try{
						jobDetail = scheduler.getJobDetail(jobKey);
					}catch(JobPersistenceException e){
						e.printStackTrace();
						continue;
					}
					
					String cronExpression = "", createTime = "";
					
					if (trigger instanceof CronTrigger) {
						CronTrigger cronTrigger = (CronTrigger) trigger;
						cronExpression = cronTrigger.getCronExpression();
						createTime = cronTrigger.getDescription();
					}
					TaskInfo info = new TaskInfo();
					info.setJobName(jobKey.getName());
					info.setJobGroup(jobKey.getGroup());
					info.setJobDescription(jobDetail.getDescription());
					info.setJobStatus(triggerState.name());
					info.setCronExpression(cronExpression);
					info.setCreateTime(createTime);
					info.setDataJson(jobDetail.getJobDataMap().getString("dataJson"));
					list.add(info);
				}					
			}
		}
		return list;
	}
	
	/**
	 * 保存定时任务
	 * @param info
	 * 2016年10月9日上午11:30:40
	 */
	public void addJob(TaskInfo info) {
		String jobName = info.getJobName(), 
			   jobGroup = info.getJobGroup(), 
			   cronExpression = info.getCronExpression(),
			   jobDescription = info.getJobDescription(),
			   dataJson = info.getDataJson();
		add(jobName, jobGroup, cronExpression, jobDescription,dataJson);
	}
	
	/**
	 * 修改定时任务
	 * @param info
	 * 2016年10月9日下午2:20:07
	 */
	public void edit(TaskInfo info) {
		String jobName = info.getJobName(), 
			   jobGroup = info.getJobGroup(), 
			   cronExpression = info.getCronExpression(),
			   jobDescription = info.getJobDescription(),
			   createTime = DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"),
					   dataJson = info.getDataJson();
		try {
			logger.debug("jobName={},jobGroup={}",jobName,jobGroup);
			if (!checkExists(jobName, jobGroup)) {
				throw new I18nMessageException("500",String.format("Job不存在, jobName:{%s},jobGroup:{%s}", jobName, jobGroup));
			}
			TriggerKey triggerKey = TriggerKey.triggerKey(jobName, jobGroup);
	        JobKey jobKey = new JobKey(jobName, jobGroup);
	        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression).withMisfireHandlingInstructionDoNothing();
	        CronTrigger cronTrigger = TriggerBuilder.newTrigger().withIdentity(triggerKey).withDescription(createTime).withSchedule(cronScheduleBuilder).build();
	        
	        JobDetail jobDetail = scheduler.getJobDetail(jobKey);
	        if(StringUtils.isNotBlank(dataJson)){
	        	jobDetail.getJobDataMap().put("dataJson", dataJson);
	        }
	        logger.debug("descr={}",jobDescription);
	        jobDetail = jobDetail.getJobBuilder().withDescription(jobDescription).build();
	        HashSet<Trigger> triggerSet = new HashSet<Trigger>();
	    	triggerSet.add(cronTrigger);
	        
	    	scheduler.scheduleJob(jobDetail, triggerSet, true);
		} catch (SchedulerException e) {
			throw new I18nMessageException(e);
		}
	}
	
	/**
	 * 删除定时任务
	 * @param jobName
	 * @param jobGroup
	 * 2016年10月9日下午1:51:12
	 */
	public void delete(String jobName, String jobGroup){
		/*if(StringUtils.isNotBlank(jobGroup)){
			JobGroup jg = JobGroup.valueByName(jobGroup);
			if(jg != null){
				jobGroup = jg.getName();
			}
		}*/
		TriggerKey triggerKey = TriggerKey.triggerKey(jobName, jobGroup);
        try {
			if (checkExists(jobName, jobGroup)) {
				scheduler.pauseTrigger(triggerKey);
			    scheduler.unscheduleJob(triggerKey);
			}
		} catch (SchedulerException e) {
			throw new I18nMessageException(e);
		}
	}
	
	/**
	 * 暂停定时任务
	 * @param jobName
	 * @param jobGroup
	 * 2016年10月10日上午9:40:19
	 */
	public void pause(String jobName, String jobGroup){
		TriggerKey triggerKey = TriggerKey.triggerKey(jobName, jobGroup);
		try {
			if (checkExists(jobName, jobGroup)) {
				scheduler.pauseTrigger(triggerKey);
			}
		} catch (SchedulerException e) {
			throw new I18nMessageException(e);
		}
	}
	
	/**
	 * 重新开始任务
	 * @param jobName
	 * @param jobGroup
	 * 2016年10月10日上午9:40:58
	 */
	public void resume(String jobName, String jobGroup){
		TriggerKey triggerKey = TriggerKey.triggerKey(jobName, jobGroup);
        
        try {
			if (checkExists(jobName, jobGroup)) {
				scheduler.resumeTrigger(triggerKey);
			}
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void trigger(String jobName, String jobGroup) {
		// TODO Auto-generated method stub
		JobKey jobKey = JobKey.jobKey(jobName, jobGroup);
        try {
			if (checkExists(jobName, jobGroup)) {
				scheduler.triggerJob(jobKey);
			}
		} catch (SchedulerException e) {
			e.printStackTrace();
		}	
	}
	
	/**
	 * 验证是否存在
	 * @param jobName
	 * @param jobGroup
	 * @throws SchedulerException
	 * 2016年10月8日下午5:30:43
	 */
	public boolean checkExists(String jobName, String jobGroup) throws SchedulerException{
		TriggerKey triggerKey = TriggerKey.triggerKey(jobName, jobGroup);
		return scheduler.checkExists(triggerKey);
	}

	@Override
	public void addJob(String jobName, JobGroup jobGroup, Integer sec, String info,String json) {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.SECOND, c.get(Calendar.SECOND) + 60);
		String exp = c.get(Calendar.SECOND)+" "+c.get(Calendar.MINUTE)+" "+c.get(Calendar.HOUR_OF_DAY)+" "+c.get(Calendar.DAY_OF_MONTH) +" "+ (c.get(Calendar.MONTH)+1) +" ? "+c.get(Calendar.YEAR);
		add(jobName,jobGroup.getName() , exp, info,json);
	}

	@Override
	public void addSystemJob(String jobName, Integer sec, String info,String json) {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.SECOND, c.get(Calendar.SECOND) + sec);
		String exp = c.get(Calendar.SECOND)+" "+c.get(Calendar.MINUTE)+" "+c.get(Calendar.HOUR_OF_DAY)+" "+c.get(Calendar.DAY_OF_MONTH) +" "+ (c.get(Calendar.MONTH)+1) +" ? "+c.get(Calendar.YEAR);
		add(jobName, JobGroup.SYSTEM.getName(), exp, info, json);
	}
	
	@Override
	public void addSystemJob(String jobName,String jobGroup, Integer sec, String info,String json) {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.SECOND, c.get(Calendar.SECOND) + sec);
		String exp = c.get(Calendar.SECOND)+" "+c.get(Calendar.MINUTE)+" "+c.get(Calendar.HOUR_OF_DAY)+" "+c.get(Calendar.DAY_OF_MONTH) +" "+ (c.get(Calendar.MONTH)+1) +" ? "+c.get(Calendar.YEAR);
		add(jobName, JobGroup.SYSTEM.getName()+"."+jobGroup, exp, info, json);
	}
	
	private void add(String jobName,String jobGroup,
			String cronExpression,String jobDescription,String json){
		String createTime = DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss");
		try {
			if (checkExists(jobName, jobGroup)) {
		        logger.info("===> AddJob fail, job already exist, jobGroup:{}, jobName:{}", jobGroup, jobName);
		        throw new I18nMessageException("500",String.format("Job已经存在, jobName:{%s},jobGroup:{%s}", jobName, jobGroup));
		    }
			
			TriggerKey triggerKey = TriggerKey.triggerKey(jobName, jobGroup);
			JobKey jobKey = JobKey.jobKey(jobName, jobGroup);
			
			CronScheduleBuilder schedBuilder = CronScheduleBuilder.cronSchedule(cronExpression).withMisfireHandlingInstructionDoNothing();
			CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(triggerKey).withDescription(createTime).withSchedule(schedBuilder).build();
		
		
			Class<? extends Job> clazz = (Class<? extends Job>)Class.forName(jobName);
			JobDetail jobDetail = JobBuilder.newJob(clazz).withIdentity(jobKey).withDescription(jobDescription).build();
			jobDetail.getJobDataMap().put("dataJson", json);
			scheduler.scheduleJob(jobDetail, trigger);
		} catch (ClassNotFoundException e) {
			throw new I18nMessageException(e);
		} catch (SchedulerException e) {
			e.printStackTrace();
			throw new I18nMessageException(e);
		}
	}
	public static void main(String[] args) {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.SECOND, c.get(Calendar.SECOND) + 60);
		String exp = c.get(Calendar.SECOND)+" "+c.get(Calendar.MINUTE)+" "+c.get(Calendar.HOUR_OF_DAY)+" "+c.get(Calendar.DAY_OF_MONTH) +" "+ (c.get(Calendar.MONTH)+1) +" ? "+c.get(Calendar.YEAR);
		System.out.println(exp);
		System.out.println(UUID.randomUUID().toString());
	}
}
