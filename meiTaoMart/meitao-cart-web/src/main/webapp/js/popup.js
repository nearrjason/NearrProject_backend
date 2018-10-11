function openForm() {
    $('#myForm').fadeIn(100);
    $('#overlay').fadeIn(100);
}

function closeForm() {
    $('#myForm').fadeOut(100);
    $('#overlay').fadeOut(100);
}

function popup() {
    $('#popup').fadeIn(100);
    $('#overlay').fadeIn(100);
}

function closepopup() {
    $('#popup').fadeOut(100);
    $('#overlay').fadeOut(100);
}

function openAddForm() {
    $('#addForm').fadeIn(100);
    $('#overlay').fadeIn(100);
}

function closeAddForm() {
    $('#addForm').fadeOut(100);
    $('#overlay').fadeOut(100);
}

function confirmDelete() {
    closepopup();
    //还要删除信用卡信息
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