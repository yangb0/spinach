package com.yang.spinach.frame.utils.redis.cache;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import javax.annotation.Resource;

import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;

import redis.clients.jedis.Tuple;

import com.yang.spinach.frame.utils.SerializeUtil;

public class RedisCache implements ICache {

	// -1 - never expire
	private int expire = -1;
	private RedisTemplate<String, Object> redisTemplate;

	@Override
	public String deleteCached(final byte[] sessionId) throws Exception {
		redisTemplate.execute(new RedisCallback<Object>() {
			public Long doInRedis(RedisConnection connection)
					throws DataAccessException {
				connection.del(sessionId);
				return null;
			}
		});
		return null;
	}

	@Override
	public String updateCached(final byte[] key, final byte[] session,
			final Long expireSec) throws Exception {
		return (String) redisTemplate.execute(new RedisCallback<Object>() {
			public String doInRedis(final RedisConnection connection)
					throws DataAccessException {
				connection.set(key, session);
				if (expireSec != null) {
					connection.expire(key, expireSec);
				} else {
					connection.expire(key, expire);
				}
				return new String(key);
			}
		});

	}

	@Override
	public Object getCached(final byte[] sessionId) throws Exception {
		return redisTemplate.execute(new RedisCallback<Object>() {
			public Object doInRedis(RedisConnection connection)
					throws DataAccessException {
				byte[] bs = connection.get(sessionId);
				return SerializeUtil.unserialize(bs);
			}
		});

	}

	@Override
	public Set<byte[]> getKeys(final byte[] keys) throws Exception {
		return redisTemplate.execute(new RedisCallback<Set>() {
			public Set doInRedis(RedisConnection connection)
					throws DataAccessException {
				Set<byte[]> setByte = connection.keys(keys);
				if (setByte == null || setByte.size() < 1) {
					return null;
				}
				Set set = new HashSet();
				for (byte[] key : setByte) {
					byte[] bs = connection.get(key);
					set.add(bs);
				}

				return set;

			}
		});
	}

	@Override
	public Set getHashKeys(final byte[] key) throws Exception {
		return (Set) redisTemplate.execute(new RedisCallback<Set>() {
			public Set doInRedis(RedisConnection connection)
					throws DataAccessException {
				Set<byte[]> hKeys = connection.hKeys(key);
				if (hKeys == null || hKeys.size() > 1) {
					return null;
				}
				Set set = new HashSet();
				for (byte[] bs : hKeys) {
					set.add(SerializeUtil.unserialize(bs));
				}
				return set;
			}
		});

	}

	@Override
	public Boolean updateHashCached(final byte[] key, final byte[] mapkey,
			final byte[] value, final Long expireSec) throws Exception {

		return redisTemplate.execute(new RedisCallback<Boolean>() {
			public Boolean doInRedis(RedisConnection connection)
					throws DataAccessException {
				Boolean hSet = connection.hSet(key, mapkey, value);
				if (expireSec != null) {
					connection.expire(key, expireSec);
				} else {
					connection.expire(key, expire);
				}
				return hSet;
			}
		});
	}

	@Override
	public Object getHashCached(final byte[] key, final byte[] mapkey)
			throws Exception {
		return redisTemplate.execute(new RedisCallback<Object>() {
			public Object doInRedis(RedisConnection connection)
					throws DataAccessException {
				byte[] hGet = connection.hGet(key, mapkey);
				return SerializeUtil.unserialize(hGet);

			}
		});
	}

	@Override
	public Long deleteHashCached(final byte[] key, final byte[] mapkey)
			throws Exception {
		return redisTemplate.execute(new RedisCallback<Long>() {
			public Long doInRedis(RedisConnection connection)
					throws DataAccessException {
				Long hDel = connection.hDel(key, mapkey);
				return hDel;

			}
		});
	}

	@Override
	public Long getHashSize(final byte[] key) throws Exception {
		return redisTemplate.execute(new RedisCallback<Long>() {
			public Long doInRedis(RedisConnection connection)
					throws DataAccessException {
				Long len = connection.hLen(key);

				return len;

			}
		});
	}

	@Override
	public Long getDBSize() throws Exception {
		return redisTemplate.execute(new RedisCallback<Long>() {
			public Long doInRedis(RedisConnection connection)
					throws DataAccessException {
				Long len = connection.dbSize();

				return len;

			}
		});
	}

	@Override
	public void clearDB() throws Exception {
		redisTemplate.execute(new RedisCallback<Long>() {
			public Long doInRedis(RedisConnection connection)
					throws DataAccessException {
				connection.flushDb();
				return null;

			}
		});
	}

	public RedisTemplate<String, Object> getRedisTemplate() {
		return redisTemplate;
	}

	public void setRedisTemplate(RedisTemplate<String, Object> redisTemplate) {
		this.redisTemplate = redisTemplate;
	}

	public int getExpire() {
		return expire;
	}

	public void setExpire(int expire) {
		this.expire = expire;
	}

	@Override
	public List getHashValues(final byte[] key) throws Exception {
		return redisTemplate.execute(new RedisCallback<List>() {
			public List doInRedis(RedisConnection connection)
					throws DataAccessException {
				List<byte[]> hVals = connection.hVals(key);

				if (hVals == null || hVals.size() < 1) {
					return null;
				}
				List list = new ArrayList();

				for (byte[] bs : hVals) {
					list.add(SerializeUtil.unserialize(bs));
				}
				return list;

			}
		});
	}

	public boolean zExists(final String key, final String value) {
		return redisTemplate.execute(new RedisCallback<Boolean>() {

			public Boolean doInRedis(RedisConnection connection)
					throws DataAccessException {
				return connection.zScore(redisTemplate.getStringSerializer()
						.serialize(key), redisTemplate.getStringSerializer()
						.serialize(value)) != null;
			}
		});
	}

	public void zAdd(final String key, final String value, final double score) {
		redisTemplate.execute(new RedisCallback<Serializable>() {

			public Serializable doInRedis(RedisConnection connection)
					throws DataAccessException {
				connection.zAdd(
						redisTemplate.getStringSerializer().serialize(key),
						score,
						redisTemplate.getStringSerializer().serialize(value));
				return true;
			}
		});
	}

	public void zAdd(final String key, final Map<String, Long> map) {
		redisTemplate.execute(new RedisCallback<Serializable>() {

			public Serializable doInRedis(RedisConnection connection)
					throws DataAccessException {

				for (Entry<String, Long> entry : map.entrySet()) {
					connection.zAdd(redisTemplate.getStringSerializer()
							.serialize(key), entry.getValue(), redisTemplate
							.getStringSerializer().serialize(entry.getKey()));
				}
				return true;
			}
		});
	}

	public void zRem(final String key, final String member) {
		redisTemplate.execute(new RedisCallback<Serializable>() {

			public Serializable doInRedis(RedisConnection connection)
					throws DataAccessException {
				connection.zRem(
						redisTemplate.getStringSerializer().serialize(key),
						redisTemplate.getStringSerializer().serialize(member));
				return true;
			}
		});
	}

	public Set<String> zPop(final String key, final int begin, final int end) {
		return redisTemplate.execute(new RedisCallback<Set<String>>() {

			public Set<String> doInRedis(RedisConnection connection)
					throws DataAccessException {
				Set<String> jsons = new LinkedHashSet<String>();

				Set<byte[]> set = connection.zRange(redisTemplate
						.getStringSerializer().serialize(key), begin, end);

				if (set != null) {

					for (byte[] bytes : set) {

						connection.zRem(redisTemplate.getStringSerializer()
								.serialize(key), bytes);

						jsons.add((String) redisTemplate.getStringSerializer()
								.deserialize(bytes));
					}
				}

				return jsons;
			}
		});
	}

	public Set<String> zPopBySocre(final String key, final double min,
			final double max, final int count) {

		return redisTemplate.execute(new RedisCallback<Set<String>>() {

			public Set<String> doInRedis(RedisConnection connection)
					throws DataAccessException {
				Set<String> jsons = new LinkedHashSet<String>();

				Set<byte[]> set = connection.zRevRangeByScore(redisTemplate
						.getStringSerializer().serialize(key), min, max, 0,
						count);

				if (set != null) {

					for (byte[] bytes : set) {

						connection.zRem(redisTemplate.getStringSerializer()
								.serialize(key), bytes);

						jsons.add((String) redisTemplate.getStringSerializer()
								.deserialize(bytes));
					}
				}

				return jsons;
			}
		});
	}

	public long zCard(final String key) {
		return redisTemplate.execute(new RedisCallback<Long>() {

			public Long doInRedis(RedisConnection connection)
					throws DataAccessException {
				return connection.zCard(redisTemplate.getStringSerializer()
						.serialize(key));
			}
		});
	}

	public Set<String> zRange(final String key, final long begin, final long end) {

		return redisTemplate.execute(new RedisCallback<Set<String>>() {

			public Set<String> doInRedis(RedisConnection connection)
					throws DataAccessException {
				Set<String> jsons = new LinkedHashSet<String>();

				Set<byte[]> set = connection.zRange(redisTemplate
						.getStringSerializer().serialize(key), begin, end);

				if (set != null) {
					for (byte[] bytes : set) {
						jsons.add((String) redisTemplate.getStringSerializer()
								.deserialize(bytes));
					}
				}
				return jsons;
			}
		});
	}

	public Set<Tuple> zRangeWithScore(final String key, final long begin,
			final long end) {
		return redisTemplate.execute(new RedisCallback<Set<Tuple>>() {

			public Set<Tuple> doInRedis(RedisConnection connection)
					throws DataAccessException {
				Set<Tuple> jsons = new LinkedHashSet<Tuple>();

				Set<org.springframework.data.redis.connection.RedisZSetCommands.Tuple> temp = connection
						.zRangeWithScores(redisTemplate.getStringSerializer()
								.serialize(key), begin, end);

				if (temp != null) {
					for (org.springframework.data.redis.connection.RedisZSetCommands.Tuple tuple : temp) {
						jsons.add(new Tuple((String) redisTemplate
								.getStringSerializer().deserialize(
										tuple.getValue()), tuple.getScore()));
					}
				}
				return jsons;
			}
		});
	}

	public void setEx(final String key, final String value, final long seconds) {
		redisTemplate.execute(new RedisCallback<Serializable>() {

			public Serializable doInRedis(RedisConnection connection)
					throws DataAccessException {
				connection.setEx(
						redisTemplate.getStringSerializer().serialize(key),
						seconds,
						redisTemplate.getStringSerializer().serialize(value));
				return true;
			}
		});
	}

	public String get(final String key) {
		return redisTemplate.execute(new RedisCallback<String>() {

			public String doInRedis(RedisConnection connection)
					throws DataAccessException {
				byte[] bytes = connection.get(redisTemplate
						.getStringSerializer().serialize(key));
				return redisTemplate.getStringSerializer().deserialize(bytes);
			}
		});
	}

	public void del(final String... keys) {
		redisTemplate.execute(new RedisCallback<Serializable>() {

			public Serializable doInRedis(RedisConnection connection)
					throws DataAccessException {
				for (String string : keys) {

					connection.del(redisTemplate.getStringSerializer()
							.serialize(string));
				}
				return true;
			}
		});
	}

	public boolean exists(final String key) {
		return redisTemplate.execute(new RedisCallback<Boolean>() {

			public Boolean doInRedis(RedisConnection connection)
					throws DataAccessException {
				return connection.exists(redisTemplate.getStringSerializer()
						.serialize(key));
			}
		});
	}

	public long dbSize() {
		return redisTemplate.execute(new RedisCallback<Long>() {

			public Long doInRedis(RedisConnection connection)
					throws DataAccessException {
				return connection.dbSize();
			}
		});
	}

	public void flushAll() {
		redisTemplate.execute(new RedisCallback<Serializable>() {

			public Serializable doInRedis(RedisConnection connection)
					throws DataAccessException {
				connection.flushAll();
				return true;
			}
		});
	}

	public void set(final String key, final String value) {
		redisTemplate.execute(new RedisCallback<Serializable>() {

			public Serializable doInRedis(RedisConnection connection)
					throws DataAccessException {
				connection.set(
						redisTemplate.getStringSerializer().serialize(key),
						redisTemplate.getStringSerializer().serialize(value));
				return true;
			}
		});
	}

	public void hDel(final String key, final String... fields) {
		redisTemplate.execute(new RedisCallback<Serializable>() {

			public Serializable doInRedis(RedisConnection connection)
					throws DataAccessException {
				for (String string : fields) {
					connection.hDel(redisTemplate.getStringSerializer()
							.serialize(key), redisTemplate
							.getStringSerializer().serialize(string));
				}
				return true;
			}
		});
	}

	public boolean hExists(final String key, final String field) {
		return redisTemplate.execute(new RedisCallback<Boolean>() {

			public Boolean doInRedis(RedisConnection connection)
					throws DataAccessException {
				return connection.hExists(redisTemplate.getStringSerializer()
						.serialize(key), redisTemplate.getStringSerializer()
						.serialize(field));
			}
		});
	}

	public String hGet(final String key, final String field) {
		return redisTemplate.execute(new RedisCallback<String>() {

			public String doInRedis(RedisConnection connection)
					throws DataAccessException {
				byte[] bytes = connection.hGet(redisTemplate
						.getStringSerializer().serialize(key), redisTemplate
						.getStringSerializer().serialize(field));
				return redisTemplate.getStringSerializer().deserialize(bytes);
			}
		});
	}

	public Map<String, String> hGet(final String key, final String... field) {
		return redisTemplate.execute(new RedisCallback<Map<String, String>>() {

			public Map<String, String> doInRedis(RedisConnection connection)
					throws DataAccessException {
				Map<String, String> map = new LinkedHashMap<String, String>();
				for (String string : field) {
					byte[] bytes = connection.hGet(
							redisTemplate.getStringSerializer().serialize(key),
							redisTemplate.getStringSerializer().serialize(
									string));

					map.put(string, redisTemplate.getStringSerializer()
							.deserialize(bytes));
				}
				return map;
			}
		});
	}

	public long hLen(final String key) {
		return redisTemplate.execute(new RedisCallback<Long>() {

			public Long doInRedis(RedisConnection connection)
					throws DataAccessException {
				return connection.hLen(redisTemplate.getStringSerializer()
						.serialize(key));
			}
		});
	}

	public void hmSet(final String key, final Map<String, String> map) {
		redisTemplate.execute(new RedisCallback<Serializable>() {

			public Serializable doInRedis(RedisConnection connection)
					throws DataAccessException {
				for (Entry<String, String> entry : map.entrySet()) {
					connection.hSet(
							redisTemplate.getStringSerializer().serialize(key),
							redisTemplate.getStringSerializer().serialize(
									entry.getKey()),
							redisTemplate.getStringSerializer().serialize(
									entry.getValue()));
				}
				return true;
			}
		});
	}

	public void hSet(final String key, final String field, final String value) {
		redisTemplate.execute(new RedisCallback<Serializable>() {

			public Serializable doInRedis(RedisConnection connection)
					throws DataAccessException {
				connection.hSet(
						redisTemplate.getStringSerializer().serialize(key),
						redisTemplate.getStringSerializer().serialize(field),
						redisTemplate.getStringSerializer().serialize(value));
				return true;
			}
		});
	}

	public void subscribe(final MessageListener listener,
			final String... channels) {
		redisTemplate.execute(new RedisCallback<Serializable>() {

			public Serializable doInRedis(RedisConnection connection)
					throws DataAccessException {

				for (String channel : channels) {
					connection.subscribe(listener, redisTemplate
							.getStringSerializer().serialize(channel));
				}

				return true;
			}
		});
	}

	public void publish(final String channel, final String message) {
		redisTemplate.execute(new RedisCallback<Serializable>() {

			public Serializable doInRedis(RedisConnection connection)
					throws DataAccessException {

				connection.publish(redisTemplate.getStringSerializer()
						.serialize(channel), redisTemplate
						.getStringSerializer().serialize(message));

				return true;
			}
		});
	}

}
