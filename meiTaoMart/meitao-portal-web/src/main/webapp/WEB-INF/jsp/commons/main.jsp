
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div class="MainWrap">
	<div class="Wrap">
		<div class="subHeader">
			<div class="mainNav">
				<div class="navmenu">
					<div class="categories hover">

						<div class="dt">
							<a name="sfbest_hp_hp_menu_all" class="trackref topall"
								href="/html/web/categorys.html">精选商品分类</a>
						</div>
						<!-- left bar 商品分类 -->
						<div id="allSort" class="dd">
							<div id="booksort">

								<c:forEach items="${itemCatList}" var="itemCat1">
									<div class="item">
										<span class="i-master">
											<h3 class="dev">
												<p class="fresh"></p>
												<div class="trackref" fro_id="1" target="_blank"
													name="sfbest_Uhead_Uhead_menu_category1-hot0">
													<a
														href="http://192.168.1.100:8085/category.html?cn=${itemCat1.name}">${itemCat1.name}</a>
												</div>

											</h3>
											<div class="level2cat">
												<c:forEach items="${itemCat1.childrenList}" var="itemCat2">

													<a name="sfbest_Uhead_Uhead_menu_category1-2"
														class="trackref"
														href="http://192.168.1.100:8085/category.html?cn=${itemCat2.name}"
														target="_blank">${itemCat2.name} </a>
													<br>

												</c:forEach>
											</div>
										</span>

										<!-- hover出来的部分 -->
										<%-- <div class="i-cm">
										<div class="i-left">
											<div class="cat-sort">
												<c:forEach items="${itemCat1.childrenList}" var="itemCat2">
													<dl>
														<dt>
															<strong><a
																name="sfbest_Uhead_Uhead_menu_category1-2"
																class="trackref"
																href=""
																target="_blank">${itemCat2.name} </a></strong>
														</dt>
														<dd>
															<c:forEach items="${itemCat2.childrenList}"
																var="itemCat3">
																<a name="sfbest_Uhead_Uhead_menu_category1-2"
																	class="trackref"
																	href="">${itemCat3.name}</a>
															</c:forEach>
														</dd>
													</dl>
												</c:forEach>
											</div>
											<a name="sfbest_Uhead_Uhead_menu_category1-banner"
												class="trackref"
												href="http://192.168.1.100/fresh/321-0-0-0-0-2-0-0-0-1010-4862.html"
												target="_blank">
												<div class="i-img">
													<!-- <img src="/images/html/1463455028.jpg"> -->
												</div>
											</a>
										</div>
										<div class="i-right">
											<span onclick="$('.item').removeClass('hover')"
												class="i-close"></span>
										</div>
									</div> --%>
									</div>
								</c:forEach>
							</div>
						</div>
					</div>
					<span class="clear"></span>
				</div>
			</div>

			<!--MAINMENU END MAINMENU END MAINMENU END-->


			<!--  轮播图 -->
			<div class="slide_show" id="slide_show">
				<div class="indexW">
					<div id="index_slide" class="slide_wrap">
						<ol>
							<c:forEach items="${ad1List}" var="node" varStatus="status">
								<li><a name="sfbest_hp_hp_focus_${status.index }"
									class="fore_pic trackref"
									href="http://192.168.1.100:8085/search.html?keyword=${node.name }"
									target="_blank"> <img id="lunbo_1" alt="${node.url }"
										src="${node.images }">
								</a></li>
							</c:forEach>

						</ol>
					</div>
					<%-- <div class="rSide">
					<c:forEach items="${ad1List}" var="node" varStatus="status">
						<li><a name="sfbest_hp_hp_focus_${status.index }"
							class="fore_pic trackref" href="${node.url }" target="_blank">
								<img id="lunbo_1" alt="${node.title }" src="${node.pic }">
						</a></li>
					</c:forEach>

				</div> --%>
				</div>
				<ul id="lunboNum">
					<c:forEach items="${ad1List }" varStatus="status">
						<li class="<c:if test="${status.index==0 }">cur</c:if>"></li>
					</c:forEach>
				</ul>
				<!-- <div class="indexbg" id="indexbg">
				<dl style="left: -1903px;">
					<dd style="width: 1903px; background: rgb(20, 16, 13);"></dd>
					<dd style="width: 1903px; background: rgb(119, 96, 86);"></dd>
					<dd style="width: 1903px; background: rgb(17, 12, 55);"></dd>
					<dd style="width: 1903px; background: rgb(239, 244, 248);"></dd>
					<dd style="width: 1903px; background: rgb(231, 32, 37);"></dd>
					<dd style="width: 1903px; background: rgb(128, 29, 92);"></dd>
					<dd style="width: 1903px; background: rgb(117, 144, 1);"></dd>
					<dd style="width: 1903px; background: rgb(253, 213, 29);"></dd>
				</dl>
			</div> -->
			</div>
			<!--row1 end-->

		</div>

		<div class="main-block">
			<h2>优选必买</h2>
			<div class="bestbuy">
				<c:forEach items="${optimizedItemList }" var="optimizedItem">
					<div class="single-item">
						<a href="http://192.168.1.100:8086/item/${optimizedItem.id}.html">
							<img src="${optimizedItem.oneImage}" alt="">
							<p>${optimizedItem.name}</p>
						</a>
						<p class="price">
							<c:if test="${optimizedItem.discount != 0 }">
								<span class="line_through" style="color: #707070"> $<fmt:formatNumber
										value="${optimizedItem.salePrice / 100}" maxFractionDigits="2"
										minFractionDigits="2" />
								</span>
								<span> $<fmt:formatNumber
										value="${optimizedItem.salePrice * (100 - optimizedItem.discount) / 10000}"
										maxFractionDigits="2" minFractionDigits="2" />
								</span>
							</c:if>
							<c:if test="${optimizedItem.discount == 0 }">
								<span> $<fmt:formatNumber
										value="${optimizedItem.salePrice / 100}" maxFractionDigits="2"
										minFractionDigits="2" />
								</span>
							</c:if>
						</p>
						<!--

                            检查商品库存，如有货，添加加入购物车按键
                            
                        -->
						<c:if test="${optimizedItem.stockNumber == 0 }">
							已售完
						</c:if>
						<c:if test="${optimizedItem.stockNumber != 0 }">
							<button class="add-to-cart"
								onclick="addToCart(${optimizedItem.id})">
								<img src="/images/icons/plus-symbol.svg" alt="">
							</button>
						</c:if>
					</div>
				</c:forEach>
			</div>
		</div>


		<!-- item blocks -->
		<div class="main-block">
			<h2>新品上架</h2>
			<div class="newItem">
				<c:forEach items="${recentItemList }" var="recentItem">
					<div class="single-item">
						<a href="http://192.168.1.100:8086/item/${recentItem.id}.html">
							<img src="${recentItem.oneImage}" alt="">
							<p>${recentItem.name}</p>
						</a>
						<p class="price">
							<c:if test="${recentItem.discount != 0 }">
								<span class="line_through" style="color: #707070"> $<fmt:formatNumber
										value="${recentItem.salePrice / 100}" maxFractionDigits="2"
										minFractionDigits="2" />
								</span>
								<span> $<fmt:formatNumber
										value="${recentItem.salePrice * (100 - recentItem.discount) / 10000}"
										maxFractionDigits="2" minFractionDigits="2" />
								</span>
							</c:if>
							<c:if test="${recentItem.discount == 0 }">
								<span> $<fmt:formatNumber
										value="${recentItem.salePrice / 100}" maxFractionDigits="2"
										minFractionDigits="2" />
								</span>
							</c:if>
						</p>
						<!--

                            检查商品库存，如有货，添加加入购物车按键
                            
                        -->
						<c:if test="${recentItem.stockNumber == 0 }">
							已售完
						</c:if>
						<c:if test="${recentItem.stockNumber != 0 }">
							<button class="add-to-cart" onclick="addToCart(${recentItem.id})">
								<img src="/images/icons/plus-symbol.svg" alt="">
							</button>
						</c:if>
					</div>
				</c:forEach>
			</div>
		</div>


		<%-- <div class="banner">
			<a href=""> <img
				src="../images/Wallpapers/clark_street_chicago-wallpaper-1920x1080.jpg"
				alt="">
			</a>
		</div>

		<div class="main-block">
			<div class="block-ads">
				<a href=""> <img
					src="../images/Wallpapers/city_79-wallpaper-1920x1080.jpg" alt="">
				</a> <a href=""> <img
					src="../images/Wallpapers/city_79-wallpaper-1920x1080.jpg" alt="">
				</a>
				<!-- <c:forEach items="${adList }" var="ad">
                    <a href="">
                        <img src="${ad image src}" alt="">
                    </a>
                </c:forEach> -->
			</div>
		</div>
		--%>



		<!-- <div id="loadingImage" class="loading">
			<p class="loadingWords">正在添加到购物车！</p>
			<div class="spinner">
				<div class="double-bounce1"></div>
				<div class="double-bounce2"></div>
			</div>
		</div>
		<div id="overlay"></div> -->
		<div id="snackbar">商品成功加入购物车</div>

		<div class="popup" id="snackbar-fail">
			<p class="cause"></p>
			<button type="submit" class="toast-close" onclick="refreshPage()">
				<img src="/images/icons/close-white.svg" alt="">
			</button>
		</div>
	</div>
</div>