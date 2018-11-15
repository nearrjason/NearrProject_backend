function showError(inputId, error_message) {
	if (inputId) {
		var errorElement = $("#" + inputId);
		errorElement.fadeIn(1);
		errorElement.html(error_message);
	}

}

function closeError(inputId) {
	var errorElement = $("#" + inputId);
	errorElement.fadeOut(1);
}

function selectGroudShipping() {
	var primaryAddressId = $("#primaryAddressId").val();
	$("#shippingMethodInput").val(1);
	$('.normalship').addClass('selected');
	$('.normals hip').off("click");
	$('.fastship').removeClass('selected');
	if (primaryAddressId) {
		updateFinalOrderInfo(primaryAddressId);
	}

	// $('.fastship').on('click', selectFastShip);
}

function selectExpressShipping() {
	var primaryAddressId = $("#primaryAddressId").val();
	$("#shippingMethodInput").val(2);
	$('.fastship').addClass('selected');
	$('.fastship').off("click");
	$('.normalship').removeClass('selected');
	updateFinalOrderInfo(primaryAddressId);
	// $('.normalship').on('click', selectGroudShipping);
}

function openShippingAddressList() {
	// $('.ship-bar-plus').hide();
	/* $('.ship-option').slideDown(); */
	if ($('.ship-option:visible').length == 0) {
		$('.ship-option').slideDown(120);
		$('#dropdown-img1').animate({
			deg : 180
		}, {
			step : function(now, fx) {
				$('#dropdown-img1').css({
					transform : "rotate(" + now + "deg)"
				});
			}
		});
	} else {
		$('.ship-option').slideUp(120);
		$('#dropdown-img1').animate({
			deg : 0
		}, {
			step : function(now, fx) {
				$('#dropdown-img1').css({
					transform : "rotate(" + now + "deg)"
				});
			}
		});
	}
}

function selectShippingAddress(element, addressId) {
	$.get("/order/checkout_page/select_address.action", {
		addressId : addressId
	}, function(page) {
		if (page) {
			$("#address_drop_list").html(page);
			console.log("select: " + $("#shippingMethodInput").val());
			$("#address_drop_list").show();
			$("#showPrimaryAddress").show();
			$('.address-list').hide();

			updateFinalOrderInfo(addressId);
			// $("#finalZipCode").html(finalZipCode);
		}
	})
}

function updateFinalOrderInfo(primaryAddressId) {
	console.log("update: " + $("#shippingMethodInput").val());
	var isExpressShipping = $("#shippingMethodInput").val() == 2;
	$.get("/order/checkout_page/update_order_info.action", {
		primaryAddressId : primaryAddressId,
		isExpressShipping : isExpressShipping
	}, function(page) {
		$("#final_prices_block").html(page);

	})
}

function selectShippingCard(element, cardId) {
	$.get("/order/checkout_page/select_card.action", {
		cardId : cardId
	}, function(page) {
		if (page) {
			$("#card_drop_list").html(page);

			$("#card_drop_list").show();
			$("#showPrimaryCard").show();
			$('.add-card-info').hide();
		}
	})
}

// function chooseCreditPay() {
// $('.creditcard-list').show();
// $('.zhifubao-list').hide();
// $('.wechat-list').hide();
// }

// function chooseZhifubaoPay() {
// $('.zhifubao-list').show();
// $('.creditcard-list').hide();
// $('.wechat-list').hide();
// }

// function chooseWechatPay() {
// $('.wechat-list').show();
// $('.zhifubao-list').hide();
// $('.creditcard-list').hide();
// }

function backToPay2() {
	$('.show-card-list').hide();
	$('.cardinfo-show').show();
	$('.add-card').show();
}

function selectCard() {
	/*
	 * $('.cardinfo-show').hide(); $('.add-card').hide();
	 */
	/* $('.show-card-list').slideDown(); */
	if ($('.show-card-list:visible').length == 0) {
		$('.show-card-list').slideDown(120);
		$('#dropdown-img2').animate({
			deg : 180
		}, {
			step : function(now, fx) {
				$('#dropdown-img2').css({
					transform : "rotate(" + now + "deg)"
				});
			}
		});
	} else {
		$('.show-card-list').slideUp(120);
		$('#dropdown-img2').animate({
			deg : 0
		}, {
			step : function(now, fx) {
				$('#dropdown-img2').css({
					transform : "rotate(" + now + "deg)"
				});
			}
		});
	}

}

function openNewCardWindow() {
	/*
	 * $('.cardinfo-show').hide(); $('.add-card').hide();
	 */
	$("#card_drop_list").slideUp(120);
	$('.add-card-info').slideDown(120);
}
// 显示primary address 和 address list
function openNewAddressWindow() {
	/*
	 * $('.ship-info').hide(); $('.add-address').hide();
	 */
	$("#address_drop_list").slideUp(120);
	$('.address-list').slideDown(120); // 打开添加地址窗口(这个class名字起的真** **)
}

function submitNewCard() {
	closeError("order_payment_method");
	var submitNewCardFormArray = $("#submitNewCardForm").serializeArray();
	if (!cardInputValidation(submitNewCardFormArray, "order_payment_method")) {
		return;
	}

	var isUsingShippingAddressAsBillingAddress = $(
			"#isUsingShippingAddressAsBillingAddress").val();
	if (!eval(isUsingShippingAddressAsBillingAddress)) {
		$("#addBillingShippingPhone").val(
				"" + $("#addBillingShippingPhone1").val()
						+ $("#addBillingShippingPhone2").val()
						+ $("#addBillingShippingPhone3").val());
		if (!addressInputValidation($("#submitBillingAddressForm")
				.serializeArray(), "order_billing_address")) {
			return;
		}
	}

	var cardNumberValue = submitNewCardFormArray[2].value;
	var params = $("#submitNewCardForm").serialize();

	if (validateAmex(cardNumberValue)) {
		params += "&cardType=3";
	} else if (validateVisa(cardNumberValue)) {
		params += "&cardType=4";
	} else if (validateMastercard(cardNumberValue)) {
		params += "&cardType=5";
	} else if (validateDiscovercard(cardNumberValue)) {
		params += "&cardType=6";
	}

	$.post("/order/checkout_page/save_card.action", params, function(result) {
		if (result) {
			console.log(result);
			if (result.status == 200) {
				$.get("/order/checkout_page/update_card_list.html", function(
						page) {
					if (page) {
						$("#card_drop_list").html(page);

						$("#card_drop_list").show();
						$("#showPrimaryCard").show();
						$('.add-card-info').hide();
					}
				})
			} else {
				showError("order_payment_method", result.msg);
			}
		}
	})

}

function cardInputValidation(submitFormArray, cardId) {
	closeError(cardId);
	console.log(submitFormArray);
	var firstNameValue = submitFormArray[0].value;
	var lastNameValue = submitFormArray[1].value;
	var cardNumberValue = submitFormArray[2].value;
	var monthValue = submitFormArray[3].value;
	var yearValue = submitFormArray[4].value;

	if (firstNameValue == "") {
		showError(cardId, "银行卡 First Name 不能为空");
		return false;
	}
	if (lastNameValue == "") {
		showError(cardId, "银行卡 Last Name 不能为空");
		return false;
	}

	if (!isAplhabet(firstNameValue)) {
		showError(cardId, "银行卡 First Name 必须为英文字符");
		return false;
	}

	if (!isAplhabet(lastNameValue)) {
		showError(cardId, "银行卡 Last Name 必须为英文字符");
		return false;
	}

	if (cardNumberValue == "") {
		showError(cardId, "银行卡信息缺失！");
		return false;
	}

	if (!validateVisa(cardNumberValue) && !validateMastercard(cardNumberValue)
			&& !validateDiscovercard(cardNumberValue)
			&& !validateAmex(cardNumberValue)) {
		showError(cardId, "请输入正确的卡号，银行卡类型限于: Visa Mastercard Discover Amex");
		return false;
	}

	return true;
}

function submitNewAddress() {
	closeError("order_shipping_address");
	$("#addAddressShippingPhone").val(
			"" + $("#addAddressShippingPhone1").val()
					+ $("#addAddressShippingPhone2").val()
					+ $("#addAddressShippingPhone3").val());

	if (!addressInputValidation($("#submitNewAddressForm").serializeArray(),
			"order_shipping_address")) {
		return;
	}
	$.post("/order/checkout_page/save_address.action", $(
			"#submitNewAddressForm").serialize(), function(result) {
		if (result) {
			console.log(result);
			if (result.status == 200) {
				$.get("/order/checkout_page/update_address_list.html",
						function(page) {
							if (page) {
								$("#address_drop_list").html(page);

								$("#address_drop_list").show();
								$("#showPrimaryAddress").show();
								$('.address-list').hide();

								updateFinalOrderInfo($("#primaryAddressId")
										.val());
							}
						})
			} else {
				showError("order_shipping_address", result.msg);
			}
		}

		/*
		 * if (page) { $("#address_drop_list").html(page);
		 * 
		 * $('.ship-info').show(); $('.add-address').show();
		 * 
		 * $("#address_drop_list").show(); $("#showPrimaryAddress").show();
		 * $('.address-list').hide(); }
		 */
	})
}

function addressInputValidation(submitFormArray, addressId) {
	closeError(addressId);
	console.log(submitFormArray);
	var firstNameValue = submitFormArray[0].value;
	var lastNameValue = submitFormArray[1].value;
	var shippingPhoneValue = submitFormArray[2].value;
	var streetValue = submitFormArray[3].value;
	var cityValue = submitFormArray[4].value;
	var stateValue = submitFormArray[5].value;
	var zipcodeValue = submitFormArray[6].value;

	console.log(shippingPhoneValue);
	if (firstNameValue == "") {
		showError(addressId, "地址 First Name 不能为空");
		return false;
	}
	if (lastNameValue == "") {
		showError(addressId, "地址 Last Name 不能为空");
		return false;
	}

	if (!isAplhabet(firstNameValue)) {
		showError(addressId, "地址 First Name 必须为英文字符");
		return false;
	}

	if (!isAplhabet(lastNameValue)) {
		showError(addressId, "地址 Last Name 必须为英文字符");
		return false;
	}

	if (shippingPhoneValue == "") {
		showError(addressId, "地址 Phone Number 不能为空");
		return false;
	}
	if (shippingPhoneValue.length != 10) {
		showError(addressId, "地址 Phone Number 格式不正确");
		return false;
	}

	if (streetValue == "" || cityValue == "" || stateValue == ""
			|| zipcodeValue == "") {
		showError(addressId, "地址信息缺失");
		return false;
	}

	if (!validateStreet(streetValue)) {
		showError(addressId, "Street 必须为英文字符或数字");
		return false;
	}

	if (!validateCity(cityValue)) {
		showError(addressId, "city 必须为英文字符");
		return false;
	}

	if (!validateZipCode(zipcodeValue)) {
		showError(addressId, "Zip Code 格式不正确");
		return false;
	}

	return true;
}

function showEnterCVV() {
	commonPopup("enter", "");
}

function finalCheckout() {
	var cvv = $("#cvvInput").val();
	if (!eval(validateCVV(cvv))) {
		/*
		 * showError("cvv_check", "cvv 格式不正确！"); alert("银行卡信息不完整，请检查！");
		 */
		commonPopup("fail", "cvv 格式不正确");
		return;
	}

	var shippingAddress = convertUrlToJson($("#checkoutAddress").serialize());
	var card = convertUrlToJson($("#checkoutCard").serialize());
	var orderInfo = convertUrlToJson($("#checkoutOrder").serialize());

	var orderItemsArray = $("#checkoutOrderItemList").serializeArray();
	var orderItems = createOrderItemListJsonArray(orderItemsArray.length,
			orderItemsArray);

	/*
	 * var billingAddress = eval(isUsingShippingAddressAsBillingAddress) ?
	 * shippingAddress :
	 * convertUrlToJson($("#submitBillingAddressForm").serialize());
	 */

	/*
	 * $("#loadingImage").fadeIn(100); $("#overlay").show();
	 */

	commonPopup("loading", "");

	$.post("/order/final_payment.action", {
		shippingAddress : JSON.stringify(shippingAddress),
		card : JSON.stringify(card),
		orderInfo : JSON.stringify(orderInfo),
		orderItems : JSON.stringify(orderItems),
		// billingAddress : JSON.stringify(billingAddress),
		cvv : cvv
	}, function(response) {
		if (response.status == 200) {
			commonPopup("success");
		} else {
			commonPopup("fail", response.msg);
			if (response.status == 202) {
				$("#failureButton").off("click").on("click", refreshOrderPage);
			}
		}
	})
}

function refreshOrderPage() {
	location.href = "/order/checkout_page.html";
}

function continueShopping() {
	location.href = "http://192.168.1.100";
}

function goToOrders() {
	location.href = "http://192.168.1.100:8094/user/profile/orders";
}

function createOrderItemListJsonArray(size, array) {
	var result = new Array();
	for (var i = 0; i < size; i += 2) {
		result.push({
			"itemId" : array[i].value,
			"itemNumber" : array[i + 1].value
		});
	}

	return result;
}

function convertUrlToJson(URL) {
	return JSON.parse('{"'
			+ decodeURI(URL.replace(/&/g, "\",\"").replace(/=/g, "\":\""))
			+ '"}')
}

$(function() {
	$('.useAddr').on('click', hideBillAddr);

	var addressLength = $("#addressLength").val();
	console.log(addressLength);
	if (addressLength > 0) {
		$("#showPrimaryAddress").show();
	} else {
		$("#noAddressList").show();
	}

	var cardLength = $("#cardLength").val();
	console.log(cardLength);
	if (cardLength > 0) {
		$("#showPrimaryCard").show();
	} else {
		console.log("no");
		$("#noCardList").show();
	}

	$('.phonenum-fill').keyup(function() {
		if ($(this).val().length == $(this).attr("maxlength")) {
			$(this).nextAll("input").first().focus();
		}
	});

	$('.phonenum-fill')
			.keydown(
					function(event) {
						var key = event.keyCode || event.which;
						if (65 <= key && key <= 90) {
							event.preventDefault();
						}
						if (key == 8) {
							if ($(this).val().length == 0) {
								$(this).prevAll("input").first().focus();
							}
						}
						if ($(this).val().length == $(this).attr("maxlength")
								&& ((48 <= key && key <= 57) || (65 <= key && key <= 90))) {
							event.preventDefault();
						}
					});

	selectGroudShipping();

});

// 使用当前收获地址作为账单地址
function hideBillAddr() {
	$("#isUsingShippingAddressAsBillingAddress").attr("value", true);
	$('.billingAddressBlock').slideUp();
	$('.useAddr').html('手动填写账单地址(已使用当前收货地址作为账单地址)');
	$('.useAddr').off('click').on('click', showBillAddr);
}

// 手动填写账单地址
function showBillAddr() {
	$('.billingAddressBlock').slideDown();
	$('.useAddr').html('使用当前收货地址作为账单地址');
	$('.useAddr').off('click').on('click', hideBillAddr);
	$("#isUsingShippingAddressAsBillingAddress").attr("value", false);
}

function openPopup() {
	$('#enterCVV').fadeIn(50);
	$('.enter').show();
}

function payNow() {
	$(".close-popup").hide();
	$('.enter').hide();
	$('.loading').show();
}

function successPopup() {
	$('.loading').hide();
	$('.success').show();
}

function failPopup(message) {
	$('#enterCVV').fadeIn(50);
	$('.loading').hide();
	$(".fail h1").html(message);
	$(".fail").show();
}

function closePopup() {
	$('#enterCVV').fadeOut(50);
	$('#overlay').fadeOut(50);
	resetPopup();
}

function resetPopup() {
	$('.enter').hide();
	$('.loading').hide();
	$('.success').hide();
	$('.fail').hide();
}

function commonPopup(type, message) {
	$('#overlay').fadeIn(50);
	resetPopup();
	switch (type) {
	case "enter":
		openPopup();
		break;
	case "loading":
		payNow()
		break;
	case "success":
		successPopup();
		break;
	case "fail":
		// $("#failureButton").on("click", closePopup);
		failPopup(message);
	}
}

$(document).on(
		"click",
		function(e) {
			var target = $(e.target);
			if ($('.ship-option:visible').length != 0
					&& target.closest("#addr-drop").length == 0) {
				if (target.closest(".ship-option").length == 0) {
					$('.ship-option').slideUp(120);
					$('#dropdown-img1').animate({
						deg : 0
					}, {
						step : function(now, fx) {
							$('#dropdown-img1').css({
								transform : "rotate(" + now + "deg)"
							});
						}
					});
				}
				;
				e.stopPropagation();
			} else if ($('.show-card-list:visible').length != 0
					&& target.closest("#card-drop").length == 0) {
				if (target.closest(".show-card-list").length == 0) {
					$('.show-card-list').slideUp(120);
					$('#dropdown-img2').animate({
						deg : 0
					}, {
						step : function(now, fx) {
							$('#dropdown-img2').css({
								transform : "rotate(" + now + "deg)"
							});
						}
					});
				}
				;
				e.stopPropagation();
			}
		})