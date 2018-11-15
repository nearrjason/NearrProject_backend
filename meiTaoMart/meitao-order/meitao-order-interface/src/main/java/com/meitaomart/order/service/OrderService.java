package com.meitaomart.order.service;

import java.util.List;
import java.util.Set;

import com.meitaomart.common.pojo.CartItem;
import com.meitaomart.common.pojo.EasyUIDataGridResult;
import com.meitaomart.common.utils.MeitaoResult;
import com.meitaomart.order.pojo.OrderInfo;
import com.meitaomart.pojo.MeitaoAddress;
import com.meitaomart.pojo.MeitaoBankingCard;
import com.meitaomart.pojo.MeitaoOrder;
import com.meitaomart.pojo.MeitaoOrderItem;
import com.meitaomart.pojo.MeitaoUser;
import com.shippo.model.Transaction;

public interface OrderService {

	//MeitaoResult createOrder(OrderInfo orderInfo, MeitaoAddress address, MeitaoUser user);
	OrderInfo getOrderInfo(List<CartItem> cartItemList, MeitaoUser user, MeitaoAddress primaryAddress);
	OrderInfo getOrderInfo(List<CartItem> cartItemList, MeitaoUser user, MeitaoAddress primaryAddress, Boolean isExpressShipping);
	//List<MeitaoAddress> getAddressList(Long userId);
	//List<MeitaoBankingCard> getCardList(Long userId);
	List<MeitaoOrder> getOrderList();
	List<MeitaoOrderItem> getOrderItemList();
	EasyUIDataGridResult getOrderList(int page, int rows);
	EasyUIDataGridResult getOrderItemList(int page, int rows);
	List<OrderInfo> getOrderInfoListByUserId(Long userId);
	
	MeitaoResult goToPay(MeitaoUser user, MeitaoAddress shippingAddress, MeitaoAddress billingAddress, MeitaoBankingCard card, OrderInfo orderInfo, List<MeitaoOrderItem> orderItemList, String cvv);
	MeitaoResult goToPay(MeitaoUser user, Long addressId, Long cardId, OrderInfo orderInfo, List<MeitaoOrderItem> orderItemList, String cvv);
}