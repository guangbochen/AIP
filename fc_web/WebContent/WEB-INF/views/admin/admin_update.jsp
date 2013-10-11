<!-- this page updates the order status by admin -->
<%@ page language="java" contentType="text/html" import="" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>FC Sportsware</title>
    <!-- inner class css -->
    <link rel="stylesheet" type="text/css" href="assets/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="assets/style.css">
</head>
<body>

	
	
  <c:choose>
	<c:when test="${not empty admin}">
	
  	<!-- access with login as admin -->
	<%@ include file="header.jsp" %>
	<!-- admin page content -->
    <div class="container">
    	<h2>FC Sportsware Admin</h2>
	    <a href="admin?action=listOrders" class="btn btn-primary">
	    	<span class="glyphicon glyphicon-list-alt"></span> View Outstanding Orders
    	</a>
    	
    	<a href="admin?action=searchOrder" class="btn btn-primary">
	    	<span class="glyphicon glyphicon-star"></span> Update Order
    	</a>
    	<hr>
    	
   		<form method="POST" action="admin?action=searchOrder">
    	<div class="col-lg-4">
	    	<div class="input-group ${error }">
		    	<label> Searching Orders: </label>
			    	<input type="text" class="form-control" id="searchOrder" placeholder="${isempty } order number" name="nu">
		      		<span class="input-group-btn">
		        	<input type="submit" class="btn btn-info searchButton" type="button" value="Search">
		      		</span>
	    	</div><!-- /input-group -->
	  	</div><!-- /.col-lg-6 -->
		</form>
    	
    	
    	<!-- displays order and ordered products for admin -->
    	<c:if test="${not empty order and not empty ops}">
	    	<div class="mySpace"></div>
	    	
	    	<!-- displays order details -->
	    	<div class="panel panel-warning">
		    	<div class="panel-heading">
		        <h3 class="panel-title">Order Details :</h3>
		        </div>
		        <div class="panel-body">
        <form method="POST" action="admin?action=updateOrder">
        <table class="table">
        	<tr>
        		<td>Order Number : </td>
        		<td>${order.orderNumber }</td>
        		<td>Title : </td>
        		<td>${order.title }</td>
        	</tr>
        	<tr>
        		<td>Surname : </td>
        		<td>${order.surname }</td>
        		<td>Given name : </td>
        		<td>${order.givenName}</td>
        	</tr>
        	<tr>
        		<td>Email name : </td>
        		<td>${order.email }</td>
        		<td>House/Unit number : </td>
        		<td>${order.unitNumber }</td>
        	</tr>
        	<tr>
        		<td>Street : </td>
        		<td>${order.street }</td>
        		<td>State : </td>
        		<td>${order.state }</td>
        	</tr>
        	<tr>
        		<td>Suburb : </td>
        		<td>${order.suburb }</td>
        		<td>Postcode : </td>
        		<td>${order.postCode }</td>
        	</tr>
        	<tr>
        		<td>Country : </td>
        		<td>${order.country }</td>
        		<td>Credit Card Number : </td>
        		<td>${order.paymentDetails }</td>
        	</tr>
        	<tr>
                <td>Order Status : </td>
        		<td>${order.status}</td>
        		<td></td>
        		<td></td>
        	</tr>
            <tr>
                <td></td>
                <td></td>
                <td><input type="hidden" value="${order.orderNumber}" name="orderNumber"></td>
                <td> Update Order Status
                    <select class="form-control" name="status">
                    <option>ORDERED</option>
                    <option>PAID</option>
                    <option>SENT</option>
                    </select>
                </td>
            </tr>
	        </table>
            <input type="submit" class="btn btn-lg btn-warning admin-update" value="update">
        </form>
		        </div>
		    </div>
    		
    		<!-- shows ordered products -->
	    	<h4>Customer ${order.givenName } ${order.surname } has ordered the following products :</h4>
	    	<span class="sr-only">99% Complete</span>
	    	<table class="table table-condensed">
	    		<tr class="warning">
	    		<th>Category</th>
	    		<th>Code</th>
	    		<th>Description</th>
	    		<th>Quantity</th>
	    		<th>Line Total</th>
	    		</tr>
		    	<c:forEach var="op" items="${ops}">
		    	<tr>
		    		<td>${op.product.category }</td>
		    		<td>${op.product.code }</td>
		    		<td>${op.product.description }</td>
		    		<td>${op.quantity }</td>
		    		<td>$ ${op.lineTotal }</td>
		    	</tr>
		    	</c:forEach>
		    	<tr class="active">
		    		<td></td>
		    		<td></td>
		    		<td></td>
		    		<td>Grand Total:</td>
		    		<td>$ ${order.grandTotal}</td>
		    	</tr>
	    	</table>
    	</c:if>
	
	</div>
	<!-- end of container -->
	<%@ include file="footer.jsp" %>
	</c:when>
	<c:otherwise>
	<!-- invalid access to the admin page, shows warnning messag -->
	  <div class="panel panel-danger">
        <div class="panel-heading">
          <h3 class="panel-title">Warning </h3>
        </div>
        <div class="panel-body">
        <h3>Please don't trying to access this link without permission !  2013, All rights reserved by © Guangbo Chen.</h3>
        </div>
      </div>
	</c:otherwise>
  </c:choose>
	
</body>
</html>