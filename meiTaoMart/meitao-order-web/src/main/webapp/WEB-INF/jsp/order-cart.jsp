<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0" />
<meta name="format-detection" content="telephone=no" />
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<meta name="format-detection" content="telephone=no" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>订单结算页 -美桃商城</title>
<!--结算页面样式-->
<link rel="stylesheet" type="text/css"
	href="/css/jquery.alerts.css?v=20160713" />
<link rel="stylesheet" type="text/css" href="/css/head.css?v=20160713" />
<link rel="stylesheet" type="text/css" href="/css/order.css?v=20160713">
	<script type="text/javascript" src="/js/jquery-1.5.1.min.js?v=20160713"></script>
	<script type="text/javascript" src="/js/jquery.alerts.js?v=20160713"></script>
	<script type="text/javascript" src="/js/cart.js?v=20160713"></script>
	<script type="text/javascript" src="/js/cookie.js?v=20160416222"></script>
	<script type="text/javascript" src="/js/shadow.js?v=20160416"></script>
	<script src="/js/common.js?v=20160713" type="text/javascript"></script>
	<script src="/js/jquery.region.js?v=20160713" type="text/javascript"></script>
	<script src="/js/order.js?v=20160713" type="text/javascript"></script>
</head>
<body id="mainframe">
	<jsp:include page="commons/header.jsp" />
	<div class="orderMain">
		<%-- <form id="orderForm" class="hide" action="/order/create.html"
			method="post">
			<input type="hidden" name="paymentType" value="2" /> <input
				type="hidden" name="postFee" value="10" />
			<c:set var="totalPrice"
				value="${ orderInfo.subtotal + orderInfo.shippingFee + orderInfo.taxFee}" />
			<c:forEach items="${cartItemList }" var="cartItem" varStatus="status">
				<input type="hidden" name="orderItems[${status.index}].itemId"
					value="${cartItem.id}" />
				<input type="hidden"
					name="orderItems[${status.index}].purchaseQuantity"
					value="${cartItem.purchaseQuantity }" />
				<input type="hidden" name="orderItems[${status.index}].salePrice"
					value="${cartItem.salePrice}" />
				<input type="hidden" name="orderItems[${status.index}].totalPrice"
					value="${cartItem.salePrice * cartItem.purchaseQuantity}" />
				<input type="hidden" name="orderItems[${status.index}].name"
					value="${cartItem.name}" />
				<input type="hidden" name="orderItems[${status.index}].oneImage"
					value="${cartItem.oneImage}" />
			</c:forEach>
			<input type="hidden" name="payment"
				value="<fmt:formatNumber groupingUsed="false" maxFractionDigits="2" minFractionDigits="2" value="${totalPrice/100 }"/>" />
			<input type="hidden" name="orderShipping.receiverName"
				value="${user.username}" /> <input type="hidden"
				name="orderShipping.receiverMobile" value="${user.phone}" /> <input
				type="hidden" name="orderShipping.receiverState" value="冰岛" /> <input
				type="hidden" name="orderShipping.receiverCity" value="雷克雅未克" /> <input
				type="hidden" name="orderShipping.receiverDistrict" value="电信1区" />
			<input type="hidden" name="orderShipping.receiverAddress" value="三里屯" />
		</form> --%>
		<h3 class="orderHd">填写并核对订单信息</h3>
		<div id="userAddrId" class="orderTbody ">
			<h3 class="orderTitle">
				收货人信息：<span class="addrAlter" style="display: inline;">[修改]</span>
			</h3>
			<div class="orderItem">
				<div class="orderCurr" id="userAddress" style="display: block;">
					<ul>
						<li>骆安 827 S Carpenter St</li>
					</ul>
				</div>
			</div>
		</div>
		<div id="payDataId" class="orderTbody">
			<h3 class="orderTitle">
				支付及配送方式： <span class="addrAlter hide" style="display: inline;">[修改]</span>
			</h3>
			<div class="orderItem ">
				<div class="orderCurr" style="display: block;">
					<div class="payment" id="payshow">在线支付</div>
					<div class="shipment">
						<div class="shipOrder" id="ylsorder2">
							<div class="sOrders">
								<span class="tOrder" na="chai1">订单1</span>
								<div class="sOrders">
									<div class="scon">
										<strong>美桃</strong>负责配送。<br>商品下单后尽快为您发货 
									</div>
								</div>
								<span class="clear"></span>
							</div>
						</div>
						<span class="clear"></span>
					</div>
				</div>
			</div>
		</div>

		<div id="userInvoiceId" class="orderTbody">
			<a name="userInvoice"></a>
			<!-- <h3 class="orderTitle">发票信息：<span class="addrAlter" id="invoice_edit" onclick="invoice_edit()">[修改]</span></h3>
    <div class="orderItem">
      <div class="orderCurr invMsg">
     <div id="p_inv_type">暂不需要发票</div>
            <div id="p_inv_title"></div>
        <div id="p_inv_content"></div>      </div>
    </div> -->
		</div>
		<div class="orderTbody" style="width: 958px; border-bottom: 0 none;">
			<div class="backToCart">
				<a style="text-decoration: underline;" target=""
					href="http://192.168.1.100:8090/cart/cart.html">返回修改购物车</a>
			</div>
			<h3 class="orderTitle">商品信息：</h3>
			<span class="clear"></span>
			<div class="orderPItem">
				<div id="producthtml">

					<div id="chaidanorder2" class="">
						<div class="orderItem">
							<div class="dateShow">
								<strong>商品下单后尽快为您发货</strong>
							</div>
							<div class="orderTbl">
								<div class="orderThead">
									<div class="tCol">商品图片</div>
									<div class="tCol pGoods">商品名称</div>
									<div class="tCol pPrice">单价</div>
									<div class="tCol pPromotion">返现</div>
									<div class="tCol pInventory">库存状态</div>
									<div class="tCol pQuantity">数量</div>
									<div class="tCol pWeight">重量(含包装)</div>
									<div class="tCol pSubtotal">小计</div>
								</div>
								<c:set var="totalPrice"
									value="${ orderInfo.subtotal + orderInfo.shippingFee + orderInfo.taxFee}" />
								<c:forEach items="${cartItemList }" var="cartItem"
									varStatus="status">

									<div class="orderPInfo">
										<div class="pItem">
											<a href="http://192.168.1.100:8086/item/${cartItem.id}.html"
												target="_blank"> <img src="${cartItem.oneImage }"
												style="width: 60px; height: 60px;" />
											</a>
										</div>
										<div class="pItem pGoods">
											<a href="http://192.168.1.100:8086/item/${cartItem.id}.html"
												target="_blank">${cartItem.name }</a>
										</div>
										<div class="pItem pPrice"
											style="position: relative; z-index: 99">
											$
											<fmt:formatNumber groupingUsed="false" maxFractionDigits="2"
												minFractionDigits="2" value="${cartItem.salePrice / 100 }" />
										</div>
										<div class="pItem pPromotion">$0</div>
										<div class="pItem pInventory" pid="57111">现货</div>
										<div class="pItem pQuantity">${cartItem.purchaseQuantity}</div>
										<div class="pItem pWeight">${cartItem.netWeight}g</div>
										<div class="pItem pSubtotal">$${cartItem.salePrice *
											cartItem.purchaseQuantity / 100}</div>
										<span class="clear"></span>
									</div>
								</c:forEach>
								<div class="orderPInfo order_xj">
									<span class="sp1">运费小计：</span> <span class="sp2">${orderInfo.shippingFee / 100}</span>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="orderItem" style="position: relative; padding-top: 10px;">

				<div id="ordercoupon1" class="ordercoupon">
					<span id="ordercoupon"></span>
				</div>
				<div class="order-info" style="z-index: -1;">
					<div class="orderWeight fl">商品总重量：${cartItem.netWeight }g（含包装）</div>
					<div id="priceitems" class="priceitem">
						<dl class="fl">
							<dd>
								<div>总商品金额：</div>
								<span class="priceArea">$<span id="yingProce"> <fmt:formatNumber
											value="${orderInfo.subtotal / 100}" maxFractionDigits="2"
											minFractionDigits="2" groupingUsed="true" />
								</span></span>
							</dd>
							<dd>
								<div>运费金额：</div>
								<span class="priceArea">$<span id="shopping_price">${orderInfo.shippingFee / 100}</span></span>
							</dd>
							<dd>
								<div>tax：</div>
								<span class="priceArea">$<span id="shopping_price">${orderInfo.taxFee / 100}</span></span>
							</dd>
							<dd>
								<div>应付总额：</div>
								<span class="priceArea">$<span id="allPrice"> <fmt:formatNumber
											value="${totalPrice / 100}" maxFractionDigits="2"
											minFractionDigits="2" groupingUsed="true" />
								</span></span>
							</dd>
						</dl>
					</div>
				</div>
			</div>
		</div>
		<div class="orderTFoot">
			<div id="orderSaveTip" class="ct"></div>
			<div class="submitOrder">
				<div class="orderSubmit">
					<input id="save" name="save" onclick="$('#orderForm').submit()"
						class="submitBtn" value="提交订单 " type="button" />
				</div>
				<div class="orderAccount">
					<span class="t">应付总额：</span> <span class="p">$</span> <span
						id="countPrice" class="p"><fmt:formatNumber
							value="${totalPrice / 100}" maxFractionDigits="2"
							minFractionDigits="2" groupingUsed="true" /> </span>
				</div>
				<span class="clear"></span>
			</div>
		</div>
	</div>
	<!-- /main -->
	<jsp:include page="commons/footer.jsp" />
</body>
</html>