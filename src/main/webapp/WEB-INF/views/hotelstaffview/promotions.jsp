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
	<title>Echo 酒店促销</title>
	<link rel="icon" type="image/png" href="${pageContext.request.contextPath }/img/hotelicon.png">
     <link href="${pageContext.request.contextPath }/css/bootstrap.min.css" rel="stylesheet" />
    <link href="${pageContext.request.contextPath }/css/bootstrap-datetimepicker.min.css" rel="stylesheet"/>
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
                           <h4 class="title">酒店营销策略</h4>
                       </div>
                       <div class="content table-responsive  ">
						 <div class="row content" style="margin-top: -20px;">	
						 
						 <div class="panel panel-info">
						   <div class="panel-heading">生日特惠折扣 </div>
						   <div class="panel-body row">
                                <form class="form-inline col-md-1" id="form1" action="${pageContext.request.contextPath }/hotelstaff/bir_switch_update" method="POST">
					                  <label><input name="checkbox1" id="checkbox1" onclick="checkbox_1()"  type="checkbox" 
					                   <c:if test="${hotelPromotionItem.birthdaySwitch == true}">checked</c:if> 
					                  />启用</label>  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</form>
					             <form class="form-inline  col-md-11" id="form1" action="${pageContext.request.contextPath }/hotelstaff/bir_update" method="POST">
									  <div class="form-group">
									    <input type="text" class="form-control" id="input1" name="discount" placeholder="折扣率（请输入小于零的小数）"  
									    <c:if test="${hotelPromotionItem.birthdayDiscount != null }">
									   		 value='${hotelPromotionItem.birthdayDiscount}'
									    </c:if>
					                     <c:if test="${hotelPromotionItem.birthdaySwitch == false}">disabled</c:if> 
									    >
									  </div>
									  <button type="submit" id="sub1" class="btn btn-info btn-fill">确认</button>
							  </form>
						   </div>
						 </div>
						 
						 <div class="panel panel-info">
						   <div class="panel-heading">预订特定数量以上折扣 </div>
						   <div class="panel-body row">
                               <form class="form-inline col-md-1" id="form2" action="${pageContext.request.contextPath }/hotelstaff/num_switch_update" method="POST">
					                  <label><input name="checkbox2" id="checkbox2" onclick="checkbox_2()"  type="checkbox" 
					                   <c:if test="${hotelPromotionItem.bookingDiscountSwitch == true}">checked</c:if> 
					                  />启用</label>  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </form>
					           <form class="form-inline col-md-11" id="form2" action="${pageContext.request.contextPath }/hotelstaff/num_update" method="POST">
									  <div class="form-group">
									    <input type="text" class="form-control" name="number" placeholder="预订数量"
									     <c:if test="${hotelPromotionItem.bookingDiscountSwitch == false}">disabled</c:if> 
									     <c:if test="${hotelPromotionItem.bookingMeasure != null }">
									   		 value='${hotelPromotionItem.bookingMeasure}'
									    </c:if>
									    />
									    <input type="text" class="form-control" id="input2" name="discount" placeholder="折扣率（请输入小于零的小数）"  
									    <c:if test="${hotelPromotionItem.bookingDiscountSwitch == false}">disabled</c:if> 
									    <c:if test="${hotelPromotionItem.bookingDiscount != null }">
									   		 value='${hotelPromotionItem.bookingDiscount}'
									    </c:if>
									    >
									  </div>
									  <button type="submit"  class="btn btn-info btn-fill">确认</button>
							  </form>
						   </div>
						 </div>
						 
						 <div class="panel panel-info">
						   <div class="panel-heading">合作企业客户折扣 </div>
						   <div class="panel-body row">
                               <form class="form-inline  col-md-1" id="form3" action="${pageContext.request.contextPath }/hotelstaff/coo_switch_update" method="POST">
					                  <label><input name="checkbox3" id="checkbox3" onclick="checkbox_3()"  type="checkbox" 
					                   <c:if test="${hotelPromotionItem.cooperativeEnterpriseSwitch == true}">checked</c:if> 
					                  />启用</label>  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</form>
					           <form class="form-inline col-md-11" id="form2" action="${pageContext.request.contextPath }/hotelstaff/coo_update" method="POST">
									  <div class="form-group">
									    <input type="text" class="form-control" id="input3" name="discount" placeholder="折扣率（请输入小于零的小数）"   
									     <c:if test="${hotelPromotionItem.cooperativeEnterpriseSwitch == false}">disabled</c:if> 
									     <c:if test="${hotelPromotionItem.cooperativeEnterpriseDiscount != null }">
									   		 value='${hotelPromotionItem.cooperativeEnterpriseDiscount}'
									    </c:if>
									    >
									  </div>
									  <button type="submit"  class="btn btn-info btn-fill">确认</button>
							  </form>
						   </div>
						 </div>
						 
						 <div class="panel panel-info">
						   <div class="panel-heading">特定日期住宿折扣 
						   <button class="btn btn-success btn-sm btn-fill pull-right" style="margin-top: -5px;" data-toggle="modal" data-target="#myModal">添加促销时间段</button>
						   </div>
						   <div class="panel-body ">
                                 <table class="table">
								    <thead>
								        <tr>
								            <th class="text-center">#</th>
								            <th class="text-center">初始日期</th>
								            <th class="text-center">结束日期</th>
								            <th class="text-center">拆扣率</th>
								        </tr>
								    </thead>
								    <tbody>
								    <c:forEach var="prodate" items="${prodates}">
								        <tr>
								            <td class="text-center">${prodate.id }</td>
								            <td class="text-center"><fmt:formatDate  pattern="yyyy-MM-dd" value="${prodate.startDate }" /></td>
								            <td class="text-center"><fmt:formatDate  pattern="yyyy-MM-dd" value="${prodate.endDate }" /></td>
								            <td class="text-center">${prodate.discount }</td>
								            <td class="td-actions text-right">
								                <a type="button" href="${pageContext.request.contextPath }/hotelstaff/promotiondate/delete/${prodate.id }"  rel="tooltip" title="删除" class="btn btn-danger btn-simple btn-xs delete" >
								                    <i class="fa fa-times"></i>
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
        <h4 class="modal-title" id="myModalLabel">添加促销时间段</h4>
      </div>
      <div class="modal-body">
			  <form action="${pageContext.request.contextPath }/hotelstaff/promotiondate" method="post">
                   <div class="row">
                       
                       <div class="col-md-6">
                           <div class="form-group">
                               <label>起始日期</label><br/>
                               <input class="laydate-icon" onclick="laydate()" name="start" placeholder="开始日期">	 
                           </div>        
                       </div>
                       <div class="col-md-6">
                           <div class="form-group">
                               <label>结束日期</label><br/>
                              <input class="laydate-icon" onclick="laydate()" name="end" placeholder="结束日期">	 
                           </div>        
                       </div>
                       
                       <div class="col-md-6">
                           <div class="form-group">
                            <label>折扣率</label>
                               <input type="text" class="form-control" name="discount"  placeholder="折扣率" />
                           </div>        
                       </div>
                       
                   </div>
					<hr>
                   <button type="submit" class="btn btn-info btn-fill pull-right" onclick="return checkAdd();">提交</button>
                   <div class="clearfix"></div>
               </form>
		 </div>
      
    </div>
  </div>
</div>
<!-- 弹出窗-类型END -->
    
     <c:if test="${Info != null }">
   		 ${Info }
    </c:if>

</body>

	<script src="${pageContext.request.contextPath }/scripts/jquery.min.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath }/scripts/bootstrap.min.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath }/scripts/bootstrap-datetimepicker.min.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath }/scripts/light-bootstrap-dashboard.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath }/scripts/bootstrap-table.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath }/scripts/laydate.js" type="text/javascript"></script>
	
 	<script type="text/javascript">
 	
	function checkbox_1(){
		if($('#checkbox1').is(':checked')) {
			$("#form1").submit();
		}
		else{
			$("#form1").submit();
		}
	}
	
	function checkbox_2(){
		if($('#checkbox2').is(':checked')) {
			$("#form2").submit();
		}
		else{
			$("#form2").submit();
		}
	}
	
	function checkbox_3(){
		if($('#checkbox3').is(':checked')) {
			$("#form3").submit();
		}
		else{
			$("#form3").submit();
		}
	}
	
	</script>
</html>