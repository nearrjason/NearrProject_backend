<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<head>
	<meta content="text/html; charset=UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta http-equiv="X-UA-Compatible" content="ie=edge"/>
    <title>搜索结果 -（商品名称）</title>
    <link rel="stylesheet" href="/css/topbar.css"/>
    <link rel="stylesheet" href="/css/header.css"/>
    <link rel="stylesheet" href="/css/search.css"/>
    <link rel="stylesheet" href="/css/footer.css"/>
    <link rel="stylesheet" href="/css/cart-popup.css"/>
</head>
<body>
	<jsp:include page="commons/topbar.jsp" />
	<div id="headerPage">
    	<jsp:include page="commons/header.jsp" />
    </div>
	<jsp:include page="commons/main.jsp" />
	
	<script type="text/javascript" src="/js/common.js"></script>
	<script type="text/javascript" src="/js/jquery-3.3.1.min.js"></script>
	
    <script src="/js/search.js" type="text/javascript"></script>
    <script src="/js/popup.js" type="text/javascript"></script>
    <script src="/js/pagination.js" type="text/javascript"></script>
    <script src="/js/jquery.cookie.js" type="text/javascript"></script>
    <script src="/js/header.js" type="text/javascript"></script>
    <script src="/js/meitaomart.js" type="text/javascript"></script>
    <script src="/js/jquery.cookie.js" type="text/javascript"></script>
    
</body>