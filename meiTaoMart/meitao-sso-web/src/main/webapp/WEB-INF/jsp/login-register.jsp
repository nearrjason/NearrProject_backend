<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ page session="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>登陆/注册 - 美桃网</title>
<link rel="stylesheet" href="/css/header.css">
<link rel="stylesheet" href="/css/login-register.css">
<link rel="stylesheet" href="/css/footer.css">
<link rel="stylesheet" href="/css/loading.css">
</head>
<body>
	<div class="header">
		<div class="logo">
			<a href=""><img src="/images/logo.svg" alt=""></a>
		</div>
	</div>
	<!--Header ends here-->

	<!--Main starts here-->
	<div class="main">
		<input id="redirectURL" type="hidden" value="${redirect}"> <input
			id="initType" type="hidden" value="${type }">
		<div class="wrap">
			<ul class="select-list">
				<li class="login" onclick="goLogIn()"><b>登陆</b></li>
				<li class="signup" onclick="goSignUp()"><b>注册</b></li>
			</ul>
		</div>
		<div class="window-wrap">
			<div class="login-window" id="login-interface">
				<form id="login_form" method="post" onsubmit="return false">

					<h2>登陆您的账户</h2>
					<br> <label for="email">用户名/邮箱</label><br> <input
						id="usernameOrEmail" name="usernameOrEmail" class="email" /> <br>
					<div class="pw-wrap">
						<label for="password">密码</label><br> <input name="password"
							type="password" class="password" id="login_password" value="" />
						<button id="pwToggle" type="button">
							<img class="v-btn" src="/images/icons/view.svg" alt="">
						</button>
					</div>
					<p id="login_error_message" class="error_message">密码须包含至少八位字符，并同时包含小写字母及数字</p>
					<a class="forget" href="/page/forget_psw">忘记密码？</a><br>
					<button id="login_submit" class="login-btn" type="submit">登陆</button>
				</form>

			</div>
			<div class="signup-window" id="register-interface">
				<form id="signup_form" name="signup_form" method="post"
					onsubmit="return false">
					<h2>注册新用户</h2>
					<label for="email">邮箱</label><br> <input id="register_email"
						name="email" class="email">
					<p id="register_error_message_email" class="error_message"></p>
					<span class="regInput" id="userMamErr"></span><br> <label
						for="username">用户名</label><br> <input id="register_username"
						type="text" name="username" class="username"><br>
					<p id="register_error_message_username" class="error_message"></p>
					<label for="password">密码</label><br> <input name="password"
						type="password" id="register_password" class="pw"
						pattern="(?=.*\d)(?=.*[a-z]).{8,}" onfocusout="checkPW(this)"><br>
					<p id="register_error_message_password" class="error_message"></p>
					<label for="password-check">确认密码</label><br> <input
						type="password" id="register_password_check" onfocusout=""><br>

					<!-- <p id="warning">两次密码不一致</p> -->
					<p id="register_error_message_password_check" class="error_message"></p>
					<button id="signup_submit" class="signup-btn" type="submit">创建账户</button>
					<p>
						点击创建账户即表示同意 <a href=""><b>&nbsp;使用条款&nbsp;</b></a> 与 <a href=""><b>&nbsp;用户协议&nbsp;</b></a>
					</p>
				</form>

			</div>
		</div>
	</div>
	<!--Main ends here-->

	<!--Footer starts here-->
	<div class="footer">
		<div class="cat1">
			<h3>客户服务</h3>
			<ul>
				<li><a href="">联系客服</a></li>|
				<li><a href="">帮助中心</a></li>|
				<li><a href="">退货换货</a></li>
			</ul>
		</div>

		<div class="cat2">
			<h3>关于美桃</h3>
			<ul>
				<li><a href="">关于我们</a></li>|
				<li><a href="">我们的商品</a></li>|
				<li><a href="">我们的服务</a></li>
			</ul>
		</div>

		<div class="copyright">
			<img src="/images/logo.svg" alt="">
			<p>&copy;2018 meitaomart.com 版权所有</p>
		</div>

	</div>
	<!--Footer ends here-->

	<div id="loadingImage" class="loading">
		<p class="loadingWords">正在登陆！</p>
		<div class="spinner">
			<div class="double-bounce1"></div>
			<div class="double-bounce2"></div>
		</div>
	</div>
	<div id="overlay"></div>


	<script src="/js/inputValidation.js" type="text/javascript"></script>
	<script type="text/javascript" src="/js/jquery-3.3.1.min.js"></script>
	<!-- <script type="text/javascript" src="/js/jquery-1.5.1.min.js"></script> -->
	<script src="/js/login-register.js" type="text/javascript"></script>
	<script src="/js/register.js" type="text/javascript"></script>
	<script src="/js/login.js" type="text/javascript"></script>
	<script src="/js/common.js" type="text/javascript"></script>

	<!-- <script type="text/javascript" src="/js/jquery.alerts.js"></script> -->
</body>
</html>