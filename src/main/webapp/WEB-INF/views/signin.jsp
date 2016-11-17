<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="zh-CN">
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
	<meta content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0' name='viewport' />
	<title>Echo Hotel Signin</title>
	<link rel="icon" type="image/png" href="${pageContext.request.contextPath }/img/hotelicon.png">
    <link href="${pageContext.request.contextPath }/css/bootstrap.min.css" rel="stylesheet" />
    <link href="${pageContext.request.contextPath }/css/material-kit.css" rel="stylesheet"/>
    <link href="${pageContext.request.contextPath }/css/demo.css" rel="stylesheet"/>
</head>


<body class="signup-page">

	<!-- 页面顶端 -->
	<nav class="navbar navbar-transparent navbar-absolute">
    	<div class="container">
        	<div class="navbar-header">
        		<a class="navbar-brand" href="${pageContext.request.contextPath }">Echo Hotel</a>
        	</div>
        	<div class="collapse navbar-collapse" id="navigation-example">
        		<ul class="nav navbar-nav navbar-right">
    			    <li> <a href="${pageContext.request.contextPath }/customer/signup" target="_blank"> 注册  </a> </li>
    			    <li> <a href="${pageContext.request.contextPath }" target="_blank"> 首页  </a> </li>
        		</ul>
        	</div>
    	</div>
    </nav>

    <div class="wrapper">
		<div class="header header-filter" style="background-image: url('${pageContext.request.contextPath }/img/signin-bg1.jpg'); background-size: cover; background-position: top center;">
			<div class="container">
				<div class="row">
					<div class="col-md-4 col-md-offset-4 col-sm-6 col-sm-offset-3">
						<div class="card card-signup">
							<c:if test="${requestScope.notSignin != null }"> 
								${ requestScope.notSignin }
							</c:if>
						    <!-- 登录表单 -->
							<form:form class="form" method="post" modelAttribute="login" action="${pageContext.request.contextPath }/customer/signin">
								<c:if test="${requestScope.orderUrl != null }"> 
									<input type="hidden" name="orderUrl" value="${ requestScope.orderUrl }">
								</c:if>
								<div class="header header-info text-center">
									<h4>Echo 登录</h4>
								</div>
								<p class="text-divider">
								<c:if test="${sessionScope.authCustomer == null }">
									<span style="color: #999999">尚无账号，</span><a href="${pageContext.request.contextPath }/customer/signup" style="color:rgb(3, 169, 244)">立刻注册</a><br/>
								</c:if>
								<c:if test="${sessionScope.authCustomer != null }"> 
									<span style="color: #999999">您已登录，</span><a href="${pageContext.request.contextPath }/customer/info" style="color:rgb(3, 169, 244)">${sessionScope.authCustomer.nickname }</a> <br/>
								</c:if>
								</p> <br/>
								
								<div class="content">
 
									<div class="input-group">
										<span class="input-group-addon glyphicon glyphicon-user"> </span>
										<form:input type="text" path="uservalue" class="form-control" placeholder="用户名/邮箱/手机号" />
										<form:errors path="uservalue" cssClass="error"></form:errors>
									</div>
									
									<div class="input-group">
										<span class="input-group-addon glyphicon glyphicon-log-in"> </span>
										<form:input type="password" path="pwd" class="form-control"  placeholder="密码"/>
										<form:errors path="pwd" cssClass="error"></form:errors>
									</div>
									
									<div class="input-group">
										<span class="input-group-addon">
											<img src="${pageContext.request.contextPath }/captcha-image"  id="kaptchaImage"  style="margin-bottom: -3px">
									    </span>
										<form:input type="text" path="captcha" class="form-control" placeholder="验证码"/>
										<form:errors path="captcha" cssClass="error"></form:errors>
										<!--  
										<a href="#" onclick="changeCode()"><span style="float:right;color: #ADADAD;font-size: 8px;">看不清?换一张</span></a>
										-->
									</div>
								</div>
								<div class="footer text-center">
									<input type="submit" class="btn btn-simple btn-info btn-lg" value="登录">
								</div>
							</form:form>
							<!-- 登录表单END -->
						</div>
					</div>
				</div>
			</div>

			<footer class="footer">
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

		</div>

    </div>

</body>

	<script src="${pageContext.request.contextPath }/scripts/jquery.min.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath }/scripts/bootstrap.min.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath }/scripts/material.min.js"></script>
	<script src="${pageContext.request.contextPath }/scripts/material-kit.js" type="text/javascript"></script>
	<script type="text/javascript"> 
	$(function(){  //生成验证码         
	    $('#kaptchaImage').click(function () {  
	    $(this).hide().attr('src', '${pageContext.request.contextPath }/captcha-image?' + Math.floor(Math.random()*100) ).fadeIn(); });      
	});   
	  		  
	function changeCode() {  //刷新
	    $('#kaptchaImage').hide().attr('src', '${pageContext.request.contextPath }/captcha-image?' + Math.floor(Math.random()*100) ).fadeIn();  
	    event.cancelBubble=true;  
	}  
	</script> 
</html>