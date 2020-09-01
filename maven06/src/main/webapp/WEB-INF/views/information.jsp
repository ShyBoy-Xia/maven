<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>

<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="description"
	content="A front-end template that helps you build fast, modern mobile web apps.">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, minimum-scale=1.0">
<title>个人信息</title>

<!-- Add to homescreen for Chrome on Android -->
<meta name="mobile-web-app-capable" content="yes">
<link rel="icon" sizes="192x192" href="images/android-desktop.png">

<!-- Add to homescreen for Safari on iOS -->
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="apple-mobile-web-app-title" content="Material Design Lite">
<link rel="apple-touch-icon-precomposed" href="images/ios-desktop.png">

<!-- Tile icon for Win8 (144x144 + tile color) -->
<meta name="msapplication-TileImage"
	content="images/touch/ms-touch-icon-144x144-precomposed.png">
<meta name="msapplication-TileColor" content="#3372DF">

<link rel="shortcut icon" href="images/favicon.png">

<!-- SEO: If your mobile URL is different from the desktop URL, add a canonical link to the desktop page https://developers.google.com/webmasters/smartphone-sites/feature-phones -->
<!--
    <link rel="canonical" href="http://www.example.com/">
    -->

<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Roboto:regular,bold,italic,thin,light,bolditalic,black,medium&amp;lang=en">
<link rel="stylesheet"
	href="https://fonts.googleapis.com/icon?family=Material+Icons">
<link rel="stylesheet"
	href="https://code.getmdl.io/1.3.0/material.cyan-light_blue.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/infostyle.css">
<script src="${pageContext.request.contextPath}/js/jquery.js"></script>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/bootstrap/css/bootstrap.min.css">
<script
	src="${pageContext.request.contextPath}/css/bootstrap/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/js/information.js"></script>
<script src="${pageContext.request.contextPath}/js/sweetalert.min.js"></script>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/sweetalert.css">
<style>
.inputDiv {
  font-size: 0;
}

#view-source {
	position: fixed;
	display: block;
	right: 0;
	bottom: 0;
	margin-right: 40px;
	margin-bottom: 40px;
	z-index: 900;
}

.templatemo-blue-button {
	background-color: #39ADB4;
	border: none;
	color: white;
}
</style>
<script>
	/*加载年份*/
	function years(obj, Cyear) {
		var len = 114; // select长度,即option数量
		var selObj = document.getElementById(obj);
		var selIndex = len - 1;
		var newOpt; // OPTION对象

		// 循环添加OPION元素到年份select对象中
		for (i = 1; i <= len; i++) {
			if (selObj.options.length != len) { // 如果目标对象下拉框升度不等于设定的长度则执行以下代码
				newOpt = window.document.createElement("OPTION"); // 新建一个OPTION对象
				newOpt.text = Cyear - len + i; // 设置OPTION对象的内容
				newOpt.value = Cyear - len + i; // 设置OPTION对象的值
				selObj.options.add(newOpt, i); // 添加到目标对象中
			}

		}
	}

	function months() {
		var month = document.getElementById("month");
		month.length = 0;
		for (i = 1; i < 13; i++) {
			month.options.add(new Option(i, i));
		}

	}

	function change_date() {
		// var birthday = document.birthday; 
		var year = document.getElementById("year");
		var month = document.getElementById("month");
		var date = document.getElementById("date");
		vYear = parseInt(year.value);
		vMonth = parseInt(month.value);
		date.length = 0;

		//根据年月获取天数 
		var max = (new Date(vYear, vMonth, 0)).getDate();
		for (var i = 1; i <= max; i++) {
			date.options.add(new Option(i, i));
		}
	}
</script>

</head>
<body>

	<%--修改商品信息模态框--%>
	<!-- Modal -->
	<div class="modal fade" id="update-info" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">修改个人信息</h4>
				</div>
				<div class="modal-body">
					<form class="form-horizontal" id="insert-form" name="insert-form"
						method="post">
						<div class="form-group">
							<label for="name" class="col-sm-2 control-label">用户名</label>
							<div class="col-sm-9">
								<input type="text" class="form-control" name="username"
									id="username" value="${user.username}">
							</div>
						</div>
					  <!--  <div class="form-group">
							  <label for="name" class="col-sm-2 control-label">手机号</label>
							<div class="col-sm-9">
								<input type="text" class="form-control" name="telephone"
									id="telephone" value="${user.telephone}">
							</div>
						</div>-->
					</form>
				  <form class="form-inline mt-2 mb-4">
						<div data-toggle="distpicker" data-autoselect="3">
							<label for="telephone" class="col-sm-2 control-label"
								style="padding-left: 28px; padding-top: 10px">生日</label> <select
								class="form-control" id="year" name ="year" onchange="change_date()"
								onfocus="years('year',new Date().getFullYear()),change_date()" 
								"></select>年
							<select class="form-control" id="month" name ="month"
								onfocus="months(),change_date()" onchange="change_date()"
								></select>月
							<select class="form-control" id="date" name ="date"></select>日
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
					<button type="button" class="btn btn-primary" id="saveInfo">保存</button>
				</div>
			</div>
		</div>
	</div>


	<div
		class="demo-layout mdl-layout mdl-js-layout mdl-layout--fixed-drawer mdl-layout--fixed-header">
		<header
			class="demo-header mdl-layout__header mdl-color--grey-100 mdl-color-text--grey-600">
			<div class="mdl-layout__header-row">
				<span class="mdl-layout-title">个人信息</span>
				<div class="mdl-layout-spacer"></div>
				<div
					class="mdl-textfield mdl-js-textfield mdl-textfield--expandable">
				</div>
		</header>
		<div
			class="demo-drawer mdl-layout__drawer mdl-color--blue-grey-900 mdl-color-text--blue-grey-50">
			<header class="demo-drawer-header">
				<%-- <img src="images/user.jpg" class="demo-avatar">--%>
				<div class="demo-avatar-dropdown">
					<h1>淘一淘</h1>
					<%-- <span>hello@example.com</span>--%>
					<div class="mdl-layout-spacer"></div>
				</div>
			</header>
			<nav class="demo-navigation mdl-navigation mdl-color--blue-grey-800">
				<a class="mdl-navigation__link"
					href="${pageContext.request.contextPath}/main"><i
					class="mdl-color-text--blue-grey-400 material-icons"
					role="presentation">home</i>主页</a> <a class="mdl-navigation__link"
					href="${pageContext.request.contextPath}/information"><i
					class="mdl-color-text--blue-grey-400 material-icons"
					role="presentation">inbox</i>个人信息</a> <a class="mdl-navigation__link"
					href="${pageContext.request.contextPath}/info/list"><i
					class="mdl-color-text--blue-grey-400 material-icons"
					role="presentation">forum</i>订单管理</a> <a class="mdl-navigation__link"
					href="${pageContext.request.contextPath}/info/address"><i
					class="mdl-color-text--blue-grey-400 material-icons"
					role="presentation">local_offer</i>地址管理</a> <a
					class="mdl-navigation__link"
					href="${pageContext.request.contextPath}/info/favorite"><i
					class="mdl-color-text--blue-grey-400 material-icons"
					role="presentation">flag</i>我的收藏</a>
			</nav>
		</div>
		<main class="mdl-layout__content mdl-color--grey-100">
			<div class="mdl-grid demo-content">
				<div
					class="demo-charts mdl-color--white mdl-shadow--2dp mdl-cell mdl-cell--12-col mdl-grid">
					<div class="tab-content">
						<table class="table" cellpadding="6" cellspacing="1">
							<thead>
								<th style="border: 0px solid transparent">
									<%--<h1>个人信息</h1>--%>
								</th>
							</thead>
							<tbody>
								<tr>
									<th style="border: 0px solid transparent" class="tl">用户号</th>
									<td style="border: 0px solid transparent" class="tr"
										id="userIdVal">${user.getUserid()}</td>
								</tr>
								<tr>
									<th style="border: 0px solid transparent" class="tl">用户名</th>
									<td style="border: 0px solid transparent" class="tr"
										id="nameVal">${user.getUsername()}</td>
								</tr>
								<tr>
									<th style="border: 0px solid transparent" class="tl">注册时间</th>
									<td style="border: 0px solid transparent" class="tr"
										id="regTimeVal">${user.regtime.year+1900}年
										${user.regtime.month+1} 月 ${user.regtime.date} 日</td>
								</tr>
								<tr>
									<th style="border: 0px solid transparent" class="tl">绑定邮箱</th>
									<td style="border: 0px solid transparent" class="tr"
										id="emailVal">${user.getEmail()}</td>
								</tr>
								<!-- <tr>
									<th style="border: 0px solid transparent" class="tl">绑定手机号</th>
									<td style="border: 0px solid transparent" class="tr"
										id="telephoneVal">${user.getTelephone()}</td>
								</tr> -->
								<tr>
									<th style="border: 0px solid transparent" class="tl">生日</th>
									<td style="border: 0px solid transparent" class="tr"
										id="regTimeVal">${user.birthday.year+1900}年
										${user.birthday.month+1} 月 ${user.birthday.date} 日</td>
								</tr>
							</tbody>
							<tfoot>

							</tfoot>
						</table>
						<div>
							<form action="${pageContext.request.contextPath}/fileUpload"
								method="post" enctype="multipart/form-data">
								<div>
								<input type="file" name="filename" id="fileToUpload" 
								class="filestyle" data-buttonName="btn-primary" data-buttonBefore="true" data-icon="false" multiple="multiple"><input
									type="submit" value="上传头像" class="templatemo-blue-button" id="changeImage"/>
								</div>
							</form>
						</div>

					</div>
					<div class="mdl-card__actions mdl-card--border">
						<button class="templatemo-blue-button" id="changeInfo">
							<h5>修改信息</h5>
						</button>
					</div>
				</div>
			</div>
		</main>
	</div>
	<script src="https://code.getmdl.io/1.3.0/material.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap-filestyle.min.js"></script> 
</body>
</html>
