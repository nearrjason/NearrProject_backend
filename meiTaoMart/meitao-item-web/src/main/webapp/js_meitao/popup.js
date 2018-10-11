function popup() {
    $('#popup').fadeIn(100);
    $('#overlay').fadeIn(100);
}

function confirmZip() {
    $('#popup').fadeOut(100);
    $('#overlay').fadeOut(100);
    if ($('#zip').val()) {
        $('.area-selection').html($('#zip').val());
    } else {
        $('.area-selection').html('请输入配送地区');
    }
}



function showCart() {
    $('.cart-popup').fadeIn(50);
}

function unshowCart() {
    $('.cart-popup').fadeOut(50);
}

var cart = document.getElementById("cart");
cart.onmouseenter = function () {

    showCart();
};

cart.onmouseleave = function () {

    unshowCart();
}