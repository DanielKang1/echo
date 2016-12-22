<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %> 
<!DOCTYPE html>
<html lang="zh-CN">
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
	<meta content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0' name='viewport' />
	<meta name="author" content="Shen Jie NJU Software">
	<meta name="description" content="Just Demo.  2016/10/19 1:03  ">
	<title>Echo Hotel 主页</title>
	<link rel="icon" type="image/png" href="${pageContext.request.contextPath }/img/hotelicon.png">
	<link href="${pageContext.request.contextPath }/css/CSSreset.min.css" rel="stylesheet"/>
	<link href="${pageContext.request.contextPath }/css/divas_free_skin.css" rel="stylesheet"/>
    <link href="${pageContext.request.contextPath }/css/bootstrap.min.css" rel="stylesheet" />
    <link href="${pageContext.request.contextPath }/css/material-kit.css" rel="stylesheet"/>
    <link href="${pageContext.request.contextPath }/css/demo.css" rel="stylesheet"/>
    
    
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
   		
		<div class="header header-filter" style="background-image: url('${pageContext.request.contextPath }/img/barbg.jpg');"></div>
		
		<div  class="main" style="background-color:white;">
			<div class="profile-content">
	          <div class="container">
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

				<div style="padding-top: 35px;">
					<h3 class="text-center description">酒店主题</h3>
					<p class="description text-center">你想要的都在这里</p>
				</div>
				<div class="row">
					<div class="col-md-4">
						<div class="grid">
							<figure class="effect-lily"> <img src="${pageContext.request.contextPath }/img/t1.jpg"   />
								<figcaption>
								<h2> <span>设计师酒店</span></h2>
								<p>当顶级设计师遇上顶级酒店</p>
								</figcaption> 
							</figure>
						</div>
					</div>
					<div class="col-md-4">
						<div class="grid">
							<figure class="effect-lily"> <img src="${pageContext.request.contextPath }/img/t2.jpg"   />
								<figcaption>
								<h2>  <span>青年旅社</span></h2>
								<p>那些年我们住过的上下铺</p>
								</figcaption> 
							</figure>
						</div>
					</div>
					<div class="col-md-4">
						<div class="grid">
							<figure class="effect-lily"> <img src="${pageContext.request.contextPath }/img/t3.jpg"   />
								<figcaption>
								<h2>  <span>特色客栈</span></h2>
								<p>别致的感受很有必要</p>
								</figcaption> 
							</figure>
						</div>
					</div>
					<div class="col-md-4">
						<div class="grid">
							<figure class="effect-lily"> <img src="${pageContext.request.contextPath }/img/t4.jpg"   />
								<figcaption>
								<h2>  <span>海岛酒店</span></h2>
								<p>到碧海蓝天下用力呼吸</p>
								</figcaption> 
							</figure>
						</div>
					</div><div class="col-md-4">
						<div class="grid">
							<figure class="effect-lily"> <img src="${pageContext.request.contextPath }/img/t5.jpg"   />
								<figcaption>
								<h2>  <span>海外温泉</span></h2>
								<p>冬日暖身，悠然泡汤</p>
								</figcaption> 
							</figure>
						</div>
					</div><div class="col-md-4">
						<div class="grid">
							<figure class="effect-lily"> <img src="${pageContext.request.contextPath }/img/t6.jpg"  />
								<figcaption>
								<h2>  <span>园林酒店</span></h2>
								<p>山水树林，鸟语花香</p>
								</figcaption> 
							</figure>
						</div>
					</div>
				</div>
	        </div>
	        
	        		  
	      <div class="container">
				<div>
					<h3 class="text-center description">热门旅游目的地</h3>
					<p class="description text-center">大家推荐的好去处</p>
				</div>
				<div class="row">
					<div class="col-md-4">
						<div class="grid">
							<figure class="effect-lily"> <img src="${pageContext.request.contextPath }/img/city/beijing.jpg"   />
								<figcaption>
								<h2> <span>北京</span></h2>
								<p>天安门/鸟巢/故宫/国贸CBD/西单商业区</p>
								</figcaption> 
							</figure>
						</div>
					</div>
					<div class="col-md-4">
						<div class="grid">
							<figure class="effect-lily"> <img src="${pageContext.request.contextPath }/img/city/nanjing.jpg"   />
								<figcaption>
								<h2> <span>南京</span></h2>
								<p>新街口/夫子庙/玄武湖/中山陵/仙林大学城</p>
								</figcaption> 
							</figure>
						</div>
					</div>
					<div class="col-md-4">
						<div class="grid">
							<figure class="effect-lily"> <img src="${pageContext.request.contextPath }/img/city/shanghai.jpg"   />
								<figcaption>
								<h2> <span>上海</span></h2>
								<p>陆家嘴/外滩/浦东机场/南京路步行街/老城隍庙</p>
								</figcaption> 
							</figure>
						</div>
					</div>
					
				</div>
		  </div>   
		   <div class="container">
			   <section id="slider_wrapper" style="margin-top: 40px;">
					<div id="slider" class="divas-slider">
						<ul class="divas-slide-container">
				           <li class="divas-slide"><img src="img/slider/placeholder.gif" alt="" data-src="${pageContext.request.contextPath }/img/slider/photo/banner1.jpg" data-title="<h3>寻找下一站</h3><span>——————————<br>精选目的地推荐</span>"/></li>
				           <li class="divas-slide"><img src="img/slider/placeholder.gif" alt="" data-src="${pageContext.request.contextPath }/img/slider/photo/banner2.jpg" data-title="<h3>新酒店开业</h3><span>——————————<br>开业特惠专享</span>" /></li>
				           <li class="divas-slide"><img src="img/slider/placeholder.gif" alt="" data-src="${pageContext.request.contextPath }/img/slider/photo/banner3.jpg" data-title="<h3>日本红叶季</h3><span>——————————<br>赏枫优质酒店速速GET</span>" /></li>
					    </ul>
					    <div class="divas-navigation">
					        <span class="divas-prev">&nbsp;</span>
					        <span class="divas-next">&nbsp;</span>
					    </div>
					</div>
			  </section>
			  	<br><br>
			   <img src="${pageContext.request.contextPath }/img/promotion1.jpg" alt="img01" style="width: 1140px;height: 100px;"/>
			   <br><br>
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
	
	
	<c:if test="${requestScope.orderInfo != null }">
		${requestScope.orderInfo }
	</c:if>
	
</html>