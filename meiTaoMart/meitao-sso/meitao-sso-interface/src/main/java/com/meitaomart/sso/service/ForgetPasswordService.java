package com.meitaomart.sso.service;

import com.meitaomart.common.utils.MeitaoResult;

public interface ForgetPasswordService {
	MeitaoResult sendEmail(String emailAddress);
	String getEmailAddressByCode(String passwordCode);
}
