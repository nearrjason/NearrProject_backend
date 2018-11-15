package com.meitaomart.common.utils;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.meitaomart.common.pojo.ShippingInfo;
import com.shippo.Shippo;
import com.shippo.exception.APIConnectionException;
import com.shippo.exception.APIException;
import com.shippo.exception.AuthenticationException;
import com.shippo.exception.InvalidRequestException;
import com.shippo.model.Rate;
import com.shippo.model.Shipment;
import com.shippo.model.Transaction;

public final class ShippingUtils {
	private static final long GROUND_SHIPPING = 1L;
	private static final long EXPRESS_SHIPPING = 2L;
	private static final long FREE_SHIPPING_TRESHOLD = 4900L;
	
	private static final String SHIPPING_API_KEY = "shippo_test_ac84135ba9c1d983eab147a0ae7bff682ed23acf";
	private static final String DEFAULT_COUNTRY = "US";
	private static final String SUCCESS_MESSAGE = "SUCCESS";
	
	private static final String DEFAULT_NAME = "Mr C";
	private static final String DEFAULT_STREET = "3996 County Rd 516";
	private static final String DEFAULT_CITY = "Matawan";
	private static final String DEFAULT_STATE = "NJ";
	private static final String DEFAULT_ZIP = "07747";
	private static final String DEFAULT_PHONE = "8122398775";
	private static final String DEFAULT_EMAIL = "jiangxingcheng2008@gmail.com";
	
	private static final String DEFAULT_LENGTH = "5";
	private static final String DEFAULT_WIDTH = "5";
	private static final String DEFAULT_HEIGHT = "5";
	private static final String DEFAULT_DISTANCE_UNIT = "in";
	private static final String DEFAULT_MASS_UNIT = "lb";
	private static final String DEFAULT_WEIGHT = "2";
	
	private static final DecimalFormat TO_FOUR_DECIMAL_PLACE = new DecimalFormat("#.####");
	private static final double GRAM_TO_LB_RATE = 0.00220462;
	
	private static final String ERROR_SUBJECT = "创建shipping information时出错！";

	public static Long getShippingFee(long subtotal, Boolean isExpress) {
		if (isExpress != null && isExpress.booleanValue()) {
			return EXPRESS_SHIPPING;
		}

		return subtotal >= FREE_SHIPPING_TRESHOLD ? 0L : GROUND_SHIPPING;
	}

	public static Long getShippingFee(long subtotal) {
		return getShippingFee(subtotal, false);
	}

	public static Transaction getShippingInfo(ShippingInfo shippingInfo) {
		
 		Rate rate = getShippingRate(shippingInfo);
 		//System.out.println( "=============final rate==============:"+ rate);
 		//System.out.println("Getting shipping label..");
 		Map<String, Object> transParams = new HashMap<String, Object>();
 		transParams.put("rate", rate.getObjectId());
 		transParams.put("async", false);
 		
 		Transaction transaction = null;
		try {
			transaction = Transaction.create(transParams,Shippo.apiKey);
		} catch (Exception e) {
			String body = e.getMessage();
			EmailUtils.groupSendEmail(ERROR_SUBJECT, body);
			return null;
		}

		return SUCCESS_MESSAGE.equals(transaction.getStatus()) ? transaction : null;

	}
	
	public static Rate getShippingRate(ShippingInfo shippingInfo) {
		Shippo.apiKey = SHIPPING_API_KEY;

		Map<String, Object> toAddressMap = new HashMap<String, Object>();
		toAddressMap.put("name", shippingInfo.getFirstName() + " " + shippingInfo.getLastName());
		toAddressMap.put("street1", shippingInfo.getStreet());
		toAddressMap.put("city", shippingInfo.getCity());
		toAddressMap.put("state", shippingInfo.getState());
		toAddressMap.put("zip", shippingInfo.getZip());
		toAddressMap.put("country", DEFAULT_COUNTRY);
		toAddressMap.put("phone", shippingInfo.getPhoneNumber());
		toAddressMap.put("email", shippingInfo.getEmailAddress());

		// from address
		Map<String, Object> fromAddressMap = new HashMap<String, Object>();
		fromAddressMap.put("name", DEFAULT_NAME);
 		fromAddressMap.put("street1", DEFAULT_STREET);
 		fromAddressMap.put("city", DEFAULT_CITY);
 		fromAddressMap.put("state", DEFAULT_STATE);
 		fromAddressMap.put("zip", DEFAULT_ZIP);
 		fromAddressMap.put("country", DEFAULT_COUNTRY);
 		fromAddressMap.put("email", DEFAULT_EMAIL);
 		fromAddressMap.put("phone", DEFAULT_PHONE);
		fromAddressMap.put("metadata", "order ID " + shippingInfo.getOrderId());
		
		Map<String, Object> parcelMap = new HashMap<String, Object>();
 		parcelMap.put("length", DEFAULT_LENGTH);
 		parcelMap.put("width", DEFAULT_WIDTH);
 		parcelMap.put("height", DEFAULT_HEIGHT);
 		parcelMap.put("distance_unit", DEFAULT_DISTANCE_UNIT);
 		
 		String weight = shippingInfo.getTotalWeight() != 0 ? TO_FOUR_DECIMAL_PLACE.format(GRAM_TO_LB_RATE * shippingInfo.getTotalWeight()) : DEFAULT_WEIGHT;
 		
 		//GRAM_TO_LB_RATE * shippingInfo.getTotalWeight();
 		parcelMap.put("weight", weight);
 		parcelMap.put("mass_unit", DEFAULT_MASS_UNIT);
 		List<Map<String, Object>> parcels = new ArrayList<Map<String, Object>>();
 		parcels.add(parcelMap);
 		
 		Map<String, Object> shipmentMap = new HashMap<String, Object>();
 		shipmentMap.put("address_to", toAddressMap);
 		shipmentMap.put("address_from", fromAddressMap);
 		shipmentMap.put("parcels", parcels);
 		shipmentMap.put("async", false);
 		
 		
 		Shipment shipment = null;
		try {
			shipment = Shipment.create(shipmentMap,Shippo.apiKey);
		} catch (Exception e) {
			String body = e.getMessage();
			EmailUtils.groupSendEmail(ERROR_SUBJECT, body);
			return null;
		} 
 		
 		// select shipping rate according to your business logic
 		// we select the first rate in this example
 		List<Rate> rates = shipment.getRates();
 		return rates == null || rates.size() == 0 ? null : rates.get(0);
	}
}
