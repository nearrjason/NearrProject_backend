function searchKeyAndPage(key, page) {
	if ($("#searchType").val() == "keyword") {
		location.href="/search.html?keyword=" + key + "&page=" + page;
	} else if ($("#searchType").val() == "categoryName") {
		location.href="/category.html?cn=" + key + "&page=" + page;
	}
	
};

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

function refreshPage() {
	location.reload();
}

function addToCart(product_id) {
	$.ajax({
		url : "http://192.168.1.100:8090/cart/add/"+product_id+".action",
		dataType : "jsonp",
		jsonp: "callback",
		type : "GET",
		success : function(response){
			if (response.status == 200) {
				toast("", "snackbar");
				$.get(
					"/refresh/cart.html",
					function(page) {
						$("#headerPage").html(page);
					}
				)
			} else {
				toast(response.msg, "snackbar-fail");
			}
		}
	});
}