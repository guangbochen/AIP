<!-- this page show an confirmation dialog once customer's order has processed successfully -->
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

    <div class="topPadding">
    <div class="container">
    	<!-- displays success processing info for customer -->
        <div class="alert alert-success notice">
        <h2>Dear Customer, You have paid your order successfully !</h2>
        <h3>Your Ordering Number is : ${orderNumber }</h3>
        <p><a class="btn btn-lg btn-info" href="index"> Close it</a></p>
      </div>
    </div>
	</div> <!-- end of body container -->
	
	
</body>
</html>
