package com.meitaomart.app.order;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.meitaomart.common.utils.MeitaoResult;

@Controller
public class BankingCard {
	private static final String AMEX = "(?:3[47][0-9]{13})";
	private static final String VISA = "(?:4[0-9]{12}(?:[0-9]{3})?)";
	private static final String MASTERCARD = "(?:5[1-5][0-9]{14})";
	private static final String DISCOVER = "(?:6(?:011|5[0-9][0-9])[0-9]{12})";
	
	@RequestMapping("/cardType")
	@ResponseBody
	public MeitaoResult getCardType(String cardNumber) {
		if (cardNumber.matches(AMEX)) {
			return MeitaoResult.build(200, "amex", 3);
		} 
		
		if (cardNumber.matches(VISA)) {
			return MeitaoResult.build(200, "visa", 4);
		}
		
		if (cardNumber.matches(MASTERCARD)) {
			return MeitaoResult.build(200, "mastercard", 5);
		}
		
		if (cardNumber.matches(DISCOVER)) {
			return MeitaoResult.build(200, "discover", 6);
		}
		
		return MeitaoResult.build(400, "card type not found!");
	}
}
