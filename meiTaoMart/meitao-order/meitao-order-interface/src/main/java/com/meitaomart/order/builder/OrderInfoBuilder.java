package com.meitaomart.order.builder;

import java.util.Date;
import java.util.List;

import com.meitaomart.common.builder.CartItemBuilder;
import com.meitaomart.order.pojo.OrderInfo;
import com.meitaomart.pojo.MeitaoOrderItem;

public class OrderInfoBuilder {
	private static OrderInfoBuilder orderInfoBuilder = new OrderInfoBuilder();
	
	private OrderInfoBuilder() {
	}

	public static OrderInfoBuilder getInstance() {
		return orderInfoBuilder;
	}
	
	private String orderId;

    private Long subtotal;

    private Byte paymentType;

    private Long shippingFee;

    private Long taxFee;

    private Byte status;

    private Date createdTime;

    private Date updatedTime;

    private Date paymentTime;

    private Date consignTime;

    private Date endTime;

    private Date closeTime;

    private String shippingName;

    private String shippingCode;

    private Long userId;

    private String userComment;

    private String username;
    
    private List<MeitaoOrderItem> orderItems;

	public OrderInfoBuilder setOrderId(String orderId) {
		this.orderId = orderId;
		return this;
	}

	public OrderInfoBuilder setSubtotal(Long subtotal) {
		this.subtotal = subtotal;
		return this;
	}

	public OrderInfoBuilder setPaymentType(Byte paymentType) {
		this.paymentType = paymentType;
		return this;
	}

	public OrderInfoBuilder setShippingFee(Long shippingFee) {
		this.shippingFee = shippingFee;
		return this;
	}

	public OrderInfoBuilder setTaxFee(Long taxFee) {
		this.taxFee = taxFee;
		return this;
	}

	public OrderInfoBuilder setStatus(Byte status) {
		this.status = status;
		return this;
	}

	public OrderInfoBuilder setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
		return this;
	}

	public OrderInfoBuilder setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
		return this;
	}

	public OrderInfoBuilder setPaymentTime(Date paymentTime) {
		this.paymentTime = paymentTime;
		return this;
	}

	public OrderInfoBuilder setConsignTime(Date consignTime) {
		this.consignTime = consignTime;
		return this;
	}

	public OrderInfoBuilder setEndTime(Date endTime) {
		this.endTime = endTime;
		return this;
	}

	public OrderInfoBuilder setCloseTime(Date closeTime) {
		this.closeTime = closeTime;
		return this;
	}

	public OrderInfoBuilder setShippingName(String shippingName) {
		this.shippingName = shippingName;
		return this;
	}

	public OrderInfoBuilder setShippingCode(String shippingCode) {
		this.shippingCode = shippingCode;
		return this;
	}

	public OrderInfoBuilder setUserId(Long userId) {
		this.userId = userId;
		return this;
	}

	public OrderInfoBuilder setUserComment(String userComment) {
		this.userComment = userComment;
		return this;
	}

	public OrderInfoBuilder setUsername(String username) {
		this.username = username;
		return this;
	}

	public OrderInfoBuilder setOrderItems(List<MeitaoOrderItem> orderItems) {
		this.orderItems = orderItems;
		return this;
	}
	
	public OrderInfo build() {
		OrderInfo orderInfo = new OrderInfo();
		
		orderInfo.setOrderId(this.orderId);
		orderInfo.setSubtotal(this.subtotal);
		orderInfo.setPaymentType(this.paymentType);
		orderInfo.setShippingFee(this.shippingFee);
		orderInfo.setTaxFee(this.taxFee);
		orderInfo.setStatus(this.status);
		orderInfo.setCreatedTime(this.createdTime);
		orderInfo.setUpdatedTime(this.updatedTime);
		orderInfo.setPaymentTime(this.paymentTime);
		orderInfo.setConsignTime(this.consignTime);
		orderInfo.setEndTime(this.endTime);
		orderInfo.setCloseTime(this.closeTime);
		orderInfo.setShippingName(this.shippingName);
		orderInfo.setShippingCode(this.shippingCode);
		orderInfo.setUserId(this.userId);
		orderInfo.setUserComment(this.userComment);
		orderInfo.setUsername(this.username);
		orderInfo.setOrderItems(this.orderItems);
		
		return orderInfo;
	}
    
    
}