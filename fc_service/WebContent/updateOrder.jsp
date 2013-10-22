<%@ page language="java" contentType="text/html" import="server.chen.guangbo.com.*, java.util.*" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Supplier Service Client</title>
<style>
	body{width:1248px; margin-left:auto; margin-right:auto; padding-top:20px;}
</style>
</head>
<body>
<%
String orderNumber = request.getParameter("orderNumber");
String status = request.getParameter("status");
SupplierServerService spc = new SupplierServerService();
SupplierServer sc = spc.getSupplierServerPort();
String result = sc.updateOrder(orderNumber, status);
%>

<h2>Updating Order Status Notice</h2>
<p>Order Number: <%=orderNumber %> </p>
<p>Order Status: <%=status %> </p>
<h4>Notice:</h4>
<P><%=result %></P>
<p>Click <a href="supplierClient.jsp">here</a> to go back.</p>
</body>
</html>