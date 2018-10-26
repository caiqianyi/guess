package com.lebaoxun.lottery.game.listener;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

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
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lebaoxun.lottery.game.core.entity.DrawLotteryLog;
import com.lebaoxun.lottery.game.core.entity.Prize;
import com.lebaoxun.lottery.game.utils.DrawLotteryUtil;

/**
 * 执行抽奖消费
 * @author caiqianyi
 *
 */
@Component
@RabbitListener(queues = "lottery.draw.queue")
public class DrawLotteryListener {

	private Logger logger = LoggerFactory.getLogger(DrawLotteryListener.class);
	
	@Resource
	private RedisTemplate<String,Object> redisTemplate;
	
	@Resource(name = "redisTemplate")
	private HashOperations<String,String,Object> hashOps;
	
	@Resource(name = "redisTemplate")
	private ZSetOperations<String,Integer> zsetOps;
	
	@Bean
    public Queue queueDrawLottery() {
        return new Queue("lottery.draw.queue",true);
    }

    @Bean
    Binding bindingDrawLottery(Queue queueDrawLottery, DirectExchange directExchange) {
        return BindingBuilder.bind(queueDrawLottery).to(directExchange).with("lottery.draw.queue");
    }
    
	@RabbitHandler
    public void receive(Object data) {
		Message message = (Message) data;
		String text = new String(message.getBody());
		logger.debug("body={}",text);
		JSONObject mesg = JSONObject.parseObject(text);
		
		String handle = mesg.getString("handle");
		Integer userId = mesg.getInteger("userId");
		
		String key = "lottery:draw:record:"+userId;
		if(hashOps.hasKey(key, handle)){
			List<DrawLotteryLog> list = JSONArray.parseArray((String)hashOps.get(key, handle), DrawLotteryLog.class);
			List<Prize> prizes = (List<Prize>) redisTemplate.opsForValue().get("lottery:draw:prizes");
			TreeMap<String,Integer> map = (TreeMap<String, Integer>) redisTemplate.opsForValue().get("user:lotterys:"+userId);
			if(map == null){
				map = new TreeMap<String,Integer>();
			}
			for(int i=0;i<list.size();i++){
				DrawLotteryLog token = list.get(i);
        		int index = DrawLotteryUtil.draw(prizes);
        		Prize pz = prizes.get(index);
        		token.setLottery(pz.getLottery());
        		token.setStatus(1);
        		Integer count = (Integer) map.get(pz.getLottery());
        		if(count == null){
        			count = 0;
        		}
        		map.put(pz.getLottery(), ++count);
        		
        		if(pz.getAmount() != null && pz.getAmount() > 0){
        			pz.setAmount(pz.getAmount()-1);
        		}
        	}
			Map<String,Prize> prizesMap = new HashMap<String,Prize>();
			for(Prize pz : prizes){
				prizesMap.put(pz.getLottery(), pz);
			}
			Long score = 0l;
			for(String lottery : map.keySet()){
				score += prizesMap.get(lottery).getPower() * map.get(lottery);
			}
			zsetOps.add("lottery:users:power:rank", userId, score);
			redisTemplate.opsForValue().set("user:lotterys:"+userId, map);
			hashOps.put(key, handle, JSONObject.toJSON(list).toString());
			redisTemplate.opsForValue().set("lottery:draw:prizes", prizes);
		}
    }
}
