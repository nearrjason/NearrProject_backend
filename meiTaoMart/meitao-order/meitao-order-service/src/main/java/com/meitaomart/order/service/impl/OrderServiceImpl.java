package com.meitaomart.order.service.impl;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.omg.CORBA.PRIVATE_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.meitaomart.common.builder.ShippingInfoBuilder;
import com.meitaomart.common.jedis.JedisClient;
import com.meitaomart.common.pojo.CartItem;
import com.meitaomart.common.pojo.EasyUIDataGridResult;
import com.meitaomart.common.pojo.ItemInfo;
import com.meitaomart.common.pojo.ShippingInfo;
import com.meitaomart.common.utils.EmailUtils;
import com.meitaomart.common.utils.ErrorMessageUtils;
import com.meitaomart.common.utils.JsonUtils;
import com.meitaomart.common.utils.MeitaoResult;
import com.meitaomart.common.utils.PaymentUtils;
import com.meitaomart.common.utils.ShippingUtils;
import com.meitaomart.common.utils.TaxUtils;
import com.meitaomart.mapper.MeitaoAddressMapper;
import com.meitaomart.mapper.MeitaoBankingCardMapper;
import com.meitaomart.mapper.MeitaoItemMapper;
import com.meitaomart.mapper.MeitaoItemPriceMapper;
import com.meitaomart.mapper.MeitaoOrderItemMapper;
import com.meitaomart.mapper.MeitaoOrderMapper;
import com.meitaomart.order.builder.OrderInfoBuilder;
import com.meitaomart.order.pojo.OrderInfo;
import com.meitaomart.order.service.OrderService;
import com.meitaomart.pojo.MeitaoAddress;
import com.meitaomart.pojo.MeitaoAddressExample;
import com.meitaomart.pojo.MeitaoAddressExample.Criteria;
import com.meitaomart.pojo.MeitaoBankingCard;
import com.meitaomart.pojo.MeitaoBankingCardExample;
import com.meitaomart.pojo.MeitaoItem;
import com.meitaomart.pojo.MeitaoItemPrice;
import com.meitaomart.pojo.MeitaoOrder;
import com.meitaomart.pojo.MeitaoOrderExample;
import com.meitaomart.pojo.MeitaoOrderItem;
import com.meitaomart.pojo.MeitaoOrderItemExample;
import com.meitaomart.pojo.MeitaoUser;
import com.shippo.model.Transaction;
import com.taxjar.exception.TaxjarException;

/**
 * 订单处理服务
 * 
 * @version 1.0
 */
@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private MeitaoItemMapper itemMapper;
	@Autowired
	private MeitaoOrderMapper orderMapper;
	@Autowired
	private MeitaoOrderItemMapper orderItemMapper;
	@Autowired
	private MeitaoAddressMapper addressMapper;
	@Autowired
	private MeitaoBankingCardMapper cardMapper;
	@Autowired
	private MeitaoItemPriceMapper itemPriceMapper;
	@Autowired
	private JedisClient jedisClient;

	@Value("${ORDER_ID_GEN_KEY}")
	private String ORDER_ID_GEN_KEY;
	@Value("${ORDER_ID_START}")
	private String ORDER_ID_START;
	@Value("${ORDER_DETAIL_ID_GEN_KEY}")
	private String ORDER_DETAIL_ID_GEN_KEY;
	@Value("${DEFAULT_COUNTRY}")
	private String DEFAULT_COUNTRY;
	@Value("${REDIS_CART_PRE}")
	private String REDIS_CART_PRE;
	@Value("${REDIS_ITEM_PRE}")
	private String REDIS_ITEM_PRE;

	@Override
	public OrderInfo getOrderInfo(List<CartItem> cartItemList, MeitaoUser meitaoUser, MeitaoAddress primaryAddress) {
		return getOrderInfo(cartItemList, meitaoUser, primaryAddress, false);
	}

	@Override
	public OrderInfo getOrderInfo(List<CartItem> cartItemList, MeitaoUser meitaoUser, MeitaoAddress primaryAddress,
			Boolean isExpressShipping) {

		Long subtotal = 0L;

		for (CartItem cartItem : cartItemList) {
			Long realPrice = (cartItem.getSalePrice() * (100 - cartItem.getDiscount()) + 50) / 100;
			subtotal += realPrice * cartItem.getPurchaseQuantity();
		}

		Long taxFee = TaxUtils.getTaxFee();
		Long shippingFee = ShippingUtils.getShippingFee(subtotal, isExpressShipping);
		Byte shippingMethod = isExpressShipping != null && isExpressShipping.booleanValue() ? Byte.valueOf((byte) 2)
				: Byte.valueOf((byte) 1);

		if (primaryAddress != null) {
			Float updatedTaxFee = null;
			try {
				updatedTaxFee = TaxUtils.getTaxFee(primaryAddress.getStreet(), primaryAddress.getCity(),
						primaryAddress.getState(), primaryAddress.getZipcode(), subtotal, shippingFee);
			} catch (TaxjarException e) {
				String subject = "tax fee获取异常！";
				String message = "某用户在生成商品详情页面时出现tax fee无法获取异常，请检查tax接口或者用户的地址信息\n该用户所填地址如下\n" + "\nstreet: "
						+ primaryAddress.getStreet() + "\ncity: " + primaryAddress.getCity() + "\nstate: "
						+ primaryAddress.getState() + "\nzipcode: " + primaryAddress.getZipcode() + "\n详情如下:\n"
						+ e.getMessage() + "\n\n对应用户id: ";
				reportAdminByEmail(subject, message, meitaoUser.getId());
			}
			if (updatedTaxFee != null) {
				taxFee = Long.valueOf((long) (updatedTaxFee * 100));
			}
		}

		OrderInfo orderInfo = OrderInfoBuilder.getInstance().setSubtotal(subtotal).setShippingFee(shippingFee)
				.setShippingMethod(shippingMethod).setTaxFee(taxFee).setUserId(meitaoUser.getId())
				.setUsername(meitaoUser.getUsername()).build();

		return orderInfo;
	}

	@Override
	public List<MeitaoOrder> getOrderList() {
		MeitaoOrderExample example = new MeitaoOrderExample();
		List<MeitaoOrder> list = orderMapper.selectByExample(example);
		return list;
	}

	@Override
	public EasyUIDataGridResult getOrderList(int page, int rows) {
		PageHelper.startPage(page, rows);
		// 执行查询
		/*
		 * MeitaoItemExample example = new MeitaoItemExample(); List<MeitaoItem>
		 * list = itemMapper.selectByExample(example);
		 */
		List<MeitaoOrder> list = getOrderList();
		// 创建一个返回值对象
		EasyUIDataGridResult result = new EasyUIDataGridResult();
		result.setRows(list);
		// 取分页结果
		PageInfo<MeitaoOrder> pageInfo = new PageInfo<>(list);
		// 取总记录数
		long total = pageInfo.getTotal();
		result.setTotal(total);
		return result;
	}

	@Override
	public EasyUIDataGridResult getOrderItemList(int page, int rows) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MeitaoOrderItem> getOrderItemList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<OrderInfo> getOrderInfoListByUserId(Long userId) {
		// TODO Auto-generated method stub
		List<OrderInfo> result = new ArrayList<>();
		List<MeitaoOrder> meitaoOrderList = getOrderListByUserId(userId);
		for (MeitaoOrder meitaoOrder : meitaoOrderList) {
			List<MeitaoOrderItem> meitaoOrderItemList = getOrderItemListByOrderId(meitaoOrder.getId());
			OrderInfo orderInfo = createOrderInfo(meitaoOrder, meitaoOrderItemList);
			result.add(orderInfo);
		}

		return result;
	}

	@Override
	public MeitaoResult goToPay(MeitaoUser user, Long addressId, Long cardId, OrderInfo orderInfo,
			List<MeitaoOrderItem> orderItemList, String cvv) {
		MeitaoAddress address = addressMapper.selectByPrimaryKey(addressId);
		MeitaoBankingCard card = cardMapper.selectByPrimaryKey(cardId);
		return goToPay(user, address, null, card, orderInfo, orderItemList, cvv);
	}

	@Override
	public MeitaoResult goToPay(MeitaoUser user, MeitaoAddress shippingAddress, MeitaoAddress billingAddress,
			MeitaoBankingCard card, OrderInfo orderInfo, List<MeitaoOrderItem> orderItemList, String cvv) {
		MeitaoResult result = finalPaymentValidation(user, shippingAddress, card, orderInfo, orderItemList);
		if (!Integer.valueOf(200).equals(result.getStatus())) {
			return result;
		}

		String totalPrice = String
				.valueOf(orderInfo.getSubtotal() + orderInfo.getShippingFee() + orderInfo.getTaxFee());

		String finalPaymentMessage = PaymentUtils.doPayment(user.getEmail(), card.getCardNumber().toString(),
				card.getMonth(), card.getYear(), cvv, "50");

		if (finalPaymentMessage.length() > 0) {
			return MeitaoResult.build(401, finalPaymentMessage);
		}

		List<Long> itemIdList = updateItemStockNumber(orderItemList);

		try {
			String subject = "支付成功！";
			String body = "某用户购买商品支付成功!\n用户信息:" + "\n\nuser id: " + user.getId() + "\nName: "
					+ shippingAddress.getFirstName() + " " + shippingAddress.getLastName() + "\nusername: "
					+ user.getUsername() + "\nuser email: " + user.getEmail();
			EmailUtils.groupSendEmail(subject, body);

			// 创建订单！
			orderInfo.setOrderItems(orderItemList);
			Long orderId = (Long) createOrder(orderInfo, shippingAddress, card, user).getData();

			// 创建ups
			Transaction transaction = goToShipping(user, shippingAddress, orderItemList, orderId);
			if (transaction != null && orderId != null) {
				// 将tracking number加入数据库
				MeitaoOrder orderWithShippingCode = new MeitaoOrder();
				orderWithShippingCode.setShippingCode((String) transaction.getTrackingNumber());
				MeitaoOrderExample example = new MeitaoOrderExample();
				com.meitaomart.pojo.MeitaoOrderExample.Criteria criteria = example.createCriteria();
				criteria.andIdEqualTo(orderId);
				orderMapper.updateByExampleSelective(orderWithShippingCode, example);
			}
		} catch (Exception e) {
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);

			String subject = "系统出现异常";
			String body = sw.toString();
			EmailUtils.groupSendEmail(subject, body);
		}
		return MeitaoResult.ok(itemIdList);
	}

	private List<Long> updateItemStockNumber(List<MeitaoOrderItem> orderItemList) {
		List<Long> itemIdList = new ArrayList<>();
		for (MeitaoOrderItem orderItem : orderItemList) {
			Long itemId = orderItem.getItemId();
			Integer itemNumber = orderItem.getItemNumber();

			if (itemId != null && itemNumber != null) {
				Integer stockNumber = itemMapper.selectStockNumberByPrimaryKey(itemId);
				if (stockNumber != null && stockNumber >= itemNumber) {
					itemIdList.add(itemId);
					MeitaoItem item = new MeitaoItem();
					item.setId(itemId);
					item.setStockNumber(stockNumber - itemNumber);
					itemMapper.updateByPrimaryKeySelective(item);
					// deleteRedis(REDIS_ITEM_PRE + ":" + itemId + ":BASE");
				}
			}
		}

		return itemIdList;
	}

	private OrderInfo createOrderInfo(MeitaoOrder meitaoOrder, List<MeitaoOrderItem> meitaoOrderItemList) {
		OrderInfo orderInfo = OrderInfoBuilder.getInstance().setOrderId(meitaoOrder.getId())
				.setSubtotal(meitaoOrder.getSubtotal()).setPaymentType(meitaoOrder.getPaymentType())
				.setShippingFee(meitaoOrder.getShippingFee()).setTaxFee(meitaoOrder.getTaxFee())
				.setStatus(meitaoOrder.getStatus()).setCreatedTime(meitaoOrder.getCreatedTime())
				.setUpdatedTime(meitaoOrder.getUpdatedTime()).setPaymentTime(meitaoOrder.getPaymentTime())
				.setConsignTime(meitaoOrder.getConsignTime()).setEndTime(meitaoOrder.getEndTime())
				.setCloseTime(meitaoOrder.getCloseTime()).setShippingName(meitaoOrder.getShippingName())
				.setShippingCode(meitaoOrder.getShippingCode()).setUserId(meitaoOrder.getUserId())
				.setUserComment(meitaoOrder.getUserComment()).setUsername(meitaoOrder.getUsername())
				.setOrderItems(meitaoOrderItemList).setAddressId(meitaoOrder.getAddressId())
				.setCardId(meitaoOrder.getCardId()).build();
		return orderInfo;
	}

	private List<MeitaoOrder> getOrderListByUserId(Long userId) {
		MeitaoOrderExample example = new MeitaoOrderExample();
		example.setOrderByClause("created_time DESC");
		com.meitaomart.pojo.MeitaoOrderExample.Criteria criteria = example.createCriteria();
		criteria.andUserIdEqualTo(userId);
		List<MeitaoOrder> list = orderMapper.selectByExample(example);
		return list;
	}

	private List<MeitaoOrderItem> getOrderItemListByOrderId(Long orderId) {
		MeitaoOrderItemExample example = new MeitaoOrderItemExample();
		com.meitaomart.pojo.MeitaoOrderItemExample.Criteria criteria = example.createCriteria();
		criteria.andOrderIdEqualTo(orderId);
		List<MeitaoOrderItem> list = orderItemMapper.selectByExample(example);
		return list;
	}

	private Transaction goToShipping(MeitaoUser user, MeitaoAddress address, List<MeitaoOrderItem> orderItemList,
			Long orderId) {
		int totalWeight = 0;
		for (MeitaoOrderItem orderItem : orderItemList) {
			MeitaoItem item = itemMapper.selectByPrimaryKey(orderItem.getItemId());
			if (item != null && item.getNetWeight() != null) {
				totalWeight += item.getNetWeight();
			}
		}

		ShippingInfo shippingInfo = ShippingInfoBuilder.getInstance().setFirstName(address.getFirstName())
				.setLastName(address.getLastName()).setPhoneNumber(address.getShippingPhone())
				.setEmailAddress(user.getEmail()).setStreet(address.getStreet()).setCity(address.getCity())
				.setState(address.getState()).setZip(address.getZipcode()).setTotalWeight(totalWeight)
				.setOrderId(orderId).build();
		Transaction transaction = ShippingUtils.getShippingInfo(shippingInfo);
		String subject = null;
		String body = null;
		if (transaction != null) {
			subject = "创建shipping infomation成功!";
			body = "label url: " + transaction.getLabelUrl() + "\ntracking number: " + transaction.getTrackingNumber()
					+ "\norder id: " + orderId + "\n\nuser id: " + user.getId() + "\nName: " + address.getFirstName()
					+ " " + address.getLastName() + "\nusername: " + user.getUsername() + "\nuser email: "
					+ user.getEmail();
			EmailUtils.groupSendEmail(subject, body);
		}

		return transaction;
	}

	private MeitaoResult createOrder(OrderInfo orderInfo, MeitaoAddress address, MeitaoBankingCard card,
			MeitaoUser user) {
		/**
		 * orderInfo信息完善
		 */
		// 生成订单号。使用redis的incr生成。
		if (!jedisClient.exists(ORDER_ID_GEN_KEY)) {
			jedisClient.set(ORDER_ID_GEN_KEY, ORDER_ID_START);
		}

		Long orderId = jedisClient.incr(ORDER_ID_GEN_KEY);
		// 补全orderInfo的属性
		orderInfo.setId(orderId);
		// 1、未发货，2、已发货
		orderInfo.setStatus((byte) 1);

		orderInfo.setCreatedTime(new Date());
		orderInfo.setUpdatedTime(new Date());
		// 第一期我们认为， 产生订单即支付
		orderInfo.setPaymentTime(new Date());
		// 把用户信息添加到orderInfo中。
		orderInfo.setUserId(user.getId());
		orderInfo.setUsername(user.getUsername());

		/**
		 * address信息完善
		 */
		address.setUserId(user.getId());
		address.setCreatedTime(new Date());
		// 默认国家是United State
		if (address.getCountry() == null || address.getCountry().length() == 0) {
			address.setCountry(DEFAULT_COUNTRY);
		}

		// address加入数据库
		Long addressId = address.getId();
		if (addressId == null) {
			String subject = "某用户地址id丢失";
			String body = "某用户支付成功却无法得到地址id, 请立即检查，否则会影响订单创建， 对应用户id: ";
			reportAdminByEmail(subject, body, user.getId());
		}
		orderInfo.setAddressId(addressId);

		// card加入数据库
		Long cardId = card.getId();
		if (cardId == null) {
			String subject = "某用户银行卡id丢失";
			String body = "某用户支付成功却无法得到银行卡id, 请立即检查，否则会影响订单创建， 对应用户id: ";
			reportAdminByEmail(subject, body, user.getId());
		}
		orderInfo.setCardId(cardId);

		// 订单加入数据库
		orderMapper.insert(orderInfo);

		// 订单明细表加入数据库
		List<MeitaoOrderItem> orderItems = orderInfo.getOrderItems();
		for (MeitaoOrderItem meitaoOrderItem : orderItems) {
			// 生成明细id
			Long orderItemId = jedisClient.incr(ORDER_DETAIL_ID_GEN_KEY);
			// 补全pojo的属性
			meitaoOrderItem.setId(orderItemId);
			meitaoOrderItem.setOrderId(orderId);
			// 向明细表插入数据
			orderItemMapper.insert(meitaoOrderItem);
		}

		// 返回MeitaoResult，包含订单号
		return MeitaoResult.ok(orderId);
	}

	private MeitaoResult finalPaymentValidation(MeitaoUser user, MeitaoAddress shippingAddress, MeitaoBankingCard card,
			OrderInfo orderInfo, List<MeitaoOrderItem> orderItemList) {
		if (user == null || shippingAddress == null || card == null || orderInfo == null || orderItemList == null) {
			String subject = "美桃服务器错误！";
			String body = "某用户在支付时出现空指针，请立即检查！对应用户id(若无法检测到用户，则不显示): ";
			reportAdminByEmail(subject, body, user != null ? user.getId() : null);
			return MeitaoResult.build(500, ErrorMessageUtils.ERROR_MEESAGE_FOR_MEITAO_SYSTEM_EXCEPTION);
		}

		if (shippingAddress.getLastName() == null || shippingAddress.getLastName().length() == 0
				|| shippingAddress.getFirstName() == null || shippingAddress.getFirstName().length() == 0
				|| shippingAddress.getShippingPhone() == null || shippingAddress.getShippingPhone().length() == 0
				|| shippingAddress.getStreet() == null || shippingAddress.getStreet().length() == 0
				|| shippingAddress.getCity() == null || shippingAddress.getCity().length() == 0
				|| shippingAddress.getState() == null || shippingAddress.getState().length() == 0
				|| shippingAddress.getZipcode() == null || shippingAddress.getZipcode().length() == 0) {
			String subject = "地址检测错误！";
			String body = "某用户在支付时出现地址信息不完全，请立即检查！对应用户id(若无法检测到用户，则不显示): ";
			reportAdminByEmail(subject, body, user != null ? user.getId() : null);
			return MeitaoResult.build(201, "地址信息不完整！");
		}

		if (card.getCardNumber() == null || card.getFirstName() == null || card.getFirstName().length() == 0
				|| card.getLastName() == null || card.getLastName().length() == 0 || card.getMonth() == null
				|| card.getMonth().length() == 0 || card.getYear() == null || card.getYear().length() == 0) {
			String subject = "银行卡检测错误！";
			String body = "某用户在支付时出现银行卡信息不完全，请立即检查！对应用户id(若无法检测到用户，则不显示): ";
			reportAdminByEmail(subject, body, user != null ? user.getId() : null);
			return MeitaoResult.build(201, "银行卡信息不完整！");
		}

		if (orderInfo.getSubtotal() == null || orderInfo.getTaxFee() == null || orderInfo.getShippingFee() == null) {
			String subject = "美桃服务器错误！";
			String body = "某用户在支付时价格为空，请立即检查！对应用户id： ";
			reportAdminByEmail(subject, body, user.getId());
			return MeitaoResult.build(500, ErrorMessageUtils.ERROR_MEESAGE_FOR_MEITAO_SYSTEM_EXCEPTION);
		}

		MeitaoResult orderItemListValidationResult = orderItemListValidation(orderItemList, user);
		if (!Integer.valueOf(200).equals(orderItemListValidationResult.getStatus())) {
			return orderItemListValidationResult;
		}

		if (!priceValidation(orderItemList, orderInfo.getSubtotal())) {
			String subject = "用户支付时出现商品价格与数据库价格不一致！";
			String message = "如果近期没有价格调整，可能涉及有人修改价格以及数据安全问题！请立即检查\n对应用户id: ";
			reportAdminByEmail(subject, message, user.getId());

			return MeitaoResult.build(201, "对不起! 您支付的商品中存在近期价格调整！请返回购物车确认商品价格并重新提交！");
		}

		return MeitaoResult.ok();
	}

	private boolean priceValidation(List<MeitaoOrderItem> orderItemList, Long subtotalFromOrder) {
		Long subtotalFromDatabase = 0L;
		for (MeitaoOrderItem orderItem : orderItemList) {
			MeitaoItemPrice meitaoItemPrice = itemPriceMapper.selectByPrimaryKey(orderItem.getItemId());
			Long realPrice = (meitaoItemPrice.getSalePrice() * (100 - meitaoItemPrice.getDiscount()) + 50) / 100;
			subtotalFromDatabase += realPrice * orderItem.getItemNumber();
		}
		return subtotalFromDatabase != null && subtotalFromOrder != null
				&& subtotalFromOrder.equals(subtotalFromDatabase);
	}

	/**
	 * 订单商品列表的校验(库存数量校验和与购物车一致性校验)
	 * 
	 * @param orderItemList
	 * @return
	 */
	private MeitaoResult orderItemListValidation(List<MeitaoOrderItem> orderItemList, MeitaoUser user) {
		if (orderItemList == null || orderItemList.size() == 0) {
			String subject = "某用户支付的商品列表中没有商品！";
			String message = "对应用户id: ";
			reportAdminByEmail(subject, message, user.getId());
			return MeitaoResult.build(201, "购物车为空！");
		}

		/*
		 * 库存数量校验
		 */
		StringBuilder outOfStockList = new StringBuilder();
		for (MeitaoOrderItem orderItem : orderItemList) {
			Long itemId = orderItem.getItemId();
			Integer itemNumber = orderItem.getItemNumber();

			if (itemId == null || itemNumber == null) {
				return MeitaoResult.build(400, "无法检测到商品信息！");
			}
			
			MeitaoItem item = itemMapper.selectByPrimaryKey(itemId);
			if (item == null) {
				String subject = "商品id错误！";
				String message = "某用户支付时商品id无法找到对应的商品\n对应商品id: ";
				reportAdminByEmail(subject, message, itemId);
				return MeitaoResult.build(400, "此商品id没有对应的商品: " + itemId);
			}
			
			if (item.getStatus() == null || !item.getStatus().equals((byte)1)) {
				String subject ="商品已下架或已删除!";
				String message ="某用户在支付时出现商品已下架或已删除!\n对应商品id: " + itemId + "\n对应用户id: " + user.getId();
				reportAdminByEmail(subject, message);
				return MeitaoResult.build(202, "对不起，您的订单中有近期下架商品: " + item.getName() + ", 点击确定将过滤已下架商品!");
			}

			Integer stockNumber = itemMapper.selectStockNumberByPrimaryKey(itemId);
			if (stockNumber == null) {
				String subject = "数据库发生错误！";
				String message = "数据库中商品库存为空\n对应商品id: ";
				reportAdminByEmail(subject, message, itemId);
				return MeitaoResult.build(400, message + itemId);
			}

			if (itemNumber.compareTo(stockNumber) > 0) {
				String itemName = itemMapper.selectItemNameByPrimaryKey(itemId);
				outOfStockList.append("\n" + itemName);
			}
		}

		if (outOfStockList.length() > 0) {
			String msg = "对不起，以下商品库存不足： \n" + outOfStockList.toString() + "\n\n点击返回购物车查看最新商品信息！";
			String subject = "用户支付时库存不足！";
			String message = "用户在支付以下商品时库存不足:\n" + outOfStockList.toString() + "\n\n对应用户id: ";
			reportAdminByEmail(subject, message, user.getId());
			return MeitaoResult.build(201, msg);
		}

		/*
		 * 与购物车一致性校验
		 */
		List<CartItem> cartItemList = getCartList(user.getId());
		if (!itemLastComparator(cartItemList, orderItemList)) {
			String subject = "某用户出现商品与购物车信息不一致！";
			String message = "此情况属于正常，如国在短时间内多次收到此消息，请联系用户并检查校验代码\n对应用户id: ";
			reportAdminByEmail(subject, message, user.getId());
			return MeitaoResult.build(202, "您的购物车有更新，点击确定将最新购物车信息更新到结算中心！");
		}
		return MeitaoResult.ok();
	}

	private boolean itemLastComparator(List<CartItem> cartItemList, List<MeitaoOrderItem> orderItemList) {
		Map<Long, Integer> mapFromIdToQuantity = new HashMap<>();
		for (CartItem cartItem : cartItemList) {
			Integer purchaseQuantity = cartItem.getPurchaseQuantity();
			Integer stockNumber = cartItem.getStockNumber();
			if (purchaseQuantity != null && purchaseQuantity > 0 && stockNumber != null && stockNumber > 0) {
				mapFromIdToQuantity.put(cartItem.getId(), purchaseQuantity);
			}
		}

		for (MeitaoOrderItem orderItem : orderItemList) {
			Integer purchaseQuantity = mapFromIdToQuantity.get(orderItem.getItemId());
			if (purchaseQuantity == null || !purchaseQuantity.equals(orderItem.getItemNumber())) {
				return false;
			}
			mapFromIdToQuantity.remove(orderItem.getItemId());
		}

		return mapFromIdToQuantity.size() == 0;
	}

	private List<CartItem> getCartList(long userId) {
		// 根据用户id查询购车列表
		List<String> jsonList = jedisClient.hvals(REDIS_CART_PRE + ":" + userId);
		List<CartItem> cartItemList = new ArrayList<>();
		for (String string : jsonList) {
			// 创建一个TbItem对象
			CartItem cartItem = JsonUtils.jsonToPojo(string, CartItem.class);
			// 添加到列表
			cartItemList.add(cartItem);
		}
		return cartItemList;
	}

	private void reportAdminByEmail(String subject, String message, Long id) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		// body
		String body = "" + message + id + "\n" + dateFormat.format(date);
		EmailUtils.groupSendEmail(subject, body);
	}

	private void reportAdminByEmail(String subject, String message) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		// body
		String body = "" + message + "\n" + dateFormat.format(date);
		EmailUtils.groupSendEmail(subject, body);
	}

	private void deleteRedis(String key) {
		if (jedisClient.exists(key)) {
			try {
				jedisClient.del(key);
			} catch (Exception e) {
				EmailUtils.groupSendEmailForJavaException(e.getStackTrace().toString());
			}
		}
	}
}
