package com.meitaomart.common.utils;

public final class ShippingUtils {
	private static final long GROUND_SHIPPING = 599L;
	private static final long EXPRESS_SHIPPING = 1999L;
	private static final long FREE_SHIPPING_TRESHOLD = 4900L;
	
	public static Long getShippingFee(long subtotal, boolean isExpress) {
		if (isExpress) {
			return EXPRESS_SHIPPING;
		}
		
		return subtotal >= FREE_SHIPPING_TRESHOLD ? 0L : GROUND_SHIPPING;
	}
	
	public static Long getShippingFee(long subtotal) {
		return getShippingFee(subtotal, false);
	}
}
