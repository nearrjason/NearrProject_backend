var errorMessageArray = [];
errorMessageArray[0] = '请输入您的邮箱';
errorMessageArray[1] = '请输入您的手机号码';
errorMessageArray[2] = '6-20位字符,可使用字母、数字、下划线。不建议使用纯数字或字母组合。';
errorMessageArray[3] = '请再次输入密码';
errorMessageArray[4] = '请输入短信验证码';
errorMessageArray[5] = '请输入验证码';
errorMessageArray[6] = '请输入您的优选单邀请码';
errorMessageArray[7] = '';
errorMessageArray[8] = '请输入您的用户名。可使用字母、数字、下划线。';
errorMessageArray[9] = '此邮箱已经被注册！';
errorMessageArray[10] = '此用户名已经被注册！请重新输入。';

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

function cardInputValidation(submitFormArray, cardId) {
	closeError(cardId);
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

	if (!(validateVisa(cardNumberValue) || validateMastercard(cardNumberValue)
			|| validateDiscovercard(cardNumberValue) || validateAmex(cardNumberValue))) {
		showError(cardId, "请输入正确的卡号，银行卡类型限于: Visa Mastercard Discover Amex");
		return false;
	}

	return true;
}

function addressInputValidation(submitFormArray, addressId) {
	closeError(addressId);
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

	/*if (!isAplhabet(firstNameValue)) {
		showError(addressId, "First Name 必须为英文字符！");
		return false;
	}

	if (!isAplhabet(lastNameValue)) {
		showError(addressId, "Last Name 必须为英文字符！");
		return false;
	}*/
	
	if (shippingPhoneValue == "") {
		showError(addressId, "地址电话号码不能为空");
		return false;
	}
	if (shippingPhoneValue.length != 10) {
		showError(addressId, "地址电话长度不正确");
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