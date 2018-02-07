package com.lebaoxun.webchat.server;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

import com.lebaoxun.soa.core.redis.IRedisHash;

@Configuration
public class WebSocketConfig {
    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }
    
    private String clubIds[] = new String[]{"10001","10002","10003"};
    @Resource
	private IRedisHash redisHash;
    
    @Bean
    String initClub(){
    	for(String clubId : clubIds){
    		String clubKey = "webchat:club:"+clubId;
    		Set<String> set = new HashSet<String>();
			redisHash.hSet(clubKey, "online", set);
    	}
    	return null;
    }
}