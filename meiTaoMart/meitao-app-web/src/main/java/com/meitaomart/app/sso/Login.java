package com.meitaomart.app.sso;

import java.util.HashMap;
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

import com.meitaomart.common.utils.MeitaoResult;
import com.meitaomart.sso.service.LoginService;

@Controller
public class Login {
	@Autowired
    private LoginService loginService;

    @Value("${TOKEN_KEY}")
    private String TOKEN_KEY;

    /*
        map
        参数：
            redirect, 非空代表重定向到登录之前的界面
            type, 值为login代表显示登录界面，值为register代表显示注册界面
     */
    @RequestMapping("/page/login")
    @ResponseBody
    public Map<String, Object> showLogin(HttpServletRequest request, String redirect, Model model) {
        Map<String, Object> map = new HashMap<>();
        map.put("redirect", redirect);
        map.put("type", "login");
        return map;
    }

    // ResponseBody
    @RequestMapping(value="/do/login", method=RequestMethod.POST)
    @ResponseBody
    public MeitaoResult login(String usernameOrEmail, String password) {
        if (usernameOrEmail == null || usernameOrEmail.length() == 0) {
            return MeitaoResult.build(304, "登录失败, 无法检测到用户名");
        }

        if (password == null) {
            return MeitaoResult.build(304, "登录失败, 无法检测密码");
        }

        MeitaoResult meitaoResult = loginService.userLogin(usernameOrEmail, password);
        
        //对于app直接返回结果，如果status是200, 那么会携带token信息
        return meitaoResult;
    }
}
