package com.meitaomart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.meitaomart.common.pojo.EasyUIDataGridResult;
import com.meitaomart.common.utils.MeitaoResult;
import com.meitaomart.pojo.MeitaoItem;
import com.meitaomart.pojo.MeitaoItemPrice;
import com.meitaomart.pojo.MeitaoItem;
import com.meitaomart.service.ItemService;

/**
 * 商品管理Controller
 * <p>
 * Title: ItemController
 * </p>
 * <p>
 * Description:
 * </p>
 * 
 * @version 1.0
 */

@Controller
public class ItemController {
	@Autowired
	private ItemService itemService;

	/*
	@RequestMapping("/item/{itemId}")
	@ResponseBody
	public MeitaoItem getItemById(@PathVariable Long itemId) {

		MeitaoItem item = itemService.getItemById(itemId);
		return item;
	}
	*/

	@RequestMapping("/item/list")
	@ResponseBody
	public EasyUIDataGridResult getItemList(Integer page, Integer rows) {
		// 调用服务查询商品列表
		EasyUIDataGridResult result = itemService.getItemList(page, rows);
		return result;
	}

	/**
	 * 商品添加功能
	 */
	@RequestMapping(value = "/item/save", method = RequestMethod.POST)
	@ResponseBody
	public MeitaoResult addItem(MeitaoItem item, MeitaoItemPrice itemPrices, String desc) {
		MeitaoResult result = itemService.addItem(item, itemPrices, desc);
		return result;
	}

	/**
	 * 商品编辑功能
	 */
	@RequestMapping(value = "/rest/page/item-edit", method = RequestMethod.GET)
	@ResponseBody
	public MeitaoResult updateItem(MeitaoItem item) {
		System.out.println(item);
		// MeitaoResult result = itemService.addItem(item, desc);
		return null;
	}

	/**
	 * 商品删除
	 */
	@RequestMapping(value = "/rest/item/delete", method = RequestMethod.POST)
	@ResponseBody
	public MeitaoResult deleteItem(String ids) {
		MeitaoResult result = itemService.deleteItems(ids);
		return result;
	}
}