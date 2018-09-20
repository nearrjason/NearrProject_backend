package com.meitaomart.sso.service;

import com.meitaomart.common.utils.MeitaoResult;
import com.meitaomart.pojo.MeitaoUser;

public interface RegisterService {
	MeitaoResult checkData(String param, int type);
	MeitaoResult register(MeitaoUser user);
}
