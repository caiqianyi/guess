package com.caiqianyi.soa.quartz.core;

import java.io.IOException;
import java.util.Properties;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import com.alibaba.druid.filter.config.ConfigTools;

/**
 * 配置任务调度中心
 * [QRTZ_JOB_DETAILS], [QRTZ_TRIGGERS] and [QRTZ_CRON_TRIGGERS]
 * @author lance
 */
@Configuration
public class QuartzConfig {
	
	private Logger logger = LoggerFactory.getLogger(QuartzConfig.class);
	
	@Resource
	private Environment env;
	
	@Resource
	private JobFactory jobFactory;
	
	
	@Value(value="${quartz.job.enabled}")
	private Boolean autoStartup = true;
	
	@Bean
    public SchedulerFactoryBean schedulerFactoryBean() throws IOException {
        SchedulerFactoryBean factory = new SchedulerFactoryBean();

        factory.setOverwriteExistingJobs(true);

        // 延时启动
        //factory.setStartupDelay(20);

        // 加载quartz数据源配置
        factory.setQuartzProperties(quartzProperties());
        
        // 自定义Job Factory，用于Spring注入
        factory.setJobFactory(jobFactory);
        
        factory.setAutoStartup(autoStartup);
        return factory;
    }
	
	/*@Bean
	public Scheduler scheduler(SchedulerFactoryBean sfb) throws IOException, SchedulerException {
		sfb.setSchedulerName("QuartzScheduler");
		Scheduler scheduler = sfb.getScheduler();
		logger.debug("==================>>quartzJobEnabled:"+quartzJobEnabled);
		if(quartzJobEnabled){
			scheduler.start();
		}
		return scheduler;
	}*/
	
	/**
	 * 设置quartz属性
	 * @throws IOException
	 */
	public Properties quartzProperties() throws IOException {
		Properties prop = new Properties();
		prop.put("quartz.scheduler.instanceName", "ServerScheduler");
		prop.put("org.quartz.scheduler.instanceId", "AUTO");
		prop.put("org.quartz.scheduler.skipUpdateCheck", "true");
		prop.put("org.quartz.scheduler.instanceId", "NON_CLUSTERED");
		prop.put("org.quartz.scheduler.jobFactory.class", "org.quartz.simpl.SimpleJobFactory");
		prop.put("org.quartz.jobStore.class", "org.quartz.impl.jdbcjobstore.JobStoreTX");
		prop.put("org.quartz.jobStore.driverDelegateClass", "org.quartz.impl.jdbcjobstore.StdJDBCDelegate");
		prop.put("org.quartz.jobStore.dataSource", "quartzDataSource");
		prop.put("org.quartz.jobStore.tablePrefix", "t_qrtz_");
		prop.put("org.quartz.jobStore.isClustered", "true");
		prop.put("org.quartz.threadPool.class", "org.quartz.simpl.SimpleThreadPool");
        prop.put("org.quartz.threadPool.threadCount", "5");
        
        RelaxedPropertyResolver propertyResolver = new RelaxedPropertyResolver(env, "spring.datasource.");
        
		try {
			String username = ConfigTools.decrypt(propertyResolver.getProperty("username"));
			String password = ConfigTools.decrypt(propertyResolver.getProperty("password"));
			prop.put("org.quartz.dataSource.quartzDataSource.driver", propertyResolver.getProperty("driver-class-name"));
			prop.put("org.quartz.dataSource.quartzDataSource.URL", propertyResolver.getProperty("url"));
			
			
			logger.debug("quartz.dataSource ==>> url : {}",propertyResolver.getProperty("url"));
			logger.debug("quartz.dataSource ==>> username : {}",username);
			logger.debug("quartz.dataSource ==>> password : {}",password);
			prop.put("org.quartz.dataSource.quartzDataSource.user", username);
			prop.put("org.quartz.dataSource.quartzDataSource.password",  password);
			prop.put("org.quartz.dataSource.quartzDataSource.maxConnections", "10");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
		return prop;
	}
}