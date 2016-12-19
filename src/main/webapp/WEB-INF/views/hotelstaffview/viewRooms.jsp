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
	<title>Echo 查看订单</title>
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
                 
                   <div class="card">
                       <div class="header">
                           <h4 class="title">客房管理</h4>
                       </div>
                       <div class="content table-responsive  ">
						 <div class="row content" style="margin-top: -20px;">	
						 
						 <div class="panel panel-info">
						   <div class="panel-heading">客房类型
								<button class="btn btn-success btn-sm btn-fill pull-right" style="margin-top: -5px;" data-toggle="modal" data-target="#myModal">添加客房类型</button>
						  </div>
						  <div class="panel-body">
                                <c:if test="${roomTypes != null }"> 
                                    <table class="table table-hover ">
	                                    <thead>
	                                    <tr>
	                                    	<th>HotelID</th>
	                                    	<th>价格</th>
	                                    	<th>名称</th>
	                                    	<th>简介</th>
	                                    	<th></th>
	                                    </tr>
	                                    </thead>
	                                    <tbody>
                                    	<c:forEach var="roomType" items="${roomTypes }">
                                        <tr>
                                        	<td class="col-md-1">${roomType.hotelID }</td>
                                        	<td class="col-md-2">${roomType.price }</td>
                                        	<td class="col-md-3">${roomType.typeName }</td>
                                        	<td class="col-md-5">${roomType.typeDesc }</td>
                                        	<td class="td-actions text-right">
								                <a type="button" href="${pageContext.request.contextPath }/hotelstaff/roomType/${roomType.typeID}"  rel="tooltip" title="修改" class="btn btn-success btn-simple btn-xs">
								                    <i class="fa fa-edit"></i>
								                </a>
								                <a type="button" href="${pageContext.request.contextPath }/hotelstaff/roomType/${roomType.typeID}" onclick="return checkDelete();" rel="tooltip" title="删除" class="btn btn-danger btn-simple btn-xs delete" >
								                    <i class="fa fa-times"></i>
								                </a>
								            </td>
                                        </tr>
                                        </c:forEach>
                                        </tbody>
                               		</table>
                                    </c:if>
                                    
                                    <br/>
                                    <c:if test="${roomType != null }">
                                     <form action="${pageContext.request.contextPath }/hotelstaff/roomType/${roomType.typeID}" method="post">
                                     <input type="hidden" name="_method" value="PUT">
                                      <table class="table table-hover ">
                                      <thead>
                                      <tr>
	                                    	<th>HotelID</th>
	                                    	<th>价格</th>
	                                    	<th>名称</th>
	                                    	<th>简介</th>
	                                    	<th></th>
	                                    </tr>
	                                    </thead>
	                                    <tbody>
	                                     <tr>
	                                        <td class="col-md-1">${roomType.hotelID } </td>
                                        	<td class="col-md-2"><input value="${roomType.price }" onkeyup="value=value.replace(/[^\d]/g,'')" name ="price" class="form-control"/></td>
                                        	<td class="col-md-3"><input value="${roomType.typeName }" name ="typeName" class="form-control"/></td>
                                        	<td class="col-md-5"><input value="${roomType.typeDesc }" name ="typeDesc" class="form-control"/></td>
                                        	<td class="text-right">
                                        		<button type="submit" class="btn btn-info btn-sm btn-fill" onclick="return checkUpdate();">修改</button>
								            </td>
								         </tr>
                               			 </table>
                                    </form>
                                    </c:if>
                                    
                                <form action="" method="post" class="form_d">
									<input type="hidden" name="_method" value="DELETE">
								</form>
									</div>
								</div>
						 
						      <div class="panel panel-info">
									<div class="panel-heading">客房设置</div>
									<div class="panel-body">
									<form class="form-inline" role="form" method="post" action="${pageContext.request.contextPath }/hotelstaff/room/chooseRoom">
									  <input class="form-control" type="text" placeholder="请输入房间号" name="roomNum" onkeyup="value=value.replace(/[^\d]/g,'')">
									  <button type="submit" class="btn btn-warning btn-fill">客房设置</button>
									</form>
									
									<c:if test="${searchRoom != null}">
									<br>
									
									<form action="${pageContext.request.contextPath }/hotelstaff/room/" method="post">
									  <input type="hidden" name="roomNumber" value="${searchRoom.roomNumber}">
									  <input type="hidden" name="_method" value="PUT">
                                      <table class="table ">
                                      <thead> <tr> <th>ID</th> <th>房间号</th> <th>类型名称</th> <th>状态</th> <th></th> </tr> </thead>
	                                  <tbody>
	                                     <tr>
	                                        <td class="col-md-1">${searchRoom.id } </td>
                                        	<td class="col-md-2">${searchRoom.roomNumber }</td>
                                        	<td class="col-md-3">
	                                        	<select class="form-control" id="roomType" name="roomType">
												<option value="${searchRoom.typeID }">${searchRoom.typeName }</option>
												<c:forEach items="${roomTypes}" var="roomType">
					        					 	<option value="${roomType.typeID}">${roomType.typeName}</option>
					         					</c:forEach>
										       </select> 
									       </td>
                                        	<td class="col-md-2">
	                                        	<c:if test="${searchRoom.status == 0}"> 空闲</c:if>
	                                        	<c:if test="${searchRoom.status == 1}"> 使用中</c:if>
                                        	</td>
								           <td class="td-actions text-right col-md-2">
								                <button type="submit" onclick="return checkUpdate();" rel="tooltip" title="确认修改" class="btn btn-success btn-simple btn-xs">
								                    <i class="fa fa-check"></i>
								                </button>
								                <a type="button" href="${pageContext.request.contextPath }/hotelstaff/room/delete/${searchRoom.roomNumber}" onclick="return checkDelete();" rel="tooltip" title="删除" class="btn btn-danger btn-simple btn-xs" >
								                    <i class="fa fa-times"></i>
								                </a>
								            </td>
								           </tr>
								       </tbody>
                               		 </table>
                               		 </form>
									</c:if>
									</div>
								</div>
								
								<div class="panel panel-info">
									<div class="panel-heading">客房清单
										<button class="btn btn-success btn-sm btn-fill pull-right" style="margin-top: -5px;" data-toggle="modal" data-target="#myModal2">添加客房</button>
									</div>
									<div class="panel-body">
										<table data-toggle="table" data-url="${pageContext.request.contextPath }/hotelstaff/getRooms"  data-show-refresh="true" data-show-toggle="true" 
										data-show-columns="true" data-search="true" data-select-item-name="toolbar1" data-pagination="true" data-sort-name="name" data-sort-order="desc">
										    <thead>
										    <tr>
										        <th data-field="id" data-sortable="true" >ID</th>
										        <th data-field="roomNumber" data-sortable="true">房间号</th>
										        <th data-field="typeName"  data-sortable="true">客房类型</th>
										        <th data-field="price" data-sortable="true">客房价格</th>
										        <th data-field="status" data-sortable="true" >客房状态</th>
										    </tr>
										    </thead>
										</table>
									</div>
								</div>
								
						 </div>
	                 </div>
	              </div>  
               </div>
          </div>   
          
             
       </div>
    </div>
    
<!-- 弹出窗-类型 -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        <h4 class="modal-title" id="myModalLabel">添加客房</h4>
      </div>
      <div class="modal-body">
			  <form action="${pageContext.request.contextPath }/hotelstaff/roomType/" method="post">
                   <div class="row">
                       <div class="col-md-4">
                           <div class="form-group">
                               <label>价格</label>
                               <input type="text" class="form-control" name="price" onkeyup="value=value.replace(/[^\d]/g,'')" />
                           </div>        
                       </div>
                       <div class="col-md-8">
                           <div class="form-group">
                               <label>名称</label>
                               <input type="text" class="form-control"  name="typeName" >
                           </div>        
                       </div>
                   </div>
                   
                   <div class="row">
                       <div class="col-md-12">
                           <div class="form-group">
                               <label>简介</label>
                               <textarea rows="3" class="form-control" name="typeDesc"></textarea>
                           </div>        
                       </div>
                   </div>

                   <button type="submit" class="btn btn-info btn-fill pull-right" onclick="return checkAdd();">提交</button>
                   <div class="clearfix"></div>
               </form>
		 </div>
      
    </div>
  </div>
</div>
<!-- 弹出窗-类型END -->

<!-- 弹出窗-房间 -->
<div class="modal fade" id="myModal2" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        <h4 class="modal-title" id="myModalLabel">添加客房2</h4>
      </div>
      <div class="modal-body">
			  <form action="${pageContext.request.contextPath }/hotelstaff/room/" method="post">
                   <div class="row">
                       <div class="col-md-5">
                           <div class="form-group">
                               <label>房间号</label>
                               <input type="text" class="form-control"  name="roomNum" onkeyup="value=value.replace(/[^\d]/g,'')" />
                           </div>        
                       </div>
                       <div class="col-md-7">
                           <div class="form-group">
                               <label>客房类型</label>
                               <select class="form-control"id="roomType" name="roomType">
								<option value="">选择客房...</option>
								<c:forEach items="${roomTypes}" var="roomType">
	        					 	<option value="${roomType.typeID}">${roomType.typeName}</option>
	         					</c:forEach>
						       </select> 
                           </div>        
                       </div>
                   </div>
                   

                   <button type="submit" class="btn btn-info btn-fill pull-right" onclick="return checkAdd();">提交</button>
                   <div class="clearfix"></div>
               </form>
		 </div>
      
    </div>
  </div>
</div>
<!-- 弹出窗-房间END -->
    	
     <c:if test="${roomInfo != null }">
   		 ${roomInfo }
    </c:if>   
    <c:if test="${roomTypeInfo != null }">
   		 ${roomTypeInfo }
    </c:if>
</body>

	<script src="${pageContext.request.contextPath }/scripts/jquery.min.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath }/scripts/bootstrap.min.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath }/scripts/light-bootstrap-dashboard.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath }/scripts/bootstrap-table.js" type="text/javascript"></script>
	<script type="text/javascript">
	$(function(){
		$(".delete").click(function(){
			var href = $(this).attr("href");   //就是在提交删除时先获取href，交给form。
			//alert(href);
			$(".form_d").attr("action", href).submit();	//这里只有一个form		
			return false;
		});
	})
	</script>
	
	<script type="text/javascript">
	 
	 function checkDelete(){
			if(confirm("您确认要删除吗？")) {
			   return true;
			};
			   return false;
			}
	 
	 function checkUpdate(){
		if(confirm("您确认要进行修改吗？")) {
		   return true;
		};
		   return false;
		}

	 function checkAdd(){
			if(confirm("您确认要添加吗？")) {
			   return true;
			};
			   return false;
			}
	 </script>

</html>