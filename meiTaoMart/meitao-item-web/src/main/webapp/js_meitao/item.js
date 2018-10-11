var maxAmount;

$(function(){
	maxAmount = $("#itemStockNumber").val();
});

function checkPurchaseQuantity(e, stockNumber) {
	var inputQuantity = Math.max(1, Math.min($(e).val(), stockNumber));
	if (isNaN(inputQuantity)) {
		inputQuantity = stockNumber;
	}
	$(e).val(parseInt(inputQuantity));
}

function descClick() {
    $('.description').addClass('selected');
    $('.description').off("click");
    $('.content-info').removeClass('selected');
    $('.content-info').on('click',contentClick);
    $('.i-d').fadeIn(1);
    $('.i-c').fadeOut(1);
}

function contentClick() {
    $('.content-info').addClass('selected');
    $('.content-info').off("click");
    $('.description').removeClass('selected');
    $('.description').on('click',descClick);
    $('.i-c').fadeIn(1);
    $('.i-d').fadeOut(1);
}

function minusItem(itemId) {
	var inputId = "#purchaseQuantity" + itemId;
    var c = parseInt($(inputId).val());
    if (c > 1) {
        $(inputId).val(c-1);
    }
}

function addItem(itemId) {
	var inputId = "#purchaseQuantity" + itemId;
    var c = parseInt($(inputId).val());
    if (c < maxAmount) {
        $(inputId).val(c+1);
    }
}

function addToCart(product_id) {
	var purchaseQuantity = $("#purchaseQuantity" + product_id).val();
	$.ajax({
		url : "http://192.168.1.100:8090/cart/add/"+product_id+".action?purchaseQuantity=" + purchaseQuantity,
		dataType : "jsonp",
		jsonp: "callback",
		type : "GET",
		success : function(response){
			if (response.status == 200) {
				alert("添加购物车成功！");
			}
		}
	});
}

$('.small-pic a').on('click',function() {
    var image = $(this).html();
    $('.focus-pic').html(image);
})