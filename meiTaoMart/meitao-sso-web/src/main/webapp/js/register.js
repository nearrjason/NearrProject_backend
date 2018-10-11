$(function() {
	$("#signup_submit").click(function() {
		REGISTER.reg();
	});
});

var REGISTER = {
	param : {
		// 单点登录系统的url
		surl : ""
	},
	closeAllErrorMessage : function() {
		closeError("register_error_message_email");
		closeError("register_error_message_username");
		closeError("register_error_message_password");
		closeError("register_error_message_password_check");
	},
	registerInputcheck : function() {
		if ($("#register_email").val() == "") {
			showError("register_error_message_email", "邮箱不能为空");
			return false;
		}
		
		if (!eval(validateEmail($("#register_email").val()))) {
			showError("register_error_message_email", "邮箱格式不正确");
			return false;
		}
		
		if ($("#register_username").val() == "") {
			showError("register_error_message_username", "用户名不能为空");
			return false;
		}

		if ($("#register_password").val() == "") {
			showError("register_error_message_password", "密码不能为空");
			return false;
		}
		
		if (!checkPW(document.getElementById("register_password"))) {
			showError("register_error_message_password", "密码须包含至少八位字符，并同时包含小写字母及数字");
			return false;
		}
		
		if ($("#register_password_check").val() == "") {
			showError("register_error_message_password_check", "确认密码不能为空");
			return false;
		}
		if ($("#register_password").val() != $("#register_password_check")
				.val()) {
			showError("register_error_message_password_check", "两次密码不一致");
			return false;
		}

		return true;
	},
	beforeSubmit : function() {
		console.log("before");
		// 检查用户是否已经被占用
		var username = $("#register_username").val();
		var email = $("#register_email").val();

		$.ajax({
			url : REGISTER.param.surl + "/user/check/" + email + "/3?r="
					+ Math.random(),
			// 加随机数是防止浏览器走缓存
			success : function(data) {
				
				if (!data.data) {
					// 检查邮箱是否存在, 如果是false说明不存在
					$.ajax({
								url : REGISTER.param.surl + "/user/check/"
										+ escape(username) + "/1?r="
										+ Math.random(),
								success : function(data) {
									if (!data.data) {
										// 如果是false说明不存在
										REGISTER.doSubmit();
									} else {
										// showError("email","emailErr",defaultArr[9]);
										showError("register_error_message_username",
												"此用户名已经被注册");
									}
								}
							});
				} else {
					// showError("regName","userMamErr",defaultArr[10]);
					showError("register_error_message_email", "此邮箱已经被注册");
				}
			}
		});

	},
	doSubmit : function() {
		$.post("/user/register", $("#signup_form").serialize(), function(data) {

			if (data.status == 200) {
				REGISTER.login($("#register_email").val(), $("#register_password").val());
			} else {
				showError("register_error_message", "注册失败");
			}
		});
	},
	login : function(email, password) {
		$.post("/user/login", {
			usernameOrEmail : email,
			password : password
		}, function(data) {
			if (data.status == 200) {
				location.href = "http://192.168.1.100:8082";
			} else {
				showError("login_error_message", data.msg);
			}
		});
	},
	reg : function() {
		this.closeAllErrorMessage();
		if (this.registerInputcheck()) {
			this.beforeSubmit();
		}
	}
};