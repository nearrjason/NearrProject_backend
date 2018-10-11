<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
    <div class="main">
        <h1>个人中心</h1>
        <div class="main-box">
            <jsp:include page="main/left_bar.jsp" />
            <jsp:include page="main/account.jsp" />
            <jsp:include page="main/address.jsp" />
            <jsp:include page="main/orders.jsp" />
            <jsp:include page="main/orders-detail.jsp" />
            <jsp:include page="main/payment.jsp" />
            <jsp:include page="main/popup.jsp" />
            <input id="showType" type="hidden" value="${type }"></input>
        </div>
    </div>