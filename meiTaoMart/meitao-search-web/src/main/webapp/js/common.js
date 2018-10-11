function searchKeyAndPage(key, page) {
	location.href="/search.html?keyword=" + key + "&page=" + page;
};

function addToCart(product_id) {
	$.ajax({
		url : "http://192.168.1.100:8090/cart/add/"+product_id+".action",
		dataType : "jsonp",
		jsonp: "callback",
		type : "GET",
		success : function(response){
			if (response.status == 200) {
				/*alert("添加购物车成功！");*/
				$.get(
					"/refresh/cart.html",
					function(page) {
						$("#headerPage").html(page);
					}
				)
			}
		}
	});
}