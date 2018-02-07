package com.lebaoxun.soa.core.redis;

import java.util.List;

public interface IRedisSorted{
	/**
	 * 增加一个或多个元素，如果该元素已经存在，更新它的socre值
	 * 虽然有序集合有序，但它也是集合，不能重复元素，添加重复元素只会 
	 * 更新原有元素的score值
	 * @param space
	 *            见枚举类RedisSpace定义
	 * @param key zAdd 特定key定义规则。详见返回值
	 * @param score 排序字段
	 * @param value 
	 * @return String null on failure，else create key
	 */
	public String zAdd(String key,double score,Object value); 
	
	/**
	 * 返回key对应的有序集合中score介于min和max之间的所有元素（包哈score等于min或者max的元素）。元素按照score从低到高的顺序排列。如果元素具有相同的score，那么会按照字典顺序排列。
	 * @param space
	 *            见枚举类RedisSpace定义
	 * @param key 
	 * @param min score最小值
	 * @param max score最大值
	 * @return List<Object> 包含在指定范围内的值，按照score从低到高的顺序排列
	 */
	public List<Object> zRangeByScore(String key,double min,double max);
	
	/**
	 * 从有序集合中删除指定的成员。非现有成员被忽略。当键存在，并且不持有有序集合，则会返回错误。
	 * @param space
	 *            见枚举类RedisSpace定义
	 * @param key
	 * @return 返回整型，成员来自有序集合(不包括非现有成员)被删除的数量。 1 on success, 0 on failure. 
	 */
	public Long zDel(String key,Object val);
	
	/**
	 * 返回key对应的有序集合中介于min和max间的元素的个数。
	 * @param space
	 *            见枚举类RedisSpace定义
	 * @param key
	 * @param min score最小值
	 * @param max score最大值
	 * @return 返回整数，有序集合score值在min和 max之间的成员的数量，或者如果键不存在则返回0。。
	 */
	public Long zCount(String key,double min,double max);
	
	/**
	 * 返回存储在key对应的有序集合中的元素的个数。
	 * @param space
	 *            见枚举类RedisSpace定义
	 * @param key
	 * @return 返回整数，有序集合的基数(元素的数量)，或者如果键不存在则返回0。
	 */
	public Long zSize(String key);
	
	/**
	 * 返回key对应的有序集合中member的score值
	 * @param space
	 *            见枚举类RedisSpace定义
	 * @param key
	 * @param value
	 * @return Double，如果key在有序集合中不存在，那么将会返回null。
	 */
	public Double zScore(String key,Object value);
}
