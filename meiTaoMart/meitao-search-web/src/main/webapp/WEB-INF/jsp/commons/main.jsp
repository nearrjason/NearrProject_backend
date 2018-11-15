<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<div class="main">
	<div class="nav">
		<ul class="nav-bar">
			<li id="title">相关商品分类</li>
			<li><p>共&nbsp;${recordCount }&nbsp;条搜索结果</p></li>
		</ul>

	</div>

	<div class="content">
		<div class="category">
			<div class="category-list">
				<c:forEach items="${itemCategoryList }" var="itemCategory2">
					<div class="secondlevel">
						<div class="little-wrap">

							<a
								href="http://192.168.1.100:8085/category.html?cn=${itemCategory2.name}">${itemCategory2.name}</a>

							<!-- <button id="cat-drop" onclick="categoryDrop(this)">
								<img src="/images/icons/down-arrow.svg" alt="">
							</button> -->
						</div>
						<%-- <ul class="thirdlevel">
							<c:forEach items="${itemCategory2.childrenList }"
								var="itemCategory3">
								<li><a
									href="">${itemCategory3.name}</a></li>
							</c:forEach>
						</ul> --%>
					</div>
				</c:forEach>
			</div>

		</div>

		<div class="result">
			<div class="items">
				<!--所有搜索结果商品显示-->

				<c:forEach items="${itemList }" var="item">
					<div class="single-item">
						<a href="http://192.168.1.100:8086/item/${item.id}.html"> <img
							src="${item.oneImage}" alt="">
							<p>${item.name}</p>
						</a>
						<p class="price">
							<c:if test="${item.discount != 0 }">
								<span class="line_through" style="color: #707070"> $<fmt:formatNumber
										value="${item.salePrice / 100}" maxFractionDigits="2"
										minFractionDigits="2" />
								</span>
								<span> $<fmt:formatNumber
										value="${item.salePrice * (100 - item.discount) / 10000}"
										maxFractionDigits="2" minFractionDigits="2" />
								</span>
							</c:if>
							<c:if test="${item.discount == 0 }">
								<span> $<fmt:formatNumber value="${item.salePrice / 100}"
										maxFractionDigits="2" minFractionDigits="2" />
								</span>
							</c:if>
						</p>
						<!--
    
                                检查商品库存，如有货，添加加入购物车按键
                                
                            -->
						<c:if test="${item.stockNumber == 0 }">
							已售完
						</c:if>
						<c:if test="${item.stockNumber != 0 }">
							<button class="add-to-cart" onclick="addToCart(${item.id})">
								<img src="/images/icons/plus-symbol.svg" alt="">
							</button>
						</c:if>

					</div>
				</c:forEach>
			</div>

			<div class="bottom">
				<div class="pagination">
					<a id="first">首页</a> <a id="previous"><img
						src="/images/icons/back.svg" alt=""></a>
					<div id="pag"></div>
					<a id="next"><img src="/images/icons/next.svg" alt=""></a> <a
						id="last">尾页</a>
					<p class="total-pages">
						共<span></span>页
					</p>
					<input id="totalPageNumber" value="${totalPages }"
						<%-- value="${totalPages }" --%> type="hidden"></input> <input
						id="currentKeyword" value="${query }" type="hidden"></input> <input
						id="currentPage" value="${page }" type="hidden"></input>
					<input id="currentCategoryName" value="${cn }" type="hidden"></input>
					<input id="searchType" type="hidden" value="${searchType }">
				</div>
			</div>



			<div id="snackbar">商品成功加入购物车</div>

			<div class="popup" id="snackbar-fail">
				<p class="cause"></p>
				<button type="submit" class="toast-close" onclick="refreshPage()">
					<img src="/images/icons/close-white.svg" alt="">
				</button>
			</div>
		</div>
	</div>
</div>