var errorMessageArray = [];
errorMessageArray[0]	='请输入您的邮箱';
errorMessageArray[1]	='请输入您的手机号码';
errorMessageArray[2]	='6-20位字符,可使用字母、数字、下划线。不建议使用纯数字或字母组合。';
errorMessageArray[3]	='请再次输入密码';
errorMessageArray[4]	='请输入短信验证码';
errorMessageArray[5]	='请输入验证码';
errorMessageArray[6]	='请输入您的优选单邀请码';
errorMessageArray[7]	='';
errorMessageArray[8]	='请输入您的用户名。可使用字母、数字、下划线。';
errorMessageArray[9]	='此邮箱已经被注册！';
errorMessageArray[10]	='此用户名已经被注册！请重新输入。';

function showError(inputId, error_message) {
	console.log(inputId);
	var errorElement = $("#" + inputId);
	errorElement.fadeIn(1);
	errorElement.html(error_message);
}

function closeError(inputId) {
	var errorElement = $("#" + inputId);
	errorElement.fadeOut(1);
}