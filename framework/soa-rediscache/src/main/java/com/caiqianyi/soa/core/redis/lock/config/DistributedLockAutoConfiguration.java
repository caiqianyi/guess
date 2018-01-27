package com.caiqianyi.soa.core.redis.lock.config;

import javax.annotation.Resource;

import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

import com.caiqianyi.soa.core.redis.lock.DistributedLock;
import com.caiqianyi.soa.core.redis.lock.impl.RedisDistributedLock;

/**
 * @author fuwei.deng
 * @date 2017年6月14日 下午3:11:31
 * @version 1.0.0
 */
@Configuration
@AutoConfigureAfter(RedisAutoConfiguration.class)
public class DistributedLockAutoConfiguration {
    
	@Resource
	private RedisTemplate<String,?> redisTemplate;
	
    @Bean
    public DistributedLock redisDistributedLock(){
        return new RedisDistributedLock(redisTemplate);
    }
    
}