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
    <link rel="stylesheet" href="/css/base_w1200.css">
    <link rel="stylesheet" href="/css/index.css">
    <link rel="stylesheet" href="/css/topbar.css">
    <link rel="stylesheet" href="/css/header.css">
    <link rel="stylesheet" href="/css/footer.css">
    <link rel="stylesheet" href="/css/cart-popup.css">
    <link rel="stylesheet" href="/css/loading.css">
    <link rel="stylesheet" href="/css/toast.css">
    <script src="/js/constants.js" type="text/javascript"></script>
    <script src="/js/jquery-3.3.1.min.js" type="text/javascript"></script>
</head>
<body>
	<!-- header start -->
    
    <jsp:include page="commons/topbar.jsp" />
    <div id="headerPage" class="headerPage">
    	<jsp:include page="commons/header.jsp" />
    </div>
	<jsp:include page="commons/main.jsp" />
	<div class="footerPage">
		<jsp:include page="commons/footer.jsp" />
	</div>
	
	
    <script src="/js/popup.js" type="text/javascript"></script>
    <!-- <script src="/js/global_index.js" type="text/javascript"></script> -->
    <script src="/js/jquery.cookie.js" type="text/javascript"></script>
    <!-- <script src="/js/cookie.js" type="text/javascript"></script> -->
    <script src="/js/header.js" type="text/javascript"></script>
    <script src="/js/meitaomart.js" type="text/javascript"></script>
    <script src="/js/index.js" type="text/javascript"></script>
    <script src="/js/global_index.js" type="text/javascript"></script>
</body>
