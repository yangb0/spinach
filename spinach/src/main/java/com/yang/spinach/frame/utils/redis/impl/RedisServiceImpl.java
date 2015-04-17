package com.yang.spinach.frame.utils.redis.impl;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import redis.clients.jedis.Tuple;

import com.yang.spinach.frame.utils.redis.RedisService;

@Component
public class RedisServiceImpl implements RedisService {

	private RedisTemplate<Serializable, Serializable> redisTemplate;

	public RedisTemplate<Serializable, Serializable> getRedisTemplate() {
		return redisTemplate;
	}

	@Resource
	public void setRedisTemplate(
			RedisTemplate<Serializable, Serializable> redisTemplate) {
		this.redisTemplate = redisTemplate;
	}

	@Override
	public boolean zExists(final String key, final String value) {
		return redisTemplate.execute(new RedisCallback<Boolean>() {

			@Override
			public Boolean doInRedis(RedisConnection connection)
					throws DataAccessException {
				return connection.zScore(redisTemplate.getStringSerializer()
						.serialize(key), redisTemplate.getStringSerializer()
						.serialize(value)) != null;
			}
		});
	}

	@Override
	public void zAdd(final String key, final String value, final double score) {
		redisTemplate.execute(new RedisCallback<Serializable>() {

			@Override
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

	@Override
	public void zAdd(final String key, final Map<String, Long> map) {
		redisTemplate.execute(new RedisCallback<Serializable>() {

			@Override
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

	@Override
	public void zRem(final String key, final String member) {
		redisTemplate.execute(new RedisCallback<Serializable>() {

			@Override
			public Serializable doInRedis(RedisConnection connection)
					throws DataAccessException {
				connection.zRem(
						redisTemplate.getStringSerializer().serialize(key),
						redisTemplate.getStringSerializer().serialize(member));
				return true;
			}
		});
	}

	@Override
	public Set<String> zPop(final String key, final int begin, final int end) {
		return redisTemplate.execute(new RedisCallback<Set<String>>() {

			@Override
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

	@Override
	public Set<String> zPopBySocre(final String key, final double min,
			final double max, final int count) {

		return redisTemplate.execute(new RedisCallback<Set<String>>() {

			@Override
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

	@Override
	public long zCard(final String key) {
		return redisTemplate.execute(new RedisCallback<Long>() {

			@Override
			public Long doInRedis(RedisConnection connection)
					throws DataAccessException {
				return connection.zCard(redisTemplate.getStringSerializer()
						.serialize(key));
			}
		});
	}

	@Override
	public Set<String> zRange(final String key, final long begin, final long end) {

		return redisTemplate.execute(new RedisCallback<Set<String>>() {

			@Override
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

	@Override
	public Set<Tuple> zRangeWithScore(final String key, final long begin,
			final long end) {
		return redisTemplate.execute(new RedisCallback<Set<Tuple>>() {

			@Override
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

	@Override
	public void setEx(final String key, final String value, final long seconds) {
		redisTemplate.execute(new RedisCallback<Serializable>() {

			@Override
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

	@Override
	public String get(final String key) {
		return redisTemplate.execute(new RedisCallback<String>() {

			@Override
			public String doInRedis(RedisConnection connection)
					throws DataAccessException {
				byte[] bytes = connection.get(redisTemplate
						.getStringSerializer().serialize(key));
				return redisTemplate.getStringSerializer().deserialize(bytes);
			}
		});
	}

	@Override
	public void del(final String... keys) {
		redisTemplate.execute(new RedisCallback<Serializable>() {

			@Override
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

	@Override
	public boolean exists(final String key) {
		return redisTemplate.execute(new RedisCallback<Boolean>() {

			@Override
			public Boolean doInRedis(RedisConnection connection)
					throws DataAccessException {
				return connection.exists(redisTemplate.getStringSerializer()
						.serialize(key));
			}
		});
	}

	@Override
	public long dbSize() {
		return redisTemplate.execute(new RedisCallback<Long>() {

			@Override
			public Long doInRedis(RedisConnection connection)
					throws DataAccessException {
				return connection.dbSize();
			}
		});
	}

	@Override
	public void flushAll() {
		redisTemplate.execute(new RedisCallback<Serializable>() {

			@Override
			public Serializable doInRedis(RedisConnection connection)
					throws DataAccessException {
				connection.flushAll();
				return true;
			}
		});
	}

	@Override
	public void set(final String key, final String value) {
		redisTemplate.execute(new RedisCallback<Serializable>() {

			@Override
			public Serializable doInRedis(RedisConnection connection)
					throws DataAccessException {
				connection.set(
						redisTemplate.getStringSerializer().serialize(key),
						redisTemplate.getStringSerializer().serialize(value));
				return true;
			}
		});
	}

	@Override
	public void hDel(final String key, final String... fields) {
		redisTemplate.execute(new RedisCallback<Serializable>() {

			@Override
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

	@Override
	public boolean hExists(final String key, final String field) {
		return redisTemplate.execute(new RedisCallback<Boolean>() {

			@Override
			public Boolean doInRedis(RedisConnection connection)
					throws DataAccessException {
				return connection.hExists(redisTemplate.getStringSerializer()
						.serialize(key), redisTemplate.getStringSerializer()
						.serialize(field));
			}
		});
	}

	@Override
	public String hGet(final String key, final String field) {
		return redisTemplate.execute(new RedisCallback<String>() {

			@Override
			public String doInRedis(RedisConnection connection)
					throws DataAccessException {
				byte[] bytes = connection.hGet(redisTemplate
						.getStringSerializer().serialize(key), redisTemplate
						.getStringSerializer().serialize(field));
				return redisTemplate.getStringSerializer().deserialize(bytes);
			}
		});
	}

	@Override
	public Map<String, String> hGet(final String key, final String... field) {
		return redisTemplate.execute(new RedisCallback<Map<String, String>>() {

			@Override
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

	@Override
	public long hLen(final String key) {
		return redisTemplate.execute(new RedisCallback<Long>() {

			@Override
			public Long doInRedis(RedisConnection connection)
					throws DataAccessException {
				return connection.hLen(redisTemplate.getStringSerializer()
						.serialize(key));
			}
		});
	}

	@Override
	public void hmSet(final String key, final Map<String, String> map) {
		redisTemplate.execute(new RedisCallback<Serializable>() {

			@Override
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

	@Override
	public void hSet(final String key, final String field, final String value) {
		redisTemplate.execute(new RedisCallback<Serializable>() {

			@Override
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

	@Override
	public void subscribe(final MessageListener listener,
			final String... channels) {
		redisTemplate.execute(new RedisCallback<Serializable>() {

			@Override
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

	@Override
	public void publish(final String channel, final String message) {
		redisTemplate.execute(new RedisCallback<Serializable>() {

			@Override
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
