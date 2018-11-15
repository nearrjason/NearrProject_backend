$(function() {
	$('#addrLink').on('click', goToAddr);
	$('#creditLink').on('click', goToPayment);
	$('#accLink').on('click', goToAcc);
	$('#orderLink').on('click', goToOrders);
	
	var showType = $("#showType").val();
	console.log(showType)
	switch (showType) {
	case "order":
		showOrders();
		break;
	case "account":
		showAccount();
		break;
	case "address":
		showAddress();
		break;
	case "card":
		showCards();
	}

	/*
	 * $(".address tr:odd").addClass("address-detail"); $(".address
	 * tr:even").addClass("address-detail even");
	 */
	
	document.addEventListener('invalid', (function(){
	      return function(e){
	          //prevent the browser from showing default error bubble/ hint
	          e.preventDefault();
	          // optionally fire off some custom validation handler
	          // myvalidationfunction();
	      };
	  })(), true);
});

function showOrders() {
	if (!$('#orderLink').hasClass('selected')) {
		$('.links li').removeClass('selected');
		$('#orderLink').addClass('selected');
		$('.right').fadeOut(1);
		$('#orderDetailBox').fadeOut(1);
		$('#ordersBox').fadeIn(300);
		$('#orderLink').off('click');
		$('#addrLink').off('click').on('click', goToAddr);
		$('#creditLink').off('click').on('click', goToPayment);
		$('#accLink').off('click').on('click', goToAcc);
	}
}

function goToOrders() {
	location.href = "/user/profile/orders";
	//showOrders();
}

function showAddress() {
	if (!$('#addrLink').hasClass('selected')) {
		$('.links li').removeClass('selected');
		$('#addrLink').addClass('selected');
		$('.right').fadeOut(1);
		$('#orderDetailBox').fadeOut(1);
		
		
		$('#addressBox').fadeIn(300);
		$('#addrLink').off('click');
		$('#orderLink').off('click').on('click', goToOrders);
		$('#creditLink').off('click').on('click', goToPayment);
		$('#accLink').off('click').on('click', goToAcc);
	}
}

function goToAddr() {
	location.href = "/user/profile/addresses";
	//showAddress();
}

function showCards() {
	if (!$('#creditLink').hasClass('selected')) {
		$('.links li').removeClass('selected');
		$('#creditLink').addClass('selected');
		$('.right').fadeOut(1);
		$('#orderDetailBox').fadeOut(1);
		$('#paymentBox').fadeIn(300);
		$('#creditLink').off('click');
		$('#addrLink').off('click').on('click', goToAddr);
		$('#orderLink').off('click').on('click', goToOrders);
		$('#accLink').off('click').on('click', goToAcc);
	}
}

function goToPayment() {
	location.href = "/user/profile/cards";
	//showCards();
}

function showAccount() {
	if (!$('#accLink').hasClass('selected')) {
		$('.links li').removeClass('selected');
		$('#accLink').addClass('selected');
		$('.right').fadeOut(1);
		$('#orderDetailBox').fadeOut(1);
		$('#accountBox').fadeIn(300);
		$('#accLink').off('click');
		$('#addrLink').off('click').on('click', goToAddr);
		$('#creditLink').off('click').on('click', goToPayment);
		$('#orderLink').off('click').on('click', goToOrders);
	}
}

function goToAcc() {
	location.href = "/user/profile/account";
	//showAccount();
}

function setAsDefault(id, type) {
	console.log(id);
	console.log(type);
	$.post("/user/profile/set_as_default", {
		"id" : id,
		"type" : type
	}, function(result) {

	})
}

function deleteOne(id, type) {
	location.href = "/user/profile/delete?id=" + id + "&type=" + type;
}

/*
 * $('.order-detail').on('click', function() { $('.right').fadeOut(1);
 * $('#orderDetailBox').fadeIn(300); console.log($(this).attr("index")); });
 */

function checkOrderDetail(e) {
	var index = $(e).attr("index");
	$('.right').fadeOut(1);
	$("#orderDetailBox" + index).fadeIn(300);
}

function submitChangePassword() {
	closeError("reset_password_error_message");
	
	var changePasswordFormArray = $("#changePasswordForm").serializeArray();
	if (!eval(passwordInvalidation(changePasswordFormArray))) {
		return;
	}
	
	$.post("/user/profile/change_password", $("#changePasswordForm")
			.serialize(), function(response) {
		if (response.status == 200) {
			$("#changePasswordForm").trigger("reset");
			$('#changeForm').fadeOut(100);
			$('#overlay').fadeOut(100);
			alert("密码修改成功！");
		} else {
			showError("reset_password_error_message", response.msg);
		}
	})
}

function passwordInvalidation(changePasswordFormArray) {
	var newPassword = changePasswordFormArray[1].value;
	var newPasswordRepeat = changePasswordFormArray[2].value;
	
	if ($("#oldPassword").val() == "") {
		showError("reset_password_error_message", "请输入旧密码");
		return false;
	}
	
	if (newPassword == null || newPassword == "") {
		showError("reset_password_error_message", "新密码不能为空!");
		return false;
	}
	
	if (newPassword != newPasswordRepeat) {
		showError("reset_password_error_message", "两次密码不一致");
		return false;
	}
	
	if (!checkPW(document.getElementById("newPassword"))) {
		showError("reset_password_error_message", "密码须包含至少八位字符，并同时包含小写字母及数字");
		return false;
	}
	
	return true;
}

$('.back-btn').on('click', function() {
    $('.orderDetailBox').slideUp(1);
    $('#ordersBox').slideDown(300);
});

$('#allOrders').on('click', function() {
	$('.orderDetailBox').fadeOut(1);
	$('#ordersBox').fadeIn(300);
});