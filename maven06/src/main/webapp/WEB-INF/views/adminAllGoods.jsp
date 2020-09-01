<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
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
<!-- JS -->
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-1.11.2.min.js"></script>
<link href="${pageContext.request.contextPath}/css/font-awesome.min.css"
	rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/bootstrap.min.css"
	rel="stylesheet">
<script
	src="${pageContext.request.contextPath}/css/bootstrap/js/bootstrap.min.js"></script>
<link href="${pageContext.request.contextPath}/css/templatemo-style.css"
	rel="stylesheet">


<script src="${pageContext.request.contextPath}/js/sweetalert.min.js"></script>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/sweetalert.css">
<%--<script src="${pageContext.request.contextPath}/js/jquery.form.min.js"></script>--%>
<style>
table {
	table-layout: fixed; /* 只有定义了表格的布局算法为fixed，下面td的定义才能起作用。 */
}

td {
	width: 100%;
	word-break: keep-all; /* 不换行 */
	white-space: nowrap; /* 不换行 */
	overflow: hidden; /* 内容超出宽度时隐藏超出部分的内容 */
	text-overflow: ellipsis;
	title: ghcl
	/* 当对象内文本溢出时显示省略标记(...) ；需与overflow:hidden;一起使用。*/
}

.show-span {
	border: none !important;
}
</style>
</head>
<body>

	<%--修改商品信息模态框--%>
	<!-- Modal -->
	<div class="modal fade" id="update-goods" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">修改商品信息</h4>
				</div>
				<div class="modal-body">
					<form class="form-horizontal" id="update-form" name="update-form"
						method="post">
						<div class="form-group">
							<label for="goodsid" class="col-sm-2 control-label">id</label>
							<div class="col-sm-9">
								<span id="goodsid" class="form-control"></span>
							</div>
						</div>
						<div class="form-group">
							<label for="goodsname" class="col-sm-2 control-label">商品名</label>
							<div class="col-sm-9">
								<input type="text" class="form-control" name="goodsname"
									id="goodsname">
							</div>
						</div>
						<div class="form-group">
							<label for="price" class="col-sm-2 control-label">价格</label>
							<div class="col-sm-9">
								<input type="number" class="form-control" name="price"
									id="price">
							</div>
						</div>
						<div class="form-group">
							<label for="num" class="col-sm-2 control-label">数量</label>
							<div class="col-sm-9">
								<input type="number" class="form-control" id="num" name="num">
							</div>
						</div>
						<div class="form-group">
							<label for="description" class="col-sm-2 control-label">描述</label>
							<div class="col-sm-9">
								<textarea class="form-control" id="description"
									name="description"></textarea>
							</div>
						</div>
						<div class="form-group">
							<label for="category" class="col-sm-2 control-label">分类1</label>
							<div class="col-sm-9">
								<select class="form-control" id="category" name="category">
									<c:forEach items="${categoryList}" var="item">
										<option value="${item.cateid}" >${item.catename}</option>
									</c:forEach>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label for="category" class="col-sm-2 control-label">分类2</label>
							<div class="col-sm-9">
								<select class="form-control" id="category" name="category2">
									<c:forEach items="${categorySecList}" var="item">
										<option value="${item.catesecid}" >${item.catesecname}</option>
									</c:forEach>
								</select>
							</div>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
					<button type="button" class="btn btn-primary" id="saveUpdate">保存</button>
				</div>
			</div>
		</div>
	</div>


	<!-- Left column -->
	<div class="templatemo-flex-row">
		<jsp:include page="sidebar.jsp" />
		<!-- Main content -->
		<div class="templatemo-content col-1 light-gray-bg">
			<jsp:include page="goodsNav.jsp" />
			<div class="templatemo-content-container">
				<div class="templatemo-content-widget no-padding">
					<div class="panel panel-default table-responsive">
						<table id="goodsinfo"
							class="table table-striped table-bordered templatemo-user-table">
							<thead>
								<tr>
									<th><a href="" class="white-text templatemo-sort-by">id<span
											class="caret"></span></a></th>
									<th><a href="" class="white-text templatemo-sort-by">商品名<span
											class="caret"></span></a></th>
									<th><a href="" class="white-text templatemo-sort-by">价格<span
											class="caret"></span></a></th>
									<th><a href="" class="white-text templatemo-sort-by">数量<span
											class="caret"></span></a></th>
									<th><a href="" class="white-text templatemo-sort-by">类别1<span
											class="caret"></span></a></th>
									<th><a href="" class="white-text templatemo-sort-by">类别2<span
											class="caret"></span></a></th>
									<th><a href="" class="white-text templatemo-sort-by">描述<span
											class="caret"></span></a></th>
									<th>编辑</th>
									<th>删除</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${pageInfo}" var="goods" varStatus="num">
									<tr>
										<td>${goods.goodsid}</td>
										<td><a
											href="${pageContext.request.contextPath}/detail?goodsid=${goods.goodsid}"
											class="templatemo-link">${goods.goodsname}</a></td>
										<td>${goods.price}</td>
										<td>${goods.num}</td>
										<td>${goods.cate1}</td>
										<td>${goods.cate2}</td>
										<td title="${goods.description}">${goods.description}</td>
										<td><button href="" class="templatemo-edit-btn">编辑</button></td>
										<td><button href="" class="templatemo-delete-btn">删除</button></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>


			</div>
		</div>
	</div>
	<div id="path" style="display: none;">${pageContext.request.contextPath}</div>
	<!-- jQuery -->
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/js/templatemo-script.js"></script>
	<script src="${pageContext.request.contextPath}/js/goodsManage.js"></script>
	<!-- Templatemo Script -->
	<script>
		$(document).ready(
				function() {
					// Content widget with background image
					var imageUrl = $('img.content-bg-img').attr('src');
					$('.templatemo-content-img-bg').css('background-image',
							'url(' + imageUrl + ')');
					$('img.content-bg-img').hide();
				});
	</script>
</body>
</html>
