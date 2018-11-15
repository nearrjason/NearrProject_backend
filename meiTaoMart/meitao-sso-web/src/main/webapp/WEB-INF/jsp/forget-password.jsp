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
	<title>忘记密码</title>
	<link rel="stylesheet" href="/css/header.css">
	<link rel="stylesheet" href="/css/forget-password.css">
	<link rel="stylesheet" href="/css/login-register.css">
	<link rel="stylesheet" href="/css/footer.css">
	<script src="/js/constants.js" type="text/javascript"></script>
</head>
<body>
	<!--Header starts here-->
	<div class="header">
		<div class="logo">
			<a href=""><img src="/images/logo.svg" alt=""></a>
		</div>
	</div>
	<!--Header ends here-->



	<!--Main starts here-->
	<div class="main">
		<div class="wrap">
			<ul class="step-list">
				<li class="step1 selected"><b>输入邮箱</b></li>
				<li class="step2"><b>查收验证邮件</b></li>
				<li class="step3"><b>设置新密码</b></li>
			</ul>
		</div>
		<div class="window-wrap">
			<div class="input-email">
				<h2>找回您的美桃账户</h2>
				<br> <label for="email">请输入您用于注册的电子邮箱</label><br> <input
					id="emailAddress" type="email" class="email" required><br>
				<p id="reset_password_email_error_message" class="error_message"></p>
				<button class="check-btn" type="submit" onclick="sendEmail()">发送验证邮件</button>
			</div>

			<div class="veri-code">
				<p>已发送验证邮件到</p>
				<br>
				<h2 id="emailAddressForResetPassword"></h2>
				<p class="resend">
					没收到邮件？<a href="#" onclick="resendEmail()">点击重新发送</a>
				</p>
				<div class="btns">
					<button class="check-btn" type="submit" onclick="backEmail()">换一个邮箱</button>
					<!-- <button class="check-btn" type="submit" onclick="sendEmail()">下一步</button> -->
				</div>
			</div>

			<input id="initTab" type="hidden" value="${setPassword }"> <input
				id="emailAddressFromPasswordCode" type="hidden"
				value="${emailAddress }"> <input id="isPasswordCodeExpire"
				type="hidden" value="${passwordCodeExpire }">

			<div class="new-pw">
				<h2>设置新密码</h2>
				<br> <label for="password">密码</label><br> <input
					type="password" id="new_password" class="pw"
					pattern="(?=.*\d)(?=.*[a-z]).{8,}" onfocusout="checkPW(this)"
					required><br> <label for="password-check">确认密码</label><br>
				<input type="password" id="new_password_check" class="pw-check"
					onfocusout=""><br>
				<p id="reset_password_error_message" class="error_message"></p>
				<button class="check-btn" type="submit" onclick="resetPassword()">重置密码并登陆</button>
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

	<script src="/js/inputValidation.js" type="text/javascript"></script>
	<script type="text/javascript" src="../js/jquery-3.3.1.min.js"></script>
	<script src="/js/forget_password.js" type="text/javascript"></script>
	<script src="/js/common.js" type="text/javascript"></script>
</body>
</html>