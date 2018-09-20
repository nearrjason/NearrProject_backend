package com.meitaomart.sso.service;

import com.meitaomart.common.utils.MeitaoResult;

public interface LogoutService {
	MeitaoResult userLogout(String token);
}
