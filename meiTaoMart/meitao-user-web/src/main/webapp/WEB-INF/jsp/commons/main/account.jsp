<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<div class="right" id="accountBox">
	<h2>账户设置</h2>
	<button type="button" class="pwChange" onclick="openChangeForm()">点击修改密码</button>
	<hr>
	<div class="user-info">
		<c:set var="user" value="${user}" />
		<form id="account_form" class="form_container">
			<div class="email">
				<label for="email">Email: </label>
				<!--以下三种邮件状态表示-->
				<!-- <button type="button" class="veri-btn" onclick="sendVerification()">点击验证邮箱</button>
				<p class="veri-await">请查收邮件</p>
				<p class="veri-pass">已验证</p> -->
				<br> <input type="text" placeholder="Email:"
					value="${user.email}" name="email" disabled>
			</div>
			<div class="userID">
				<label for="username">Username: </label><br> <input type="text"
					placeholder="Username: " value="${user.username}" name="username"
					disabled>
			</div>
			<div class="gender">
				<label for="gender">Gender: </label><br>
				<c:if test="${empty user.sex}">
					<select name="sex">
						<option value="" selected>保密</option>
						<option value=true>男</option>
						<option value=false>女</option>
					</select>
				</c:if>
				<c:if test="${user.sex == true}">
					<select name="sex">
						<option value="">保密</option>
						<option value=true selected>男</option>
						<option value=false>女</option>
					</select>
				</c:if>
				<c:if test="${user.sex == false}">
					<select name="sex">
						<option value="">保密</option>
						<option value=true>男</option>
						<option value=false selected>女</option>
					</select>
				</c:if>

			</div>

			<br> <br>

			<div class="dob">
				<label for="dob">Date of birth: </label><br> <input name="birthdayStr" type="date">
			</div>
			<div class="phone">
				<label for="phone">Phone: </label><br> <input type="tel"
					placeholder="" value="${user.phone}" name="phone">
			</div>
			<br>
			<!-- <div class="info">
				<label for="info">User Info: </label><br>
				<textarea cols="30" rows="10"></textarea>
			</div> -->
		</form>
	</div>
	<div class="save">
		<button type="submit" class="submit-button" onclick="updateUserInfo()">保存信息</button>
	</div>
</div>