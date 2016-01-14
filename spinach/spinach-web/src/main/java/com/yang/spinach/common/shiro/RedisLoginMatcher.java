package com.yang.spinach.common.shiro;

import com.yang.spinach.common.utils.redis.cache.ICache;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.crypto.hash.Md5Hash;

import java.util.concurrent.atomic.AtomicInteger;

public class RedisLoginMatcher extends HashedCredentialsMatcher {

	private ICache cache;
	private static final String prefix = "passwordCache_";
	private Long expire = 30 * 60L;// 缓存超时时间

	@Override
	public boolean doCredentialsMatch(AuthenticationToken token,
			AuthenticationInfo info) {
		String username = (String) token.getPrincipal();
		String cacheName = prefix + username;
		// +1
		String count = cache.get(cacheName);
		AtomicInteger retryCount = null;
		if (count == null) {
			retryCount = new AtomicInteger(0);
			cache.setEx(cacheName, retryCount.toString(), expire);
		}else{
			retryCount = new AtomicInteger(Integer.valueOf(count));
		}
		int m = retryCount.incrementAndGet();
		ShiroSessionUtils.setAttribute("loginNum", m);
		if (m > 5) {
			// count > 5 throw 5次登陆异常
			throw new ExcessiveAttemptsException();
		}
		String credentials = new Md5Hash(token.getCredentials()).toString();
		boolean matches = credentials.equals(getCredentials(info).toString());

		if (matches) {
			// 去掉错误记录
			cache.del(cacheName);
		} else {
			cache.setEx(cacheName, retryCount.toString(), expire);
		}
		return matches;
	}

	public ICache getCache() {
		return cache;
	}

	public void setCache(ICache cache) {
		this.cache = cache;
	}

	public Long getExpire() {
		return expire;
	}

	public void setExpire(Long expire) {
		this.expire = expire;
	}

}
