package com.meitaomart.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.meitaomart.common.pojo.EasyUITreeNode;
import com.meitaomart.common.utils.MeitaoResult;
import com.meitaomart.service.ItemCategoryService;

/**
 * 商品分类管理Controller
 * @author anluo
 *
 */

@Controller
public class ItemCategoryController {
	@Autowired
	private ItemCategoryService itemCategoryService;
	
	@RequestMapping("/item/category/list")
	@ResponseBody
	public List<EasyUITreeNode> getItemCategoryList(
			@RequestParam(name="id", defaultValue="0") Long parentId) {
		// 调用服务查询节点列表
		List<EasyUITreeNode> list = itemCategoryService.getItemCategoryListEasyUI(parentId);
		return list;
	}
	
	/**
	 * 添加分类节点
	 */
	@RequestMapping(value="/item/category/create", method=RequestMethod.POST)
	@ResponseBody
	public MeitaoResult createItemCategory(Long parentId, String name) {
		MeitaoResult meitaoResult = itemCategoryService.addItemCategory(parentId, name);
		return meitaoResult;
	}
	
	/**
	 * 重命名
	 */
	@RequestMapping(value="/item/category/update", method=RequestMethod.POST)
	@ResponseBody
	public MeitaoResult updateItemCategory(Long id, String name) {
		//調用服務添加節點
		MeitaoResult meitaoResult = itemCategoryService.updateItemCategory(id, name);
		return meitaoResult;
	}
	
	/**
	 * 删除分类节点
	 */
	@RequestMapping(value="/item/category/delete", method=RequestMethod.POST)
	@ResponseBody
	public MeitaoResult deleteItemCategory(Long id) {
		//調用服務添加節點
		MeitaoResult meitaoResult = itemCategoryService.deleteItemCategory(id);
		return meitaoResult;
	}
}
