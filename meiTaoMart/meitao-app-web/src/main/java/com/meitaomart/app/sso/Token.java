package com.meitaomart.app.sso;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.meitaomart.common.utils.MeitaoResult;
import com.meitaomart.sso.service.TokenService;

@Controller
public class Token {
	@Autowired
    private TokenService tokenService;
	
    @RequestMapping(value="/user/token")
    @ResponseBody
    public MeitaoResult getUserByToken(String token) {
        MeitaoResult result = tokenService.getUserByToken(token);
        return result;
    }
}
