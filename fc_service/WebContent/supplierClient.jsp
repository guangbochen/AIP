<%@ page language="java" contentType="text/html" import="server.chen.guangbo.com.*, java.util.*" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style>
	body{width:1248px; margin-left:auto; margin-right:auto; padding-top:20px;}
</style>
<title>Supplier Service Client</title>
</head>
<body>
<%! List<PaidOrder> orders; %>
<%
SupplierServerService spc = new SupplierServerService();
SupplierServer sc = spc.getSupplierServerPort();
orders = sc.listOrders();
%>

<h1>Supplier Service Client</h1>
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
</body>
</html>