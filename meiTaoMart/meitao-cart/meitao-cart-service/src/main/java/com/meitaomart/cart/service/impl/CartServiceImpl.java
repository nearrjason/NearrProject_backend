package com.meitaomart.cart.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.meitaomart.cart.service.CartService;
import com.meitaomart.common.builder.CartItemBuilder;
import com.meitaomart.common.jedis.JedisClient;
import com.meitaomart.common.pojo.CartItem;
import com.meitaomart.common.pojo.ItemInfo;
import com.meitaomart.common.utils.MeitaoResult;
import com.meitaomart.common.utils.JsonUtils;
import com.meitaomart.mapper.MeitaoItemMapper;
import com.meitaomart.mapper.MeitaoItemPriceMapper;
import com.meitaomart.pojo.MeitaoItem;
import com.meitaomart.pojo.MeitaoItemPrice;

/**
 * 购物车处理服务
 * 
 * @version 1.0
 */
@Service
public class CartServiceImpl implements CartService {

	@Autowired
	private JedisClient jedisClient;
	@Value("${REDIS_CART_PRE}")
	private String REDIS_CART_PRE;
	@Autowired
	private MeitaoItemMapper meitaoItemMapper;
	@Autowired
	private MeitaoItemPriceMapper meitaoItemPriceMapper;

	@Override
	public MeitaoResult addCart(long userId, long itemId, int purchaseQuantity) {
		
		MeitaoResult result = checkItemEffective(itemId, purchaseQuantity);
		if (!Integer.valueOf(200).equals(result.getStatus())) {
			return result;
		}
		return addCartIntoRedis(REDIS_CART_PRE + ":" + userId, itemId, purchaseQuantity);
	}

	@Override
	public MeitaoResult addCartByToken(String cartToken, long itemId, int purchaseQuantity) {
		MeitaoResult result = checkItemEffective(itemId, purchaseQuantity);
		if (!Integer.valueOf(200).equals(result.getStatus())) {
			return result;
		}
		return addCartIntoRedis(cartToken, itemId, purchaseQuantity);
	}
	
	private MeitaoResult checkItemEffective(long itemId, int purchaseQuantity) {
		MeitaoItem item = meitaoItemMapper.selectByPrimaryKey(itemId);
		if (item == null) {
			return MeitaoResult.build(400, "无法检测到商品，请刷新页面并重试!");
		}
		
		if (item.getStockNumber() == null || item.getStockNumber().equals(0) || purchaseQuantity > item.getStockNumber()) {
			return MeitaoResult.build(400, "对不起，您所选择的商品库存不足，当前库存数为: " + item.getStockNumber());
		}
		
		if (item.getStatus() == null || !item.getStatus().equals((byte)1)) {
			return MeitaoResult.build(400, "对不起，您所选择的商品已下架，请选择其他商品!");
		}
		
		return MeitaoResult.ok();
	}

	private MeitaoResult addCartIntoRedis(String key, long itemId, int purchaseQuantity) {
		boolean hexists = jedisClient.hexists(key, itemId + "");
		// 如果存在数量相加
		if (hexists) {
			String json = jedisClient.hget(key, itemId + "");
			// 把json转换成TbItem
			CartItem cartItem = JsonUtils.jsonToPojo(json, CartItem.class);
			if (cartItem == null) {
				return MeitaoResult.build(501, "添加购物车失败, redis中得到空购物车信息，请检查redis!");
			}
			cartItem.setPurchaseQuantity(cartItem.getPurchaseQuantity() + purchaseQuantity);
			// 写回redis
			jedisClient.hset(key, itemId + "", JsonUtils.objectToJson(cartItem));
			return MeitaoResult.ok(cartItem);
		}

		MeitaoItem meitaoItem = meitaoItemMapper.selectByPrimaryKey(itemId);
		MeitaoItemPrice meitaoItemPrice = meitaoItemPriceMapper.selectByPrimaryKey(itemId);
		if (meitaoItem == null || meitaoItemPrice == null) {
			return MeitaoResult.build(501, "添加购物车失败, 没有找到对应商品!");
		}

		CartItem cartItem = createCartItem(meitaoItem, meitaoItemPrice, purchaseQuantity);
		// 添加到购物车列表
		jedisClient.hset(key, itemId + "", JsonUtils.objectToJson(cartItem));
		return MeitaoResult.ok(cartItem);
	}

	private CartItem createCartItem(MeitaoItem meitaoItem, MeitaoItemPrice meitaoItemPrice, Integer purchaseQuantity) {
		CartItem cartItem = null;

		if (meitaoItem != null && meitaoItemPrice != null) {
			cartItem = CartItemBuilder.getInstance().setId(meitaoItem.getId()).setName(meitaoItem.getName())
					.setStockNumber(meitaoItem.getStockNumber()).setSalePrice(meitaoItemPrice.getSalePrice())
					.setDiscount(meitaoItemPrice.getDiscount()).setNetWeight(meitaoItem.getNetWeight())
					.setImages(meitaoItem.getImages()).setOneImage(meitaoItem.getImages())
					.setPurchaseQuantity(purchaseQuantity).build();
		}

		return cartItem;
	}

	@Override
	public MeitaoResult mergeCart(long userId, List<CartItem> cartItemList) {
		// 遍历商品列表
		// 把列表添加到购物车。
		// 判断购物车中是否有此商品
		// 如果有，数量相加
		// 如果没有添加新的商品
		for (CartItem cartItem : cartItemList) {
			addCart(userId, cartItem.getId(), cartItem.getPurchaseQuantity());
		}
		// 返回成功
		return MeitaoResult.ok();
	}

	@Override
	public List<CartItem> getCartList(long userId, boolean isFilterOOSItem) {
		return getCartListFromRedis(REDIS_CART_PRE + ":" + userId, isFilterOOSItem);
	}

	@Override
	public List<CartItem> getCartListByToken(String cartToken, boolean isFilterOOSItem) {
		// TODO Auto-generated method stub
		return getCartListFromRedis(cartToken, isFilterOOSItem);
	}

	private List<CartItem> getCartListFromRedis(String key, boolean isFilterOOSItem) {
		// 根据用户id查询购车列表
		List<String> jsonList = jedisClient.hvals(key);
		List<CartItem> cartItemList = new ArrayList<>();
		if (jsonList == null || jsonList.size() == 0) {
			return cartItemList;
		}
		for (String string : jsonList) {
			// 创建一个TbItem对象
			CartItem cartItem = JsonUtils.jsonToPojo(string, CartItem.class);
			cartItemCheckUpdate(cartItem);
			jedisClient.hset(key, cartItem.getId() + "", JsonUtils.objectToJson(cartItem));
			if (!isFilterOOSItem || isEffective(cartItem)) {
				// 添加到列表
				cartItemList.add(cartItem);
			}
		}
		return cartItemList;
	}
	
	private boolean isEffective(CartItem cartItem) {
		Integer stockNumber = cartItem.getStockNumber();
		Byte status = cartItem.getStatus();
		return stockNumber != null && stockNumber > 0 || status != null && status.equals((byte)1);
	}

	private void cartItemCheckUpdate(CartItem cartItem) {
		Long itemId = cartItem.getId();
		MeitaoItem item = meitaoItemMapper.selectByPrimaryKey(itemId);
		cartItem.setStockNumber(item.getStockNumber());
		cartItem.setPurchaseQuantity(Math.min(cartItem.getPurchaseQuantity(), cartItem.getStockNumber()));
		cartItem.setStatus(item.getStatus());

		MeitaoItemPrice itemPrice = meitaoItemPriceMapper.selectByPrimaryKey(itemId);
		Long salePrice = itemPrice.getSalePrice();
		if (salePrice != null && !salePrice.equals(cartItem.getSalePrice())) {
			cartItem.setSalePrice(salePrice);
		}
		Byte discount = itemPrice.getDiscount();
		if (discount != null && !discount.equals(cartItem.getDiscount())) {
			cartItem.setDiscount(discount);
		}
	}

	@Override
	public MeitaoResult updateCartPurchaseQuantity(long userId, long itemId, int purchaseQuantity) {
		return updateCartPurchaseQuantityInRedis(REDIS_CART_PRE + ":" + userId, itemId, purchaseQuantity);
	}
	
	@Override
	public MeitaoResult updateCartPurchaseQuantityByToken(String cartToken, long itemId, int purchaseQuantity) {
		return updateCartPurchaseQuantityInRedis(cartToken, itemId, purchaseQuantity);
	}
	
	private MeitaoResult updateCartPurchaseQuantityInRedis(String key, long itemId, int purchaseQuantity) {
		String json = jedisClient.hget(key, itemId + "");
		// 更新商品数量
		if (json == null) {
			return MeitaoResult.build(501, "更新购物车失败， 无法找到对应商品id");
		}
		CartItem cartItem = JsonUtils.jsonToPojo(json, CartItem.class);
		if (cartItem == null) {
			return MeitaoResult.build(501, "更新购物车失败， 无法找到对应商品id");
		}
		cartItem.setPurchaseQuantity(purchaseQuantity);
		// 写入redis
		jedisClient.hset(key, itemId + "", JsonUtils.objectToJson(cartItem));
		return MeitaoResult.ok(cartItem);
	}

	@Override
	public MeitaoResult deleteCartItem(long userId, long itemId) {
		// 删除购物车商品
		jedisClient.hdel(REDIS_CART_PRE + ":" + userId, itemId + "");
		return MeitaoResult.ok();
	}
	
	@Override
	public MeitaoResult deleteCartItemByToken(String cartToken, long itemId) {
		jedisClient.hdel(cartToken, itemId + "");
		return MeitaoResult.ok();
	}

	@Override
	public MeitaoResult clearCartItem(long userId) {
		// 删除购物车信息
		jedisClient.del(REDIS_CART_PRE + ":" + userId);
		return MeitaoResult.ok();
	}
}
