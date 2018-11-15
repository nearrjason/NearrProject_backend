package com.meitaomart.cart.service;

import java.util.List;

import com.meitaomart.common.pojo.CartItem;
import com.meitaomart.common.utils.MeitaoResult;
import com.meitaomart.pojo.MeitaoItem;

public interface CartService {

	MeitaoResult addCart(long userId, long itemId, int purchaseQuantity);
	MeitaoResult addCartByToken(String cartToken, long itemId, int purchaseQuantity);
	MeitaoResult mergeCart(long userId, List<CartItem> itemList);
	List<CartItem> getCartList(long userId, boolean isFilterOOSItem);
	List<CartItem> getCartListByToken(String cartToken, boolean isFilterOOSItem);
	MeitaoResult updateCartPurchaseQuantity(long userId, long itemId, int purchaseQuantity);
	MeitaoResult updateCartPurchaseQuantityByToken(String cartToken, long itemId, int purchaseQuantity);
	MeitaoResult deleteCartItem(long userId, long itemId);
	MeitaoResult deleteCartItemByToken(String cartToken, long itemId);
	MeitaoResult clearCartItem(long userId);
}
