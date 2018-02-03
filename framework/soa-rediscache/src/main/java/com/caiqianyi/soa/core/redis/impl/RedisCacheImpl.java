package com.caiqianyi.soa.core.redis.impl;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.caiqianyi.commons.utils.SerializeUtil;
import com.caiqianyi.soa.core.redis.IRedisCache;

/**
 * IRedisCache瀹炵幇
 * 
 */
@Repository("redisCache")
public class RedisCacheImpl implements IRedisCache {

	@Resource
	private RedisTemplate<String,Object> redisTemplate;

	@Override
	public boolean set(String key, final Object obj) {
		return set(key, obj, null);
	}

	@Override
	public boolean setSys(String key, Object obj) {
		// TODO Auto-generated method stub
		redisTemplate.opsForValue().set(key, obj);
		return true;
	}

	@Override
	public boolean set(final String ks, final Object obj,
			final Long expire) {

		return redisTemplate.execute(new RedisCallback<Boolean>() {
			public Boolean doInRedis(RedisConnection con)
					throws DataAccessException {
				byte[] k = redisTemplate.getStringSerializer().serialize(ks);
				byte[] v = SerializeUtil.serialize(obj);

				con.set(k, v);

				if (expire != null && expire > 0) {
					con.expire(k, expire);
				}

				return true;
			}
		}, true);
	}

	@Override
	public boolean exists(String key) {
		return redisTemplate.hasKey(key);
	}
	
	@Override
	public boolean has(final String key) {
		// TODO Auto-generated method stub
		return redisTemplate.execute(new RedisCallback<Boolean>() {
			public Boolean doInRedis(RedisConnection con)
					throws DataAccessException {
				byte[] k = redisTemplate.getStringSerializer().serialize(key);
				return con.exists(k);
			}
		});
	}

	@Override
	public void del(final String... keys) {
		if (keys != null && keys.length > 0) {
			if (keys.length == 1)
				redisTemplate.execute(new RedisCallback<Object>() {
					public Object doInRedis(RedisConnection con)
							throws DataAccessException {
						Set<byte[]> sks  = con.keys(redisTemplate.getStringSerializer().serialize(
								keys[0]));
						for(byte[] sk : sks){
							con.del(sk);
						}
						return null;
					}
				});
			else
				for (String key : keys) {
					del(key);
				}
		}
	}

	@Override
	public boolean update(String key, Object obj) {
		return update(key, obj, null);
	}

	@Override
	public boolean update(final String ks, final Object obj,
			final Long expire) {
		return redisTemplate.execute(new RedisCallback<Boolean>() {
			public Boolean doInRedis(RedisConnection con)
					throws DataAccessException {
				byte[] k = redisTemplate.getStringSerializer().serialize(ks);
				long t = expire != null && expire > 0 ? expire : con.ttl(k);

				if (obj != null) {
					byte[] v = SerializeUtil.serialize(obj);
					con.set(k, v);
				}

				if (t > 0) {
					con.expire(k, t);
				}

				return true;
			}
		}, true);
	}

	@Override
	public Object get(final String key) {
		return redisTemplate.execute(new RedisCallback<Object>() {
			public Object doInRedis(RedisConnection con)
					throws DataAccessException {
				byte[] k = redisTemplate.getStringSerializer().serialize(key);
				if (con.exists(k)) {
					byte[] bytes = con.get(k);
					return SerializeUtil.unserialize(bytes);
				}
				return null;
			}
		});
	}
	
	@Override
	public Object getSys(String key) {
		return redisTemplate.execute(new RedisCallback<Object>() {
			public Object doInRedis(RedisConnection con)
					throws DataAccessException {
				byte[] k = redisTemplate.getStringSerializer().serialize(key);
				if (con.exists(k)) {
					byte[] bytes = con.get(k);
					return redisTemplate.getValueSerializer().deserialize(bytes);
				}
				return null;
			}
		});
	}

	@Override
	public Long ttl(final String ks) {
		return redisTemplate.execute(new RedisCallback<Long>() {
			public Long doInRedis(RedisConnection con)
					throws DataAccessException {
				byte[] k = redisTemplate.getStringSerializer().serialize(ks);
				return con.ttl(k);
			}
		}, true);
	}

	@Override
	public Set<String> searchKey(final String ks) {
		final Set<String> result = new HashSet<String>();

		redisTemplate.execute(new RedisCallback<Object>() {
			public Object doInRedis(RedisConnection con)
					throws DataAccessException {
				Set<byte[]> sets = con.keys(ks.getBytes());
				for (byte[] k : sets) {
					result.add(new String(k));
				}
				return null;
			}
		});

		return result;
	}

	@Override
	public void clear(final String key) {
		Set<String> keys = searchKey(key);

		if (keys != null) {
			for (final String k : keys) {
				redisTemplate.execute(new RedisCallback<Object>() {
					public Object doInRedis(RedisConnection con)
							throws DataAccessException {
						con.del(redisTemplate.getStringSerializer()
								.serialize(k));
						return null;
					}
				});
			}
		}
	}

	@Override
	public int size(String key) {
		Set<String> keys = searchKey(key);

		if (keys != null) {
			return keys.size();
		}

		return 0;
	}

	/**
	 * 累加
	 * @param key
	 */
	@Override
	public void incByKey(final String key){
		redisTemplate.execute(new RedisCallback<Object>() {
			public Object doInRedis(RedisConnection con)
					throws DataAccessException {
				byte[] k = redisTemplate.getStringSerializer().serialize(key);
				if (con.exists(k)) {
					con.incrBy(k, 1);
				}
				return null;
			}
		});
	}
}
