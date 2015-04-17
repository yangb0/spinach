package com.yang.spinach.frame.utils.redis;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import redis.clients.jedis.Tuple;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-test.xml" })
public class RedisServiceTest extends AbstractJUnit4SpringContextTests {
	private RedisService redisService;

	@Test
	public void testGetAndSetAndDelAndExists() {
		redisService.flushAll();
		assertEquals(0L, redisService.dbSize());

		assertNull(redisService.get("Hello"));
		assertFalse(redisService.exists("Hello"));
		redisService.set("Hello", "World");
		assertEquals(1L, redisService.dbSize());
		assertTrue(redisService.exists("Hello"));
		assertEquals("World", redisService.get("Hello"));
		assertEquals(1L, redisService.dbSize());
		redisService.del("Hello");
		assertEquals(0L, redisService.dbSize());

		redisService.flushAll();
		assertEquals(0L, redisService.dbSize());

		redisService.setEx("Hello", "World", 1);
		assertNotNull(redisService.get("Hello"));
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		assertNull(redisService.get("Hello"));

		redisService.flushAll();
	}

	@Test
	public void testSortedSet() {
		redisService.flushAll();
		assertEquals(0L, redisService.dbSize());

		assertEquals(0, redisService.zCard("List"));
		redisService.zAdd("List", "Item1", 100);
		assertEquals(1, redisService.zCard("List"));

		Set<String> set = redisService.zRange("List", 0, 100);
		assertNotNull(set);
		assertEquals(1, set.size());
		String string = set.iterator().next();
		assertNotNull(string);
		assertEquals("Item1", string);
		assertEquals(1, redisService.zCard("List"));

		Set<Tuple> tuples = redisService.zRangeWithScore("List", 0, 100);
		assertNotNull(tuples);

		Tuple tuple = tuples.iterator().next();
		assertNotNull(tuple);
		assertTrue(tuple.getScore() == 100);
		assertEquals("Item1", tuple.getElement());

		redisService.zAdd("List", "Item2", 200);
		assertEquals(2, redisService.zCard("List"));

		set = redisService.zRange("List", 0, 100);
		assertNotNull(set);
		assertEquals(2, set.size());

		redisService.flushAll();

		Map<String, Long> map = new HashMap<String, Long>();
		map.put("Item1", 100L);
		map.put("Item2", 200L);
		map.put("Item3", 1L);
		redisService.zAdd("List", map);
		assertEquals(1L, redisService.dbSize());
		assertEquals(3L, redisService.zCard("List"));

		set = redisService.zRange("List", 0, 100);
		assertNotNull(set);
		assertEquals(3, set.size());

		Iterator<String> it = set.iterator();
		assertEquals("Item3", it.next());
		assertEquals("Item1", it.next());
		assertEquals("Item2", it.next());

		assertEquals(3, redisService.zCard("List"));

		set = redisService.zPop("List", 0, 10);
		assertNotNull(set);
		assertEquals(3, set.size());
		assertEquals(0, redisService.zCard("List"));

		redisService.flushAll();
	}

	@Test
	public void testHashs() {
		redisService.flushAll();
		assertEquals(0L, redisService.dbSize());

		assertFalse(redisService.hExists("Hash", "Item1"));
		assertNull(redisService.hGet("Hash", "Item1"));
		assertEquals(0, redisService.hLen("Hash"));

		redisService.hSet("Hash", "Item1", "Value1");

		assertTrue(redisService.hExists("Hash", "Item1"));
		assertNotNull(redisService.hGet("Hash", "Item1"));
		assertEquals("Value1", redisService.hGet("Hash", "Item1"));
		assertEquals(1, redisService.hLen("Hash"));

		redisService.hDel("Hash", "Item1");
		assertEquals(0, redisService.hLen("Hash"));

		Map<String, String> map = new HashMap<String, String>();
		map.put("Item1", "Value1");
		map.put("Item2", "Value2");
		map.put("Item3", "Value3");
		redisService.hmSet("Hash", map);
		assertEquals(3, redisService.hLen("Hash"));

		map = redisService.hGet("Hash", "Item2", "Item1");
		assertNotNull(map);
		assertEquals(2, map.size());
		assertNotNull(map.get("Item2"));
		assertNotNull(map.get("Item1"));

		Set<String> set = map.keySet();
		assertEquals("Item2", set.iterator().next());

		redisService.hDel("Hash", "Item1", "Item2", "Item3");
		assertEquals(0, redisService.hLen("Hash"));

		redisService.flushAll();
	}

	public RedisService getRedisService() {
		return redisService;
	}

	@Resource
	public void setRedisService(RedisService redisService) {
		this.redisService = redisService;
	}

}
