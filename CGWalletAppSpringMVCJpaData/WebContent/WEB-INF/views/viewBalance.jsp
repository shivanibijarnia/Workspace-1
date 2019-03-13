<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<center>
<h1>Welcome to Login Page</h1>
<table>
<form:form action="loginCustomer" method="post" modelAttribute="customer">
<tr>
<td>Mobile number:</td>
<td><form:input path="mobileNo" size="30"/></td>
<td><form:errors path="mobileNo" cssClass="error"/></td>
</tr>
<tr>
<td><input type="submit" value="submit"/></td>
</tr>
</form:form>
<a href="login">Return to login Page</a>
</body>
</html>