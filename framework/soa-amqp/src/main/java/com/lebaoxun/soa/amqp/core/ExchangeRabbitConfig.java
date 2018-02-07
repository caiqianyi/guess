package com.lebaoxun.soa.amqp.core;

import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ExchangeRabbitConfig {
	
    @Bean
    FanoutExchange fanoutExchange() {
        return new FanoutExchange(AmqpExchange.FANOUT.getValue());
    }
    
    @Bean
    TopicExchange topicExchange() {
        return new TopicExchange(AmqpExchange.TOPIC.getValue());
    }
    
    @Bean
    DirectExchange directExchange() {
        return new DirectExchange(AmqpExchange.DIRECT.getValue());
    }
}
