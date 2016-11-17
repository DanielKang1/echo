<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
	<meta content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0' name='viewport' />
	<title>Echo Hotel Signup</title>
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
					<li> <a href="${pageContext.request.contextPath }/customer/signin" target="_blank"> 登录  </a> </li>
    			    <li> <a href="${pageContext.request.contextPath }" target="_blank"> 首页  </a> </li>
        		</ul>
        	</div>
    	</div>
    </nav>
	<!-- 页面顶端END -->
	
    <div class="wrapper">
		<div class="header header-filter" style="background-image: url('${pageContext.request.contextPath }/img/signup-bg1.jpg'); background-size: cover; background-position: top center;">
			<div class="container">
				<div class="row">
					<div class="col-md-4 col-md-offset-4 col-sm-6 col-sm-offset-3">
						<div class="card card-signup">
						<!-- 注册Form -->
						<form:form class="form" action="${pageContext.request.contextPath }/customer/signup" method="POST" modelAttribute="customer">
								<div class="header header-primary text-center">
									<h4>Echo 注册</h4>
								</div>
								<p class="text-divider"><span style="color: #999999">已有账号，</span><a href="">直接登录</a></p><br>
								<div class="content">

									<div class="input-group">
										<span class="input-group-addon glyphicon glyphicon-user"> </span>
										<form:input path="nickname" type="text" class="form-control" placeholder="用户名，长度在14位之内"/><form:errors path="nickname" cssClass="error"></form:errors> 
									</div>
									
									<div class="input-group">
										<span class="input-group-addon glyphicon glyphicon-envelope"> </span>
										<form:input  path="email" type="text" class="form-control" placeholder="邮箱"/><form:errors path="email" cssClass="error"></form:errors>
									</div>
									
									<div class="input-group">
										<span class="input-group-addon glyphicon glyphicon-phone"> </span>
										<form:input path="phone" type="text" placeholder="手机号 +86" class="form-control"/><form:errors path="phone" cssClass="error"></form:errors> 
									</div>
									
									<div class="input-group">
										<span class="input-group-addon glyphicon glyphicon-log-in"> </span>
										<form:input type="password" path="pwd" class="form-control" placeholder="密码，6至14位的字母、数字或符号"/><form:errors path="pwd" cssClass="error"></form:errors>
									</div>
									
									<div class="input-group">
										<span class="input-group-addon glyphicon glyphicon-repeat"> </span>
										<input type="password" name="confirmpwd" class="form-control" placeholder="请再次输入密码">
									</div>

									<div class="checkbox">
										<label>
											<input type="checkbox" name="optionsCheckboxes" onclick="checkbox()" id="checkbox1" checked>
											我已同意 <a href="" data-toggle="modal" data-target="#myModal">《EchoHotel服务协议》</a> 
										</label>
									</div>  
								</div>
								<div class="footer text-center">
									<input type="submit" id="submitbut" class="btn btn-simple btn-primary btn-lg" value="提交注册信息">
								</div>
							</form:form>
							<!-- 注册Form END-->
							<c:if test="${errorRegInfo != null }">
								${errorRegInfo }
							</c:if>
						</div>
					</div>
				</div>
			</div>

			<!-- 底部补充 -->
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
			<!-- 底部补充 END-->
			
		</div>

    </div>
	
	<!-- 条款弹出框 -->
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	  <div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	        <h4 class="modal-title" id="myModalLabel">EchoHotel服务条款</h4>
	      </div>
	      <div class="modal-body">
	        <h5>1．EchoHotel网服务条款的确认</h5>
		    <p>EchoHotel网的所有权与运作权归EchoHotel信息技术有限公司（以下简称“EchoHotel”）所有，涉及具体产品服务的将由相关的服务商提供。本服务条款具有法律约束力。一旦您点选“注册”并通过注册程序，即表示您自愿接受本协议之所有条款，并已成为EchoHotel网的注册会员。</p>
		    <p>用户在享用EchoHotel网络会员服务的同时，同意接受EchoHotel网络会员服务提供的各类信息服务。</p>
		    <h5>2．服务内容</h5>
		    <p>2.1　EchoHotel网服务的具体内容由EchoHotel根据实际情况提供。</p>
		    <p>2.2　EchoHotel在EchoHotel网上向其会员提供相关网络服务，与相关网络服务有关的设备（如个人电脑、手机、及其他与接入互联网或移动网有关的装置）及所需的费用（如为接入互联网而支付的电话费及上网费、为使用移动网而支付的手机费）均由会员自行负担。</p>
		    <h5>3．会员帐号及密码</h5>
		    <p>3.1 用户可通过在EchoHotel旅行网网站系统或移动客户端系统完成注册程序并通过身份认证成为会员。如会员的账号信息（包括但不限于用户名、头像、简介、个性签名等）含有不雅、不当、非法、令人反感的词汇或图像或存在以下情形的，EchoHotel可不予注册，并保留对已注册会员进行注销的权利：</p>
		    <p>(1) 冒用党和国家领导人、社会名人、国家机构或其他社会机构的真实姓名、名称、字号、艺名、笔名注册的；</p>
		    <p>(2) 包含不文明、不健康内容，或包含歧视、侮辱、猥亵类词语的；</p>
		    <p>(3) 注册易产生歧义、引起他人误解或其他不符合法律规定的账号的。</p>
		    <p>3.2 您注册会员成功后，将得到一个帐号和密码。您应妥善保管该帐号及密码，并对以该帐号进行的所有活动及事件负法律责任。因黑客行为或会员保管疏忽致使帐号、密码被他人非法使用的，EchoHotel不承担任何责任。如您发现任何非法使用会员帐号或安全漏洞的情况，请立即与EchoHotel联系。</p>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-default btn-simple" data-dismiss="modal">Close</button>
	      </div>
	    </div>
	  </div>
	</div>
	<!-- 条款弹出框END -->

</body>

	<script src="${pageContext.request.contextPath }/scripts/jquery.min.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath }/scripts/bootstrap.min.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath }/scripts/material.min.js"></script>
	<script src="${pageContext.request.contextPath }/scripts/material-kit.js" type="text/javascript"></script>
	<script type="text/javascript">
	function checkbox(){
		if(document.getElementById("checkbox1").checked)
			{document.getElementById("submitbut").disabled=false;}
		else
		    {document.getElementById("submitbut").disabled=true;}
	}
	</script>
</html>