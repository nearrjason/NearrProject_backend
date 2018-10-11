 package com.meitaomart.email.test;

import javax.mail.*;

import org.junit.Test;

import com.meitaomart.common.utils.EmailUtils;
import com.meitaomart.common.utils.MeitaoResult;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*; //j

import javax.mail.*; //j
import javax.mail.internet.*; //j
import javax.activation.*; //j

import javax.imageio.spi.ServiceRegistry;

import org.apache.commons.lang3.StringUtils;

public class EmailTest {
	@Test
	public void emailTest() {

		/*// TODO:customer email address
		String to = "luoanfast000@gmail.com";
		// set date
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		// will send email from donotreply@meitaomart.com
		final String username = "donotreply@meitaomart.com";
		final String pass = "Nearr123!";
		// subject/title
		String subject = "Welcome to Meitaomart";
		// body
		String body = "Dear Customer:\n"
				+ " Welcome to Meitaomart, you've successfully become a member of Meitaomart!\n" + "Best Wish\n"
				+ "Meitao Team\n" + dateFormat.format(date);
		// 发送邮件
		EmailUtils.sendEmail(username, pass, to, subject, body);*/
	}
}
