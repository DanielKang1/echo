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
	<title>Echo 酒店管理</title>
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
                          <div class="row">
                                <div class="col-md-4">
                                    <h4 class="title">基础信息</h4>
                                </div>
                                <div class="col-md-8">
                                    <button  data-toggle="modal" data-target="#myModal" class="btn btn-info btn-fill pull-right"><i class="fa fa-cog"></i>&nbsp;&nbsp;修改信息</button> 
                                </div>
                           </div>
                      </div>
                      <div class="content">
                          <form>
                              <div class="row">
                                  <div class="col-md-4">
                                      <div class="form-group">
                                          <label>酒店名称</label> 
                                          <input type="text" class="form-control" disabled value="${requestScope.hotel.hotelName}">
                                      </div>        
                                  </div>
                                  <div class="col-md-2">
                                      <div class="form-group">
                                          <label>城市</label>
                                          <input type="text" class="form-control" disabled value="${requestScope.hotel.city}">
                                      </div>        
                                  </div>
                                  <div class="col-md-4">
                                      <div class="form-group">
                                          <label>商圈</label>
                                          <input type="text" class="form-control" disabled value="${requestScope.hotel.district}">
                                      </div>        
                                  </div>
                                  <div class="col-md-2">
                                      <div class="form-group">
                                          <label>星级</label>
                                          <input type="text" class="form-control" disabled value="${requestScope.hotel.starLevel}">
                                      </div>        
                                  </div>
                              </div>
                              
                              <div class="row">
                                  <div class="col-md-12">
                                      <div class="form-group">
                                          <label>详细地址</label>
                                          <input type="text" class="form-control" disabled value="${requestScope.hotel.address}">
                                      </div>        
                                  </div>
                              </div>
                              
                              <div class="row">
                                  <div class="col-md-12">
                                      <div class="form-group">
                                          <label>设施服务</label>
                                           <textarea class="form-control">${requestScope.hotel.facility}</textarea>
                                      </div>        
                                  </div>
                              </div>
                              
                              <div class="row">
                                  <div class="col-md-12">
                                      <div class="form-group">
                                          <label>酒店简介</label>
                                          <textarea class="form-control">${requestScope.hotel.briefIntro}</textarea>
                                      </div>        
                                  </div>
                              </div>
                              
                          </form>
                      </div>
                 </div>
                 

               </div>
          </div>      
       </div>
    </div>
	                 

	<!-- 信息修改弹出框 -->
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	  <div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	        <h5 class="modal-title" id="myModalLabel"><i class="fa fa-cog"></i>修改信息</h5>
	      </div>
	      <div class="modal-body ">
			<div class="card card-plain card-nav-tabs">
				<div class="header header-success">
					<div class="nav-tabs-navigation">
						<div class="nav-tabs-wrapper">
							<ul class="nav nav-tabs" data-tabs="tabs">
								<li class="active">
									<a href="#profile" data-toggle="tab">
										<i style="font-style: normal;">基础信息修改</i>
									</a>
								</li>
								<li>
									<a href="#messages" data-toggle="tab">
										<i style="font-style: normal;">星级修改</i>
									</a>
								</li>
							</ul>
						</div>
					</div>
				</div>
				<div class="content">
					<div class="tab-content text-center">
						<div class="tab-pane active" id="profile">
                                <form action="${pageContext.request.contextPath }/hotelstaff/updateInfo" method="POST">
                           <table>
                               <div class="row">
                               	 <div class="col-md-10 col-md-offset-1" >
	                                 <div class="row">
	                                      <div class="col-md-3">
	                                     	 <span style="line-height:35px">商圈：</span>
	                                      </div>
	                                      <div class="col-md-9">
	         								 <select class="form-control"id="district" name="district">
	         								 <c:forEach items="${districts}" var="district">
	        					 				<option value="${district.districtName}">${district.districtName}</option>
	         								</c:forEach>
	         								 </select>
	                                      </div>
	                                </div>    
	                                <div class="row">
	                                      <div class="col-md-3">
	                                     	 <span style="line-height:35px">详细地址：</span>
	                                      </div>
	                                      <div class="col-md-9">
	                                      	 <input type="text" class="form-control"  name="address" value="${requestScope.hotel.address}" placeholder="详细地址">
	                                      </div>
	                                </div> 
	                                <div class="row">
	                                      <div class="col-md-3">
	                                     	 <span style="line-height:35px">设施服务：</span>
	                                      </div>
	                                      <div class="col-md-9">
	                                      	 <textarea  class="form-control" name="facility" placeholder="输入设施服务">${requestScope.hotel.facility}</textarea>
	                                      </div>
	                                </div>  
	                                <div class="row">
	                                      <div class="col-md-3">
	                                     	 <span style="line-height:35px">酒店介绍：</span>
	                                      </div>
	                                      <div class="col-md-9">
	                                      	 <textarea  class="form-control" name="briefIntro" placeholder="输入简介">${requestScope.hotel.briefIntro}</textarea>
	                                      </div>
	                                </div>  
	                                <div class="row">
	                                      <div class="col-md-12">
	                                     	  <button type="submit" class="btn btn-success btn-fill pull-right">提交</button> 
	                                      </div>
	                                </div>    
                                  </div>
                               </div>
                           </table>
                       </form> 
						</div>
						<div class="tab-pane" id="messages">
						<form action="${pageContext.request.contextPath }/hotelstaff/modifyStarLevel" method="POST">
                           <table>
                               <div class="row">
                               	 <div class="col-md-8 col-md-offset-2" >
	                                 <div class="row">
	                                      <div class="col-md-3">
	                                     	 <span style="line-height:35px">星级：</span>
	                                      </div>
	                                      <div class="col-md-9">
	                                      	 <select class="form-control"id="starLevel" name="starLevel">
	        					 				<option value="1">1</option>
	        					 				<option value="2">2</option>
	        					 				<option value="3">3</option>
	        					 				<option value="4">4</option>
	        					 				<option value="5">5</option>
	         								 </select>
	                                      </div>
	                                </div>    
	                                <div class="row">
	                                      <div class="col-md-12">
	                                     	  <button type="submit" class="btn btn-success btn-fill pull-right">提交</button> 
	                                      </div>
	                                </div>   
	                                 <hr><span style="color: #999999;font-size: 10px;">星级的提交需要审核，请您联系网站管理人员，提交相关信息。</span> 
                                  </div>
                               </div>
                           </table>
                       </form>
						</div>
					 
					</div>
				</div>
			</div>
        </div>
	    
	    </div>
	  </div>
	</div>
	<!-- 信息修改弹出框END -->
	
	<!-- hotelInfo -->
	<c:if test="${hotelInfo != null }">
			${hotelInfo}
	</c:if>

</body>

	<script src="${pageContext.request.contextPath }/scripts/jquery.min.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath }/scripts/bootstrap.min.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath }/scripts/light-bootstrap-dashboard.js" type="text/javascript"></script>
 

</html>