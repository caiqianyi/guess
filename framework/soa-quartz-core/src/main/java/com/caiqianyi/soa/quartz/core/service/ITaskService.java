package com.caiqianyi.soa.quartz.core.service;

import java.util.List;

import org.quartz.SchedulerException;

import com.caiqianyi.soa.quartz.core.JobGroup;
import com.caiqianyi.soa.quartz.core.entity.TaskInfo;

public interface ITaskService {
	
	/**
	 * 所有任务列表
	 * 2016年10月9日上午11:16:59
	 * @throws SchedulerException 
	 */
	public List<TaskInfo> list(String jobGroup) throws SchedulerException;
	
	
	/**
	 * 创建sec秒执行的定时任务，并保存 
	 * @param jobName 任务class name
	 * @param jobGroup 任务组 名称
	 * @param sec      
	 * @param info
	 */
	public void addJob(String jobName,JobGroup jobGroup,Integer sec,String info,String json);
	
	
	/**
	 * 创建sec秒执行的定时任务，并保存 
	 * @param jobName 任务class name
	 * @param jobGroup 任务组 名称
	 * @param sec      
	 * @param info
	 */
	public void addSystemJob(String jobName,Integer sec,String info,String json);
	
	public void addSystemJob(String jobName,String jobGroup,Integer sec,String info,String json);
	
	/**
	 * 保存定时任务
	 * @param info
	 * 2016年10月9日上午11:30:40
	 */
	public void addJob(TaskInfo info);
	
	/**
	 * 修改定时任务
	 * @param info
	 * 2016年10月9日下午2:20:07
	 */
	public void edit(TaskInfo info);
	
	/**
	 * 删除定时任务
	 * @param jobName
	 * @param jobGroup
	 * 2016年10月9日下午1:51:12
	 */
	public void delete(String jobName, String jobGroup);
	
	/**
	 * 暂停定时任务
	 * @param jobName
	 * @param jobGroup
	 * 2016年10月10日上午9:40:19
	 */
	public void pause(String jobName, String jobGroup);
	
	/**
	 * 重新开始任务
	 * @param jobName
	 * @param jobGroup
	 * 2016年10月10日上午9:40:58
	 */
	public void resume(String jobName, String jobGroup);
	
	/**
	 * 立即执行
	 * @param jobName
	 * @param jobGroup
	 */
	public void trigger(String jobName,String jobGroup);
	
	/**
	 * 验证是否存在
	 * @param jobName
	 * @param jobGroup
	 * @throws SchedulerException
	 * 2016年10月8日下午5:30:43
	 */
	boolean checkExists(String jobName, String jobGroup)throws SchedulerException; 
}
