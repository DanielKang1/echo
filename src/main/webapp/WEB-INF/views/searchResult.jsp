<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn"%> 
<!DOCTYPE html>
<html lang="zh-CN">
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
	<meta content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0' name='viewport' />
	<title>Echo Hotel 搜索页</title>
	<link rel="icon" type="image/png" href="${pageContext.request.contextPath }/img/hotelicon.png">
	<link href="${pageContext.request.contextPath }/css/CSSreset.min.css" rel="stylesheet"/>
	<link href="${pageContext.request.contextPath }/css/divas_free_skin.css" rel="stylesheet"/>
    <link href="${pageContext.request.contextPath }/css/bootstrap.min.css" rel="stylesheet" />
    <link href="${pageContext.request.contextPath }/css/material-kit.css" rel="stylesheet"/>
    <link href="${pageContext.request.contextPath }/css/demo.css" rel="stylesheet"/>
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
     
    <div class="wrapper" style="margin-top: -210px;">
   		
		<div class="header header-filter" style="background-image: url('${pageContext.request.contextPath }/img/signin-bg1.jpg');"></div>
		
		<div  class="main " style="background-color:white;">
		
		<form:form class="form" action="${pageContext.request.contextPath }/searchHandle"  method="POST" modelAttribute="hotelSearcher">
					<div class="col-md-4">
				     		<select class="form-control"id="city" name="city">
								<option value="">选择城市...</option>
								<c:forEach items="${cities}" var="city">
	        					 	<option value="${city}">${city}</option>
	         					</c:forEach>
						    </select> 
					</div>
					<div class="col-md-4"> 
						    <select class="form-control" id="district" name="district">
							    <option value="">商圈</option>
					        </select> 
					</div> 
					<div class="col-md-4"> 
					 		<form:input path="keyWord" type="text" placeholder="搜索：酒店名/设施服务" class="form-control"/>
					</div>
					<div class="col-md-1">   <span style="color: #999999">价格：</span>  </div>
					<div class="col-md-9">       
					        <div class="radio checkbox-inline" style="margin-top: 0px;float: left;"><label><input type="radio" name="priceRange" value="0" checked>不限</label></div>
							<div class="radio checkbox-inline" style="float: left;"><label><input type="radio" name="priceRange" value="1">150元及以下</label></div>
							<div class="radio checkbox-inline" style="float: left;"><label><input type="radio" name="priceRange" value="2">151-300元</label></div>
							<div class="radio checkbox-inline" style="float: left;"><label><input type="radio" name="priceRange" value="3">301-600元</label></div>
							<div class="radio checkbox-inline" style="float: left;"><label><input type="radio" name="priceRange" value="4">601-1000元</label></div>
							<div class="radio checkbox-inline" style="float: left;"><label><input type="radio" name="priceRange" value="5">1000元以上</label></div>
					</div>
					<div class="col-md-2">   
							 <form:input path="priceFloor" size="4"  type="text" placeholder="下限" onkeyup="value=value.replace(/[^\d]/g,'')"/>-<form:input path="priceCeiling" size="4" type="text" placeholder="上限" onkeyup="value=value.replace(/[^\d]/g,'')"/>
					</div>
					<div class="col-md-1">   <span style="color: #999999">星级：</span>  </div>
					<div class="col-md-9">
							<div class="radio checkbox-inline" style="margin-top: 0px;float: left;"><label><form:radiobutton path="starLevel" value="0"/>不限</label></div>
							<div class="radio checkbox-inline" style="float: left;"><label><form:radiobutton path="starLevel" value="2"/>二星级及以下/经济</label></div>
							<div class="radio checkbox-inline" style="float: left;"><label><form:radiobutton path="starLevel" value="3"/>三星级/舒适</label></div>
							<div class="radio checkbox-inline" style="float: left;"><label><form:radiobutton path="starLevel" value="4"/>四星级</label></div>
							<div class="radio checkbox-inline" style="float: left;"><label><form:radiobutton path="starLevel" value="5"/>五星级/豪华</label></div>
			        </div>
			        <div class="col-md-2"> 
				      <button type="submit" class="btn btn-primary" style="float: left;">提交搜索</button>
				    </div>
				     <div class="col-md-12 checkbox" style="margin-top: -30px;"> 
			      		  <label><input name="selectLived" id="selectLived"   type="checkbox"/> 只搜索入住过的酒店 </label>
			        </div>
				</form:form>
				
			<div class="row">
				 <div class="col-md-8"  style="padding-top: 15px;">
					  <div class="panel panel-default" >
					      <div class="panel-heading">
					      <div class="row">
					      	<div class="col-md-2"> 
					      	 <h3 class="panel-title">搜索结果 </h3>
					      	</div>
					      	<div class="col-md-6"> 
						      	 <a href="${pageContext.request.contextPath }/sort/price1/?city=${hotelSearcher_CS.city}&district=${hotelSearcher_CS.district}&starLevel=${hotelSearcher_CS.keyWord}&keyWord=${hotelSearcher_CS.keyWord}&priceFloor=${hotelSearcher_CS.priceFloor}&priceCeiling=${hotelSearcher_CS.priceCeiling}&selectLived=${selectLived}" class="btn-info btn-sm" title="价格由高到低排序">价格↓</a>
						      	 <a href="${pageContext.request.contextPath }/sort/price2/?city=${hotelSearcher_CS.city}&district=${hotelSearcher_CS.district}&starLevel=${hotelSearcher_CS.keyWord}&keyWord=${hotelSearcher_CS.keyWord}&priceFloor=${hotelSearcher_CS.priceFloor}&priceCeiling=${hotelSearcher_CS.priceCeiling}&selectLived=${selectLived}" class="btn-info btn-sm" title="价格由低到高排序">价格↑</a>
						      	 <a href="${pageContext.request.contextPath }/sort/starLevel/?city=${hotelSearcher_CS.city}&district=${hotelSearcher_CS.district}&starLevel=${hotelSearcher_CS.keyWord}&keyWord=${hotelSearcher_CS.keyWord}&priceFloor=${hotelSearcher_CS.priceFloor}&priceCeiling=${hotelSearcher_CS.priceCeiling}&selectLived=${selectLived}" class="btn-info btn-sm" title="按星级由高到低排序">星级排序</a>
						      	 <a href="${pageContext.request.contextPath }/sort/rating/?city=${hotelSearcher_CS.city}&district=${hotelSearcher_CS.district}&starLevel=${hotelSearcher_CS.keyWord}&keyWord=${hotelSearcher_CS.keyWord}&priceFloor=${hotelSearcher_CS.priceFloor}&priceCeiling=${hotelSearcher_CS.priceCeiling}&selectLived=${selectLived}" class="btn-info btn-sm" title="按评分由高到低排序">评分排序</a>
					      	</div>
					      </div>
					       
					       
					      </div>
					      <!-- 搜索结果 -->
					      <div class="panel-body">
				         <c:if test="${SelectionReminder != null}">
					        <span style="color:red;font-size: 10px;font-style: italic;">${SelectionReminder}</span>
					     </c:if>
					      <c:forEach items="${hotelSearchResultList}" var="hotelSearchResult">
	        				 <div class="media">
							      <a class="media-left" href="${pageContext.request.contextPath }/hotel_${hotelSearchResult.hotel.hotelID}" target="_blank">
							        <img class="media-object img-rounded" style="margin-top: 5px;" src="${pageContext.request.contextPath }/img/hotel/hotel1.jpg" >
							      </a>
							      <div class="media-body">
							        <h4 class="media-heading"><a target="_blank" href="${pageContext.request.contextPath }/hotel_${hotelSearchResult.hotel.hotelID}"><span>${hotelSearchResult.hotel.hotelName}</span></a> </h4> 
							        <div style="float: right;">
							        	<c:if test="${hotelSearchResult.minPrice == 0}">
							        	<span style="color: #999999;font-size: 10px;">未知</span></div>
							        	</c:if>
							        	<c:if test="${hotelSearchResult.minPrice != 0}">
							        	<span style="font-size: 28px;color: orange;font-weight: bold;">&yen;${hotelSearchResult.minPrice}</span>
							        	<span style="color: #999999;font-size: 10px;">起</span></div>
							        	</c:if>
							        	
							        <div class="media-content">
						                <a href="${pageContext.request.contextPath }/getHotels/city/${hotelSearchResult.hotel.city}" class="res"><span style="color: #1597B8">${hotelSearchResult.hotel.city} </span></a>  ：
						                <a href="${pageContext.request.contextPath }/getHotels/district/${hotelSearchResult.hotel.district}" class="res"><span style="color: #1597B8">${hotelSearchResult.hotel.district}</span></a>    
						                <span>${hotelSearchResult.hotel.address}</span>
						                <p>总评分：${hotelSearchResult.rating} &nbsp;&nbsp;&nbsp;<a href="" class="res"><span style="color: #1597B8">源自${hotelSearchResult.evalutionNum}位住客的点评。</span></a></p>
						                <p>
						                	<span style="font-weight: bold;">简介</span>：${hotelSearchResult.hotel.briefIntro}
						                </p>
						                <p>
						                    <span style="font-weight: bold;">设施服务</span>： ${hotelSearchResult.hotel.facility}
						                </p>
					                </div>
					                  <table class="table table-striped resroom text-center">
									      <thead>
									      <tr><td>客房类型</td> <td>价格</td><td>支付方式</td><td>预订</td></tr>
									      </thead>
									      <tbody>
									      
									      <c:forEach items="${hotelSearchResult.roomInfo}" var="roomSearchResult">
									        <tr><td>${roomSearchResult.roomTypeName }</td>
									        <td>&yen;${roomSearchResult.price}</td>
									        <td>到店支付</td>
									        <td class="text-center"><a class="btn btn-primary btn-xs" target="_blank" style="margin-top: -0px;" href="${pageContext.request.contextPath }/customer/order/hotel_${hotelSearchResult.hotel.hotelID}&roomType_${roomSearchResult.roomTypeID}">预订</a></td></tr>
									      </c:forEach> 
									      
									      <c:if test="${fn:length(hotelSearchResult.roomInfo) == 0}">
									      
									      <tr>  <td colspan="4" style="text-center">暂无满足您价格条件的客房，您可查看该酒店的其他客房类型。</td>  </tr>
									      </c:if> 
									      </tbody>
									 </table>
							      </div>
							    </div>
							    <hr>
	         					</c:forEach>
							    
					     	
						  </div>
						  <!-- 搜索结果 END -->
			   	  </div>
	       	   </div>
		       
		       	<div class="col-md-4" style="margin-top: 15px;margin-left: -20px;">
	       	 	    <div class="panel panel-default" >
					      <div class="panel-heading">
					        <h3 class="panel-title">热门推荐</h3>
					      </div>
					      <div class="panel-body">
					         <div class="media">
					         	  <a class="media-left" href="#">
							        <img class="media-object img-rounded" style="margin-top: 5px;width: 70px;height: 70px;" src="${pageContext.request.contextPath }/img/hotel/hotel6.jpg" >
							      </a>
							      <div class="media-body">
							        <p class="media-heading">南京古南都饭店</p> 
							        <div style="float: right;margin-top: -25px;">
							        	<span style="font-size: 20px;color: orange;">&yen;886</span><span style="color: #999999;font-size: 10px;">起</span>
							        </div>
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
						                <a href="#" class="res"><span style="color: #1597B8">南京</span></a>  ：
						                <a href="#" class="res"><span style="color: #1597B8"> 玄武湖湖南路地区</span></a>    
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
	<script src="${pageContext.request.contextPath }/scripts/jquery.divas-1.1.min.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath }/scripts/bootstrap.min.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath }/scripts/material.min.js"></script>
	<script src="${pageContext.request.contextPath }/scripts/material-kit.js" type="text/javascript"></script>
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
    
			$(document).ready(function()
			{
				$("#slider").divas({
					 
					slideTransitionClass: "divas-slide-transition-left",
					titleTransitionClass: "divas-title-transition-left",
					titleTransitionParameter: "left",
					titleTransitionStartValue: "-999px",
					titleTransitionStopValue: "0px",
					wingsOverlayColor: "rgba(0,0,0,0.6)"
				});
			});
		</script>
	
	
</html>