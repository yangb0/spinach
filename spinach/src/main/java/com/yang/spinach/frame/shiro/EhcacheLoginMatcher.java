package com.yang.spinach.frame.shiro;

import java.util.concurrent.atomic.AtomicInteger;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.crypto.hash.Md5Hash;

public class EhcacheLoginMatcher extends HashedCredentialsMatcher {

	private final Cache<String, AtomicInteger> passwordCache; // 原子

	public EhcacheLoginMatcher(CacheManager cacheManager) {
		passwordCache = cacheManager.getCache("passwordCache");
	}

	@Override
	public boolean doCredentialsMatch(AuthenticationToken token,
			AuthenticationInfo info) {
		String username = (String) token.getPrincipal();
		// +1
		AtomicInteger retryCount = passwordCache.get(username);
		if (retryCount == null) {
			retryCount = new AtomicInteger(0);
			passwordCache.put(username, retryCount);
		}
		int m = retryCount.incrementAndGet();
		ShiroSessionUtils.setAttribute("loginNum", m);
		if (m > 5) {
			// count > 5 throw 5次登陆异常
			throw new ExcessiveAttemptsException();
		}

		// boolean matches = super.doCredentialsMatch(token, info); fs myself
		// immp
		// System.out.println("TokenCredentials=" + new
		// Md5Hash(token.getCredentials()).toString());
		// System.out.println("InfoCredentials=" +
		// getCredentials(info).toString());
		String credentials = new Md5Hash(token.getCredentials()).toString();
		boolean matches = credentials.equals(getCredentials(info).toString());

		if (matches) {
			// 去掉错误记录
			passwordCache.remove(username);
		} else {
			passwordCache.put(username, retryCount);
		}
		return matches;
	}
}
