package com.caiqianyi.soa.core.redis;

import java.util.Set;

/**
 * redis缓存统一接口
 * 
 * @author liuqs
 *
 */
public interface IRedisCache {

	/**
	 * 设置指定值到指定的命名空间
	 * 
	 * @param space
	 *            见枚举类RedisSpace定义
	 * @param key
	 *            key值
	 * @param obj
	 *            value值
	 * @return
	 */
	public boolean set(String key, Object obj);

	/**
	 * 
	 * @param space
	 *            见枚举类RedisSpace定义
	 * @param key
	 *            key值
	 * @param obj
	 *            value值
	 * @param expire
	 *            超时时间，单位 秒
	 * @return
	 */
	public boolean set(String key, Object obj, Long expire);


	/**
	 * 删除指定命名空间下的key
	 * 
	 * @param space
	 *            见枚举类RedisSpace定义
	 * @param keys
	 *            key值
	 */
	public void del(String... keys);

	/**
	 * 清空操作
	 * 
	 * @param space
	 *            见枚举类RedisSpace定义
	 * @param key
	 *            通配 mobile_ 删除mobile_*所有的key；null值是删除 RedisSpace *
	 */
	public void clear(String key);

	/**
	 * 判断key是否存在
	 * 
	 * @param space
	 *            见枚举类RedisSpace定义
	 * @param key
	 *            key值
	 * @return
	 */
	public boolean exists(String key);
	
	public boolean has(String key);

	/**
	 * 清空操作
	 * 
	 * @param space
	 *            见枚举类RedisSpace定义
	 * @param key
	 *            通配 mobile_ 删除mobile_*所有的key；null值是删除 RedisSpace *
	 */
	public int size(String key);

	/**
	 * 更新
	 * 
	 * @param space
	 *            见枚举类RedisSpace定义
	 * @param key
	 *            key值
	 * @param obj
	 *            新值
	 * @return
	 */
	public boolean update(String key, Object obj);

	/**
	 * 更新
	 * 
	 * @param space
	 *            见枚举类RedisSpace定义
	 * @param key
	 *            key值
	 * @param obj
	 *            值
	 * @param expire
	 *            超时时间，单位 秒
	 * @return
	 */
	public boolean update(String key, Object obj, Long expire);

	/**
	 * 获取key对应的值
	 * @param key
	 * @return
	 */
	public Object get(String key);
	
	public Object getSys(String key);

	/**
	 * 设置超时时间
	 * 
	 * @param space
	 *            见枚举类RedisSpace定义
	 * @param key
	 *            key值
	 * @return
	 */
	public Long ttl(String key);
	
	/**
	 * 累加
	 * @param key
	 */
	public void incByKey(String key);

	/**
	 * 获取指定key的集合
	 * 
	 * @param space
	 *            见枚举类RedisSpace定义
	 * @param key
	 * @return
	 */
	public Set<String> searchKey(String key);

}
