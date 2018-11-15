 package com.meitaomart.app.sso;

import java.util.HashMap;
import java.util.Map;

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
public class ForgetPassword {
	@Autowired
	private ForgetPasswordService forgetPasswordService;
	@Autowired
	private UserService userService;
	
	/*
	 * 返回map 参数 setPassword, true表示返回填写新密码的页面 emailAddress, 邮箱
	 * passwordCodeExpire, true表示找回密码连接已过期
	 */
	@RequestMapping(value = "/page/forget_psw", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> setNewPasswordPage(String code, HttpServletRequest request) {
		Map<String, Object> map = new HashMap<>();
		if (code != null && code.length() != 0) {
			String emailAddress = forgetPasswordService.getEmailAddressByCode(code);
			if (emailAddress != null && emailAddress.length() != 0) {
				map.put("setPassword", true);
				map.put("emailAddress", emailAddress);
			} else {
				map.put("passwordCodeExpire", true);
			}
		}
		return map;
	}

	// ResponseBody
	@RequestMapping(value = "/page/forget_psw", method = RequestMethod.POST)
	@ResponseBody
	public MeitaoResult sendEmail(String emailAddress, HttpServletRequest request) {
		MeitaoResult meitaoResult = forgetPasswordService.sendEmail(emailAddress);
		return meitaoResult;
	}
	
	// ResponseBody
	@RequestMapping(value = "/page/forget_psw/change_password", method = RequestMethod.POST)
	@ResponseBody
	public MeitaoResult setNewPassword(String emailAddress, String password, HttpServletRequest request) {
		MeitaoResult result = userService.changePassword(emailAddress, password);
		return result;
	}
	
	
}
