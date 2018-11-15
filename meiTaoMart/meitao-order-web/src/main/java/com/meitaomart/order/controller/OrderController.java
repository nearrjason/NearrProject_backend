package com.meitaomart.order.controller;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.meitaomart.cart.service.CartService;
import com.meitaomart.common.pojo.CartItem;
import com.meitaomart.common.utils.EmailUtils;
import com.meitaomart.common.utils.JsonUtils;
import com.meitaomart.common.utils.MeitaoResult;
import com.meitaomart.order.pojo.FinalCheckoutMap;
import com.meitaomart.order.pojo.OrderInfo;
import com.meitaomart.order.service.OrderService;
import com.meitaomart.pojo.MeitaoAddress;
import com.meitaomart.pojo.MeitaoBankingCard;
import com.meitaomart.pojo.MeitaoOrderItem;
import com.meitaomart.pojo.MeitaoUser;
import com.meitaomart.search.service.SearchItemService;
import com.meitaomart.user.service.UserService;

/**
 * 订单管理Controller
 * 
 * @version 1.0
 */
@Controller
public class OrderController {

	@Autowired
	private CartService cartService;
	@Autowired
	private OrderService orderService;
	@Autowired
	private UserService userService;
	@Autowired
	private SearchItemService searchItemService;

	@RequestMapping("/order/checkout_page")
	public String showCheckoutPage(HttpServletRequest request) {
		MeitaoUser meitaoUser = (MeitaoUser) request.getAttribute("user");
		List<CartItem> cartItemList = cartService.getCartList(meitaoUser.getId(), true);
		
		List<MeitaoAddress> addressList = userService.getAddressListByUserId(meitaoUser.getId());
		List<MeitaoBankingCard> cardList = userService.getCardListByUserId(meitaoUser.getId());
		
		request.setAttribute("cartItemList", cartItemList);
		request.setAttribute("user", meitaoUser);
		
		MeitaoAddress primaryAddress = null;
		if (addressList != null) {
			request.setAttribute("addressList", addressList);
			if (addressList.size() > 0) {
				primaryAddress = addressList.get(0);
				request.setAttribute("primaryAddress", primaryAddress);
			}
		}
		if (cardList != null) {
			request.setAttribute("cardList", cardList);
			if (cardList.size() > 0) {
				request.setAttribute("primaryCard", cardList.get(0));
			}
		}
		// 返回页面
		
		OrderInfo orderInfo = orderService.getOrderInfo(cartItemList, meitaoUser, primaryAddress);
		request.setAttribute("orderInfo", orderInfo);
		return "order_checkout";
	}

	@RequestMapping(value = "/order/final_payment", method = RequestMethod.POST)
	@ResponseBody
	public MeitaoResult finalPayment(FinalCheckoutMap map, HttpServletRequest request) {
		MeitaoUser user = (MeitaoUser) request.getAttribute("user");
		MeitaoAddress shippingAddress = JsonUtils.jsonToPojo(map.getShippingAddress(), MeitaoAddress.class);
		MeitaoBankingCard card = JsonUtils.jsonToPojo(map.getCard(), MeitaoBankingCard.class);
		OrderInfo orderInfo = JsonUtils.jsonToPojo(map.getOrderInfo(), OrderInfo.class);
		MeitaoAddress billingAddress = JsonUtils.jsonToPojo(map.getBillingAddress(), MeitaoAddress.class);
		List<MeitaoOrderItem> orderItemList = JsonUtils.jsonToList(map.getOrderItems(), MeitaoOrderItem.class);
		String cvv = map.getCvv();
		MeitaoResult meitaoResult = orderService.goToPay(user, shippingAddress, billingAddress, card, orderInfo, orderItemList, cvv);
		if (meitaoResult.getStatus() == 200) {
			// 清空购物车
			try {
				cartService.clearCartItem(user.getId());
				Object data = meitaoResult.getData();
				if (data instanceof List<?>) {
					@SuppressWarnings("unchecked")
					List<Long> itemIdList = (List<Long>)data;
					searchItemService.updatePartialItems(itemIdList);
				}
			} catch(Exception e) {
				StringWriter sw = new StringWriter();
				PrintWriter pw = new PrintWriter(sw);
				e.printStackTrace(pw);
				
				String subject = "系统出现异常";
				String body = sw.toString();
				EmailUtils.groupSendEmail(subject, body);
			}
			
		}
		return meitaoResult;
	}

	@RequestMapping(value = "/order/checkout_page/save_address", method = RequestMethod.POST)
	@ResponseBody
	public MeitaoResult saveAddress(MeitaoAddress meitaoAddress, HttpServletRequest request) {
		MeitaoUser meitaoUser = (MeitaoUser) request.getAttribute("user");
		if (meitaoAddress == null || meitaoUser == null) {
			return null;
		}
		MeitaoResult meitaoResult = userService.addNewAddress(meitaoAddress, meitaoUser.getId());
		return meitaoResult;
	}

	

	@RequestMapping(value = "/order/checkout_page/save_card", method = RequestMethod.POST)
	@ResponseBody
	public MeitaoResult saveCard(MeitaoBankingCard meitaoBankingCard, String cvvNumber, HttpServletRequest request) {
		MeitaoUser meitaoUser = (MeitaoUser) request.getAttribute("user");
		if (meitaoBankingCard == null || meitaoUser == null) {
			return null;
		}
		MeitaoResult meitaoResult = userService.addNewCard(meitaoBankingCard, meitaoUser.getId());
		return meitaoResult;
	}
	
	@RequestMapping("/order/checkout_page/update_address_list")
	public String updateAddressList(HttpServletRequest request) {
		MeitaoUser meitaoUser = (MeitaoUser) request.getAttribute("user");
		List<MeitaoAddress> addressList = userService.getAddressListByUserId(meitaoUser.getId());
		if (addressList != null) {
			request.setAttribute("addressList", addressList);
			if (addressList.size() > 0) {
				for (int i = addressList.size() - 1; i >= 0; i--) {
					MeitaoAddress address = addressList.get(i);
					if (address != null) {
						request.setAttribute("primaryAddress", address);
						break;
					}
				}

			}
		}
		return "commons/main/address_drop_list";
	}

	@RequestMapping("/order/checkout_page/update_card_list")
	public String updateCardList(HttpServletRequest request) {
		MeitaoUser meitaoUser = (MeitaoUser) request.getAttribute("user");
		List<MeitaoBankingCard> cardList = userService.getCardListByUserId(meitaoUser.getId());
		if (cardList != null) {
			request.setAttribute("cardList", cardList);
			if (cardList.size() > 0) {
				for (int i = cardList.size() - 1; i >= 0; i--) {
					MeitaoBankingCard card = cardList.get(i);
					if (card != null) {
						request.setAttribute("primaryCard", card);
						break;
					}
				}

			}
		}
		return "commons/main/card_drop_list";
	}
	
	@RequestMapping(value = "/order/checkout_page/select_address")
	public String selectAddress(Long addressId, HttpServletRequest request) {
		MeitaoUser meitaoUser = (MeitaoUser) request.getAttribute("user");
		if (meitaoUser == null) {
			System.out.println("检测用户错误！");
		}
		
		List<MeitaoAddress> addressList = userService.getAddressListByUserId(meitaoUser.getId());
		if (addressList != null) {
			request.setAttribute("addressList", addressList);
			if (addressList.size() > 0) {
				for (int i = addressList.size() - 1; i >= 0; i--) {
					MeitaoAddress address = addressList.get(i);
					if (address != null && address.getId().equals(addressId)) {
						request.setAttribute("primaryAddress", address);
						break;
					}
				}

			}
		}
		return "commons/main/address_drop_list";
	}
	
	@RequestMapping(value = "/order/checkout_page/select_card")
	public String selectCard(Long cardId, HttpServletRequest request) {
		MeitaoUser meitaoUser = (MeitaoUser) request.getAttribute("user");
		if (meitaoUser == null) {
			System.out.println("检测用户错误！");
		}
		
		List<MeitaoBankingCard> cardList = userService.getCardListByUserId(meitaoUser.getId());
		if (cardList != null) {
			request.setAttribute("cardList", cardList);
			if (cardList.size() > 0) {
				for (int i = cardList.size() - 1; i >= 0; i--) {
					MeitaoBankingCard card = cardList.get(i);
					if (card != null && card.getId().equals(cardId)) {
						request.setAttribute("primaryCard", card);
						break;
					}
				}

			}
		}
		return "commons/main/card_drop_list";
	}
	
	@RequestMapping(value = "/order/checkout_page/update_order_info")
	public String updateOrderInfo(HttpServletRequest request, Long primaryAddressId, Boolean isExpressShipping) {
		if (primaryAddressId != null) {
			MeitaoUser meitaoUser = (MeitaoUser) request.getAttribute("user");
			List<CartItem> cartItemList = cartService.getCartList(meitaoUser.getId(), true);
			MeitaoAddress primaryAddress = userService.getAddressByPrimaryId(primaryAddressId);
			if (primaryAddress != null) {
				OrderInfo orderInfo = orderService.getOrderInfo(cartItemList, meitaoUser, primaryAddress, isExpressShipping);
				request.setAttribute("orderInfo", orderInfo);
				request.setAttribute("primaryAddress", primaryAddress);
			}
		}
		
		return "commons/main/final_prices_block";
	}
}
