<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>echo 注册</title>
</head>
<body>

 <form:form action="${pageContext.request.contextPath }/customer/register" method="POST" modelAttribute="customer">
 	登录邮箱:<form:input  path="email" /><form:errors path="email"></form:errors>
 	<br>
 	密码:<form:input type="password" path="pwd" /><form:errors path="pwd"></form:errors>
 	<br>
 	<%-- confirmpwd不是Customer的属性，使用form:input 会报错 --%>
 	确认密码:<input type="password" name="confirmpwd" /> 
 	<br>
 	用户名:<form:input path="nickname" /><form:errors path="nickname"></form:errors> 
 	<br>
 	手机号:+86<form:input path="phone" /><form:errors path="phone"></form:errors> 
 	<br>
 	<input type="submit" value="Submit">
 	<br>
</form:form>
	 
	 
	<c:if test="${errorRegInfo != null }">
		${errorRegInfo }
	</c:if>


</body>
</html>