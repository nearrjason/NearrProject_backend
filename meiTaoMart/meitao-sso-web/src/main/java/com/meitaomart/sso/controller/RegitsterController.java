package com.meitaomart.sso.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.meitaomart.common.utils.CookieUtils;
import com.meitaomart.common.utils.MeitaoResult;
import com.meitaomart.pojo.MeitaoUser;
import com.meitaomart.sso.service.LoginService;
import com.meitaomart.sso.service.RegisterService;

/**
 * 注册功能Controller
 * 
 */
@Controller
public class RegitsterController {
	
	@Autowired
	private RegisterService registerService;
	
	@Autowired
	private LoginService loginService;
	
	@Value("${TOKEN_KEY}")
	private String TOKEN_KEY;
	
	@Value("${COOKIE_EXPIRE}")
	private Integer COOKIE_EXPIRE;

	@RequestMapping("/page/register")
	public String showRegister(HttpServletRequest request) {
		request.setAttribute("type", "register");
		return "login-register";
	}
	
	@RequestMapping("/user/check/{param}/{type}")
	@ResponseBody
	public MeitaoResult checkData(@PathVariable String param, @PathVariable Integer type) {
		MeitaoResult meitaoResult = registerService.checkData(param, type);
		return meitaoResult;
	}
	
	/*
	@RequestMapping(value="/user/register", method=RequestMethod.POST)
	@ResponseBody
	public MeitaoResult register(TbUser user) {
		MeitaoResult meitaoResult = registerService.register(user);
		return meitaoResult;
	}
	*/
	@RequestMapping(value="/user/register", method=RequestMethod.POST)
	@ResponseBody
	public MeitaoResult register(MeitaoUser user, HttpServletRequest request, HttpServletResponse response) {
		MeitaoResult meitaoResult = registerService.register(user);
		if (Integer.valueOf(200).equals(meitaoResult.getStatus())) {
			meitaoResult = loginService.userLogin(user.getEmail(), user.getPassword());
			if(meitaoResult.getStatus() == 200) {
				Map<String, Object> map = (Map<String, Object>) meitaoResult.getData();
				String token = map.get("token").toString();
				//如果登录成功需要把token写入cookie
				System.out.println(token);
				CookieUtils.setCookie(request, response, TOKEN_KEY, token, COOKIE_EXPIRE, true);
			}
		}
		return meitaoResult;
	}
}
