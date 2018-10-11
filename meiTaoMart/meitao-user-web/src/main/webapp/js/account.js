function updateUserInfo() {
	console.log($("#account_form").serialize());
	$.post(
		"/user/profile/account",
		$("#account_form").serialize(),
		function(response) {
			if (response.status == 200) {
				alert("修改信息成功！");
			} else {
				alert(response.msg);
			}
		}
	);
}

