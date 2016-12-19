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
	<title>Echo 入住与退房处理</title>
	<link rel="icon" type="image/png" href="${pageContext.request.contextPath }/img/hotelicon.png">
    <link href="${pageContext.request.contextPath }/css/bootstrap.min.css" rel="stylesheet" />
    <link href="${pageContext.request.contextPath }/css/animate.min.css" rel="stylesheet"/>
    <link href="${pageContext.request.contextPath }/css/light-bootstrap-dashboard.css" rel="stylesheet"/>
    <link href="${pageContext.request.contextPath }/css/bootstrap-table.css" rel="stylesheet"/>
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
                 
				  
				  <!-- 客房管理 --> 
					 <div class="card">
                      <div class="header">
                          <div class="row">
                                <div class="col-md-4">
                                    <h4 class="title">当前入住信息</h4>
                                </div>
                                <div class="col-md-8">
                                </div>
                           </div>
                      </div>
                      <div class="content">
                        <div class="row">
							<div class="col-lg-12">
							
								<c:if test="${notCheckoutRoomCheckItems == null}">
								     <hr/>
									 <span style="font-size: 10px;color: #999999">没有当前入住信息。</span>
								</c:if>
								 <table class="table">
								    <thead>
								        <tr>
								            <th class="text-center">#</th>
								            <th class="text-center">客户ID</th>
								            <th class="text-center">订单ID</th>
								            <th class="text-center">房间号</th>
								            <th class="text-center">入住处理时间</th>
								            <th class="text-right">查看订单</th>
								        </tr>
								    </thead>
								    <tbody>
								    <c:forEach var="roomcheck" items="${notCheckoutRoomCheckItems}">
								        <tr>
								            <td class="text-center">${roomcheck.id }</td>
								            <td class="text-center">${roomcheck.userID }</td>
								            <td class="text-center">${roomcheck.orderID }</td>
								            <td class="text-center">${roomcheck.roomNumber }</td>
								            <td class="text-center"><fmt:formatDate  pattern="yyyy-MM-dd" value="${roomcheck.checkInDate }" /></td>
								            <td class="td-actions text-right">
								                <a  class="btn btn-info btn-simple btn-xs" href="${pageContext.request.contextPath }/hotelstaff/getOrderInfo/${roomcheck.orderID }">
								                    <i class="fa fa-bars"></i>
								                </a>
								            </td>
								        </tr>
								  </c:forEach>
								    </tbody>
								</table>
								<br>
								
						<!-- 订单详情 -->	
						<c:if test="${orderInfo != null}">
						 <div class="panel panel-default" style="font-size: 10px;margin-top: 20px;">
					      <div class="panel-heading" >
					        <span style="color: #999999">订单号：</span><span>${orderInfo.orderID }</span>
					        <span style="margin-left: 20px;color: #999999">${orderInfo.hotel.hotelName }</span>
					      </div>
					      <div class="panel-body">
					       <table class="gridtable content">
								<tr>
									<td class="a1" width="10%">姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名：</td><td class="a2" width="10%">${orderInfo.reservedName }</td>
									<td class="a1" width="10%">联系方式：</td><td class="a2" width="10%">${orderInfo.reservedPhone }</td>
									<td class="a1" width="10%">房间类型：</td><td class="a2" width="20%">${orderInfo.roomType.typeName }</td>
									<td class="a1" width="10%">预订数量：</td><td class="a2" width="10%">${orderInfo.bookingNum }</td>
								</tr>
								<tr>
									<td class="a1" width="10%">支付方式：</td><td class="a2" width="10%">前台支付</td>
									<td class="a1" width="10%">支付总额：</td><td class="a2" width="10%" style="color: red">&yen;${orderInfo.total }</td>
									<td class="a1" width="10%">入住人数：</td><td class="a2" width="10%">${orderInfo.peopleNum }</td>
									<td class="a1" width="10%">携带小孩：</td><td class="a2" width="10%">
									<c:if test="${orderInfo.hasChild == 1}">携带孩子</c:if>
									<c:if test="${orderInfo.hasChild == 0}">未带孩子</c:if>
									</td>
								</tr>
								<tr>
								<td class="a1" width="10%">订单状态：</td><td class="a2" width="10%" style="color: green">
							   <c:choose>
									<c:when test="${orderInfo.orderType == 0}">未执行订单</c:when>
									<c:when test="${orderInfo.orderType == 1}">已执行订单</c:when>
									<c:when test="${orderInfo.orderType == 2}">已撤销订单</c:when>
									<c:when test="${orderInfo.orderType == 3}">异常订单</c:when>
								</c:choose>	
								</td>	
								<td class="a1" width="10%">入住时间：</td><td class="a2" width="15%" style="color: #CC9900">
								<fmt:formatDate  pattern="yyyy-MM-dd" value="${orderInfo.checkinDate }" /></td>
								</tr>
							</table>
							 </div>  
							  </div>
							  </c:if>
							 <!-- 订单详情END -->	
				  
				  
								
					</div>
				 </div>
			 </div>
		  </div>	  
		  <!-- 客房管理END -->
				  
				  
				   <!-- 入住处理-->
                 <div class="card">
                      <div class="header">
                          <div class="row">
                                <div class="col-md-4">
                                    <h4 class="title">入住处理</h4>
                                </div>
                                <div class="col-md-8">
                                 <form class="form-inline" action="${pageContext.request.contextPath }/hotelstaff/searchOrder" method="POST">
									  <div class="form-group">
									    <input type="text" class="form-control" id="phone" name="phone" placeholder="请输入用户预留手机号">
									  </div>
									  <button type="submit" class="btn btn-info btn-fill">搜索</button>
								 </form>
                                </div>
                           </div>
                      </div>
                      <div class="content">
					      <c:if test="${unexecutedOrders != null }">
							<c:forEach items="${unexecutedOrders }" var="order">
							<div class="panel panel-info" style="font-size: 10px;margin-top: 20px;">
					      <div class="panel-heading" >
					        <span style="color: #999999">订单号：</span><span>${order.orderID }</span>
					        <span style="margin-left: 20px;color: #999999">${order.hotel.hotelName }</span>
					        <span style="float: right;color: #999999"><fmt:formatDate  pattern="yyyy-MM-dd hh:mm:ss" value="${order.creationDate }" /></span>
					      </div>
					      <div class="panel-body">
					       <table class="gridtable content">
								<tr>
									<td class="a1" width="10%">姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名：</td><td class="a2" width="10%">${order.reservedName }</td>
									<td class="a1" width="10%">联系方式：</td><td class="a2" width="10%">${order.reservedPhone }</td>
									<td class="a1" width="10%">房间类型：</td><td class="a2" width="20%">${order.roomType.typeName }</td>
									<td class="a1" width="10%">预订数量：</td><td class="a2" width="10%">${order.bookingNum }</td>
								</tr>
								<tr>
									<td class="a1" width="10%">支付方式：</td><td class="a2" width="10%">前台支付</td>
									<td class="a1" width="10%">支付总额：</td><td class="a2" width="10%" style="color: red">&yen;${order.total }</td>
									<td class="a1" width="10%">入住人数：</td><td class="a2" width="10%">${order.peopleNum }</td>
									<td class="a1" width="10%">携带小孩：</td><td class="a2" width="10%">
									<c:if test="${order.hasChild == 1}">携带孩子</c:if>
									<c:if test="${order.hasChild == 0}">未带孩子</c:if>
									</td>
								</tr>
								<tr>
								<td class="a1" width="10%">订单状态：</td><td class="a2" width="10%" style="color: green">
							   <c:choose>
									<c:when test="${order.orderType == 0}">未执行订单</c:when>
									<c:when test="${order.orderType == 1}">已执行订单</c:when>
									<c:when test="${order.orderType == 2}">已撤销订单</c:when>
									<c:when test="${order.orderType == 3}">异常订单</c:when>
								</c:choose>	
								</td>	
								<td class="a1" width="10%">入住时间：</td><td class="a2" width="15%" style="color: #CC9900">
								<fmt:formatDate  pattern="yyyy-MM-dd" value="${order.checkinDate }" />~<fmt:formatDate  pattern="yyyy-MM-dd" value="${order.checkoutDate }" /></td>
								</tr>
							</table>
							
							<div style="margin-top: 20px;margin-left: -20px;">
							  <a class="btn btn-success btn-fill" style="margin-left: 30px;" href="${pageContext.request.contextPath }/hotelstaff/checkin_${order.orderID }">确认入住</a> 
							</div>
							
							 </div>  
							  </div>
							
							
							</c:forEach>
							</c:if>
							
							 <c:if test="${roomNumbers == null }">
								 <c:if test="${unexecutedOrders != null && fn:length(unexecutedOrders) == 0}">
								 <hr/>
								 <span style="font-size: 10px;color: #999999">没有以此手机号为预留号码的订单。</span>
								 </c:if>
							 </c:if>
					      </div>
					    </div>
				 <!-- 入住处理END -->
					    
				<!-- 退房处理 --> 
				  <div class="card">
                      <div class="header">
                          <div class="row">
                                <div class="col-md-4">
                                    <h4 class="title">退房处理</h4>
                                </div>
                                <div class="col-md-8" style="margin-left: -100px;">
                                 <form class="form-inline" action="${pageContext.request.contextPath }/hotelstaff/getUsingRoom" method="POST">
									  <div class="form-group">
									    <input type="text" class="form-control" id="roomNumber" name="roomNumber" placeholder="请输入房间号">
									  </div>
									  <button type="submit" class="btn btn-info btn-fill">搜索</button>
								 </form>
                                </div>
                           </div>
                      </div>
                      <div class="content">
						  <c:if test="${getRoomError != null }">
						   <hr/>
							  <span style="font-size: 10px;color: #999999">${getRoomError  }</span>
						  </c:if>
					 <c:if test="${roomcheckItemSearchResult != null && orderSearchResult != null }">
                      <table class="table">
						    <thead>
						        <tr>
						            <th class="text-center">#</th>
						            <th>预留姓名</th>
						            <th>订单ID</th>
						            <th>房间号</th>
						            <th class="text-right">入住处理时间</th>
						            <th class="text-right">退房处理</th>
						        </tr>
						    </thead>
						    <tbody>
						        <tr> 
						            <td class="text-center">${roomcheckItemSearchResult.id }</td>
						            <td>${orderSearchResult.reservedName }</td>
						            <td>${orderSearchResult.orderID }</td>
						            <td>${roomcheckItemSearchResult.roomNumber }</td>
						            <td class="text-right"><fmt:formatDate  pattern="yyyy-MM-dd hh:mm:ss" value="${roomcheckItemSearchResult.checkInDate }" /></td>
						             <td class=" text-right">
						                <a href="${pageContext.request.contextPath }/hotelstaff/checkout_${roomcheckItemSearchResult.id }&roomNumber_${roomcheckItemSearchResult.roomNumber }"  class="btn btn-success btn-sm btn-fill">退房</a>
						            </td>
						        </tr>
						    </tbody>
						</table> 
						<div class="panel panel-default" style="font-size: 10px;margin-top: 20px;">
					      <div class="panel-heading" >
					        <span style="color: #999999">相关订单：</span><span>${orderSearchResult.orderID }</span>
					        <span style="margin-left: 20px;color: #999999">${orderSearchResult.hotel.hotelName }</span>
					      </div>
					      <div class="panel-body">
					       <table class="gridtable content">
								<tr>
									<td class="a1" width="10%">姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名：</td><td class="a2" width="10%">${orderSearchResult.reservedName }</td>
									<td class="a1" width="10%">联系方式：</td><td class="a2" width="10%">${orderSearchResult.reservedPhone }</td>
									<td class="a1" width="10%">房间类型：</td><td class="a2" width="20%">${orderSearchResult.roomType.typeName }</td>
									<td class="a1" width="10%">预订数量：</td><td class="a2" width="10%">${orderSearchResult.bookingNum }</td>
								</tr>
								<tr>
									<td class="a1" width="10%">支付方式：</td><td class="a2" width="10%">前台支付</td>
									<td class="a1" width="10%">支付总额：</td><td class="a2" width="10%" style="color: red">&yen;${orderSearchResult.total }</td>
									<td class="a1" width="10%">入住人数：</td><td class="a2" width="10%">${orderSearchResult.peopleNum }</td>
									<td class="a1" width="10%">携带小孩：</td><td class="a2" width="10%">
									<c:if test="${orderSearchResult.hasChild == 1}">携带孩子</c:if>
									<c:if test="${orderSearchResult.hasChild == 0}">未带孩子</c:if>
									</td>
								</tr>
								<tr>
								<td class="a1" width="10%">订单状态：</td><td class="a2" width="10%" style="color: green">
							   <c:choose>
									<c:when test="${orderSearchResult.orderType == 0}">未执行订单</c:when>
									<c:when test="${orderSearchResult.orderType == 1}">已执行订单</c:when>
									<c:when test="${orderSearchResult.orderType == 2}">已撤销订单</c:when>
									<c:when test="${orderSearchResult.orderType == 3}">异常订单</c:when>
								</c:choose>	
								</td>	
								<td class="a1" width="10%">入住时间：</td><td class="a2" width="15%" style="color: #CC9900">
								<fmt:formatDate  pattern="yyyy-MM-dd" value="${orderSearchResult.checkinDate }" />~<fmt:formatDate  pattern="yyyy-MM-dd" value="${orderSearchResult.checkoutDate }" /></td>
								</tr>
							</table>
							 </div>  
							  </div>
							</c:if>
					 </div>
				  </div>
				  <!-- 退房处理END -->
				 
				  <!-- 线下处理 -->
				  <div class="card">
                      <div class="header">
                          <div class="row">
                                <div class="col-md-4">
                                    <h4 class="title">线下入住/退房</h4>
                                </div>
                                <div class="col-md-8">
                                </div>
                           </div>
                      </div>
                      <div class="content">
                         <form class="form-inline" action="${pageContext.request.contextPath }/hotelstaff/checkinOffline" method="POST">
							  <div class="form-group">
							    <input type="text" class="form-control" id="roomNumber" name="roomNumber" placeholder="请输入房间号">
							  </div>
							  <button type="submit" class="btn btn-info btn-fill">入住</button>
						 </form>
						 <br>
						 <form class="form-inline" action="${pageContext.request.contextPath }/hotelstaff/checkoutOffline" method="POST">
							  <div class="form-group">
							    <input type="text" class="form-control" id="roomNumber" name="roomNumber" placeholder="请输入房间号" onkeyup="value=value.replace(/[^\d]/g,'')">
							  </div>
							  <button type="submit" class="btn btn-info btn-fill">退房</button>
						 </form>
					 </div>
				  </div> 
				  <!-- 线下处理END -->
				  
				  
		 
					    
					    
                    
                      </div>
                 </div>
             </div>
       </div>
       
       <c:if test="${roomNumbers != null }">
       	${roomNumbers }
       </c:if>
  
 	   <c:if test="${checkoutSuccess != null }">
       	${checkoutSuccess }
       </c:if>
       
       <c:if test="${orderInfoIsNull != null}">
	      ${orderInfoIsNull }
	   </c:if>
       
       <c:if test="${checkOfflineInfo != null}">
	      ${checkOfflineInfo }
	   </c:if>
	                 


</body>

	<script src="${pageContext.request.contextPath }/scripts/jquery.min.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath }/scripts/bootstrap.min.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath }/scripts/light-bootstrap-dashboard.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath }/scripts/bootstrap-table.js" type="text/javascript"></script>

</html>