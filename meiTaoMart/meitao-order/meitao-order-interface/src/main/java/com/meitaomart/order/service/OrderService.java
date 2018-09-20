package com.meitaomart.order.service;

import java.util.List;

import com.meitaomart.common.pojo.CartItem;
import com.meitaomart.common.utils.MeitaoResult;
import com.meitaomart.order.pojo.OrderInfo;
import com.meitaomart.pojo.MeitaoAddress;
import com.meitaomart.pojo.MeitaoUser;

public interface OrderService {

	MeitaoResult createOrder(OrderInfo orderInfo);
	OrderInfo getOrderInfo(List<CartItem> cartItemList, MeitaoUser user);
	List<MeitaoAddress> getAddressList(Long userId);
}
