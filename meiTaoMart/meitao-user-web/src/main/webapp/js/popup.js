$(function() {
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

	$('.card-num-logo input').on(
			'input',
			function() {
				var cardNumber = $('.card-num-logo input').val();
				if (eval(validateAmex(cardNumber))) {
					$('.card-num-logo img').attr('src',
							'/images/icons/amex.svg');
				} else if (validateVisa(cardNumber)) {
					$('.card-num-logo img').attr('src',
							'/images/icons/visa.svg');
				} else if (validateMastercard(cardNumber)) {
					$('.card-num-logo img').attr('src',
							'/images/icons/mastercard.svg');
				} else if (validateDiscovercard(cardNumber)) {
					$('.card-num-logo img').attr('src',
							'/images/icons/discover.svg');
				} else {
					$('.card-num-logo img').attr('src',
							'/images/icons/image-cvv.svg');
				}
			});
});

function confirmUpdate(formId, type, index) {
	if (type == "address") {
		$("#userUpdateAddressShippingPhone" + index).val(
				""
						+ $("#userUpdateAddressShippingPhoneOne" + index).val()
						+ $("#userUpdateAddressShippingPhoneTwo" + index).val()
						+ $("#userUpdateAddressShippingPhoneThree" + index)
								.val());
	}
	var submitFormArray = $(formId).serializeArray();
	if (type == "address") {
		closeError("user_update_address_method" + index);
		if (!addressInputValidation(submitFormArray,
				"user_update_address_method" + index)) {
			return;
		}
		$.post("/user/profile/update/address", $(formId).serialize(), function(
				response) {
			if (response.status == 200) {
				location.href = "/user/profile/addresses";
			} else {
				showError("user_update_address_method" + index, response.msg);
			}
		}

		)
	} else if (type == "card") {
		closeError("user_update_card_method" + index);
		if (!cardInputValidation(submitFormArray, "user_update_card_method"
				+ index)) {
			return;
		}
		var cardNumberValue = submitFormArray[2].value;
		var params = $(formId).serialize();
		if (validateAmex(cardNumberValue)) {
			params += "&cardType=3";
		} else if (validateVisa(cardNumberValue)) {
			params += "&cardType=4";
		} else if (validateMastercard(cardNumberValue)) {
			params += "&cardType=5";
		} else if (validateDiscovercard(cardNumberValue)) {
			params += "&cardType=6";
		}
		$.post("/user/profile/update/card", params, function(response) {
			if (response.status == 200) {
				location.href = "/user/profile/cards";
			} else {
				showError("user_update_card_method" + index, response.msg);
			}
		}

		)
	}
	console.log(type);
	console.log($(formId));

}

function confirmAdd(formId, type) {
	if (type == "address") {
		$("#userAddAddressShippingPhone").val(
				"" + $("#userAddAddressShippingPhone1").val()
						+ $("#userAddAddressShippingPhone2").val()
						+ $("#userAddAddressShippingPhone3").val());
	}

	var submitFormArray = $(formId).serializeArray();
	if (type == "address") {
		closeError("user_add_address");
		if (!addressInputValidation(submitFormArray, "user_add_address")) {
			return;
		}
		$.post("/user/profile/add/address", $(formId).serialize(), function(
				response) {
			if (response.status == 200) {
				location.href = "/user/profile/addresses";
			} else {
				showError("user_add_address", response.msg);
			}
		}

		)
	} else if (type == "card") {
		closeError("user_add_card");
		if (!cardInputValidation(submitFormArray, "user_add_card")) {
			return;
		}
		// 账单地址校验
		if (!addressInputValidation($("#addBillingAddressFormSubmit")
				.serializeArray(), "user_add_card")) {
			return;
		}
		var cardNumberValue = submitFormArray[2].value;
		var params = $(formId).serialize();
		if (validateAmex(cardNumberValue)) {
			params += "&cardType=3";
		} else if (validateVisa(cardNumberValue)) {
			params += "&cardType=4";
		} else if (validateMastercard(cardNumberValue)) {
			params += "&cardType=5";
		} else if (validateDiscovercard(cardNumberValue)) {
			params += "&cardType=6";
		}
		$.post("/user/profile/add/card", params, function(response) {
			if (response.status == 200) {
				location.href = "/user/profile/cards";
			} else {
				showError("user_add_card", response.msg);
			}
		}

		)
	}
}

function confirmDelete(e) {
	var id = $(e + " input").attr("id");
	var type = $(e + " input").attr("deleteType");
	console.log(id);
	console.log(type);
	deleteOne(id, type);
	// closepopup(e);
}

function openForm(e, index) {
	$(e + "" + index).fadeIn(100);
	$('#overlay').fadeIn(100);
}

function closeForm(e) {
	$(e).fadeOut(100);
	$('#overlay').fadeOut(100);
}

function popup(e, id) {
	$(e + " input").attr("id", id);
	$(e).fadeIn(100);
	$('#overlay').fadeIn(100);
}

function closepopup(e) {
	$(e).fadeOut(100);
	$('#overlay').fadeOut(100);
}

function openAddForm(e) {
	$(e).fadeIn(100);
	$('#overlay').fadeIn(100);
}

function closeAddForm(e) {
	$(e).fadeOut(100);
	$('#overlay').fadeOut(100);
}

function openChangeForm() {
	$('#changeForm').fadeIn(100);
	$('#overlay').fadeIn(100);
}

function closeChangeForm() {
	$('#changeForm').fadeOut(100);
	$('#overlay').fadeOut(100);
}

function showCart() {
	$('.cart-popup').fadeIn(50);
}

function unshowCart() {
	$('.cart-popup').fadeOut(50);
}

var cart = document.getElementById("cart");
cart.onmouseenter = function() {

	showCart();
};

cart.onmouseleave = function() {

	unshowCart();
}