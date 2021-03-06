<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="totalPrice"
	value="${ orderInfo.subtotal + orderInfo.shippingFee + orderInfo.taxFee}" />
<div class="total-price">
	<span class="customfont"><b>合计</b></span> <span class="tprice">
		$<fmt:formatNumber value="${orderInfo.subtotal / 100}"
			maxFractionDigits="2" minFractionDigits="2" groupingUsed="true" />
	</span>
</div>
<br>
<div class="ship-to">
	<span class="customfont">配送至</span> <span id="finalZipCode" class="sto">${primaryAddress.zipcode}</span>
</div>
<br>
<div class="tax-price">
	<span class="customfont">税费</span>
	<c:if test="${orderInfo.taxFee != null }">
		<span class="tprice"> $<fmt:formatNumber
				value="${orderInfo.taxFee / 100}" maxFractionDigits="2"
				minFractionDigits="2" groupingUsed="true" />
		</span>
	</c:if>
	<c:if test="${orderInfo.taxFee == null }">
		<span class="tprice"> - </span>
	</c:if>

</div>
<br>
<div class="ship-price">
	<span class="customfont">邮费</span>
	<c:if test="${orderInfo.shippingFee > 0 }">
		<span class="sto"> $<fmt:formatNumber
				value="${orderInfo.shippingFee / 100}" maxFractionDigits="2"
				minFractionDigits="2" groupingUsed="true" />
		</span>
	</c:if>
	<c:if test="${orderInfo.shippingFee == 0 }">
		<span class="sto"> Free Shipping </span>
	</c:if>
</div>
<br>

<div class="cal-total-price">
	<span class="customfont"><b>总计</b></span> <span class="ctprice">
		$<fmt:formatNumber value="${totalPrice / 100}" maxFractionDigits="2"
			minFractionDigits="2" groupingUsed="true" />
	</span>
</div>
<form id="checkoutOrder" method="post">
	<input type="hidden" name="subtotal" value="${orderInfo.subtotal }" />
	<input type="hidden" name="shippingFee"
		value="${orderInfo.shippingFee }" /> <input type="hidden"
		name="taxFee" value="${orderInfo.taxFee }" /> <input type="hidden"
		id="shippingMethodInput" name="shippingMethod" value=""></input>
	<%-- <c:forEach items="${addressList }" var="addtress" varStatus="status">
			</c:forEach> --%>
</form>