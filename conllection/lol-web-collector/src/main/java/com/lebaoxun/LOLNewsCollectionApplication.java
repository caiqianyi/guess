package com.lebaoxun;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.client.RestTemplate;

import com.lebaoxun.soa.web.framework.datasource.DynamicDataSourceRegister;

//springboot启动时会自动注入数据源和配置jpa
//排除其注入
@Import({ DynamicDataSourceRegister.class })
@SpringBootApplication
@EnableDiscoveryClient
@EnableTransactionManagement
public class LOLNewsCollectionApplication{
	
	@Bean
	@LoadBalanced
	RestTemplate restTemplate() {
		return new RestTemplate();
	}
	
	public static void main(String[] args) {
        SpringApplication.run(LOLNewsCollectionApplication.class, args);
    }
	
} 