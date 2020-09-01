<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>后台管理</title>
<!-- 引入 echarts.js -->
<script src="${pageContext.request.contextPath}/js/echarts.min.js"
	charset="utf-8"></script>
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
							<li><a
								href="${pageContext.request.contextPath}/admin/count/show"
								class="active">本周销售统计</a></li>
							<li><a
								href="${pageContext.request.contextPath}/admin/count/showGoods"
								class="active">本周商品销售统计</a></li>

						</ul>
					</nav>
				</div>
			</div>


			<div id="test1" style="width: 1200px; height: 600px;"></div>
			<script type="text/javascript">
			var url = '${pageContext.request.contextPath}/admin/count/goods';  
	        $.getJSON(url).done(function(json) {
			sale = json.sale;//销量  
            names = json.names;
			volume = json.volume;
			
		    var chart1 = echarts.init(document.getElementById('test1'));
		    var option = {
		            title: [
		                {
		                    top: '40%',
		                    left: 10,
		                    subtextStyle: {
		                        align: 'left',
		                        color: '#000000',
		                        fontSize: 12,
		                    },
		                    subtext: '本\n周\n销\n售\n额'//   \n换行
		                },
		                {
		                    top: '40%',
		                    right: 10,
		                    subtextStyle: {
		                        align: 'right',
		                        color: '#000000',
		                        fontSize: 12,
		                    },
		                    subtext: '本\n周\n销\n量\n'
		                },
		            ],
		            grid: {
		                top: 100
		            },
		            backgroundColor: {
		                type: 'linear',
		                x: 0,
		                y: 0,
		                x2: 0,
		                y2: 1,
		                global: false // 缺省为 false
		            },
		            tooltip: {
		                trigger: 'axis',
		                axisPointer: {
		                    type: 'cross',
		                    crossStyle: {
		                        color: '#999'
		                    }
		                }
		            },
		            legend: {
		                data: [
		                    {name: '销售额', icon: 'circle'},
		                    {name: '销量', icon: 'circle'},
		                ],
		     
		            },
		            xAxis: [
		                {
		                    type: 'category',
		                    data: names,
		                    axisPointer: {
		                        type: 'shadow'
		                    },
		                    axisLabel: {
		                        interval: 0,//横轴信息全部显示
		                    }
		                }
		            ],
		            yAxis: [//这里配置两条Y轴
		                {
		                    type: 'value',
		                    
		                    name: '单位（元）',

		                },
		                {
		                    type: 'value',
		                    
		                    name: '单位（件）',
		                   
		                }
		            ],
		            series: [
		                {
		                    name: '销售额',
		                    type: 'bar',																			
		                    yAxisIndex: 0, //使用的 y 轴的 index，在单个图表实例中存在多个 y轴的时候有用。
		                    data: sale,
		                },
		                {
		                    name: '销量',
		                    type: 'bar',
		              
		                    yAxisIndex: 1,
		                    data: volume,
		                },
		               
		            ],
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
		        }
		    ;
		    chart1.setOption(option);
	        });
		</script>

		</div>
	</div>
</body>
</html>