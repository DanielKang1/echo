<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>

<!DOCTYPE html>
<html lang="zh-CN">
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
	<meta content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0' name='viewport' />
	<meta name="author" content="Shen Jie NJU Software">
	<title>Echo Hotel 酒店详情页</title>
	<link rel="icon" type="image/png" href="${pageContext.request.contextPath }/img/hotelicon.png">
	<link href="${pageContext.request.contextPath }/css/CSSreset.min.css" rel="stylesheet"/>
	<link href="${pageContext.request.contextPath }/css/divas_free_skin.css" rel="stylesheet"/>
    <link href="${pageContext.request.contextPath }/css/bootstrap.min.css" rel="stylesheet" />
    <link href="${pageContext.request.contextPath }/css/material-kit.css" rel="stylesheet"/>
    <link href="${pageContext.request.contextPath }/css/demo.css" rel="stylesheet"/>
    <link href="${pageContext.request.contextPath }/css/bootstrap-datetimepicker.min.css" rel="stylesheet"/>
    <link href="${pageContext.request.contextPath }/css/font-awesome.min.css" rel="stylesheet"/>
    
</head>

<body class="profile-page">

	<nav class="navbar navbar-transparent navbar-fixed-top navbar-color-on-scroll">
    	<div class="container">
        	<div class="navbar-header">
        		<a class="navbar-brand" href="${pageContext.request.contextPath }">Echo Hotel</a>
        	</div>
        	<div class="collapse navbar-collapse" id="navigation-example">
        		<ul class="nav navbar-nav navbar-left">
				    <li> <a href="${pageContext.request.contextPath }" target="_blank"> 热门推荐  </a> </li>
    			    <li> <a href="${pageContext.request.contextPath }" target="_blank"> 度假主题 </a> </li>
    			    <li> <a href="${pageContext.request.contextPath }" target="_blank"> 酒店探索 </a> </li>
        		</ul>
        		<ul class="nav navbar-nav navbar-right">
				   <c:if test="${sessionScope.authCustomer == null }">
    			    <li> <a href="${pageContext.request.contextPath }/customer/signin" target="_blank"> 登录  </a> </li>
    			    <li> <a href="${pageContext.request.contextPath }/customer/signup" target="_blank"> 注册  </a> </li>
                   
                   </c:if>
                    <c:if test="${sessionScope.authCustomer != null }">
                   		 <li class="dropdown">
                             <a href="#" class="dropdown-toggle" data-toggle="dropdown"><span style="font-size: 14px;"> ${sessionScope.authCustomer.nickname }</span> <b class="caret"></b> </a>
                             <ul class="dropdown-menu">
                               <li><a href="${pageContext.request.contextPath }/customer/info" target="_blank"><i class="fa fa-home"></i>我的主页</a></li>
                               <li><a href="${pageContext.request.contextPath }/all" target="_blank"><i class="fa fa-building"></i>酒店查询</a></li>
                               <li><a href="${pageContext.request.contextPath }/customer/goViewOrders" target="_blank"><i class="fa fa-th-list"></i>订单查询</a></li>
                               <li class="divider"></li>
                               <li><a href="${pageContext.request.contextPath }/customer/signout"><i class="fa fa-sign-out"></i>退出</a>
                             </ul>
                         </li>
                    </c:if>
        		</ul>
        	</div>
    	</div>
    </nav>
     
	 
    <div class="wrapper">
   		
		<div class="header header-filter" style="background-image: url('${pageContext.request.contextPath }/img/hotelbg1.jpg');"></div>
		
		<div  class="main main-raised" style="padding: 10px 10px 10px 15px;margin-top: -280px;">
				<div >
					<h3 class="text-left ">${hotel.hotelName }</h3>
					 <a href=""><span style="color:  #9c27b0">${hotel.city }</span></a>  ：
	                <a href=""><span style="color:  #9c27b0"> ${hotel.district }</span></a>    
	                <span> ${hotel.address }</span>
				</div>
				
			<div class="row">
				 <div class="col-md-8"  style="padding-top: 15px;">
				 	<!-- 客房预订 -->
					  <div class="panel panel-primary" >
					      <div class="panel-heading">
					        <h3 class="panel-title">客房预订</h3>
					      </div>
					      <div class="panel-body">
					      	 <!-- 入住日期 & 退房日期 -->
					      	 <%-- 
							 <div class="row" style="margin-top: -15px;background-color: #FBEFFE">
								<div class="col-md-2"></div>
								<div class="col-md-4">		      
									<div class="form-group"> 
						                <div class="input-group date form_date" data-date="" data-date-format="yyyy MM dd" data-link-field="dtp_input2" data-link-format="yyyy-mm-dd">
						                    <span style="font-size: 10px;">入住时间</span>
						                    <input class="form-control" id="checkin" type="text" value="" placeholder="入住日期">
						                    <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
											<span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
						                </div> 
						            </div>
						           </div>
						           <div class="col-md-4">	
						            <div class="form-group"> 
						                <div class="input-group date form_date" data-date="" data-date-format="yyyy MM dd" data-link-field="dtp_input2" data-link-format="yyyy-mm-dd">
						                    <span style="font-size: 10px;">退房时间</span>
						                    <input class="form-control" id="checkout"  type="text" value="" placeholder="退房日期">
						                    <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
											<span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
						                </div> 
						            </div>
						           </div>
						           <div class="col-md-2"></div>
						          </div>
						          --%>
						         <!-- 入住日期 & 退房日期 END -->
							     <!-- 房型  -->
								 <table class="table table-striped resroom text-center" style="margin-top: 10px;">
								      <thead>
								      <tr class="title_"><td>客房类型</td><td>最多入住</td><td>床型</td> <td>价格</td><td>支付方式</td><td>预订</td></tr>
								      </thead>
								      <tbody>
								      <c:forEach items="${roomResults}" var="roomType">
								        <tr>
									        <td>${roomType.typeName }</td>
									        <td>2人</td>
									        <td>大床</td>
									        <td style="color: orange;">&yen;${roomType.price }</td>
									        <td style="color: green;font-weight: bold;">到店支付</td>
									        <td class="text-center"><a class="btn btn-primary btn-xs" style="margin-top: -0px;" href="${pageContext.request.contextPath }/customer/order/hotel_${hotel.hotelID}&roomType_${roomType.typeID}">预订</a></td>
								        </tr>
								        </c:forEach>
								      </tbody>
								</table>
					     		<!-- 房型END  -->
						  </div>
			   	  </div>
			   	  <!-- 客房预订END -->
			   	  <!-- 酒店详情 -->
					  <div class="panel panel-primary" >
					      <div class="panel-heading"  >
					        <h3 class="panel-title">酒店详情</h3>
					      </div>
					      <div class="panel-body">
							   <table class="table table-striped resroom text-left">
							   		 <tr><td width="15%">酒店电话</td><td>025-84711888</td> </tr>
							   		 <tr><td width="15%">上网服务</td><td>公共区域提供WiFi</td> </tr>
							   		 <tr><td width="15%">停车场</td><td>酒店提供停车位</td> </tr>
									 <tr><td width="15%">开业时间</td><td>酒店开业时间 1983年 新近装修时间	2016 年</td> </tr>
									 <tr><td width="15%">酒店设施</td><td>免费Wifi、免费停车、游泳池、健身房、商务中心、会议室、中餐厅、西餐厅、接机服务（收费）</td> </tr>
									 <tr><td width="15%">酒店简介</td><td>1983年开业至今，金陵饭店多次成功地接待世界多国政要及名流巨商。尊贵宾客、高端商务、精英会议已然将金陵饭店视为首选。金陵饭店不仅屡获“中国最受欢迎十大酒店”和“中国最佳商务酒店”殊荣，更一举摘得中国服务业领域唯一中国质量管理最高奖桂冠。 无论是商务旅行或是家庭出游，深具东方情韵的金陵饭店皆能用心满足每位客人的个性化需求。</td> </tr>
							   </table>
						  </div>
			   	  </div>
			   	  <!-- 酒店详情END -->
			   	  <!-- 历史预订信息 --> 
			   	  <c:if test="${orders!= null && fn:length(orders) > 0}">
					  <div class="panel panel-primary" >
					      <div class="panel-heading"  >
					        <h3 class="panel-title">历史预订信息</h3>
					      </div>
					      <div class="panel-body">
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
						                        <td class="text-right">
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
			   	  </c:if>
			   	  <!-- 历史预订信息END -->
			   	  <!-- 评论信息 -->
					  <div class="panel panel-primary" >
					      <div class="panel-heading">
					        <h3 class="panel-title">评论信息</h3>
					      </div>
					      <div class="panel-body">
					      
					      	<c:forEach items="${alleva }" var="eva">
					      	<div class="media">
							      <div class="media-left text-center" >
							        <img class="media-object img-rounded tx" src="${pageContext.request.contextPath }/img/head/tx1.jpg" >
							        <span style="font-size: 10px;" >${eva.customerName }</span>
							      </div>
							      <div class="media-body" style="padding-left: 30px;">
							        <div id="${eva.evaluationID }"></div>
							        <div class="media-content">
					                	<span style="font-weight: bold;">优点</span>:${eva.merit } <br>
					                	<span style="font-weight: bold;">缺点</span>:${eva.demerit }  <br>
					                	<span> ${eva.comment } </span>
					                </div>
							      </div>
							    </div>
							    <hr>
					      	</c:forEach>
					      	<c:if test="${ alleva!=null && fn:length(alleva) == 0 }"> <div style="text-align:center;"><span style="font-size: 10px;color: #999999;">没有评论</span></div></c:if>
					      
					     	
						  </div>
			   	  </div>
			   	  <!-- 评论信息END -->
	       	   </div>
		       
		       	<div class="col-md-4" style="margin-top: 15px;margin-left: -20px;">
		       		<div class="panel panel-default" >
					      <div class="panel-heading">
					        <h3 class="panel-title">酒店地址</h3>
					      </div>
					      <div class="panel-body">
							     <iframe src="http://www.google.cn/maps/embed?pb=!1m18!1m12!1m3!1d64342.02720486826!2d118.75360686395183!3d32.054065292428504!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x35b58b4255794bc9%3A0x54f67b57d0a85240!2z5rGf6IuP55yB5Y2X5Lqs5biC6byT5qW85Yy6!5e0!3m2!1szh-CN!2scn!4v1476986394053" 
					     		 width="350" height="400" frameborder="0" style="border:0" allowfullscreen></iframe>
					      </div>
				    </div>
	       	 	    <div class="panel panel-default" >
					      <div class="panel-heading">
					        <h3 class="panel-title">周边酒店</h3>
					      </div>
					      <div class="panel-body">
					         <div class="media">
					         	  <a class="media-left" href="#">
							        <img class="media-object img-rounded" style="margin-top: 5px;width: 70px;height: 70px;" src="${pageContext.request.contextPath }/img/hotel/hotel6.jpg" >
							      </a>
							      <div class="media-body">
							        <p class="media-heading">南京古南都饭店</p> 
							        <div style="float: right;margin-top: -25px;"><span style="font-size: 20px;color: orange;">&yen;886</span><span style="color: #999999;font-size: 10px;">起</span></div>
							        <div class="media-content">
						                <a href="" class="res"><span style="color: #1597B8">南京</span></a>  ：
						                <a href="" class="res"><span style="color: #1597B8"> 玄武湖湖南路地区</span></a>    
						                <span>鼓楼区中央路193号。</span>
						                <p>总评分：4.8  &nbsp;&nbsp;&nbsp;<a href="" class="res"><span style="color: #1597B8">源自2814位住客的点评。</span></a></p>
						               </div>
							      </div>
							    </div>
							    
							    <div class="media">
						    	  <a class="media-left" href="#">
						        	<img class="media-object img-rounded" style="margin-top: 5px;width: 70px;height: 70px;" src="${pageContext.request.contextPath }/img/hotel/hotel3.jpg" >
						          </a>
							      <div class="media-body">
							        <p class="media-heading">南京玄武饭店</p> 
							        <div style="float: right;margin-top: -25px;"><span style="font-size: 20px;color: orange;">&yen;886</span><span style="color: #999999;font-size: 10px;">起</span></div>
							        <div class="media-content">
						                <a href="" class="res"><span style="color: #1597B8">南京</span></a>  ：
						                <a href="" class="res"><span style="color: #1597B8"> 玄武湖湖南路地区</span></a>    
						                <span>鼓楼区中央路193号。</span>
						                <p>总评分：4.8  &nbsp;&nbsp;&nbsp;<a href="" class="res"><span style="color: #1597B8">源自2814位住客的点评。</span></a></p>
						               </div>
							      </div>
							    </div>
							    
							    <div class="media">
							      <a class="media-left" href="#">
						        	<img class="media-object img-rounded" style="margin-top: 5px;width: 70px;height: 70px;" src="${pageContext.request.contextPath }/img/hotel/hotel2.jpg" >
						          </a>
							      <div class="media-body">
							        <p class="media-heading">南京香格里拉大酒店</p> 
							        <div style="float: right;margin-top: -25px;"><span style="font-size: 20px;color: orange;">&yen;886</span><span style="color: #999999;font-size: 10px;">起</span></div>
							        <div class="media-content">
						                <a href="" class="res"><span style="color: #1597B8">南京</span></a>  ：
						                <a href="" class="res"><span style="color: #1597B8"> 玄武湖湖南路地区</span></a>    
						                <span>鼓楼区中央路193号。</span>
						                <p>总评分：4.8  &nbsp;&nbsp;&nbsp;<a href="" class="res"><span style="color: #1597B8">源自2814位住客的点评。</span></a></p>
						              </div>
							      </div>
							    </div>
					      </div>
				    </div>
				    
				    <div class="panel panel-default" >
					      <div class="panel-heading">
					        <h3 class="panel-title">浏览历史</h3>
					      </div>
					      <div class="panel-body">
					         <div class="media">
					         	  <a class="media-left" href="#">
							        <img class="media-object img-rounded" style="margin-top: 5px;width: 70px;height: 70px;" src="${pageContext.request.contextPath }/img/hotel/hotel6.jpg" >
							      </a>
							      <div class="media-body">
							        <p class="media-heading">南京古南都饭店</p> 
							        <div style="float: right;margin-top: -25px;"><span style="font-size: 20px;color: orange;">&yen;886</span><span style="color: #999999;font-size: 10px;">起</span></div>
							        <div class="media-content">
						                <a href="" class="res"><span style="color: #1597B8">南京</span></a>  ：
						                <a href="" class="res"><span style="color: #1597B8"> 玄武湖湖南路地区</span></a>    
						                <span>鼓楼区中央路193号。</span>
						                <p>总评分：4.8  &nbsp;&nbsp;&nbsp;<a href="" class="res"><span style="color: #1597B8">源自2814位住客的点评。</span></a></p>
						               </div>
							      </div>
							    </div>
							    
							    <div class="media">
						    	  <a class="media-left" href="#">
						         	<img class="media-object img-rounded" style="margin-top: 5px;width: 70px;height: 70px;" src="${pageContext.request.contextPath }/img/hotel/hotel3.jpg" >
						          </a>
							      <div class="media-body">
							        <p class="media-heading">南京玄武饭店</p> 
							        <div style="float: right;margin-top: -25px;"><span style="font-size: 20px;color: orange;">&yen;886</span><span style="color: #999999;font-size: 10px;">起</span></div>
							        <div class="media-content">
						                <a href="" class="res"><span style="color: #1597B8">南京</span></a>  ：
						                <a href="" class="res"><span style="color: #1597B8"> 玄武湖湖南路地区</span></a>    
						                <span>鼓楼区中央路193号。</span>
						                <p>总评分：4.8  &nbsp;&nbsp;&nbsp;<a href="" class="res"><span style="color: #1597B8">源自2814位住客的点评。</span></a></p>
						               </div>
							      </div>
							    </div>
							    
							    <div class="media">
							      <a class="media-left" href="#">
						        	<img class="media-object img-rounded" style="margin-top: 5px;width: 70px;height: 70px;" src="${pageContext.request.contextPath }/img/hotel/hotel2.jpg" >
						          </a>
							      <div class="media-body">
							        <p class="media-heading">南京香格里拉大酒店</p> 
							        <div style="float: right;margin-top: -25px;"><span style="font-size: 20px;color: orange;">&yen;886</span><span style="color: #999999;font-size: 10px;">起</span></div>
							        <div class="media-content">
						                <a href="" class="res"><span style="color: #1597B8">南京</span></a>  ：
						                <a href="" class="res"><span style="color: #1597B8"> 玄武湖湖南路地区</span></a>    
						                <span>鼓楼区中央路193号。</span>
						                <p>总评分：4.8  &nbsp;&nbsp;&nbsp;<a href="" class="res"><span style="color: #1597B8">源自2814位住客的点评。</span></a></p>
						              </div>
							      </div>
							    </div>
					      </div>
				    </div>
		       	</div>
		       	
		      </div>
		
		
	</div>
		 
    </div>
    
     <footer class="footer"   >
        <div class="container">
           <nav class="pull-left">
              
			<ul>
				<li> <a href=""> 网站导航 </a> </li>
				<li> <a href=""> 关于EchoHotel </a> </li>
				<li> <a href=""> 广告业务</a> </li>
				<li> <a href=""> 联系我们</a> </li> 
			</ul>
           </nav>
           <div class="copyright pull-right">
              Copyright &copy; 2016, echohotel.com. all rights reserved.
           </div>
        </div>
     </footer>


</body>

	<script src="${pageContext.request.contextPath }/scripts/jquery.min.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath }/scripts/bootstrap.min.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath }/scripts/material.min.js"></script>
	<script src="${pageContext.request.contextPath }/scripts/material-kit.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath }/scripts/bootstrap-datetimepicker.min.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath }/scripts/jquery.raty.js" type="text/javascript"></script>
	
	<script type="text/javascript">
	
	$(document).ready(function(){ 
			var d = new Date();
			var lm = d.getMonth() + 1;
			var ld = d.getDate() ;
			var ld2 = d.getDate() + 1 ;
			if(lm<10) lm = "0"+lm;
			if(ld<10) ld = "0"+ld;
			var today=d.getFullYear() + '-' + lm + '-' + ld;
			var tomorrow=d.getFullYear() + '-' + lm + '-' + ld2 ;
			$('#checkin').val(today);
			$('#checkout').val(tomorrow);
		});
 
	$('.form_date').datetimepicker({
        weekStart: 1,
        todayBtn:  1,
		autoclose: 1,
		todayHighlight: 1,
		startView: 2,
		minView: 2,
		forceParse: 0,
        format: 'yyyy-mm-dd',
        minDate: new Date()
    });
	
	<c:forEach items="${alleva }" var="eva">
		$('#${eva.evaluationID }').raty({
				  readOnly : true,
				  score    : ${eva.mark }
				});
	</c:forEach>
	
	$('#star1').raty({
		  readOnly : true,
		  score    : 5
		});
	$('#star2').raty({
		  readOnly : true,
		  score    : 5
		});
	$('#star3').raty({
		  readOnly : true,
		  score    : 5
		});
</script>

	
</html>