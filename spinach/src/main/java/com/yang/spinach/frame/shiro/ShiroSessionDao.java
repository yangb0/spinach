package com.yang.spinach.frame.shiro;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.shiro.cache.AbstractCacheManager;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.cache.CacheManagerAware;
import org.apache.shiro.cache.MapCache;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.ValidatingSession;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;

import com.yang.spinach.frame.utils.SerializeUtil;
import com.yang.spinach.frame.utils.redis.cache.ICache;

/**
 * 自定义授权会话管理类
 * 
 * @author yang
 *
 */
public class ShiroSessionDao extends AbstractSessionDAO implements
		CacheManagerAware {

	/**
	 * The default active sessions cache name, equal to
	 * {@code shiro-activeSessionCache}.
	 */
	public static final String keyPrefix = "shiro-activeSessionCache_";

	private Long expire = 30 * 60L;// 缓存超时时间
	/**
	 * The CacheManager to use to acquire the Session cache.
	 */
	private ICache cache;

	/**
	 * Default no-arg constructor.
	 */
	public ShiroSessionDao() {
		setCacheManager(new AbstractCacheManager() {
			@Override
			protected Cache<Serializable, Session> createCache(String name)
					throws CacheException {
				return new MapCache<Serializable, Session>(name,
						new ConcurrentHashMap<Serializable, Session>());
			}
		});
	}

	@Override
	public Collection<Session> getActiveSessions() {
		Set<Session> set = new HashSet<Session>();
		Set<byte[]> keys;
		try {
			keys = cache.getKeys((keyPrefix + "*").getBytes());
			if (keys != null && keys.size() > 0) {
				for (byte[] key : keys) {
					Session s;
					s = (Session) cache.getCached(key);
					set.add(s);
				}
			}
		} catch (Exception e) {
		}
		return set;
	}

	/**
	 * Calls {@code super.create(session)}, then caches the session keyed by the
	 * returned {@code sessionId}, and then returns this {@code sessionId}.
	 *
	 * @param session
	 *            Session object to create in the EIS and then cache.
	 */
	public Serializable create(Session session) {
		Serializable sessionId = super.create(session);
		if (session != null) {
			try {
				cache.updateCached(getKey(sessionId),
						SerializeUtil.serialize(session), getExpire());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return sessionId;
	}

	/**
	 * Returns the cached session with the corresponding {@code sessionId} or
	 * {@code null} if there is no session cached under that id (or if there is
	 * no Cache).
	 *
	 * @param sessionId
	 *            the id of the cached session to acquire.
	 * @return the cached session with the corresponding {@code sessionId}, or
	 *         {@code null} if the session does not exist or is not cached.
	 */
	protected Session getCachedSession(Serializable sessionId) {
		Session cached = null;
		if (sessionId != null) {
			Object o;
			try {
				o = cache.getCached(getKey(sessionId));
				if (o != null) {
					cached = (Session) o;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return cached;
	}

	/**
	 * Attempts to acquire the Session from the cache first using the session ID
	 * as the cache key. If no session is found,
	 * {@code super.readSession(sessionId)} is called to perform the actual
	 * retrieval.
	 *
	 * @param sessionId
	 *            the id of the session to retrieve from the EIS.
	 * @return the session identified by {@code sessionId} in the EIS.
	 * @throws UnknownSessionException
	 *             if the id specified does not correspond to any session in the
	 *             cache or EIS.
	 */
	public Session readSession(Serializable sessionId)
			throws UnknownSessionException {
		Session s = getCachedSession(sessionId);
		if (s == null) {
			s = super.readSession(sessionId);
		}
		return s;
	}

	/**
	 * Updates the state of the given session to the EIS by first delegating to
	 * {@link #doUpdate(org.apache.shiro.session.Session)}. If the session is a
	 * {@link ValidatingSession}, it will be added to the cache only if it is
	 * {@link ValidatingSession#isValid()} and if invalid, will be removed from
	 * the cache. If it is not a {@code ValidatingSession} instance, it will be
	 * added to the cache in any event.
	 *
	 * @param session
	 *            the session object to update in the EIS.
	 * @throws UnknownSessionException
	 *             if no existing EIS session record exists with the identifier
	 *             of {@link Session#getId() session.getId()}
	 */
	public void update(Session session) throws UnknownSessionException {
		doUpdate(session);
		if (session instanceof ValidatingSession) {
			if (((ValidatingSession) session).isValid()) {

				try {
					cache.updateCached(getKey(session.getId()),
							SerializeUtil.serialize(session), getExpire());
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				uncache(session);
			}
		} else {
			try {
				cache.updateCached(getKey(session.getId()),
						SerializeUtil.serialize(session), getExpire());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Removes the specified session from any cache and then permanently deletes
	 * the session from the EIS by delegating to {@link #doDelete}.
	 *
	 * @param session
	 *            the session to remove from caches and permanently delete from
	 *            the EIS.
	 */
	public void delete(Session session) {
		uncache(session);
		doDelete(session);
	}

	/**
	 * Removes the specified Session from the cache.
	 *
	 * @param session
	 *            the session to remove from the cache.
	 */
	protected void uncache(Session session) {
		if (session == null) {
			return;
		}
		Serializable id = session.getId();
		if (id == null) {
			return;
		}
		cache.del(keyPrefix + id.toString());
	}

	protected Serializable doCreate(Session session) {
		Serializable sessionId = generateSessionId(session);
		assignSessionId(session, sessionId);
		return sessionId;
	}

	public byte[] getKey(Serializable sessionId) {
		return (keyPrefix + sessionId).getBytes();
	}

	protected Session doReadSession(Serializable sessionId) {
		return null; // should never execute because this implementation relies
						// on parent class to access cache, which
		// is where all sessions reside - it is the cache implementation that
		// determines if the
		// cache is memory only or disk-persistent, etc.
	}

	protected void doUpdate(Session session) {
		// does nothing - parent class persists to cache.
	}

	protected void doDelete(Session session) {
		// does nothing - parent class removes from cache.
	}

	@Override
	public void setCacheManager(CacheManager cacheManager) {

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
