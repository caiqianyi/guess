package com.lebaoxun.pay.listener;
/*package com.caiqianyi.pay.listener;

import java.util.Date;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.caiqianyi.soa.amqp.core.DirectRabbitConfig;

@Component
@RabbitListener(queues = DirectRabbitConfig.BuyCardQueue)
public class BuyCardListener {
	
	private Logger logger = LoggerFactory.getLogger(BuyCardListener.class);
	
	@Resource
	private IAccountService accountService;
	
	@Bean
    public Queue queueBuyCard() {
        return new Queue(DirectRabbitConfig.BuyCardQueue);
    }

    @Bean
    Binding bindingDirectExchangeBuyCard(Queue queueBuyCard, DirectExchange directExchange) {
        return BindingBuilder.bind(queueBuyCard).to(directExchange).with(DirectRabbitConfig.BuyCardQueue);
    }
	
	@RabbitHandler
    public void receive(Object data) {
		Message message = (Message) data;
		String body = new String(message.getBody());
		logger.debug("body={}",body);
		try {
			String orderNo = body.replace("\\\"", "\"");
			orderNo = orderNo.substring(1, orderNo.length()-1);
			logger.debug("orderNo={}",orderNo);
			accountService.cardRechargeForAgent(orderNo, new Date().getTime());
		}  catch (Exception e) {
			e.printStackTrace();
		}
    }
}*/