package com.meitaomart.sso.controller;

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
	public String showLogin(String redirect, Model model) {
		model.addAttribute("redirect", redirect);
		return "login";
	}
	
	@RequestMapping(value="/user/login", method=RequestMethod.POST)
	@ResponseBody
	public MeitaoResult login(String username, String password,
			HttpServletRequest request, HttpServletResponse response) {
		MeitaoResult meitaoResult = loginService.userLogin(username, password);
		//判断是否登录成功
		if(meitaoResult.getStatus() == 200) {
			String token = meitaoResult.getData().toString();
			//如果登录成功需要把token写入cookie
			System.out.println(token);
			CookieUtils.setCookie(request, response, TOKEN_KEY, token, COOKIE_EXPIRE, true);
		}
		//返回结果
		return meitaoResult;
	}
}