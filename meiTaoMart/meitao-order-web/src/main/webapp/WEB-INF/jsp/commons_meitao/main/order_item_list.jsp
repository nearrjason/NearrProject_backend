<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<div class="wrap">
	<table>
		<tr class="t-header">
			<td class="h-all">商品名称</td>
			<td class="h-price">单价</td>
			<td class="h-count">数量</td>
			<!-- <td class="h-total">总计</td> -->
			<!-- <td class="h-none"></td> -->
		</tr>
		<c:forEach items="${cartItemList}" var="cartItem"
			varStatus="cartItemUtils">
			<fmt:parseNumber type="number" var="realPrice"
				value="${cartItem.salePrice * (100 - cartItem.discount) / 100 + 0.5}"
				integerOnly="true" />
			<c:set var="subtotalPrice"
				value="${ subtotalPrice + (realPrice * cartItem.purchaseQuantity)}" />
			<tr class="item">
				<td>
					<div class="item-info">
						<img src="${cartItem.oneImage }" alt="">
						<p>${cartItem.name }</p>
					</div>
				</td>
				<td class="unit-price"><span
					id="unitSalePrice${cartItemUtils.index}" value="${realPrice}">
						$<fmt:formatNumber value="${realPrice / 100}"
							maxFractionDigits="2" minFractionDigits="2" />
				</span></td>
				<td class="input-count">
					<p>&times ${cartItem.purchaseQuantity }</p>
				</td>

				<%-- <td class="total"><span
					id="singleTotalPrice${cartItemUtils.index}"
					class="singleTotalPrices"
					value="${realPrice * cartItem.purchaseQuantity}"> $<fmt:formatNumber
							value="${realPrice * cartItem.purchaseQuantity / 100 }"
							maxFractionDigits="2" minFractionDigits="2" />
				</span></td> --%>
				<%-- <td><button type="button" class="d-item"
						onclick="deleteItem(${cartItem.id})">
						<img class="button" src="/images/icons/close.svg" alt="">
					</button></td> --%>
			</tr>
		</c:forEach>
	</table>
</div>
<form id="checkoutOrderItemList">
	<c:forEach items="${cartItemList }" var="cartItem" varStatus="status">
		<input type="hidden" name="itemId" value="${cartItem.id}" />
		<input type="hidden" name="itemNumber"
			value="${cartItem.purchaseQuantity }" />
	</c:forEach>
</form>