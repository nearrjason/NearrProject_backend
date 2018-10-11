package com.meitaomart.sso.service;

import com.meitaomart.common.utils.MeitaoResult;
import com.meitaomart.pojo.MeitaoUser;

/**
 * 根据token查询用户信息
 * @version 1.0
 */
public interface TokenService {
	MeitaoResult getUserByToken(String token);
	MeitaoResult updateUserByToken(String token, MeitaoUser user);
}
