package com.lebaoxun;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.lebaoxun.soa.web.framework.datasource.DynamicDataSourceRegister;

@Import({DynamicDataSourceRegister.class})
@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
@EnableTransactionManagement
public class AccountApplication{
	
	 /*@Bean
     public ServletRegistrationBean dispatcherRegistration(DispatcherServlet dispatcherServlet) {
         ServletRegistrationBean registration = new ServletRegistrationBean(dispatcherServlet);
         registration.getUrlMappings().clear();
         registration.addUrlMappings("*.json");
         return registration;
     }*/
	
	 public static void main(String[] args) {
         SpringApplication.run(AccountApplication.class, args);
     }
	 
}
 