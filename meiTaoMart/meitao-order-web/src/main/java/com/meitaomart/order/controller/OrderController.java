package com.meitaomart.order.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.meitaomart.cart.service.CartService;
import com.meitaomart.common.pojo.CartItem;
import com.meitaomart.common.utils.MeitaoResult;
import com.meitaomart.order.pojo.OrderInfo;
import com.meitaomart.order.service.OrderService;
import com.meitaomart.pojo.MeitaoAddress;
import com.meitaomart.pojo.MeitaoItem;
import com.meitaomart.pojo.MeitaoUser;

/**
 * 订单管理Controller
 * @version 1.0
 */
@Controller
public class OrderController {
	
	@Autowired
	private CartService cartService;
	@Autowired
	private OrderService orderService;

	@RequestMapping("/order/order-cart")
	public String showOrderCart(HttpServletRequest request) {
		//取用户id
		MeitaoUser meitaoUser= (MeitaoUser) request.getAttribute("user");
		//根据用户id取收货地址列表
		//使用静态数据
		//取支付方式列表
		//静态数据
		//根据用户id取购物车列表
		List<CartItem> cartItemList = cartService.getCartList(meitaoUser.getId());
		OrderInfo orderInfo = orderService.getOrderInfo(cartItemList, meitaoUser);
		List<MeitaoAddress> addressList = orderService.getAddressList(meitaoUser.getId());
		//把购物车列表传递给jsp
		request.setAttribute("cartItemList", cartItemList);
		request.setAttribute("user", meitaoUser);
		request.setAttribute("orderInfo", orderInfo);
		request.setAttribute("addressList", addressList);
		//返回页面
		return "order-cart";
	}
	
	@RequestMapping(value="/order/create", method=RequestMethod.POST)
	public String createOrder(OrderInfo orderInfo, HttpServletRequest request) {
		//取用户信息
		MeitaoUser user = (MeitaoUser) request.getAttribute("user");
		//把用户信息添加到orderInfo中。
		orderInfo.setUserId(user.getId());
		orderInfo.setUsername(user.getUsername());
		//调用服务生成订单
		MeitaoResult meitaoResult = orderService.createOrder(orderInfo);
		//如果订单生成成功，需要删除购物车
		if (meitaoResult.getStatus() == 200) {
			//清空购物车
			cartService.clearCartItem(user.getId());
		}
		//把订单号传递给页面
		request.setAttribute("orderId", meitaoResult.getData());
		request.setAttribute("payment", orderInfo.getSubtotal());
		//返回逻辑视图
		return "success";
	}
}
