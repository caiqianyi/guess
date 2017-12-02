package com.caiqianyi.soa.core.redis.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.caiqianyi.commons.utils.SerializeUtil;
import com.caiqianyi.soa.core.redis.IRedisHash;

@Repository("redisHash")
public class RedisHashImpl implements
		IRedisHash {
	
	@Resource
	private RedisTemplate<String,?> redisTemplate;

	@Override
	public Boolean hSet(final String newk, final String f, final Object v) {
		// TODO Auto-generated method stub
		return redisTemplate.execute(new RedisCallback<Boolean>() {
			public Boolean doInRedis(RedisConnection con) throws DataAccessException {
				byte[] key = redisTemplate.getStringSerializer().serialize(newk);
				byte[] value = SerializeUtil.serialize(v);
				byte[] field = redisTemplate.getStringSerializer().serialize(f);
				return con.hSet(key, field, value);
			}
		});
	}
	
	@Override
	public Boolean hSet(final String k,final String f,final Object v, final Integer expire) {
		// TODO Auto-generated method stub
		return redisTemplate.execute(new RedisCallback<Boolean>() {
			public Boolean doInRedis(RedisConnection con) throws DataAccessException {
				byte[] key = redisTemplate.getStringSerializer().serialize(k);
				byte[] value = SerializeUtil.serialize(v);
				byte[] field = redisTemplate.getStringSerializer().serialize(f);
				con.hSet(key, field, value);
				if (expire != null && expire > 0) {
					con.expire(key, expire);
				}
				return true;
			}
		});
	}

	@Override
	public Boolean hSetNx(final String newk, final String f, final Object v) {
		return redisTemplate.execute(new RedisCallback<Boolean>() {
			public Boolean doInRedis(RedisConnection con) throws DataAccessException {
				byte[] key = redisTemplate.getStringSerializer().serialize(newk);
				byte[] value = SerializeUtil.serialize(v);
				byte[] field = redisTemplate.getStringSerializer().serialize(f);
				return con.hSetNX(key, field, value);
			}
		});
	}

	@Override
	public Object hGet(final String newk, final String f) {
		return redisTemplate.execute(new RedisCallback<Object>() {
			public Object doInRedis(RedisConnection con) throws DataAccessException {
				byte[] key = redisTemplate.getStringSerializer().serialize(newk);
				byte[] field = redisTemplate.getStringSerializer().serialize(f);
				return SerializeUtil.unserialize(con.hGet(key, field));
			}
		});
	}

	@Override
	public Long hLen(final String newk) {
		return redisTemplate.execute(new RedisCallback<Long>() {
			public Long doInRedis(RedisConnection con) throws DataAccessException {
				byte[] key = redisTemplate.getStringSerializer().serialize(newk);
				return con.hLen(key);
			}
		});
	}

	@Override
	public Long hDel(final String newk) {
		return redisTemplate.execute(new RedisCallback<Long>() {
			public Long doInRedis(RedisConnection con) throws DataAccessException {
				byte[] k = redisTemplate.getStringSerializer().serialize(newk);
				return con.del(k);
			}
		});
	}
	
	@Override
	public Long hDel(final String newk,final String field) {
		return redisTemplate.execute(new RedisCallback<Long>() {
			public Long doInRedis(RedisConnection con) throws DataAccessException {
				byte[] k = redisTemplate.getStringSerializer().serialize(newk);
				byte[] fields = redisTemplate.getStringSerializer().serialize(field);
				return con.hDel(k,fields);
			}
		});
	}

	@Override
	public Map<String, Object> hGetAll(final String newk) {
		return redisTemplate.execute(new RedisCallback<Map<String, Object>>() {
			public Map<String, Object> doInRedis(RedisConnection con) throws DataAccessException {
				byte[] k = redisTemplate.getStringSerializer().serialize(newk);
				Map<String, Object> result = new HashMap<String,Object>();
				
				Map<byte[],byte[]> map = con.hGetAll(k);
				for(byte[] rk : map.keySet()){
					result.put(redisTemplate.getStringSerializer().deserialize(rk), SerializeUtil.unserialize(map.get(rk)));
				}
				return result;
			}
		});
	}

	@Override
	public Boolean hExists(final String newk, final String f) {
		return redisTemplate.execute(new RedisCallback<Boolean>() {
			public Boolean doInRedis(RedisConnection con) throws DataAccessException {
				byte[] key = redisTemplate.getStringSerializer().serialize(newk);
				byte[] field = redisTemplate.getStringSerializer().serialize(f);
				return con.hExists(key,field);
			}
		});
	}
	
	@Override
	public Boolean hExists(String key) {
		// TODO Auto-generated method stub
		return redisTemplate.hasKey(key);
	}

	@Override
	public boolean exists(String key) {
		return redisTemplate.hasKey(key);
	}
}
