<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="addressLength" value="${fn:length(addressList)}"></c:set>
<input id="addressLength" type="hidden" value="${addressLength}" />
<div class="ship-bar-plus">
	<div class="keep-parallel">
		<div class="ship-info">
			<div class="info-display" id="addr-drop">
				<!-- primary address -->
				<div id="showPrimaryAddress" class="show_primary_address">
					<c:set var="primaryAddress" value="${primaryAddress}"></c:set>
					<div id="primaryAddress" class="wrapper">
						<form id="checkoutAddress" class="checkoutAddress"
							onsubmit="return false">
							<div id="primaryName" class="info-name">
								${primaryAddress.firstName}&nbsp;${primaryAddress.lastName}
								<input type="hidden" name="firstName"
									value="${primaryAddress.firstName}" />
								<input type="hidden" name="lastName"
									value="${primaryAddress.lastName}" />
							</div>

							<div class="info-phone-loc">
								<div class="info-phone keep-parallel">
									<img class="phone-img" src="/images_meitao/phone.svg" alt="">
									<div id="primaryShippingPhone" class="phone-num">
										<div>(${fn:substring(primaryAddress.shippingPhone, 0, 3) })&nbsp;${fn:substring(primaryAddress.shippingPhone, 3, 6)}-${fn:substring(primaryAddress.shippingPhone, 6, 10) }</div>
										<input type="hidden" name="shippingPhone"
											value="${primaryAddress.shippingPhone}" />

									</div>
								</div>
								<br>
								<div class="info-loc keep-parallel">
									<img class="loc-img" src="/images_meitao/location.svg" alt="">
									<div id="primaryShippingAddress" class="loc-name">
										<div id="primaryShippingAddressStreet">${primaryAddress.street},&nbsp;</div>
										<input type="hidden" name="street"
											value="${primaryAddress.street}" />
										<div id="primaryShippingAddressCity">${primaryAddress.city},
											&nbsp;</div>
										<input type="hidden" name="city"
											value="${primaryAddress.city}" />
										<div id="primaryShippingAddressState">${primaryAddress.state}</div>
										<input type="hidden" name="state"
											value="${primaryAddress.state}" />
									</div>
								</div>
								<div class="zipc">
									<!-- <span><a href=""><img class="zipc-img" src="/images_meitao/location.svg" alt=""></a></span> -->
									<div id="primaryZipcode" class="zipc-num">
										<div>${primaryAddress.zipcode}</div>
										<input type="hidden" name="zipcode"
											value="${primaryAddress.zipcode}" />
									</div>
								</div>
							</div>
						</form>

						<!-- <div class="info-default">默认地址</div> -->

						<!-- dropdown address -->
						<div class="dropdown-arrow">
							<img id="dropdown-img1" class="dropdown-img"
								onclick="openShippingAddressList()"
								src="/images_meitao/chevron-down.svg" alt="">
						</div>
					</div>
				</div>
				<div id="noAddressList" class="no-address">
					<h2>暂无收货地址</h2>
				</div>
			</div>
		</div>

		<!-- <div class="add-address">
			<img class="plus-img" src="/images_meitao/plus-symbol.svg" alt=""
				onclick="openNewAddressWindow()">
		</div> -->
	</div>
</div>

<div class="ship-option">
	<div class="address-drop">
		<!-- <img class="dropdown-img" onclick="selectShippingAddress()" src="/images_meitao/chevron-up.svg" alt=""> -->
		<!-- <h3 class="customfont">收货地址</h3> -->

		<div class="info-display2">
			<c:forEach items="${addressList}" var="address"
				varStatus="addressUtils">

				<div class="addr1"
					onclick="selectShippingAddress(this, ${address.id}, ${address.zipcode  })">
					<%-- <form index="${addressUtils.index }" onsubmit="return false"> --%>
					<div class="info-name">
						${address.firstName}&nbsp;${address.lastName} <input type="hidden"
							name="firstName" value="${address.firstName}" /> <input
							type="hidden" name="lastName" value="${address.lastName}" />
					</div>
					<div class="info-phone-loc">
						<div class="info-phone">
							<span><img class="phone-img"
								src="/images_meitao/phone.svg" alt=""></span> <span
								class="phone-num">(${fn:substring(address.shippingPhone, 0, 3) })&nbsp;${fn:substring(address.shippingPhone, 3, 6)}-${fn:substring(address.shippingPhone, 6, 10) }</span>
							<input type="hidden" name="shippingPhone"
								value="${address.shippingPhone}" />
						</div>
						<br>
						<div class="info-loc">
							<span><img class="loc-img"
								src="/images_meitao/location.svg" alt=""></span> <span
								class="loc-name"> ${address.street},
								&nbsp;${address.city},&nbsp; ${address.state} </span> <input
								type="hidden" name="street" value="${address.street}" /> <input
								type="hidden" name="city" value="${address.city}" /> <input
								type="hidden" name="state" value="${address.state}" />
						</div>
						<div class="zipc">
							<span class="zipc-num">${address.zipcode}</span> <input
								type="hidden" name="zipcode" value="${address.zipcode}" />
						</div>
					</div>
					<!-- <div class="edit-pen">
							<img class="pen-img" src="/images_meitao/edit.svg" alt="">
						</div> -->
					<c:if test="${address.isMain eq true }">
						<div class="info-default" style="float: right;">默认地址</div>
					</c:if>
					<!-- </form> -->
				</div>



				<br>
			</c:forEach>
		</div>
	</div>
</div>