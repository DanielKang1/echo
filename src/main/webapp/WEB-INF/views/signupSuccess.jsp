<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="zh-CN">
	<head>
		<meta charset="UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
		<meta content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0' name='viewport' />
		<title>Signup Successfully</title>
		<link rel="icon" type="image/png" href="${pageContext.request.contextPath }/img/hotelicon.png">
	    <link href="${pageContext.request.contextPath }/css/bootstrap.min.css" rel="stylesheet" />
	    <link href="${pageContext.request.contextPath }/css/material-kit.css" rel="stylesheet"/>
	    <link href="${pageContext.request.contextPath }/css/demo.css" rel="stylesheet"/>
    </head>

	<body>
		<div> 
			<h4 style="color: green;"> &nbsp;&nbsp;注册成功，请您登录。<span id="time">3</span>秒后前往登录页！ </h4>
	    </div>
	</body>
	
	<script type="text/javascript">
	    function delayURL(url) {
		var delay = document.getElementById("time").innerHTML;
		//最后的innerHTML不能丢，否则delay为一个对象 
		if (delay > 0) {
			delay--;
			document.getElementById("time").innerHTML = delay;
		} else {
			window.top.location.href = url;
		}
		setTimeout("delayURL('" + url + "')", 1000);
		//此处1000毫秒即每一秒跳转一次 
	    }
	    
	    window.onload=function(){
	    delayURL("${pageContext.request.contextPath }/customer/signin");}
    </script>
    
</html>
