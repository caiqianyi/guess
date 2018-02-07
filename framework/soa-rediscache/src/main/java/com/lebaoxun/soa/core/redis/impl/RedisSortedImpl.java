package com.lebaoxun.soa.core.redis.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.lebaoxun.commons.utils.SerializeUtil;
import com.lebaoxun.soa.core.redis.IRedisSorted;

/**
 * IRedisSortedSet实现
 * 
 */
@Repository("redisSorted")
public class RedisSortedImpl implements IRedisSorted {
	
	
	@Resource
	private RedisTemplate<String,?> redisTemplate;
	
	@Override
	public String zAdd(final String newk, final double score, final Object value) {
		// TODO Auto-generated method stub
		boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {
			public Boolean doInRedis(RedisConnection con) throws DataAccessException {
				byte[] k = redisTemplate.getStringSerializer().serialize(newk);
				byte[] v = SerializeUtil.serialize(value);
				return con.zAdd(k, score, v);
			}
		});
		if(result)
			return newk;
		return null;
	}
	@Override
	public List<Object> zRangeByScore(final String newk,final double min, final double max) {
		// TODO Auto-generated method stub
		
		return redisTemplate.execute(new RedisCallback<List<Object>>() {
			public List<Object> doInRedis(RedisConnection con) throws DataAccessException {
				List<Object> list = new ArrayList<Object>();
				byte[] k = redisTemplate.getStringSerializer().serialize(newk);
				Set<byte[]> set = con.zRangeByScore(k, min, max);
				if(set != null && !set.isEmpty()){
					for(byte[] rval : set){
						list.add(SerializeUtil.unserialize(rval));
					}
				}
				return list;
			}
		});
	}
	
	@Override
	public Long zCount(final String newk, final double min, final double max) {
		
		return redisTemplate.execute(new RedisCallback<Long>() {
			public Long doInRedis(RedisConnection con) throws DataAccessException {
				byte[] k = redisTemplate.getStringSerializer().serialize(newk);
				return con.zCount(k, min, max);
			}
		});
	}
	
	@Override
	public Long zDel(final String newk,final Object val) {
		
		return redisTemplate.execute(new RedisCallback<Long>() {
			public Long doInRedis(RedisConnection con) throws DataAccessException {
				byte[] k = redisTemplate.getStringSerializer().serialize(newk);
				byte[] vbs = SerializeUtil.serialize(val);
				return con.zRem(k, vbs);
			}
		});
	}
	@Override
	public Long zSize(final String newk) {
		
		return redisTemplate.execute(new RedisCallback<Long>() {
			public Long doInRedis(RedisConnection con) throws DataAccessException {
				byte[] k = redisTemplate.getStringSerializer().serialize(newk);
				return con.zCard(k);//返回在指定的键存储在集合中的元素的数量。
			}
		});
	}
	
	@Override
	public Double zScore(final String newk, final Object value) {
		
		return redisTemplate.execute(new RedisCallback<Double>() {
			public Double doInRedis(RedisConnection con) throws DataAccessException {
				byte[] k = redisTemplate.getStringSerializer().serialize(newk);
				byte[] vbs = SerializeUtil.serialize(value);
				return con.zScore(k, vbs);//返回在指定的键存储在集合中的元素的数量。
			}
		});
	}
}
