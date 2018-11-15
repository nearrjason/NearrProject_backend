 package com.meitaomart.app.order;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.meitaomart.app.pojo.FinalCheckoutMap;
import com.meitaomart.cart.service.CartService;
import com.meitaomart.common.pojo.CartItem;
import com.meitaomart.common.utils.JsonUtils;
import com.meitaomart.common.utils.MeitaoResult;
import com.meitaomart.order.pojo.OrderInfo;
import com.meitaomart.order.service.OrderService;
import com.meitaomart.pojo.MeitaoAddress;
import com.meitaomart.pojo.MeitaoBankingCard;
import com.meitaomart.pojo.MeitaoOrderItem;
import com.meitaomart.pojo.MeitaoUser;
import com.meitaomart.user.service.UserService;

@Controller
public class OrderCheckoutPage {
	@Autowired
	private CartService cartService;
	@Autowired
	private OrderService orderService;
	@Autowired
	private UserService userService;
	
	@RequestMapping("/order/checkoutPage")
	@ResponseBody
	public MeitaoResult showCheckoutPage(HttpServletRequest request) {
		MeitaoUser user = (MeitaoUser) request.getAttribute("user");
		if (user == null) {
			return MeitaoResult.build(400, "无法检测到用户");
		}
		List<CartItem> cartItemList = cartService.getCartList(user.getId(), true);
		List<MeitaoAddress> addressList = userService.getAddressListByUserId(user.getId());
		MeitaoAddress primaryAddress = addressList != null && addressList.size() > 0 ? addressList.get(0) : null;
		OrderInfo orderInfo = orderService.getOrderInfo(cartItemList, user, primaryAddress);
		
		List<MeitaoBankingCard> cardList = userService.getCardListByUserId(user.getId());
		MeitaoBankingCard primaryCard = cardList != null && cardList.size() > 0 ? cardList.get(0) : null;
		
		List<Object> shippingMethodList = new ArrayList<>();
		
		Map<String, Object> shippingMethod1 = new HashMap<>();
		shippingMethod1.put("title", "平邮");
		shippingMethod1.put("money", 5.99);
		shippingMethod1.put("id", 1);
		shippingMethodList.add(shippingMethod1);
		
		Map<String, Object> shippingMethod2 = new HashMap<>();
		shippingMethod2.put("title", "急邮");
		shippingMethod2.put("money", 15.99);
		shippingMethod2.put("id", 2);
		shippingMethodList.add(shippingMethod2);
		
		Map<String, Object> result = new HashMap<>();
		
		result.put("taxFee", orderInfo != null ? orderInfo.getTaxFee() : null);
		result.put("primaryAddress", primaryAddress);
		result.put("primaryCard", primaryCard);
		result.put("shippingMethodList", shippingMethodList);
		result.put("defaultShippingMethodId", 1);
		result.put("shippingFee", orderInfo != null ? orderInfo.getShippingFee() : null);
		result.put("limitMoneyForFreeeMail", 49);
		
		return MeitaoResult.ok(result);
	}
	
	@RequestMapping("/order/checkoutPage/cartItemList")
	@ResponseBody
	public MeitaoResult showCheckoutPageCartItemList(HttpServletRequest request) {
		MeitaoUser user = (MeitaoUser) request.getAttribute("user");
        return user != null ? MeitaoResult.ok(cartService.getCartList(user.getId(), true)) : MeitaoResult.build(400, "无法检测到用户");
	}
	
	/**
	 * 结算中心页面中获取用户的地址列表
	 * @param request
	 * @return status code, message, 地址列表结果
	 */
	@RequestMapping("/order/checkoutPage/addressList")
	@ResponseBody
	public MeitaoResult showCheckoutPageAddressList(HttpServletRequest request) {
		MeitaoUser user = (MeitaoUser) request.getAttribute("user");
		return user != null ? MeitaoResult.ok(userService.getAddressListByUserId(user.getId())) : MeitaoResult.build(400, "无法检测到用户");
	}
	
	/**
	 * 结算中心页面获取默认地址
	 * @param request
	 * @return
	 */
	@RequestMapping("/order/checkoutPage/primaryAddress")
	@ResponseBody
	public MeitaoResult showCheckoutPagePrimaryAddress(HttpServletRequest request) {
		MeitaoUser user = (MeitaoUser) request.getAttribute("user");
		if (user == null) {
			return MeitaoResult.build(400, "无法检测到用户");
		}
		List<MeitaoAddress> addressList = userService.getAddressListByUserId(user.getId());
		return MeitaoResult.ok(addressList != null && addressList.size() > 0 ? addressList.get(0) : null);
	}
	
	/**
	 * 结算中心获取银行卡列表
	 * @param request
	 * @return
	 */
	@RequestMapping("/order/checkoutPage/cardList")
	@ResponseBody
	public MeitaoResult showCheckoutPageCardList(HttpServletRequest request) {
		MeitaoUser user = (MeitaoUser) request.getAttribute("user");
		return user != null ? MeitaoResult.ok(userService.getCardListByUserId(user.getId())) : MeitaoResult.build(400, "无法检测到用户");
	}
	
	/**
	 * 结算中心页面获取默认银行卡
	 * @param request
	 * @return
	 */
	@RequestMapping("/order/checkoutPage/primaryCard")
	@ResponseBody
	public MeitaoResult showCheckoutPagePrimaryCard(HttpServletRequest request) {
		MeitaoUser user = (MeitaoUser) request.getAttribute("user");
		List<MeitaoBankingCard> cardList = userService.getCardListByUserId(user.getId());
		return MeitaoResult.ok(cardList != null && cardList.size() > 0 ? cardList.get(0) : null);
	}
	
	/**
	 * 结算中心获取订单详情
	 * @param request
	 * @return
	 */
	@RequestMapping("/order/checkoutPage/orderInfo")
	@ResponseBody
	public MeitaoResult showCheckoutPageOrderInfo(HttpServletRequest request) {
		MeitaoUser user = (MeitaoUser) request.getAttribute("user");
		if (user == null) {
			return MeitaoResult.build(400, "无法检测到用户");
		}
		List<CartItem> cartItemList = cartService.getCartList(user.getId(), true);
		List<MeitaoAddress> addressList = userService.getAddressListByUserId(user.getId());
		MeitaoAddress primaryAddress = addressList != null && addressList.size() > 0 ? addressList.get(0) : null;
		OrderInfo orderInfo = orderService.getOrderInfo(cartItemList, user, primaryAddress);
		return MeitaoResult.ok(orderInfo);
	}
	
	/**
	 * 结算中心选择地址时获取地址列表
	 * @param request
	 * @return
	 */
	@RequestMapping("/order/checkoutPage/selectAddress/addressList")
	@ResponseBody
	public MeitaoResult showCheckoutPageSelectAddressAddressList(HttpServletRequest request) {
		MeitaoUser user = (MeitaoUser) request.getAttribute("user");
		return user != null ? MeitaoResult.ok(userService.getAddressListByUserId(user.getId())) : MeitaoResult.build(400, "无法检测到用户");
	}
	
	/**
	 * 结算中心选择地址时获取默认地址
	 * @param addressId
	 * @param request
	 * @return
	 */
	@RequestMapping("/order/checkoutPage/selectAddress/primaryAddress")
	@ResponseBody
	public MeitaoResult showCheckoutPageSelectAddressPrimaryAddress(Long addressId, HttpServletRequest request) {
		MeitaoUser user = (MeitaoUser) request.getAttribute("user");
		return user != null ? MeitaoResult.ok(userService.getAddressByPrimaryId(addressId)) : MeitaoResult.build(400, "无法检测到用户"); 
		
	}
	
	/**
	 * 结算中心选择银行卡时获取银行卡列表
	 * @param request
	 * @return
	 */
	@RequestMapping("/order/checkoutPage/selectCard/cardList")
	@ResponseBody
	public MeitaoResult showCheckoutPageSelectCardCardList(HttpServletRequest request) {
		MeitaoUser user = (MeitaoUser) request.getAttribute("user");
		return user != null ? MeitaoResult.ok(userService.getCardListByUserId(user.getId())) : MeitaoResult.build(400, "无法检测到用户"); 
	}
	
	/**
	 * 结算中心选择银行卡时获取默认银行卡
	 * @param cardId
	 * @param request
	 * @return
	 */
	@RequestMapping("/order/checkoutPage/selectCard/primaryCard")
	@ResponseBody
	public MeitaoResult showCheckoutPageSelectCardPrimaryCard(Long cardId, HttpServletRequest request) {
		MeitaoUser user = (MeitaoUser) request.getAttribute("user");
		return user != null ? MeitaoResult.ok(userService.getCardByPrimaryId(cardId)) : MeitaoResult.build(400, "无法检测到用户"); 
	}
	
	/**
	 * 选择地址，选择配送方式时都会调用的方法，返回更新后的primary address和final 订单价格信息
	 * @param request
	 * @param primaryAddressId
	 * @param isExpressShipping
	 * @return
	 */
	@RequestMapping(value = "/order/checkoutPage/updateOrderInfo")
	@ResponseBody
	public MeitaoResult updateOrderInfo(HttpServletRequest request, Long primaryAddressId, int shippingMethod) {
		Map<String, Object> updatedOrderInfoMap = new HashMap<>();
		if (primaryAddressId != null) {
			MeitaoUser user = (MeitaoUser) request.getAttribute("user");
			if (user == null) {
				return MeitaoResult.build(400, "无法检测到用户");
			}
			List<CartItem> cartItemList = cartService.getCartList(user.getId(), true);
			MeitaoAddress primaryAddress = userService.getAddressByPrimaryId(primaryAddressId);
			if (primaryAddress != null) {
				Boolean isExpressShipping = shippingMethod == 2 ? true : false;
				OrderInfo orderInfo = orderService.getOrderInfo(cartItemList, user, primaryAddress, isExpressShipping);
				updatedOrderInfoMap.put("orderInfo", orderInfo);
				updatedOrderInfoMap.put("primaryAddress", primaryAddress);
				
			}
		}
		
		return MeitaoResult.ok(updatedOrderInfoMap);
	}
	
	/**
	 * 结算中心新增地址保存
	 * @param meitaoAddress
	 * (参数详情: 
	 * 		firstName,
	 * 		lastName,
	 * 		shippingPhone,
	 * 	 	street,
	 * 		city,
	 * 		state,
	 * 		zipcode
	 * )
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/order/checkoutPage/saveAddress", method = RequestMethod.POST)
	@ResponseBody
	public MeitaoResult saveAddress(MeitaoAddress meitaoAddress, HttpServletRequest request) {
		MeitaoUser user = (MeitaoUser) request.getAttribute("user");
		if (user == null) {
			return MeitaoResult.build(400, "无法检测到用户");
		}
		MeitaoResult meitaoResult = userService.addNewAddress(meitaoAddress, user.getId());
		return meitaoResult;
	}

	
	/**
	 * 结算中心增加银行卡保存
	 * @param meitaoBankingCard
	 	(参数详情: 
	 * 		firstName,
	 * 		lastName,
	 * 		cardNumber,
	 * 	 	year,
	 * 		month
	 * )
	 * @param cvvNumber
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/order/checkoutPage/saveCard", method = RequestMethod.POST)
	@ResponseBody
	public MeitaoResult saveCard(MeitaoBankingCard meitaoBankingCard, String cvvNumber, HttpServletRequest request) {
		MeitaoUser user = (MeitaoUser) request.getAttribute("user");
		if (user == null) {
			return MeitaoResult.build(400, "无法检测到用户");
		}
		MeitaoResult meitaoResult = userService.addNewCard(meitaoBankingCard, user.getId());
		return meitaoResult;
	}
	
	@RequestMapping(value = "/order/finalPayment", method = RequestMethod.POST)
	@ResponseBody
	public MeitaoResult finalPayment(FinalCheckoutMap map, HttpServletRequest request) {
		MeitaoUser user = (MeitaoUser) request.getAttribute("user");
		
		Long addressId = map.getAddressId();
		Long cardId = map.getCardId();
		String cvv = map.getCvv();
		OrderInfo orderInfoObj = JsonUtils.jsonToPojo(map.getOrderInfo(), OrderInfo.class);
		List<MeitaoOrderItem> orderItemListObj = JsonUtils.jsonToList(map.getOrderItemList(), MeitaoOrderItem.class);
		
		MeitaoResult meitaoResult = orderService.goToPay(user, addressId, cardId, orderInfoObj, orderItemListObj, cvv);
		if (meitaoResult.getStatus() == 200) {
			// 清空购物车
			cartService.clearCartItem(user.getId());
		}
		return meitaoResult;
	}
	
	@RequestMapping(value = "/order/checkoutPage/shippingMethod", method = RequestMethod.POST)
	@ResponseBody
	public MeitaoResult getShippingMethodPrice(HttpServletRequest request) {
		Map<String, Double> map = new HashMap<>();
		map.put("normal", 5.99);
		map.put("express", 15.99);
		return MeitaoResult.ok(map);
	}
}