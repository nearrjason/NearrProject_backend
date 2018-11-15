package com.meitaomart.service.test;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.meitaomart.common.jedis.JedisClient;
import com.meitaomart.common.jedis.JedisClientPool;
import com.meitaomart.common.pojo.ItemInfo;
import com.meitaomart.service.ItemService;
import com.meitaomart.service.impl.ItemServiceImpl;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

import java.io.IOException;
import java.util.*;

public class ItemServiceTest {

	@Test
	public void getItemList() {
		// ApplicationContext applicationContext = new
		// ClassPathXmlApplicationContext("classpath:spring/applicationContext-*.xml");
		// ItemService itemService =
		// applicationContext.getBean(ItemService.class);
		// List<ItemInfo> itemList = itemService.getItemList();
		// System.out.println(itemList);
	}

	@Test
	public void getItemListById() {
		/*
		 * ApplicationContext applicationContext = new
		 * ClassPathXmlApplicationContext(
		 * "classpath:spring/applicationContext-*.xml"); ItemService itemService
		 * = applicationContext.getBean(ItemService.class); ItemInfo itemInfo =
		 * itemService.getItemById(153714284176352L);
		 * System.out.println(itemInfo);
		 */
	}

	@Test
	public void redisTest1() {
		/*
		 * Set<HostAndPort> nodes = new HashSet<>(); nodes.add(new
		 * HostAndPort("${redis.url}", 7001)); nodes.add(new
		 * HostAndPort("${redis.url}", 7002)); nodes.add(new
		 * HostAndPort("${redis.url}", 7003)); nodes.add(new
		 * HostAndPort("${redis.url}", 7004)); nodes.add(new
		 * HostAndPort("${redis.url}", 7005)); nodes.add(new
		 * HostAndPort("${redis.url}", 7006)); JedisCluster jedisCluster = new
		 * JedisCluster(nodes);
		 * 
		 * jedisCluster.set("hello", "100"); String result =
		 * jedisCluster.get("hello"); // 第三步：打印结果 System.out.println(result); //
		 * 第四步：系统关闭前，关闭JedisCluster对象。 try { jedisCluster.close(); } catch
		 * (IOException e) { // TODO Auto-generated catch block
		 * EmailUtils.groupSendEmailForJavaException(e.getStackTrace().toString()); }
		 */
	}

	@Test
	public void redisTest2() {
		/*ApplicationContext applicationContext = new ClassPathXmlApplicationContext(
				"classpath:spring/applicationContext-redis.xml");
		JedisClient jedisClient = applicationContext.getBean(JedisClientPool.class);
		Set<HostAndPort> nodes = new HashSet<>();
		nodes.add(new HostAndPort("${redis.url}", 7001));
		nodes.add(new HostAndPort("${redis.url}", 7002));
		nodes.add(new HostAndPort("${redis.url}", 7003));
		nodes.add(new HostAndPort("${redis.url}", 7004));
		nodes.add(new HostAndPort("${redis.url}", 7005));
		nodes.add(new HostAndPort("${redis.url}", 7006));
		JedisCluster jedisCluster2 = new JedisCluster(nodes);
		try {
			jedisClient.set("hello", "101");
			String result = jedisClient.get("hello");
			// 第三步：打印结果
			System.out.println(result);
		} finally {
			try {
				jedisClient.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}*/

		// 第四步：系统关闭前，关闭JedisCluster对象。

	}

	@Test
	public void redisTest() {

		// 初始化spring容器

		/*ApplicationContext applicationContext = new ClassPathXmlApplicationContext(
				"classpath:spring/applicationContext-redis.xml");
		JedisClient jedisClient = applicationContext.getBean(JedisClient.class);
		String json = "";
		try { // json = jedisClient.hget("CONTENT_LIST", "0");
			jedisClient.set("aws_single", "awsRedis");
			String str = jedisClient.get("aws_single");
			System.out.println(str);
		} catch (Exception e) { // TODO: handle exception EmailUtils.groupSendEmailForJavaException(e.getStackTrace().toString()); }
			e.printStackTrace();

		}*/

		// 第二步：直接使用JedisCluster对象操作redis。在系统中单例存在。

	}
}
