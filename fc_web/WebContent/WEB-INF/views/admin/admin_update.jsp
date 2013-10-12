<!-- this page updates the order status by admin -->
<%@ page language="java" contentType="text/html" import="" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>fc_sportsware admin</title>
    <!-- inner class css -->
    <link rel="stylesheet" type="text/css" href="assets/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="assets/style.css">
</head>
<body>

  	<!-- access with login as admin -->
	<%@ include file="header.jsp" %>
	<!-- admin page content -->
    <div class="container body-wrapper">
        <p><a href="index">Home</a> >> <a href="admin"> View Outstanding Orders </a> >> Update Order</p>
    	<hr>
    	
    	<!-- searching order form -->
   		<form method="POST" action="admin?action=check" class="row">
    	<div class="col-lg-4">
	    	<div class="input-group ${error }">
		    	<label> Searching Order: </label>
			    	<input type="text" class="form-control" id="searchOrder" placeholder="${isempty } order number" name="nu">
		      		<span class="input-group-btn">
		        	<input type="submit" class="btn btn-default admin-search" type="button" value="Search">
		      		</span>
	    	</div><!-- /input-group -->
	  	</div><!-- /.col-lg-6 -->
		</form>
    	
    	<!-- displays order details and orderlines -->
    	<c:if test="${not empty order}">
	    	<!-- displays order details -->
	    	<div class="panel panel-warning">
		    	<div class="panel-heading">
		        <h3 class="panel-title"><strong>Order Details :</strong></h3>
		        </div>
		        <div class="panel-body">
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
				        </table> <hr>
	        
	        			<!-- update order status form-->
				        <form method="POST" action="admin?action=update" class="form pull-right">
					        <label>Update Order Status</label>
		                    <select class="form-control" name="status">
			                    <option>ORDERED</option>
			                    <option>PAID</option>
			                    <option>SENT</option>
		                    </select>
			                <input type="hidden" value="${order.orderNumber}" name="orderNumber"></td>
				            <input type="submit" class="btn lg btn-warning admin-update" value="update">
				        </form>
		        </div><!-- end panel body -->
		    </div><!-- end panel -->
    		
    		<!-- shows all orderlines -->
	    	<h4>Customer [${order.givenName } ${order.surname }] has following orderlines for order "${order.orderNumber}" :</h4>
	    	<span class="sr-only">99% Complete</span>
	    	<table class="table table-condensed">
	    		<tr class="warning">
	    		<th>Category</th>
	    		<th>Code</th>
	    		<th>Description</th>
	    		<th>Quantity</th>
	    		<th>Line Total</th>
	    		</tr>
		    	<c:forEach var="ol" items="${order.orderlines}">
		    	<tr>
		    		<td>${ol.product.category }</td>
		    		<td>${ol.product.code }</td>
		    		<td>${ol.product.description }</td>
		    		<td>${ol.quantity }</td>
		    		<td>$ <fmt:formatNumber minFractionDigits="2" value=" ${ol.lineTotal }"></fmt:formatNumber></td>
		    	</tr>
		    	</c:forEach>
		    	<tr class="warning">
		    		<td></td>
		    		<td></td>
		    		<td></td>
		    		<td><strong>Grand Total:</strong></td>
			    	<c:forEach var="ol" items="${order.orderlines }">
			    		<c:set var="grandTotal" value="${grandTotal + ol.lineTotal }" />
		    		</c:forEach>
		    		<td>$ <fmt:formatNumber minFractionDigits="2" value=" ${grandTotal }"></fmt:formatNumber></td>
		    	</tr>
	    	</table>
    	</c:if>
	</div>
	
	<!-- end of container -->
	<%@ include file="footer.jsp" %>
</body>
</html>