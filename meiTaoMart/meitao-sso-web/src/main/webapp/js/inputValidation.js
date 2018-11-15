//检查密码的格式
function checkPW(input) {
    return input.checkValidity();
}

function validateUsername(username) {
	var re = /^[a-zA-Z0-9@._]*$/;
	return re.test(username);
}

function validateEmail(email) {
	var re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
	return re.test(email);
}

function validateAmex(cardno) {
	var re = /^(?:3[47][0-9]{13})$/;
	return re.test(cardno);
}

function validateVisa(cardno) {
	var re = /^(?:4[0-9]{12}(?:[0-9]{3})?)$/;
	return re.test(cardno);
}

function validateMastercard(cardno) {
	var re = /^(?:5[1-5][0-9]{14})$/;
	return re.test(cardno);
}

function validateDiscovercard(cardno) {
	var re = /^(?:6(?:011|5[0-9][0-9])[0-9]{12})$/;
	return re.test(cardno);
}

function validateZipCode(zipcode) {
    var re = /(^\d{5}$)|(^\d{5}-\d{4}$)/;
    return re.test(zipcode);
}