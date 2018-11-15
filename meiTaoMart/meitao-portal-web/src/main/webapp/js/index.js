var i = $('.block-ads').children().length;
var width = 1200 / i - 2.5;
$('.block-ads a').css('width', width);
$('.block-ads a img').css('width', width);

$('.add-to-cart').mouseover(function() {
	$(this).animate({
		deg : 180
	}, {
		step : function(now, fx) {
			$(this).css({
				transform : "rotate(" + now + "deg)"
			});
		}
	});
});

$('.add-to-cart').mouseleave(function() {
	$(this).animate({
		deg : 0
	}, {
		step : function(now, fx) {
			$(this).css({
				transform : "rotate(" + now + "deg)"
			});
		}
	});
});


function toast(message, id) {
    // Get the snackbar DIV
    var x = document.getElementById(id);
    $("#snackbar-fail p").html(message);
    // Add the "show" class to DIV
    x.className = "show";
    console.log(id);
    if (id == "snackbar") {
    	setTimeout(function(){ x.className = x.className.replace("show", ""); }, 3000);
    }
}
/*
function close() {
    var x = document.getElementById("snackbar-fail");
    
    x.className = x.className.replace("show", "");
    // setTimeout(function(){ x.className = x.className.replace("show", ""); }, 1);
}*/

function refreshPage() {
	location.reload();
}

function addToCart(product_id) {
	$.ajax({
		url : "http://192.168.1.100:8090/cart/add/" + product_id + ".action",
		dataType : "jsonp",
		jsonp : "callback",
		type : "GET",
		success : function(response) {
			console.log(response);
			if (response.status == 200) {
				toast("", "snackbar");
				$.get("/refresh/cart.html", function(page) {
					$("#headerPage").html(page);
				})
			} else {
				toast(response.msg, "snackbar-fail");
			}
		}
	});
}

/*$(document).on("mouseover", "div.item",function(){
    $(this).addClass("hover");
});
$(document).on("mouseleave", "div.item",function(){
	$(this).removeClass("hover");
});*/