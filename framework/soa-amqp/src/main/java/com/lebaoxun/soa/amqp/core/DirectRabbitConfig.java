package com.lebaoxun.soa.amqp.core;

import org.springframework.context.annotation.Configuration;

@Configuration
public class DirectRabbitConfig {

    public final static String AlarmMailQueue = "alarm.mail.queue";
    
    public final static String AlipayNotifyQueue = "alipay.notify.queue";
    
}
