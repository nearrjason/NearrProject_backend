<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>我的购物车</title>
    <link rel="stylesheet" href="/css/topbar.css"/>
    <link rel="stylesheet" href="/css/header.css"/>
    <link rel="stylesheet" href="/css/cart.css"/>
    <link rel="stylesheet" href="/css/footer.css"/>
    <link rel="stylesheet" href="/css/cart-popup.css"/> 
    <link rel="stylesheet" href="/css/loading.css"/> 
</head>
<body>
	<jsp:include page="commons/topbar.jsp" />
	<jsp:include page="commons/header.jsp" />
	<jsp:include page="commons/main.jsp" />
	<script type="text/javascript" src="/js/jquery-3.3.1.min.js"></script>
    <script src="/js/cart.js" type="text/javascript"></script>
    <script src="/js/popup.js" type="text/javascript"></script>
    <script src="/js/meitaomart.js" type="text/javascript"></script>
    <script src="/js/jquery.cookie.js" type="text/javascript"></script>
    <script type="text/javascript" src="/js/jquery.price_format.2.0.min.js"></script>
</body>
</html>