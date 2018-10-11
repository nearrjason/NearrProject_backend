package com.meitaomart.sso.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.meitaomart.common.utils.MeitaoResult;
import com.meitaomart.sso.service.ForgetPasswordService;
import com.meitaomart.user.service.UserService;

@Controller
public class ForgetPasswordController {
	@Autowired
	private ForgetPasswordService forgetPasswordService;
	@Autowired
	private UserService userService;
	
	/*@RequestMapping(value="/page/forget_psw", method = RequestMethod.GET)
	public String forgetPassword() {
		return "forget-password";
	}*/
	
	@RequestMapping(value="/page/forget_psw", method = RequestMethod.GET)
	public String setNewPasswordPage(String code, HttpServletRequest request) {
		if (code != null && code.length() != 0) {
			String emailAddress = forgetPasswordService.getEmailAddressByCode(code);
			if (emailAddress != null && emailAddress.length() != 0) {
				request.setAttribute("setPassword", true);
				request.setAttribute("emailAddress", emailAddress);
			} else {
				System.out.println("此链接已过期");
				request.setAttribute("passwordCodeExpire", true);
			}
		}
		return "forget-password";
	}
	
	@RequestMapping(value="/page/forget_psw", method = RequestMethod.POST)
	@ResponseBody
	public MeitaoResult sendEmail(String emailAddress, HttpServletRequest request) {
		MeitaoResult meitaoResult = forgetPasswordService.sendEmail(emailAddress);
		return meitaoResult;
	}
	
	@RequestMapping(value="/page/forget_psw/change_password", method = RequestMethod.POST)
	@ResponseBody
	public MeitaoResult setNewPassword(String emailAddress, String password, HttpServletRequest request) {
		MeitaoResult result = userService.changePassword(emailAddress, password);
		return result;
	}
}
