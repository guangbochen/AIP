<%@ page language="java" contentType="text/html" import="server.chen.guangbo.com.*, java.util.*, javax.xml.ws.BindingProvider" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Supplier Service Client</title>
<style>
	body{width:1248px; margin-left:auto; margin-right:auto; padding-top:20px;}
	h4{color:#B00000  }
</style>
</head>
<body>
<%

//retrieve order number and order status
String orderNumber = request.getParameter("orderNumber");
String status = request.getParameter("status");

//retrieve username  and password from http session
String username = "", password = "";
HttpSession sess = request.getSession();
if(!sess.isNew())
{
	username = (String)session.getAttribute("username");
	password = (String)session.getAttribute("password");
}

SupplierServerService spc = new SupplierServerService();
SupplierServer sc = spc.getSupplierServerPort();
((BindingProvider) sc).getRequestContext().put(BindingProvider.USERNAME_PROPERTY, username);
((BindingProvider) sc).getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, password);
String result = sc.updateOrder(orderNumber, status);

%>

<h1>Supplier Service Client</h1>
<h2>Updating Order Status Notice</h2>
<p>Entered Order Number: <%=orderNumber %> | Entered Order Status: <%=status %> </p>
<h3>Notice:</h3>
<h4><%=result %></h4>
<p>Click <a href="supplierClient.jsp">here</a> to go back.</p>
</body>
</html>