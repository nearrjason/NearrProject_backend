package com.meitaomart.cart.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.meitaomart.cart.service.CartService;
import com.meitaomart.common.builder.CartItemBuilder;
import com.meitaomart.common.pojo.CartItem;
import com.meitaomart.common.pojo.ItemInfo;
import com.meitaomart.common.utils.CookieUtils;
import com.meitaomart.common.utils.MeitaoResult;
import com.meitaomart.common.utils.JsonUtils;
import com.meitaomart.pojo.MeitaoItem;
import com.meitaomart.pojo.MeitaoUser;
import com.meitaomart.service.ItemService;

/**
 * 购物车处理Controller
 */
@Controller
public class CartController {

	@Value("${COOKIE_CART_EXPIRE}")
	private Integer COOKIE_CART_EXPIRE;

	@Autowired
	private ItemService itemService;
	@Autowired
	private CartService cartService;
	
	@RequestMapping("/cart/add/{itemId}")
	@ResponseBody
	public Object addCart(@PathVariable Long itemId, @RequestParam(defaultValue = "1") Integer purchaseQuantity,
			String callback, HttpServletRequest request, HttpServletResponse response) {
		// 判断用户是否登录
		if (itemId == null) {
			return MeitaoResult.build(403, "item id 为 null");
		}
		
		MeitaoUser user = (MeitaoUser) request.getAttribute("user");
		MeitaoResult result = MeitaoResult.ok();
		// 如果是登录状态，把购物车写入redis
		if (user != null) {
			// 保存到服务端
			result = cartService.addCart(user.getId(), itemId, purchaseQuantity);
			if (StringUtils.isNotBlank(callback)) {
				//把结果封装成一个js语句响应
				MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(result);
				mappingJacksonValue.setJsonpFunction(callback);
				return mappingJacksonValue;
			}
			// 返回逻辑视图
			return result;
		}
		
		String cartToken = CookieUtils.getCookieValue(request, "cart", true);
		if (StringUtils.isBlank(cartToken)) {
			cartToken = "cart_" + UUID.randomUUID().toString();
			CookieUtils.setCookie(request, response, "cart", cartToken, COOKIE_CART_EXPIRE, true);
		}
		
		result = cartService.addCartByToken(cartToken, itemId, purchaseQuantity);
		
		// 返回添加成功页面
		if (StringUtils.isNotBlank(callback)) {
			//把结果封装成一个js语句响应
			MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(result);
			mappingJacksonValue.setJsonpFunction(callback);
			return mappingJacksonValue;
		}
		return result;
	}

	private List<CartItem> getCartListByCartToken(HttpServletRequest request) {
		String cartToken = CookieUtils.getCookieValue(request, "cart", true);
		// 判断json是否为空
		if (StringUtils.isBlank(cartToken)) {
			return new ArrayList<>();
		}
		// 把json转换成商品列表
		List<CartItem> list = cartService.getCartListByToken(cartToken, false);
		return list;
	}

	@RequestMapping("/cart/cart")
	public String showCatList(HttpServletRequest request, HttpServletResponse response) {
		// 从cookie中取购物车列表
		List<CartItem> cartItemList = getCartListByCartToken(request);
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
		// 返回逻辑视图
		return "cart";
	}

	/**
	 * 更新购物车商品数量
	 */
	@RequestMapping("/cart/update/purchaseQuantity/{itemId}/{purchaseQuantity}")
	@ResponseBody
	public MeitaoResult updateCartPurchaseQuantity(@PathVariable Long itemId, @PathVariable Integer purchaseQuantity, HttpServletRequest request,
			HttpServletResponse response) {
		// 判断用户是否为登录状态
		MeitaoUser user = (MeitaoUser) request.getAttribute("user");
		if (user != null) {
			cartService.updateCartPurchaseQuantity(user.getId(), itemId, purchaseQuantity);
			return MeitaoResult.ok();
		}
		
		String cartToken = CookieUtils.getCookieValue(request, "cart", true);
		if (StringUtils.isBlank(cartToken)) {
			return MeitaoResult.ok();
		}
		return cartService.updateCartPurchaseQuantityByToken(cartToken, itemId, purchaseQuantity);
	}

	/**
	 * 删除购物车商品
	 */
	@RequestMapping("/cart/delete/{itemId}")
	public String deleteCartItem(@PathVariable Long itemId, HttpServletRequest request, HttpServletResponse response) {
		MeitaoUser user = (MeitaoUser) request.getAttribute("user");
		// 从cookie中取购物车列表
		if (user != null) {
			cartService.deleteCartItem(user.getId(), itemId);
		} else {
			String cartToken = CookieUtils.getCookieValue(request, "cart", true);
			if (!StringUtils.isBlank(cartToken)) {
				cartService.deleteCartItemByToken(cartToken, itemId);
			}
		}
		// 返回逻辑视图
		return "redirect:/cart/cart.html";
	}

	/**
	 * 删除购物车商品
	 */
	@RequestMapping("/cart/delete/multiple/{itemIds}")
	public String deleteCartItems(@PathVariable String itemIds, HttpServletRequest request,
			HttpServletResponse response) {
		
		return "redirect:/cart/cart.html";
	}
	
	@RequestMapping("/refresh/cart")
	public String refreshCart(HttpServletRequest request, HttpServletResponse response) {
		List<CartItem> cartItemList = getCartListByCartToken(request);
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
}
