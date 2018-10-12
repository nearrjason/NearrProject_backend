package com.meitaomart.order.pojo;

import java.io.Serializable;
import java.util.List;

import com.meitaomart.common.pojo.ItemInfo;
import com.meitaomart.pojo.MeitaoAddress;
import com.meitaomart.pojo.MeitaoBankingCard;
import com.meitaomart.pojo.MeitaoOrder;
import com.meitaomart.pojo.MeitaoOrderItem;

public class OrderInfo extends MeitaoOrder implements Serializable {

	private List<MeitaoOrderItem> orderItems;
	private List<ItemInfo> orderItemsInfo;
	private MeitaoAddress orderAddress;
	private MeitaoBankingCard orderCard;
	
	public List<MeitaoOrderItem> getOrderItems() {
		return orderItems;
	}
	public void setOrderItems(List<MeitaoOrderItem> orderItems) {
		this.orderItems = orderItems;
	}
	public List<ItemInfo> getOrderItemsInfo() {
		return orderItemsInfo;
	}
	public void setOrderItemsInfo(List<ItemInfo> orderItemsInfo) {
		this.orderItemsInfo = orderItemsInfo;
	}
	public MeitaoAddress getOrderAddress() {
		return orderAddress;
	}
	public void setOrderAddress(MeitaoAddress orderAddress) {
		this.orderAddress = orderAddress;
	}
	public MeitaoBankingCard getOrderCard() {
		return orderCard;
	}
	public void setOrderCard(MeitaoBankingCard orderCard) {
		this.orderCard = orderCard;
	}
}
