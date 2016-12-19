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
	<title>Echo 网站营销管理</title>
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
                    <a href="" target="_blank">
                        <i class="fa fa-align-left"></i>
                        <p>信用充值</p>
                    </a>            
                </li>
                <li>
                    <a href="" target="_blank">
                        <i class="fa fa-clipboard"></i>
                        <p>异常订单</p>
                    </a>        
                </li>
                <li>
                    <a href="" target="_blank">
                        <i class="fa fa-calendar"></i>
                        <p>网站营销</p>
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
                              	<span style="font-size: 14px;color: #666666;"> ${sessionScope.authWebMarketer.marketerName}</span> <b class="caret"></b> </a>
                              <ul class="dropdown-menu">
                                <li><a href="#"><i class="fa fa-home"></i>我的主页</a></li>
                                <li><a href="#"><i class="fa fa-cog"></i>设置</a></li>
                                <li><a href="#"><i class="fa fa-building"></i>酒店查询</a></li>
                                <li><a href="#"><i class="fa fa-th-list"></i>查看订单</a></li>
                                <li class="divider"></li>
                                <li><a href="${pageContext.request.contextPath }/marketer/signout"><i class="fa fa-sign-out"></i>退出</a>
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
                                    <h4 class="title">充值服务</h4>
                                </div>
                                <div class="col-md-8">
                                </div>
                           </div>
                      </div>
                      <div class="content">
						 <form class="form-inline" action="${pageContext.request.contextPath }/marketer/getcus" method="POST">
							  <div class="form-group">
							    <input type="text" class="form-control" id="name" name="name" placeholder="请输入用户名" >
							  </div>
							  <button type="submit" class="btn btn-info btn-fill">搜索</button>
						 </form>
						 <c:if test="${customer == null && flag != null}">
					 	 	<hr/>
					     	<span style="font-size: 10px;color: #999999">无该搜索结果</span>
					 	 </c:if>
					 	 <c:if test="${customer != null }">
					 	 <hr/>
					 	 <form action="${pageContext.request.contextPath }/marketer/recharge" method="post">
					 	  <div class="row">
                                  <div class="col-md-2">
                                      <div class="form-group">
                                          <label>ID</label> 
                                          <input type="text" class="form-control" disabled value="${customer.customer_id }">
                                      </div>        
                                  </div>
                                  <div class="col-md-3">
                                      <div class="form-group">
                                          <label>用户名</label>
                                          <input type="text" class="form-control" disabled value="${customer.nickname }">
                                      </div>        
                                  </div>
                                  <div class="col-md-3">
                                      <div class="form-group">
                                          <label>手机号</label>
                                          <input type="text" class="form-control" disabled value="${customer.phone }">
                                      </div>        
                                  </div>
                                  <div class="col-md-2">
                                      <div class="form-group">
                                          <label>信用值</label>
                                          <input type="text" class="form-control" disabled value="${customer.credit }">
                                      </div>        
                                  </div>
                                  <div class="col-md-2">
                                      <div class="form-group">
                                          <label>充值金额</label>
                                          <input type="text" class="form-control" name="rechargeNum">
                                      </div>        
                                  </div>
                                  <input type="hidden" name="cid" value="${customer.customer_id }">
                              </div>
                              <button type="submit" class="btn btn-info btn-fill pull-right">确认充值</button>
		                  	  <div class="clearfix"></div>
					 	   </form>
					 	 </c:if>
					 </div>
				  </div> 
				  
				  
				   <div class="card">
                      <div class="header">
                          <div class="row">
                                <div class="col-md-4">
                                    <h4 class="title">网站营销</h4>
                                </div>
                                <div class="col-md-8">
                                </div>
                           </div>
                      </div>
                      <div class="content">
                      
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
								                <a type="button" href="${pageContext.request.contextPath }/marketer/promotiondate/delete/${prodate.id }"  rel="tooltip" title="删除" class="btn btn-danger btn-simple btn-xs delete" >
								                    <i class="fa fa-times"></i>
								                </a>
								                
								            </td>
								        </tr>
								  </c:forEach>
								    </tbody>
								</table>
						   </div>
						 </div>
						 
						 <div class="panel panel-info">
						   <div class="panel-heading">会员等级 
						    <button class="btn btn-success btn-sm btn-fill pull-right" style="margin-top: -5px;" data-toggle="modal" data-target="#myModal2">添加会员等级</button>
						   </div>
						   <div class="panel-body ">
							   <table class="table">
							  		 <thead>
					                    <tr>
					                   		<th class="text-left ">#</th>
					                        <th class="text-left col-md-3">级别名称</th>
					                        <th class="text-left col-md-2">所需信用值</th>
					                        <th class="text-left col-md-2">折扣率</th>
					                        <th class="td-actions text-right">更新/删除</th>
					                    </tr>
					                  <thead>  
					                  <tbody>
					                   <c:if test="${memberDiscounts != null }">
					                  <c:forEach items="${memberDiscounts }" var="mb">
					                	 <tr>
					                   		<td class="text-left col-md-2">${mb.levelID }</td>
					                        <td class="text-left col-md-3">${mb.levelName }</td>
					                        <td class="text-left col-md-2">${mb.creditLimit }</td>
					                        <td class="text-left col-md-2">${mb.discount }</td>
					                        <td class="td-actions text-right">
					                         <a type="button" href="${pageContext.request.contextPath }/marketer/memberdiscount/goupdate/${mb.levelID }"  rel="tooltip" title="修改" class="btn btn-success btn-simple btn-xs">
							                    <i class="fa fa-edit"></i>
							                </a>
							                <a type="button" href="${pageContext.request.contextPath }/marketer/memberdiscount/delete/${mb.levelID }"  rel="tooltip" title="删除" class="btn btn-danger btn-simple btn-xs delete" >
							                    <i class="fa fa-times"></i>
							                </a>
					                        </td>
					                    </tr>
					                  </c:forEach>
					                  </c:if>
					                  </tbody>  
					           </table>
					           
					           <c:if test="${updateMD != null }">
					           <form action="${pageContext.request.contextPath }/marketer/memberdiscount/update/" method="post">
					            <table class="table">
							  		 <thead>
					                    <tr>
					                   		<th class="text-left ">#</th>
					                        <th class="text-left col-md-3">级别名称</th>
					                        <th class="text-left col-md-2">所需信用值</th>
					                        <th class="text-left col-md-2">折扣率</th>
					                        <th class="td-actions text-right">更新/删除</th>
					                    </tr>
					                  <thead>  
					                  <tbody>
					                   
					                	 <tr>
					                   		<td class="text-left col-md-2">${updateMD.levelID }</td>
					                        <td class="text-left col-md-3"><input name="levelName" class="form-control" value="${updateMD.levelName }"></td>
					                        <td class="text-left col-md-2"><input name="creditLimit" class="form-control" value="${updateMD.creditLimit }"></td>
					                        <td class="text-left col-md-2"><input name="discount" class="form-control" value="${updateMD.discount }"></td>
					                        <td class=" text-right">
					                         <button type="submit" class="btn btn-info btn-sm btn-fill">修改</button>
					                        </td>
					                    </tr>
				                     </tbody>  
				          			 </table>
				          			 <input name="levelID" type="hidden" value="${updateMD.levelID }">
			          			 </form>
			                  </c:if>
					                    
						   </div>
						   </div>
						   
						   <div class="panel panel-info">
						   <div class="panel-heading">VIP会员特定商圈折扣
						   <button class="btn btn-success btn-sm btn-fill pull-right" style="margin-top: -5px;" data-toggle="modal" data-target="#myModal3">添加VIP会员特定商圈折扣</button>
						   </div>
						   <div class="panel-body ">
						   		<c:if test="${districtDiscounts != null }">
						   		
					            <table class="table">
							  		 <thead>
					                    <tr>
					                        <th class="text-left col-md-3">城市</th>
					                        <th class="text-left col-md-3">商圈</th>
					                        <th class="text-left col-md-1">VIP1折扣</th>
					                        <th class="text-left col-md-1">VIP2折扣</th>
					                        <th class="text-left col-md-1">VIP3折扣</th>
					                        <th class="text-left col-md-1">VIP4折扣</th>
					                        <th class="text-left col-md-1">VIP5折扣</th>
					                    </tr>
					                  <thead>  
					                  <c:forEach items="${districtDiscounts }" var="districtDiscount">
					                  <tbody>
					                	 <tr>
					                        <td class="text-left col-md-3">${districtDiscount.cityName } </td>
					                        <td class="text-left col-md-3">${districtDiscount.districtName } </td>
					                        <td class="text-left col-md-1">${districtDiscount.v1Discount }  </td>
					                        <td class="text-left col-md-1">${districtDiscount.v2Discount }  </td>
					                        <td class="text-left col-md-1">${districtDiscount.v3Discount }  </td>
					                        <td class="text-left col-md-1">${districtDiscount.v4Discount }  </td>
					                        <td class="text-left col-md-1">${districtDiscount.v5Discount }  </td>
					                    </tr>
				                     </tbody>  
				                     </c:forEach>
				          			 </table>
			                  </c:if>
						   </div>
						 </div>
						  
					 </div>
				  </div> 
				  
				  
				  <div class="card">
                      <div class="header">
                          <div class="row">
                                <div class="col-md-4">
                                    <h4 class="title">异常订单</h4>
                                </div>
                                <div class="col-md-8">
                                </div>
                           </div>
                      </div>
                      <div class="content">
						   <table class="table">
							  		 <thead>
					                    <tr>
					                        <th class="text-left ">ID</th>
					                        <th class="text-left ">酒店名称</th>
					                        <th class="text-left ">预留姓名</th>
					                        <th class="text-left ">预留号码</th>
					                        <th class="text-left ">下单日期</th>
					                        <th class="text-left ">订单总额</th>
					                    </tr>
					                  <thead>  
					                  <c:forEach items="${orders }" var="order">
					                  <tbody>
					                	 <tr>
					                        <td class="text-left">${order.orderID  } </td>
					                        <td class="text-left">${order.hotel.hotelName  } </td>
					                        <td class="text-left">${order.reservedName  } </td>
					                        <td class="text-left">${order.reservedPhone  } </td>
					                        <td class="text-left"><fmt:formatDate  pattern="yyyy-MM-dd HH:mm:ss" value="${order.creationDate }" /> </td>
					                        <td class="text-left">${order.total  } </td>
					                        <td class="td-actions text-right">
								                <a href="${pageContext.request.contextPath }/marketer/abnormalrecover1/${order.orderID}" rel="tooltip" title="恢复一半信用值" class="btn btn-warning btn-simple btn-xs">
								                    <i class="fa fa-history"></i>
								                </a>
								                <a href="${pageContext.request.contextPath }/marketer/abnormalrecover2/${order.orderID}" rel="tooltip"  title="恢复全部信用值" class="btn btn-success btn-simple btn-xs">
								                    <i class="fa fa-history"></i>
								                </a>
								            </td>
					                    </tr>
				                     </tbody>  
				                     </c:forEach>
				          			 </table>
				          			 <c:if test="${orders == null || fn:length(orders) == 0}">
								     	<span style="font-size: 10px;color: #999999">今日无异常订单。</span>
								 	 </c:if>
					 </div>
				  </div> 
				  
          </div>      
       </div>
    </div>
	      
  <!-- 弹出窗-特定时间促销-->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        <h4 class="modal-title" id="myModalLabel">添加促销时间段</h4>
      </div>
      <div class="modal-body">
			  <form action="${pageContext.request.contextPath }/marketer/promotiondate" method="post">
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
<!-- 弹出窗-特定时间促销 END -->

<!-- 弹出窗-会员级别-->
<div class="modal fade" id="myModal2" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        <h4 class="modal-title" id="myModalLabel">添加新会员级别</h4>
      </div>
      <div class="modal-body">
			  <form action="${pageContext.request.contextPath }/marketer/memberdiscount/add" method="post">
	 				 <table class="table">
					  		 <thead>
			                    <tr>
			                        <th class="text-left">级别名称</th>
			                        <th class="text-left  ">所需信用值</th>
			                        <th class="text-left  ">折扣率</th>
			                    </tr>
			                  <thead>  
			                  <tbody>
			                	 <tr>
			                        <td class="text-left  "><input name="levelName" class="form-control"> </td>
			                        <td class="text-left "><input name="creditLimit"  class="form-control"> </td>
			                        <td class="text-left  "><input name="discount"  class="form-control"> </td>
			                    </tr>
			                  </tbody>  
			           </table>
					<hr>
                   <button type="submit" class="btn btn-info btn-fill pull-right">提交</button>
                   <div class="clearfix"></div>
               </form>
		 </div>
      
    </div>
  </div>
</div>
<!-- 弹出窗-会员级别END -->


<!-- 弹出窗-VIP特定商圈折扣-->
<div class="modal fade" id="myModal3" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        <h4 class="modal-title" id="myModalLabel">添加VIP会员特定商圈优惠</h4>
      </div>
      <div class="modal-body">
			  <form action="${pageContext.request.contextPath }/marketer/VIPdistrict" method="post">
	 				 <table class="table">
					  		 <thead>
			                    <tr>
			                        <th colspan="2" class="text-left">城市</th>
			                        <th colspan="3" class="text-left ">商圈</th>
			                    </tr>
			                  <thead>  
			                  <tbody>
			                	 <tr>
			                        <td class="text-left  " colspan="2">
				                        <select class="form-control"id="city" name="cityName">
											<option value="">选择城市...</option>
											<c:forEach items="${cities}" var="city">
				        					 	<option value="${city}">${city}</option>
				         					</c:forEach>
									    </select>  
			                        </td>
			                        <td class="text-left " colspan="3">
			                        	<select class="form-control" id="district" name="districtName">
										    <option value="">商圈</option>
								        </select> 
			                         </td>
			                    </tr>
			                  </tbody>  
			                  
			                  <thead>
			                    <tr>
			                        <th class="text-left ">VIP1级</th>
			                        <th class="text-left ">VIP2级</th>
			                        <th class="text-left ">VIP3级</th>
			                        <th class="text-left ">VIP4级</th>
			                        <th class="text-left ">VIP5级</th>
			                    </tr>
			                  <thead> 
			                  <tbody>
			                	 <tr>
			                	 	<td class="text-left  "><input class="form-control" name="vip1"></td>
			                	 	<td class="text-left  "><input class="form-control" name="vip2"></td>
			                	 	<td class="text-left  "><input class="form-control" name="vip3"></td>
			                	 	<td class="text-left  "><input class="form-control" name="vip4"></td>
			                	 	<td class="text-left  "><input class="form-control" name="vip5"></td>
			                    </tr>
			                  </tbody>
			           </table>
					<hr>
                   <button type="submit" class="btn btn-info btn-fill pull-right">提交</button>
                   <div class="clearfix"></div>
               </form>
		 </div>
      
    </div>
  </div>
</div>
<!-- 弹出窗-VIP特定商圈折扣 END -->
	                 
<c:if test="${info != null }">
	<script>alert('${info}');</script>
</c:if>
	
</body>

	<script src="${pageContext.request.contextPath }/scripts/jquery.min.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath }/scripts/bootstrap.min.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath }/scripts/light-bootstrap-dashboard.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath }/scripts/laydate.js" type="text/javascript"></script>
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