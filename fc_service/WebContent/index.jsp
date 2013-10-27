<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style>
	body{width:1248px; margin-left:auto; margin-right:auto; padding-top:20px;}
</style>
<title>Insert title here</title>
</head>
<body>
<%
String username = "", password = "";
HttpSession sess = request.getSession();
if(!sess.isNew())
{
	username = (String)session.getAttribute("username");
	password = (String)session.getAttribute("password");
}
%>
	<h1>Supplier Service Client</h1>
	<h3>Login Form</h3>
	<form method="POST" action="supplierClient.jsp">
		Username : <input type="text" name="username" placeholder=" Enter username" value="<%=username %>"><br>
		Password : <input type="password" name="password" placeholder=" Enter password" value="<%=password %>"><br>
		<input type="submit" value="Submit">
	</form>
	 The remote user is <%= request.getRemoteUser() %>
</body>
</html>