package com.meitaomart.sso.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.meitaomart.common.utils.CookieUtils;
import com.meitaomart.common.utils.MeitaoResult;
import com.meitaomart.sso.service.LoginService;

/**
 * 用户登录处理
 * 
 */
@Controller
public class LoginController {

	@Autowired
	private LoginService loginService;

	@Value("${TOKEN_KEY}")
	private String TOKEN_KEY;
	
	@Value("${COOKIE_EXPIRE}")
	private Integer COOKIE_EXPIRE;
	
	@RequestMapping("/page/login")
	public String showLogin(HttpServletRequest request, String redirect, Model model) {
		model.addAttribute("redirect", redirect);
		request.setAttribute("type", "login");
		return "login-register";
	}
	
	
	@RequestMapping(value="/user/login", method=RequestMethod.POST)
	@ResponseBody
	public MeitaoResult login(String usernameOrEmail, String password,
			HttpServletRequest request, HttpServletResponse response) {
		if (usernameOrEmail == null || usernameOrEmail.length() == 0) {
			return MeitaoResult.build(304, "登录失败, 无法检测到用户名");
		}
		
		if (password == null) {
			return MeitaoResult.build(304, "登录失败, 无法检测密码");
		}
		
		MeitaoResult meitaoResult = loginService.userLogin(usernameOrEmail, password);
		//判断是否登录成功
		if(meitaoResult.getStatus() == 200) {
			Map<String, Object> map = (Map<String, Object>) meitaoResult.getData();
			String token = map.get("token").toString();
			//如果登录成功需要把token写入cookie
			System.out.println(token);
			CookieUtils.setCookie(request, response, TOKEN_KEY, token, COOKIE_EXPIRE, true);
		}
		//返回结果
		return meitaoResult;
	}
}
