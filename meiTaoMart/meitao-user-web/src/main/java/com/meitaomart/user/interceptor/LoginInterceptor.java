package com.meitaomart.user.interceptor;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.meitaomart.cart.service.CartService;
import com.meitaomart.common.pojo.CartItem;
import com.meitaomart.common.utils.CookieUtils;
import com.meitaomart.common.utils.JsonUtils;
import com.meitaomart.common.utils.MeitaoResult;
import com.meitaomart.pojo.MeitaoUser;
import com.meitaomart.sso.service.TokenService;

public class LoginInterceptor implements HandlerInterceptor {
	@Autowired
	private TokenService tokenService;
	@Autowired
	private CartService  cartService;
	@Value("${SSO_URL}")
	private String SSO_URL;

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
		// 从cookie中取token
		String token = CookieUtils.getCookieValue(request, "token");
		// 判断token是否存在
		if (StringUtils.isBlank(token)) {
			// 如果token不存在，未登录状态，跳转到sso系统的登录页面。用户登录成功后，跳转到当前请求的url
			response.sendRedirect(SSO_URL + "/page/login?redirect=" + request.getRequestURL());
			// 拦截
			return false;
		}
		// 如果token存在，需要调用sso系统的服务，根据token取用户信息
		MeitaoResult meitaoResult = tokenService.getUserByToken(token);
		// 如果取不到，用户登录已经过期，需要登录。
		if (meitaoResult.getStatus() != 200) {
			// 如果token不存在，未登录状态，跳转到sso系统的登录页面。用户登录成功后，跳转到当前请求的url
			response.sendRedirect(SSO_URL + "/page/login?redirect=" + request.getRequestURL());
			// 拦截
			return false;
		}
		// 如果取到用户信息，是登录状态，需要把用户信息写入request。
		MeitaoUser user = (MeitaoUser) meitaoResult.getData();
		request.setAttribute("user", user);
		// 判断cookie中是否有购物车数据，如果有就合并到服务端。
		List<CartItem> cartItemList = getCartListByCartToken(request);
		cartService.mergeCart(user.getId(), cartItemList);
		// 放行
		return true;
	}
	
	private List<CartItem> getCartListByCartToken(HttpServletRequest request) {
		String cartToken = CookieUtils.getCookieValue(request, "cart", true);
		// 判断json是否为空
		if (StringUtils.isBlank(cartToken)) {
			return new ArrayList<>();
		}
		// 把json转换成商品列表
		List<CartItem> list = cartService.getCartListByToken(cartToken, false);
		return list;
	}

}
