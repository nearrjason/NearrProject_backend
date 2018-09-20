package com.meitaomart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.meitaomart.common.utils.MeitaoResult;
import com.meitaomart.search.service.SearchItemService;

// 导入商品数据到索引库
@Controller
public class SearchItemController {

	@Autowired
	private SearchItemService searchItemService;

	@RequestMapping(value="/index/item/import", method=RequestMethod.POST)
	@ResponseBody
	public MeitaoResult importItemList() {
		MeitaoResult meitaoResult = searchItemService.importAllItems();
		return meitaoResult;
	}
}
