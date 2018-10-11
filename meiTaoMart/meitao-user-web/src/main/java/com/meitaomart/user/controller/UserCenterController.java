package com.meitaomart.user.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.meitaomart.cart.service.CartService;
import com.meitaomart.common.pojo.CartItem;
import com.meitaomart.common.pojo.ItemInfo;
import com.meitaomart.common.utils.CookieUtils;
import com.meitaomart.common.utils.JsonUtils;
import com.meitaomart.order.pojo.OrderInfo;
import com.meitaomart.order.service.OrderService;
import com.meitaomart.pojo.MeitaoAddress;
import com.meitaomart.pojo.MeitaoBankingCard;
import com.meitaomart.pojo.MeitaoOrderItem;
import com.meitaomart.pojo.MeitaoUser;
import com.meitaomart.service.ItemService;
import com.meitaomart.user.service.UserService;

@Controller
public class UserCenterController {
	@Autowired
	private UserService userService;
	@Autowired
	private OrderService orderService;
	@Autowired
	private ItemService itemService;
	@Autowired
	private CartService cartService;
	
	@RequestMapping("/user/profile/orders")
	public String getOrders(HttpServletRequest request, HttpServletResponse response) {
		MeitaoUser meitaoUser = (MeitaoUser) request.getAttribute("user");
		if (meitaoUser == null) {
			return null;
		}
		
		List<OrderInfo> orderInfoList = orderService.getOrderInfoListByUserId(meitaoUser.getId());
		for (OrderInfo orderInfo : orderInfoList) {
			List<MeitaoOrderItem> orderItemList = orderInfo.getOrderItems();
			List<ItemInfo> itemInfoList = new ArrayList<>();
			for (MeitaoOrderItem orderItem : orderItemList) {
				Long itemId = orderItem.getItemId();
				ItemInfo itemInfo = itemService.getItemById(itemId);
				itemInfoList.add(itemInfo);
			}
			MeitaoAddress address = userService.getAddressByPrimaryId(orderInfo.getAddressId());
			MeitaoBankingCard card = userService.getCardByPrimaryId(orderInfo.getCardId());
			orderInfo.setOrderItemsInfo(itemInfoList);
			orderInfo.setOrderAddress(address);
			orderInfo.setOrderCard(card);
		}
		
		List<CartItem> cartItemList = getCartItemList(request, response);
		
		request.setAttribute("cartItemList", cartItemList);
		request.setAttribute("orderInfoList", orderInfoList);
		request.setAttribute("type", "order");
		return "user_center";
	}
	
	@RequestMapping("/user/profile/account")
	public String getAccount(HttpServletRequest request, HttpServletResponse response) {
		MeitaoUser user = (MeitaoUser) request.getAttribute("user");
		if (user == null) {
			return null;
		}
		
		MeitaoUser meitaoUser = userService.getUserByPrimaryId(user.getId());
		List<CartItem> cartItemList = getCartItemList(request, response);
		
		request.setAttribute("cartItemList", cartItemList);
		request.setAttribute("user", meitaoUser);
		request.setAttribute("type", "account");
		return "user_center";
	}
	
	@RequestMapping("/user/profile/addresses")
	public String getAddresses(HttpServletRequest request, HttpServletResponse response) {
		MeitaoUser meitaoUser = (MeitaoUser) request.getAttribute("user");
		if (meitaoUser == null) {
			return null;
		}
		List<MeitaoAddress> meitaoAddressList = userService.getAddressListByUserId(meitaoUser.getId());
		List<CartItem> cartItemList = getCartItemList(request, response);
		
		request.setAttribute("cartItemList", cartItemList);
		request.setAttribute("addressList", meitaoAddressList);
		request.setAttribute("type", "address");
		return "user_center";
	}
	
	@RequestMapping("/user/profile/cards")
	public String getCards(HttpServletRequest request, HttpServletResponse response) {
		MeitaoUser meitaoUser = (MeitaoUser) request.getAttribute("user");
		if (meitaoUser == null) {
			return null;
		}
		List<MeitaoBankingCard> meitaoCardList = userService.getCardListByUserId(meitaoUser.getId());
		List<CartItem> cartItemList = getCartItemList(request, response);
		
		request.setAttribute("cartItemList", cartItemList);
		request.setAttribute("cardList", meitaoCardList);
		request.setAttribute("type", "card");
		return "user_center";
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
