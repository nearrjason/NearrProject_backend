package com.meitaomart.common.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.taxjar.exception.TaxjarException;
import com.taxjar.model.taxes.TaxResponse;

public final class TaxUtils {
	private static final String TAX_API_TOKEN = "fc160786287b446ea1165ea88edcb268";
	private static final String FROM_COUNTRY = "US";
	private static final String FROM_ZIPCODE = "07747";
	private static final String FROM_STATE = "NJ";
	private static final String FROM_CITY = "Matawan";
	private static final String FROM_STREET = "3996 Highway 516";
	private static final String TO_COUNTRY = "US";

	public static Float getTaxFee(String toStreet, String toCity, String toState, String toZipcode, long subtotal,
			long shippingFee) throws TaxjarException {
		double amount = subtotal / 100.0;
		double shipping = shippingFee / 100.0;

		System.out.println(amount);
		System.out.println(shipping);

		Taxjar client = new Taxjar(TAX_API_TOKEN);
		/* try { */
		Map<String, Object> params = new HashMap<>();
		// send item from
		params.put("from_country", FROM_COUNTRY);
		params.put("from_zip", FROM_ZIPCODE);
		params.put("from_state", FROM_STATE);
		params.put("from_city", FROM_CITY);
		params.put("from_street", FROM_STREET);

		params.put("to_country", TO_COUNTRY);
		params.put("to_zip", toZipcode);
		params.put("to_state", toState);
		params.put("to_city", toCity);
		params.put("to_street", toStreet);
		params.put("amount", amount);
		params.put("shipping", shipping);

		List<Map> nexusAddresses = new ArrayList();
		Map<String, Object> nexusAddress = new HashMap<>();
		nexusAddress.put("country", TO_COUNTRY);
		nexusAddress.put("zip", toZipcode);
		nexusAddress.put("state", toState); // 收货地址
		nexusAddresses.add(nexusAddress);

		params.put("nexus_addresses", nexusAddresses);
		// params.put("line_items", lineItems);

		TaxResponse res = client.taxForOrder(params);
		
		return res != null && res.tax != null ? res.tax.getAmountToCollect() : null;
	}

	public static String validateAddress(String toStreet, String toCity, String toState, String toZipcode,
			long userId) {
		try {
			getTaxFee(toStreet, toCity, toState, toZipcode, 0L, 0L);
		} catch (TaxjarException e) {
			String subject = "验证地址时出现错误！";
			String body = "可能是用户在添加地址时出现的地址信息错误，详情如下:\n" + e.getMessage() + "\n\n对应用户id: " + userId;
			EmailUtils.groupSendEmail(subject, body);

			return e.getMessage();
		}

		return "";
	}

	public static Long getTaxFee() {
		return null;
	}
}