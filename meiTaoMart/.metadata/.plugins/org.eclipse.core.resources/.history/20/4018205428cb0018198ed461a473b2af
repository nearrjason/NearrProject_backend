$(function() {
    $("#initType").val() == "register" ? goSignUp() : goLogIn();
    $("#pwToggle").click(function() {
    	pwToggle();
	});
    
    document.addEventListener('invalid', (function(){
        return function(e){
            //prevent the browser from showing default error bubble/ hint
            e.preventDefault();
            // optionally fire off some custom validation handler
            // myvalidationfunction();
        };
    })(), true);
});

function goSignUp() {
    $('.signup').addClass('selected');
    $('.signup').off("click");
    $('.login').removeClass('selected');
    $('.login').on('click',goLogIn);
    $('.signup-window').fadeIn(300);
    $('.login-window').fadeOut(1);
    /*
	$("#register-interface").css("display", "block");
	$("#login-interface").css("display", "none");
	*/
}

function goLogIn() {
    $('.login').addClass('selected');
    $('.login').off("click");
    $('.signup').removeClass('selected');
    $('.signup').on('click',goLogIn);
    $('.login-window').fadeIn(300);
    $('.signup-window').fadeOut(1);
    /*
	$("#register-interface").css("display", "none");
	$("#login-interface").css("display", "block");
	*/
}

function pwToggle() {
    var x = $('#login_password');
    var h = $('.v-btn');
    if (x.attr('type') === "password") {
        h.attr('src', '/images/icons/hide.svg');
        x.attr('type', "text");
    } else {
        h.attr('src', '/images/icons/view.svg');
        x.attr('type',"password");
    }
}

/*function check(input) {
    if (input.value != $("#register_password").val()) {
        $('#warning').fadeIn(1);
        input.setCustomValidity('Password Must be Matching.');
    } else {
        // input is valid -- reset the error message
        $('#warning').fadeOut(1);
        input.setCustomValidity('');
    }
}*/

/*function checkPW(input) {
    if (!input.checkValidity()) {
        $('#error').fadeIn(1);
    } else {
        $('#error').fadeOut(1);
    }
}*/