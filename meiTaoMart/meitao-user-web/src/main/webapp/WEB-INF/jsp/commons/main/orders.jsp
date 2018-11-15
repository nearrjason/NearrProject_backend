<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<div class="right" id="ordersBox">
	<h2>我的订单</h2>
	<hr>
	<div class="orders">
		<!--single order overview-->
		<c:forEach items="${orderInfoList}" var="orderInfo"
			varStatus="orderInfoUtils">
			<c:set var="totalPrice"
				value="${ orderInfo.subtotal + orderInfo.shippingFee + orderInfo.taxFee}" />
			<div class="orders-info">
				<div class="order-time">
					<p class="date">${orderInfo.createdTime}</p>
					<p>订单号：${orderInfo.id}</p>
				</div>

				<div class="order-item">
					<div class="items">
						<c:forEach items="${orderInfo.orderItemsInfo}" var="orderItemInfo">
							<div class="item">
								<div class="i-name">
									<div class="wrap1">
										<img src="${orderItemInfo.oneImage }" alt="">
									</div>
									<div class="wrap2">
										<p>${orderItemInfo.name }${orderItemInfo.netWeight}g</p>
									</div>
								</div>
								<c:forEach items="${orderInfo.orderItems}" var="orderItem">
									<c:if test="${orderItemInfo.id eq orderItem.itemId }">
										<p class="amount">&times; ${orderItem.itemNumber }</p>
									</c:if>

								</c:forEach>
							</div>
						</c:forEach>
					</div>

					<div class="price">
						<p class="label">总金额</p>
						<p>
							$
							<fmt:formatNumber value="${totalPrice / 100}"
								maxFractionDigits="2" minFractionDigits="2" groupingUsed="true" />
						</p>
					</div>

					<div class="tracking">
						<p>追踪号</p>
						<a href=""> <c:if test="${orderInfo. shippingCode != null}">
								<p>${orderInfo. shippingCode}</p>
							</c:if> <c:if test="${orderInfo. shippingCode == null}">
								<p>未发货</p>
							</c:if>

						</a>
					</div>

					<div class="detail">
						<a class="order-detail" index="${orderInfoUtils.index}"
							href="javascript:void(0)" onclick="checkOrderDetail(this)"><p>查看详情</p></a>
					</div>
				</div>
			</div>
		</c:forEach>
	</div>
</div>


