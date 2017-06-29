package com.wishbuild.saas.test.cache.service;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.data.redis.core.ZSetOperations.TypedTuple;

/**
 * 
 * @author xujun
 * @desc redis service
 */
public interface RedisService {
	/* ----------- common --------- */
	public Collection<String> keys(String pattern);

	public void delete(String key);

	public void delete(Collection<String> key);
	
	public boolean hasKey(String key);

	/* ----------- string --------- */
	public <T> T get(String key, Class<T> clazz);

	public <T> List<T> mget(Collection<String> keys, Class<T> clazz);

	public <T> void set(String key, T obj, Long timeout, TimeUnit unit);

	public <T> T getAndSet(String key, T obj, Class<T> clazz);

	public int decrement(String key, int delta);

	public int increment(String key, int delta);

	/* ----------- list --------- */
	public int size(String key);

	public <T> List<T> range(String key, long start, long end, Class<T> clazz);

	public void rightPushAll(String key, Collection<?> values, Long timeout, TimeUnit unit);

	public <T> void leftPush(String key, T obj);

	public <T> T leftPop(String key, Class<T> clazz);

	public void remove(String key, int count, Object obj);

	/* ----------- zset --------- */
	public int zcard(String key);

	public <T> List<T> zrange(String key, long start, long end, Class<T> clazz);

	public void zadd(String key, Object obj, double score);

	public void zaddAll(String key, List<TypedTuple<?>> tupleList, Long timeout, TimeUnit unit);

	public void zrem(String key, Object obj);

	public void unionStore(String destKey, Collection<String> keys, Long timeout, TimeUnit unit);

}
