<!-- this page shows customer shopping cart details -->
<%@ page language="java" contentType="text/html" %>
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
<!-- import page header -->
<%@ include file="header.jsp" %>


    <div class="container">
    	<!-- shows the navi title -->
        <p><a href="index">Home</a> >> Check My Shopping cart</p>
        <hr/>
        <!-- displays the orders -->
        <table class="table table-hover">
	        <tr class="success">
	            <th>Category</th>
	            <th>Code</th>
	            <th>Description</th>
	            <th>Quantity</th>
	            <th>Line total</th>
	            <th></th>
	        </tr>
			<!-- print out customer's order -->
	      	<c:forEach var="ol" items="${shoppingCart }">
	        <tr>
	            <td>${ol.product.category }</td>
	            <td>${ol.product.code }</td>
	            <td>${ol.product.description }</td>
	            <td>
			        <form method="POST" action="orders?action=update">
		            <input type="hidden" name="pid" value="${ol.product.id }" /> 
		            <input type="input" name="quantity" value="${ol.quantity }" class="myQuantity"/> 
		            <input type="submit" value="Update" class="btn btn-warning"/> 
	            	</form>
	            </td>
	            <td>$ ${ol.lineTotal }</td>
	            <td>
			        <form method="POST" action="orders?action=delete">
		            <input type="hidden" name="pid" value="${ol.product.id }" /> 
		            <center><input type="submit" value="Delete" class="btn btn-danger"/></center>
	            	</form>
	            </td>
	        </tr>
	       </c:forEach>
        </table>
        <!-- end of displaying the orders -->
        
        <c:if test="${not empty emptyOrder }">
        	<blockquote>
			  <p class="text-danger">${emptyOrder }</p>
			</blockquote>
        </c:if>
        
        <hr>
        <!-- Shows the grand total of the order -->
        <h4 class="pull-right">Grand Total of My Order : $ ${grandTotal }</h4>
        
        
        <!-- cancel the entire order -->
        <div>
        <form method="POST" action="orders?action=cancel">
        	<input type="submit" class="btn btn-lg btn-warning" value="Cancle Entire Order">
        </form>
        </div>
        
        <!-- purchasing the order -->
        <a href="orders?action=purchase">
        <button type="button" class="btn btn-lg btn-danger">
	        <span class="glyphicon glyphicon-credit-card"></span> Purchase My Order
	    </button>
	    </a>
	    
        <!-- continue shopping -->
        <p class="pull-right">
	        <a href="products" class="btn btn-primary btn-lg"> 
	        <span class="glyphicon glyphicon-shopping-cart"></span> Continue shopping </a>
        </p>
        
    </div>

<!-- import page footer -->
<%@ include file="footer.jsp" %>
</body>
</html>