<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>淘一淘-购物车</title>
    <script src="${pageContext.request.contextPath}/js/jquery.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/shopcart.css">
    <script src="${pageContext.request.contextPath}/css/bootstrap/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/sweetalert.min.js"></script>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/sweetalert.css">
</head>
<body>
<div id="main" class="container">
    <jsp:include page="header.jsp"/>
</div>
<div class="shopping_cart">
    <div class="container">
        <div class="row">
            <div class="all_wis_frm">
                <div class="col-md-12">
                    <div class="wishlist-content wishlist-content-2">
                            <div class="wishlist-table wishlist-table-2 table-responsive">
                            <table id="cart-table">
                                    <thead>
                                    <tr>     
                                        <th class="product-thumbnail product-thumbnail-2">日销量排行</th>
                                        <th class="product-thumbnail product-thumbnail-2"></th>
                                        <th class="product-name product-name_2"><span
                                                class="nobr">商品</span></th>    
                                        <th class="product-stock-stauts"><span class="nobr">
														最近销量 </span></th>
                                        
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:if test="${!empty goodsAndImageDay }">
                                    	<c:forEach items="${goodsAndImageDay}" var="goods">
                                    	<tr>
                                    		<td class="product-thumbnail product-thumbnail-2">${goods.num }</td>
                                    		<td class="product-thumbnail product-thumbnail-2">
                                    		<img src="${pageContext.request.contextPath}/${goods.imagePaths[0].path}" alt="">
                                    		</td>
                                        <td class="product-name product-name_2"><a
													href="${pageContext.request.contextPath}/detail?goodsid=${goods.goodsid}">
                                        <span
                                                class="nobr">${goods.goodsname }</span>
                                                </a></td>
                                        <td class="product-stock-stauts"><span class="nobr">
														${goods.volume } </span></td>
                                        </tr>
                                    	</c:forEach>
                                    </c:if>


                                    </tbody>
                                    
                                </table>
                                <table id="cart-table">
                                    <thead>
                                    <tr>     
                                        <th class="product-thumbnail product-thumbnail-2">周销量排行</th>
                                        <th class="product-thumbnail product-thumbnail-2"></th>
                                        <th class="product-name product-name_2"><span
                                                class="nobr">商品</span></th>    
                                        <th class="product-stock-stauts"><span class="nobr">
														最近销量 </span></th>
                                        
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:if test="${!empty goodsAndImageWeek }">
                                    	<c:forEach items="${goodsAndImageWeek}" var="goods">
                                    	<tr>
                                    		<td class="product-thumbnail product-thumbnail-2">${goods.num }</td>
                                    		<td class="product-thumbnail product-thumbnail-2">
                                    		<img src="${pageContext.request.contextPath}/${goods.imagePaths[0].path}" alt="">
                                    		</td>
                                        <td class="product-name product-name_2"><a
													href="${pageContext.request.contextPath}/detail?goodsid=${goods.goodsid}"><span
                                                class="nobr">${goods.goodsname }</span></a></td>
                                        <td class="product-stock-stauts"><span class="nobr">
														${goods.volume } </span></td>
                                        </tr>
                                    	</c:forEach>
                                    	</c:if>


                                    </tbody>
                                    
                                </table>
                                
                                <table id="cart-table">
                                    <thead>
                                    <tr>     
                                        <th class="product-thumbnail product-thumbnail-2">月销量排行</th>
                                        <th class="product-thumbnail product-thumbnail-2"></th>
                                        <th class="product-name product-name_2"><span
                                                class="nobr">商品</span></th>    
                                        <th class="product-stock-stauts"><span class="nobr">
														最近销量 </span></th>
                                        
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:if test="${!empty goodsAndImageMonth }">
                                    	<c:forEach items="${goodsAndImageMonth}" var="goods">
                                    	<tr>
                                    		<td class="product-thumbnail product-thumbnail-2">${goods.num }</td>
                                    		<td class="product-thumbnail product-thumbnail-2">
                                    		<img src="${pageContext.request.contextPath}/${goods.imagePaths[0].path}" alt="">
                                    		</td>
                                        <td class="product-name product-name_2"><a
													href="${pageContext.request.contextPath}/detail?goodsid=${goods.goodsid}"><span
                                                class="nobr">${goods.goodsname }</span></a></td>
                                        <td class="product-stock-stauts"><span class="nobr">
														${goods.volume } </span></td>
                                        </tr>
                                    	</c:forEach>
                                    	</c:if>


                                    </tbody>
                                    
                                </table>
                            </div>
                       
                    </div>
                </div>
            </div>
        </div>
    </div>
    </div>
</body>
</html>
