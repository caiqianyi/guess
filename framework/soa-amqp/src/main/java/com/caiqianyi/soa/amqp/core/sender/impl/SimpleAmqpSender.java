package com.caiqianyi.soa.amqp.core.sender.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Component;

import com.caiqianyi.soa.amqp.core.AmqpExchange;
import com.caiqianyi.soa.amqp.core.sender.IRabbitmqSender;

@Component
public class SimpleAmqpSender implements IRabbitmqSender{
	
	private Logger logger = LoggerFactory.getLogger(SimpleAmqpSender.class);
	
	private AmqpTemplate amqpTemplate;
	
	@Resource	
	public void setAmqpTemplate(AmqpTemplate amqpTemplate) {
		this.amqpTemplate = amqpTemplate;
	}

	@Override
	public void sendContractDirect(String routingKey, Object message) {
		logger.debug("send queue:"+routingKey);
		amqpTemplate.convertAndSend(AmqpExchange.DIRECT.getValue(),routingKey, message);
	}
	
	@Override
	public void sendContractTopic(String queue,Object message) {
		amqpTemplate.convertAndSend(AmqpExchange.TOPIC.getValue(), queue , message);
	}

	@Override
	public void sendContractFanout(Object message) {
		amqpTemplate.convertAndSend(AmqpExchange.FANOUT.getValue(), "" , message);
	}

}
