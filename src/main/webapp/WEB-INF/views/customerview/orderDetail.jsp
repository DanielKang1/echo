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
    <link href="${pageContext.request.contextPath }/css/demo.css" rel="stylesheet"/>
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
            
          
                            <div class="content">
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
                            </div>
                      </div>
                 </div>
         
   </div>
 </div>   


 
     
     
 	 

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
 