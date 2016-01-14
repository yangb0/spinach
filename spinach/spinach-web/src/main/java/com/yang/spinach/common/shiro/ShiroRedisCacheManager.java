package com.yang.spinach.common.shiro;

import com.yang.spinach.common.utils.redis.cache.ICache;
import org.apache.shiro.cache.AbstractCacheManager;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;

/**
 * shiro session管理
 * 
 * @author yang
 *
 */
public class ShiroRedisCacheManager extends AbstractCacheManager {
	private ICache cache;

	@SuppressWarnings("rawtypes")
	@Override
	protected Cache createCache(String cacheName) throws CacheException {
		return new ShiroRedisCache<String, Object>(cacheName, cache);
	}

	public ICache getCache() {
		return cache;
	}

	public void setCache(ICache cache) {
		this.cache = cache;
	}

}
