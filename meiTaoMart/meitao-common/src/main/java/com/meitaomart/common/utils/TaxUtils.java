package com.meitaomart.common.utils;

import java.util.Random;

public final class TaxUtils {
	/**
	 * 
	 * @param subtotal 商品总金额(单位:分)
	 * @return 税 (单位:分)
	 */
	public static Long getTaxFee(Long subtotal) {
		return null;
	}
	
	/**
	 * 
	 * @return 税 (单位:分)
	 */
	public static Long getTaxFee() {
		Random random = new Random();
		long taxFee = random.nextInt(2000) + 1;
		return taxFee;
	}
}
