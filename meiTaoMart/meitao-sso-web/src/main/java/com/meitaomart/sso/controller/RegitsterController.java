package com.meitaomart.sso.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.meitaomart.common.utils.MeitaoResult;
import com.meitaomart.pojo.MeitaoUser;
import com.meitaomart.sso.service.RegisterService;

/**
 * 注册功能Controller
 * 
 */
@Controller
public class RegitsterController {
	
	@Autowired
	private RegisterService registerService;

	@RequestMapping("/page/register")
	public String showRegister() {
		return "register";
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
	public MeitaoResult register(MeitaoUser user) {
		MeitaoResult meitaoResult = registerService.register(user);
		return meitaoResult;
	}
}
