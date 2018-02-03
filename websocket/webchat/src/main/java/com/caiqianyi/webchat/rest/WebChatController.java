package com.caiqianyi.webchat.rest;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@Component
@RestController
public class WebChatController {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private DiscoveryClient discoveryClient;
	
	@Value("${spring.application.name}")
	private String application;

	@RequestMapping(method=RequestMethod.GET,value="/webchat/discovery")
	public List<String> serviceUrl() {
		logger.debug("application={}",application);
		List<String> url = new ArrayList<String>();
		List<ServiceInstance> serviceInstances = discoveryClient.getInstances(application);
		for(ServiceInstance si : serviceInstances){
			String ws = "ws://"+si.getHost()+":"+si.getPort()+"/server/club/";
			logger.debug("ws={}",ws);
			url.add(ws);
		}
	    return url;
	}
}
