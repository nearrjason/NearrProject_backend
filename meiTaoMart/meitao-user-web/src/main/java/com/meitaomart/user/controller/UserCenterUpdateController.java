package com.meitaomart.user.controller;

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

import com.meitaomart.common.utils.CookieUtils;
import com.meitaomart.common.utils.MeitaoResult;
import com.meitaomart.pojo.MeitaoAddress;
import com.meitaomart.pojo.MeitaoBankingCard;
import com.meitaomart.pojo.MeitaoUser;
import com.meitaomart.sso.service.TokenService;
import com.meitaomart.user.service.UserService;

@Controller
public class UserCenterUpdateController {
	@Autowired
	private UserService userService;
	@Autowired
	private TokenService tokenService;
	
	@Value("${ADDRESS_TYPE}")
	private String ADDRESS_TYPE;
	@Value("${CARD_TYPE}")
	private String CARD_TYPE;
	
	@RequestMapping(value="/user/profile/account", method=RequestMethod.POST)
	@ResponseBody
	public MeitaoResult updateAccount(MeitaoUser user, String birthdayStr, HttpServletRequest request) {
		Long userId = ((MeitaoUser) request.getAttribute("user")).getId();
		String token = CookieUtils.getCookieValue(request, "token");
		
		MeitaoResult result = MeitaoResult.ok();
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date birthday = null;
		try {
			birthday = format.parse(birthdayStr);
		} catch (ParseException e) {
			String msg = "生日信息错误! 其他信息已保存成功！若要继续修改生日信息，请重新设置生日信息并保存！";
			result.setStatus(201);
			result.setMsg(msg);
		}
		
		user.setBirthday(birthday);
		MeitaoResult meitaoResult = userService.updateUser(user, userId);
		MeitaoUser updatedUser = (MeitaoUser)meitaoResult.getData();
		if (updatedUser == null) {
			return MeitaoResult.build(201, "保存信息失败！");
		}
		tokenService.updateUserByToken(token, updatedUser);
		return result;
	}
	
	@RequestMapping(value="/user/profile/set_as_default", method=RequestMethod.POST)
	@ResponseBody
	public MeitaoResult setAsDefault(Long id, String type, HttpServletRequest request) {
		Long userId = ((MeitaoUser) request.getAttribute("user")).getId();
		return userService.setAsDefault(id, type, userId);
	}
	
	@RequestMapping(value="/user/profile/change_password", method=RequestMethod.POST)
	@ResponseBody
	public MeitaoResult changePassword(String oldPassword, String newPassword, HttpServletRequest request) {
		Long userId = ((MeitaoUser) request.getAttribute("user")).getId();
		MeitaoResult result = userService.changePassword(userId, oldPassword, newPassword);
		return result;
	}
	
	
	@RequestMapping("/user/profile/delete")
	public String deleteOne(Long id, String type, HttpServletRequest request) {
		Long userId = ((MeitaoUser) request.getAttribute("user")).getId();
		userService.deleteOne(id, type, userId);
		return ADDRESS_TYPE.equals(type) ? "redirect:/user/profile/addresses" : "redirect:/user/profile/cards";
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
		if (cardId == null) {
			return MeitaoResult.build(400, "更新银行卡失败: card id 为空！");
		}
		Long userId = ((MeitaoUser) request.getAttribute("user")).getId();
		MeitaoResult result = userService.updateCard(card, cardId, userId);
		return result;
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
}
