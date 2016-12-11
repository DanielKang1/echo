<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 

<!DOCTYPE html>
<html lang="zh-CN">
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
	<meta content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0' name='viewport' />
	<title>Echo 订单信息页</title>
	<link rel="icon" type="image/png" href="${pageContext.request.contextPath }/img/hotelicon.png">
    <link href="${pageContext.request.contextPath }/css/bootstrap.min.css" rel="stylesheet" />
    <link href="${pageContext.request.contextPath }/css/animate.min.css" rel="stylesheet"/>
    <link href="${pageContext.request.contextPath }/css/light-bootstrap-dashboard.css" rel="stylesheet"/>
    <link href="${pageContext.request.contextPath }/css/font-awesome.min.css" rel="stylesheet"/>
    <style type="text/css">
    a:hover{
    text-decoration:underline;
    }
    </style>
</head>
<body> 

<div class="wrapper">

	<!-- 侧边栏 -->
    <div class="sidebar" data-color="azure" data-image="${pageContext.request.contextPath }/img/signup-bg1.jpg">    
    	<div class="sidebar-wrapper">
            <div class="logo">
                <a href="${pageContext.request.contextPath }/all" class="simple-text">
                    Echo Hotel
                </a>
            </div>
                       
            <ul class="nav">
                <li>
                    <a href="${pageContext.request.contextPath }/customer/info" target="_blank">
                        <i class="fa fa-user"></i>
                        <p>账号与密码</p>
                    </a>            
                </li>
                <li>
                    <a href="${pageContext.request.contextPath }/all" target="_blank">
                        <i class="fa fa-search"></i>
                        <p>酒店查询</p>
                    </a>
                </li> 
                <li class="active">
                     <a href="${pageContext.request.contextPath }/customer/goViewOrders" target="_blank">
                    	<i class="fa fa-list-ul"></i>
                        <p>订单一览</p>
                    </a>        
                </li>
                <li>
                    <a href="${pageContext.request.contextPath }/goViewEvaluations" target="_blank">
                        <i class="fa fa-comments"></i>
                        <p>我的评论</p>
                    </a>        
                </li>
                <li>
                    <a href="${pageContext.request.contextPath }/customer/goMemberHandle" target="_blank">
                         <i class="fa fa-check"></i>
                        <p>Echo会员</p>
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
                    <ul class="nav navbar-nav navbar-left">
                   		<li><a href=""><span style="font-size: 14px;color: #666666;">主页</span></a></li> 	
                        <li><a href=""><span style="font-size: 14px;color: #666666;">热门推荐</span></a></li> 
                        <li><a href=""><span style="font-size: 14px;color: #666666;">度假主题</span></a></li> 
                    </ul>
                    
                     <ul class="nav navbar-nav navbar-right">
                        <li class="dropdown">
                              <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                              	<span style="font-size: 14px;color: #666666;"> ${sessionScope.authCustomer.nickname}</span> <b class="caret"></b> </a>
                              <ul class="dropdown-menu">
                                <li><a href="#"><i class="fa fa-home"></i>我的主页</a></li>
                                <li><a href="#"><i class="fa fa-cog"></i>设置</a></li>
                                <li><a href="#"><i class="fa fa-building"></i>酒店查询</a></li>
                                <li><a href="#"><i class="fa fa-th-list"></i>订单查询</a></li>
                                <li class="divider"></li>
                                <li><a href="${pageContext.request.contextPath }/customer/signout"><i class="fa fa-sign-out"></i>退出</a>
                              </ul>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>
        <!-- 导航条END -->             

        <div class="content">
            <div class="container-fluid">
            
            
            	<div class="row">
                    <div class="col-md-12">
                        <div class="card">
                            <div class="header">
                                <h4 class="title">订单总表</h4>
                            </div>
                            <div class="content">
 								<table class="table">
					                <thead>
					                    <tr>
					                        <th class="text-left">订单ID</th>
					                        <th class="text-left">订单状态</th>
					                        <th class="text-left">酒店名称</th>
					                        <th class="text-left">下单日期</th>
					                        <th class="text-left">总金额</th>
					                    </tr>
					                </thead>
					                <tbody>
					                    <c:forEach items="${orders }" var="order">
						                    <tr>
						                        <td class="text-left">${order.orderID }</td>
						                        <td class="text-left">
							                        <c:choose>
														<c:when test="${order.orderType == 0}"><span style="color: #1DC7EA">未执行订单</span></c:when>
														<c:when test="${order.orderType == 1}"><span style="color: #87CB16">已执行订单</span></c:when>
														<c:when test="${order.orderType == 2}"><span style="color: #999999">已撤销订单</span></c:when>
														<c:when test="${order.orderType == 3}"><span style="color: #999999">异常订单</span></c:when>
														<c:when test="${order.orderType == 4}"><span style="color: #87CB16">已评价订单</span></c:when>
													</c:choose>	
						                        </td>
						                        <td class="text-left">${order.hotel.hotelName }  </td>
						                        <td class="text-left"><fmt:formatDate  pattern="yyyy-MM-dd HH:mm:ss" value="${order.creationDate }" /></td>
						                        <td class="text-left">&yen;${order.total }</td>
						                        <td class="td-actions text-right">
						                        <c:if test="${order.orderType == 0}">
						                            <a type="button" rel="tooltip" title="撤销订单" class="btn btn-danger btn-simple btn-xs" 
						                            href="${pageContext.request.contextPath }/customer/cancelorder/${order.orderID}" 
						                            onclick="if(confirm('确认撤销?')==false) return false;">
						                                <i class="fa fa-times"></i>
						                            </a>
						                        </c:if>   
						                        <c:if test="${order.orderType == 1}">
						                            <a  rel="tooltip"  href="${pageContext.request.contextPath }/eva/${order.orderID }"  title="添加评论" class="btn btn-success btn-simple btn-xs">
					                                	<i class="fa fa-edit"></i>
					                            	</a>
						                        </c:if>  
						                        <c:if test="${order.orderType == 3}">
							                        <button type="button" rel="tooltip" title="申诉" class="btn btn-warning btn-simple btn-xs" onclick="alert('请联系管理人员，进行线下处理。')">
						                                <i class="fa fa-bullhorn"></i>
						                            </button> 
						                        </c:if> 
						                            <a  rel="tooltip" title="查看详情" href="${pageContext.request.contextPath }/customer/order/detail/${order.orderID}" class="btn btn-info btn-simple btn-xs">
						                                <i class="fa fa-external-link"></i>
						                            </a>
						                        </td>
						                    </tr>
					                    </c:forEach>
					                </tbody>
		            			</table>
                            </div>
                        </div>
                    </div>
                 </div>
            
                 
                 
                 <div class="row">
                    <div class="col-md-12">
                        <div class="card">
                            <div class="header">
 								  <span class="category">分类查看订单：
	 								  <a href="${pageContext.request.contextPath }/customer/viewOrdersByType/1"><span style="color: #87CB16">已执行订单</span></a>&nbsp;&nbsp;&nbsp;
									  <a href="${pageContext.request.contextPath }/customer/viewOrdersByType/0"><span style="color: #1DC7EA">未执行订单</span></a>&nbsp;&nbsp;&nbsp;
									  <a href="${pageContext.request.contextPath }/customer/viewOrdersByType/2"><span style="color: #999999">已撤销订单</span></a>&nbsp;&nbsp;&nbsp;
									  <a href="${pageContext.request.contextPath }/customer/viewOrdersByType/3"><span style="color: #999999">异常订单</span></a>
 								  </span>
							</div>	  
                            <div class="content">
                            <c:if test="${ordersByType != null }">
 								<table class="table">
					                 <thead>
					                    <tr>
					                        <th class="text-left">订单ID</th>
					                        <th class="text-left">订单状态</th>
					                        <th class="text-left">酒店名称</th>
					                        <th class="text-left">下单日期</th>
					                        <th class="text-left">总金额</th>
					                    </tr>
					                </thead>
					                <tbody>
					                   <c:forEach items="${ordersByType }" var="order">
						                    <tr>
						                        <td class="text-left">${order.orderID }</td>
						                        <td class="text-left">
							                        <c:choose>
														<c:when test="${order.orderType == 0}"><span style="color: #1DC7EA">未执行订单</span></c:when>
														<c:when test="${order.orderType == 1}"><span style="color: #87CB16">已执行订单</span></c:when>
														<c:when test="${order.orderType == 2}"><span style="color: #999999">已撤销订单</span></c:when>
														<c:when test="${order.orderType == 3}"><span style="color: #999999">异常订单</span></c:when>
														<c:when test="${order.orderType == 4}"><span style="color: #87CB16">已评价订单</span></c:when>
													</c:choose>	
						                        </td>
						                        <td class="text-left">${order.hotel.hotelName }  </td>
						                        <td class="text-left"><fmt:formatDate  pattern="yyyy-MM-dd HH:mm:ss" value="${order.creationDate }" /></td>
						                        <td class="text-left">&yen;${order.total }</td>
						                        <td class="td-actions text-right">
						                         <c:if test="${order.orderType == 0}">
						                            <a type="button" rel="tooltip" title="撤销订单" class="btn btn-danger btn-simple btn-xs" 
						                            href="${pageContext.request.contextPath }/customer/cancelorder/${order.orderID}" 
						                            onclick="if(confirm('确认撤销?')==false) return false;">
						                                <i class="fa fa-times"></i>
						                            </a>
						                        </c:if>   
						                        <c:if test="${order.orderType == 1}">
						                            <a  rel="tooltip"  href="${pageContext.request.contextPath }/eva/${order.orderID }"  title="添加评论" class="btn btn-success btn-simple btn-xs">
					                                	<i class="fa fa-edit"></i>
					                            	</a>
						                        </c:if>  
						                        <c:if test="${order.orderType == 3}">
							                        <button type="button" rel="tooltip" title="申诉" class="btn btn-warning btn-simple btn-xs" onclick="alert('请联系管理人员，进行线下处理。')">
						                                <i class="fa fa-bullhorn"></i>
						                            </button> 
						                        </c:if> 
						                            <a  rel="tooltip" title="查看详情" href="${pageContext.request.contextPath }/customer/order/detail/${order.orderID}" class="btn btn-info btn-simple btn-xs">
						                                <i class="fa fa-external-link"></i>
						                            </a>
						                        </td>
						                    </tr>
					                    </c:forEach>
					                </tbody>
		            			</table>
		            			</c:if>
		            			<c:if test="${ ordersByType!=null && fn:length(ordersByType) == 0 }"> <div style="text-align:center;"><span style="font-size: 10px;color: #999999;">没有该类订单</span></div></c:if>
                            </div>
                      </div>
                 </div>
              </div>
                
                 <div class="row">
                    <div class="col-md-12">
                        <div class="card">
                            <div class="header">
	                            <p class="category">您预订过的酒店包括：</p>
                            </div>
                            <div class="content">
 								<table class="table table_honer">
					                <thead>
					                    <tr>
					                        <th class="text-center">#</th>
					                        <th>酒店名称</th>
					                        <th>城市</th>
					                        <th>商圈</th>
					                        <th class="text-left">预订次数</th>
					                    </tr>
					                </thead>
					                <tbody>
					                <c:forEach items="${staResult }" var="entry">
					                	<tr>
					                        <td class="text-center">${entry.key.hotelID }</td>
					                        <td><a href="${pageContext.request.contextPath }/hotel_${entry.key.hotelID}" target="_blank">${entry.key.hotelName}</a></td>
					                        <td>${entry.key.city }</td>
					                        <td>${entry.key.district}</td>
					                        <td class="text-left">${entry.value }</td>
					                    </tr>
					                </c:forEach>
					                </tbody>
		            			</table>
                            </div>
                        </div>
                    </div>
                 </div>
                 
          </div>      

       </div>
   </div>
     
 
	</div>
	<!-- 订单详细信息弹出框END -->

<c:if test="${ Info != null }">
${Info }
</c:if>

</body>

	<script src="${pageContext.request.contextPath }/scripts/jquery.min.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath }/scripts/bootstrap.min.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath }/scripts/light-bootstrap-dashboard.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath }/scripts/jquery.raty.js" type="text/javascript"></script>
 	<script type="text/javascript">
 	$('#star1').raty();
 	</script>
</html>
 