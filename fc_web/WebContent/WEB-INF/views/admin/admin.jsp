<!--  this page is default admin home page -->
<%@ page language="java" contentType="text/html" import="" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>FC Sportsware</title>
    <!-- inner class css -->
    <link rel="stylesheet" type="text/css" href="assets/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="assets/style.css">
</head>
<body>

  	<!-- access with login as admin -->
	<%@ include file="header.jsp" %>
	<!-- admin page content -->
    <div class="container body-wrapper">
        <p><a href="index">Home</a> >> Admin</p>
    	<hr>
    	
    	<h3>Outstanding Orders:</h3> <br>
    	<!-- displays outstanding orders -->
    	<table class="table table-bordered table-striped">
    		<tr class="warning">
    		<th>Order Number</th>
    		<th>Surname</th>
    		<th>Country</th>
    		<th>Postcode</th>
    		<th>Grand Total of Order</th>
    		<th>Status Code</th>
    		</tr>
	    	<c:forEach var="order" items="${orders}">
	    		<c:set var="grandTotal" value="0" />
	    	<tr>
	    		<td><a href="admin?action=check&nu=${order.orderNumber }">${order.orderNumber }</a></td>
	    		<td>${order.surname }</td>
	    		<td>${order.country }</td>
	    		<td>${order.postCode }</td>
	    		<!-- calculate the grandtotal via adding all the linetotal -->
		    	<c:forEach var="ol" items="${order.orderlines }">
		    		<c:set var="grandTotal" value="${grandTotal + ol.lineTotal }" />
	    		</c:forEach>
	    		<td>$ <fmt:formatNumber minFractionDigits="2" value=" ${grandTotal }"></fmt:formatNumber></td>
	    		<td>${order.status }</td>
	    	</tr>
	    	</c:forEach>
    	</table>
	
	</div>
	<!-- end of container -->
	<%@ include file="footer.jsp" %>
</body>
</html>