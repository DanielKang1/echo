<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 


<!DOCTYPE html>
<html lang="zh-CN">
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
	<meta content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0' name='viewport' />
	<title>Echo 查看客房</title>
	<link rel="icon" type="image/png" href="${pageContext.request.contextPath }/img/hotelicon.png">
    <link href="${pageContext.request.contextPath }/css/bootstrap.min.css" rel="stylesheet" />
    <link href="${pageContext.request.contextPath }/css/animate.min.css" rel="stylesheet"/>
    <link href="${pageContext.request.contextPath }/css/light-bootstrap-dashboard.css" rel="stylesheet"/>
    <link href="${pageContext.request.contextPath }/css/font-awesome.min.css" rel="stylesheet"/>
    <link href="${pageContext.request.contextPath }/css/demo.css" rel="stylesheet"/>
</head>
<body> 

<div class="wrapper">

<!-- 侧边栏 -->
    <div class="sidebar" data-color="azure" data-image="${pageContext.request.contextPath }/img/signup-bg1.jpg">    
    	<div class="sidebar-wrapper">
            <div class="logo">
                <a href="" class="simple-text">
                    Echo Hotel
                </a>
            </div>
                       
                       <ul class="nav">
                <li>
                    <a href="${pageContext.request.contextPath }/hotelstaff/hotelManage" target="_blank">
                        <i class="fa fa-building"></i>
                        <p>基本信息</p>
                    </a>            
                </li>
                <li>
                    <a href="${pageContext.request.contextPath }/hotelstaff/goCheckHandle" target="_blank">
                        <i class="fa fa-check-square-o"></i>
                        <p>入住处理</p>
                    </a>
                </li> 
                <li>
                    <a href="${pageContext.request.contextPath }/hotelstaff/goCheckHandle" target="_blank">
                    	<i class="fa fa-minus-square-o"></i>
                        <p>退房处理</p>
                    </a>        
                </li>
                <li>
                    <a href="${pageContext.request.contextPath }/hotelstaff/viewRooms" target="_blank">
                        <i class="fa fa-comments"></i>
                        <p>客房管理</p>
                    </a>        
                </li>
                <li>
                    <a href="${pageContext.request.contextPath }/hotelstaff/viewOrders" target="_blank">
                         <i class="fa fa-calendar"></i>
                        <p>查看订单</p>
                    </a>        
                </li>
                <li>
                    <a href="${pageContext.request.contextPath }/hotelstaff/promotions" target="_blank">
                         <i class="fa fa-shopping-cart"></i>
                        <p>酒店促销</p>
                    </a>        
                </li>
            </ul> 
    	</div>
    </div>
    <!-- 侧边栏END -->
    
    
    <div class="main-panel">
   		<!-- 导航条 -->
        <nav class="navbar navbar-default navbar-fixed">
            <div class="container-fluid">    
                 
                <div class="collapse navbar-collapse">      
                <ul class="nav navbar-nav navbar-left" style="margin-top: 16px;">
                   		<li> <span style="font-size: 14px;color: #666666;">${requestScope.hotel.hotelName}</span> </li> 	
                    </ul> 
                    
                    <ul class="nav navbar-nav navbar-right">
                        <li class="dropdown">
                              <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                              	<span style="font-size: 14px;color: #666666;"> ${sessionScope.authHotelStaff.staffName}</span> <b class="caret"></b> </a>
                              <ul class="dropdown-menu">
                                <li><a href="#"><i class="fa fa-home"></i>我的主页</a></li>
                                <li><a href="#"><i class="fa fa-cog"></i>设置</a></li>
                                <li><a href="#"><i class="fa fa-building"></i>酒店查询</a></li>
                                <li><a href="#"><i class="fa fa-th-list"></i>查看订单</a></li>
                                <li class="divider"></li>
                                <li><a href="${pageContext.request.contextPath }/hotelstaff/signout"><i class="fa fa-sign-out"></i>退出</a>
                              </ul>
                        </li>
                        
                    </ul>
                </div>
            </div>
        </nav>
        <!-- 导航条END -->                     

        <div class="content">
            <div class="container-fluid">
                 
                   <div class="card">
                       <div class="header">
                           <h4 class="title">查看订单</h4>
							<ol class="breadcrumb" style="margin-top: 20px;">
							  <li><a href="${pageContext.request.contextPath }/hotelstaff/getOrders_0">未执行订单 <span class="badge" style="margin-top: -5px;">${ordersSize.unexecuted}</span></a></li>
							  <li><a href="${pageContext.request.contextPath }/hotelstaff/getOrders_1">已执行订单 <span class="badge" style="margin-top: -5px;">${ordersSize.executed}</span></a></li>
							  <li><a href="${pageContext.request.contextPath }/hotelstaff/getOrders_3">异常订单 <span class="badge" style="margin-top: -5px;">${ordersSize.abnormal}</span></a></li>
							  <li><a href="${pageContext.request.contextPath }/hotelstaff/getOrders_2">已撤销订单 <span class="badge" style="margin-top: -5px;">${ordersSize.cancelled}</span></a></li>
							</ol>
							<c:if test="${requestScope.orderTypeError != null }"> 
							   ${requestScope.orderTypeError }
							</c:if>
                       </div>
                       <div class="content table-responsive table-full-width">
						 <div class="row content" style="margin-top: -20px;">	
						 <div style="margin-left: 20px;">
							 <c:if test="${fn:length(orders) == 0}"><span style="font-size: 10px;color: #999999">该类订单的数量为0。</span></c:if> 
						 </div>
						 <c:forEach items="${orders }"  var="order">
							 <div class="col-md-6">			            
							    <div class="panel panel-default" style="font-size: 10px;">
							      <div class="panel-heading" >
							        <span style="color: #999999">订单号：</span><span>${order.orderID }</span>
							        <span style="margin-left: 20px;color: #999999">${order.hotel.hotelName }</span>
							        <span style="float: right;color: #999999"><fmt:formatDate  pattern="yyyy-MM-dd hh:mm:ss" value="${order.creationDate }" /></span>
							      </div>
							      <div class="panel-body">
							         <table class="gridtable content">
										<tr>
											<td class="a1" width="20%">姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名：</td><td class="a2" width="20%">${order.reservedName }</td>
											<td class="a1" width="20%">房间类型：</td><td class="a2" width="30%">${order.roomType.typeName }</td>
										</tr>
										<tr>
											<td class="a1" width="20%">联系方式：</td><td class="a2" width="20%">${order.reservedPhone }</td>
											<td class="a1" width="20%">预订数量：</td><td class="a2" width="30%">${order.bookingNum }</td>
										</tr>
										<tr>
											<td class="a1" width="20%">支付方式：</td><td class="a2" width="20%">前台支付</td>
											<td class="a1" width="20%">支付总额：</td><td class="a2" width="30%" style="color: red">&yen;${order.total }</td>
										</tr>
										<tr>
											<td class="a1" width="20%">入住人数：</td><td class="a2" width="20%">${order.peopleNum }</td>
											<td class="a1" width="20%">携带小孩：</td><td class="a2" width="30%">
											<c:if test="${order.hasChild == 1}">携带孩子</c:if>
											<c:if test="${order.hasChild == 0}">未带孩子</c:if>
											</td>
										</tr>
										<tr>
										<td class="a1" width="20%">订单状态：</td><td class="a2" width="20%" style="color: green">
										<c:choose>
											<c:when test="${order.orderType == 0}">未执行订单</c:when>
											<c:when test="${order.orderType == 1}">已执行订单</c:when>
											<c:when test="${order.orderType == 2}">已撤销订单</c:when>
											<c:when test="${order.orderType == 3}">异常订单</c:when>
											<c:when test="${order.orderType == 4}">已评价订单</c:when>
										</c:choose>	
										</td>	
										<td class="a1" width="20%">入住时间：</td><td class="a2" width="30%" style="color: #CC9900">
										<fmt:formatDate  pattern="yyyy-MM-dd" value="${order.checkinDate }" />~<fmt:formatDate  pattern="yyyy-MM-dd" value="${order.checkoutDate }" /></td>
										</tr>
									</table>
							      </div>
							    </div>
						    </div>
						  </c:forEach>	
						     
						 </div>
	                   
	                 </div>
	              </div>  
               </div>
          </div>      
       </div>
    </div>
	                 

 

</body>

	<script src="${pageContext.request.contextPath }/scripts/jquery.min.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath }/scripts/bootstrap.min.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath }/scripts/light-bootstrap-dashboard.js" type="text/javascript"></script>
 

</html>