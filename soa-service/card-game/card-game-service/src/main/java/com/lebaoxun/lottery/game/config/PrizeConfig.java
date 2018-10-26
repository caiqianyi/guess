package com.lebaoxun.lottery.game.config;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.core.RedisTemplate;

import com.lebaoxun.lottery.game.core.entity.Prize;

@Configuration
public class PrizeConfig {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Resource
	private Environment env;
	
	@Resource
	private RedisTemplate<String,Object> redisTemplate;
	
	private void addPrize(List<Prize> prizes){
		/**
		 * 
		 lottery:
		  prizes[0]:
		    prob: 0.0000002D
		    amount: 1
		    level: 1
		    totalAmount: 10
		    power: 5000000
		    lottery: F1
		 */
		Integer index = prizes.size();
		String strarF = String.format("lottery.prizes[%s]", index);
		logger.debug("strarF={}",strarF);
		if(env.containsProperty(strarF+".prob")){
			Double prob = Double.parseDouble(env.getProperty(strarF+".prob"));
			String amount = env.getProperty(strarF+".amount");
			Integer level = Integer.parseInt(env.getProperty(strarF+".level"));
			Long power = Long.parseLong(env.getProperty(strarF+".power"));
			String lottery = env.getProperty(strarF+".lottery");
			
			Prize prize = new Prize();
			prize.setProb(prob);
			
			if(StringUtils.isNotBlank(amount) 
					&& StringUtils.isNumeric(amount)){
				prize.setAmount(Integer.parseInt(amount));
			}
			
			prize.setLevel(level);
			prize.setPower(power);
			prize.setLottery(lottery);
			
			String totalAmount = env.getProperty(strarF+".totalAmount");
			if(StringUtils.isNotBlank(totalAmount) 
					&& StringUtils.isNumeric(totalAmount)){
				prize.setTotalAmount(Integer.parseInt(totalAmount));
			}
			prizes.add(prize);
			addPrize(prizes);
		}
	}
	
	@Bean
	List<Prize> getPrizes(){
		String key = "lottery:draw:prizes";
		List<Prize> prizes = (List<Prize>) redisTemplate.opsForValue().get(key);
		if(prizes == null || prizes.isEmpty()){
			prizes = new ArrayList<Prize>();
			addPrize(prizes);
			redisTemplate.opsForValue().set(key, prizes);
		}
		return prizes;
	}
	
	
}
