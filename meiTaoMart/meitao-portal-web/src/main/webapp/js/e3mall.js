var E3MALL = {
	checkLogin : function(){
		var _ticket = $.cookie("token");
		console.log(_ticket);
		if(!_ticket){
			return ;
		}
		$.ajax({
			url : "http://192.168.1.100:8088/user/token/" + _ticket,
			dataType : "jsonp",
			type : "GET",
			success : function(data){
				if(data.status == 200){
					var username = data.data.username;
					var html = username + "，欢迎来到美桃商城！<a href=\"http://192.168.1.100:8082/user/logout.html\" class=\"link-logout\">[退出登录]</a>";
					$("#loginbar").html(html);
				}
			}
		});
	}
}

$(function(){
	// 查看是否已经登录，如果已经登录查询登录信息
	E3MALL.checkLogin();
});