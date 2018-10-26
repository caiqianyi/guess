package com.lebaoxun.lottery.game.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.lang.time.DateFormatUtils;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.lebaoxun.commons.exception.I18nMessageException;
import com.lebaoxun.lottery.game.core.entity.DrawLotteryLog;
import com.lebaoxun.lottery.game.core.entity.Prize;
import com.lebaoxun.lottery.game.service.ILotteryService;
import com.lebaoxun.soa.amqp.core.sender.IRabbitmqSender;

@Service
public class LotteryServiceImpl implements ILotteryService {

	@Resource
	private RedisTemplate<String,Object> redisTemplate;
	
	@Resource(name = "redisTemplate")
	private HashOperations<String,String,Object> hashOps;
	
	@Resource(name = "redisTemplate")
	private ZSetOperations<String,Integer> zsetOps;
	
	@Resource
	private IRabbitmqSender rabbitmqSender;
	
	@Override
	public DrawLotteryLog gainToken(Integer userId) {
		// TODO Auto-generated method stub
		String expect = DateFormatUtils.format(new Date(), "yyyyMMddHH");
		String key = "lottery:token:"+userId;
		if(hashOps.hasKey(key, expect)){
			throw new I18nMessageException("10001");
		}
		DrawLotteryLog lotteryToken = new DrawLotteryLog();
		lotteryToken.setId(UUID.randomUUID().toString().replaceAll("-", ""));
		lotteryToken.setCreateTime(new Date());
		lotteryToken.setExpect(expect);
		lotteryToken.setStatus(0);
		lotteryToken.setType("GET");
		lotteryToken.setUserId(userId);
		
		hashOps.put(key, expect, JSONObject.toJSON(lotteryToken).toString());
		return lotteryToken;
	}
	
	@Override
	public List<DrawLotteryLog> buyGainToken(Integer userId, Integer count) {
		// TODO Auto-generated method stub
		String expect = DateFormatUtils.format(new Date(), "yyyyMMddHH");
		String key = "lottery:token:"+userId;
		List<DrawLotteryLog> list = new ArrayList<DrawLotteryLog>();
		for(int i=0;i<count;i++){
			DrawLotteryLog lotteryToken = new DrawLotteryLog();
			String id = UUID.randomUUID().toString().replaceAll("-", "");
			lotteryToken.setId(UUID.randomUUID().toString().replaceAll("-", ""));
			lotteryToken.setCreateTime(new Date());
			lotteryToken.setExpect(expect);
			lotteryToken.setStatus(0);
			lotteryToken.setType("BUY");
			lotteryToken.setUserId(userId);
			list.add(lotteryToken);
			hashOps.put(key, id, JSONObject.toJSON(lotteryToken).toString());
		}
		return list;
	}
	
	@Override
	public boolean isGain(Integer userId) {
		// TODO Auto-generated method stub
		String expect = DateFormatUtils.format(new Date(), "yyyyMMddHH");
		String key = "lottery:token:"+userId;
		return hashOps.hasKey(key, expect);
	}

	@Override
	public int findMyDrawCount(Integer userId) {
		// TODO Auto-generated method stub
		String key = "lottery:token:"+userId;
		Map<String, Object> map = hashOps.entries(key);
		if(map == null){
			return 0;
		}
		return map.size();
	}

	@Override
	public String runDrawLottery(Integer userId, Integer count) {
		// TODO Auto-generated method stub
		String key = "lottery:token:"+userId;
		Map<String, Object> map = hashOps.entries(key);
		if(map == null || map.size() < count){
			throw new I18nMessageException("10003");
		}
		String handle = UUID.randomUUID().toString().replaceAll("-", "");
		List<Object> list = new ArrayList<Object>();
		int i = 0;
		for(Object expect : map.keySet()){
			if(i < count){
				String json = (String)map.get(expect);
				DrawLotteryLog lotteryToken = JSONObject.parseObject(json, DrawLotteryLog.class);
				hashOps.delete(key, expect);
				list.add(lotteryToken);
				i++;
				continue;
			}
			break;
		}
		hashOps.put("lottery:draw:record:"+userId,handle, JSONObject.toJSON(list).toString());
		JSONObject json = new JSONObject();
		json.put("handle", handle);
		json.put("userId", userId);
		rabbitmqSender.sendContractDirect("lottery.draw.queue", json.toJSONString());
		return handle;
	}

	@Override
	public List<DrawLotteryLog> findLotteryByHandle(Integer userId, String handle) {
		// TODO Auto-generated method stub
		String json = (String) hashOps.get("lottery:draw:record:"+userId, handle);
		List<DrawLotteryLog> tokens = JSONObject.parseArray(json, DrawLotteryLog.class);
		return tokens;
	}
	
	@Override
	public TreeMap<String, Integer> findMyLottery(Integer userId) {
		// TODO Auto-generated method stub
		return (TreeMap<String, Integer>) redisTemplate.opsForValue().get("user:lotterys:"+userId);
	}

	@Override
	public List<Map<String,Object>> rankTops() {
		// TODO Auto-generated method stub
		String key = "lottery:users:power:rank";
		if(redisTemplate.hasKey(key)){
			Set<Integer> set = zsetOps.range(key, 0, 100);
			List<Integer> ranks = new ArrayList<Integer>();
			for(Integer id : set){
				ranks.add(id);
			}
			List<Map<String,Object>> tops = new ArrayList<Map<String,Object>>();
			for(int i=ranks.size()-1;i>-1;i--){
				Integer id = ranks.get(i);
				Long power = zsetOps.score(key, id).longValue();
				Map<String,Object> item = new LinkedHashMap<String,Object>();
				
				Integer cardCount = 0; 
				TreeMap<String, Integer> lotterys = findMyLottery(id);
				for(String lottery:lotterys.keySet()){
					cardCount += lotterys.get(lottery);
				}
				item.put("userId", id);
				item.put("power", power);
				item.put("rank", ranks.size()-i-1);
				item.put("cards", cardCount);
				tops.add(item);
			}
			return tops;
		}
		return null;
	}
	
	@Override
	public Map<String, Object> findMyRank(Integer userId) {
		// TODO Auto-generated method stub
		String key = "lottery:users:power:rank";
		if(redisTemplate.hasKey(key)){
			Long rank = zsetOps.rank(key, userId);
			Long power = zsetOps.score(key, userId).longValue();
			Map<String,Object> item = new LinkedHashMap<String,Object>();
			
			Integer cardCount = 0; 
			TreeMap<String, Integer> lotterys = findMyLottery(userId);
			for(String lottery:lotterys.keySet()){
				cardCount += lotterys.get(lottery);
			}
			item.put("power", power);
			item.put("rank", zsetOps.size(key)-rank-1);
			item.put("cards", cardCount);
			return item;
		}
		return null;
	}
	
	@Override
	public List<Prize> findAllPrize() {
		List<Prize> prizes = (List<Prize>) redisTemplate.opsForValue().get("lottery:draw:prizes");
		for(Prize pz : prizes){
			pz.setAmount(null);
		}
		return prizes;
	}

}
