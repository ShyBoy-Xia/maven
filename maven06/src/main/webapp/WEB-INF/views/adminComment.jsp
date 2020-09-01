<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>后台管理</title>
<meta name="description" content="">
<meta name="author" content="templatemo">

<link
	href='http://fonts.googleapis.com/css?family=Open+Sans:400,300,400italic,700'
	rel='stylesheet' type='text/css'>
<link href="${pageContext.request.contextPath}/css/font-awesome.min.css"
	rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/bootstrap.min.css"
	rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/templatemo-style.css"
	rel="stylesheet">

<%--swal弹框--%>
<script src="${pageContext.request.contextPath}/js/sweetalert.min.js"></script>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/sweetalert.css">

</head>
<body>
	<div class="templatemo-flex-row">
		<jsp:include page="sidebar.jsp"></jsp:include>
		<!-- Main content -->
		<div class="templatemo-content col-1 light-gray-bg">
			<div class="templatemo-top-nav-container">
				<div class="row">
					<nav class="templatemo-top-nav col-lg-12 col-md-12">
						<ul class="text-uppercase">
							<li><a href="" class="active">所有评论</a></li>

						</ul>
					</nav>
				</div>
			</div>
			<div class="templatemo-content-container">
				<div class="templatemo-content-widget no-padding">
					<div class="panel panel-default table-responsive">
						<table id="goodsinfo"
							class="table table-striped table-bordered templatemo-user-table">
							<thead>
								<tr>
									<td><a href="" class="white-text templatemo-sort-by">id<span
											class="caret"></span></a></td>
									<td><a href="" class="white-text templatemo-sort-by">商品<span
											class="caret"></span></a></td>
									<td><a href="" class="white-text templatemo-sort-by">用户名<span
											class="caret"></span></a></td>
									<td><a href="" class="white-text templatemo-sort-by">评论<span
											class="caret"></span></a></td>
									<td><a href="" class="white-text templatemo-sort-by">评分<span
											class="caret"></span></a></td>
									<td>删除</td>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${comment}" var="com">
									<tr>
										<td>${com.goodsName}</td>
										<td>${com.username}</td>
										<td>${com.content}</td>
										<td>${com.point}</td>
										<td><a
											href="${pageContext.request.contextPath}/detail?goodsid=${com.goodsid}"
											class="templatemo-link">详情</a></td>
										<td>
											<a href="${pageContext.request.contextPath}/admin/comment/delete?commentid=${com.commentid}" class="templatemo-delete-btn">删除</a>
										</td>
									</tr>
								</c:forEach>

							</tbody>
						</table>
					</div>
				</div>

				<div class="pagination-wrap" id="page-div-nav">
					<div class="page-info" id="page-info-area"></div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>