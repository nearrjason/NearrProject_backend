package com.meitaomart.service.test;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.meitaomart.common.pojo.ItemInfo;
import com.meitaomart.service.ItemService;
import com.meitaomart.service.impl.ItemServiceImpl;

public class ItemServiceTest {
	
	@Test
	public void getItemList() {
//		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-*.xml");
//		ItemService itemService = applicationContext.getBean(ItemService.class);
//		List<ItemInfo> itemList = itemService.getItemList();
//		System.out.println(itemList);
	}
	
	@Test
	public void getItemListById() {
		/*ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-*.xml");
		ItemService itemService = applicationContext.getBean(ItemService.class);
		ItemInfo itemInfo = itemService.getItemById(153714284176352L);
		System.out.println(itemInfo);*/
	}
}
