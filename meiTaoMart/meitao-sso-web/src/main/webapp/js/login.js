$(function() {
	$("#login_submit").click(function() {
		LOGIN.login();
	});
	redirectUrl = $("#redirectURL").val();
});

var LOGIN = {
	loginInputcheck : function() {
		if ($("#usernameOrEmail").val() == "") {
			showError("login_error_message", "请输入邮箱或用户名");
			return false;
		}
		if ($("#login_password").val() == "") {
			showError("login_error_message", "密码不能为空");
			return false;
		}
		
		return true;
	},
	doLogin : function() {
		// console.log($("#login_form").serialize());

		$.post(
			"/user/login", 
			$("#login_form").serialize(),
		    function(data) {
				if (data.status == 200) {
					if (redirectUrl == "") {
						location.href = "http://192.168.1.100";
					} else {
						location.href = redirectUrl;
					}
	
				} else {
					showError("login_error_message", data.msg);
				}
			}
		);

	},
	login : function() {
		closeError("login_error_message");
		/*$("#loadingImage").fadeIn(100);
		$("#overlay").show();*/
		if (this.loginInputcheck()) {
			this.doLogin();
		}
		//$("#loadingImage").fadeOut(100);
	}
};
