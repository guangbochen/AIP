<!-- this page allows customer to fill in customer personal details -->
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
<!-- import page header -->
<%@ include file="header.jsp" %>

    <div class="container">
    	<!-- shows the navigation title -->
        <p><a href="index">Home</a> >> <a href="orders">Check My Shopping cart</a> >> Purchase My Order</p>
        <hr/>
        
        <form method="POST" action="purchase?action=submit">
        <table class="table table-striped purchaseTable">
        	<tr>
        		<td>Title : </td>
        		<td><input type="text" name="title" value="Mr" placeholder="${isempty}" id="title"></td>
        	</tr>
        	<tr>
        		<td>Surname : </td>
        		<td><input type="text" name="surname" value="Joes" placeholder="${isempty}" id="surname"></td>
        	</tr>
        	<tr>
        		<td>Given name : </td>
        		<td><input type="text" name="givenName" value="Blog" placeholder="${isempty}" id="gname"></td>
        	</tr>
        	<tr>
        		<td>Email name : </td>
        		<td><input type="text" name="email" value="test@email.com.au" placeholder="${isempty}" id="email"></td>
        	</tr>
        	<tr>
        		<td>House/Unit number : </td>
        		<td><input type="text" name="unit" value="20A" placeholder="${isempty}" id="unit"></td>
        	</tr>
        	<tr>
        		<td>Street : </td>
        		<td><input type="text" name="street" value="40 Kent Street" placeholder="${isempty}" id="street"></td>
        	</tr>
        	<tr>
        		<td>State : </td>
        		<td><input type="text" name="state" value="NSW" placeholder="${isempty}" id="state"></td>
        	</tr>
        	<tr>
        		<td>Suburb : </td>
        		<td><input type="text" name="suburb" value="Ultimo" placeholder="${isempty}" id="suburb"></td>
        	</tr>
        	<tr>
        		<td>Postcode : </td>
        		<td><input type="text" name="postcode" value="2000" placeholder="${isempty}" id="postcode"></td>
        	</tr>
        	<tr>
        		<td>Country : </td>
        		<td><input type="text" name="country" value="Australia" placeholder="${isempty}" id="country"></td>
        	</tr>
        	<tr>
        		<td>Credit Card Number : </td>
        		<td><input type="text" name="creditCard" value="19712312719827398173891" placeholder="${isempty}" id="card"></td>
        	</tr>
        	<tr>
        		<td></td>
        		<td><input type="submit" class="btn btn-primary" value="Purchase"></td>
        	</tr>
        <!-- 
        	<tr>
        		<td>Title : </td>
        		<td><input type="text" name="title" value="${order.title }" placeholder="${isempty}" id="title"></td>
        	</tr>
        	<tr>
        		<td>Surname : </td>
        		<td><input type="text" name="surname" value="${order.surname }" placeholder="${isempty}" id="surname"></td>
        	</tr>
        	<tr>
        		<td>Given name : </td>
        		<td><input type="text" name="givenName" value="${order.givenName}" placeholder="${isempty}" id="gname"></td>
        	</tr>
        	<tr>
        		<td>Email name : </td>
        		<td><input type="text" name="email" value="${order.email }" placeholder="${isempty}" id="email"></td>
        	</tr>
        	<tr>
        		<td>House/Unit number : </td>
        		<td><input type="text" name="unit" value="${order.unitNumber }" placeholder="${isempty}" id="unit"></td>
        	</tr>
        	<tr>
        		<td>Street : </td>
        		<td><input type="text" name="street" value="${order.street }" placeholder="${isempty}" id="street"></td>
        	</tr>
        	<tr>
        		<td>State : </td>
        		<td><input type="text" name="state" value="${order.state }" placeholder="${isempty}" id="state"></td>
        	</tr>
        	<tr>
        		<td>Suburb : </td>
        		<td><input type="text" name="suburb" value="${order.suburb }" placeholder="${isempty}" id="suburb"></td>
        	</tr>
        	<tr>
        		<td>Postcode : </td>
        		<td><input type="text" name="postcode" value="${order.postCode }" placeholder="${isempty}" id="postcode"></td>
        	</tr>
        	<tr>
        		<td>Country : </td>
        		<td><input type="text" name="country" value="${order.country }" placeholder="${isempty}" id="country"></td>
        	</tr>
        	<tr>
        		<td>Credit Card Number : </td>
        		<td><input type="text" name="creditCard" value="${order.paymentDetails }" placeholder="${isempty}" id="card"></td>
        	</tr>
        	<tr>
        		<td></td>
        		<td><input type="submit" class="btn btn-primary" value="Purchase"></td>
        	</tr>
        	 -->
	        </table>
        </form>
        
        <!-- cancel purchasing the order -->
        <div class="purchase-cancel">
        <a href="orders"> <button class="btn btn-warning">Cancel</button> </a>
        </div>
        
	</div> <!-- end of body container -->
	
	
<!-- import page footer -->
<%@ include file="footer.jsp" %>
</body>
</html>