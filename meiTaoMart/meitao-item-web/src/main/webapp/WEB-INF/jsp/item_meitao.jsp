<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>Document</title>
<link rel="stylesheet" href="/css/topbar.css">
<link rel="stylesheet" href="/css/header.css">
<link rel="stylesheet" href="/css/footer.css">
<link rel="stylesheet" href="/css/cart-popup.css">
<link rel="stylesheet" href="/css/item.css">
<link rel="stylesheet" href="/css/popup.css">
<link rel="stylesheet" href="/css/loading.css">
<link rel="stylesheet" href="/css/toast.css">
<link rel="shortcut icon"
	href="/images/Screen Shot 2018-09-17 at 5.19.22 PM.png" />
<script src="/js/constants.js" type="text/javascript"></script>
</head>
<body>
	<jsp:include page="commons/topbar.jsp" />
	<div id="headerPage" class="headerPage">
		<jsp:include page="commons/header.jsp" />
	</div>
	<jsp:include page="commons/main.jsp" />
	<div class="footerPage">
		<jsp:include page="commons/footer.jsp" />
	</div>

	<div id="loadingImage" class="loading">
		<p class="loadingWords">正在加入购物车！</p>
		<div class="spinner">
			<div class="double-bounce1"></div>
			<div class="double-bounce2"></div>
		</div>
	</div>
	<div id="overlay"></div>

	<script type="text/javascript" src="/js/common.js"></script>
	<script type="text/javascript" src="/js/jquery-3.3.1.min.js"></script>
	<script src="/js/item.js" type="text/javascript"></script>
	<script src="/js/popup.js" type="text/javascript"></script>
	<script src="/js/jquery.cookie.js" type="text/javascript"></script>
	<script src="/js/meitaomart.js" type="text/javascript"></script>
</body>
</html>