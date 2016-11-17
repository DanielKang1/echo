<%@page import="org.springframework.beans.factory.annotation.Autowired"%>
<%@page import="com.echo.domain.po.Customer"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
	<meta content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0' name='viewport' />
	<meta name="author" content="Shen Jie NJU Software">
	<meta name="description" content="This is just the beginning!">
	<title>欢迎来到EchoHotel</title>
	<link rel="icon" type="image/png" href="${pageContext.request.contextPath }/img/hotelicon.png">
    <link href="${pageContext.request.contextPath }/css/bootstrap.min.css" rel="stylesheet" />
    <link href="${pageContext.request.contextPath }/css/material-kit.css" rel="stylesheet"/>
    <link href="${pageContext.request.contextPath }/css/demo.css" rel="stylesheet"/>
</head>


<body class="landing-page">

 	<!-- 首页头部 -->
    <nav class="navbar navbar-transparent navbar-absolute">
    	<div class="container">
        	<div class="navbar-header">
        		<a class="navbar-brand" href="${pageContext.request.contextPath }">Echo Hotel</a>
        	</div>
        	<div class="collapse navbar-collapse" id="navigation-example">
        		<ul class="nav navbar-nav navbar-right">
					<li> <a href="${pageContext.request.contextPath }" target="_blank"> 热门推荐  </a> </li>
    			    <li> <a href="${pageContext.request.contextPath }" target="_blank"> 度假主题 </a> </li>
    			    <li> <a href="${pageContext.request.contextPath }" target="_blank"> 酒店探索 </a> </li>
    			    <li> <a href="${pageContext.request.contextPath }/customer/signin" target="_blank"> 登录  </a> </li>
    			    <li> <a href="${pageContext.request.contextPath }/customer/signup" target="_blank"> 注册  </a> </li>
        		</ul>
        	</div>
    	</div>
    </nav>
    
   <div class="wrapper">
		<div class="header header-filter" style="background-image: url('${pageContext.request.contextPath }/img/index-bg1.jpg'); background-size: cover; background-position: top center;">
			<div class="container">
		     <div class="row">
           		<div class="col-md-6"></div>
				<div class="col-md-6">
					<h1 class="title">欢迎来到EchoHotel</h1>
	                <h5>我们可以为您提供国内500个城市酒店预订，国内超过2万家的酒店、宾馆、旅社、客栈、经济连锁、酒店公寓、青年旅社等住宿信息，
	                	覆盖酒店图片、酒店地址以及真实用户的酒店点评等。网上预订神秘特价酒店，还可体验不可思议低价惊喜。</h5>
	                <br />
	                <a href="${pageContext.request.contextPath }/all" class="btn btn-info btn-raised btn-lg"> 开始预订</a>
	                <div class="copyright pull-right" style="margin-top:150px;"> 
	               		<span>Copyright &copy; 2016, echohotel.com. all rights reserved.</span>
	           		 </div>
				</div>
           </div>
		 </div>
	  </div>
   </div>
   	
 
</body>

	<script src="${pageContext.request.contextPath }/scripts/jquery.min.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath }/scripts/bootstrap.min.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath }/scripts/material.min.js"></script>
	<script src="${pageContext.request.contextPath }/scripts/material-kit.js" type="text/javascript"></script>

</html>