 package com.meitaomart.item.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.meitaomart.cart.service.CartService;
import com.meitaomart.common.pojo.CartItem;
import com.meitaomart.common.pojo.ItemInfo;
import com.meitaomart.common.utils.CookieUtils;
import com.meitaomart.common.utils.JsonUtils;
import com.meitaomart.pojo.MeitaoItem;
import com.meitaomart.pojo.MeitaoItemDesc;
import com.meitaomart.pojo.MeitaoUser;
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
	@Autowired
	private CartService cartService;
	
	@RequestMapping("/item/{itemId}")
	public String showItemInfo(@PathVariable Long itemId, Model model, HttpServletRequest request, HttpServletResponse response) {
		// 调用服务获取商品基本信息
		if (itemId == null) {
			return "item_meitao";
		}
		ItemInfo itemInfo = itemService.getItemById(itemId);
		List<CartItem> cartItemList = getCartItemList(request, response);
		// 取商品描述信息
		// 把信息传递给页面
		model.addAttribute("cartItemList", cartItemList);
		model.addAttribute("item", itemInfo);
		return "item_meitao";
	}
	
	@RequestMapping("/refresh/cart")
	public String refreshCart(HttpServletRequest request, HttpServletResponse response) {
		List<CartItem> cartItemList = getCartListFromCookie(request);
		// 判断用户是否为登录状态
		MeitaoUser user = (MeitaoUser) request.getAttribute("user");
		// 如果是登录状态
		if (user != null) {
			// 从cookie中取购物车列表
			// 如果不为空，把cookie中的购物车商品和服务端的购物车商品合并。
			cartService.mergeCart(user.getId(), cartItemList);
			// 把cookie中的购物车删除
			CookieUtils.deleteCookie(request, response, "cart");
			// 从服务端取购物车列表
			cartItemList = cartService.getCartList(user.getId(), false);

		}
		// 把列表传递给页面
		request.setAttribute("cartItemList", cartItemList);
		return "commons/header";
	}
	
	private List<CartItem> getCartItemList(HttpServletRequest request, HttpServletResponse response) {
		List<CartItem> cartItemList = getCartListFromCookie(request);
		// 判断用户是否为登录状态
		MeitaoUser user = (MeitaoUser) request.getAttribute("user");
		// 如果是登录状态
		if (user != null) {
			// 从cookie中取购物车列表
			// 如果不为空，把cookie中的购物车商品和服务端的购物车商品合并。
			cartService.mergeCart(user.getId(), cartItemList);
			// 把cookie中的购物车删除
			CookieUtils.deleteCookie(request, response, "cart");
			// 从服务端取购物车列表
			cartItemList = cartService.getCartList(user.getId(), false);

		}
		
		return cartItemList;
	}
	
	private List<CartItem> getCartListFromCookie(HttpServletRequest request) {
		String json = CookieUtils.getCookieValue(request, "cart", true);
		// 判断json是否为空
		if (StringUtils.isBlank(json)) {
			return new ArrayList<>();
		}
		// 把json转换成商品列表
		List<CartItem> list = JsonUtils.jsonToList(json, CartItem.class);
		return list;
	}
}
