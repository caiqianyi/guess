package com.lebaoxun.webchat.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;

/**
 * 房间广播消息
 * @author caiqianyi
 *
 */
@Component
@RabbitListener(queues = WebChatQueueConfig.CHAT_ROOM_BROADCAST)
public class ClubBroadcastListener {

	private Logger logger = LoggerFactory.getLogger(ClubBroadcastListener.class);
	
	@Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(ConnectionFactory connectionFactory) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setPrefetchCount(5);
        factory.setAcknowledgeMode(AcknowledgeMode.AUTO);
        return factory;
    }
	
	@Bean
    public Queue queueWebchatClubBroadcast() {
        return new Queue(WebChatQueueConfig.CHAT_ROOM_BROADCAST,true);
    }

    @Bean
    Binding bindingFanoutExchangeWebchatClubBroadcast(Queue queueWebchatClubBroadcast, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(queueWebchatClubBroadcast).to(fanoutExchange);
    }
	
	@RabbitHandler
    public void receive(Object data) {
		Message message = (Message) data;
		String text = new String(message.getBody());
		logger.debug("body={}",text);
		JSONObject mesg = JSONObject.parseObject(text);
		ClubWebChatBoat.broadcast(mesg.getString("clubId"), mesg.getString("to"), mesg.getString("message"), mesg.getString("handle"));
    }
}
