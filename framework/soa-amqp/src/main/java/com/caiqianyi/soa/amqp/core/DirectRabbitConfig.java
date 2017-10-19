package com.caiqianyi.soa.amqp.core;

import org.springframework.context.annotation.Configuration;

@Configuration
public class DirectRabbitConfig {

    public final static String AlarmMailQueue = "alarm.mail.queue";
    
    public final static String BuyCardQueue = "buy.card.queue";
    
    public final static String BuyCardCashbackCompleteQueue = "cashback.complete.queue";
}
