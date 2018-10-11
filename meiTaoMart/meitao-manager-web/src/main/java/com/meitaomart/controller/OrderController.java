package com.meitaomart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.meitaomart.common.pojo.EasyUIDataGridResult;
import com.meitaomart.order.service.OrderService;

@Controller
public class OrderController {
	@Autowired
	private OrderService orderService;
		
	@RequestMapping("/order/list")
	@ResponseBody
	public EasyUIDataGridResult getOrderList(Integer page, Integer rows) {
		// 调用服务查询商品列表
		EasyUIDataGridResult result = orderService.getOrderList(page, rows);
		return result;
	}
	
	@RequestMapping("/orderItem/list")
	@ResponseBody
	public EasyUIDataGridResult getOrderItemList(Integer page, Integer rows) {
		// 调用服务查询商品列表
		EasyUIDataGridResult result = orderService.getOrderList(page, rows);
		return result;
	}
}
