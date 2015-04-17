package com.yang.spinach.frame.shiro;

import java.util.Collection;
import java.util.Set;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yang.spinach.frame.utils.SerializeUtil;
import com.yang.spinach.frame.utils.redis.cache.ICache;

public class ShiroRedisCache<K, V> implements Cache<K, V> {
	private Logger logger = LoggerFactory.getLogger(getClass());
	private String name;
	private ICache cache;

	public ShiroRedisCache(String name, ICache cached) {
		this.name = name;
		this.cache = cached;
	}

	/**
	 * 获得byte[]型的key
	 * 
	 * @param key
	 * @return
	 */
	private byte[] getByteKey(K key) {
		if (key instanceof String) {
			String preKey = key.toString();
			return preKey.getBytes();
		} else {
			return SerializeUtil.serialize(key);
		}
	}

	private byte[] getByteName() {
		return name.getBytes();

	}

	@Override
	public V get(K key) throws CacheException {
		logger.debug("根据key从Redis中获取对象 key [" + key + "]");
		try {
			if (key == null) {
				return null;
			} else {
				V value = (V) cache.getHashCached(getByteName(),
						getByteKey(key));
				return value;
			}
		} catch (Throwable t) {
			throw new CacheException(t);
		}

	}

	@Override
	public V put(K key, V value) throws CacheException {
		logger.debug("根据key从存储 key [" + key + "]");
		try {
			cache.updateHashCached(getByteName(), getByteKey(key),
					SerializeUtil.serialize(value), null);
			return value;
		} catch (Throwable t) {
			throw new CacheException(t);
		}
	}

	@Override
	public V remove(K key) throws CacheException {
		logger.debug("从redis中删除 key [" + key + "]");
		try {
			V previous = get(key);
			cache.deleteHashCached(getByteName(), getByteKey(key));
			return previous;
		} catch (Throwable t) {
			throw new CacheException(t);
		}
	}

	@Override
	public void clear() throws CacheException {
		logger.debug("从redis中删除所有元素");
		try {
			cache.deleteCached(getByteName());
		} catch (Throwable t) {
			throw new CacheException(t);
		}
	}

	@Override
	public int size() {
		try {
			Long longSize = new Long(cache.getHashSize(getByteName()));
			return longSize.intValue();
		} catch (Throwable t) {
			throw new CacheException(t);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public Set<K> keys() {
		try {
			Set<K> keys = cache.getHashKeys(getByteName());
			return keys;
		} catch (Throwable t) {
			throw new CacheException(t);
		}
	}

	@Override
	public Collection<V> values() {
		try {
			Collection<V> values = cache.getHashValues(getByteName());
			return values;
		} catch (Throwable t) {
			throw new CacheException(t);
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ICache getCache() {
		return cache;
	}

	public void setCache(ICache cache) {
		this.cache = cache;
	}


}
