package com.lebaoxun.job.manager.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.quartz.Job;
import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.lebaoxun.commons.exception.SuccessMessage;
import com.lebaoxun.soa.quartz.core.JobGroup;
import com.lebaoxun.soa.quartz.core.entity.TaskInfo;
import com.lebaoxun.soa.quartz.core.service.ITaskService;

/**
 * 任务管理
 */
@Controller
public class TaskManageController {
	private Logger logger = LoggerFactory.getLogger(TaskManageController.class);
	
	@Resource
	private ITaskService taskService;
	
	@Resource
	private Map<String,Job> jobs;

	/**
	 * 管理页
	 */
	@RequestMapping(value={"", "/", "index"})
	public ModelAndView info(String jobGroup,Map<String, Object> model){
		if(StringUtils.isNotBlank(jobGroup))
			model.put("jobGroup", JobGroup.valueOf(jobGroup));
		List<String> jobClassNames = new ArrayList<String>();
		for(Job job : jobs.values()){
			jobClassNames.add(job.getClass().getName());
		}
		model.put("jobGroups", JobGroup.values());
		model.put("jobClassNames",jobClassNames);
		logger.debug("model={}",new Gson().toJson(model));
		return new ModelAndView("index");
	}
	
	/**
	 * 任务列表
	 * @return
	 * @throws SchedulerException 
	 */
	@ResponseBody
	@RequestMapping(value="list", method=RequestMethod.POST)
	public String list(String jobGroup) throws SchedulerException{
		Map<String, Object> map = new HashMap<String, Object>();
		List<TaskInfo> infos = taskService.list(jobGroup);
		map.put("rows", infos);
		map.put("total", infos.size());
		return JSON.toJSONString(map);
	}
	 
	/**
	 * 保存定时任务
	 * @param info
	 */
	@ResponseBody
	@RequestMapping(value="save", method=RequestMethod.POST, produces = "application/json; charset=UTF-8")
	public SuccessMessage save(TaskInfo info){
		if(info.getId() == 0)
			taskService.addJob(info);
		else
			taskService.edit(info);
		return new SuccessMessage("ok");
	}
	
	/**
	 * 删除定时任务
	 * @param jobName
	 * @param jobGroup
	 */
	@ResponseBody
	@RequestMapping(value="delete/{jobName}/{jobGroup}", produces = "application/json; charset=UTF-8")
	public SuccessMessage delete(@PathVariable String jobName, @PathVariable String jobGroup){
		logger.debug("====>>TaskManageController delete jobGroup:{}",jobGroup);
		taskService.delete(jobName, "undefined".equals(jobGroup) ? "" : jobGroup);
		return new SuccessMessage("ok");
	}
	
	/**
	 * 暂停定时任务
	 * @param jobName
	 * @param jobGroup
	 */
	@ResponseBody
	@RequestMapping(value="pause/{jobName}/{jobGroup}", produces = "application/json; charset=UTF-8")
	public SuccessMessage pause(@PathVariable String jobName, @PathVariable String jobGroup){
		taskService.pause(jobName, jobGroup);
		return new SuccessMessage("ok");
	}
	
	/**
	 * 立即开始
	 * @param jobName
	 * @param jobGroup
	 */
	@ResponseBody
	@RequestMapping(value="trigger/{jobName}/{jobGroup}", produces = "application/json; charset=UTF-8")
	public SuccessMessage trigger(@PathVariable String jobName, @PathVariable String jobGroup){
		taskService.trigger(jobName, jobGroup);
		return new SuccessMessage("ok");
	}
	
	/**
	 * 重新开始定时任务
	 * @param jobName
	 * @param jobGroup
	 */
	@ResponseBody
	@RequestMapping(value="resume/{jobName}/{jobGroup}", produces = "application/json; charset=UTF-8")
	public SuccessMessage resume(@PathVariable String jobName, @PathVariable String jobGroup){
		taskService.resume(jobName, jobGroup);
		return new SuccessMessage("ok");
	}
}
