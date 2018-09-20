package com.meitaomart.order.service.impl;

import java.util.Date;
import java.util.List;

import org.omg.CORBA.PRIVATE_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.meitaomart.common.jedis.JedisClient;
import com.meitaomart.common.pojo.CartItem;
import com.meitaomart.common.utils.MeitaoResult;
import com.meitaomart.common.utils.ShippingUtils;
import com.meitaomart.common.utils.TaxUtils;
import com.meitaomart.mapper.MeitaoAddressMapper;
import com.meitaomart.mapper.MeitaoOrderItemMapper;
import com.meitaomart.mapper.MeitaoOrderMapper;
import com.meitaomart.order.builder.OrderInfoBuilder;
import com.meitaomart.order.pojo.OrderInfo;
import com.meitaomart.order.service.OrderService;
import com.meitaomart.pojo.MeitaoAddress;
import com.meitaomart.pojo.MeitaoAddressExample;
import com.meitaomart.pojo.MeitaoAddressExample.Criteria;
import com.meitaomart.pojo.MeitaoOrderItem;
import com.meitaomart.pojo.MeitaoUser;

/**
 * 订单处理服务
 * @version 1.0
 */
@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private MeitaoOrderMapper orderMapper;
	@Autowired
	private MeitaoOrderItemMapper orderItemMapper;
	@Autowired
	private MeitaoAddressMapper addressMapper;
	/*@Autowired
	private TbOrderShippingMapper orderShippingMapper;*/
	@Autowired
	private JedisClient jedisClient;
	
	@Value("${ORDER_ID_GEN_KEY}")
	private String ORDER_ID_GEN_KEY;
	@Value("${ORDER_ID_START}")
	private String ORDER_ID_START;
	@Value("${ORDER_DETAIL_ID_GEN_KEY}")
	private String ORDER_DETAIL_ID_GEN_KEY;
	
	@Override
	public MeitaoResult createOrder(OrderInfo orderInfo) {
		//生成订单号。使用redis的incr生成。
		if (!jedisClient.exists(ORDER_ID_GEN_KEY)) {
			jedisClient.set(ORDER_ID_GEN_KEY, ORDER_ID_START);
		}
		String orderId = jedisClient.incr(ORDER_ID_GEN_KEY).toString();
		//补全orderInfo的属性
		orderInfo.setOrderId(orderId);
		//1、未付款，2、已付款，3、未发货，4、已发货，5、交易成功，6、交易关闭
		orderInfo.setStatus((byte)1);
		orderInfo.setCreatedTime(new Date());
		orderInfo.setUpdatedTime(new Date());
		//插入订单表
		orderMapper.insert(orderInfo);
		//向订单明细表插入数据。
		List<MeitaoOrderItem> orderItems = orderInfo.getOrderItems();
		for (MeitaoOrderItem meitaoOrderItem : orderItems) {
			//生成明细id
			String odId = jedisClient.incr(ORDER_DETAIL_ID_GEN_KEY).toString();
			//补全pojo的属性
			meitaoOrderItem.setId(odId);
			meitaoOrderItem.setOrderId(orderId);
			//向明细表插入数据
			orderItemMapper.insert(meitaoOrderItem);
		}
		//向订单物流表插入数据
		/*
		TbOrderShipping orderShipping = orderInfo.getOrderShipping();
		orderShipping.setOrderId(orderId);
		orderShipping.setCreated(new Date());
		orderShipping.setUpdated(new Date());
		orderShippingMapper.insert(orderShipping);
		*/
		//返回MeitaoResult，包含订单号
		return MeitaoResult.ok(orderId);
	}

	@Override
	public OrderInfo getOrderInfo(List<CartItem> cartItemList, MeitaoUser meitaoUser) {
		// TODO Auto-generated method stub
		Long subtotal = 0L;
		Long taxFee = TaxUtils.getTaxFee();
		Long shippingFee = ShippingUtils.getShippingFee();
		
		for (CartItem cartItem : cartItemList) {
			subtotal += cartItem.getSalePrice() * cartItem.getPurchaseQuantity();
		}
		
		OrderInfo orderInfo = OrderInfoBuilder.getInstance().setSubtotal(subtotal).setShippingFee(shippingFee).setTaxFee(taxFee).
				setUserId(meitaoUser.getId()).setUsername(meitaoUser.getUsername()).build();
		return orderInfo;
	}

	@Override
	public List<MeitaoAddress> getAddressList(Long userId) {
		// TODO Auto-generated method stub
		MeitaoAddressExample example = new MeitaoAddressExample();
		Criteria criteria = example.createCriteria();
		criteria.andUserIdEqualTo(userId);
		List<MeitaoAddress> list = addressMapper.selectByExample(example);
		return list;
	}

}
