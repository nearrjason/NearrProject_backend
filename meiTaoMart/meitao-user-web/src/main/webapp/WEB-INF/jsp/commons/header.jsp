<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<div class="header">
	<div class="logo">
		<a href="http://192.168.1.100"><img src="/images/logo.svg" alt=""></a>
	</div>

	<div class="search">
		<form class="search_form"
			action="http://192.168.1.100:8085/search.html" id="searchForm"
			name="query" method="GET">
			<input type="text" class="text" name="keyword" id="keyword" value=""
				style="color: rgb(153, 153, 153);" autocomplete="off">
			<button id="searchButton" class="submit-btn">
				<img src="/images/icons/search-dark.svg" alt="">
			</button>
		</form>
	</div>

	<div class="cart">
		<div class="wrapper" id="cart">
			<c:forEach items="${cartItemList}" var="cartItem">
				<c:set var="totalQuantity"
				value="${ totalQuantity + cartItem.purchaseQuantity}" />
			</c:forEach>
			<a href="http://192.168.1.100:8090/cart/cart.html" class="shopping-cart"><img
				src="/images/icons/shopping-cart.svg" alt="">购物车&nbsp; 
				<span>
					<c:if test="${fn:length(cartItemList) > 0}">
						${totalQuantity}
					</c:if>
					<c:if test="${fn:length(cartItemList) == 0}">
						0
					</c:if>
				</span>
			</a>
			<!--shopping cart popup-->
			<c:if test="${fn:length(cartItemList) > 0}">
				<div class="cart-popup" id="cart-popup">

					<div class="cart-view">
						<c:forEach items="${cartItemList}" var="cartItem"
							varStatus="cartItemUtils">
							<fmt:parseNumber type="number" var="realPrice"
								value="${cartItem.salePrice * (100 - cartItem.discount) / 100 + 0.5}"
								integerOnly="true" />
							<%-- <c:set var="subtotalPrice"
								value="${ subtotalPrice + (realPrice * cartItem.purchaseQuantity)}" /> --%>
							<div class="s-item">
								<div class="div1">
									<img src="${cartItem.oneImage }" alt="">
								</div>
								<div class="div2">
									<p class="itemName">${cartItem.name }${cartItem.netWeight }g
									</h2>
									<p class="itemCount">数量：${cartItem.purchaseQuantity }</p>
								</div>
								<div class="div3">
									<p class="itemPrice">
										$
										<fmt:formatNumber value="${realPrice / 100}"
											maxFractionDigits="2" minFractionDigits="2" />
									</p>
									<!-- <a href="">删除</a> -->
								</div>
							</div>
							<hr>
						</c:forEach>
					</div>
					<hr>
					<div class="check-cart">
						<a href="http://192.168.1.100:8090/cart/cart.html">查看购物车</a>
					</div>
				</div>
			</c:if>
		</div>
	</div>
	
	<script src="/js/popup.js" type="text/javascript"></script>
</div>