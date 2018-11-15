package com.meitaomart.app.item;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.meitaomart.cart.service.CartService;
import com.meitaomart.common.pojo.CartItem;
import com.meitaomart.common.pojo.ItemInfo;
import com.meitaomart.common.utils.JsonUtils;
import com.meitaomart.common.utils.MeitaoResult;
import com.meitaomart.pojo.MeitaoUser;
import com.meitaomart.service.ItemService;
import com.meitaomart.sso.service.TokenService;

@Controller
public class ItemId {
	@Autowired
    private ItemService itemService;
	@Autowired
    private CartService cartService;
	@Autowired
	private TokenService tokenService;

    // 商品详情页面的购物车列表
    @RequestMapping("/item/cartItemList")
    @ResponseBody
    public MeitaoResult showItemInfoCartItemList(Model model, HttpServletRequest request, HttpServletResponse response) {
    	String token = request.getParameter("token");
    	MeitaoResult meitaoResult = tokenService.getUserByToken(token);
    	MeitaoUser user = (MeitaoUser) meitaoResult.getData();
    	
        List<CartItem> cartItemList = new ArrayList<>();
       
        if (user != null) {
            cartItemList = cartService.getCartList(user.getId(), false);

        }
        return MeitaoResult.ok(cartItemList);
    }

    /**
     * 通过商品id返回商品详情，若没有找到该商品，返回null
     * @param itemId
     * @param model
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/item/{itemId}")
    @ResponseBody
    public MeitaoResult showItemInfoItemInfo(@PathVariable Long itemId, Model model, HttpServletRequest request, HttpServletResponse response) {
        // 调用服务获取商品基本信息
        if (itemId == null) {
            return MeitaoResult.build(400, "item id 为空！");
        }
        ItemInfo itemInfo = itemService.getItemById(itemId);
        // 取商品描述信息
        // 把信息传递给页面

        return MeitaoResult.ok(itemInfo);
    }
}
