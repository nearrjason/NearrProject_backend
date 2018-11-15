package com.meitaomart.portal.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.meitaomart.common.utils.CookieUtils;
import com.meitaomart.sso.service.LogoutService;

@Controller
public class LogoutController {
	@Autowired
	private LogoutService logoutService;
	
	@Value("${TOKEN_KEY}")
	private String TOKEN_KEY;
	
	@RequestMapping("/user/logout")
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		CookieUtils.deleteCookie(request, response, TOKEN_KEY);
		String token = CookieUtils.getCookieValue(request, TOKEN_KEY, true);
		logoutService.userLogout(token);
		return "redirect:/index.html";
	}
}