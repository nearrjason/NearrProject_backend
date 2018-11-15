package com.meitaomart.app.logout;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.meitaomart.common.utils.MeitaoResult;
import com.meitaomart.sso.service.LogoutService;

@Controller
public class Logout {
	@Autowired
    private LogoutService logoutService;

    @Value("${TOKEN_KEY}")
    private String TOKEN_KEY;

    // logout后返回主页的url
    @RequestMapping("/do/logout")
    @ResponseBody
    public MeitaoResult logout(String token, HttpServletRequest request, HttpServletResponse response) {
        return logoutService.userLogout(token);
    }
}
