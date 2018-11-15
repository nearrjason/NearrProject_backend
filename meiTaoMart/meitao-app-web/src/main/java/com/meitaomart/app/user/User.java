package com.meitaomart.app.user;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.meitaomart.cart.service.CartService;
import com.meitaomart.common.pojo.CartItem;
import com.meitaomart.common.pojo.ItemInfo;
import com.meitaomart.common.utils.JsonUtils;
import com.meitaomart.common.utils.MeitaoResult;
import com.meitaomart.order.pojo.OrderInfo;
import com.meitaomart.order.service.OrderService;
import com.meitaomart.pojo.MeitaoAddress;
import com.meitaomart.pojo.MeitaoBankingCard;
import com.meitaomart.pojo.MeitaoOrderItem;
import com.meitaomart.pojo.MeitaoUser;
import com.meitaomart.service.ItemService;
import com.meitaomart.user.service.UserService;

@Controller
public class User {
	@Autowired
    private UserService userService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private ItemService itemService;
    @Autowired
    private CartService cartService;

	@RequestMapping("/user/profile/cartItemList")
	@ResponseBody
	public MeitaoResult getAccountCartItemList(HttpServletRequest request, HttpServletResponse response) {
		MeitaoUser user = (MeitaoUser) request.getAttribute("user");
        return user != null ? MeitaoResult.ok(cartService.getCartList(user.getId(), false)) : MeitaoResult.build(400, "无法检测到用户");
	}

	@RequestMapping("/user/profile/account")
	@ResponseBody
	public MeitaoResult getAccountMeitaoUser(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<>();
		MeitaoUser user = (MeitaoUser) request.getAttribute("user");
		if (user == null) {
			return MeitaoResult.build(400, "无法检测到用户");
		}

		MeitaoUser meitaoUser = userService.getUserByPrimaryId(user.getId());
		map.put("user", meitaoUser);
		map.put("type", "account");
		return MeitaoResult.ok(map);
	}

    @RequestMapping("/user/profile/addressList")
    @ResponseBody
    public MeitaoResult getAddressesMeitaoAddressList(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> map = new HashMap<>();
        MeitaoUser user = (MeitaoUser) request.getAttribute("user");
        if (user == null) {
        	return MeitaoResult.build(400, "无法检测到用户");
        }
        List<MeitaoAddress> meitaoAddressList = userService.getAddressListByUserId(user.getId());
        map.put("addressList", meitaoAddressList);
        map.put("type", "address");
        return MeitaoResult.ok(map);
    }

    @RequestMapping("/user/profile/cardList")
    @ResponseBody
    public MeitaoResult getCardsmeitaoCardList(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> map = new HashMap<>();
        MeitaoUser user = (MeitaoUser) request.getAttribute("user");
        if (user == null) {
        	return MeitaoResult.build(400, "无法检测到用户");
        }
        List<MeitaoBankingCard> meitaoCardList = userService.getCardListByUserId(user.getId());
        map.put("cardList", meitaoCardList);
        map.put("type", "card");
        return MeitaoResult.ok(map);
    }
    
    @RequestMapping("/user/profile/orderInfoList")
    @ResponseBody
    public MeitaoResult getOrdersOrderInfoList(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> map = new HashMap<>();
        MeitaoUser user = (MeitaoUser) request.getAttribute("user");
        if (user == null) {
        	return MeitaoResult.build(400, "无法检测到用户");
        }

        List<OrderInfo> orderInfoList = orderService.getOrderInfoListByUserId(user.getId());
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

        map.put("orderInfoList", orderInfoList);
        map.put("type", "order");
        return MeitaoResult.ok(map);
    }
}
