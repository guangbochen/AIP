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
        <p><a href="index">Home</a> >> <a href="admin"> Admin </a> >> Updating Notice</p>
    	<hr>
    	
    	<h3>Updating Order Notice:</h3> <br>
	
    	<div class="alert alert-success">
 			<h3>${updateSuccess }<a class="btn btn-default" href="admin"> Close it !</a></h3>
    	</div>
    	
	</div>
	<!-- end of container -->
	<%@ include file="footer.jsp" %>
</body>
</html>