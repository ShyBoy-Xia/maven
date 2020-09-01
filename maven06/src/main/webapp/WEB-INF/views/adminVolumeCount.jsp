<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>后台管理</title>
<!-- 引入 echarts.js -->
<script src="${pageContext.request.contextPath}/js/echarts.min.js" charset="utf-8"></script>
<script type="text/javascript"  
    src="${pageContext.request.contextPath}/js/jquery-3.2.1.min.js"></script> 
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
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
							<li><a href="${pageContext.request.contextPath}/admin/count/show" class="active">本周销售统计</a></li>
							<li><a href="${pageContext.request.contextPath}/admin/count/showGoods" class="active">本周商品销售统计</a></li>

						</ul>
					</nav>
				</div>
			</div>
			<!-- 为ECharts准备一个具备大小（宽高）的Dom -->
			<div id="sales" style="width: 900px; height: 600px;"></div>
			<script type="text/javascript">
			var myChart = echarts.init(document.getElementById('sales'));  
	        var url = '${pageContext.request.contextPath}/admin/count/sales';  
	        $.getJSON(url).done(function(json) {  
	            // 2.获取数据  
	            sales = json.sales;//销量  
	            days = json.days;//日期  
	  
	            // 3.更新图表myChart的数据  
	            var option = {  
	                title : {  
	                    text : '本周销售统计'  
	                },  
	                tooltip : {},  
	                legend : {  
	                    data : [ '营业额' ],  
	                    //data : [ '营业额' ]  
	                },  
	                xAxis : {  
	                    data : days 
	                },  
	                yAxis : {
	                	
	                },  
	                series : [ {  
	                    name : '营业额',  
	                    type : 'bar',  
	                    data : sales 
	                } ],  
	                toolbox : {  
	                    show : true,  
	                    feature : {  
	                        mark : {  
	                            show : true  
	                        },  
	                        dataView : {  
	                            show : true,  
	                            readOnly : false  
	                        },  
	                        magicType : {  
	                            show : true,  
	                            type : [ 'line', 'bar' ]  
	                        },  
	                        restore : {  
	                            show : true  
	                        },  
	                        saveAsImage : {  
	                            show : true  
	                        }  
	                    }  
	                },  
	            };  
	            myChart.setOption(option);  
	        })
			</script>

		</div>
	</div>
</body>
</html>