<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ page session="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<head>
	<meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>美桃商城</title>
    <link rel="stylesheet" href="/css/topbar.css">
    <link rel="stylesheet" href="/css/header.css">
    <link rel="stylesheet" href="/css/payment.css">
    <link rel="stylesheet" href="/css/order-detail.css">
    <link rel="stylesheet" href="/css/orders.css">
    <link rel="stylesheet" href="/css/account.css">
    <link rel="stylesheet" href="/css/address.css">
    <link rel="stylesheet" href="/css/footer.css">
    <link rel="stylesheet" href="/css/popup.css">
    <link rel="stylesheet" href="/css/cart-popup.css">
    <link rel="stylesheet" href="/css/allPage.css">
    <script src="/js/constants.js" type="text/javascript"></script>
    <script type="text/javascript" src="/js/jquery-3.3.1.min.js"></script>
</head>
<body>
	<div id="overlay"></div>
	<jsp:include page="commons/topbar.jsp" />
	<div class="headerPage">
		<jsp:include page="commons/header.jsp" />
	</div>
	<jsp:include page="commons/main.jsp" />
	<div class="footerPage">
		<jsp:include page="commons/footer.jsp" />
	</div>
	<!-- header start -->
	
	<%-- <jsp:include page="commons/main.jsp" />
	<jsp:include page="commons/footer.jsp" /> --%>
	
	<script src="/js/jquery.cookie.js" type="text/javascript"></script>
    <script src="/js/allPage.js" type="text/javascript"></script>
    <script src="/js/account.js" type="text/javascript"></script>
    <script src="/js/address.js" type="text/javascript"></script>
    <script src="/js/payment.js" type="text/javascript"></script>
    <script src="/js/popup.js" type="text/javascript"></script>
    <script src="/js/meitaomart.js" type="text/javascript"></script>
    <script src="/js/common.js" type="text/javascript"></script>
    <script src="/js/inputValidation.js" type="text/javascript"></script>
</body>
