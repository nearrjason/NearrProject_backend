<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta http-equiv="X-UA-Compatible" content="ie=edge"/>
    <title>结算中心 - 美桃网</title>
    <link rel="stylesheet" href="/css_meitao/header.css"/>
    <link rel="stylesheet" href="/css_meitao/checkout.css"/>
    <link rel="stylesheet" href="/css_meitao/footer.css"/>
    <!-- <link rel="stylesheet" href="/css_meitao/cart.css"/> -->
    <link rel="stylesheet" href="/css_meitao/loading.css"/>
</head>
<body style="background-color:#f2f2f2;">
	<jsp:include page="commons_meitao/header.jsp" />
	<jsp:include page="commons_meitao/main.jsp" />
	<jsp:include page="commons_meitao/footer.jsp" />
	
	<div id="loadingImage" class="loading">
		<p class="loadingWords">正在支付！</p>
		<div class="spinner">
			<div class="double-bounce1"></div>
			<div class="double-bounce2"></div>
		</div>
	</div>
	<div id="overlay"></div>
	
    <script type="text/javascript" src="/js_meitao/jquery-3.3.1.min.js"></script>
    <script src="/js_meitao/checkout.js" type="text/javascript"></script>
    <script src="/js_meitao/inputValidation.js" type="text/javascript"></script>
</body>
</html>