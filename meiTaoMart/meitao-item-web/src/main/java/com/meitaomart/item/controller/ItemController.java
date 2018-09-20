 package com.meitaomart.item.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.meitaomart.common.pojo.ItemInfo;
import com.meitaomart.pojo.MeitaoItem;
import com.meitaomart.pojo.MeitaoItemDesc;
import com.meitaomart.service.ItemService;

/**
 * 商品详情页面展示Controller
 * @author anluo
 *
 */
@Controller
public class ItemController {
	@Autowired
	private ItemService itemService;
	
	@RequestMapping("/item/{itemId}")
	public String showItemInfo(@PathVariable Long itemId, Model model) {
		// 调用服务获取商品基本信息
		ItemInfo itemInfo = itemService.getItemById(itemId);
		// 取商品描述信息
		// 把信息传递给页面
		model.addAttribute("item", itemInfo);
		return "item";
	}
}
