<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<center>
<h2>Welcome ${customer.name}</h2><br>
<h2>Your bank details are:</h2>
<center>
<table>
<tr>
<td><b>NAME</b></td>
<td>${customer.name}</td>
</tr>
<tr>
<td><b>MOBILE NUMBER</b></td>
<td>${customer.mobileNo}</td>
</tr>
<tr>
<td><b>BALANCE</b></td>
<td>${customer.wallet.balance}</td>
</tr>
</table>
</center>
<a href="login">Return to login Page</a>
</center>
</body>
</html>