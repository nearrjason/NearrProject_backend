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
    <link rel="stylesheet" href="/css/header.css"/>
    <link rel="stylesheet" href="/css/checkout.css"/>
    <link rel="stylesheet" href="/css/footer.css"/>
    <!-- <link rel="stylesheet" href="/css/cart.css"/> -->
    <link rel="stylesheet" href="/css/loading.css"/>
    <script src="/js/constants.js" type="text/javascript"></script>
</head>
<body style="background-color:#f2f2f2;">
	<jsp:include page="commons/header.jsp" />
	<jsp:include page="commons/main.jsp" />
	<div class="footerPage">
		<jsp:include page="commons/footer.jsp" />
	</div>
	
	<!-- <div id="loadingImage" class="loading">
		<p class="loadingWords">正在支付！</p>
		<div class="spinner">
			<div class="double-bounce1"></div>
			<div class="double-bounce2"></div>
		</div>
	</div>
	<div id="overlay"></div> -->
	
    <script type="text/javascript" src="/js/jquery-3.3.1.min.js"></script>
    <script src="/js/checkout.js" type="text/javascript"></script>
    <script src="/js/inputValidation.js" type="text/javascript"></script>
</body>
</html>