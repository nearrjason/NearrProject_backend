package com.meitaomart.search.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.meitaomart.cart.service.CartService;
import com.meitaomart.common.pojo.CartItem;
import com.meitaomart.common.pojo.ItemInfo;
import com.meitaomart.common.pojo.SearchItem;
import com.meitaomart.common.pojo.SearchResult;
import com.meitaomart.common.utils.CookieUtils;
import com.meitaomart.common.utils.JsonUtils;
import com.meitaomart.content.pojo.ItemCategoryList;
import com.meitaomart.pojo.MeitaoItemCategory;
import com.meitaomart.pojo.MeitaoUser;
import com.meitaomart.search.service.SearchService;
import com.meitaomart.service.ItemCategoryService;
import com.meitaomart.service.ItemService;

/**
 * 商品搜索Controller
 * @author anluo
 *
 */

@Controller
public class SearchController {
	@Autowired
	private SearchService searchService;
	@Autowired
	private CartService cartService;
	@Autowired
	private ItemCategoryService itemCategoryService;
	@Autowired
	private ItemService itemService;
	
	@Value("${SEARCH_RESULT_ROWS}")
	private Integer SEARCH_RESULT_ROWS;
	
	@RequestMapping("/search")
	public String searchItemListByKeyWord(String keyword, 
			@RequestParam(defaultValue = "1") Integer page, Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (keyword == null || keyword.length() == 0) {
			return "search_meitao";
		}
		
		keyword = new String(keyword.getBytes("iso-8859-1"), "utf-8");
		// 查询商品列表
		SearchResult searchResult = searchService.search(keyword, page, SEARCH_RESULT_ROWS);
		model.addAttribute("searchType", "keyword");
		return postSearch(searchResult, keyword, page, model, request, response);
		
	}
	
	@RequestMapping("/category")
	public String searchItemListByCategorys(Model model, @RequestParam String cn, @RequestParam(defaultValue = "1") Integer page, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (cn == null) {
			return "search_meitao";
		}
		cn = new String(cn.getBytes("iso-8859-1"), "utf-8");
		model.addAttribute("searchType", "categoryName");
		SearchResult searchResult = searchService.search(cn, page, SEARCH_RESULT_ROWS);
		return postSearch(searchResult, cn, page, model, request, response);
	}
	
	private String postSearch(SearchResult searchResult, String keyword, int page, Model model, HttpServletRequest request, HttpServletResponse response) {
		List<SearchItem> itemList = searchResult.getItemList();
		Long levelOneCategoryId = null;
		if (itemList != null && itemList.size() != 0) {
			Long categoryId = itemList.get(0).getCategoryId();
			levelOneCategoryId = getLevelOneCategoryId(categoryId);
		}
		
		List<ItemCategoryList> itemCategoryListWithChildrenList = new ArrayList<>();
		if (levelOneCategoryId != null) {
			itemCategoryListWithChildrenList = itemCategoryService.getItemCategoryListWithChildrenList(levelOneCategoryId);
		}
		
		List<CartItem> cartItemList = getCartItemList(request, response);
		
		// 把结果传递给页面
		model.addAttribute("query", keyword);
		model.addAttribute("totalPages", searchResult.getTotalPages());
		model.addAttribute("page", page);
		model.addAttribute("recordCount", searchResult.getRecordCount());
		model.addAttribute("itemList", searchResult.getItemList());
		model.addAttribute("cartItemList", cartItemList);
		model.addAttribute("itemCategoryList", itemCategoryListWithChildrenList);
		// 异常测试
		// 返回逻辑视图
		return "search_meitao";
	}
	
	@RequestMapping("/refresh/cart")
	public String refreshCart(HttpServletRequest request, HttpServletResponse response) {
		List<CartItem> cartItemList = getCartListByCartToken(request);
		// 判断用户是否为登录状态
		MeitaoUser user = (MeitaoUser) request.getAttribute("user");
		// 如果是登录状态
		if (user != null) {
			// 从cookie中取购物车列表
			// 如果不为空，把cookie中的购物车商品和服务端的购物车商品合并。
			cartService.mergeCart(user.getId(), cartItemList);
			// 把cookie中的购物车删除
			CookieUtils.deleteCookie(request, response, "cart");
			// 从服务端取购物车列表
			cartItemList = cartService.getCartList(user.getId(), false);

		}
		// 把列表传递给页面
		request.setAttribute("cartItemList", cartItemList);
		return "commons/header";
	}
	
	private Long getLevelOneCategoryId(Long categoryId) {
		Long parentId1 = itemCategoryService.getParentCategoryId(categoryId);
		if (Long.valueOf(0L).equals(parentId1)) {
			return categoryId;
		}
		Long parentId2 = itemCategoryService.getParentCategoryId(parentId1);
		if (Long.valueOf(0L).equals(parentId2)) {
			return parentId1;
		}
		
		return itemCategoryService.getParentCategoryId(parentId2);
	}
	
	private List<CartItem> getCartItemList(HttpServletRequest request, HttpServletResponse response) {
		List<CartItem> cartItemList = getCartListByCartToken(request);
		// 判断用户是否为登录状态
		MeitaoUser user = (MeitaoUser) request.getAttribute("user");
		// 如果是登录状态
		if (user != null) {
			// 从cookie中取购物车列表
			// 如果不为空，把cookie中的购物车商品和服务端的购物车商品合并。
			cartService.mergeCart(user.getId(), cartItemList);
			// 把cookie中的购物车删除
			CookieUtils.deleteCookie(request, response, "cart");
			// 从服务端取购物车列表
			cartItemList = cartService.getCartList(user.getId(), false);

		}
		
		return cartItemList;
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