package com.lebaoxun;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import com.netflix.discovery.DiscoveryManager;

@SpringBootApplication(exclude={DataSourceAutoConfiguration.class,HibernateJpaAutoConfiguration.class})
@EnableDiscoveryClient
@EnableEurekaClient
public class WebChatApplication {
	
	private static Logger logger = LoggerFactory.getLogger(WebChatApplication.class);
	/*
	 * @Bean public ServletRegistrationBean
	 * dispatcherRegistration(DispatcherServlet dispatcherServlet) {
	 * ServletRegistrationBean registration = new
	 * ServletRegistrationBean(dispatcherServlet);
	 * registration.getUrlMappings().clear();
	 * registration.addUrlMappings("*.json"); return registration; }
	 */

	public static void main(String[] args) {
		SpringApplication.run(WebChatApplication.class, args);
		
		Runtime.getRuntime().addShutdownHook(new Thread(){
            @Override
            public void run() {
            	logger.info("Shutting down product service, unregister from Eureka!");
                DiscoveryManager.getInstance().shutdownComponent();
            }
        });
	}

}
