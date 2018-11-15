package com.meitaomart.app.pojo;

import java.io.Serializable;

import com.meitaomart.pojo.MeitaoAddress;
import com.meitaomart.pojo.MeitaoBankingCard;

public class FinalCheckoutMap implements Serializable{
	private Long addressId;
	private Long cardId;
	private String orderInfo;
	private String orderItemList;
	private String cvv;
	
	
	public Long getAddressId() {
		return addressId;
	}
	public void setAddressId(Long addressId) {
		this.addressId = addressId;
	}
	public Long getCardId() {
		return cardId;
	}
	public void setCardId(Long cardId) {
		this.cardId = cardId;
	}
	public String getOrderInfo() {
		return orderInfo;
	}
	public void setOrderInfo(String orderInfo) {
		this.orderInfo = orderInfo;
	}
	
	public String getOrderItemList() {
		return orderItemList;
	}
	public void setOrderItemList(String orderItemList) {
		this.orderItemList = orderItemList;
	}
	public String getCvv() {
		return cvv;
	}
	public void setCvv(String cvv) {
		this.cvv = cvv;
	}
}