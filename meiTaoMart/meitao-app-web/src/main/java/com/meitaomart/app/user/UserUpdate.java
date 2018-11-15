package com.meitaomart.app.user;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.meitaomart.common.utils.MeitaoResult;
import com.meitaomart.pojo.MeitaoAddress;
import com.meitaomart.pojo.MeitaoBankingCard;
import com.meitaomart.pojo.MeitaoUser;
import com.meitaomart.sso.service.TokenService;
import com.meitaomart.user.service.UserService;

@Controller
public class UserUpdate {
	@Autowired
    private UserService userService;
    @Autowired
    private TokenService tokenService;

    @Value("${ADDRESS_TYPE}")
    private String ADDRESS_TYPE;
    @Value("${CARD_TYPE}")
    private String CARD_TYPE;

    @RequestMapping(value="/user/profile/update/account", method=RequestMethod.POST)
    @ResponseBody
    public MeitaoResult updateAccount(MeitaoUser user, String token, HttpServletRequest request) {
        Long userId = ((MeitaoUser) request.getAttribute("user")).getId();
     
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        
        MeitaoResult meitaoResult = userService.updateUser(user, userId);
        MeitaoUser updatedUser = (MeitaoUser)meitaoResult.getData();
        if (updatedUser == null) {
            return MeitaoResult.build(201, "保存信息失败！");
        }
        tokenService.updateUserByToken(token, updatedUser);
        return MeitaoResult.ok(updatedUser);
    }
    
    @RequestMapping("/user/profile/add/address")
    @ResponseBody
    public MeitaoResult addAddress(MeitaoAddress address, HttpServletRequest request) {
        Long userId = ((MeitaoUser) request.getAttribute("user")).getId();
        MeitaoResult result = userService.addNewAddress(address, userId);
        return result;
    }

    @RequestMapping("/user/profile/add/card")
    @ResponseBody
    public MeitaoResult addCard(MeitaoBankingCard card, HttpServletRequest request) {
        Long userId = ((MeitaoUser) request.getAttribute("user")).getId();
        MeitaoResult result = userService.addNewCard(card, userId);
        return result;
    }
    
    @RequestMapping(value="/user/profile/changePassword", method=RequestMethod.POST)
    @ResponseBody
    public MeitaoResult changePassword(String oldPassword, String newPassword, HttpServletRequest request) {
        Long userId = ((MeitaoUser) request.getAttribute("user")).getId();
        MeitaoResult result = userService.changePassword(userId, oldPassword, newPassword);
        return result;
    }
    
    /**
     * 删除个人中心的一个address或者card, 由type决定
     * @param id
     * @param type
     * @param request
     * @return
     */
    @RequestMapping("/user/profile/delete")
    @ResponseBody
    public MeitaoResult deleteOne(Long id, String type, HttpServletRequest request) {
        Long userId = ((MeitaoUser) request.getAttribute("user")).getId();
        MeitaoResult result = userService.deleteOne(id, type, userId);
        return result;
    }
    
    @RequestMapping(value="/user/profile/setAsDefault", method=RequestMethod.POST)
    @ResponseBody
    public MeitaoResult setAsDefault(Long id, String type, HttpServletRequest request) {
        Long userId = ((MeitaoUser) request.getAttribute("user")).getId();
        return userService.setAsDefault(id, type, userId);
    }
    
    @RequestMapping("/user/profile/update/address")
    @ResponseBody
    public MeitaoResult updateAddress(MeitaoAddress address, Long addressId, HttpServletRequest request) {
        Long userId = ((MeitaoUser) request.getAttribute("user")).getId();
        MeitaoResult result = userService.updateAddress(address, addressId, userId);
        return result;
    }

    @RequestMapping("/user/profile/update/card")
    @ResponseBody
    public MeitaoResult updateCard(MeitaoBankingCard card, Long cardId, HttpServletRequest request) {
        Long userId = ((MeitaoUser) request.getAttribute("user")).getId();
        MeitaoResult result = userService.updateCard(card, cardId, userId);
        return result;
    }
}
