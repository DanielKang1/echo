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
	<title>Echo Admin</title>
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
                    <a href="${pageContext.request.contextPath }/admin/" target="_blank">
                        <i class="fa fa-users"></i>
                        <p>用户管理</p>
                    </a>            
                </li>
                <li>
                    <a href="${pageContext.request.contextPath }/admin/" target="_blank">
                        <i class="fa fa-building"></i>
                        <p>酒店管理</p>
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
                    <ul class="nav navbar-nav navbar-right">
                        <li class="dropdown">
                              <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                              	<span style="font-size: 14px;color: #666666;"> ${sessionScope.authWebAdmin.adminName}</span> <b class="caret"></b> </a>
                              <ul class="dropdown-menu">
                                <li><a href="${pageContext.request.contextPath }/admin/signout"><i class="fa fa-sign-out"></i>退出</a>
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
                      <div class="header">  <h4 class="title">用户管理</h4>  </div>
                      <div class="content">
                      
                      <ul id="myTab" class="nav nav-tabs">
						<li class="active"> <a href="#customer" data-toggle="tab"> 客户管理 </a> </li>
						<li><a href="#hotelstaff" data-toggle="tab">酒店工作人员管理</a></li>
						<li><a href="#marketer" data-toggle="tab">营销人员管理</a></li>
					  </ul>
					  <div id="myTabContent" class="tab-content">
							<div class="tab-pane fade in active" id="customer">
							<br>	
							 <form class="form-inline" action="${pageContext.request.contextPath }/admin/search_cus" method="POST">
								  <div class="form-group">
								    <input type="text" class="form-control" id="name"  name="name" placeholder="请输入客户用户名">
								  </div>
								  <button type="submit" class="btn btn-info btn-fill">搜索</button>
							 </form>
							</div>
							
							<div class="tab-pane fade" id="hotelstaff">
							 <br>	
							 <form class="form-inline" action="${pageContext.request.contextPath }/admin/search_staff" method="POST">
								  <div class="form-group">
								    <input type="text" class="form-control" id="name"  name="name" placeholder="请输入酒店工作人员用户名">
								  </div>
								  <button type="submit" class="btn btn-info btn-fill">搜索</button>
							 </form>
							</div>
							
							<div class="tab-pane fade" id="marketer">
							 <br>	
							 <form class="form-inline" action="${pageContext.request.contextPath }/admin/search_marketer" method="POST">
								  <div class="form-group">
								    <input type="text" class="form-control"  id="name"  name="name" placeholder="请输入营销人员用户名">
								  </div>
								  <button type="submit" class="btn btn-info btn-fill">搜索</button>
								  <a class="btn btn-warning btn-fill" data-toggle="modal" data-target="#myModal">添加营销人员</a>
							 </form>
							</div>
					  </div>
                      
                              <!-- 搜索结果 Customer-->
							  <c:if test="${sea_customer == null && flag1 != null}">
							   <br><hr>
							   <span style="font-size: 10px;color: #999999">无该搜索结果</span>
							  </c:if>
							  <c:if test="${sea_customer != null }">
                                <br><hr>
                                 <form method="post" action="${pageContext.request.contextPath }/admin/update_cus">
                                    <div class="row">
                                        <div class="col-md-2">
                                            <div class="form-group">
                                                <label>ID</label>
                                                <input type="text" class="form-control" disabled name="cid" value="${sea_customer.customer_id }">
                                            </div>        
                                        </div>
                                        <div class="col-md-3">
                                            <div class="form-group">
                                                <label>用户名</label>
                                                <input type="text" class="form-control" disabled value="${sea_customer.nickname }">
                                            </div>        
                                        </div>
                                        <input type="hidden"   name="cid" value="${sea_customer.customer_id }">
                                        <div class="col-md-3">
                                            <div class="form-group">
                                                <label for="exampleInputEmail1">邮箱</label>
                                                <input type="email" class="form-control"  name="email" value="${sea_customer.email }">
                                            </div>        
                                        </div>
                                        <div class="col-md-4">
                                            <div class="form-group">
                                                <label>手机号</label>
                                                <input type="text" class="form-control"  name="phone" value="${sea_customer.phone }">
                                            </div>        
                                        </div>
                                    </div>
                                    <button type="submit" class="btn btn-success btn-fill pull-right">确认更新</button>
                                    <div class="clearfix"></div>
                                </form>
                                </c:if>
                                 <!-- 搜索结果 Customer  END-->
                                
                                 <!-- 搜索结果HotelStaff-->
                                 <c:if test="${sea_staff == null && flag2 != null}">
								   <br><hr>
								   <span style="font-size: 10px;color: #999999">无该搜索结果</span>
								  </c:if>
								   <c:if test="${sea_staff != null }">
                                  <br><hr>
                                  <form method="post" action="${pageContext.request.contextPath }/admin/update_staff">
                                    <div class="row">
                                        <div class="col-md-3">
                                            <div class="form-group">
                                                <label>ID</label>
                                                <input type="text" class="form-control" disabled   value="${sea_staff.staffID }">
                                            </div>        
                                        </div>
                                         <input type="hidden"   name="sid" value="${sea_staff.staffID }">
                                        <div class="col-md-5">
                                            <div class="form-group">
                                                <label>员工姓名</label>
                                                <input type="text" class="form-control" name="name"  value="${sea_staff.staffName }">
                                            </div>        
                                        </div>
                                        <div class="col-md-4">
                                            <div class="form-group">
                                                <label>手机号</label>
                                                <input type="text" class="form-control" name="phone" value="${sea_staff.phone }">
                                            </div>        
                                        </div>
                                    </div>
                                    
                                    <div class="row">
                                     <div class="col-md-2">
                                            <div class="form-group">
                                                <label for="exampleInputEmail1">酒店ID</label>
                                                <input type="email" class="form-control" disabled  value="${sea_staff_hotel.hotelID }" >
                                            </div>        
                                        </div>
                                        <div class="col-md-10">
                                            <div class="form-group">
                                                <label for="exampleInputEmail1">酒店名称</label>
                                                <input type="email" class="form-control" disabled value="${sea_staff_hotel.hotelName }">
                                            </div>        
                                        </div>
                                    </div>
                                    <button type="submit" class="btn btn-success btn-fill pull-right">确认更新</button>
                                    <div class="clearfix"></div>
                                </form>
                                </c:if>
                                <!-- 搜索结果HotelStaff   END-->
                                
                                 <!-- 搜索结果 WebMarketer -->
								  <c:if test="${sea_marketer == null && flag3 != null}">
								   <br><hr>
								   <span style="font-size: 10px;color: #999999">无该搜索结果</span>
								  </c:if>
								  <c:if test="${sea_marketer != null }">
	                                <br><hr>
	                                 <form method="post" action="${pageContext.request.contextPath }/admin/update_mar">
	                                     <div class="row">
                                        <div class="col-md-2">
                                            <div class="form-group">
                                                <label>ID</label>
                                                <input type="text" class="form-control" disabled   value="${sea_marketer.marketerID }">
                                            </div>        
                                        </div>
                                        <input type="hidden"   name="mid" value="${sea_marketer.marketerID }">
                                        <div class="col-md-10">
                                            <div class="form-group">
                                                <label>营销人员名称</label>
                                                <input type="text" class="form-control" name="name" value="${sea_marketer.marketerName }">
                                            </div>        
                                        </div>
                                    </div>
                                    <button type="submit" class="btn btn-success btn-fill pull-right">确认更新</button>
                                    <div class="clearfix"></div>
	                                </form>
	                               </c:if>
                                  <!-- 搜索结果 WebMarketer END-->
                                
                         </div>
                      </div> 

                      
                      <div class="card">
	                      <div class="header">  <h4 class="title">酒店管理</h4>  </div>
	                      <div class="content">
	                      
		                   <ul id="myTab2" class="nav nav-tabs">
							<li class="active"> <a href="#search_hotel" data-toggle="tab"> 酒店查询</a> </li>
							<li><a href="#add_hotel" data-toggle="tab">添加酒店与管理员</a></li>
						  </ul>
						  <div id="myTab2Content" class="tab-content">
								<div class="tab-pane fade in active" id="search_hotel">
								<br>	
									 <form class="form-inline" action="${pageContext.request.contextPath }/admin/search_hotel" method="POST">
										  <div class="form-group">
										    <input type="text" class="form-control"  id="hotelName"  name="hotelName" placeholder="请输入酒店名称">
										  </div>
										  <button type="submit" class="btn btn-info btn-fill">搜索</button>
								 	</form>
								 	<c:if test="${hotel == null && flag4 != null}">
								 	 <hr/>
								     <span style="font-size: 10px;color: #999999">无该搜索结果</span>
								 	</c:if>
								 	<c:if test="${hotel != null}">
								<hr/>
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
	                                           <textarea class="form-control" disabled>${requestScope.hotel.facility}</textarea>
	                                      </div>        
	                                  </div>
	                              </div>
	                              
	                              <div class="row">
	                                  <div class="col-md-12">
	                                      <div class="form-group">
	                                          <label>酒店简介</label>
	                                          <textarea class="form-control" disabled>${requestScope.hotel.briefIntro}</textarea>
	                                      </div>        
	                                  </div>
	                              </div>
	                              
	                               <div class="row">
	                                  <div class="col-md-2">
	                                      <div class="form-group">
	                                          <label>员工ID</label>
	                                          <input type="text" class="form-control" disabled value="${requestScope.staff.staffID}">
	                                      </div>        
	                                  </div>
	                                  <div class="col-md-5">
	                                      <div class="form-group">
	                                          <label>员工姓名</label>
	                                          <input type="text" class="form-control" disabled value="${requestScope.staff.staffName}">
	                                      </div>        
	                                  </div>
	                                  <div class="col-md-5">
	                                      <div class="form-group">
	                                          <label>员工手机号</label>
	                                          <input type="text" class="form-control" disabled value="${requestScope.staff.phone}">
	                                      </div>        
	                                  </div>
								</div>
								</c:if>
 	                           </div>  
								
								
								
								<div class="tab-pane fade" id="add_hotel">
								 <br>	
								  <form method="post" action="${pageContext.request.contextPath }/admin/add_hotel">
		                              <div class="row">
		                                  <div class="col-md-4">
		                                      <div class="form-group">
		                                          <label>酒店名称</label> 
		                                          <input type="text" class="form-control"  name="hotelName" value="">
		                                      </div>        
		                                  </div>
		                                  <div class="col-md-2">
		                                      <div class="form-group">
		                                          <label>城市</label>
		                                         <select class="form-control"id="city" name="city">
													<option value="">选择城市...</option>
													<c:forEach items="${cities}" var="city">
						        					 	<option value="${city}">${city}</option>
						         					</c:forEach>
											    </select> 
		                                      </div>        
		                                  </div>
		                                  <div class="col-md-4">
		                                      <div class="form-group">
		                                          <label>商圈</label>
		                                          <select class="form-control" id="district" name="district">
										          </select>
		                                      </div>        
		                                  </div>
		                                  <div class="col-md-2">
		                                      <div class="form-group">
		                                          <label>星级</label>
		                                          <select class="form-control" id="starLevel" name="starLevel">
		                                          	<option value="5">5</option>
		                                          	<option value="4">4</option>
		                                          	<option value="3">3</option>
		                                          	<option value="2">2</option>
										          </select>
		                                      </div>        
		                                  </div>
		                              </div>
		                              <div class="row">
		                                  <div class="col-md-4">
		                                      <div class="form-group">
		                                          <label>员工姓名</label> 
		                                          <input type="text" class="form-control"  name="staffName" value="">
		                                      </div>        
		                                  </div>
		                                  <div class="col-md-4">
		                                      <div class="form-group">
		                                          <label>员工手机号</label> 
		                                          <input type="text" class="form-control"  name="staffPhone" value="">
		                                      </div>        
		                                  </div>
		                                  <div class="col-md-4">
		                                      <div class="form-group">
		                                          <label>员工密码</label> 
		                                          <input type="text" class="form-control"  name="staffPwd" value="">
		                                      </div>        
		                                  </div>
		                              </div>
		                              <button type="submit" class="btn btn-info btn-fill pull-right">确认添加</button>
		                  			 <div class="clearfix"></div>
		                          </form>
								</div>
						  </div>
	                      
	                       
	                      
                          </div>
                  	</div>
                 </div>
            </div>
            
                  
            
       </div>
    </div>
	                 
<!-- 弹出窗-添加营销人员 -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        <h4 class="modal-title" id="myModalLabel">添加营销人员</h4>
      </div>
      <div class="modal-body">
			  <form action="${pageContext.request.contextPath }/admin/add_mar" method="post">
                   <div class="row">
                       <div class="col-md-6">
                           <div class="form-group">
                               <label>营销人员姓名</label>
                               <input type="text" class="form-control"  name="name"  />
                           </div>        
                       </div>
                       <div class="col-md-6">
                           <div class="form-group">
                               <label>营销人员密码</label>
                               <input type="password" class="form-control"  name="pwd"  />
                           </div>        
                       </div>
                   </div>
                   <button type="submit" class="btn btn-info btn-fill pull-right">提交</button>
                   <div class="clearfix"></div>
               </form>
		 </div>
      
    </div>
  </div>
</div>
<!-- 弹出窗-房间END -->

<c:if test="${Info != null }">
	<script>alert('${Info}');</script>
</c:if>

	
</body>

	<script src="${pageContext.request.contextPath }/scripts/jquery.min.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath }/scripts/bootstrap.min.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath }/scripts/light-bootstrap-dashboard.js" type="text/javascript"></script>
	<script type="text/javascript">
	 $("#city").change(function(){
	        $("#district option:not(:first)").remove();
	         var city=$(this).val();
	         if(city!=""){
		         var url="${pageContext.request.contextPath }/city";
		         var args={"city":city,"time":new Date()};
		         $.post(url, args, function(data){
						for(var i = 0; i < data.length; i++){
							var id = data[i].id;
							var districtName = data[i].districtName;
							$("#district").append("<option value='"+districtName+"'>"+districtName+"</option>"); 
						}
					});
	            }
	       });
	</script>
 

</html>