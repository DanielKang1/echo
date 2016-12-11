<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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
                <li>
                     <a href="${pageContext.request.contextPath }/customer/goViewOrders" target="_blank">
                    	<i class="fa fa-list-ul"></i>
                        <p>订单一览</p>
                    </a>        
                </li>
                <li  class="active">
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
                                <h4 class="title">填写评论</h4>
                            </div>
                            <div class="content">
                             <form action="${pageContext.request.contextPath }/eva"  method="POST">
                                 <input type="hidden" name="orderID" value="${order.orderID}">
                            	 <table class="table table_honer">
				                  <tr>
				                      <th width="20%">酒店名称</th>
				                      <td class="text-left">${order.hotel.hotelName }</td>
				                  </tr>
				                  <tr>
				                      <th width="20%">订单号</th>
				                      <td class="text-left">${order.orderID}</td>
				                  </tr>
				                  <tr>
				                      <th width="20%">客房类型</th>
				                      <td class="text-left">${order.roomType.typeName}</td>
				                  </tr>
				                  <tr>
				                      <th width="20%">评分</th>
				                      <td class="text-left"> <div id="star1"></div></td>
				                  </tr>
				                  <tr>
				                      <th width="20%">优点</th>
				                      <td class="text-left"> <input type="text" name="advantage" class="form-control"></td>
				                  </tr>
				                  <tr>
				                      <th width="20%">优点</th>
				                      <td class="text-left"> <input type="text" name="disadvantage" class="form-control"></td>
				                  </tr>
				                  <tr>
				                      <th width="20%">综合评论</th>
				                      <td class="text-left"><textarea class="form-control" rows="3" name="comment"></textarea></td>
				                  </tr>
				                 
				                 </tbody>
				       			</table>
				       			  <input type="submit" class="btn btn-info btn-fill pull-right" style="margin-top: -20px;margin-right: 16px;" value="提交" />
				       			    <br />
								 </form>	    
                            </div>
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
	<script src="${pageContext.request.contextPath }/scripts/jquery.raty.js" type="text/javascript"></script>
 	<script type="text/javascript">
 	$('#star1').raty({
 		path:"${pageContext.request.contextPath }/img/",
 		 click: function (score) {  
             $("#form").attr("score",score); 
         } 
 	});
        
 	</script>
	
 
</html>
 