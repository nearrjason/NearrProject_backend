var MEITAOMART = {
	checkLogin : function(){
		var _ticket = $.cookie("token");
		console.log(_ticket);
		if(!_ticket){
			return ;
		}
		console.log("test");
		$.ajax({
			url : "http://192.168.1.100:8088/user/token/" + _ticket,
			dataType : "jsonp",
			type : "GET",
			success : function(data){
				if(data.status == 200){
					var username = data.data.username;
					var html = username + "，欢迎来到美桃商城！<a href=\"http://192.168.1.100/user/logout.html\" class=\"link-logout\">[退出登录]</a>";
					$("#loginbar").html(html);
				} else {
					var html = "<li><a href='http://192.168.1.100:8088/page/login'>登录</a></li> <li><a href='http://192.168.1.100:8088/page/register'>注册</a></li>";
				}
			}
		});
	}
}

$(function(){
	// 查看是否已经登录，如果已经登录查询登录信息
	MEITAOMART.checkLogin();
});