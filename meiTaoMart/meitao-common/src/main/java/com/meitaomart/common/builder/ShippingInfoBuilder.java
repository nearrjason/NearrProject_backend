package com.meitaomart.common.builder;

import com.meitaomart.common.pojo.ShippingInfo;

public class ShippingInfoBuilder {
	private static ShippingInfoBuilder shippingInfoBuilder = new ShippingInfoBuilder();
	
	private ShippingInfoBuilder() {}
	
	public static ShippingInfoBuilder getInstance() {
		return shippingInfoBuilder;
	}
	
	private String firstName;
	private String lastName;
	private String emailAddress;
	private String phoneNumber;
	private String street;
	private String city;
	private String state;
	private String zip;
	private Long orderId;
	private int totalWeight;
	
	public ShippingInfoBuilder setFirstName(String firstName) {
		this.firstName = firstName;
		return this;
	}
	
	public ShippingInfoBuilder setLastName(String lastName) {
		this.lastName = lastName;
		return this;
	}
	
	public ShippingInfoBuilder setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
		return this;
	}
	
	public ShippingInfoBuilder setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
		return this;
	}
	
	public ShippingInfoBuilder setStreet(String street) {
		this.street = street;
		return this;
	}
	
	public ShippingInfoBuilder setCity(String city) {
		this.city = city;
		return this;
	}
	
	public ShippingInfoBuilder setState(String state) {
		this.state = state;
		return this;
	}
	
	public ShippingInfoBuilder setZip(String zip) {
		this.zip = zip;
		return this;
	}
	
	public ShippingInfoBuilder setOrderId(Long orderId) {
		this.orderId = orderId;
		return this;
	}
	
	public ShippingInfoBuilder setTotalWeight(int totalWeight) {
		this.totalWeight = totalWeight;
		return this;
	}
	
	public ShippingInfo build() {
		ShippingInfo shippingInfo = new ShippingInfo();

		shippingInfo.setFirstName(this.firstName);
		shippingInfo.setLastName(this.lastName);
		shippingInfo.setEmailAddress(this.emailAddress);
		shippingInfo.setPhoneNumber(this.phoneNumber);
		shippingInfo.setStreet(this.street);
		shippingInfo.setCity(this.city);
		shippingInfo.setState(this.state);
		shippingInfo.setZip(this.zip);
		shippingInfo.setOrderId(this.orderId);
		shippingInfo.setTotalWeight(this.totalWeight);
		
		return shippingInfo;
	}
}
