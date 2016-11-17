<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>echo 登录</title>
<script type="text/javascript" src="${pageContext.request.contextPath }/scripts/jquery-2.1.1.min.js"></script>
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
</head>
<body>
<h3>登录</h3>

<form:form action="${pageContext.request.contextPath }/customer/login" method="post" modelAttribute="login" >
	<form:input type="text" path="uservalue" placeholder="手机号/用户名/邮箱"/><form:errors path="uservalue"></form:errors>
	<form:input type="password" path="pwd" /><form:errors path="pwd"></form:errors>
	<form:input type="text" path="captcha" /><form:errors path="captcha"></form:errors>
	 <br><img src="${pageContext.request.contextPath }/captcha-image"  id="kaptchaImage"  style="margin-bottom: -3px">
 <a href="#" onclick="changeCode()">看不清?换一张</a>  
	<input type="submit" value="登录">
</form:form >

</body>
</html>