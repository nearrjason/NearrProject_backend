package com.meitaomart.app.cart;

import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.meitaomart.cart.service.CartService;
import com.meitaomart.common.pojo.CartItem;
import com.meitaomart.common.utils.MeitaoResult;
import com.meitaomart.pojo.MeitaoUser;
import com.meitaomart.service.ItemService;

@Controller
public class Cart {
	@Autowired
	private ItemService itemService;
	@Autowired
	private CartService cartService;

	/**
	 * 添加商品到购物车，返回商品对象
	 * 
	 * @param itemId
	 * @param purchaseQuantity
	 * @param callback
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/cart/add/{itemId}")
	@ResponseBody
	public MeitaoResult addCart(@PathVariable Long itemId, @RequestParam(defaultValue = "1") Integer purchaseQuantity,
			HttpServletRequest request, HttpServletResponse response) {
		if (itemId == null) {
			return MeitaoResult.build(400, "item id 为 null");
		}
		// 判断用户是否登录
		MeitaoUser user = (MeitaoUser) request.getAttribute("user");
		if (user != null) {
			cartService.addCart(user.getId(), itemId, purchaseQuantity);
			return showCartList(request, response);
		}
		return MeitaoResult.build(400, "无法检测到用户");
	}

	// 返回购物车列表
	@RequestMapping("/cart/cartItemList")
	@ResponseBody
	public MeitaoResult showCartList(HttpServletRequest request, HttpServletResponse response) {
		MeitaoUser user = (MeitaoUser) request.getAttribute("user");
		if (user == null) {
			return MeitaoResult.build(400, "无法检测到用户");
		}

		List<CartItem> list = cartService.getCartList(user.getId(), false);
		List<CartItem> effectiveList = new ArrayList<>();
		List<CartItem> ineffectiveList = new ArrayList<>();

		Long subtotal = 0L;
		for (CartItem cartItem : list) {
			if (cartItem != null) {
				if (!Byte.valueOf((byte) 1).equals(cartItem.getStatus())
						|| Integer.valueOf(0).equals(cartItem.getStockNumber())) {
					ineffectiveList.add(cartItem);
				} else if (cartItem.getSalePrice() != null && cartItem.getDiscount() != null){
					effectiveList.add(cartItem);
					Long realPrice = (cartItem.getSalePrice() * (100 - cartItem.getDiscount()) + 50) / 100;
					subtotal += realPrice * cartItem.getPurchaseQuantity();
				}
			}
		}

		Map<String, Object> map = new HashMap<>();
		map.put("effectiveList", effectiveList);
		map.put("ineffectiveList", ineffectiveList);
		map.put("subtotal", subtotal);
		map.put("limitMoneyForFreeeMail", 49);

		return MeitaoResult.ok(map);
	}

	@RequestMapping("/cart/delete/{itemId}")
	@ResponseBody
	public MeitaoResult deleteCartItem(@PathVariable Long itemId, HttpServletRequest request,
			HttpServletResponse response) {
		if (itemId == null) {
			return MeitaoResult.build(400, "item id 为 null");
		}
		MeitaoUser user = (MeitaoUser) request.getAttribute("user");
		if (user != null) {
			cartService.deleteCartItem(user.getId(), itemId);
			return showCartList(request, response);
		}

		return MeitaoResult.build(400, "无法检测到用户");
	}

	@RequestMapping("/cart/clear/ineffective")
	@ResponseBody
	public MeitaoResult clearCartIneffective(HttpServletRequest request, HttpServletResponse response) {
		MeitaoUser user = (MeitaoUser) request.getAttribute("user");
		if (user == null) {
			return MeitaoResult.build(400, "无法检测到用户");
		}
		
		List<CartItem> list = cartService.getCartList(user.getId(), false);

		for (CartItem cartItem : list) {
			if (cartItem != null) {
				if (!Byte.valueOf((byte) 1).equals(cartItem.getStatus())
						|| Integer.valueOf(0).equals(cartItem.getStockNumber())) {
					Long itemId = cartItem.getId();
					cartService.deleteCartItem(user.getId(), itemId);
				} 
			}
		}
		
		return showCartList(request, response);
	}

	@RequestMapping("/cart/clear")
	@ResponseBody
	public MeitaoResult clearCart(HttpServletRequest request, HttpServletResponse response) {
		MeitaoUser user = (MeitaoUser) request.getAttribute("user");
		if (user != null) {
			cartService.clearCartItem(user.getId());
			return showCartList(request, response);
		}

		return MeitaoResult.build(400, "无法检测到用户");
	}

	@RequestMapping("/cart/update/purchaseQuantity/{itemId}/{purchaseQuantity}")
	@ResponseBody
	public MeitaoResult updateCartPurchaseQuantity(@PathVariable Long itemId, @PathVariable Integer purchaseQuantity,
			HttpServletRequest request, HttpServletResponse response) {
		// 判断用户是否为登录状态
		MeitaoUser user = (MeitaoUser) request.getAttribute("user");
		if (user != null) {
			cartService.updateCartPurchaseQuantity(user.getId(), itemId, purchaseQuantity);
			return showCartList(request, response);
		}
		return MeitaoResult.build(400, "无法检测到用户");
	}
}
