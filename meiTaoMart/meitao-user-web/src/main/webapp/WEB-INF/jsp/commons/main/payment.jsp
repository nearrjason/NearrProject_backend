<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<div class="right" id="paymentBox">
	<h2>信用卡管理</h2>
	<hr>
	<div class="credit">
		<!--section for card info iteration-->
		<c:forEach items="${cardList}" var="card" varStatus="cardUtils">
			<c:set var="cardNumberStr"
				value="${fn:trim(card.cardNumber)}"></c:set>
			<div class="credit-detail">
				<div class="card-brand">
					<c:if test="${card.cardType eq 3 }">
						<img src="/images/icons/amex.svg" alt="">
					</c:if>
					<c:if test="${card.cardType eq 4 }">
						<img src="/images/icons/visa.svg" alt="">
					</c:if>
					<c:if test="${card.cardType eq 5 }">
						<img src="/images/icons/mastercard.svg" alt="">
					</c:if>
					<c:if test="${card.cardType eq 6 }">
						<img src="/images/icons/discover.svg" alt="">
					</c:if>
				</div>
				<div class="card-info">
					<p class="c-n">
						<b>****&emsp;****&emsp;****&emsp;${fn:substring(cardNumberStr, 12, 16)}</b>
					</p>
					<div class="c-i">
						<p>
							持卡人&emsp;<b>${card.firstName }&emsp; ${card.lastName }</b>
						</p>
						<p>
							有效期&emsp;<b>${card.month }/${card.year }</b>
						</p>
					</div>
				</div>
				<div class="card-edit">
					<c:if test="${card.isMain == null || card.isMain == false }">
						<button type="submit" class="default-card" onclick="setAsDefault(${card.id}, 'card')">设为默认</button>
					</c:if>
					<c:if test="${card.isMain == true }">
						<button type="submit" class="default-card selected" onclick="setAsDefault(${card.id}, 'card')">已设默认</button>
					</c:if>
										
					<button class="edits" onclick="openForm('#editCreditForm', '${cardUtils.index}')">
						<img src="/images/icons/edit.svg" alt="">
					</button>
					<button class="edits" onclick="popup('#deleteCreditForm', '${card.id}')">
						<img src="/images/icons/delete.svg" alt="">
					</button>
				</div>
			</div>
		</c:forEach>





















		<!--^^^^^^-->
		<!-- <div class="credit-detail even">
			<div class="card-brand">
				<img src="../../icons/mastercard.svg" alt="">
			</div>
			<div class="card-info">
				<p class="c-n">
					<b>****&emsp;****&emsp;****&emsp;1234</b>
				</p>
				<div class="c-i">
					<p>
						持卡人&emsp;<b>Xingcheng Jiang</b>
					</p>
					<p>
						有效期&emsp;<b>01/21</b>
					</p>
				</div>
			</div>
			<div class="card-edit">
				<button type="submit" class="default-card">设为默认</button>
				<button class="edits" onclick="openForm('#editCreditForm')">
					<img src="../../icons/edit.svg" alt="">
				</button>
				<button class="edits" onclick="popup('#deleteCreditForm')">
					<img src="../../icons/delete.svg" alt="">
				</button>
			</div>
		</div>

		<div class="credit-detail">
			<div class="card-brand">
				<img src="../../icons/amex.svg" alt="">
			</div>
			<div class="card-info">
				<p class="c-n">
					<b>****&emsp;****&emsp;****&emsp;1234</b>
				</p>
				<div class="c-i">
					<p>
						持卡人&emsp;<b>Xingcheng Jiang</b>
					</p>
					<p>
						有效期&emsp;<b>01/21</b>
					</p>
				</div>
			</div>
			<div class="card-edit">
				<button type="submit" class="default-card">设为默认</button>
				<button class="edits" onclick="openForm('#editCreditForm')">
					<img src="../../icons/edit.svg" alt="">
				</button>
				<button class="edits" onclick="popup('#deleteCreditForm')">
					<img src="../../icons/delete.svg" alt="">
				</button>
			</div>
		</div>

		<div class="credit-detail even">
			<div class="card-brand">
				<img src="../../icons/discover.svg" alt="">
			</div>
			<div class="card-info">
				<p class="c-n">
					<b>****&emsp;****&emsp;****&emsp;1234</b>
				</p>
				<div class="c-i">
					<p>
						持卡人&emsp;<b>Xingcheng Jiang</b>
					</p>
					<p>
						有效期&emsp;<b>01/21</b>
					</p>
				</div>
			</div>
			<div class="card-edit">
				<button type="submit" class="default-card">设为默认</button>
				<button class="edits" onclick="openForm('#editCreditForm')">
					<img src="../../icons/edit.svg" alt="">
				</button>
				<button class="edits" onclick="popup('#deleteCreditForm')">
					<img src="../../icons/delete.svg" alt="">
				</button>
			</div>
		</div> -->

	</div>
	<div class="add">
		<button type="button" class="add-button"
			onclick="openAddForm('#addCreditForm')">添加信用卡</button>
	</div>
</div>