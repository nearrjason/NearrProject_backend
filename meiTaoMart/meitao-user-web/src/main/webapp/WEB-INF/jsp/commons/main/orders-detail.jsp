<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:forEach items="${orderInfoList}" var="orderInfo" varStatus="status">
	<div class="rdRight orderDetailBox" id="orderDetailBox${status.index }">
		<h2>
			<a id="allOrders">我的订单</a>&emsp;>&emsp;订单详情
		</h2>
		<hr>
		<c:set var="totalPrice"
			value="${ orderInfo.subtotal + orderInfo.shippingFee + orderInfo.taxFee}" />
		<c:set var="cardNumberStr"
			value="${fn:trim(orderInfo.orderCard.cardNumber)}"></c:set>
		<div class="rdorder">
			<div class="rdorders-info">
				<p class="rdtime">
					<span>${orderInfo.createdTime}</span>
				</p>
				<p class="rdorder-number">
					订单号： <span>${orderInfo.id}</span>
				</p>
				<p class="rdtracking">
					追踪码： 
					<a href=""> 
						<c:if
							test="${orderInfo. shippingCode != null}">
							${orderInfo. shippingCode}
						</c:if> 
						<c:if test="${orderInfo. shippingCode == null}">
							未发货
						</c:if>
					</a>
				</p>
				<p class="rdtotal-p">
					合计总额： <span>$<fmt:formatNumber value="${totalPrice / 100}"
							maxFractionDigits="2" minFractionDigits="2" /></span>
				</p>
			</div>
			<div class="rdrecipient-info">
				<p class="rdseller">
					发货商： <br> <span>美桃网</span>
				</p>
				<p class="rdshipping-method">
					配送方式： <br>
					<c:if test="${orderInfo.shippingMethod == 1 }">
						<span>UPS Ground Shipping</span>
					</c:if>
					<c:if test="${ orderInfo.shippingMethod == 2}">
						<span>UPS Express Shipping</span>
					</c:if>

				</p>
				<p class="rdaddress">
					收货信息： <br> <span>${orderInfo.orderAddress.firstName}
						${orderInfo.orderAddress.lastName} <br>
					</span> <span> ${orderInfo.orderAddress.street},
						${orderInfo.orderAddress.city} ${orderInfo.orderAddress.state}
						${orderInfo.orderAddress.zipcode}</span>
				</p>
				<p class="rdpayment">
					支付方式： <br> <span><img src="../../icons/visa.svg" alt=""><br>****
						**** **** ${fn:substring(cardNumberStr, 12, 16)} </span>
				</p>
			</div>
			<div class="rditems">
				<table>
					<tr class="rdt-header">
						<td class="rdh-all">商品名称</td>
						<td class="rdh-price">单价</td>
						<td class="rdh-count">数量</td>
						<td class="rdh-total">总计</td>
					</tr>
					<c:forEach items="${orderInfo.orderItemsInfo}" var="orderItemInfo"
						varStatus="orderInfoUtils">
						<tr class="rditem">
							<td>
								<div class="rditem-info">
									<img src="${orderItemInfo.oneImage }" alt="">
									<p>${orderItemInfo.name }</p>
								</div>
							</td>
							<td class="rdunit-price">$<fmt:formatNumber
									value="${orderItemInfo.salePrice / 100}" maxFractionDigits="2"
									minFractionDigits="2" />
							</td>
							<c:forEach items="${orderInfo.orderItems}" var="orderItem">
								<c:if test="${orderItemInfo.id eq orderItem.itemId }">
									<td class="rdinput-count">
										<p class="amount">&times; ${orderItem.itemNumber }</p>
									</td>
									<td class="rdtotal">$<fmt:formatNumber
											value="${orderItemInfo.salePrice * orderItem.itemNumber / 100}"
											maxFractionDigits="2" minFractionDigits="2" />
									</td>
								</c:if>
							</c:forEach>
						</tr>
					</c:forEach>
				</table>
			</div>

			<div class="back">
				<button class="back-btn" type="button">返回全部订单</button>
			</div>
		</div>

	</div>
</c:forEach>