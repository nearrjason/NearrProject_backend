function check(input) {
    /*if (input.value != document.getElementById('reset_password').value) {
        $('#warning').fadeIn(1);
        input.setCustomValidity('Password Must be Matching.');
    } else {
        // input is valid -- reset the error message
        $('#warning').fadeOut(1);
        input.setCustomValidity('');
    }*/
}


/*function checkPW(input) {
    if (!input.checkValidity()) {
        $('#error').fadeIn(1);
    } else {
        $('#error').fadeOut(1);
    }
}*/

function sendEmail() {
	var emailAddress = $("#emailAddress").val();
	console.log(emailAddress);
	if (!emailAddress) {
		return;
	}
	$.ajax({
		url : "/user/check/"
				+ emailAddress + "/3?r=" + Math.random(),
		success : function(response) {
			if (response.data) {
				// 如果是true说明存在
				$.post(
			    	"/page/forget_psw",
			    	{
			    		emailAddress:emailAddress
			    	},
			    	function(response1) {
			    		if (response1.status == 200) {
			    			$("#emailAddressForResetPassword").html(emailAddress);
			    			toVeri();
			    		}
			    	}
			    )
				
			} else {
				// showError("email","emailErr",defaultArr[9]);
				showError("reset_password_email_error_message", "此邮箱不存在！请检查邮箱");
			}
		}
	});
}

function resendEmail() {
	var emailAddress = $("#emailAddress").val();
	$.post(
    	"/page/forget_psw",
    	{
    		emailAddress:emailAddress
    	},
    	function(response) {
    		
    	}
    )
}

function toVeri() {
	$('.input-email').fadeOut(1);
    $('.veri-code').fadeIn(1);
    $('.step2').addClass('selected');
    $('.step1').removeClass('selected');
}

function backEmail() {
    $('.veri-code').fadeOut(1);
    $('.input-email').fadeIn(1);
    $('.step1').addClass('selected');
    $('.step2').removeClass('selected');
}

function toPW() {
    $('.veri-code').fadeOut(1);
    $('.new-pw').fadeIn(1);
    $('.step3').addClass('selected');
    $('.step2').removeClass('selected');
    $('.input-email').fadeOut(1);
    $('.step1').removeClass('selected');
}

function resetPassword() {
	closeError("reset_password_error_message");
	var emailAddress = $("#emailAddressFromPasswordCode").val();
	var newPassword = $("#new_password").val();
	var newPasswordCheck = $("#new_password_check").val();
	
	if (newPassword != newPasswordCheck) {
		showError("reset_password_error_message", "两次密码不一致");
		return false;
	}
	
	if (!checkPW(document.getElementById("new_password"))) {
		showError("reset_password_error_message", "密码须包含至少八位字符，并同时包含小写字母及数字");
		return false;
	}
	
	$.post(
		"/page/forget_psw/change_password",
		{
			emailAddress:emailAddress,
			password:newPassword
		},
		function(response) {
			if (response.status == 200) {
				login(emailAddress, newPassword);
			} else {
				alert(response.msg);
			}
		}
	)
}

function login(email, password) {
	$.post("/user/login", {
		usernameOrEmail : email,
		password : password
	}, function(data) {
		if (data.status == 200) {
			location.href = "http://192.168.1.100";
		} else {
			showError("login_error_message", data.msg);
		}
	});
}

$(function() {
	var init = $("#initTab").val();
	if ($("#initTab").val()) {
		toPW();
	}
	var codeExpire = $("#isPasswordCodeExpire").val();
	if (codeExpire) {
		alert("此链接已过期！请重新发送验证邮件");
	}
});