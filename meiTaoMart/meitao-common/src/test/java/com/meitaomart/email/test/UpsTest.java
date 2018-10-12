package com.meitaomart.email.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.meitaomart.common.builder.ShippingInfoBuilder;
import com.meitaomart.common.pojo.ShippingInfo;
import com.meitaomart.common.utils.ShippingUtils;
import com.shippo.Shippo;
import com.shippo.exception.APIConnectionException;
import com.shippo.exception.APIException;
import com.shippo.exception.AuthenticationException;
import com.shippo.exception.InvalidRequestException;
import com.shippo.model.Rate;
import com.shippo.model.Shipment;
import com.shippo.model.Transaction;

public class UpsTest {
	@Test
	public void testUps() {
		System.out.println("=============shipping service start==============");

		// replace with your Shippo Token
		// need to switch this to live apikey
		Shippo.apiKey = "shippo_test_ac84135ba9c1d983eab147a0ae7bff682ed23acf"; // this
																				// is
																				// test
																				// token
		// live token: shippo_live_f7cb190bfac018299c5e890326488e2b5b9b542d

		// Optional defaults to false
		// Shippo.setDEBUG(true);

		// to address
		Map<String, Object> toAddressMap = new HashMap<String, Object>();
		toAddressMap.put("name", "Donard Trump");
		toAddressMap.put("street1", "18 Vermeer Dr");
		toAddressMap.put("city", "South Amboy");
		toAddressMap.put("state", "NJ");
		toAddressMap.put("zip", "08879");
		toAddressMap.put("country", "US");
		toAddressMap.put("phone", "8122416882");
		toAddressMap.put("email", "anluo1120@gmail.com");

		// from address
		Map<String, Object> fromAddressMap = new HashMap<String, Object>();
		fromAddressMap.put("name", "Mr C");
		fromAddressMap.put("street1", "3996 County Rd 516");
		fromAddressMap.put("city", "Matawan");
		fromAddressMap.put("state", "NJ");
		fromAddressMap.put("zip", "07747");
		fromAddressMap.put("country", "US");
		fromAddressMap.put("email", "anluo1120@gmail.com");
		fromAddressMap.put("phone", "8122398775");
		//fromAddressMap.put("metadata", "order ID 12345");

		// parcel
		Map<String, Object> parcelMap = new HashMap<String, Object>();
		parcelMap.put("length", "5");
		parcelMap.put("width", "5");
		parcelMap.put("height", "5");
		parcelMap.put("distance_unit", "in");
		parcelMap.put("weight", "2.4991");
		parcelMap.put("mass_unit", "lb");
		List<Map<String, Object>> parcels = new ArrayList<Map<String, Object>>();
		parcels.add(parcelMap);

		Map<String, Object> shipmentMap = new HashMap<String, Object>();
		shipmentMap.put("address_to", toAddressMap);
		shipmentMap.put("address_from", fromAddressMap);
		shipmentMap.put("parcels", parcels);
		shipmentMap.put("async", false);

		Shipment shipment = null;
		try {
			shipment = Shipment.create(shipmentMap, Shippo.apiKey);
		} catch (AuthenticationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidRequestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (APIConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (APIException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// select shipping rate according to your business logic
		// we select the first rate in this example
		List<Rate> rates = shipment.getRates();
		Rate rate = rates.get(0);
		// System.out.println( "=============final rate==============:"+ rate);
		System.out.println("Getting shipping label..");
		Map<String, Object> transParams = new HashMap<String, Object>();
		transParams.put("rate", rate.getObjectId());
		transParams.put("async", false);
		Transaction transaction = null;
		try {
			transaction = Transaction.create(transParams, Shippo.apiKey);
		} catch (AuthenticationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidRequestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (APIConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (APIException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String trackingNumber = transaction.getTrackingNumber().toString();

		if (transaction.getStatus().equals("SUCCESS")) {
			System.out.println(String.format("Label url : %s", transaction.getLabelUrl()));
			System.out.println(String.format("Tracking number : %s", transaction.getTrackingNumber()));
		} else {
			System.out.println(String.format("An Error has occured while generating you label. Messages : %s",
					transaction.getMessages()));
		}
	}
	
	@Test
	public void testUps1() {
		ShippingInfo shippingInfo = ShippingInfoBuilder.getInstance().setFirstName("Donald")
				.setLastName("Trump").setPhoneNumber("3126473347")
				.setEmailAddress("luoanfast000@gmail.com").setStreet("32 Cutter Ave%2C Apt 1").setCity("Boston")
				.setState("MA").setZip("02114").setTotalWeight(52)
				.setOrderId(100576L).build();
		ShippingInfo shippingInfo2 = ShippingInfoBuilder.getInstance().setFirstName("Donald")
				.setLastName("Trump").setPhoneNumber("3126473347")
				.setEmailAddress("luoanfast000@gmail.com").setStreet("32 Cutter Ave%2C Apt 1").setCity("South Amboy")
				.setState("NJ").setZip("08879").setTotalWeight(52)
				.setOrderId(100576L).build();
		ShippingUtils.getShippingInfo(shippingInfo);
	}
}
