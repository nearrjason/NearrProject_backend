package com.meitaomart.app.sso;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.meitaomart.common.utils.MeitaoResult;
import com.meitaomart.pojo.MeitaoUser;
import com.meitaomart.sso.service.LoginService;
import com.meitaomart.sso.service.RegisterService;

@Controller
public class Register {
	private static final Integer USERNAME_TYPE = 1;
	private static final Integer EMAIL_TYPE = 3;
	
	@Autowired
	private RegisterService registerService;
	@Autowired
    private LoginService loginService;

	@RequestMapping("/page/register")
	@ResponseBody
	public Map<String, Object> showRegister(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<>();
		map.put("type", "register");
		return map;
	}
	
	/**
	 * 
	 * @param email, username, password
	 * @return
	 */
	@RequestMapping(value="/do/register", method=RequestMethod.POST)
	@ResponseBody
	public MeitaoResult register(MeitaoUser user, HttpServletRequest request, HttpServletResponse response) {
		MeitaoResult meitaoResult = registerService.register(user);
		if (Integer.valueOf(200).equals(meitaoResult.getStatus())) {
			meitaoResult = loginService.userLogin(user.getEmail(), user.getPassword());
		}
		return meitaoResult;
	}
}
