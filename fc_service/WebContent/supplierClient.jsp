<%@ page language="java" contentType="text/html" import="server.chen.guangbo.com.*, java.util.*, javax.xml.ws.BindingProvider" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style>
	body{width:1248px; margin-left:auto; margin-right:auto; padding-top:20px;}
</style>
<title>Supplier Service Client</title>
</head>
<body>

<%
//get username and password
String message="", username="", password="";
List<PaidOrder> orders = null;
username = request.getParameter("username");
password = request.getParameter("password");

HttpSession sess = request.getSession();
if(username != null || password != null) {
	session.setAttribute("username", username );
	session.setAttribute("password", password );
}
else{
	username = (String)session.getAttribute("username");
	password = (String)session.getAttribute("password");
}


//Access supplier web service with BASIC authentication
SupplierServerService spc = new SupplierServerService();
SupplierServer sc = spc.getSupplierServerPort();
((BindingProvider) sc).getRequestContext().put(BindingProvider.USERNAME_PROPERTY, username);
((BindingProvider) sc).getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, password);


try {
	if(sc.isAuthorised() == true) 
	{
		message = "welcome, " + username;
		orders = sc.listOrders();
	}
}
catch(Exception e) {
	message = e.getMessage();
}
%>

<h1>Supplier Service Client</h1>

<p><%=message %></p>
<p>Click <a href="index.jsp">here</a> to go back.</p>

<% if(orders != null){ %>
	<h2>List of PAID Orders</h2>
	<table border="1" cellpadding="8">
		<tbody>
		<tr bgcolor="#dddddd">
			<th>Order Number</th>
			<th>Surname</th>
			<th>Total Count of Items</th>
			<th>Order Status</th>
			<th>Grand Total</th>
		</tr>
		<% for(PaidOrder o: orders) {%>
		<tr>
			<td><%=o.getOrderNumber() %></td>
			<td><%=o.getSurname() %></td>
			<td><%=o.getTotalCount() %></td>
			<td><%=o.getStatus() %></td>
			<td><%=o.getGrandTotal() %></td>
		</tr>
		<% }%>
		</tbody>
	</table>
	
	
	<h2>Update Order Status</h2>
	<form method="POST" action="updateOrder.jsp">
		<label>Order Number:</label>
		<input type="text" name="orderNumber">
		<label>Order Status:</label>
		<input type="text" name="status">
		<input type="submit" value="Submit">
	</form>
<% }%>
</body>
</html>