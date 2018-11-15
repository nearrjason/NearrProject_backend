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

$(function () {
    $("#phoneNumberInput").keydown(function (event) {
        var key = event.keyCode || event.which;
        if (65 <= key && key <= 90) {
            event.preventDefault();
        }
        if ($(this).val().length == $(this).attr("maxlength") && ((48 <= key && key <= 57) || (65 <= key && key <= 90))) {
            event.preventDefault();
        }
    });
});

