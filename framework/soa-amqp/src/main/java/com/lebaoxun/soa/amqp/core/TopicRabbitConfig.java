package com.lebaoxun.soa.amqp.core;

import org.springframework.context.annotation.Configuration;

/**
 * fanout 队列配置
 * @author cqy
 *
 */
@Configuration
public class TopicRabbitConfig {

    public final static String Message = "topic.message";
    public final static String Messages = "topic.messages";
    
}
