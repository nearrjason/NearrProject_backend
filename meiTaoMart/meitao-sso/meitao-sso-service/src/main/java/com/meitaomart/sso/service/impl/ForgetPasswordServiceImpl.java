package com.meitaomart.sso.service.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.meitaomart.common.jedis.JedisClient;
import com.meitaomart.common.utils.EmailUtils;
import com.meitaomart.common.utils.IDUtils;
import com.meitaomart.common.utils.MeitaoResult;

@Service
public class ForgetPasswordServiceImpl implements com.meitaomart.sso.service.ForgetPasswordService {
	@Autowired
	private JedisClient jedisClient;
	@Value("${PASSWORD_CODE_PREFIX}")
	private String PASSWORD_CODE_PREFIX;
	@Value("${PASSWORD_CODE_EXPIRE}")
	private int PASSWORD_CODE_EXPIRE;
	
	@Override
	public MeitaoResult sendEmail(String emailAddress) {
		if (emailAddress == null || emailAddress.length() == 0) {
			return MeitaoResult.build(304, "无法检测到邮箱！");
		}
		String passwordCode = IDUtils.genPasswordCode();
		String urlPrefix = "http://192.168.1.100:8088/page/forget_psw?code=";
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		// subject/title
		String subject = "Meitaomart password reset!!!";
		// body
		String body = "Please click the following link for password reset:\n" + urlPrefix + passwordCode + " \n" + dateFormat.format(date);
		EmailUtils.sendEmail(emailAddress, subject, body);
		insertCodeIntoRedis(passwordCode, emailAddress);
		return MeitaoResult.ok();
	}
	
	private void insertCodeIntoRedis(String passwordCode, String emailAddress) {
		jedisClient.set(PASSWORD_CODE_PREFIX + passwordCode, emailAddress);
		jedisClient.expire(PASSWORD_CODE_PREFIX + passwordCode, PASSWORD_CODE_EXPIRE);
	}

	@Override
	public String getEmailAddressByCode(String passwordCode) {
		return jedisClient.get(PASSWORD_CODE_PREFIX + passwordCode);
	}

}
