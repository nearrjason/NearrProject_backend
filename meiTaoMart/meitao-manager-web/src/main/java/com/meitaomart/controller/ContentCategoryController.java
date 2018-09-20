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
import com.meitaomart.content.service.ContentCategoryService;

/**
 * 内容分类管理controller
 * @author anluo
 *
 */

@Controller
public class ContentCategoryController {
	@Autowired
	private ContentCategoryService contentCategoryService;
	
	@RequestMapping("/content/category/list")
	@ResponseBody
	public List<EasyUITreeNode> getContentCatList(@RequestParam(name="id", defaultValue="0")Long parentId) {
		List<EasyUITreeNode> list = contentCategoryService.getContentCatList(parentId);
		return list;
	}
	
	/**
	 * 添加分类节点
	 */
	@RequestMapping(value="/content/category/create", method=RequestMethod.POST)
	@ResponseBody
	public MeitaoResult createContentCategory(Long parentId, String name) {
		MeitaoResult meitaoResult = contentCategoryService.addContentCategory(parentId, name);
		return meitaoResult;
	}
	
	/**
	 * 重命名
	 */
	@RequestMapping(value="/content/category/update", method=RequestMethod.POST)
	@ResponseBody
	public MeitaoResult updateContentCategory(Long id, String name) {
		//調用服務添加節點
		MeitaoResult meitaoResult = contentCategoryService.updateContentCategory(id, name);
		return meitaoResult;
	}
	
	/**
	 * 删除分类节点
	 */
	@RequestMapping(value="/content/category/delete", method=RequestMethod.POST)
	@ResponseBody
	public MeitaoResult deleteContentCategory(Long id) {
		//調用服務添加節點
		MeitaoResult meitaoResult = contentCategoryService.deleteContentCategory(id);
		return meitaoResult;
	}
}
