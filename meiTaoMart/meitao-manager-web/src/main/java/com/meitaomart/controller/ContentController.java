package com.meitaomart.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.meitaomart.common.pojo.EasyUIDataGridResult;
import com.meitaomart.common.pojo.EasyUITreeNode;
import com.meitaomart.common.utils.MeitaoResult;
import com.meitaomart.content.service.ContentService;
import com.meitaomart.pojo.MeitaoContent;

/**
 * 內容管理Controller
 * @author anluo
 *
 */

@Controller
public class ContentController {
	
	@Autowired
	private ContentService contentService;
	
	@RequestMapping(value="/content/save", method=RequestMethod.POST)
	@ResponseBody
	public MeitaoResult addContent(MeitaoContent content) {
		MeitaoResult meitaoResult = contentService.addContent(content);
		return meitaoResult;
	}
	
	@RequestMapping(value="/content/delete", method=RequestMethod.POST)
	@ResponseBody
	public MeitaoResult deleteContent(String ids) {
		// 調用服務把內容數據保存到數據庫
		MeitaoResult meitaoResult = contentService.deleteContents(ids);
		return meitaoResult;
	}
	
	@RequestMapping(value="/content/query/list", method=RequestMethod.GET)
	@ResponseBody
	public EasyUIDataGridResult getContentList(Long categoryId, Integer page, Integer rows) {
		// 調用服務把內容數據保存到數據庫
		EasyUIDataGridResult result = contentService.getContentList(categoryId, page, rows);
		return result;
	}
}
