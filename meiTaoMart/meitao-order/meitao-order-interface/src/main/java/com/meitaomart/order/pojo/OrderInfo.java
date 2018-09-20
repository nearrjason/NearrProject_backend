package com.meitaomart.order.pojo;

import java.io.Serializable;
import java.util.List;

import com.meitaomart.pojo.MeitaoOrder;
import com.meitaomart.pojo.MeitaoOrderItem;

public class OrderInfo extends MeitaoOrder implements Serializable {

	private List<MeitaoOrderItem> orderItems;
	//private TbOrderShipping orderShipping;
	public List<MeitaoOrderItem> getOrderItems() {
		return orderItems;
	}
	public void setOrderItems(List<MeitaoOrderItem> orderItems) {
		this.orderItems = orderItems;
	}
	/*public TbOrderShipping getOrderShipping() {
		return orderShipping;
	}
	public void setOrderShipping(TbOrderShipping orderShipping) {
		this.orderShipping = orderShipping;
	}*/
	
}
