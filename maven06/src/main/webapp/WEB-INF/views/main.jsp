<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>淘一淘</title>
<script src="${pageContext.request.contextPath}/js/jquery.js"></script>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/main.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/bootstrap/css/bootstrap.min.css">
<script
	src="${pageContext.request.contextPath}/css/bootstrap/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/js/sort.js"></script>
<script src="${pageContext.request.contextPath}/js/holder.js"></script>

</head>
<body>
	<div id="main" class="container">

		<%@ include file="header.jsp"%>
		<!-- 旋转图 -->
		<div class="header-bottom">
			<div class="sort">
				<div class="sort-channel">
					<ul class="sort-channel-list list-group">
						<c:forEach items="${category}" var="cate">
							<li class="list-group-item"><a
								href="${pageContext.request.contextPath}/category?cate=1&cateid=${cate.cateid}">${cate.catename }</a>
								<div class="sort-detail">
									<c:forEach items="${catesec[cate.cateid]}" var="catesec">
										<dl class="dl-hor">
											<dt>
												<a
													href="${pageContext.request.contextPath}/category?cate=2&cateid=${catesec.catesecid}&catename=${catesec.catesecname }">${catesec.catesecname }</a>
											</dt>
											<dd>
												<!--  <a href="#">*</a>
												<a
													href="#">*</a>
												<a href="">*</a> <a
													href="#">*</a>-->
											</dd>
										</dl>

									</c:forEach>
								</div></li>
						</c:forEach>

					</ul>
				</div>

			</div>
			<div id="mycarousel" class="carousel slide" data-ride="carousel">
				<div class="carousel-inner">
					<div class="item active">
						<img src="${pageContext.request.contextPath}/image/4.jpg" alt="">
					</div>

					<div class="item">
						<img src="${pageContext.request.contextPath}/image/3.jpg" alt="">
					</div>
					<div class="item">
						<img src="${pageContext.request.contextPath}/image/5.jpg" alt="">
					</div>
					<div class="item">
						<img src="${pageContext.request.contextPath}/image/6.jpg" alt="">
					</div>
				</div>

			</div>
			<div class="clear-float"></div>
		</div>

		<div class="content">
			<c:forEach items="${category}" var="cate">
				<c:if test="${!empty goodsmap[cate.cateid]}">
					<div class="module">
						<div class="hd">
							<h2>${cate.catename }</h2>
							<hr>
						</div>

						<div class="bd">
							<div class="data">
								<ul>
									<c:forEach items="${goodsmap[cate.cateid]}" var="goods">
										<li class="data-item-li">
											<div class="to-big">
												<a
													href="${pageContext.request.contextPath}/detail?goodsid=${goods.goodsid}">
													<img src="${pageContext.request.contextPath}/${goods.imagePaths[0].path}" alt=""
													width="200" height="200">
												</a>
											</div>
											<p class="text-right">
												<a
													href="${pageContext.request.contextPath}/detail?goodsid=${goods.goodsid}">${goods.goodsname}</a>
											</p>
											<div class="text-right">
												<b>￥${goods.price}</b>
											</div>
											<div>
												<c:if test="${goods.fav}">
													<button
														class="like-button glyphicon glyphicon-heart btn btn-default"
														data-id="${goods.goodsid}" style="display: none;"></button>
												</c:if>
												<c:if test="${!goods.fav}">
													<button
														class="like-button glyphicon glyphicon-heart-empty btn btn-default"
														data-id="${goods.goodsid}" style="display: none;"></button>
												</c:if>
												<!-- <button class="like-button1 glyphicon glyphicon-heart-empty btn btn-default "></button> -->
											</div>
										</li>
									</c:forEach>

									<div class="clear-float" style="clear: both;"></div>
								</ul>

							</div>
						</div>
					</div>
				</c:if>
			</c:forEach>

		</div>
	</div>
</body>
</html>


