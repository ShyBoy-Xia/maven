<%@ page language="java" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>">
<meta charset="utf-8">
<title>邮箱注册</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap/css/bootstrap-datetimepicker.min.css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/css/bootstrap/js/bootstrap.min.js"></script>

<script src="${pageContext.request.contextPath}/js/jquery.js"></script>
    <script src="${pageContext.request.contextPath}/js/sort.js"></script>
    <script src="${pageContext.request.contextPath}/js/holder.js"></script>

<style type="text/css">
#register {
	width: 450px;
	height: 100px;
	margin: 50px auto;
}

#btn {
	margin-left: 197px;
	margin-top: -24px;
	width: 120px;
	height: 25px;
	font-size: 14px;
	color: #7904c9
}

</style>


<script type="text/javascript">

$(function(){
	$("#btn").click(function(){
		if($("#email").val()){
			$.ajax({
				type:"POST",
				url :"${pageContext.request.contextPath}/registerMailQuest?"+Math.random(),
				data:{
					email:$("#email").val(),
				},
				success:function(data){
					alert("验证码发送成功，请注意查收。");
				},
			})
		}else{
			alert("邮箱发送失败");
			$("#notice").html("请填写邮箱");
			setTimeout(function(){
				$("#notice").hide();
			},1000);
		}
	}
	);
		//  判断用户是否可以注册
		$("#submit").click(function() {
			if ($("#email").val()) {
				$("#RegistForm").submit();
			} else { //  如果没有值
				$("#notice").html("请填写完整信息");
				setTimeout(function() {
					$("#notice").hide();
				}, 1000);
			}
		});
	});
</script>
</head>
<body>
	<div class="container">
		<div id="header">
			<%@ include file="header.jsp"%>
		</div>
		<div id="register">
			<form class="form-horizontal" id="RegistForm" method="post"
				action="${pageContext.request.contextPath}/RegisterResult">
				<fieldset>
					<div id="legend" class="">
						<legend class="">用户注册</legend>
					</div>
					<div class="form-group">
						<!-- Text input-->
						<label class="col-sm-2 control-label">用户名</label>
						<div class="col-sm-5">
							<input type="text" placeholder="请输入6位数账号..." class="form-control"
								required name="username">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">密码</label>
						<div class="col-sm-5">
							<input type="password" placeholder="请输入6位数密码..." required
								class="form-control" name="password">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">邮箱</label>
						<div class="col-sm-5">
							<input type="email" placeholder="请填写邮箱地址..." class="form-control"
								id="email" name="email" required>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">验证码</label>
						<div class="col-sm-5">
							<input type="text" name="code" id="code" placeholder="请输入邮箱的验证码"
								class="form-control" required> 
								<input type="button" name="btn"
								class="btn btn-default" id="btn" value="发送验证码" />
						</div>
					</div>
					<span id="notice" class="hide">请先完成邮箱验证</span><br />
					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-10">
							<a href="login.jsp" class="btn btn-success">返回手机号注册</a> <input
								class="btn btn-info" type="submit" id="submit" value="确认注册" />
						</div>
						<div class="error">
                                    ${errorMsg}
                                </div>
					</div>
				</fieldset>
			</form>
		</div>
	</div>
</body>
</html>