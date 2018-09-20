package com.meitaomart.common.utils;

import java.util.Random;

public final class ShippingUtils {
	public static Long getShippingFee() {
		Random random = new Random();
		long shippingFee = random.nextInt(2000) + 1;
		return shippingFee;
	}
}
